package GamesMarket.graphicControl.forum;

import GamesMarket.model.Comment;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;

public class CommentGraphicController {

    @FXML
    private TextArea text;
    @FXML
    private Label username;

    public void setData(Comment comment) {
        text.setEditable(false);
        username.setText(comment.getUsername());
        text.setText(comment.getText());
    }

}
