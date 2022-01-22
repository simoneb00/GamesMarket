package gamesmarket.bean;

import java.io.File;

public class ShopPostBean {
    private String postShopName;
    private String shopPostGame;
    private double shopPostPrice;
    private File shopPostImageFile;

    public ShopPostBean(String postShopName, String shopPostGame, double shopPostPrice, File shopPostImageFile) {
        this.postShopName = postShopName;
        this.shopPostGame = shopPostGame;
        this.shopPostPrice = shopPostPrice;
        this.shopPostImageFile = shopPostImageFile;
    }

    public String getPostShopName() {
        return postShopName;
    }

    public void setPostShopName(String postShopName) {
        this.postShopName = postShopName;
    }

    public String getShopPostGame() {
        return shopPostGame;
    }

    public void setShopPostGame(String shopPostGame) {
        this.shopPostGame = shopPostGame;
    }

    public double getShopPostPrice() {
        return shopPostPrice;
    }

    public void setShopPostPrice(double shopPostPrice) {
        this.shopPostPrice = shopPostPrice;
    }

    public File getShopPostImageFile() {
        return shopPostImageFile;
    }

    public void setShopPostImageFile(File shopPostImageFile) {
        this.shopPostImageFile = shopPostImageFile;
    }
}
