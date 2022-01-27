package gamesmarket.graphiccontrol;

import gamesmarket.bean.GameBean;
import gamesmarket.control.GamesTableController;
import gamesmarket.exceptions.DuplicatedGameException;
import gamesmarket.exceptions.ErrorMessage;
import gamesmarket.model.Game;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.net.URL;
import java.sql.SQLException;
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
    private String noGameSelected = "No game selected";
    private String invalidPrice = "Invalid price";

    @FXML
    private void close(ActionEvent event) {
        Stage stage = (Stage) ((Button) event.getSource()).getScene().getWindow();
        stage.close();
    }


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        List<Game> games = this.retrieveGames();

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

    public List<Game> retrieveGames() {
        List<Game> games = new ArrayList<>();
        try {
            List<GameBean> beans = gamesTableController.retrieveGames();

            for (int i = 0; i < beans.size(); i++) {
                Game g = new Game();
                g.setName(beans.get(i).getGameName());
                g.setPlatform(beans.get(i).getGamePlatform());
                g.setGenre(beans.get(i).getGameGenre());
                g.setYear(beans.get(i).getGameYear());
                g.setDescription(beans.get(i).getGameDescription());

                games.add(g);
            }
        } catch (SQLException e) {
            ErrorMessage.displayErrorMessage();
        }

        return games;
    }

    public void addToTradelist() {
        try {
            if (selectedGame == null)
                label.setText(noGameSelected);
            else {
                label.setText(selectedGame.getName() + " added to Tradelist");
                GameBean gameBean = new GameBean();
                gameBean.setGameName(selectedGame.getName());
                gameBean.setGamePlatform(selectedGame.getPlatform());
                gamesTableController.addToTradelist(gameBean);
            }
        } catch (DuplicatedGameException e) {
            label.setText("This game is already present in your tradelist.");
        } catch (SQLException e) {
            ErrorMessage.displayErrorMessage();
        }
    }

    public void addToWishlist() {
        try {
            if (selectedGame == null)
                label.setText(noGameSelected);
            else {
                label.setText(selectedGame.getName() + " added to Wishlist");
                GameBean gameBean = new GameBean();
                gameBean.setGameName(selectedGame.getName());
                gameBean.setGamePlatform(selectedGame.getPlatform());
                gamesTableController.addToWishlist(gameBean);
            }
        } catch (DuplicatedGameException e) {
            label.setText("This game is already present in your wishlist.");
        } catch (SQLException e) {
            ErrorMessage.displayErrorMessage();
        }
    }

    public void addToShop() {
        double prc;

        try {
            prc = Double.parseDouble(price.getText());
        } catch (NullPointerException | NumberFormatException e) {
            label1.setText(invalidPrice);
            prc = -1.0;
        }
        if (selectedGame == null)
            label1.setText(noGameSelected);
        else if (price.getText().isEmpty())
            label1.setText("No price set");
        else {
            label1.setText(selectedGame.getName() + " added");
        }

        if (prc >= 0) {
            try {
                GameBean gameBean = new GameBean();
                gameBean.setGameName(selectedGame.getName());
                gameBean.setGamePlatform(selectedGame.getPlatform());
                gameBean.setGamePrice(prc);
                gamesTableController.putForSale(gameBean);
            } catch (DuplicatedGameException e) {
                label1.setText("This game is already present in your shop");
            } catch (SQLException e) {
                ErrorMessage.displayErrorMessage();
            }
        } else {
            label1.setText(invalidPrice);
        }
    }

    public void mouseClicked() {
        selectedGame = table.getSelectionModel().getSelectedItem();
    }
}
