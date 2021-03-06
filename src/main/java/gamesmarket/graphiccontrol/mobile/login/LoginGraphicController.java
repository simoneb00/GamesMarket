package gamesmarket.graphiccontrol.mobile.login;

import gamesmarket.bean.LoginCredentialsBean;
import gamesmarket.boundaries.LoginFacebookBoundary;
import gamesmarket.boundaries.LoginGoogleBoundary;
import gamesmarket.control.LoginController;
import gamesmarket.exceptions.ErrorMessage;
import gamesmarket.exceptions.InvalidEmailException;
import gamesmarket.graphiccontrol.mobile.ShopOwnerNavigationButtons;
import gamesmarket.main.Main;
import gamesmarket.model.ShopOwner;
import gamesmarket.model.User;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Objects;

public class LoginGraphicController extends ShopOwnerNavigationButtons {

    @FXML
    private TextField emailTF;
    @FXML
    private PasswordField passwordField;
    @FXML
    private Label label;

    private final LoginController loginController = new LoginController();

    @FXML
    private void backToHome(ActionEvent event) {
        this.homeButton(event);
    }

    @FXML
    public void loginButton(ActionEvent event) {
        try {
            if (emailTF.getText().isBlank() && passwordField.getText().isBlank())
                label.setText("Please enter username and password");
            else {
                LoginCredentialsBean loginCredentialsBean = new LoginCredentialsBean();
                loginCredentialsBean.setEmailAddress(emailTF.getText());
                loginCredentialsBean.setPassword(passwordField.getText());

                loginController.validateLogin(loginCredentialsBean);

                if (User.getInstance().isLoggedIn())
                    this.homeButton(event);
                else if (ShopOwner.getInstance().isLoggedIn())
                    this.shopOwnerHomeButton(event);
                else
                    label.setText("Invalid login. Please try again.");

            }
        } catch (InvalidEmailException e) {
            label.setText("Invalid email address, please try again.");
        } catch (SQLException | IOException e) {
            ErrorMessage.displayErrorMobile();
        }
    }

    @FXML
    public void signInWithGoogle(ActionEvent event) {

        LoginGoogleBoundary loginGoogleBoundary = new LoginGoogleBoundary();

        try {
            loginGoogleBoundary.validateGoogleLogin();
            this.homeButton(event);
        } catch (SQLException | IOException e) {
            ErrorMessage.displayErrorMobile();
        }
    }

    @FXML
    public void signInWithFacebook(ActionEvent event) {

        LoginFacebookBoundary loginFacebookBoundary = new LoginFacebookBoundary();

        try {
            loginFacebookBoundary.validateFacebookLogin();
            this.homeButton(event);
        } catch (SQLException | IOException e) {
            ErrorMessage.displayErrorMobile();
        }
    }


    @FXML
    public void signUp(ActionEvent event) {
        try {
            root = FXMLLoader.load(Objects.requireNonNull(Main.class.getResource("/gamesmarket/mobile/register.fxml")));
            this.show(root, event);

        } catch (Exception e) {
            ErrorMessage.displayErrorMobile();
        }
    }
}
