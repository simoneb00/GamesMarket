package GamesMarket.control.profile;

import GamesMarket.bean.GameBean;
import GamesMarket.bean.UserBean;
import GamesMarket.model.DAO.UserDAO;
import GamesMarket.model.User;
import javafx.stage.FileChooser;
import java.io.*;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UserProfileController {


    public void saveBio(UserBean userBean) throws SQLException {
        String bio = userBean.getBio();
        User.getInstance().setBio(bio);
        UserDAO.saveBio(bio);
    }


    public void updateCI(UserBean userBean) throws SQLException {
        String email = userBean.getEmail();
        String tel = userBean.getTel();
        String address = userBean.getAddress();
        String country = userBean.getCountry();

        List<String> contacts = new ArrayList<>();
        contacts.add(email);
        contacts.add(tel);
        contacts.add(address);
        contacts.add(country);
        User.getInstance().setContacts(contacts);

        UserDAO.updateCI(email, tel, address, country);
    }

    public void updateProfilePhoto() throws SQLException, FileNotFoundException {
        FileChooser fileChooser = new FileChooser();
        File selectedFile = fileChooser.showOpenDialog(null);
        String path = selectedFile.getAbsolutePath();

        UserDAO.updateProfilePhoto(path);
        User.getInstance().setProfileImagePath(path);

    }


    public void removeFromWishlist(GameBean gameBean) throws SQLException {
        String name = gameBean.getName();
        String platform = gameBean.getPlatform();
        UserDAO.removeFromWishlist(name, platform);
        for (int i = 0; i < User.getInstance().getWishlist().size(); i++) {
            if (User.getInstance().getWishlist().get(i).equals(name + " - " + platform))
                User.getInstance().getWishlist().remove(i);
        }
    }

    public void removeFromTradelist(GameBean gameBean) throws SQLException {
        String name = gameBean.getName();
        String platform = gameBean.getPlatform();
        UserDAO.removeFromTradelist(name, platform);
        for (int i = 0; i < User.getInstance().getTradelist().size(); i++) {
            if (User.getInstance().getTradelist().get(i).equals(name + " - " + platform))
                User.getInstance().getTradelist().remove(i);
        }
    }
}
