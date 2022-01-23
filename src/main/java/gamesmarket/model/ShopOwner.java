package gamesmarket.model;

public class ShopOwner {

    private String email;
    private String password;
    private Shop shop = null;
    private static ShopOwner instance = null;
    private boolean isLoggedIn = false;

    private ShopOwner(){}

    public Shop getShop() {
        return shop;
    }

    public void setShop(Shop shop) {
        this.shop = shop;
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
