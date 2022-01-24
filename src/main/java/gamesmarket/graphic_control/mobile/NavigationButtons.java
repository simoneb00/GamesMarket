package gamesmarket.graphic_control.mobile;

import gamesmarket.exceptions.ErrorMessage;
import gamesmarket.main.Main;
import gamesmarket.model.ShopOwner;
import gamesmarket.model.User;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class NavigationButtons {

    protected Parent root;
    protected Stage stage;
    protected Scene scene;
    protected String css = "file:///C:/Users/Simone%20Bauco/IdeaProjects/GamesMarket/src/main/java/GamesMarket/css/style.css";

    protected void show(Parent root, ActionEvent event) {
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);

        scene.getStylesheets().clear();
        scene.getStylesheets().add(css);

        stage.setScene(scene);
        stage.show();
    }

    @FXML
    public void homeButton(ActionEvent event) {

        try {
            root = FXMLLoader.load(Main.class.getResource("/gamesmarket/mobile/homepage.fxml"));
            this.show(root, event);

        } catch (Exception e) {
            ErrorMessage.displayErrorMobile();
        }

    }

    @FXML
    public void profileButton(ActionEvent event) {
        if (User.getInstance().isLoggedIn()) {
            try {
                root = FXMLLoader.load(Main.class.getResource("/gamesmarket/mobile/profile.fxml"));
                this.show(root, event);

            } catch (Exception e) {
                ErrorMessage.displayErrorMobile();
            }
        } else {
            this.loginButton(event);
        }

    }

    @FXML
    public void exchangeButton(ActionEvent event) {
        if (User.getInstance().isLoggedIn()) {
            try {
                root = FXMLLoader.load(Main.class.getResource("/gamesmarket/mobile/exchange.fxml"));
                this.show(root, event);

            } catch (Exception e) {
                ErrorMessage.displayErrorMobile();
            }
        } else {
            this.loginButton(event);
        }


    }

    @FXML
    public void forumButton(ActionEvent event) {
        try {
            root = FXMLLoader.load(Main.class.getResource("/gamesmarket/mobile/forum.fxml"));
            this.show(root, event);

        } catch (Exception e) {
            ErrorMessage.displayErrorMobile();
        }

    }

    @FXML
    public void shopButton(ActionEvent event) {

        try {
            root = FXMLLoader.load(Main.class.getResource("/gamesmarket/mobile/shop.fxml"));
            this.show(root, event);

        } catch (Exception e) {
            ErrorMessage.displayErrorMobile();
        }

    }

    @FXML
    protected void loginButton(ActionEvent event) {
        try {
            root = FXMLLoader.load(Main.class.getResource("/gamesmarket/mobile/login.fxml"));
            this.show(root, event);

        } catch (Exception e) {
            ErrorMessage.displayErrorMobile();
        }

    }


}
