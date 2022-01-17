package GamesMarket.graphicControl.mobile.signup;

import GamesMarket.bean.RegisterCredentialsBean;
import GamesMarket.control.SignUpController;
import GamesMarket.exceptions.DuplicatedEmailException;
import GamesMarket.exceptions.DuplicatedUsernameException;
import GamesMarket.exceptions.ErrorMessage;
import GamesMarket.exceptions.InvalidEmailException;
import GamesMarket.main.Main;
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
            Parent root = FXMLLoader.load(Main.class.getResource("/GamesMarket/mobile/login.fxml"));
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            Scene scene = new Scene(root);

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

            registerCredentialsBean.setEmail(emailTF.getText());
            registerCredentialsBean.setFirstName(firstNameTF.getText());
            registerCredentialsBean.setLastName(lastNameTF.getText());
            registerCredentialsBean.setUsername(usernameTF.getText());
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
            ErrorMessage.displayErrorMobile();
        }

    }

    @FXML
    public void shopOwnerChecked() {

        if (checkBox.isSelected()) {
            usernameTF.setEditable(false);
            usernameTF.setVisible(false);
            usernameTF.setText(null);
            isShopOwner = true;
        } else {
            usernameTF.setVisible(true);
            usernameTF.setEditable(true);
            isShopOwner = false;
        }
    }
}
