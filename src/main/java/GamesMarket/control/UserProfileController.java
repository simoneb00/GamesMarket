package GamesMarket.control;

import GamesMarket.model.User;
import javafx.stage.FileChooser;

import java.io.*;
import java.nio.file.*;

public class UserProfileController {

    public void saveContactInformation(){
        return;
    }

    public void saveBio(){
        return;
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
