package gamesmarket.bean;

import java.io.File;

public class ExchangePostBean {

    private String postUsername;
    private String postGame;
    private String postPlatform;
    private String gameToGive;
    private String platformGameToGive;
    private File postImageFile;

    public ExchangePostBean(String postUsername, String postGame, String postPlatform, String gameToGive, String platformGameToGive, File postImageFile) {
        this.postUsername = postUsername;
        this.postGame = postGame;
        this.postPlatform = postPlatform;
        this.gameToGive = gameToGive;
        this.platformGameToGive = platformGameToGive;
        this.postImageFile = postImageFile;
    }

    public String getPostUsername() {
        return postUsername;
    }

    public void setPostUsername(String postUsername) {
        this.postUsername = postUsername;
    }

    public String getPostGame() {
        return postGame;
    }

    public void setPostGame(String postGame) {
        this.postGame = postGame;
    }

    public String getPostPlatform() {
        return postPlatform;
    }

    public void setPostPlatform(String postPlatform) {
        this.postPlatform = postPlatform;
    }

    public String getGameToGive() {
        return gameToGive;
    }

    public void setGameToGive(String gameToGive) {
        this.gameToGive = gameToGive;
    }

    public String getPlatformGameToGive() {
        return platformGameToGive;
    }

    public void setPlatformGameToGive(String platformGameToGive) {
        this.platformGameToGive = platformGameToGive;
    }

    public File getPostImageFile() {
        return postImageFile;
    }

    public void setPostImageFile(File postImageFile) {
        this.postImageFile = postImageFile;
    }
}
