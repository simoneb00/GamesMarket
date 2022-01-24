package gamesmarket.graphic_control.mobile.shop;

import gamesmarket.graphic_control.mobile.NavigationButtons;
import gamesmarket.main.Main;
import gamesmarket.model.ShopPost;
import gamesmarket.model.User;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

import java.io.IOException;

public class ItemGraphicController extends NavigationButtons {

    @FXML
    private Label game;
    @FXML
    private ImageView gameImg;
    @FXML
    private Label price;
    @FXML
    private Label shop;

    private String name;
    private double prc;
    private String imgPath;
    private String vendor;

    public void setData(ShopPost shopPost) {

        this.name = shopPost.getGame();
        this.prc = shopPost.getPrice();
        this.imgPath = shopPost.getImageFile().getAbsolutePath();
        this.vendor = shopPost.getShopName();

        game.setText(name);
        price.setText("€ " + prc);
        shop.setText(vendor);
        Image image = new Image(imgPath);
        gameImg.setImage(image);
    }

    @FXML
    public void buy(ActionEvent event) {
        if (User.getInstance().isLoggedIn()) {

            try {
                FXMLLoader fxmlLoader = new FXMLLoader();
                fxmlLoader.setLocation(Main.class.getResource("/gamesmarket/mobile/checkout_payment.fxml"));

                String[] s = name.split(" - ");
                String gameName = s[0];
                String platform = s[1];

                gamesmarket.graphic_control.mobile.shop.CheckoutGraphicController checkoutGraphicController = new CheckoutGraphicController(gameName, platform, imgPath, prc, vendor);
                fxmlLoader.setController(checkoutGraphicController);

                Parent root = fxmlLoader.load();
                Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
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

        } else
            this.loginButton(event);
    }
}