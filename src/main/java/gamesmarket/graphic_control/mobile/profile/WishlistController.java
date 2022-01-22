package gamesmarket.graphic_control.mobile.profile;

import gamesmarket.bean.GameBean;
import gamesmarket.control.profile.UserProfileController;
import gamesmarket.exceptions.ErrorMessage;
import gamesmarket.graphic_control.mobile.NavigationButtons;
import gamesmarket.main.Main;
import gamesmarket.model.User;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.stage.Stage;

import java.net.URL;
import java.sql.SQLException;
import java.util.List;
import java.util.Objects;
import java.util.ResourceBundle;

public class WishlistController extends NavigationButtons implements Initializable {

    @FXML
    private ListView<String> wishlist;
    @FXML
    private Button removeSelected;

    private String selectedGame;
    private String selectedPlatform;
    private UserProfileController userProfileController = new UserProfileController();


    private void retrieveWishlist() {
        List<String> wl = User.getInstance().getWishlist();

        if (wl != null) {
            for (int i = 0; i < wl.size(); i++) {
                wishlist.getItems().add(wl.get(i));
            }
        }
    }

    @FXML
    public void mouseClicked() {
        String wishlistSelected = wishlist.getSelectionModel().getSelectedItems().toString();
        if (!Objects.equals(wishlistSelected, "[]")) {
            wishlistSelected = wishlistSelected.replaceAll("\\[|\\]", "");
            String[] strings = wishlistSelected.split(" - ");
            selectedGame = strings[0];
            selectedPlatform = strings[1];
            removeSelected.setVisible(true);
            removeSelected.setDisable(false);
        }
    }

    @FXML
    public void removeSelectedGame() {
        try {
            GameBean gameBean = new GameBean();
            gameBean.setGamePlatform(selectedPlatform);
            gameBean.setGameName(selectedGame);

            userProfileController.removeFromWishlist(gameBean);

            removeSelected.setDisable(true);
            removeSelected.setVisible(false);

            wishlist.getItems().clear();
            this.retrieveWishlist();

        } catch (SQLException e) {
            ErrorMessage.displayErrorMessage();
        }
    }

    @FXML
    public void addGame(ActionEvent event) {
        try {
            Parent root = FXMLLoader.load(Main.class.getResource("/gamesmarket/mobile/games_table.fxml"));
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
        this.retrieveWishlist();
    }
}
