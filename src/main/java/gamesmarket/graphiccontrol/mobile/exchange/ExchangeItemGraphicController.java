package gamesmarket.graphiccontrol.mobile.exchange;

import gamesmarket.exceptions.ErrorMessage;
import gamesmarket.main.Main;
import gamesmarket.model.ExchangePost;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;


import java.io.File;

public class ExchangeItemGraphicController {

    @FXML
    private ImageView gameImg;
    @FXML
    private Label gameToGive;
    @FXML
    private Label gameToReceive;
    @FXML
    private Label usernameLabel;

    public void setData(ExchangePost exchangePost) {
        usernameLabel.setText(exchangePost.getUsername());
        gameToGive.setText(exchangePost.getGame() + " - " + exchangePost.getPlatform());
        gameToReceive.setText(exchangePost.getGameToGive() + " - " + exchangePost.getPlatformGameToGive());
        File file = exchangePost.getImageFile();        // retrieve game's photo
        if (file != null) {
            Image image = new Image(file.getAbsolutePath());
            gameImg.setImage(image);
        }
    }

    @FXML
    public void contact() {

        try {

            FXMLLoader fxmlLoader = new FXMLLoader();
            fxmlLoader.setLocation(Main.class.getResource("/gamesmarket/mobile/contacts.fxml"));
            gamesmarket.graphiccontrol.mobile.exchange.ContactWindowGraphicController contactWindowGraphicController = new ContactWindowGraphicController(usernameLabel.getText());
            fxmlLoader.setController(contactWindowGraphicController);

            Parent root = fxmlLoader.load();
            Stage stage = new Stage();
            Scene scene = new Scene(root);

            stage.setScene(scene);
            stage.showAndWait();

        } catch (Exception e) {
            ErrorMessage.displayErrorMobile();
        }
    }
}
