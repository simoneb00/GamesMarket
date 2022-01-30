package gamesmarket.graphiccontrol.mobile.profile;

import gamesmarket.bean.GameBean;
import gamesmarket.control.profile.UserProfileController;
import gamesmarket.exceptions.ErrorMessage;
import gamesmarket.graphiccontrol.mobile.NavigationButtons;
import gamesmarket.model.User;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;


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
        String wishlistSelected = wishlist.getSelectionModel().getSelectedItems().toString();       // get selected game from wishlist
        if (!Objects.equals(wishlistSelected, "[]")) {
            wishlistSelected = wishlistSelected.replaceAll("[\\[\\]]", "");         // replace [] chars with blank ([game] -> game)
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
        TradelistController tradelistController = new TradelistController();
        tradelistController.addGame(event);
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        this.retrieveWishlist();
    }
}
