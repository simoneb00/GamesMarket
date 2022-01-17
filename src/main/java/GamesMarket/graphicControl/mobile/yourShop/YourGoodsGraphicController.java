package GamesMarket.graphicControl.mobile.yourShop;

import GamesMarket.bean.GameBean;
import GamesMarket.control.YourShopController;
import GamesMarket.exceptions.ErrorMessage;
import GamesMarket.graphicControl.mobile.ShopOwnerNavigationButtons;
import GamesMarket.main.Main;
import GamesMarket.model.Game;
import GamesMarket.model.Order;
import GamesMarket.model.ShopOwner;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

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
    private Parent root;
    private Scene scene;
    private Stage stage;

    @FXML
    void addNewGame(ActionEvent event) {

        try {
            root = FXMLLoader.load(Main.class.getResource("/GamesMarket/mobile/add_game_to_shop.fxml"));
            stage = (Stage)((Node)event.getSource()).getScene().getWindow();
            scene = new Scene(root);

            scene.getStylesheets().clear();
            scene.getStylesheets().add("file:///C:/Users/Simone%20Bauco/IdeaProjects/GamesMarket/src/main/java/GamesMarket/css/style.css");

            stage.setScene(scene);
            stage.show();

        } catch (Exception e) {
            e.printStackTrace();
            e.getCause();
        }

    }

    @FXML
    void removeSelected(ActionEvent event) {
        YourShopController yourShopController = new YourShopController();
        GameBean gameBean = new GameBean();

        gameBean.setName(selectedGame.getName());
        gameBean.setPlatform(selectedGame.getPlatform());
        gameBean.setPrice(selectedGame.getPrice());

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

        String name, platform, string;
        double price;

        for (int i = 0; i < games.size(); i++) {
            gamesObservableList.add(games.get(i));
        }

        gameCol.setCellValueFactory(new PropertyValueFactory<>("name"));
        platformCol.setCellValueFactory(new PropertyValueFactory<>("platform"));
        priceCol.setCellValueFactory(new PropertyValueFactory<>("price"));

        table.setItems(gamesObservableList);
    }

    public void mouseClicked() {
        selectedGame = table.getSelectionModel().getSelectedItem();
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
