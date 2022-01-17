package GamesMarket.graphicControl.mobile;

import GamesMarket.main.Main;
import GamesMarket.model.User;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class NavigationButtons {

    Parent root;
    Stage stage;
    Scene scene;

    @FXML
    public void homeButton(ActionEvent event) {

        try {
            root = FXMLLoader.load(Main.class.getResource("/GamesMarket/mobile/homepage.fxml"));
            stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            scene = new Scene(root);

            scene.getStylesheets().clear();
            scene.getStylesheets().add("file:///C:/Users/Simone%20Bauco/IdeaProjects/GamesMarket/src/main/java/GamesMarket/css/style.css");

            stage.setScene(scene);
            stage.show();

        } catch (Exception e) {
            e.printStackTrace();
            e.getCause();
        }

    }

    @FXML
    public void profileButton(ActionEvent event) {
        if (User.getInstance().isLoggedIn()) {
            try {
                root = FXMLLoader.load(Main.class.getResource("/GamesMarket/mobile/profile.fxml"));
                stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                scene = new Scene(root);

                scene.getStylesheets().clear();
                scene.getStylesheets().add("file:///C:/Users/Simone%20Bauco/IdeaProjects/GamesMarket/src/main/java/GamesMarket/css/style.css");

                stage.setScene(scene);
                stage.show();

            } catch (Exception e) {
                e.printStackTrace();
                e.getCause();
            }
        } else {
            this.loginButton(event);
        }

    }

    @FXML
    public void exchangeButton(ActionEvent event) {
        if (User.getInstance().isLoggedIn()) {
            try {
                root = FXMLLoader.load(Main.class.getResource("/GamesMarket/mobile/exchange.fxml"));
                stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                scene = new Scene(root);

                scene.getStylesheets().clear();
                scene.getStylesheets().add("file:///C:/Users/Simone%20Bauco/IdeaProjects/GamesMarket/src/main/java/GamesMarket/css/style.css");

                stage.setScene(scene);
                stage.show();

            } catch (Exception e) {
                e.printStackTrace();
                e.getCause();
            }
        } else {
            this.loginButton(event);
        }


    }

    @FXML
    public void forumButton(ActionEvent event) {
        try {
            root = FXMLLoader.load(Main.class.getResource("/GamesMarket/mobile/forum.fxml"));
            stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            scene = new Scene(root);

            scene.getStylesheets().clear();
            scene.getStylesheets().add("file:///C:/Users/Simone%20Bauco/IdeaProjects/GamesMarket/src/main/java/GamesMarket/css/style.css");

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
            root = FXMLLoader.load(Main.class.getResource("/GamesMarket/mobile/shop.fxml"));
            stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            scene = new Scene(root);

            scene.getStylesheets().clear();
            scene.getStylesheets().add("file:///C:/Users/Simone%20Bauco/IdeaProjects/GamesMarket/src/main/java/GamesMarket/css/style.css");

            stage.setScene(scene);
            stage.show();

        } catch (Exception e) {
            e.printStackTrace();
            e.getCause();
        }

    }

    @FXML
    protected void loginButton(ActionEvent event) {
        try {
            root = FXMLLoader.load(Main.class.getResource("/GamesMarket/mobile/login.fxml"));
            stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            scene = new Scene(root);

            scene.getStylesheets().clear();
            scene.getStylesheets().add("file:///C:/Users/Simone%20Bauco/IdeaProjects/GamesMarket/src/main/java/GamesMarket/css/style.css");

            stage.setScene(scene);
            stage.show();

        } catch (Exception e) {
            e.printStackTrace();
            e.getCause();
        }

    }


}
