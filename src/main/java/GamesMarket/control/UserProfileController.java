package GamesMarket.control;

import GamesMarket.bean.UserBean;
import GamesMarket.model.DAO.UserDAO;
import javafx.stage.FileChooser;

import java.awt.*;
import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class UserProfileController {

    UserDAO userDAO = new UserDAO();

    public void saveContactInformation(){
        return;
    }

    public void saveBio(UserBean userBean){
        String bio = userBean.getBio();
        userDAO.saveBio(bio);
    }

    public List<String> retrieveContactInf() {
        List<String> ci = new ArrayList<>();
        ci = userDAO.retrieveContactInf(); // retrieve user's contact information in this order: [email, tel, address, country]

        return ci;

    }

    public String retrieveBio() {
        String bio = userDAO.retrieveBio();
        return bio;
    }


    public void updateCI(UserBean userBean) {
        String email = userBean.getEmail();
        String tel = userBean.getTel();
        String address = userBean.getAddress();
        String country = userBean.getCountry();

        UserDAO userDAO = new UserDAO();
        userDAO.updateCI(email, tel, address, country);
    }

    public void updateProfilePhoto()  {


        FileChooser fileChooser = new FileChooser();
        File selectedFile = fileChooser.showOpenDialog(null);
        String path = selectedFile.getAbsolutePath();

        userDAO.updateProfilePhoto(path);

    }

    public File retrieveProfilePhoto(){
        File file = userDAO.retrieveProfilePhoto();
        return file;
    }


    public void addGameToWishlist(){
        return;
    }

    public void addGameToTradelist(){
        return;
    }
}
