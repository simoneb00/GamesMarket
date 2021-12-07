package GamesMarket.graphicControl;

import GamesMarket.control.ProfileController;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.effect.ColorAdjust;
import javafx.scene.effect.GaussianBlur;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class ProfileGraphicController {

    ProfileController pc = new ProfileController();

    public void profileCloseButtonClicked(ActionEvent event){
        Stage stage = (Stage) ((Button)event.getSource()).getScene().getWindow();
        stage.close();
    }

    public void saveBio(Button saveBioButton) {
        saveBioButton.setOpacity(0);
        pc.saveBio();
    }

    public void saveContactInformation(Button saveCIButton) {
        saveCIButton.setOpacity(0);
        pc.saveContactInformation();
    }

    public void addGameToTradelist(ActionEvent event, AnchorPane profilePane) {
        try {
            Parent root = FXMLLoader.load(getClass().getResource("tradelist.fxml"));
            Stage tradelistStage = new Stage();
            Scene tradelistScene = new Scene(root, 600, 400);
            tradelistScene.setFill(Color.TRANSPARENT);
            tradelistStage.initStyle(StageStyle.TRANSPARENT);
            tradelistStage.setScene(tradelistScene);

            GaussianBlur blur = new GaussianBlur(55);
            ColorAdjust adj = new ColorAdjust(-0.1, -0.1, -0.1, -0.1);
            adj.setInput(blur);
            profilePane.setEffect(adj);
            tradelistStage.showAndWait();
            profilePane.setEffect(null);

        } catch (Exception e) {
            e.printStackTrace();
            e.getCause();
        }
    }

    public void addGameToWishlist(ActionEvent event, AnchorPane profilePane) {
        try {
            Parent root = FXMLLoader.load(getClass().getResource("wishlist.fxml"));
            Stage wishlistStage = new Stage();
            Scene wishlistScene = new Scene(root, 600, 400);
            wishlistScene.setFill(Color.TRANSPARENT);
            wishlistStage.initStyle(StageStyle.TRANSPARENT);
            wishlistStage.setScene(wishlistScene);

            GaussianBlur blur = new GaussianBlur(55);
            ColorAdjust adj = new ColorAdjust(-0.1, -0.1, -0.1, -0.1);
            adj.setInput(blur);
            profilePane.setEffect(adj);
            wishlistStage.showAndWait();
            profilePane.setEffect(null);

        } catch (Exception e) {
            e.printStackTrace();
            e.getCause();
        }
    }

    public void updateProfilePhoto() {
        pc.updateProfilePhoto();
    }

    public void showSaveBioButton(Button saveBioButton)  {
        saveBioButton.setOpacity(1);
    }

    public void showSaveCIButton(Button saveCIButton)  {
        saveCIButton.setOpacity(1);
    }

    public void showUpdatePhotoButton(Button updatePhotoButton) {
        updatePhotoButton.setOpacity(1);
    }

    public void hideUpdatePhotoButton(Button updatePhotoButton) throws Exception {
        Thread.sleep(1000);
        updatePhotoButton.setOpacity(0);
    }
}
