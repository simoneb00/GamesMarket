package GamesMarket.graphicControl;

import GamesMarket.control.UserProfileController;
import GamesMarket.main.Main;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.effect.ColorAdjust;
import javafx.scene.effect.GaussianBlur;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.File;
import java.io.IOException;

public class ProfileGraphicController {

    @FXML
    private Label addressLabel;

    @FXML
    private Label countryLabel;

    @FXML
    private Label emailLabel;

    @FXML
    private Label telLabel;

    @FXML
    private Label usernameLabel;

    private UserProfileController pc = new UserProfileController();
    private Parent root;
    private Scene scene;
    private Stage stage;

/*
    public void saveBio(Button saveBioButton) {
        saveBioButton.setOpacity(0);
        pc.saveBio();
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
*/
    public void homeButton(ActionEvent event) {

        try {
            root = FXMLLoader.load(Main.class.getResource("/GamesMarket/home.fxml"));
            stage = (Stage)((Node)event.getSource()).getScene().getWindow();
            scene = new Scene(root);

            scene.getStylesheets().clear();
            scene.getStylesheets().add("file:///C:/Users/Simone%20Bauco/IdeaProjects/GamesMarket/src/main/java/GamesMarket/css/style.css");

            stage.setScene(scene);
            stage.show();

        } catch (Exception e) {
            e.printStackTrace();
            e.getCause();
        }
    }

    public void shopButton(ActionEvent event) {
        try {
            root = FXMLLoader.load(Main.class.getResource("/GamesMarket/shop.fxml"));
            stage = (Stage)((Node)event.getSource()).getScene().getWindow();
            scene = new Scene(root);

            scene.getStylesheets().clear();
            scene.getStylesheets().add("file:///C:/Users/Simone%20Bauco/IdeaProjects/GamesMarket/src/main/java/GamesMarket/css/style.css");

            stage.setScene(scene);
            stage.show();

        } catch (Exception e) {
            e.printStackTrace();
            e.getCause();
        }
    }
}
