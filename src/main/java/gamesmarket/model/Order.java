package gamesmarket.model;

public class Order {

    private int idOrder;
    private String vendor;
    private String platform;
    private String game;
    private double price;
    private String buyerName;
    private String buyerAddress;
    private String buyerCity;
    private String buyerTel;
    private String paymentMethod;
    private String username;
    private String buyerEmail;
    private String status;

    public void setPaymentMethod(String paymentMethod) {
        this.paymentMethod = paymentMethod;
    }

    public void setBuyerEmail(String buyerEmail) {
        this.buyerEmail = buyerEmail;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public void setIdOrder(int idOrder) {
        this.idOrder = idOrder;
    }

    public void setVendor(String vendor) {
        this.vendor = vendor;
    }

    public void setBuyerName(String buyerName) {
        this.buyerName = buyerName;
    }

    public void setBuyerAddress(String buyerAddress) {
        this.buyerAddress = buyerAddress;
    }

    public void setBuyerCity(String buyerCity) {
        this.buyerCity = buyerCity;
    }

    public void setBuyerTel(String buyerTel) {
        this.buyerTel = buyerTel;
    }

    public int getIdOrder() {
        return idOrder;
    }

    public String getStatus() {
        return status;
    }

    public String getBuyerEmail() {
        return buyerEmail;
    }

    public String getVendor() {
        return vendor;
    }

    public String getPlatform() {
        return platform;
    }

    public void setPlatform(String platform) {
        this.platform = platform;
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

    public String getBuyerName() {
        return buyerName;
    }

    public String getBuyerAddress() {
        return buyerAddress;
    }

    public String getBuyerCity() {
        return buyerCity;
    }

    public String getBuyerTel() {
        return buyerTel;
    }

    public String getPaymentMethod() {
        return paymentMethod;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}
