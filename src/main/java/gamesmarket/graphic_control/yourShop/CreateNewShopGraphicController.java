package gamesmarket.graphic_control.yourShop;

import gamesmarket.bean.ShopBean;
import gamesmarket.control.YourShopController;
import gamesmarket.exceptions.ErrorMessage;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.sql.SQLException;


public class CreateNewShopGraphicController {

    @FXML
    private Label label;
    @FXML
    private TextField addressTF;
    @FXML
    private TextField cityTF;
    @FXML
    private TextField countryTF;
    @FXML
    private TextField nameTF;


    public void backButton(ActionEvent event) {
        Stage stage = (Stage) ((Button) event.getSource()).getScene().getWindow();
        stage.close();
    }

    public void createButtonPressed(ActionEvent event) {
        try {
            if (nameTF.getText().isEmpty())
                label.setText("Invalid name. Try again.");
            else {
                ShopBean shopBean = new ShopBean();
                shopBean.setShopName(nameTF.getText());
                shopBean.setShopAddress(addressTF.getText());
                shopBean.setCity(cityTF.getText());
                shopBean.setCountry(countryTF.getText());
                YourShopController yourShopController = new YourShopController();
                yourShopController.createNewShop(shopBean);
                Stage stage = (Stage) ((Button) event.getSource()).getScene().getWindow();
                stage.close();
            }
        } catch (SQLException e) {
            ErrorMessage.displayErrorMessage();
        }
    }
}
