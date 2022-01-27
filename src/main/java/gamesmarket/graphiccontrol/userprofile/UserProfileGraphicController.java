package gamesmarket.graphiccontrol.userprofile;

import gamesmarket.bean.GameBean;
import gamesmarket.bean.UserBean;
import gamesmarket.control.profile.UserProfileController;
import gamesmarket.exceptions.ErrorMessage;
import gamesmarket.graphiccontrol.navigation.NavigationButtons;
import gamesmarket.main.Main;
import gamesmarket.model.User;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.effect.ColorAdjust;
import javafx.scene.effect.GaussianBlur;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.*;
import java.net.URL;
import java.sql.SQLException;
import java.util.List;
import java.util.Objects;
import java.util.ResourceBundle;

public class UserProfileGraphicController extends NavigationButtons implements Initializable  {

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
    @FXML
    private ListView<String> wishlist;
    @FXML
    private ListView<String> tradelist;
    @FXML
    private AnchorPane pane;
    @FXML
    private Button removeSelectedTL;
    @FXML
    private Button removeSelectedWL;
    @FXML
    private Label photoLabel;

    private UserProfileController userProfileController = new UserProfileController();
    private String selectedGame;
    private String selectedPlatform;


    @FXML
    public void updatePhoto() {
        try {
            userProfileController.updateProfilePhoto();
            tradelist.getItems().clear();
            wishlist.getItems().clear();
            this.initialize(null, null);
            photoLabel.setText("");
        } catch (RuntimeException e) {
            photoLabel.setText("No photo selected.");
        } catch (SQLException | IOException e) {
            ErrorMessage.displayErrorMessage();
        }
    }

    @FXML
    public void updateCI() {
        try {
            UserBean userBean = new UserBean();
            userBean.setUserEmail(email.getText());
            userBean.setUserTel(tel.getText());
            userBean.setUserAddress(address.getText());
            userBean.setUserCountry(country.getText());

            userProfileController.updateCI(userBean);
            this.setCI();
        } catch (SQLException e) {
            ErrorMessage.displayErrorMessage();
        }
    }


    private void setCI() {
        List<String> contactInformation = User.getInstance().getContacts();
        if (contactInformation != null && !contactInformation.isEmpty()) {
            email.setText(contactInformation.get(0));
            tel.setText(contactInformation.get(1));
            address.setText(contactInformation.get(2));
            country.setText(contactInformation.get(3));
        }
    }

    private void setBio() {
        String bio = User.getInstance().getBio();
        if(bio != null) {
            bioTF.setText(bio);
        }
    }

    @FXML
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

    @FXML
    public void saveBio() {
        try {
            UserBean userBean = new UserBean();
            userBean.setUserBio(bioTF.getText());
            userProfileController.saveBio(userBean);
        } catch (SQLException e) {
            ErrorMessage.displayErrorMessage();
        }
    }

    private void showList(Parent root) {
        Stage stage = new Stage();
        Scene scene = new Scene(root);
        scene.setFill(Color.TRANSPARENT);
        stage.initStyle(StageStyle.TRANSPARENT);
        stage.setScene(scene);

        GaussianBlur blur = new GaussianBlur(55);
        ColorAdjust adj = new ColorAdjust(-0.1, -0.1, -0.1, -0.1);
        adj.setInput(blur);
        pane.setEffect(adj);

        stage.showAndWait();
        refresh();
        pane.setEffect(null);
    }

    @FXML
    public void addToTradelist() {
        try {

            Parent root = FXMLLoader.load(Main.class.getResource("/gamesmarket/gamesTable.fxml"));
            this.showList(root);

        } catch (Exception e) {
            ErrorMessage.displayErrorMessage();
        }
    }

    @FXML
    public void addToWishlist(){
        try {

            Parent root = FXMLLoader.load(Main.class.getResource("/gamesmarket/gamesTable.fxml"));
            this.showList(root);

        } catch (Exception e) {
            ErrorMessage.displayErrorMessage();
        }
    }


    private void retrieveTradelist(){
        List<String> tl = User.getInstance().getTradelist();

        if (tl != null) {
            for (int i = 0; i < tl.size(); i++) {
                tradelist.getItems().add(tl.get(i));
            }
        }
    }

    private void retrieveWishlist() {
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

    private void refresh() {
        wishlist.getItems().clear();
        this.retrieveWishlist();

        tradelist.getItems().clear();
        this.retrieveTradelist();
    }

    @FXML
    public void wishlistMouseClicked() {
        String wishlistSelected;

        wishlistSelected = wishlist.getSelectionModel().getSelectedItems().toString();
        if (!Objects.equals(wishlistSelected, "[]")) {
            wishlistSelected = wishlistSelected.replaceAll("[\\[\\]]", "");
            String[] strings = wishlistSelected.split(" - ");
            selectedGame = strings[0];
            selectedPlatform = strings[1];
            removeSelectedWL.setVisible(true);
            removeSelectedWL.setDisable(false);
        }
    }

    @FXML
    public void tradelistMouseClicked() {
        String tradelistSelected;

        tradelistSelected = tradelist.getSelectionModel().getSelectedItems().toString();
        if (!Objects.equals(tradelistSelected, "[]")) {
            tradelistSelected = tradelistSelected.replaceAll("[\\[\\]]", "");
            String[] strings = tradelistSelected.split(" - ");
            selectedGame = strings[0];
            selectedPlatform = strings[1];
            removeSelectedTL.setVisible(true);
            removeSelectedTL.setDisable(false);
        }
    }

    public void removeFromWL() {
        try {
            GameBean gameBean = new GameBean();
            gameBean.setGamePlatform(selectedPlatform);
            gameBean.setGameName(selectedGame);

            userProfileController.removeFromWishlist(gameBean);

            removeSelectedWL.setVisible(false);
            removeSelectedWL.setDisable(true);

            refresh();
        } catch (SQLException e) {
            ErrorMessage.displayErrorMessage();
        }
    }

    public void removeFromTL() {
        try {
            GameBean gameBean = new GameBean();
            gameBean.setGamePlatform(selectedPlatform);
            gameBean.setGameName(selectedGame);

            userProfileController.removeFromTradelist(gameBean);

            removeSelectedTL.setDisable(true);
            removeSelectedTL.setVisible(false);

            refresh();
        } catch (SQLException e) {
            ErrorMessage.displayErrorMessage();
        }
    }
}
