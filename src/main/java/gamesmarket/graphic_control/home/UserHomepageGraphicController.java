package gamesmarket.graphic_control.home;

import gamesmarket.graphic_control.navigation.NavigationButtons;
import gamesmarket.main.Main;
import gamesmarket.model.ShopOwner;
import gamesmarket.model.User;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.effect.ColorAdjust;
import javafx.scene.effect.GaussianBlur;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.net.URL;
import java.util.ResourceBundle;

public class UserHomepageGraphicController extends NavigationButtons implements Initializable {

    @FXML
    private AnchorPane homePane;
    @FXML
    private Button signInButton;

    @FXML
    public void signInButtonPressed(ActionEvent event) {
        Parent root;
        this.signIn(homePane);

        if (User.getInstance().isLoggedIn()) {
            signInButton.setVisible(false);
            signInButton.isDisabled();
        } else if (ShopOwner.getInstance().isLoggedIn()) {
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