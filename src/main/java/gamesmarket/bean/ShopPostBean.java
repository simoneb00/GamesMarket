package gamesmarket.bean;

import java.io.File;

public class ShopPostBean {
    private String shopName;
    private String game;
    private double price;
    private File imageFile;

    public ShopPostBean(String shopName, String game, double price, File imageFile) {
        this.shopName = shopName;
        this.game = game;
        this.price = price;
        this.imageFile = imageFile;
    }

    public String getShopName() {
        return shopName;
    }

    public void setShopName(String shopName) {
        this.shopName = shopName;
    }

    public String getGame() {
        return game;
    }

    public void setGame(String game) {
        this.game = game;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public File getImageFile() {
        return imageFile;
    }

    public void setImageFile(File imageFile) {
        this.imageFile = imageFile;
    }
}
