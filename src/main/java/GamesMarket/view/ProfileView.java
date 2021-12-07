package GamesMarket.view;

import GamesMarket.graphicControl.ProfileGraphicController;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;


public class ProfileView {

    @FXML
    Button updatePhotoButton;
    @FXML
    Button saveBioButton;
    @FXML
    Button saveCIButton;
    @FXML
    AnchorPane profilePane;

    ProfileGraphicController profileGraphicController = new ProfileGraphicController();

    public void showUpdatePhotoButton() {
        profileGraphicController.showUpdatePhotoButton(updatePhotoButton);
    }

    public void profileCloseButtonHandler(ActionEvent event) {
        profileGraphicController.profileCloseButtonClicked(event);
    }

    public void saveBio() {
        profileGraphicController.saveBio(saveBioButton);
    }

    public void saveContactInformation() {
        profileGraphicController.saveContactInformation(saveCIButton);
    }

    public void tradelistAddButtonHandler(ActionEvent event) {
        profileGraphicController.addGameToTradelist(event, profilePane);
    }

    public void wishlistAddButtonHandler(ActionEvent event) {
        profileGraphicController.addGameToWishlist(event, profilePane);
    }

    public void updateProfilePhotoButtonHandler() {
        profileGraphicController.updateProfilePhoto();
    }

    public void showSaveBioButton() {
        profileGraphicController.showSaveBioButton(saveBioButton);
    }

    public void showSaveCIButton() {
        profileGraphicController.showSaveCIButton(saveCIButton);
    }

    public void hideUpdatePhotoButton() {
        try {
            profileGraphicController.hideUpdatePhotoButton(updatePhotoButton);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void homeButtonController() {}
    public void shopButtonController() {}
    public void exchangeButtonController() {}
    public void forumButtonController() {}


}

