package GamesMarket;

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

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;


public class LoginController {

    private Stage stage;
    private Stage loginStage = new Stage();


    @FXML
    private Button loginButton;
    @FXML
    private Button facebookLogin;
    @FXML
    private Button googleLogin;
    @FXML
    private Button signUpButton;
    @FXML
    private Label loginLabel;
    @FXML
    private TextField usernameTextField;
    @FXML
    private PasswordField passwordField;
    @FXML
    private AnchorPane loginPane;

    private boolean isLoggedIn = true;

    public void loginCloseButtonController(ActionEvent event) {
        loginStage = (Stage) ((Button)event.getSource()).getScene().getWindow();
        loginStage.close();

    }

    public boolean isLogged() {
        if (isLoggedIn)
            return true;

        else
            return false;
    }

    public void loginButtonController(ActionEvent event) {
        loginStage = (Stage) ((Button)event.getSource()).getScene().getWindow();
        if (usernameTextField.getText().isBlank() && passwordField.getText().isBlank())
            loginLabel.setText("Please enter username and password");
        else {
            validateLogin();
        }
    }

    public void validateLogin() {
        DatabaseConnection connectNow = new DatabaseConnection();
        Connection connectDB = connectNow.getConnection();

        String verifyLogin = "SELECT count(1) FROM user_account WHERE username = '" + usernameTextField.getText() + "' AND password = '" + passwordField.getText() + "'";

        try {

            Statement statement = connectDB.createStatement();
            ResultSet queryResult = statement.executeQuery(verifyLogin);

            while(queryResult.next()){

                if (queryResult.getInt(1) == 1) {
                    loginLabel.setText("Welcome!");
                    isLoggedIn = true;
                    loginStage.close();
                } else {
                    loginLabel.setText("Invalid login. Please try again.");
                    isLoggedIn = false;
                }

            }

        } catch (Exception e) {
            e.printStackTrace();
            e.getCause();
        }
    }

/*
    public void signUpButtonController(ActionEvent event) {

        try {

            Parent root = FXMLLoader.load(getClass().getResource("register.fxml"));
            Stage registerStage = new Stage();
            Scene registerScene = new Scene(root, 429, 601);
            registerScene.setFill(Color.TRANSPARENT);
            registerStage.initStyle(StageStyle.TRANSPARENT);
            registerStage.setScene(registerScene);

            loginStage = (Stage) ((Button)event.getSource()).getScene().getWindow();

            loginStage.setIconified(true);
            registerStage.showAndWait();
            loginStage.setIconified(false);

        } catch (Exception e) {
            e.printStackTrace();
            e.getCause();
        }
    }
*/
}