package GamesMarket.graphicControl.profile;

import GamesMarket.bean.GameBean;
import GamesMarket.control.profile.GamesTableController;
import GamesMarket.model.Game;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
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

    public void mouseClicked() {
        selectedGame = table.getSelectionModel().getSelectedItem();
    }
}
