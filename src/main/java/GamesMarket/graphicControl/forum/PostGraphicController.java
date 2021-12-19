package GamesMarket.graphicControl.forum;

import GamesMarket.bean.PostBean;
import GamesMarket.model.Post;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;


public class PostGraphicController {



    @FXML
    private TextArea text;
    @FXML
    private Label usernameLabel;

    Post post;

    public void setData(Post post) {
        text.setEditable(false);
        this.post = post;
        usernameLabel.setText(post.getUsername());
        text.setText(post.getText());
    }


/*
    public void setNewData(PostBean postBean) {
        Post post = new Post();
        post.setText(postBean.getText());
        post.setUsername(postBean.getUsername());

        text.setEditable(false);
        usernameLabel.setText(post.getUsername());
        text.setText(post.getText());

        ForumGraphicController forumGraphicController = new ForumGraphicController();
        forumGraphicController.addPostToGrid(post);

    }


 */

}
