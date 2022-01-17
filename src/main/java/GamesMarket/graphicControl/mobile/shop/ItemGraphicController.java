package GamesMarket.graphicControl.mobile.shop;

import GamesMarket.exceptions.ErrorMessage;
import GamesMarket.graphicControl.mobile.NavigationButtons;
import GamesMarket.main.Main;
import GamesMarket.model.ShopPost;
import GamesMarket.model.User;
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
    private String platform;
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

    public void buy(ActionEvent event) {
        if (User.getInstance().isLoggedIn()) {

            try {
                FXMLLoader fxmlLoader = new FXMLLoader();
                fxmlLoader.setLocation(Main.class.getResource("/GamesMarket/mobile/checkout_payment.fxml"));

                String[] s = name.split(" - ");
                String gameName = s[0];
                platform = s[1];

                GamesMarket.graphicControl.mobile.shop.CheckoutGraphicController checkoutGraphicController = new CheckoutGraphicController(gameName, platform, imgPath, prc, vendor);
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
