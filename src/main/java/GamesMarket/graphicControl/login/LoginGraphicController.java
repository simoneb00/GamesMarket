package GamesMarket.graphicControl.login;

import GamesMarket.exceptions.ErrorMessage;
import GamesMarket.exceptions.InvalidEmailException;
import GamesMarket.exceptions.NotLoggedInException;
import GamesMarket.main.Main;
import GamesMarket.bean.LoginCredentialsBean;
import GamesMarket.control.LoginController;
import GamesMarket.model.ShopOwner;
import GamesMarket.model.User;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;
import java.sql.SQLException;

public class LoginGraphicController {

    @FXML
    private TextField emailTextField;
    @FXML
    private Button facebookLogin;
    @FXML
    private Button googleLogin;
    @FXML
    private Button loginButton;
    @FXML
    private Label loginLabel;
    @FXML
    private AnchorPane loginPane;
    @FXML
    private PasswordField passwordField;
    @FXML
    private Button signUpButton;

    private Stage loginStage = new Stage();
    private LoginController lc = new LoginController();

    public void close(ActionEvent event) {
        loginStage = (Stage) ((Button) event.getSource()).getScene().getWindow();
        loginStage.close();
    }

    public void loginButtonHandler(ActionEvent event) {

        try {
            loginStage = (Stage) ((Button) event.getSource()).getScene().getWindow();
            if (emailTextField.getText().isBlank() && passwordField.getText().isBlank())
                loginLabel.setText("Please enter username and password");
            else {
                LoginCredentialsBean loginCredentialsBean = new LoginCredentialsBean();
                loginCredentialsBean.setEmailAddress(emailTextField.getText());
                loginCredentialsBean.setPassword(passwordField.getText());

                lc.validateLogin(loginCredentialsBean);

                this.close(event);

            }
        } catch (InvalidEmailException e) {
            loginLabel.setText("Invalid email address, please try again.");
        } catch (NotLoggedInException e) {
            loginLabel.setText("Invalid login, please try again.");
        } catch (SQLException e) {
            ErrorMessage.displayErrorMessage();
        } catch (IOException e) {
            ErrorMessage.displayErrorMessage();
        }
    }

    public void loginWithFacebook(ActionEvent event) {
        try {
            lc.validateFacebookLogin();
            this.close(event);
        } catch (SQLException e) {
            ErrorMessage.displayErrorMessage();
        } catch (IOException e) {
            ErrorMessage.displayErrorMessage();
        }
    }

    public void loginWithGoogle(ActionEvent event) {
        try {
            lc.validateGoogleLogin();
            this.close(event);
        } catch (SQLException e) {
            ErrorMessage.displayErrorMessage();
        } catch (IOException e) {
            ErrorMessage.displayErrorMessage();
        }
    }

    public void signUpButtonHandler(ActionEvent event) {

        try {

            Parent root = FXMLLoader.load(Main.class.getResource("/GamesMarket/register.fxml"));
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
            e.printStackTrace();
            e.getCause();
        }
    }
}
