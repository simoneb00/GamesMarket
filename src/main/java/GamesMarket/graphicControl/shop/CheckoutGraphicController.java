package GamesMarket.graphicControl.shop;

import GamesMarket.main.Main;
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
import org.w3c.dom.Text;

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
    private TextField nameTF;
    @FXML
    private TextField telTF;


    private String gameName;
    private String gamePlatform;
    private String gameImgPath;
    private double gamePrice;
    private String vendor;
    private int numCheckBoxesSelected = 0;


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

    public void back(ActionEvent event) {
        Stage stage = (Stage) ((Button)event.getSource()).getScene().getWindow();
        stage.close();
    }


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
                    fxmlLoader.setLocation(Main.class.getResource("/GamesMarket/checkout_shipping.fxml"));
                    fxmlLoader.setController(this);

                    Parent root = fxmlLoader.load();
                    Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                    Scene scene = new Scene(root);

                    stage.getIcons().add(new Image("file:///C:/Users/Simone%20Bauco/IdeaProjects/GamesMarket/src/main/resources/images/GamesMarketLogo.png"));
                    stage.setTitle("Checkout");

                    scene.getStylesheets().clear();
                    scene.getStylesheets().add("file:///C:/Users/Simone%20Bauco/IdeaProjects/GamesMarket/src/main/java/GamesMarket/css/style.css");

                    stage.setScene(scene);
                    stage.show();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            } else {
                this.confirmButton(event);
            }
        }

    }

    public void confirmButton(ActionEvent event) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader();
            fxmlLoader.setLocation(Main.class.getResource("/GamesMarket/checkout_confirmation.fxml"));

            ConfirmationGraphicController confirmationGraphicController = new ConfirmationGraphicController(vendor, gamePlatform, gameName, gamePrice, gameImgPath);
            fxmlLoader.setController(confirmationGraphicController);

            Parent root = fxmlLoader.load();
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            Scene scene = new Scene(root);

            stage.getIcons().add(new Image("file:///C:/Users/Simone%20Bauco/IdeaProjects/GamesMarket/src/main/resources/images/GamesMarketLogo.png"));
            stage.setTitle("Confirmation");

            scene.getStylesheets().clear();
            scene.getStylesheets().add("file:///C:/Users/Simone%20Bauco/IdeaProjects/GamesMarket/src/main/java/GamesMarket/css/style.css");

            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void backToSelectPayment(ActionEvent event) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader();
            fxmlLoader.setLocation(Main.class.getResource("/GamesMarket/checkout_payment.fxml"));
            fxmlLoader.setController(this);

            Parent root = fxmlLoader.load();
            Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
            Scene scene = new Scene(root);

            stage.getIcons().add(new Image("file:///C:/Users/Simone%20Bauco/IdeaProjects/GamesMarket/src/main/resources/images/GamesMarketLogo.png"));
            stage.setTitle("Checkout");

            scene.getStylesheets().clear();
            scene.getStylesheets().add("file:///C:/Users/Simone%20Bauco/IdeaProjects/GamesMarket/src/main/java/GamesMarket/css/style.css");

            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    private void configureCheckBox(CheckBox checkBox) {
        if (checkBox.isSelected()) {
            numCheckBoxesSelected++;
        }
    }

    public void showConfirmButton(ActionEvent event) {
        continueButton.setText("Confirm");
    }

    public void hideConfirmButton() {
        continueButton.setText("Continue");
    }
}
