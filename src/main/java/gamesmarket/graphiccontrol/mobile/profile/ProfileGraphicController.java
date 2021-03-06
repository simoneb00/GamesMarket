package gamesmarket.graphiccontrol.mobile.profile;

import gamesmarket.bean.UserBean;
import gamesmarket.control.profile.UserProfileController;
import gamesmarket.exceptions.ErrorMessage;
import gamesmarket.graphiccontrol.mobile.NavigationButtons;
import gamesmarket.main.Main;
import gamesmarket.model.User;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;


import java.io.*;
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

    @FXML
    void updateBio() {
        try {
            UserBean userBean = new UserBean();
            userBean.setUserBio(bioTF.getText());
            userProfileController.saveBio(userBean);
        } catch (SQLException e) {
            ErrorMessage.displayErrorMessage();
        }
    }

    @FXML
    void updateCI() {
        try {
            UserBean userBean = new UserBean();
            userBean.setUserEmail(emailTF.getText());
            userBean.setUserTel(telTF.getText());
            userBean.setUserAddress(addressTF.getText());
            userBean.setUserCountry(countryTF.getText());

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
        } catch (SQLException | IOException e) {
            ErrorMessage.displayErrorMessage();
        }
    }

    @FXML
    void updateTradelist(ActionEvent event) {
        try {
            root = FXMLLoader.load(Main.class.getResource("/gamesmarket/mobile/tradelist.fxml"));
            this.show(root, event);

        } catch (Exception e) {
            ErrorMessage.displayErrorMobile();
        }
    }

    @FXML
    void updateWishlist(ActionEvent event) {
        try {
            root = FXMLLoader.load(Main.class.getResource("/gamesmarket/mobile/wishlist.fxml"));
            this.show(root, event);

        } catch (Exception e) {
            ErrorMessage.displayErrorMobile();
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
            ErrorMessage.displayErrorMobile();
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
        List<String> strings = User.getInstance().getWishlist();

        if (strings != null) {
            for (int i = 0; i < strings.size(); i++) {
                wishlist.getItems().add(strings.get(i));
            }
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        this.usernameLabel.setText(User.getInstance().getUsername());

        this.setCI();              // retrieve and set contact information
        this.setBio();             // retrieve and set bio
        this.setProfilePhoto();    // retrieve and set profile photo
        this.retrieveTradelist();
        this.retrieveWishlist();
    }
}
