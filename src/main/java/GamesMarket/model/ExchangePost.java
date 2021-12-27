package GamesMarket.model;

public class ExchangePost {

    private String username;
    private String game;
    private String platform;
    private String gameToGive;
    private String platformGameToGive;

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

    public String getGameToGive() {
        return gameToGive;
    }

    public void setGameToGive(String gameToReceive) {
        this.gameToGive = gameToReceive;
    }

    public String getPlatformGameToGive() {
        return platformGameToGive;
    }

    public void setPlatformGameToGive(String platformGameToReceive) {
        this.platformGameToGive = platformGameToReceive;
    }
}
