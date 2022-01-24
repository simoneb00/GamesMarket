package gamesmarket.graphiccontrol.shop;

import gamesmarket.main.Main;
import gamesmarket.model.ShopPost;
import gamesmarket.model.User;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.effect.ColorAdjust;
import javafx.scene.effect.GaussianBlur;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;

public class ShopPostGraphicController {

    @FXML
    private Label gameLabel;
    @FXML
    private ImageView img;
    @FXML
    private Label priceLabel;
    @FXML
    private Label shopNameLabel;

    private String name;
    private double price;
    private String imgPath;
    private String vendor;


    public void setData(ShopPost shopPost) {
        this.name = shopPost.getGame();
        this.price = shopPost.getPrice();
        this.imgPath = shopPost.getImageFile().getAbsolutePath();
        this.vendor = shopPost.getShopName();

        gameLabel.setText(name);
        priceLabel.setText("€ " + price);
        shopNameLabel.setText(vendor);
        Image image = new Image(imgPath);
        img.setImage(image);
    }

    @FXML
    public void buy(ActionEvent event) {
        String platform;

        if (User.getInstance().isLoggedIn()) {
            try {
                FXMLLoader fxmlLoader = new FXMLLoader();
                fxmlLoader.setLocation(Main.class.getResource("/gamesmarket/checkout_payment.fxml"));

                String[] s = name.split(" - ");
                String gameName = s[0];
                platform = s[1];

                CheckoutGraphicController checkoutGraphicController = new CheckoutGraphicController(gameName, platform, price, imgPath, vendor);
                fxmlLoader.setController(checkoutGraphicController);

                Parent root = fxmlLoader.load();
                Stage stage = new Stage();
                Scene scene = new Scene(root);

                stage.getIcons().add(new Image("file:///C:/Users/Simone%20Bauco/IdeaProjects/GamesMarket/src/main/resources/images/GamesMarketLogo.png"));
                stage.setTitle("Checkout");

                scene.getStylesheets().clear();
                scene.getStylesheets().add("file:///C:/Users/Simone%20Bauco/IdeaProjects/GamesMarket/src/main/java/GamesMarket/css/style.css");

                stage.setScene(scene);
                stage.show();

            } catch(IOException e) {
                e.printStackTrace();
            }
        } else {
            HBox hBox = (HBox) ((Button)event.getSource()).getParent();
            VBox vBox = (VBox) hBox.getParent();
            AnchorPane pane = (AnchorPane) vBox.getParent();
            GridPane grid = (GridPane) pane.getParent();
            AnchorPane pane1 = (AnchorPane) grid.getParent();
            Parent scrollPane = pane1.getParent();
            Parent hBox1 = scrollPane.getParent();
            Parent vBox1 = hBox1.getParent();
            Parent borderPane = vBox1.getParent();
            Parent anchorPane1 = borderPane.getParent();
            Parent shopAnchorPane = anchorPane1.getParent();

            try {

                Parent root = FXMLLoader.load(Main.class.getResource("/gamesmarket/login.fxml"));
                Scene loginScene = new Scene(root);
                Stage loginStage = new Stage();
                loginStage.initStyle(StageStyle.TRANSPARENT);
                loginScene.setFill(Color.TRANSPARENT);
                loginStage.setScene(loginScene);

                GaussianBlur gaussianBlur = new GaussianBlur(45);
                ColorAdjust adj = new ColorAdjust(-0.1, -0.1, -0.1, -0.1);
                adj.setInput(gaussianBlur);
                shopAnchorPane.setEffect(adj);

                loginStage.showAndWait();
                shopAnchorPane.setEffect(null);


            } catch (Exception e) {
                e.printStackTrace();
                e.getCause();
            }
        }
    }
}
