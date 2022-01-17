package GamesMarket.exceptions;

import GamesMarket.main.Main;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import java.io.IOException;

public class ErrorMessage {

    public static void displayErrorMessage() {
        try {
            Stage stage = new Stage();
            Parent root = FXMLLoader.load(Main.class.getResource("/GamesMarket/error.fxml"));
            Scene scene = new Scene(root);

            scene.getStylesheets().clear();
            scene.getStylesheets().add("file:///C:/Users/Simone%20Bauco/IdeaProjects/GamesMarket/src/main/java/GamesMarket/css/style.css");

            stage.getIcons().add(new Image("file:///C:/Users/Simone%20Bauco/IdeaProjects/GamesMarket/src/main/resources/images/GamesMarketLogo.png"));
            stage.setTitle("GamesMarket");
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            e.getCause();
            e.printStackTrace();
        }
    }


    public static void displayImageNotFoundMessage() {
        try {
            Stage stage = new Stage();
            Parent root = FXMLLoader.load(Main.class.getResource("/GamesMarket/mobile/error_image.fxml"));
            Scene scene = new Scene(root);

            scene.getStylesheets().clear();
            scene.getStylesheets().add("file:///C:/Users/Simone%20Bauco/IdeaProjects/GamesMarket/src/main/java/GamesMarket/css/style.css");

            stage.getIcons().add(new Image("file:///C:/Users/Simone%20Bauco/IdeaProjects/GamesMarket/src/main/resources/images/GamesMarketLogo.png"));
            stage.setTitle("GamesMarket");
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            e.getCause();
            e.printStackTrace();
        }
    }

    public static void displayDuplicatedGameInTradelist() {
        try {
            Stage stage = new Stage();
            Parent root = FXMLLoader.load(Main.class.getResource("/GamesMarket/mobile/error_dup_game_tradelist.fxml"));
            Scene scene = new Scene(root);

            scene.getStylesheets().clear();
            scene.getStylesheets().add("file:///C:/Users/Simone%20Bauco/IdeaProjects/GamesMarket/src/main/java/GamesMarket/css/style.css");

            stage.getIcons().add(new Image("file:///C:/Users/Simone%20Bauco/IdeaProjects/GamesMarket/src/main/resources/images/GamesMarketLogo.png"));
            stage.setTitle("GamesMarket");
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            e.getCause();
            e.printStackTrace();
        }
    }

    public static void displayDuplicatedGameInWishlist() {
        try {
            Stage stage = new Stage();
            Parent root = FXMLLoader.load(Main.class.getResource("/GamesMarket/mobile/error_dup_game_wishlist.fxml"));
            Scene scene = new Scene(root);

            scene.getStylesheets().clear();
            scene.getStylesheets().add("file:///C:/Users/Simone%20Bauco/IdeaProjects/GamesMarket/src/main/java/GamesMarket/css/style.css");

            stage.getIcons().add(new Image("file:///C:/Users/Simone%20Bauco/IdeaProjects/GamesMarket/src/main/resources/images/GamesMarketLogo.png"));
            stage.setTitle("GamesMarket");
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            e.getCause();
            e.printStackTrace();
        }
    }

    public static void noPaymentMethodSelected() {
        try {
            Stage stage = new Stage();
            Parent root = FXMLLoader.load(Main.class.getResource("/GamesMarket/mobile/error_no_payment_selected.fxml"));
            Scene scene = new Scene(root);

            scene.getStylesheets().clear();
            scene.getStylesheets().add("file:///C:/Users/Simone%20Bauco/IdeaProjects/GamesMarket/src/main/java/GamesMarket/css/style.css");

            stage.getIcons().add(new Image("file:///C:/Users/Simone%20Bauco/IdeaProjects/GamesMarket/src/main/resources/images/GamesMarketLogo.png"));
            stage.setTitle("GamesMarket");
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            e.getCause();
            e.printStackTrace();
        }
    }

    public static void displayInvalidPriceMessage() {
        try {
            Stage stage = new Stage();
            Parent root = FXMLLoader.load(Main.class.getResource("/GamesMarket/mobile/error_invalid_price.fxml"));
            Scene scene = new Scene(root);

            scene.getStylesheets().clear();
            scene.getStylesheets().add("file:///C:/Users/Simone%20Bauco/IdeaProjects/GamesMarket/src/main/java/GamesMarket/css/style.css");

            stage.getIcons().add(new Image("file:///C:/Users/Simone%20Bauco/IdeaProjects/GamesMarket/src/main/resources/images/GamesMarketLogo.png"));
            stage.setTitle("GamesMarket");
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            e.getCause();
            e.printStackTrace();
        }
    }

