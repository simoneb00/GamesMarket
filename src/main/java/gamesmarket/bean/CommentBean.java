package gamesmarket.bean;

import gamesmarket.model.Comment;

public class CommentBean {

    private String commentUsername;
    private String commentText;


    public String getCommentUsername() {
        return commentUsername;
    }

    public void setCommentUsername(String commentUsername) {
        this.commentUsername = commentUsername;
    }

    public String getCommentText() {
        return commentText;
    }

    public void setCommentText(String commentText) {
        this.commentText = commentText;
    }
}
