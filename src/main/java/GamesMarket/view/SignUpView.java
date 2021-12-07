package GamesMarket.view;

import GamesMarket.graphicControl.SignUpGraphicController;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

public class SignUpView {

    @FXML
    private TextField firstNameTextField;
    @FXML
    private TextField lastNameTextField;
    @FXML
    private TextField emailTextField;
    @FXML
    private TextField usernameTextField;
    @FXML
    private PasswordField passwordField;
    @FXML
    private Label registerLabel;
    @FXML
    private CheckBox checkBox;

    SignUpGraphicController signUpGraphicController = new SignUpGraphicController();

    public void registerButtonHandler() {
        signUpGraphicController.registerButtonPressed(firstNameTextField, lastNameTextField, usernameTextField, passwordField, emailTextField, registerLabel);
    }

    public void backButtonHandler(ActionEvent event) {
        signUpGraphicController.backButtonPressed(event);
    }

    public void shopOwnerCheck() {
        signUpGraphicController.shopOwnerChecked(usernameTextField, checkBox);
    }
}
