package GamesMarket.graphicControl.forum;

import GamesMarket.bean.PostBean;
import GamesMarket.control.ForumController;
import GamesMarket.model.DAO.PostDAO;
import GamesMarket.model.Post;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;

public class UserPostGraphicController {

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

    public void deleteButton() {
        PostBean postBean = new PostBean();
        postBean.setUsername(usernameLabel.getText());
        postBean.setText(text.getText());

        ForumController forumController = new ForumController();
        forumController.delete(postBean);

    }
}
