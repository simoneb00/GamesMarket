package gamesmarket.graphic_control.mobile.profile;

import gamesmarket.bean.GameBean;
import gamesmarket.exceptions.DuplicatedGameException;
import gamesmarket.exceptions.ErrorMessage;
import gamesmarket.graphic_control.mobile.NavigationButtons;
import gamesmarket.model.Game;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class GamesTableGraphicController extends NavigationButtons implements Initializable {

    @FXML
    private TableColumn<Game, String> gameCol;
    @FXML
    private TableColumn<Game, String> platformCol;
    @FXML
    private TableView<Game> table;
    @FXML
    private Button tradelistButton;
    @FXML
    private Button wishlistButton;

    private gamesmarket.control.GamesTableController gamesTableController = new gamesmarket.control.GamesTableController();
    private ObservableList<Game> gameObservableList = FXCollections.observableArrayList();
    private Game selectedGame = null;


    private List<Game> retrieveGames() {
        List<Game> games = new ArrayList<>();
        try {
            List<GameBean> beans = gamesTableController.retrieveGames();

            for (int i = 0; i < beans.size(); i++) {
                Game game = new Game();
                game.setName(beans.get(i).getGameName());
                game.setPlatform(beans.get(i).getGamePlatform());
                game.setGenre(beans.get(i).getGameGenre());
                game.setYear(beans.get(i).getGameYear());
                game.setDescription(beans.get(i).getGameDescription());

                games.add(game);
            }
        } catch (SQLException e) {
            ErrorMessage.displayErrorMessage();
        }

        return games;
    }


    private void retrieveTable() {
        List<Game> games = this.retrieveGames();

        for (int i = 0; i < games.size(); i++) {
            gameObservableList.add(games.get(i));
        }

        gameCol.setCellValueFactory(new PropertyValueFactory<>("name"));
        platformCol.setCellValueFactory(new PropertyValueFactory<>("platform"));

        table.setItems(gameObservableList);
    }

    @FXML
    public void addToTradelist() {
        try {
            if (selectedGame == null)
                return;
            else {
                tradelistButton.setDisable(true);
                tradelistButton.setVisible(false);
                wishlistButton.setDisable(true);
                wishlistButton.setVisible(false);
                GameBean gameBean = new GameBean();
                gameBean.setGameName(selectedGame.getName());
                gameBean.setGamePlatform(selectedGame.getPlatform());
                gamesTableController.addToTradelist(gameBean);
            }
        } catch (DuplicatedGameException e) {
            ErrorMessage.displayDuplicatedGameInTradelist();
        } catch (SQLException e) {
            ErrorMessage.displayErrorMessage();
        }
    }

    @FXML
    public void addToWishlist() {
        try {
            if (selectedGame == null)
                return;
            else {
                wishlistButton.setDisable(true);
                wishlistButton.setVisible(false);
                tradelistButton.setVisible(false);
                tradelistButton.setDisable(true);
                GameBean gameBean = new GameBean();
                gameBean.setGameName(selectedGame.getName());
                gameBean.setGamePlatform(selectedGame.getPlatform());
                gamesTableController.addToWishlist(gameBean);
            }
        } catch (DuplicatedGameException e) {
            ErrorMessage.displayDuplicatedGameInWishlist();
        } catch (SQLException e) {
            ErrorMessage.displayErrorMessage();
        }
    }

    @FXML
    public void mouseClicked() {
        selectedGame = table.getSelectionModel().getSelectedItem();
        tradelistButton.setVisible(true);
        tradelistButton.setDisable(false);
        wishlistButton.setVisible(true);
        wishlistButton.setDisable(false);
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        this.retrieveTable();
    }
}
