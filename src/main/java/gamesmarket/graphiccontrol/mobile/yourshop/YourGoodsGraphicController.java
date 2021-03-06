package gamesmarket.graphiccontrol.mobile.yourshop;

import gamesmarket.bean.GameBean;
import gamesmarket.control.YourShopController;
import gamesmarket.exceptions.ErrorMessage;
import gamesmarket.graphiccontrol.mobile.ShopOwnerNavigationButtons;
import gamesmarket.main.Main;
import gamesmarket.model.Game;
import gamesmarket.model.ShopOwner;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

import java.net.URL;
import java.sql.SQLException;
import java.util.List;
import java.util.ResourceBundle;

public class YourGoodsGraphicController extends ShopOwnerNavigationButtons implements Initializable {

    @FXML
    private TableColumn<Game, String> gameCol;
    @FXML
    private TableColumn<Game, String> platformCol;
    @FXML
    private TableColumn<Game, String> priceCol;
    @FXML
    private Button removeSelectedButton;
    @FXML
    private TableView<Game> table;

    private Game selectedGame = null;

    @FXML
    void addNewGame(ActionEvent event) {

        try {
            Parent root = FXMLLoader.load(Main.class.getResource("/gamesmarket/mobile/add_game_to_shop.fxml"));
            this.show(root, event);

        } catch (Exception e) {
            ErrorMessage.displayErrorMobile();
        }

    }

    @FXML
    void removeSelected() {
        YourShopController yourShopController = new YourShopController();
        GameBean gameBean = new GameBean();

        gameBean.setGameName(selectedGame.getName());
        gameBean.setGamePlatform(selectedGame.getPlatform());
        gameBean.setGamePrice(selectedGame.getPrice());

        try {
            yourShopController.removeGame(gameBean);
        } catch (SQLException e) {
            ErrorMessage.displayErrorMessage();
        }

        table.getItems().clear();
        this.initialize(null, null);

        removeSelectedButton.setVisible(false);
        removeSelectedButton.setDisable(true);
    }

    private void retrieveGoods() {
        List<Game> games = ShopOwner.getInstance().getShop().getGames();
        ObservableList<Game> gamesObservableList = FXCollections.observableArrayList();


        for (int i = 0; i < games.size(); i++) {
            gamesObservableList.add(games.get(i));
        }

        gameCol.setCellValueFactory(new PropertyValueFactory<>("name"));
        platformCol.setCellValueFactory(new PropertyValueFactory<>("platform"));
        priceCol.setCellValueFactory(new PropertyValueFactory<>("price"));

        table.setItems(gamesObservableList);
    }

    @FXML
    public void mouseClicked() {
        selectedGame = table.getSelectionModel().getSelectedItem();     // gets selected game
        if (selectedGame != null) {
            removeSelectedButton.setDisable(false);
            removeSelectedButton.setVisible(true);
        }
    }


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        this.retrieveGoods();
    }
}
