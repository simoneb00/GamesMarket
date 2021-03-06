package gamesmarket.graphiccontrol.home;

import gamesmarket.graphiccontrol.navigation.NavigationButtons;
import gamesmarket.model.ShopOwner;
import gamesmarket.model.User;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;

import java.net.URL;
import java.util.ResourceBundle;

public class UserHomepageGraphicController extends NavigationButtons implements Initializable {

    @FXML
    private AnchorPane homePane;
    @FXML
    private Button signInButton;

    @FXML
    public void signInButtonPressed(ActionEvent event) {
        this.signIn(homePane);

        if (User.getInstance().isLoggedIn()) {      // hides and disables the sign in button if user is logged in
            signInButton.setVisible(false);
            signInButton.isDisabled();
        } else if (ShopOwner.getInstance().isLoggedIn()) {      // if a shop owner logged in, he's redirected to shop owners' homepage
            this.homeButtonSO(event);
        }
    }

    @FXML
    public void profileButtonPressed(ActionEvent event) {
        if (User.getInstance().isLoggedIn()) {
            this.profileButton(event);
        } else {
            this.signInButtonPressed(event);
        }
    }

    @FXML
    public void exchangeButtonPressed(ActionEvent event) {
        if (User.getInstance().isLoggedIn()) {
            this.exchangeButton(event);
        } else {
            this.signInButtonPressed(event);
        }
    }


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        if (User.getInstance().isLoggedIn() || ShopOwner.getInstance().isLoggedIn()) {
            signInButton.setVisible(false);
            signInButton.isDisabled();
        }
    }
}
