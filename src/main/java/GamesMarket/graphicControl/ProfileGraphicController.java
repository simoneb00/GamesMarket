package GamesMarket.graphicControl;

import GamesMarket.bean.UserBean;
import GamesMarket.control.UserProfileController;
import GamesMarket.main.Main;
import GamesMarket.model.User;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

import java.io.File;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class ProfileGraphicController implements Initializable {

    @FXML
    private TextField address;
    @FXML
    private TextField country;
    @FXML
    private TextField email;
    @FXML
    private TextField tel;
    @FXML
    private Label usernameLabel;
    @FXML
    private TextField bioTF;
    @FXML
    private ImageView profilePhoto;

    private UserProfileController userProfileController = new UserProfileController();
    private Parent root;
    private Scene scene;
    private Stage stage;


    public void updatePhoto() {
        userProfileController.updateProfilePhoto();
        this.initialize(null, null);
    }

    public void updateCI() {
        UserBean userBean = new UserBean();
        userBean.setEmail(email.getText());
        userBean.setTel(tel.getText());
        userBean.setAddress(address.getText());
        userBean.setCountry(country.getText());

        userProfileController.updateCI(userBean);
        this.setCI();
    }


    public void homeButton(ActionEvent event) {

        try {
            root = FXMLLoader.load(Main.class.getResource("/GamesMarket/home.fxml"));
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

    public void shopButton(ActionEvent event) {
        try {
            root = FXMLLoader.load(Main.class.getResource("/GamesMarket/shop.fxml"));
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

    public void forumButton(ActionEvent event) {
        try {
            root = FXMLLoader.load(Main.class.getResource("/GamesMarket/forum.fxml"));
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

    public void exchangeButton(ActionEvent event) {
        try {
            root = FXMLLoader.load(Main.class.getResource("/GamesMarket/exchange.fxml"));
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

    private void setCI() {
        List<String> contactInformation = userProfileController.retrieveContactInf(); // retrieve user's contact information in this order: [email, tel, address, country]

        if (!contactInformation.isEmpty()) {
            email.setText(contactInformation.get(0));
            tel.setText(contactInformation.get(1));
            address.setText(contactInformation.get(2));
            country.setText(contactInformation.get(3));
        }
    }

    private void setBio() {
        String bio = userProfileController.retrieveBio();
        if(bio != null) {
            bioTF.setText(bio);
        }
    }

    private void setProfilePhoto() {
        File file = userProfileController.retrieveProfilePhoto();
        if (file != null){
            Image image = new Image(file.getAbsolutePath());
            profilePhoto.setImage(image);
        }

    }

    public void saveBio() {
        UserBean userBean = new UserBean();
        userBean.setBio(bioTF.getText());
        userProfileController.saveBio(userBean);
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        this.usernameLabel.setText(User.getInstance().getUsername());

        setCI();
        setBio();
        setProfilePhoto();
    }
}
