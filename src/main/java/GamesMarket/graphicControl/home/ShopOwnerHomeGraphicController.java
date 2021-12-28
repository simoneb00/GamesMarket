package GamesMarket.graphicControl.home;

import GamesMarket.graphicControl.navigation.ShopOwnerNavigationButtons;
import GamesMarket.main.Main;
import GamesMarket.model.ShopOwner;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.effect.ColorAdjust;
import javafx.scene.effect.GaussianBlur;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class ShopOwnerHomeGraphicController extends ShopOwnerNavigationButtons {

    @FXML
    private AnchorPane anchorPane;

    public void yourShopButtonPressed(ActionEvent event) {
        if (ShopOwner.getInstance().isLoggedIn())
            this.yourShopButton(event);
        else
            this.signIn();
    }

    public void profileButtonPressed(ActionEvent event) {
        if (ShopOwner.getInstance().isLoggedIn())
            this.profileButton(event);
        else
            this.signIn();
    }

    public void signIn() {
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
            anchorPane.setEffect(adj);

            loginStage.showAndWait();
            anchorPane.setEffect(null);

        } catch (Exception e) {
            e.printStackTrace();
            e.getCause();
        }

    }

}
