package GamesMarket;

import javafx.fxml.FXML;
import javafx.stage.FileChooser;

import javax.swing.text.html.ImageView;
import java.awt.*;
import java.io.File;
import java.io.IOException;

public class ProfileController {

    public void saveContactInformation(){
        return;
    }

    public void saveBio(){
        return;
    }

    public void updateProfilePhoto(ImageView profileImage) throws IOException {

        FileChooser fileChooser = new FileChooser();
        File file = fileChooser.showOpenDialog(null);

        if (file != null) {
            Image image = new Image(file.toURI().toString());
            profileImage.setImage(image);

        }
    }

    public void addGameToWishlist(){
        return;
    }

    public void addGameToTradelist(){
        return;
    }
}
