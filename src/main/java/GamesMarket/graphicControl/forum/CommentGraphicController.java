package GamesMarket.graphicControl.forum;

import GamesMarket.model.Comment;
import GamesMarket.model.Post;
import GamesMarket.model.User;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ResourceBundle;

public class CommentGraphicController {

    @FXML
    private TextArea text;
    @FXML
    private Label username;

    private Comment comment;

    public void setData(Comment comment) {
        text.setEditable(false);
        this.comment = comment;
        username.setText(comment.getUsername());
        text.setText(comment.getText());
    }

}
