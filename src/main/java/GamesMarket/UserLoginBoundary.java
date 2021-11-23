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

public class UserLoginBoundary {

    @FXML
    private AnchorPane homePane;
    @FXML
    private Label loginLabel;
    @FXML
    private TextField usernameTextField;
    @FXML
    private PasswordField passwordField;

    private Stage loginStage = new Stage();

    public void loginCloseButtonController(ActionEvent event) {
        loginStage = (Stage) ((Button)event.getSource()).getScene().getWindow();
        loginStage.close();

    }

    public void loginButtonController(ActionEvent event) {
        loginStage = (Stage) ((Button)event.getSource()).getScene().getWindow();
        if (usernameTextField.getText().isBlank() && passwordField.getText().isBlank())
            loginLabel.setText("Please enter username and password");
        else {
            LoginController lc = new LoginController();
            if (lc.validateLogin(usernameTextField.getText(), passwordField.getText())) {
                loginLabel.setText("Welcome!");
            } else {
                loginLabel.setText("Invalid login, please try again.");
            }
        }
    }

    public void loginWithFacebook() {
        loginLabel.setText("login with Facebook");
    }

    public void loginWithGoogle(){
        loginLabel.setText("login with Google");
    }

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
}
