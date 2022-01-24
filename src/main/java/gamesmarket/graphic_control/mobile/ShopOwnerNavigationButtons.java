package gamesmarket.graphic_control.mobile;

import gamesmarket.exceptions.ErrorMessage;
import gamesmarket.main.Main;
import gamesmarket.model.ShopOwner;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class ShopOwnerNavigationButtons extends NavigationButtons{

    protected void show(Parent root, ActionEvent event) {
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);

        scene.getStylesheets().clear();
        scene.getStylesheets().add(css);

        stage.setScene(scene);
        stage.show();
    }

    @FXML
    public void shopOwnerForumButton(ActionEvent event) {
        try {
            root = FXMLLoader.load(Main.class.getResource("/gamesmarket/mobile/shop_owner_forum.fxml"));
            this.show(root, event);

        } catch (Exception e) {
            ErrorMessage.displayErrorMobile();
        }
    }

    @FXML
    public void yourShopButton(ActionEvent event) {
        if (ShopOwner.getInstance().getShop() == null)
            this.createNewShop(event);
        else {
            try {
                root = FXMLLoader.load(Main.class.getResource("/gamesmarket/mobile/your_shop.fxml"));
                this.show(root, event);

            } catch (Exception e) {
                ErrorMessage.displayErrorMobile();
            }
        }
    }

    @FXML
    public void shopOwnerHomeButton(ActionEvent event) {
        try {
            root = FXMLLoader.load(Main.class.getResource("/gamesmarket/mobile/shop_owner_homepage.fxml"));
            this.show(root, event);


        } catch (Exception e) {
            ErrorMessage.displayErrorMobile();
        }
    }

    @FXML
    public void createNewShop(ActionEvent event) {
        try {
            root = FXMLLoader.load(Main.class.getResource("/gamesmarket/mobile/create_new_shop.fxml"));
            this.show(root, event);

        } catch (Exception e) {
            ErrorMessage.displayErrorMobile();
        }
    }

}
