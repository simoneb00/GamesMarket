package GamesMarket.model;

import GamesMarket.graphicControl.HomepageGraphicController;

public class ShopOwner {

    private String firstName;
    private String lastName;
    private String email;
    private String password;
    private static ShopOwner instance = null;
    private boolean isLoggedIn = false;

    private ShopOwner(){return;}

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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public static ShopOwner getInstance() {
        if (ShopOwner.instance == null)
            instance = new ShopOwner();
        return instance;
    }

    public boolean isLoggedIn() {
        return isLoggedIn;
    }

    public void setLoggedIn() {
        this.isLoggedIn = true;
    }
}
