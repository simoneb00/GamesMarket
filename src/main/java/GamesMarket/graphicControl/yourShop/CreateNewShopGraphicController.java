package GamesMarket.graphicControl.yourShop;

import GamesMarket.bean.ShopBean;
import GamesMarket.control.YourShopController;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;


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
        if (nameTF.getText().isEmpty())
            label.setText("Invalid name. Try again.");
        else {
            ShopBean shopBean = new ShopBean();
            shopBean.setName(nameTF.getText());
            shopBean.setAddress(addressTF.getText());
            shopBean.setCity(cityTF.getText());
            shopBean.setCountry(countryTF.getText());
            YourShopController yourShopController = new YourShopController();
            yourShopController.createNewShop(shopBean);
            Stage stage = (Stage) ((Button) event.getSource()).getScene().getWindow();
            stage.close();
        }
    }
}
