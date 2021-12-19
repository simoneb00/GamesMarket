package GamesMarket.graphicControl.exchange;

import GamesMarket.graphicControl.shop.ItemGraphicController;
import GamesMarket.main.Main;
import GamesMarket.model.Game;
import GamesMarket.model.ShopOwner;
import GamesMarket.model.User;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Region;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class ExchangeGraphicController implements Initializable {
    @FXML
    private AnchorPane anchorPane;

    @FXML
    private GridPane grid;

    @FXML
    private ScrollPane scroll;

    @FXML
    private Button signInButton;

    private Parent root;
    private Scene scene;
    private Stage stage;

    private List<Game> games = new ArrayList<>();

    private List<Game> getData() {
        List<Game> games = new ArrayList<>();
        Game game;


        for (int i=0; i< 25; i++) {
            game = new Game();
            game.setName("FIFA 22");
            game.setImgSrc("/images/FIFA22.jpg");
            games.add(game);
        }


        return games;
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        games.addAll(getData());
        int column = 0;
        int row = 1;

        if (User.getInstance().isLoggedIn() || ShopOwner.getInstance().isLoggedIn()) {
            signInButton.setVisible(false);
            signInButton.isDisabled();
        }

        try {
            for (int i = 0; i < games.size(); i++) {
                FXMLLoader fxmlLoader = new FXMLLoader();
                fxmlLoader.setLocation(Main.class.getResource("/GamesMarket/exchangeItem.fxml"));
                AnchorPane anchorPane = fxmlLoader.load();

                ExchangeItemGraphicController itemController = fxmlLoader.getController();
                itemController.setData(games.get(i));

                if (column == 3) {
                    column = 0;
                    row++;
                }

                grid.add(anchorPane, column++, row);

                grid.setMinWidth(Region.USE_COMPUTED_SIZE);
                grid.setPrefWidth(Region.USE_COMPUTED_SIZE);
                grid.setMaxWidth(Region.USE_PREF_SIZE);

                grid.setMinHeight(Region.USE_COMPUTED_SIZE);
                grid.setPrefHeight(Region.USE_COMPUTED_SIZE);
                grid.setMaxHeight(Region.USE_PREF_SIZE);

                GridPane.setMargin(anchorPane, new Insets(10));

            }
        } catch(IOException e) {
            e.printStackTrace();
        }
    }
}
