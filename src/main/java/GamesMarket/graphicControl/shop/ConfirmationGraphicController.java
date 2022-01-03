package GamesMarket.graphicControl.shop;

import GamesMarket.bean.OrderBean;
import GamesMarket.control.ShopController;
import GamesMarket.model.User;
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
    private String buyerName;
    private String buyerAddress;
    private String buyerCity;
    private String buyerTel;
    private String buyerEmail;
    private String paymentMethod;

    public ConfirmationGraphicController(String vendor, String platform, String game, double price, String imagePath, String paymentMethod, String buyerEmail) {
        this.vendor = vendor;
        this.platform = platform;
        this.game = game;
        this.price = price;
        this.imagePath = imagePath;
        this.paymentMethod = paymentMethod;
        this.buyerEmail = buyerEmail;
    }

    public String getBuyerName() {
        return buyerName;
    }

    public void setBuyerName(String buyerName) {
        this.buyerName = buyerName;
    }

    public String getBuyerAddress() {
        return buyerAddress;
    }

    public void setBuyerAddress(String buyerAddress) {
        this.buyerAddress = buyerAddress;
    }

    public String getBuyerCity() {
        return buyerCity;
    }

    public void setBuyerCity(String buyerCity) {
        this.buyerCity = buyerCity;
    }

    public String getBuyerTel() {
        return buyerTel;
    }

    public void setBuyerTel(String buyerTel) {
        this.buyerTel = buyerTel;
    }

    private void saveOrder() {
        OrderBean orderBean = new OrderBean(vendor, platform, game, price, buyerName, buyerAddress, buyerCity, buyerTel, paymentMethod, User.getInstance().getUsername(), buyerEmail, "pending");
        ShopController shopController = new ShopController();
        shopController.saveOrder(orderBean);
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        vendorLabel.setText(vendor);
        platformLabel.setText(platform);
        priceLabel.setText("€ " + price);
        gameLabel.setText(game);

        Image image = new Image(imagePath);
        img.setImage(image);

        this.saveOrder();
    }
}
