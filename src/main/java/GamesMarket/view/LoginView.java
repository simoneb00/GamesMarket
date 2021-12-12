package GamesMarket.view;

import GamesMarket.graphicControl.LoginGraphicController;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;


public class LoginView {

    @FXML
    private Label loginLabel;
    @FXML
    private TextField emailTextField;
    @FXML
    private PasswordField passwordField;

    LoginGraphicController loginGraphicController = new LoginGraphicController();

    public void closeButtonHandler(ActionEvent event) {
        loginGraphicController.close(event);
    }

    public void loginButtonHandler(ActionEvent event) {
        loginGraphicController.loginButtonHandler(event, emailTextField, passwordField, loginLabel);
    }

    public void loginWithFacebookButtonHandler() {
        loginGraphicController.loginWithFacebook(loginLabel);
    }

    public void loginWithGoogleButtonHandler() {
        loginGraphicController.loginWithGoogle(loginLabel);
    }

    public void signUpButtonHandler(ActionEvent event) {
        loginGraphicController.signUpButtonHandler(event);
    }

}
