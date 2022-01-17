package GamesMarket.graphicControl.mobile.login;

import GamesMarket.bean.LoginCredentialsBean;
import GamesMarket.control.LoginController;
import GamesMarket.exceptions.ErrorMessage;
import GamesMarket.exceptions.InvalidEmailException;
import GamesMarket.exceptions.NotLoggedInException;
import GamesMarket.graphicControl.mobile.NavigationButtons;
import GamesMarket.main.Main;
import GamesMarket.model.ShopOwner;
import GamesMarket.model.User;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.SQLException;

public class LoginGraphicController extends NavigationButtons {

    @FXML
    private TextField emailTF;
    @FXML
    private PasswordField passwordField;
    @FXML
    private Label label;

    private LoginController loginController = new LoginController();
    private Parent root;
    private Scene scene;
    private Stage stage;

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
                    this.shopOwnerHomepage(event);

            }
        } catch (InvalidEmailException e) {
            label.setText("Invalid email address, please try again.");
        } catch (NotLoggedInException e) {
            label.setText("Invalid login, please try again.");
        } catch (SQLException | IOException e) {
            ErrorMessage.displayErrorMessage();
        }
    }

    @FXML
    public void signInWithGoogle(ActionEvent event) {
        try {
            loginController.validateGoogleLogin();
            this.homeButton(event);
        } catch (SQLException | IOException e) {
            ErrorMessage.displayErrorMessage();
        }
    }

    @FXML
    public void signInWithFacebook(ActionEvent event) {
        try {
            loginController.validateFacebookLogin();
            this.homeButton(event);
        } catch (SQLException | IOException e) {
            ErrorMessage.displayErrorMessage();
        }
    }

    private void shopOwnerHomepage(ActionEvent event) {

        try {
            root = FXMLLoader.load(Main.class.getResource("/GamesMarket/mobile/shop_owner_homepage.fxml"));
            stage = (Stage)((Node)event.getSource()).getScene().getWindow();
            scene = new Scene(root);

            scene.getStylesheets().clear();
            scene.getStylesheets().add("file:///C:/Users/Simone%20Bauco/IdeaProjects/GamesMarket/src/main/java/GamesMarket/css/style.css");

            stage.setScene(scene);
            stage.show();

        } catch (Exception e) {
            e.printStackTrace();
            e.getCause();
        }
    }

    @FXML
    public void signUp(ActionEvent event) {
        try {
            root = FXMLLoader.load(Main.class.getResource("/GamesMarket/mobile/register.fxml"));
            stage = (Stage)((Node)event.getSource()).getScene().getWindow();
            scene = new Scene(root);

            scene.getStylesheets().clear();
            scene.getStylesheets().add("file:///C:/Users/Simone%20Bauco/IdeaProjects/GamesMarket/src/main/java/GamesMarket/css/style.css");

            stage.setScene(scene);
            stage.show();

        } catch (Exception e) {
            e.printStackTrace();
            e.getCause();
        }
    }
}
