package gamesmarket.graphiccontrol.login;

import gamesmarket.bean.LoginCredentialsBean;
import gamesmarket.boundaries.LoginFacebookBoundary;
import gamesmarket.boundaries.LoginGoogleBoundary;
import gamesmarket.control.LoginController;
import gamesmarket.exceptions.ErrorMessage;
import gamesmarket.exceptions.InvalidEmailException;
import gamesmarket.main.Main;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Objects;

public class LoginGraphicController {

    @FXML
    private TextField emailTextField;
    @FXML
    private Label loginLabel;
    @FXML
    private PasswordField passwordField;


    private Stage loginStage = new Stage();
    private final LoginController lc = new LoginController();

    @FXML
    public void close(ActionEvent event) {
        loginStage = (Stage) ((Button) event.getSource()).getScene().getWindow();
        loginStage.close();
    }

    @FXML
    public void loginButtonHandler(ActionEvent event) {

        try {
            loginStage = (Stage) ((Button) event.getSource()).getScene().getWindow();
            if (emailTextField.getText().isBlank() && passwordField.getText().isBlank())
                loginLabel.setText("Please enter username and password");
            else {
                LoginCredentialsBean loginCredentialsBean = new LoginCredentialsBean();
                loginCredentialsBean.setEmailAddress(emailTextField.getText());
                loginCredentialsBean.setPassword(passwordField.getText());

                if (!lc.validateLogin(loginCredentialsBean))
                    loginLabel.setText("Invalid login, please try again.");
                else
                    this.close(event);

            }
        } catch (InvalidEmailException e) {
            loginLabel.setText("Invalid email address, please try again.");
        } catch (SQLException | IOException e) {
            ErrorMessage.displayErrorMessage();
        }
    }

    @FXML
    public void loginWithFacebook(ActionEvent event) {

        LoginFacebookBoundary loginFacebookBoundary = new LoginFacebookBoundary();

        try {
            loginFacebookBoundary.validateFacebookLogin();
            this.close(event);
        } catch (SQLException | IOException e) {
            ErrorMessage.displayErrorMessage();
        }
    }

    @FXML
    public void loginWithGoogle(ActionEvent event) {

        LoginGoogleBoundary loginGoogleBoundary = new LoginGoogleBoundary();

        try {
            loginGoogleBoundary.validateGoogleLogin();
            this.close(event);
        } catch (SQLException | IOException e) {
            ErrorMessage.displayErrorMessage();
        }
    }

    @FXML
    public void signUpButtonHandler(ActionEvent event) {

        try {

            Parent root = FXMLLoader.load(Objects.requireNonNull(Main.class.getResource("/gamesmarket/register.fxml")));
            Stage registerStage = new Stage();
            Scene registerScene = new Scene(root, 429, 601);
            registerScene.setFill(Color.TRANSPARENT);
            registerStage.initStyle(StageStyle.TRANSPARENT);
            registerStage.setScene(registerScene);

            loginStage = (Stage) ((Button) event.getSource()).getScene().getWindow();

            loginStage.setIconified(true);
            registerStage.showAndWait();
            loginStage.setIconified(false);

        } catch (Exception e) {
            ErrorMessage.displayErrorMessage();
        }
    }
}
