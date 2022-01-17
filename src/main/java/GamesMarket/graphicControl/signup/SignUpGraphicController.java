package GamesMarket.graphicControl.signup;

import GamesMarket.bean.RegisterCredentialsBean;
import GamesMarket.control.SignUpController;
import GamesMarket.exceptions.DuplicatedEmailException;
import GamesMarket.exceptions.DuplicatedUsernameException;
import GamesMarket.exceptions.ErrorMessage;
import GamesMarket.exceptions.InvalidEmailException;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.sql.SQLException;


public class SignUpGraphicController {


    @FXML
    private CheckBox checkBox;

    @FXML
    private TextField emailTextField;

    @FXML
    private TextField firstNameTextField;

    @FXML
    private TextField lastNameTextField;

    @FXML
    private Button loginButton;

    @FXML
    private PasswordField passwordField;

    @FXML
    private Label registerLabel;

    @FXML
    private TextField usernameTextField;

    private boolean isShopOwner = false;

    @FXML
    public void backButtonPressed(ActionEvent event) {
        Stage stage = (Stage) ((Button)event.getSource()).getScene().getWindow();
        stage.close();
    }

    @FXML
    public void registerButtonPressed(ActionEvent event) {

        if (emailTextField.getText().isEmpty() || passwordField.getText().isEmpty() || firstNameTextField.getText().isEmpty() || lastNameTextField.getText().isEmpty() || usernameTextField.getText().isEmpty()) {
            registerLabel.setText("Missing information!");
            return;
        }

        try {

            RegisterCredentialsBean registerCredentialsBean = new RegisterCredentialsBean();

            registerCredentialsBean.setEmail(emailTextField.getText());
            registerCredentialsBean.setFirstName(firstNameTextField.getText());
            registerCredentialsBean.setLastName(lastNameTextField.getText());
            registerCredentialsBean.setUsername(usernameTextField.getText());
            registerCredentialsBean.setPassword(passwordField.getText());

            if (isShopOwner) {
                registerCredentialsBean.setTypeOfUser("ShopOwner");
            } else {
                registerCredentialsBean.setTypeOfUser("User");
            }

            SignUpController signUpController = new SignUpController();
            signUpController.signUp(registerCredentialsBean);

            registerLabel.setText("Success!");

        } catch (InvalidEmailException e) {
            registerLabel.setText("Invalid email address. Try again.");
        } catch (DuplicatedEmailException duplicatedEmailException) {
            registerLabel.setText("This email address is already used.");
        } catch (DuplicatedUsernameException duplicatedUsernameException) {
            registerLabel.setText("This username is already used.");
        } catch (SQLException e) {
            ErrorMessage.displayErrorMessage();
        }

    }

    @FXML
    public void shopOwnerChecked(ActionEvent event) {

        if (checkBox.isSelected()) {
            usernameTextField.setEditable(false);
            usernameTextField.setVisible(false);
            usernameTextField.setText(null);
            isShopOwner = true;
        } else {
            usernameTextField.setVisible(true);
            usernameTextField.setEditable(true);
            isShopOwner = false;
        }
    }
}
