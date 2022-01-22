package gamesmarket.graphic_control.navigation;

import gamesmarket.main.Main;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;


public class NavigationButtons {

    private Parent root;
    private Scene scene;
    private Stage stage;
    private String style = "file:///C:/Users/Simone%20Bauco/IdeaProjects/GamesMarket/src/main/java/GamesMarket/css/style.css";

    @FXML
    public void homeButton(ActionEvent event) {
        try {
            root = FXMLLoader.load(Main.class.getResource("/gamesmarket/home.fxml"));
            stage = (Stage)((Node)event.getSource()).getScene().getWindow();
            scene = new Scene(root);

            scene.getStylesheets().clear();
            scene.getStylesheets().add(style);

            stage.setScene(scene);
            stage.show();

        } catch (Exception e) {
            e.printStackTrace();
            e.getCause();
        }
    }

    @FXML
    public void shopButton(ActionEvent event) {
        try {
            root = FXMLLoader.load(Main.class.getResource("/gamesmarket/shop.fxml"));
            stage = (Stage)((Node)event.getSource()).getScene().getWindow();
            scene = new Scene(root);

            scene.getStylesheets().clear();
            scene.getStylesheets().add(style);

            stage.setScene(scene);
            stage.show();

        } catch (Exception e) {
            e.printStackTrace();
            e.getCause();
        }
    }

    @FXML
    public void forumButton(ActionEvent event) {
        try {
            root = FXMLLoader.load(Main.class.getResource("/gamesmarket/forum.fxml"));
            stage = (Stage)((Node)event.getSource()).getScene().getWindow();
            scene = new Scene(root);

            scene.getStylesheets().clear();
            scene.getStylesheets().add(style);

            stage.setScene(scene);
            stage.show();

        } catch (Exception e) {
            e.printStackTrace();
            e.getCause();
        }
    }

    @FXML
    public void exchangeButton(ActionEvent event) {
        try {
            root = FXMLLoader.load(Main.class.getResource("/gamesmarket/exchange.fxml"));
            stage = (Stage)((Node)event.getSource()).getScene().getWindow();
            scene = new Scene(root);

            scene.getStylesheets().clear();
            scene.getStylesheets().add(style);

            stage.setScene(scene);
            stage.show();

        } catch (Exception e) {
            e.printStackTrace();
            e.getCause();
        }
    }

    @FXML
    public void profileButton(ActionEvent event) {
            try {
                root = FXMLLoader.load(Main.class.getResource("/gamesmarket/profile.fxml"));
                stage = (Stage)((Node)event.getSource()).getScene().getWindow();
                scene = new Scene(root);

                scene.getStylesheets().clear();
                scene.getStylesheets().add(style);

                stage.setScene(scene);
                stage.show();

            } catch (Exception e) {
                e.printStackTrace();
                e.getCause();
            }

        }


    @FXML
    public void forumButtonSO(ActionEvent event) {
        try {
            root = FXMLLoader.load(Main.class.getResource("/gamesmarket/forumSO.fxml"));
            stage = (Stage)((Node)event.getSource()).getScene().getWindow();
            scene = new Scene(root);

            scene.getStylesheets().clear();
            scene.getStylesheets().add(style);

            stage.setScene(scene);
            stage.show();

        } catch (Exception e) {
            e.printStackTrace();
            e.getCause();
        }
    }

    @FXML
    public void yourShopButtonSO(ActionEvent event) {
        try {
            root = FXMLLoader.load(Main.class.getResource("/gamesmarket/yourShop.fxml"));
            stage = (Stage)((Node)event.getSource()).getScene().getWindow();
            scene = new Scene(root);

            scene.getStylesheets().clear();
            scene.getStylesheets().add(style);

            stage.setScene(scene);
            stage.show();

        } catch (Exception e) {
            e.printStackTrace();
            e.getCause();
        }
    }

    @FXML
    public void homeButtonSO(ActionEvent event) {
        try {
            root = FXMLLoader.load(Main.class.getResource("/gamesmarket/shopOwnerHome.fxml"));
            stage = (Stage)((Node)event.getSource()).getScene().getWindow();
            scene = new Scene(root);

            scene.getStylesheets().clear();
            scene.getStylesheets().add(style);

            stage.setScene(scene);
            stage.show();

        } catch (Exception e) {
            e.printStackTrace();
            e.getCause();
        }
    }



    }




