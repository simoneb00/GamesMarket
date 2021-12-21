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

}