    public static void displayEmptyPriceMessage() {
        try {
            Stage stage = new Stage();
            Parent root = FXMLLoader.load(Main.class.getResource("/GamesMarket/mobile/error_empty_price.fxml"));
            Scene scene = new Scene(root);

            scene.getStylesheets().clear();
            scene.getStylesheets().add("file:///C:/Users/Simone%20Bauco/IdeaProjects/GamesMarket/src/main/java/GamesMarket/css/style.css");

            stage.getIcons().add(new Image("file:///C:/Users/Simone%20Bauco/IdeaProjects/GamesMarket/src/main/resources/images/GamesMarketLogo.png"));
            stage.setTitle("GamesMarket");
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            e.getCause();
            e.printStackTrace();
        }
    }

    public static void displayEmptyGameNameMessage() {
        try {
            Stage stage = new Stage();
            Parent root = FXMLLoader.load(Main.class.getResource("/GamesMarket/mobile/error_empty_game.fxml"));
            Scene scene = new Scene(root);

            scene.getStylesheets().clear();
            scene.getStylesheets().add("file:///C:/Users/Simone%20Bauco/IdeaProjects/GamesMarket/src/main/java/GamesMarket/css/style.css");

            stage.getIcons().add(new Image("file:///C:/Users/Simone%20Bauco/IdeaProjects/GamesMarket/src/main/resources/images/GamesMarketLogo.png"));
            stage.setTitle("GamesMarket");
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            e.getCause();
            e.printStackTrace();
        }
    }

    public static void displayDuplicatedGameInShopError() {
        try {
            Stage stage = new Stage();
            Parent root = FXMLLoader.load(Main.class.getResource("/GamesMarket/mobile/error_dup_game_shop.fxml"));
            Scene scene = new Scene(root);

            scene.getStylesheets().clear();
            scene.getStylesheets().add("file:///C:/Users/Simone%20Bauco/IdeaProjects/GamesMarket/src/main/java/GamesMarket/css/style.css");

            stage.getIcons().add(new Image("file:///C:/Users/Simone%20Bauco/IdeaProjects/GamesMarket/src/main/resources/images/GamesMarketLogo.png"));
            stage.setTitle("GamesMarket");
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            e.getCause();
            e.printStackTrace();
        }
    }

    public static void displayErrorMobile() {
        try {
            Stage stage = new Stage();
            Parent root = FXMLLoader.load(Main.class.getResource("/GamesMarket/mobile/generic_error.fxml"));
            Scene scene = new Scene(root);

            scene.getStylesheets().clear();
            scene.getStylesheets().add("file:///C:/Users/Simone%20Bauco/IdeaProjects/GamesMarket/src/main/java/GamesMarket/css/style.css");

            stage.getIcons().add(new Image("file:///C:/Users/Simone%20Bauco/IdeaProjects/GamesMarket/src/main/resources/images/GamesMarketLogo.png"));
            stage.setTitle("GamesMarket");
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            e.getCause();
            e.printStackTrace();
        }
    }

    public static void displayInvalidNameMessage() {
        try {
            Stage stage = new Stage();
            Parent root = FXMLLoader.load(Main.class.getResource("/GamesMarket/mobile/error_invalid_shop_name.fxml"));
            Scene scene = new Scene(root);

            scene.getStylesheets().clear();
            scene.getStylesheets().add("file:///C:/Users/Simone%20Bauco/IdeaProjects/GamesMarket/src/main/java/GamesMarket/css/style.css");

            stage.getIcons().add(new Image("file:///C:/Users/Simone%20Bauco/IdeaProjects/GamesMarket/src/main/resources/images/GamesMarketLogo.png"));
            stage.setTitle("GamesMarket");
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            e.getCause();
            e.printStackTrace();
        }
    }

    public static void missingInformationMessage() {
        try {
            Stage stage = new Stage();
            Parent root = FXMLLoader.load(Main.class.getResource("/GamesMarket/mobile/missing_information_message.fxml"));
            Scene scene = new Scene(root);

            scene.getStylesheets().clear();
            scene.getStylesheets().add("file:///C:/Users/Simone%20Bauco/IdeaProjects/GamesMarket/src/main/java/GamesMarket/css/style.css");

            stage.getIcons().add(new Image("file:///C:/Users/Simone%20Bauco/IdeaProjects/GamesMarket/src/main/resources/images/GamesMarketLogo.png"));
            stage.setTitle("GamesMarket");
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            e.getCause();
            e.printStackTrace();
        }
    }

    public void close(ActionEvent event) {
        Stage stage = (Stage) ((Button) event.getSource()).getScene().getWindow();
        stage.close();
    }
}
