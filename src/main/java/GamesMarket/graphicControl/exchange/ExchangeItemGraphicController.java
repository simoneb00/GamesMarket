package GamesMarket.graphicControl.exchange;

import GamesMarket.main.Main;
import GamesMarket.model.ExchangePost;
import GamesMarket.model.Game;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.effect.ColorAdjust;
import javafx.scene.effect.GaussianBlur;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.File;

public class ExchangeItemGraphicController {

    @FXML
    private Label gameToGive;
    @FXML
    private Label gameToReceive;
    @FXML
    private Label username;
    @FXML
    private ImageView imageView;
    @FXML
    private Label address;
    @FXML
    private Label country;
    @FXML
    private Label email;
    @FXML
    private Label tel;
    @FXML
    private Label contactUsername;


    public void setData(ExchangePost exchangePost) {
        username.setText(exchangePost.getUsername());
        gameToReceive.setText(exchangePost.getGame() + " - " + exchangePost.getPlatform());
        gameToGive.setText(exchangePost.getGameToGive() + " - " + exchangePost.getPlatformGameToGive());
        File file = exchangePost.getImageFile();
        if (file != null) {
            Image image = new Image(file.getAbsolutePath());
            imageView.setImage(image);
        }
    }

    public void contactButton() {
        try {
            Parent root = FXMLLoader.load(Main.class.getResource("/GamesMarket/contact.fxml"));
            Stage stage = new Stage();
            Scene scene = new Scene(root);

            scene.setFill(Color.TRANSPARENT);
            stage.initStyle(StageStyle.TRANSPARENT);
            stage.setScene(scene);
            stage.showAndWait();

        } catch (Exception e) {
            e.printStackTrace();
            e.getCause();
        }
    }

    public void close(ActionEvent event) {
        Stage stage = (Stage) ((Button)event.getSource()).getScene().getWindow();
        stage.close();
    }
}
