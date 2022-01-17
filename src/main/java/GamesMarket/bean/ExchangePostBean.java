package GamesMarket.bean;

import java.io.File;

public class ExchangePostBean {

    private String username;
    private String game;
    private String platform;
    private String gameToGive;
    private String platformGameToGive;
    private File imageFile;

    public ExchangePostBean(String username, String game, String platform, String gameToGive, String platformGameToGive, File imageFile) {
        this.username = username;
        this.game = game;
        this.platform = platform;
        this.gameToGive = gameToGive;
        this.platformGameToGive = platformGameToGive;
        this.imageFile = imageFile;
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

    public String getGameToGive() {
        return gameToGive;
    }

    public String getPlatformGameToGive() {
        return platformGameToGive;
    }

    public File getImageFile() {
        return imageFile;
    }

    public void setImageFile(File imageFile) {
        this.imageFile = imageFile;
    }
}
