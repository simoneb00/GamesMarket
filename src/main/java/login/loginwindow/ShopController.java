package login.loginwindow;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class ShopController extends ButtonsHandler {

//    ButtonsHandler bh = new ButtonsHandler();

    public void shopCloseButtonController(ActionEvent event) {
        Stage stage = (Stage) ((Button) event.getSource()).getScene().getWindow();
        stage.close();
    }
/*
    public void homeButtonController(ActionEvent event) {
        bh.homeButtonController(event);
    }

    public void forumButtonController(ActionEvent event) {
        bh.forumButtonController(event);
    }

    public void profileButtonController(ActionEvent event) {
        bh.profileButtonController(event);
    }

    public void exchangeButtonController(ActionEvent event) {
        bh.exchangeButtonController(event);
    }

*/
}
