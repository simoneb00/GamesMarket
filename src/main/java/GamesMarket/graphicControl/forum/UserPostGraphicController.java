package GamesMarket.graphicControl.forum;

import GamesMarket.bean.CommentBean;
import GamesMarket.bean.PostBean;
import GamesMarket.control.ForumController;
import GamesMarket.exceptions.ErrorMessage;
import GamesMarket.main.Main;
import GamesMarket.model.Comment;
import GamesMarket.model.DAO.PostDAO;
import GamesMarket.model.Post;
import GamesMarket.model.User;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;
import java.sql.SQLException;

public class UserPostGraphicController extends PostGraphicController{

    @FXML
    private TextField commentText;

    @FXML
    private GridPane grid;

    @FXML
    private TextArea text;

    @FXML
    private Label usernameLabel;


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
