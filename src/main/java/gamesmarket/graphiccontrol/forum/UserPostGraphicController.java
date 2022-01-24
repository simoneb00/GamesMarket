package gamesmarket.graphiccontrol.forum;

import gamesmarket.bean.PostBean;
import gamesmarket.control.ForumController;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;

import java.sql.SQLException;

public class UserPostGraphicController extends PostGraphicController {

    @FXML
    private TextArea text;

    @FXML
    private Label usernameLabel;


    @FXML
    public void deleteButton() {
        PostBean postBean = new PostBean(usernameLabel.getText(), text.getText());

        ForumController forumController = new ForumController();

        try {
            forumController.delete(postBean);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


}
