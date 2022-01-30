package gamesmarket.control.profile;

import gamesmarket.bean.GameBean;
import gamesmarket.bean.UserBean;
import gamesmarket.model.dao.UserDAO;
import gamesmarket.model.User;
import javafx.stage.FileChooser;
import java.io.*;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UserProfileController {


    public void saveBio(UserBean userBean) throws SQLException {
        String bio = userBean.getUserBio();
        User.getInstance().setBio(bio);
        UserDAO.saveBio(bio);
    }


    public void updateCI(UserBean userBean) throws SQLException {
        String email = userBean.getUserEmail();
        String tel = userBean.getUserTel();
        String address = userBean.getUserAddress();
        String country = userBean.getUserCountry();

        List<String> contacts = new ArrayList<>();
        contacts.add(email);
        contacts.add(tel);
        contacts.add(address);
        contacts.add(country);
        User.getInstance().setContacts(contacts);   // uploading user instance's contact information

        UserDAO.updateCI(email, tel, address, country);
    }

    public void updateProfilePhoto() throws SQLException, IOException {
        FileChooser fileChooser = new FileChooser();
        File selectedFile = fileChooser.showOpenDialog(null);   // opens a file chooser to make the user choose a new profile photo
        String path = selectedFile.getAbsolutePath();

        UserDAO.updateProfilePhoto(path);
        User.getInstance().setProfileImagePath(path);

    }


    public void removeFromWishlist(GameBean gameBean) throws SQLException {
        String name = gameBean.getGameName();
        String platform = gameBean.getGamePlatform();
        UserDAO.removeFromWishlist(name, platform);
        for (int i = 0; i < User.getInstance().getWishlist().size(); i++) {
            if (User.getInstance().getWishlist().get(i).equals(name + " - " + platform))
                User.getInstance().getWishlist().remove(i);         // remove the game from the user instance's wishlist
        }
    }

    public void removeFromTradelist(GameBean gameBean) throws SQLException {
        String name = gameBean.getGameName();
        String platform = gameBean.getGamePlatform();
        UserDAO.removeFromTradelist(name, platform);
        for (int i = 0; i < User.getInstance().getTradelist().size(); i++) {
            if (User.getInstance().getTradelist().get(i).equals(name + " - " + platform))
                User.getInstance().getTradelist().remove(i);    // remove the game from the user instance's tradelist
        }
    }
}
