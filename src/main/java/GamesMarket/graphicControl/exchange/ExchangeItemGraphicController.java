package GamesMarket.graphicControl.exchange;

import GamesMarket.main.Main;
import GamesMarket.model.ExchangePost;
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
import javafx.scene.layout.AnchorPane;
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

    @FXML
    public void contactButton(ActionEvent event) {
        try {

            AnchorPane ap = this.getParentAnchorPane(event);

            FXMLLoader fxmlLoader = new FXMLLoader();
            fxmlLoader.setLocation(Main.class.getResource("/GamesMarket/contact.fxml"));
            ContactWindowGraphicController contactWindowGraphicController = new ContactWindowGraphicController(username.getText());
            fxmlLoader.setController(contactWindowGraphicController);

            Parent root = fxmlLoader.load();
            Stage stage = new Stage();
            Scene scene = new Scene(root);

            scene.setFill(Color.TRANSPARENT);
            stage.initStyle(StageStyle.TRANSPARENT);
            stage.setScene(scene);

            GaussianBlur blur = new GaussianBlur(55);
            ColorAdjust adj = new ColorAdjust(-0.1, -0.1, -0.1, -0.1);
            adj.setInput(blur);
            ap.setEffect(adj);

            stage.showAndWait();

            ap.setEffect(null);


        } catch (Exception e) {
            e.printStackTrace();
            e.getCause();
        }
    }

    private AnchorPane getParentAnchorPane(ActionEvent event) {
        Parent parent = ((Button)event.getSource()).getParent();
        Parent parent1 = parent.getParent();
        Parent parent2 = parent1.getParent();
        Parent parent3 = parent2.getParent();
        Parent parent4 = parent3.getParent();
        Parent parent5 = parent4.getParent();
        Parent parent6 = parent5.getParent();
        Parent parent7 = parent6.getParent();
        Parent parent8 = parent7.getParent();
        Parent parent9 = parent8.getParent();
        Parent parent10 = parent9.getParent();
        Parent parent11 = parent10.getParent();
        Parent parent12 = parent11.getParent();
        Parent parent13 =  parent12.getParent();

        return (AnchorPane) parent13.getParent();
    }
}
