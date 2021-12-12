package GamesMarket.view;

import GamesMarket.graphicControl.HomepageGraphicController;
import GamesMarket.model.User;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.layout.AnchorPane;


public class HomepageView {

    @FXML
    private AnchorPane homePane;

    User user = User.getInstance();

    HomepageGraphicController homepageGraphicController = new HomepageGraphicController();

    public void signInButtonHandler() {
        homepageGraphicController.signInButtonPressed(homePane);
    }

    public void closeButtonHandler() {
        homepageGraphicController.close(homePane);
    }

    public void profileButtonHandler(ActionEvent event) {

        if (user.isLoggedIn())
            homepageGraphicController.profileButtonController(event);
        else
            this.signInButtonHandler();
    }

    public void yesExitController() {
        homepageGraphicController.yesExitController();
    }

    public void noExitController(ActionEvent event) {
        homepageGraphicController.noExitController(event);
    }


}
