package gamesmarket.bean;

public class OrderBean {

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
    private String newStatus;

    public OrderBean(int idOrder, String vendor, String platform, String game, double price, String buyerName, String buyerAddress, String buyerCity, String buyerTel, String paymentMethod, String username, String buyerEmail, String status) {
        this.idOrder = idOrder;
        this.vendor = vendor;
        this.platform = platform;
        this.game = game;
        this.price = price;
        this.buyerName = buyerName;
        this.buyerAddress = buyerAddress;
        this.buyerCity = buyerCity;
        this.buyerTel = buyerTel;
        this.paymentMethod = paymentMethod;
        this.username = username;
        this.buyerEmail = buyerEmail;
        this.status = status;
    }

    public int getIdOrder() {
        return idOrder;
    }

    public void setNewStatus(String newStatus) {
        this.newStatus = newStatus;
    }

    public String getNewStatus() {
        return this.newStatus;
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
