package gamesmarket.graphiccontrol.mobile.exchange;

import gamesmarket.bean.UserBean;
import gamesmarket.control.exchange.ContactWindowController;
import gamesmarket.exceptions.ErrorMessage;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.stage.Stage;

import java.awt.*;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.StringSelection;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class ContactWindowGraphicController implements Initializable {

    @FXML
    private ListView<String> list;
    @FXML
    private Button clipboardButton;

    private String username;
    private String selectedString;

    public ContactWindowGraphicController(String username) {
        this.username = username;
    }

    @FXML
    public void close(ActionEvent event) {
        Stage stage = (Stage) ((Button)event.getSource()).getScene().getWindow();
        stage.close();
    }

    @FXML
    public void copyToClipboard() {
        StringSelection stringSelection = new StringSelection(selectedString);
        Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();       // gets the clipboard
        clipboard.setContents(stringSelection, null);                           // copies selected text to clipboard
        clipboardButton.setVisible(false);
        clipboardButton.setDisable(true);
    }

    @FXML
    public void showClipboardButton() {
        selectedString = list.getSelectionModel().getSelectedItem();
        clipboardButton.setVisible(true);
        clipboardButton.setDisable(false);
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        try {

            ContactWindowController contactWindowController = new ContactWindowController();
            UserBean userBean = new UserBean();
            userBean.setUsername(username);
            UserBean userBean1 = contactWindowController.retrieveCI(userBean);      // retrieves contact information to show

            String email = userBean1.getUserEmail();
            String tel = userBean1.getUserTel();
            String address = userBean1.getUserAddress();
            String country = userBean1.getUserCountry();

            list.getItems().add(email);
            list.getItems().add(tel);
            list.getItems().add(address + ", " + country);

        } catch (SQLException e) {
            ErrorMessage.displayErrorMessage();
        }
    }
}
