package GamesMarket.graphicControl.forum;

import GamesMarket.model.Game;
import GamesMarket.model.Post;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.image.Image;

public class PostGraphicController {

    @FXML
    private TextArea text;

    @FXML
    private Label usernameLabel;

    Post post;

    public void setData(Post post) {
        this.post = post;
        usernameLabel.setText(post.getUsername());
        text.setText(post.getText());
    }
}
