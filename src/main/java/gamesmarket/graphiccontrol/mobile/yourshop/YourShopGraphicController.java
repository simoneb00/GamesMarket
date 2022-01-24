package gamesmarket.graphiccontrol.mobile.yourshop;

import gamesmarket.control.YourShopController;
import gamesmarket.exceptions.ErrorMessage;
import gamesmarket.graphiccontrol.mobile.ShopOwnerNavigationButtons;
import gamesmarket.main.Main;
import gamesmarket.model.Shop;
import gamesmarket.model.ShopOwner;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

import java.io.*;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class YourShopGraphicController extends ShopOwnerNavigationButtons implements Initializable {

    @FXML
    private Label addressLabel;
    @FXML
    private ImageView img;
    @FXML
    private Label shopLabel;

    private YourShopController yourShopController = new YourShopController();
    private Parent root;
    private Scene scene;
    private Stage stage;

    @FXML
    void goToGoods(ActionEvent event) {

        try {
            root = FXMLLoader.load(Main.class.getResource("/gamesmarket/mobile/your_goods.fxml"));
            this.show(root, event);

        } catch (Exception e) {
            ErrorMessage.displayErrorMobile();
        }

    }

    @FXML
    void goToYourOrders(ActionEvent event) {

        try {
            root = FXMLLoader.load(Main.class.getResource("/gamesmarket/mobile/your_orders.fxml"));
            this.show(root, event);

        } catch (Exception e) {
            ErrorMessage.displayErrorMobile();
        }

    }


    private void retrieveImage() {
        try {
            File path = ShopOwner.getInstance().getShop().getImageFile();
            if (path != null) {
                File file = new File(path.getAbsolutePath());
                InputStream isImage = new FileInputStream(file);
                img.setImage(new Image(isImage));
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    @FXML
    public void updatePhoto() {
        try {
            yourShopController.updatePhoto();
            this.initialize(null, null);
        } catch (RuntimeException e) {
            ErrorMessage.displayImageNotFoundMessage();
        } catch (SQLException | IOException e) {
            ErrorMessage.displayErrorMessage();
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        String shopname = ShopOwner.getInstance().getShop().getName();
        shopLabel.setText(shopname);
        addressLabel.setText(Shop.getInstance().getAddress() + ", " + Shop.getInstance().getCity() + ", " + Shop.getInstance().getCountry());
        this.retrieveImage();
    }
}
