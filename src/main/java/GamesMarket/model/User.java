package GamesMarket.model;

import java.util.ArrayList;
import java.util.List;

public class User {

    private String firstName;
    private String lastName;
    private String username;
    private String password;
    private String emailAddress;
    private String profileImagePath;
    private String bio;
    private List<String> contacts;
    private List<String> wishlist;
    private List<String> tradelist;
    private boolean isLoggedIn = false;
    private List<Order> orders = new ArrayList<>();

    private static User instance = null;

    private User() {
        return;
    }


    public List<Order> getOrders() {
        return orders;
    }

    public void setOrders(List<Order> orders) {
        this.orders = orders;
    }

    public String getBio() {
        return bio;
    }

    public void setBio(String bio) {
        this.bio = bio;
    }

    public List<String> getContacts() {
        return contacts;
    }

    public void setContacts(List<String> contacts) {
        this.contacts = contacts;
    }

    public List<String> getWishlist() {
        return wishlist;
    }

    public void setWishlist(List<String> wishlist) {
        this.wishlist = wishlist;
    }

    public List<String> getTradelist() {
        return tradelist;
    }

    public void setTradelist(List<String> tradelist) {
        this.tradelist = tradelist;
    }

    public String getProfileImagePath() {
        return profileImagePath;
    }

    public void setProfileImagePath(String profileImagePath) {
        this.profileImagePath = profileImagePath;
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
