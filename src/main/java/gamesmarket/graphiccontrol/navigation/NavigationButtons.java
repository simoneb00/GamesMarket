package gamesmarket.graphiccontrol.navigation;

import gamesmarket.exceptions.ErrorMessage;
import gamesmarket.main.Main;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.effect.ColorAdjust;
import javafx.scene.effect.GaussianBlur;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.util.Objects;


public class NavigationButtons {

    private void show(Parent root, ActionEvent event) {
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);

        scene.getStylesheets().clear();
        String style = "file:///C:/Users/Simone%20Bauco/IdeaProjects/GamesMarket/src/main/java/GamesMarket/css/style.css";
        scene.getStylesheets().add(style);

        stage.setScene(scene);
        stage.show();
    }

    @FXML
    public void homeButton(ActionEvent event) {
        try {
            Parent root = FXMLLoader.load(Objects.requireNonNull(Main.class.getResource("/gamesmarket/homepage.fxml")));
            this.show(root, event);

        } catch (Exception e) {
            ErrorMessage.displayErrorMobile();
        }
    }

    @FXML
    public void shopButton(ActionEvent event) {
        try {
            Parent root = FXMLLoader.load(Objects.requireNonNull(Main.class.getResource("/gamesmarket/shop.fxml")));
            this.show(root, event);

        } catch (Exception e) {
            ErrorMessage.displayErrorMobile();
        }
    }

    @FXML
    public void forumButton(ActionEvent event) {
        try {
            Parent root = FXMLLoader.load(Objects.requireNonNull(Main.class.getResource("/gamesmarket/forum.fxml")));
            this.show(root, event);

        } catch (Exception e) {
            ErrorMessage.displayErrorMobile();
        }
    }

    @FXML
    public void exchangeButton(ActionEvent event) {
        try {
            Parent root = FXMLLoader.load(Objects.requireNonNull(Main.class.getResource("/gamesmarket/exchange.fxml")));
            this.show(root, event);

        } catch (Exception e) {
            ErrorMessage.displayErrorMobile();
        }
    }

    @FXML
    public void profileButton(ActionEvent event) {
        try {
            Parent root = FXMLLoader.load(Objects.requireNonNull(Main.class.getResource("/gamesmarket/profile.fxml")));
            this.show(root, event);

        } catch (Exception e) {
            ErrorMessage.displayErrorMobile();
        }

    }


    @FXML
    public void forumButtonSO(ActionEvent event) {
        try {
            Parent root = FXMLLoader.load(Objects.requireNonNull(Main.class.getResource("/gamesmarket/forumSO.fxml")));
            this.show(root, event);

        } catch (Exception e) {
            ErrorMessage.displayErrorMobile();
        }
    }

    @FXML
    public void yourShopButtonSO(ActionEvent event) {
        try {
            Parent root = FXMLLoader.load(Objects.requireNonNull(Main.class.getResource("/gamesmarket/yourShop.fxml")));
            this.show(root, event);

        } catch (Exception e) {
            ErrorMessage.displayErrorMobile();
        }
    }

    @FXML
    public void homeButtonSO(ActionEvent event) {
        try {
            Parent root = FXMLLoader.load(Objects.requireNonNull(Main.class.getResource("/gamesmarket/shop_owner_homepage.fxml")));
            this.show(root, event);

        } catch (Exception e) {
            ErrorMessage.displayErrorMobile();
        }
    }

    @FXML
    public void signIn(AnchorPane anchorPane) {
        try {

            Parent root = FXMLLoader.load(Objects.requireNonNull(Main.class.getResource("/gamesmarket/login.fxml")));
            Stage loginStage = new Stage();
            Scene loginScene = new Scene(root);
            loginStage.initStyle(StageStyle.TRANSPARENT);
            loginScene.setFill(Color.TRANSPARENT);
            loginStage.setScene(loginScene);

            GaussianBlur blur = new GaussianBlur(50);
            ColorAdjust adj = new ColorAdjust(-0.1, -0.1, -0.1, -0.1);
            adj.setInput(blur);
            anchorPane.setEffect(adj);

            loginStage.showAndWait();
            anchorPane.setEffect(null);

        } catch (Exception e) {
            ErrorMessage.displayErrorMobile();
        }

    }

}




