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
import GamesMarket.exceptions.ErrorMessage;
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
            email.setText(userBean1.getEmail());
            tel.setText(userBean1.getTel());
            address.setText(userBean1.getAddress());
            country.setText(userBean1.getCountry());
        } catch (SQLException e) {
            ErrorMessage.displayErrorMessage();
        }

    }
}
