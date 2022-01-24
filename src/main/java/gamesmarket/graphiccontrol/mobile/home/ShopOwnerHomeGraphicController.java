package gamesmarket.graphiccontrol.mobile.home;

import gamesmarket.exceptions.ErrorMessage;
import gamesmarket.graphiccontrol.mobile.ShopOwnerNavigationButtons;
import gamesmarket.main.Main;
import gamesmarket.model.ShopOwner;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;

public class ShopOwnerHomeGraphicController extends ShopOwnerNavigationButtons {

    @FXML
    public void yourGoodsButton(ActionEvent event) {
        if (ShopOwner.getInstance().getShop() == null)
            this.createNewShop(event);
        else {
            try {
                root = FXMLLoader.load(Main.class.getResource("/gamesmarket/mobile/your_goods.fxml"));
                this.show(root, event);

            } catch (Exception e) {
                ErrorMessage.displayErrorMobile();
            }
        }
    }

    @FXML
    public void yourOrdersButton(ActionEvent event) {
        if (ShopOwner.getInstance().getShop() == null)
            this.createNewShop(event);
        else {
            try {
                root = FXMLLoader.load(Main.class.getResource("/gamesmarket/mobile/your_orders.fxml"));
                this.show(root, event);

            } catch (Exception e) {
                ErrorMessage.displayErrorMobile();
            }
        }
    }
}
