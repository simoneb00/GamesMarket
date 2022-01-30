package gamesmarket.graphiccontrol.shop;

import gamesmarket.boundaries.PaypalPaymentBoundary;
import gamesmarket.exceptions.ErrorMessage;
import gamesmarket.main.Main;
import gamesmarket.model.User;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;


public class CheckoutGraphicController implements Initializable {

    @FXML
    private CheckBox card;
    @FXML
    private CheckBox cashOnDelivery;
    @FXML
    private Label gameLabel;
    @FXML
    private ImageView img;
    @FXML
    private CheckBox paypal;
    @FXML
    private CheckBox pickUpInStore;
    @FXML
    private Label platformLabel;
    @FXML
    private Label priceLabel;
    @FXML
    private Label warningLabel;
    @FXML
    private Button continueButton;
    @FXML
    private TextField addressTF;
    @FXML
    private TextField cityTF;
    @FXML
    private TextField telTF;
    @FXML
    private TextField nameTF;


    private String gameName;
    private String gamePlatform;
    private String gameImgPath;
    private double gamePrice;
    private String vendor;
    private int numCheckBoxesSelected = 0;
    private String paymentMethod;
    private String logo = "file:///C:/Users/Simone%20Bauco/IdeaProjects/GamesMarket/src/main/resources/images/GamesMarketLogo.png";
    private String style = "file:///C:/Users/Simone%20Bauco/IdeaProjects/GamesMarket/src/main/java/GamesMarket/css/style.css";


    public CheckoutGraphicController(String name, String platform, double price, String imagePath, String vendor) {
        this.gamePrice = price;
        this.gameName = name;
        this.gameImgPath = imagePath;
        this.gamePlatform = platform;
        this.vendor = vendor;
    }


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        gameLabel.setText(gameName);
        platformLabel.setText(gamePlatform);
        priceLabel.setText("€" + gamePrice);
        Image image = new Image(gameImgPath);
        img.setImage(image);
    }

    @FXML
    public void back(ActionEvent event) {
        Stage stage = (Stage) ((Button) event.getSource()).getScene().getWindow();
        stage.close();
    }


    @FXML
    public void continueButton(ActionEvent event) {
        numCheckBoxesSelected = 0;
        configureCheckBox(cashOnDelivery);
        configureCheckBox(paypal);
        configureCheckBox(card);
        configureCheckBox(pickUpInStore);

        if (numCheckBoxesSelected == 0) {
            warningLabel.setText("Please select a payment method.");
        } else if (numCheckBoxesSelected > 1) {
            warningLabel.setText("Please select only one payment method");
        } else {
            if (!pickUpInStore.isSelected()) {
                try {
                    FXMLLoader fxmlLoader = new FXMLLoader();
                    fxmlLoader.setLocation(Main.class.getResource("/gamesmarket/checkout_shipping.fxml"));
                    fxmlLoader.setController(this);

                    Parent root = fxmlLoader.load();
                    Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                    Scene scene = new Scene(root);

                    stage.setTitle("Checkout");
                    stage.getIcons().add(new Image(logo));

                    scene.getStylesheets().clear();
                    scene.getStylesheets().add(style);

                    stage.setScene(scene);
                    stage.show();
                } catch (IOException e) {
                    ErrorMessage.displayErrorMobile();
                }
            } else {
                this.confirmButton(event);  // if user select pick up in store payment method, he does not need to enter shipping infos
            }
        }

    }

    @FXML
    public void confirmButton(ActionEvent event) {

        try {
            if (cashOnDelivery.isSelected())
                paymentMethod = "Cash on delivery";
            else if (paypal.isSelected()) {
                PaypalPaymentBoundary paypalPaymentBoundary = new PaypalPaymentBoundary();
                if (paypalPaymentBoundary.validatePayment())
                    paymentMethod = "PayPal";
                else
                    throw new Exception();
            } else if (card.isSelected())
                paymentMethod = "Credit/Debit card";
            else if (pickUpInStore.isSelected())
                paymentMethod = "Pickup in store";


            FXMLLoader fxmlLoader = new FXMLLoader();
            fxmlLoader.setLocation(Main.class.getResource("/gamesmarket/checkout_confirmation.fxml"));

            ConfirmationGraphicController confirmationGraphicController = new ConfirmationGraphicController(vendor, gamePlatform, gameName, gamePrice, gameImgPath, paymentMethod, User.getInstance().getEmailAddress());

            if (!pickUpInStore.isSelected()) {      // links to confirmation gc shipping information, if present
                confirmationGraphicController.setBuyerName(nameTF.getText());
                confirmationGraphicController.setBuyerAddress(addressTF.getText());
                confirmationGraphicController.setBuyerCity(cityTF.getText());
                confirmationGraphicController.setBuyerTel(telTF.getText());
            } else {
                confirmationGraphicController.setBuyerName(User.getInstance().getFirstName() + " " + User.getInstance().getLastName());
            }

            fxmlLoader.setController(confirmationGraphicController);

            Parent root = fxmlLoader.load();
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            Scene scene = new Scene(root);

            stage.getIcons().add(new Image(logo));

            scene.getStylesheets().clear();
            scene.getStylesheets().add(style);

            stage.setScene(scene);
            stage.show();
        } catch (Exception e) {
            ErrorMessage.displayErrorMobile();
        }
    }

    @FXML
    public void backToSelectPayment(ActionEvent event) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader();
            fxmlLoader.setLocation(Main.class.getResource("/gamesmarket/checkout_payment.fxml"));
            fxmlLoader.setController(this);

            Parent root = fxmlLoader.load();
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            Scene scene = new Scene(root);

            stage.getIcons().add(new Image(logo));
            stage.setTitle("Checkout");

            scene.getStylesheets().clear();
            scene.getStylesheets().add(style);

            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            ErrorMessage.displayErrorMobile();
        }
    }


    private void configureCheckBox(CheckBox checkBox) {
        if (checkBox.isSelected()) {
            numCheckBoxesSelected++;
        }
    }

    @FXML
    public void showConfirmButton() {
        continueButton.setText("Confirm");
    }

    @FXML
    public void hideConfirmButton() {
        continueButton.setText("Continue");
    }
}
