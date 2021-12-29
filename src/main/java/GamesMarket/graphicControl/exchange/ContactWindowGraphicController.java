package GamesMarket.graphicControl.exchange;

import GamesMarket.bean.UserBean;
import GamesMarket.control.exchange.ContactWindowController;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.stage.Stage;

import java.net.URL;
import java.util.List;
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

    public void close(ActionEvent event) {
        Stage stage = (Stage) ((Button)event.getSource()).getScene().getWindow();
        stage.close();
    }


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        contactUsername.setText(username);

        ContactWindowController contactWindowController = new ContactWindowController();
        UserBean userBean = new UserBean();
        userBean.setUsername(username);
        List<String> ci = contactWindowController.retrieveCI(userBean); // retrieve contact information in this order: [email, tel, address, country]

        email.setText(ci.get(0));
        tel.setText(ci.get(1));
        address.setText(ci.get(2));
        country.setText(ci.get(3));

    }
}
