package gamesmarket.graphic_control.navigation;

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


public class NavigationButtons {

    private String style = "file:///C:/Users/Simone%20Bauco/IdeaProjects/GamesMarket/src/main/java/GamesMarket/css/style.css";

    private void show(Parent root, ActionEvent event) {
        Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);

        scene.getStylesheets().clear();
        scene.getStylesheets().add(style);

        stage.setScene(scene);
        stage.show();
    }

    @FXML
    public void homeButton(ActionEvent event) {
        try {
            Parent root = FXMLLoader.load(Main.class.getResource("/gamesmarket/home.fxml"));
            this.show(root, event);

        } catch (Exception e) {
            e.printStackTrace();
            e.getCause();
        }
    }

    @FXML
    public void shopButton(ActionEvent event) {
        try {
            Parent root = FXMLLoader.load(Main.class.getResource("/gamesmarket/shop.fxml"));
            this.show(root, event);

        } catch (Exception e) {
            e.printStackTrace();
            e.getCause();
        }
    }

    @FXML
    public void forumButton(ActionEvent event) {
        try {
            Parent root = FXMLLoader.load(Main.class.getResource("/gamesmarket/forum.fxml"));
            this.show(root, event);

        } catch (Exception e) {
            e.printStackTrace();
            e.getCause();
        }
    }

    @FXML
    public void exchangeButton(ActionEvent event) {
        try {
            Parent root = FXMLLoader.load(Main.class.getResource("/gamesmarket/exchange.fxml"));
            this.show(root, event);

        } catch (Exception e) {
            e.printStackTrace();
            e.getCause();
        }
    }

    @FXML
    public void profileButton(ActionEvent event) {
            try {
                Parent root = FXMLLoader.load(Main.class.getResource("/gamesmarket/profile.fxml"));
                this.show(root, event);

            } catch (Exception e) {
                e.printStackTrace();
                e.getCause();
            }

        }


    @FXML
    public void forumButtonSO(ActionEvent event) {
        try {
            Parent root = FXMLLoader.load(Main.class.getResource("/gamesmarket/forumSO.fxml"));
            this.show(root, event);

        } catch (Exception e) {
            e.printStackTrace();
            e.getCause();
        }
    }

    @FXML
    public void yourShopButtonSO(ActionEvent event) {
        try {
            Parent root = FXMLLoader.load(Main.class.getResource("/gamesmarket/yourShop.fxml"));
            this.show(root, event);

        } catch (Exception e) {
            e.printStackTrace();
            e.getCause();
        }
    }

    @FXML
    public void homeButtonSO(ActionEvent event) {
        try {
            Parent root = FXMLLoader.load(Main.class.getResource("/gamesmarket/shopOwnerHome.fxml"));
            this.show(root, event);

        } catch (Exception e) {
            e.printStackTrace();
            e.getCause();
        }
    }

    @FXML
    public void signIn(AnchorPane anchorPane) {
        try {

            Parent root = FXMLLoader.load(Main.class.getResource("/gamesmarket/login.fxml"));
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
            e.printStackTrace();
            e.getCause();
        }

    }

    }




