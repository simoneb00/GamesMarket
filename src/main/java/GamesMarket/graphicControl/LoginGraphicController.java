package GamesMarket.graphicControl;

import GamesMarket.main.Main;
import GamesMarket.bean.LoginCredentialsBean;
import GamesMarket.control.LoginController;
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

    public void close(ActionEvent event) {
        loginStage = (Stage) ((Button)event.getSource()).getScene().getWindow();
        loginStage.close();
    }

    public void loginButtonHandler(ActionEvent event) {

        try {
            loginStage = (Stage) ((Button) event.getSource()).getScene().getWindow();
            if (emailTextField.getText().isBlank() && passwordField.getText().isBlank())
                loginLabel.setText("Please enter username and password");
            else {
                LoginController lc = new LoginController();

                LoginCredentialsBean loginCredentialsBean = new LoginCredentialsBean();
                loginCredentialsBean.setEmailAddress(emailTextField.getText());
                loginCredentialsBean.setPassword(passwordField.getText());

                if (lc.validateLogin(loginCredentialsBean)) {
                    loginLabel.setText("Welcome!");
                    this.close(event);
                } else {
                    loginLabel.setText("Invalid login, please try again.");
                }
            }
        } catch (Exception e) {
            loginLabel.setText("Invalid email address. Try again.");
        }
    }

    public void loginWithFacebook() {
        loginLabel.setText("login with Facebook");
    }

    public void loginWithGoogle(){
        loginLabel.setText("login with Google");
    }

    public void signUpButtonHandler(ActionEvent event) {

        try {

            Parent root = FXMLLoader.load(Main.class.getResource("/GamesMarket/register.fxml"));
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
