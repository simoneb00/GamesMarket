package GamesMarket.graphicControl.forum;

import GamesMarket.bean.CommentBean;
import GamesMarket.bean.PostBean;
import GamesMarket.control.ForumController;
import GamesMarket.main.Main;
import GamesMarket.model.Comment;
import GamesMarket.model.DAO.PostDAO;
import GamesMarket.model.Post;
import GamesMarket.model.User;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;

import java.io.IOException;

public class UserPostGraphicController extends PostGraphicController{

    @FXML
    private TextField commentText;

    @FXML
    private GridPane grid;

    @FXML
    private TextArea text;

    @FXML
    private Label usernameLabel;

    Post post;

    public void deleteButton() {
        PostBean postBean = new PostBean();
        postBean.setUsername(usernameLabel.getText());
        postBean.setText(text.getText());

        ForumController forumController = new ForumController();
        forumController.delete(postBean);

    }






}
