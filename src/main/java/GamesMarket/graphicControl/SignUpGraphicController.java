package GamesMarket.graphicControl;

import GamesMarket.bean.RegisterCredentialsBean;
import GamesMarket.control.SignUpController;
import javafx.event.ActionEvent;
import javafx.scene.control.*;
import javafx.stage.Stage;
import net.synedra.validatorfx.Check;
import org.w3c.dom.Text;


public class SignUpGraphicController {

    private boolean isShopOwner = false;

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

            if (isShopOwner) {
                registerCredentialsBean.setTypeOfUser("ShopOwner");
            } else {
                registerCredentialsBean.setTypeOfUser("User");
            }

            SignUpController signUpController = new SignUpController();


            signUpController.signUp(registerCredentialsBean);

            registerLabel.setText("Success!");

        } catch (Exception e) {
            registerLabel.setText("Invalid email address. Try again.");
        }

    }

    public void shopOwnerChecked(TextField usernameTextField, CheckBox checkBox) {

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
