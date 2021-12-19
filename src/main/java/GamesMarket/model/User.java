package GamesMarket.model;

import GamesMarket.graphicControl.HomepageGraphicController;
import javafx.scene.image.ImageView;

import java.awt.*;

public class User {

    private String firstName;
    private String lastName;
    private String username;
    private String password;
    private String emailAddress;
    private String profileImagePath;
    private boolean isLoggedIn = false;

    private static User instance = null;

    private User() {
        return;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmailAddress() {
        return emailAddress;
    }

    public void setEmailAddress(String emailAddress) {
        this.emailAddress = emailAddress;
    }

    public void updateProfileImage(String imagePath) {
        this.profileImagePath = imagePath;

        System.out.println(this.profileImagePath);
    }

    public boolean isLoggedIn() {
        return isLoggedIn;
    }

    public static User getInstance() {
        if (User.instance == null)
            User.instance = new User();
        return instance;
    }

    public void setLoggedIn() {
        this.isLoggedIn = true;
    }
}
