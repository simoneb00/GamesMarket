package gamesmarket.graphic_control.mobile.shop;

import gamesmarket.bean.OrderBean;
import gamesmarket.control.ShopController;
import gamesmarket.exceptions.ErrorMessage;
import gamesmarket.graphic_control.mobile.NavigationButtons;
import gamesmarket.model.User;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class ConfirmationGraphicController extends NavigationButtons implements Initializable {

    @FXML
    private Label platformLabel;
    @FXML
    private Label priceLabel;
    @FXML
    private Label gameLabel;
    @FXML
    private ImageView img;

    private String vendorName;
    private String gamePlt;
    private String gameName;
    private double gamePrc;
    private String photoPath;
    private String custName;
    private String custAddress;
    private String custCity;
    private String custTel;
    private String custEmail;
    private String paymentMethodChosen;

    public ConfirmationGraphicController(String vendor, String platform, String game, double price, String imagePath, String paymentMethod, String custEmail) {
        this.vendorName = vendor;
        this.gamePlt = platform;
        this.gameName = game;
        this.gamePrc = price;
        this.photoPath = imagePath;
        this.paymentMethodChosen = paymentMethod;
        this.custEmail = custEmail;
    }

    public String getCustName() {
        return custName;
    }

    public void setCustName(String custName) {
        this.custName = custName;
    }

    public String getCustAddress() {
        return custAddress;
    }

    public void setCustAddress(String custAddress) {
        this.custAddress = custAddress;
    }

    public String getCustCity() {
        return custCity;
    }

    public void setCustCity(String custCity) {
        this.custCity = custCity;
    }

    public String getCustTel() {
        return custTel;
    }

    public void setCustTel(String custTel) {
        this.custTel = custTel;
    }

    private void saveOrder() {
        try {
            OrderBean orderBean = new OrderBean();
            orderBean.setIdOrder(0);
            orderBean.setVendor(vendorName);
            orderBean.setOrderPlatform(gamePlt);
            orderBean.setOrderGame(gameName);
            orderBean.setOrderPrice(gamePrc);
            orderBean.setBuyerName(custName);
            orderBean.setBuyerAddress(custAddress);
            orderBean.setBuyerCity(custCity);
            orderBean.setBuyerTel(custTel);
            orderBean.setPaymentMethod(paymentMethodChosen);
            orderBean.setBuyerUsername(User.getInstance().getUsername());
            orderBean.setBuyerEmail(custEmail);
            orderBean.setStatus("Pending");

            ShopController shopController = new ShopController();
            shopController.saveOrder(orderBean);
        } catch (SQLException e) {
            ErrorMessage.displayErrorMessage();
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        platformLabel.setText(gamePlt);
        priceLabel.setText("€ " + gamePrc);
        gameLabel.setText(gameName);

        Image image = new Image(photoPath);
        img.setImage(image);

        this.saveOrder();
    }
}
