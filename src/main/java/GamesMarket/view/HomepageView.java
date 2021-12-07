package GamesMarket.view;

import GamesMarket.graphicControl.HomepageGraphicController;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.layout.AnchorPane;


public class HomepageView {

    @FXML
    private AnchorPane homePane;

    HomepageGraphicController homepageGraphicController = new HomepageGraphicController();

    public void signInButtonHandler() {
        homepageGraphicController.signInButtonPressed(homePane);
    }

    public void closeButtonHandler() {
        homepageGraphicController.homeCloseButtonController(homePane);
    }

    public void profileButtonHandler(ActionEvent event) {
        homepageGraphicController.profileButtonController(event);
    }

    public void yesExitController() {
        homepageGraphicController.yesExitController();
    }

    public void noExitController(ActionEvent event) {
        homepageGraphicController.noExitController(event);
    }
}
