package gamesmarket.graphiccontrol.mobile.shop;

import gamesmarket.exceptions.ErrorMessage;
import gamesmarket.graphiccontrol.mobile.NavigationButtons;
import gamesmarket.main.Main;
import gamesmarket.model.User;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;


import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class CheckoutGraphicController extends NavigationButtons implements Initializable {

    @FXML
    private ChoiceBox<String> choiceBox;
    @FXML
    private ImageView gameImg;
    @FXML
    private Label gameLabel;
    @FXML
    private Label platformLabel;
    @FXML
    private Label priceLabel;
    @FXML
    private Button continueButton;
    @FXML
    private TextField cityTF;
    @FXML
    private TextField addressTF;
    @FXML
    private TextField nameTF;
    @FXML
    private TextField telTF;

    private String gameName;
    private String gamePlatform;
    private String gameImgPath;
    private double gamePrice;
    private String vendor;
    private String paymentMethod = null;
    private String pickupInStore = "Pickup in Store";

    public CheckoutGraphicController(String gameName, String gamePlatform, String gameImgPath, double gamePrice, String vendor) {
        this.gameName = gameName;
        this.gamePlatform = gamePlatform;
        this.gameImgPath = gameImgPath;
        this.gamePrice = gamePrice;
        this.vendor = vendor;
    }

    @FXML
    public void continueButtonPressed(ActionEvent event) {
        paymentMethod = choiceBox.getValue();
        if (paymentMethod == null)
            ErrorMessage.noPaymentMethodSelected();
        else if (paymentMethod.equals(pickupInStore)) {
            this.confirm(event);
        } else {
            try {
                FXMLLoader fxmlLoader = new FXMLLoader();
                fxmlLoader.setLocation(Main.class.getResource("/gamesmarket/mobile/checkout_shipping.fxml"));
                fxmlLoader.setController(this);

                Parent root = fxmlLoader.load();
                this.show(root, event);

            } catch (IOException e) {
                ErrorMessage.displayErrorMobile();
            }
        }
    }

    @FXML
    public void backButtonPressed(ActionEvent event) {
        this.shopButton(event);
    }

    @FXML
    public void backToSelectPaymentMethod(ActionEvent event) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader();
            fxmlLoader.setLocation(Main.class.getResource("/gamesmarket/mobile/checkout_payment.fxml"));
            fxmlLoader.setController(this);

            Parent root = fxmlLoader.load();
            this.show(root, event);

        } catch (IOException e) {
            ErrorMessage.displayErrorMobile();
        }
    }

    @FXML
    public void confirm(ActionEvent event) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader();
            fxmlLoader.setLocation(Main.class.getResource("/gamesmarket/mobile/checkout_confirmation.fxml"));

            ConfirmationGraphicController confirmationGraphicController = new ConfirmationGraphicController(vendor, gamePlatform, gameName, gamePrice, gameImgPath, paymentMethod, User.getInstance().getEmailAddress());


            if (!paymentMethod.equals(pickupInStore)) {
                confirmationGraphicController.setCustName(nameTF.getText());
                confirmationGraphicController.setCustAddress(addressTF.getText());
                confirmationGraphicController.setCustCity(cityTF.getText());
                confirmationGraphicController.setCustTel(telTF.getText());
            } else {
                confirmationGraphicController.setCustName(User.getInstance().getFirstName() + " " + User.getInstance().getLastName());
            }

            fxmlLoader.setController(confirmationGraphicController);

            Parent root = fxmlLoader.load();
            this.show(root, event);

        } catch (IOException e) {
            ErrorMessage.displayErrorMobile();
        }
    }


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        choiceBox.getItems().add(pickupInStore);
        choiceBox.getItems().add("Cash on delivery");
        choiceBox.getItems().add("Paypal");
        choiceBox.getItems().add("Credit / Debit card");

        gameLabel.setText(gameName);
        platformLabel.setText(gamePlatform);
        priceLabel.setText("€" + gamePrice);
        Image image = new Image(gameImgPath);
        gameImg.setImage(image);
    }

}
