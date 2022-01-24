package gamesmarket.graphiccontrol.mobile.signup;

import gamesmarket.bean.RegisterCredentialsBean;
import gamesmarket.control.SignUpController;
import gamesmarket.exceptions.DuplicatedEmailException;
import gamesmarket.exceptions.DuplicatedUsernameException;
import gamesmarket.exceptions.ErrorMessage;
import gamesmarket.exceptions.InvalidEmailException;
import gamesmarket.main.Main;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.sql.SQLException;


public class SignUpGraphicController {


    @FXML
    private CheckBox checkBox;

    @FXML
    private TextField emailTF;

    @FXML
    private TextField firstNameTF;

    @FXML
    private TextField lastNameTF;

    @FXML
    private PasswordField passwordField;

    @FXML
    private Label registerLabel;

    @FXML
    private TextField usernameTF;

    private boolean isShopOwner = false;

    @FXML
    public void back(ActionEvent event) {
        try {
            Parent root = FXMLLoader.load(Main.class.getResource("/gamesmarket/mobile/login.fxml"));
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.show();

        } catch (Exception e) {
            ErrorMessage.displayErrorMobile();
        }
    }

    @FXML
    public void registerButtonPressed() {

        try {

            if (isShopOwner) {
                if (emailTF.getText().isEmpty() || passwordField.getText().isEmpty() || firstNameTF.getText().isEmpty() || lastNameTF.getText().isEmpty()) {
                    registerLabel.setText("Missing information!");
                    return;
                }
            } else {
                if (emailTF.getText().isEmpty() || passwordField.getText().isEmpty() || firstNameTF.getText().isEmpty() || lastNameTF.getText().isEmpty() || usernameTF.getText().isEmpty()) {
                    registerLabel.setText("Missing information!");
                    return;
                }
            }

            RegisterCredentialsBean registerCredentialsBean = new RegisterCredentialsBean();

            if (isShopOwner) {
                registerCredentialsBean.setTypeOfUser("ShopOwner");
            } else {
                registerCredentialsBean.setTypeOfUser("User");
            }

            registerCredentialsBean.setRegisterEmail(emailTF.getText());
            registerCredentialsBean.setFirstName(firstNameTF.getText());
            registerCredentialsBean.setLastName(lastNameTF.getText());
            registerCredentialsBean.setRegisterUsername(usernameTF.getText());
            registerCredentialsBean.setRegisterPassword(passwordField.getText());


            SignUpController signUpController = new SignUpController();
            signUpController.signUp(registerCredentialsBean);

            registerLabel.setText("Success!");

        } catch (DuplicatedEmailException duplicatedEmailException) {
            registerLabel.setText("This email address is already used.");
        } catch (InvalidEmailException invalidEmailException) {
            registerLabel.setText("Invalid email address. Please try again.");
        } catch (DuplicatedUsernameException duplicatedUsernameException) {
            registerLabel.setText("This username is already used.");
        } catch (SQLException e) {
            ErrorMessage.displayErrorMobile();
        }

    }

    @FXML
    public void shopOwnerChecked() {

        if (checkBox.isSelected()) {
            usernameTF.setVisible(false);
            usernameTF.setEditable(false);
            usernameTF.setText(null);
            isShopOwner = true;
        } else {
            usernameTF.setVisible(true);
            usernameTF.setEditable(true);
            isShopOwner = false;
        }
    }
}
