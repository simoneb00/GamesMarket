package gamesmarket.graphiccontrol.mobile.shop;

import gamesmarket.exceptions.ErrorMessage;
import gamesmarket.graphiccontrol.mobile.NavigationButtons;
import gamesmarket.main.Main;
import gamesmarket.model.ShopPost;
import gamesmarket.model.User;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;


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

                gamesmarket.graphiccontrol.mobile.shop.CheckoutGraphicController checkoutGraphicController = new CheckoutGraphicController(gameName, platform, imgPath, prc, vendor);
                fxmlLoader.setController(checkoutGraphicController);

                Parent root = fxmlLoader.load();
                this.show(root, event);

            } catch(IOException e) {
                ErrorMessage.displayErrorMobile();
            }

        } else
            this.loginButton(event);
    }
}
