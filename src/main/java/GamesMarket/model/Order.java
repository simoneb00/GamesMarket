package GamesMarket.model;

public class Order {

    private String username;
    private String game;
    private String platform;
    private String paymentMethod;

    public Order(String username, String game, String platform, String paymentMethod) {
        this.username = username;
        this.game = game;
        this.platform = platform;
        this.paymentMethod = paymentMethod;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getGame() {
        return game;
    }

    public void setGame(String game) {
        this.game = game;
    }

    public String getPlatform() {
        return platform;
    }

    public void setPlatform(String platform) {
        this.platform = platform;
    }

    public String getPaymentMethod() {
        return paymentMethod;
    }

    public void setPaymentMethod(String paymentMethod) {
        this.paymentMethod = paymentMethod;
    }
}
