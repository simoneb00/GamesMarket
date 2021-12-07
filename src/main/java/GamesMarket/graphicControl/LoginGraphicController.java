package GamesMarket.graphicControl;

import GamesMarket.bean.LoginCredentialsBean;
import GamesMarket.control.LoginController;
import javafx.event.ActionEvent;
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

public class LoginGraphicController {

    private Stage loginStage = new Stage();

    public void closeButtonHandler(ActionEvent event) {
        loginStage = (Stage) ((Button)event.getSource()).getScene().getWindow();
        loginStage.close();
    }

    public void loginButtonHandler(ActionEvent event, TextField emailTextField, PasswordField passwordField, Label loginLabel) {

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
                } else {
                    loginLabel.setText("Invalid login, please try again.");
                }
            }
        } catch (Exception e) {
            loginLabel.setText("Invalid email address. Try again.");
        }
    }

    public void loginWithFacebook(Label loginLabel) {
        loginLabel.setText("login with Facebook");
    }

    public void loginWithGoogle(Label loginLabel){
        loginLabel.setText("login with Google");
    }

    public void signUpButtonHandler(ActionEvent event) {

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
