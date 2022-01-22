package gamesmarket.graphic_control.exchange;

import gamesmarket.bean.UserBean;
import gamesmarket.control.exchange.ContactWindowController;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.stage.Stage;
import gamesmarket.exceptions.ErrorMessage;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class ContactWindowGraphicController implements Initializable {

    @FXML
    private TextArea address;
    @FXML
    private TextArea country;
    @FXML
    private TextArea email;
    @FXML
    private TextArea tel;
    @FXML
    private Label contactUsername;

    private String username;

    public ContactWindowGraphicController(String user) {
        this.username = user;
    }

    @FXML
    public void close(ActionEvent event) {
        Stage stage = (Stage) ((Button)event.getSource()).getScene().getWindow();
        stage.close();
    }


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        try {
            ContactWindowController contactWindowController = new ContactWindowController();
            UserBean userBean = new UserBean();
            userBean.setUsername(username);
            UserBean userBean1 = contactWindowController.retrieveCI(userBean);

            contactUsername.setText(username);
            email.setText(userBean1.getUserEmail());
            tel.setText(userBean1.getUserTel());
            address.setText(userBean1.getUserAddress());
            country.setText(userBean1.getUserCountry());
        } catch (SQLException e) {
            ErrorMessage.displayErrorMessage();
        }

    }
}
