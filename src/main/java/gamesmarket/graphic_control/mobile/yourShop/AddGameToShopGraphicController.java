package gamesmarket.graphic_control.mobile.yourShop;

import gamesmarket.bean.GameBean;
import gamesmarket.control.GamesTableController;
import gamesmarket.exceptions.DuplicatedGameException;
import gamesmarket.exceptions.ErrorMessage;
import gamesmarket.graphic_control.mobile.ShopOwnerNavigationButtons;
import gamesmarket.main.Main;
import gamesmarket.model.Game;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class AddGameToShopGraphicController extends ShopOwnerNavigationButtons implements Initializable {

    @FXML
    private ChoiceBox<String> gameChoiceBox;
    @FXML
    private TextField priceTF;
    @FXML
    private Label label;

    private GamesTableController gamesTableController = new GamesTableController();

    @FXML
    void add() {
        String game = gameChoiceBox.getValue();
        String priceString = priceTF.getText();

        try {
            if (game.isEmpty())
                ErrorMessage.displayEmptyGameNameMessage();

            double price = Double.parseDouble(priceString);

            if (price < 0) {
                ErrorMessage.displayInvalidPriceMessage();
                return;
            }

            String result[] = game.split(" - ");
            String name = result[0];
            String platform = result[1];

            System.out.println(name);
            System.out.println(platform);

            GameBean gameBean = new GameBean();
            gameBean.setName(name);
            gameBean.setPlatform(platform);
            gameBean.setPrice(price);

            gamesTableController.putForSale(gameBean);

            label.setText(name + " for " + platform + " added to your shop");

        } catch (SQLException e) {
            ErrorMessage.displayErrorMobile();
        } catch (DuplicatedGameException e) {
            ErrorMessage.displayDuplicatedGameInShopError();
        } catch (NumberFormatException e) {
            ErrorMessage.displayEmptyPriceMessage();
        }
    }

    private List<Game> retrieveGames() {
        List<Game> games = new ArrayList<>();
        try {
            List<GameBean> beans = gamesTableController.retrieveGames();

            for (int i = 0; i < beans.size(); i++) {
                Game game = new Game();
                game.setName(beans.get(i).getName());
                game.setPlatform(beans.get(i).getPlatform());
                game.setGenre(beans.get(i).getGenre());
                game.setYear(beans.get(i).getYear());
                game.setDescription(beans.get(i).getDescription());

                games.add(game);
            }
        } catch (SQLException e) {
            ErrorMessage.displayErrorMessage();
        }

        return games;
    }

    private void setGameCB() {
        List<Game> games = this.retrieveGames();

        for (int i = 0; i < games.size(); i++) {
            gameChoiceBox.getItems().add(games.get(i).getName() + " - " + games.get(i).getPlatform());
        }
    }

    @FXML
    public void back(ActionEvent event) {
        try {
            Parent root = FXMLLoader.load(Main.class.getResource("/gamesmarket/mobile/your_goods.fxml"));
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            Scene scene = new Scene(root);

            scene.getStylesheets().clear();
            scene.getStylesheets().add("file:///C:/Users/Simone%20Bauco/IdeaProjects/GamesMarket/src/main/java/GamesMarket/css/style.css");

            stage.setScene(scene);
            stage.show();

        } catch (Exception e) {
            e.printStackTrace();
            e.getCause();
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        this.setGameCB();
    }
}
