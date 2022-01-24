package gamesmarket.exceptions;

import gamesmarket.main.Main;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import java.io.IOException;

public class ErrorMessage {

    private static final String TITLE = "GamesMarket";


    private static void displayMessage(Parent root) {
        Stage stage = new Stage();
        Scene scene = new Scene(root);
        stage.setTitle(TITLE);
        stage.setScene(scene);
        stage.show();
    }


    public static void displayErrorMessage() {
        try {
            Parent root = FXMLLoader.load(Main.class.getResource("/gamesmarket/error.fxml"));
            displayMessage(root);
        } catch (IOException e) {
            e.getCause();
            e.printStackTrace();
        }
    }


    public static void displayImageNotFoundMessage() {
        try {
            Parent root = FXMLLoader.load(Main.class.getResource("/gamesmarket/mobile/error_image.fxml"));
            displayMessage(root);
        } catch (IOException e) {
            ErrorMessage.displayErrorMessage();
        }
    }

    public static void displayDuplicatedGameInTradelist() {
        try {
            Parent root = FXMLLoader.load(Main.class.getResource("/gamesmarket/mobile/error_dup_game_tradelist.fxml"));
            displayMessage(root);
        } catch (IOException e) {
            ErrorMessage.displayErrorMessage();
        }
    }

    public static void displayDuplicatedGameInWishlist() {
        try {
            Parent root = FXMLLoader.load(Main.class.getResource("/gamesmarket/mobile/error_dup_game_wishlist.fxml"));
            displayMessage(root);
        } catch (IOException e) {
            ErrorMessage.displayErrorMessage();
        }
    }

    public static void noPaymentMethodSelected() {
        try {
            Parent root = FXMLLoader.load(Main.class.getResource("/gamesmarket/mobile/error_no_payment_selected.fxml"));
            displayMessage(root);
        } catch (IOException e) {
            ErrorMessage.displayErrorMessage();
        }
    }

    public static void displayInvalidPriceMessage() {
        try {
            Parent root = FXMLLoader.load(Main.class.getResource("/gamesmarket/mobile/error_invalid_price.fxml"));
            displayMessage(root);
        } catch (IOException e) {
            ErrorMessage.displayErrorMessage();
        }
    }

    public static void displayEmptyPriceMessage() {
        try {
            Parent root = FXMLLoader.load(Main.class.getResource("/gamesmarket/mobile/error_empty_price.fxml"));
            displayMessage(root);
        } catch (IOException e) {
            ErrorMessage.displayErrorMessage();
        }
    }

    public static void displayEmptyGameNameMessage() {
        try {
            Parent root = FXMLLoader.load(Main.class.getResource("/gamesmarket/mobile/error_empty_game.fxml"));
            displayMessage(root);
        } catch (IOException e) {
            ErrorMessage.displayErrorMessage();
        }
    }

    public static void displayDuplicatedGameInShopError() {
        try {
            Parent root = FXMLLoader.load(Main.class.getResource("/gamesmarket/mobile/error_dup_game_shop.fxml"));
            displayMessage(root);
        } catch (IOException e) {
            ErrorMessage.displayErrorMessage();
        }
    }

    public static void displayErrorMobile() {
        try {
            Parent root = FXMLLoader.load(Main.class.getResource("/gamesmarket/mobile/generic_error.fxml"));
            displayMessage(root);
        } catch (IOException e) {
            ErrorMessage.displayErrorMessage();
        }
    }

    public static void displayInvalidNameMessage() {
        try {
            Parent root = FXMLLoader.load(Main.class.getResource("/gamesmarket/mobile/error_invalid_shop_name.fxml"));
            displayMessage(root);
        } catch (IOException e) {
            ErrorMessage.displayErrorMessage();
        }
    }

    public static void missingInformationMessage() {
        try {
            Parent root = FXMLLoader.load(Main.class.getResource("/gamesmarket/mobile/missing_information_message.fxml"));
            displayMessage(root);
        } catch (IOException e) {
            ErrorMessage.displayErrorMessage();
        }
    }

    public void close(ActionEvent event) {
        Stage stage = (Stage) ((Button) event.getSource()).getScene().getWindow();
        stage.close();
    }
}
