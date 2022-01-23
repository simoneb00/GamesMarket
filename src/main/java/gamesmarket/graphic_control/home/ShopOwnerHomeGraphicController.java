package gamesmarket.graphic_control.home;


import gamesmarket.graphic_control.navigation.NavigationButtons;
import gamesmarket.main.Main;
import gamesmarket.model.ShopOwner;
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

public class ShopOwnerHomeGraphicController extends NavigationButtons {

    @FXML
    private AnchorPane anchorPane;

    @FXML
    public void yourShopButtonPressed(ActionEvent event) {
        if (ShopOwner.getInstance().isLoggedIn()) {
            if (ShopOwner.getInstance().getShop() != null) {
                this.yourShopButtonSO(event);
            } else {
                try {
                    Parent root = FXMLLoader.load(Main.class.getResource("/gamesmarket/createNewShop.fxml"));
                    Stage stage = new Stage();
                    Scene scene = new Scene(root);
                    scene.setFill(Color.TRANSPARENT);
                    stage.initStyle(StageStyle.TRANSPARENT);
                    stage.setScene(scene);

                    GaussianBlur blur = new GaussianBlur(55);
                    ColorAdjust adj = new ColorAdjust(-0.1, -0.1, -0.1, -0.1);
                    adj.setInput(blur);
                    anchorPane.setEffect(adj);

                    stage.showAndWait();
                    anchorPane.setEffect(null);

                } catch (Exception e) {
                    e.printStackTrace();
                    e.getCause();
                }
            }

        } else
            this.signIn();
    }

    @FXML
    public void signIn() {
        try {

            Parent root = FXMLLoader.load(Main.class.getResource("/gamesmarket/login.fxml"));
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
