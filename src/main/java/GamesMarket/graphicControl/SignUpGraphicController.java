package GamesMarket.graphicControl;

import GamesMarket.bean.RegisterCredentialsBean;
import GamesMarket.control.SignUpController;
import javafx.event.ActionEvent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;



public class SignUpGraphicController {

    public void backButtonPressed(ActionEvent event) {
        Stage stage = (Stage) ((Button)event.getSource()).getScene().getWindow();
        stage.close();
    }

    public void registerButtonPressed(TextField firstName, TextField lastName, TextField username, PasswordField password, TextField email, Label registerLabel) {

        try {

            RegisterCredentialsBean registerCredentialsBean = new RegisterCredentialsBean();

            registerCredentialsBean.setEmail(email.getText());
            registerCredentialsBean.setFirstName(firstName.getText());
            registerCredentialsBean.setLastName(lastName.getText());
            registerCredentialsBean.setUsername(username.getText());
            registerCredentialsBean.setPassword(password.getText());

            SignUpController signUpController = new SignUpController();


            signUpController.signUp(registerCredentialsBean);

            registerLabel.setText("Success!");

        } catch (Exception e) {
            registerLabel.setText("Invalid email address. Try again.");
        }

    }
}
