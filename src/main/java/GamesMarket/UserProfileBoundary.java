package GamesMarket;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.AccessibleRole;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.effect.ColorAdjust;
import javafx.scene.effect.GaussianBlur;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;

public class UserProfileBoundary extends ButtonsHandler{

    ProfileController pc = new ProfileController();

    @FXML
    Button updatePhotoButton;
    @FXML
    Button saveBioButton;
    @FXML
    Button saveCIButton;
    @FXML
    AnchorPane profilePane;

    public void profileCloseButtonClicked(ActionEvent event){
        Stage stage = (Stage) ((Button)event.getSource()).getScene().getWindow();
        stage.close();
    }

    public void saveBio() {
        saveBioButton.setOpacity(0);
        pc.saveBio();
    }

    public void saveContactInformation() {
        saveCIButton.setOpacity(0);
        pc.saveContactInformation();
    }

    public void addGameToTradelist(ActionEvent event) {
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

    public void addGameToWishlist() {
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

    public void showSaveBioButton()  {
        saveBioButton.setOpacity(1);

    }

    public void showSaveCIButton()  {
        saveCIButton.setOpacity(1);
    }

    public void showUpdatePhotoButton() {
        updatePhotoButton.setOpacity(1);
    }

    public void hideUpdatePhotoButton() throws Exception {
        Thread.sleep(1000);
        updatePhotoButton.setOpacity(0);
    }




}
