package gamesmarket.bean;

public class PostBean {

    private String postText;
    private String postUsername;

    public PostBean(String username, String text) {
        this.postText = text;
        this.postUsername = username;
    }

    public String getPostText() {
        return postText;
    }

    public void setPostText(String postText) {
        this.postText = postText;
    }

    public String getPostUsername() {
        return postUsername;
    }

    public void setPostUsername(String postUsername) {
        this.postUsername = postUsername;
    }
}
