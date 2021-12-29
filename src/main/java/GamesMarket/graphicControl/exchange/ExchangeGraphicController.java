package GamesMarket.graphicControl.exchange;

import GamesMarket.control.exchange.ExchangeController;
import GamesMarket.graphicControl.navigation.NavigationButtons;
import GamesMarket.main.Main;
import GamesMarket.model.ExchangePost;
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

public class ExchangeGraphicController extends NavigationButtons implements Initializable {
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

    private List<ExchangePost> exchangePosts = new ArrayList<>();
    private ExchangeController exchangeController = new ExchangeController();


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        exchangePosts.addAll(exchangeController.retrieveExchange());
        int column = 0;
        int row = 1;

        if (User.getInstance().isLoggedIn() || ShopOwner.getInstance().isLoggedIn()) {
            signInButton.setVisible(false);
            signInButton.isDisabled();
        }

        try {
            for (int i = 0; i < exchangePosts.size(); i++) {

                FXMLLoader fxmlLoader = new FXMLLoader();
                fxmlLoader.setLocation(Main.class.getResource("/GamesMarket/exchangeItem.fxml"));
                AnchorPane anchorPane = fxmlLoader.load();

                ExchangeItemGraphicController itemController = fxmlLoader.getController();
                itemController.setData(exchangePosts.get(i));

                if (column == 5) {
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

                GridPane.setMargin(anchorPane, new Insets(20));

            }
        } catch(IOException e) {
            e.printStackTrace();
        }
    }

}
