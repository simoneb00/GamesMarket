package GamesMarket.graphicControl.shop;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.net.URL;
import java.util.ResourceBundle;

public class ConfirmationGraphicController implements Initializable {

    @FXML
    private Label vendorLabel;
    @FXML
    private Label platformLabel;
    @FXML
    private Label priceLabel;
    @FXML
    private Label gameLabel;
    @FXML
    private ImageView img;

    private String vendor;
    private String platform;
    private String game;
    private double price;
    private String imagePath;

    public ConfirmationGraphicController(String vendor, String platform, String game, double price, String imagePath) {
        this.vendor = vendor;
        this.platform = platform;
        this.game = game;
        this.price = price;
        this.imagePath = imagePath;
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        vendorLabel.setText(vendor);
        platformLabel.setText(platform);
        priceLabel.setText("€ " + price);
        gameLabel.setText(game);

        Image image = new Image(imagePath);
        img.setImage(image);
    }
}
