package GamesMarket.control;

import GamesMarket.bean.UserBean;
import GamesMarket.model.DAO.UserDAO;
import GamesMarket.model.User;
import javafx.stage.FileChooser;

import java.io.*;
import java.nio.file.*;
import java.util.ArrayList;
import java.util.List;

public class UserProfileController {

    public void saveContactInformation(){
        return;
    }

    public void saveBio(){
        return;
    }

    public List<String> retrieveContactInf() {
        List<String> ci = new ArrayList<>();
        UserDAO userDAO = new UserDAO();

        ci = userDAO.retrieveContactInf(); // retrieve user's contact information in this order: [email, tel, address, country]

        return ci;

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

        try {

            FileChooser fileChooser = new FileChooser();
            File selectedFile = fileChooser.showOpenDialog(null);
            String path = selectedFile.getAbsolutePath();

            Path source = Paths.get(path);
            Path target = Paths.get("C:\\Users\\Simone Bauco\\IdeaProjects\\GamesMarket\\src\\main\\resources\\profileImages\\" + selectedFile.getName());

            Files.copy(source, target, StandardCopyOption.REPLACE_EXISTING);

            User user = User.getInstance();
            user.updateProfileImage("C:\\Users\\Simone Bauco\\IdeaProjects\\GamesMarket\\src\\main\\resources\\profileImages\\" + selectedFile.getName());


        } catch(IOException e) {
            e.getCause();
            e.printStackTrace();
        }
    }

    public void addGameToWishlist(){
        return;
    }

    public void addGameToTradelist(){
        return;
    }
}
