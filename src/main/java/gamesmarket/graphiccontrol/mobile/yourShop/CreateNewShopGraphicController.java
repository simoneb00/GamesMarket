package gamesmarket.graphiccontrol.mobile.yourShop;

import gamesmarket.bean.ShopBean;
import gamesmarket.control.YourShopController;
import gamesmarket.exceptions.ErrorMessage;
import gamesmarket.graphiccontrol.mobile.ShopOwnerNavigationButtons;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;

import java.sql.SQLException;

public class CreateNewShopGraphicController extends ShopOwnerNavigationButtons {

    @FXML
    private TextField addressTF;

    @FXML
    private TextField cityTF;

    @FXML
    private TextField countryTF;

    @FXML
    private TextField nameTF;


    @FXML
    void back(ActionEvent event) {
        this.shopOwnerHomeButton(event);
    }

    @FXML
    void create(ActionEvent event) {
        try {
            if (nameTF.getText().isEmpty())
                ErrorMessage.displayInvalidNameMessage();
            if (addressTF.getText().isEmpty() || cityTF.getText().isEmpty() || countryTF.getText().isEmpty())
                ErrorMessage.missingInformationMessage();
            else {
                ShopBean shopBean = new ShopBean();
                shopBean.setShopName(nameTF.getText());
                shopBean.setShopAddress(addressTF.getText());
                shopBean.setCity(cityTF.getText());
                shopBean.setCountry(countryTF.getText());
                YourShopController yourShopController = new YourShopController();
                yourShopController.createNewShop(shopBean);

                this.yourShopButton(event);
            }
        } catch (SQLException e) {
            ErrorMessage.displayErrorMobile();
        }
    }


}
