package GamesMarket.graphicControl;

import GamesMarket.bean.GameBean;
import GamesMarket.control.profile.GamesTableController;
import GamesMarket.model.Game;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class GamesTableGraphicController implements Initializable {

    @FXML
    private TableColumn<Game, String> description;
    @FXML
    private TableColumn<Game, String> genre;
    @FXML
    private TableColumn<Game, String> name;
    @FXML
    private TableColumn<Game, String> platform;
    @FXML
    private TableColumn<Game, String> year;
    @FXML
    private TableView<Game> table;
    @FXML
    private Label label;
    @FXML
    private Label label1;
    @FXML
    private TextField price;


    private ObservableList<Game> gameObservableList = FXCollections.observableArrayList();
    private Game selectedGame = null;
    private GamesTableController gamesTableController = new GamesTableController();

    @FXML
    private void close(ActionEvent event) {
        Stage stage = (Stage) ((Button)event.getSource()).getScene().getWindow();
        stage.close();
    }


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        GamesTableController gamesTableController = new GamesTableController();
        List<Game> games = gamesTableController.retrieveGames();

        for (int i = 0; i < games.size(); i++) {
            gameObservableList.add(games.get(i));
        }

        name.setCellValueFactory(new PropertyValueFactory<>("name"));
        year.setCellValueFactory(new PropertyValueFactory<>("year"));
        genre.setCellValueFactory(new PropertyValueFactory<>("genre"));
        platform.setCellValueFactory(new PropertyValueFactory<>("platform"));
        description.setCellValueFactory(new PropertyValueFactory<>("description"));

        table.setItems(gameObservableList);

    }

    public void addToTradelist() {
        if (selectedGame == null)
            label.setText("No game selected");
        else {
            label.setText(selectedGame.getName() + " added to Tradelist");
            GameBean gameBean = new GameBean();
            gameBean.setName(selectedGame.getName());
            gameBean.setPlatform(selectedGame.getPlatform());
            gamesTableController.addToTradelist(gameBean);
        }
    }

    public void addToWishlist() {
        if (selectedGame == null)
            label.setText("No game selected");
        else {
            label.setText(selectedGame.getName() + " added to Wishlist");
            GameBean gameBean = new GameBean();
            gameBean.setName(selectedGame.getName());
            gameBean.setPlatform(selectedGame.getPlatform());
            gamesTableController.addToWishlist(gameBean);
        }
    }

    public void add() {

        double prc;

        try {
            prc = Double.parseDouble(price.getText());
        } catch (NullPointerException e) {
            label1.setText("Invalid price");
            prc = -1.0;
        } catch (NumberFormatException e) {
            label1.setText("Invalid price");
            prc = -1.0;
        }
        if (selectedGame == null)
            label1.setText("No game selected");
        else if (price.getText().isEmpty())
            label1.setText("No price set");
        else {
            label1.setText(selectedGame.getName() + " added");
        }

        if (prc >= 0) {
            GameBean gameBean = new GameBean();
            gameBean.setName(selectedGame.getName());
            gameBean.setPlatform(selectedGame.getPlatform());
            gameBean.setPrice(prc);
            gamesTableController.putForSale(gameBean);
        } else {
            label1.setText("Invalid price");
        }
    }

    public void mouseClicked() {
        selectedGame = table.getSelectionModel().getSelectedItem();
    }
}
