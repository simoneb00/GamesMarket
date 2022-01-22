package gamesmarket.graphic_control.mobile.shop;

import gamesmarket.exceptions.ErrorMessage;
import gamesmarket.graphic_control.mobile.NavigationButtons;
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
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

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

    @FXML
    public void confirm(ActionEvent event) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader();
            fxmlLoader.setLocation(Main.class.getResource("/gamesmarket/mobile/checkout_confirmation.fxml"));

            ConfirmationGraphicController confirmationGraphicController = new ConfirmationGraphicController(vendor, gamePlatform, gameName, gamePrice, gameImgPath, paymentMethod, User.getInstance().getEmailAddress());


            if (!paymentMethod.equals(pickupInStore)) {
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

            stage.setScene(scene);
            stage.show();

        } catch (IOException e) {
            e.printStackTrace();
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
