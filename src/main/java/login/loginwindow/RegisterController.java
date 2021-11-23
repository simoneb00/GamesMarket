package login.loginwindow;

import javafx.event.ActionEvent;
import javafx.scene.control.Button;
import javafx.stage.Stage;

public class RegisterController {

    Stage stage = new Stage();

    public void registerButtonController(ActionEvent event) {
        return;
    }

    public void registerCloseButtonController(ActionEvent event) {
        stage = (Stage) ((Button)event.getSource()).getScene().getWindow();
        stage.close();

    }
}
