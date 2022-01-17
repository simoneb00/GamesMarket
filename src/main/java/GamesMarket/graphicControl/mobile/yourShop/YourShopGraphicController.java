package GamesMarket.graphicControl.mobile.yourShop;

import GamesMarket.control.YourShopController;
import GamesMarket.exceptions.ErrorMessage;
import GamesMarket.graphicControl.mobile.ShopOwnerNavigationButtons;
import GamesMarket.main.Main;
import GamesMarket.model.Shop;
import GamesMarket.model.ShopOwner;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
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
            root = FXMLLoader.load(Main.class.getResource("/GamesMarket/mobile/your_goods.fxml"));
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

    @FXML
    void goToYourOrders(ActionEvent event) {

        try {
            root = FXMLLoader.load(Main.class.getResource("/GamesMarket/mobile/your_orders.fxml"));
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


    private void retrieveImage() {
        try {
            File path = ShopOwner.getInstance().getShop().getImageFile();
            if (path != null) {
                File file = new File(path.getAbsolutePath());
                InputStream isImage = (InputStream) new FileInputStream(file);
                img.setImage(new Image(isImage));
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    public void updatePhoto() {
        try {
            yourShopController.updatePhoto();
            this.initialize(null, null);
        } catch (RuntimeException e) {
            ErrorMessage.displayImageNotFoundMessage();
        } catch (SQLException e) {
            ErrorMessage.displayErrorMessage();
        } catch (IOException e) {
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
