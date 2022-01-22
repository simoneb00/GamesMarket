package gamesmarket.bean;

public class OrderBean {

    private int idOrder;
    private String vendor;
    private String orderPlatform;
    private String orderGame;
    private double orderPrice;
    private String buyerName;
    private String buyerAddress;
    private String buyerCity;
    private String buyerTel;
    private String paymentMethod;
    private String buyerUsername;
    private String buyerEmail;
    private String status;
    private String newStatus;

    public OrderBean(int idOrder, String vendor, String orderPlatform, String orderGame, double orderPrice, String buyerName, String buyerAddress, String buyerCity, String buyerTel, String paymentMethod, String buyerUsername, String buyerEmail, String status) {
        this.idOrder = idOrder;
        this.vendor = vendor;
        this.orderPlatform = orderPlatform;
        this.orderGame = orderGame;
        this.orderPrice = orderPrice;
        this.buyerName = buyerName;
        this.buyerAddress = buyerAddress;
        this.buyerCity = buyerCity;
        this.buyerTel = buyerTel;
        this.paymentMethod = paymentMethod;
        this.buyerUsername = buyerUsername;
        this.buyerEmail = buyerEmail;
        this.status = status;
    }

    public int getIdOrder() {
        return idOrder;
    }

    public void setIdOrder(int idOrder) {
        this.idOrder = idOrder;
    }

    public String getVendor() {
        return vendor;
    }

    public void setVendor(String vendor) {
        this.vendor = vendor;
    }

    public String getOrderPlatform() {
        return orderPlatform;
    }

    public void setOrderPlatform(String orderPlatform) {
        this.orderPlatform = orderPlatform;
    }

    public String getOrderGame() {
        return orderGame;
    }

    public void setOrderGame(String orderGame) {
        this.orderGame = orderGame;
    }

    public double getOrderPrice() {
        return orderPrice;
    }

    public void setOrderPrice(double orderPrice) {
        this.orderPrice = orderPrice;
    }

    public String getBuyerName() {
        return buyerName;
    }

    public void setBuyerName(String buyerName) {
        this.buyerName = buyerName;
    }

    public String getBuyerAddress() {
        return buyerAddress;
    }

    public void setBuyerAddress(String buyerAddress) {
        this.buyerAddress = buyerAddress;
    }

    public String getBuyerCity() {
        return buyerCity;
    }

    public void setBuyerCity(String buyerCity) {
        this.buyerCity = buyerCity;
    }

    public String getBuyerTel() {
        return buyerTel;
    }

    public void setBuyerTel(String buyerTel) {
        this.buyerTel = buyerTel;
    }

    public String getPaymentMethod() {
        return paymentMethod;
    }

    public void setPaymentMethod(String paymentMethod) {
        this.paymentMethod = paymentMethod;
    }

    public String getBuyerUsername() {
        return buyerUsername;
    }

    public void setBuyerUsername(String buyerUsername) {
        this.buyerUsername = buyerUsername;
    }

    public String getBuyerEmail() {
        return buyerEmail;
    }

    public void setBuyerEmail(String buyerEmail) {
        this.buyerEmail = buyerEmail;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getNewStatus() {
        return newStatus;
    }

    public void setNewStatus(String newStatus) {
        this.newStatus = newStatus;
    }
}
