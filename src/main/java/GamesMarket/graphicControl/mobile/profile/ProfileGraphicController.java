package GamesMarket.graphicControl.mobile.profile;

import GamesMarket.bean.UserBean;
import GamesMarket.control.profile.UserProfileController;
import GamesMarket.exceptions.ErrorMessage;
import GamesMarket.graphicControl.mobile.NavigationButtons;
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
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.net.URL;
import java.sql.SQLException;
import java.util.List;
import java.util.ResourceBundle;

public class ProfileGraphicController extends NavigationButtons implements Initializable {

    @FXML
    private TextField addressTF;
    @FXML
    private TextArea bioTF;
    @FXML
    private TextField countryTF;
    @FXML
    private TextField emailTF;
    @FXML
    private ImageView profilePhoto;
    @FXML
    private TextField telTF;
    @FXML
    private ListView<String> tradelist;
    @FXML
    private Label usernameLabel;
    @FXML
    private ListView<String> wishlist;

    private UserProfileController userProfileController = new UserProfileController();
    private Parent root;
    private Stage stage;
    private Scene scene;

    @FXML
    void updateBio() {
        try {
            UserBean userBean = new UserBean();
            userBean.setBio(bioTF.getText());
            userProfileController.saveBio(userBean);
        } catch (SQLException e) {
            ErrorMessage.displayErrorMessage();
        }
    }

    @FXML
    void updateCI() {
        try {
            UserBean userBean = new UserBean();
            userBean.setEmail(emailTF.getText());
            userBean.setTel(telTF.getText());
            userBean.setAddress(addressTF.getText());
            userBean.setCountry(countryTF.getText());

            userProfileController.updateCI(userBean);
            this.setCI();
        } catch (SQLException e) {
            ErrorMessage.displayErrorMessage();
        }
    }

    @FXML
    void updatePhoto() {
        try {
            userProfileController.updateProfilePhoto();
            tradelist.getItems().clear();
            wishlist.getItems().clear();
            this.initialize(null, null);
        } catch (RuntimeException e) {
            ErrorMessage.displayImageNotFoundMessage();
        } catch (SQLException | FileNotFoundException e) {
            ErrorMessage.displayErrorMessage();
        }
    }

    @FXML
    void updateTradelist(ActionEvent event) {
        try {
            root = FXMLLoader.load(Main.class.getResource("/GamesMarket/mobile/tradelist.fxml"));
            stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
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

    @FXML
    void updateWishlist(ActionEvent event) {
        try {
            root = FXMLLoader.load(Main.class.getResource("/GamesMarket/mobile/wishlist.fxml"));
            stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
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
        List<String> contactInformation = User.getInstance().getContacts();
        if (contactInformation != null && !contactInformation.isEmpty()) {
            emailTF.setText(contactInformation.get(0));
            telTF.setText(contactInformation.get(1));
            addressTF.setText(contactInformation.get(2));
            countryTF.setText(contactInformation.get(3));
        }
    }

    private void setBio() {
        String bio = User.getInstance().getBio();
        if(bio != null) {
            bioTF.setText(bio);
        }
    }

    private void setProfilePhoto() {

        try {
            String path = User.getInstance().getProfileImagePath();
            if (path != null) {
                File file = new File(path);
                InputStream isImage = new FileInputStream(file);
                profilePhoto.setImage(new Image(isImage));
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

    }

    public void retrieveTradelist(){
        List<String> tl = User.getInstance().getTradelist();

        if (tl != null) {
            for (int i = 0; i < tl.size(); i++) {
                tradelist.getItems().add(tl.get(i));
            }
        }
    }

    public void retrieveWishlist() {
        List<String> wl = User.getInstance().getWishlist();

        if (wl != null) {
            for (int i = 0; i < wl.size(); i++) {
                wishlist.getItems().add(wl.get(i));
            }
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        this.usernameLabel.setText(User.getInstance().getUsername());

        setCI();
        setBio();
        setProfilePhoto();
        retrieveTradelist();
        retrieveWishlist();
    }
}
