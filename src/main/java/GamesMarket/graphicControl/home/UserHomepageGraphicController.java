package GamesMarket.graphicControl.home;

import GamesMarket.graphicControl.navigation.NavigationButtons;
import GamesMarket.main.Main;
import GamesMarket.model.ShopOwner;
import GamesMarket.model.User;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.effect.ColorAdjust;
import javafx.scene.effect.GaussianBlur;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.net.URL;
import java.util.ResourceBundle;

public class UserHomepageGraphicController extends NavigationButtons implements Initializable {

    private Stage stage;
    private Parent root;
    private Scene scene;

    @FXML
    private AnchorPane homePane;
    @FXML
    private Label welcomeLabel;
    @FXML
    private Button signInButton;

    public void signInButtonPressed(ActionEvent event) {
        try {

            Parent root = FXMLLoader.load(Main.class.getResource("/GamesMarket/login.fxml"));
            Stage loginStage = new Stage();
            Scene loginScene = new Scene(root, 600, 400);
            loginScene.setFill(Color.TRANSPARENT);
            loginStage.initStyle(StageStyle.TRANSPARENT);
            loginStage.setScene(loginScene);

            GaussianBlur blur = new GaussianBlur(55);
            ColorAdjust adj = new ColorAdjust(-0.1, -0.1, -0.1, -0.1);
            adj.setInput(blur);
            homePane.setEffect(adj);

            loginStage.showAndWait();
            homePane.setEffect(null);

            if (User.getInstance().isLoggedIn()) {
                signInButton.setVisible(false);
                signInButton.isDisabled();
            } else if (ShopOwner.getInstance().isLoggedIn()) {
                try {
                    root = FXMLLoader.load(Main.class.getResource("/GamesMarket/shopOwnerHome.fxml"));
                    stage = (Stage)((Node)event.getSource()).getScene().getWindow();
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

        } catch (Exception e) {
            e.printStackTrace();
            e.getCause();
        }

    }

    public void profileButtonPressed(ActionEvent event) {
        if (User.getInstance().isLoggedIn()) {
            this.profileButton(event);
        } else {
            this.signInButtonPressed(event);
        }
    }

    public void exchangeButtonPressed(ActionEvent event) {
        if (User.getInstance().isLoggedIn()) {
            this.exchangeButton(event);
        } else {
            this.signInButtonPressed(event);
        }
    }



    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        if (User.getInstance().isLoggedIn() || ShopOwner.getInstance().isLoggedIn()) {
            signInButton.setVisible(false);
            signInButton.isDisabled();
        }
    }
}
