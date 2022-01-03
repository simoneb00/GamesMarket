package GamesMarket.graphicControl.forum;

import GamesMarket.bean.CommentBean;
import GamesMarket.bean.PostBean;
import GamesMarket.control.ForumController;
import GamesMarket.main.Main;
import GamesMarket.model.Comment;
import GamesMarket.model.Post;
import GamesMarket.model.ShopOwner;
import GamesMarket.model.User;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.effect.ColorAdjust;
import javafx.scene.effect.GaussianBlur;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;


public class PostGraphicController{
    @FXML
    private TextArea text;
    @FXML
    private Label usernameLabel;
    @FXML
    private GridPane grid;
    @FXML
    private TextField commentText;

    private List<Comment> oldComments = new ArrayList<>();
    private List<Comment> userOldComments = new ArrayList<>();
    private Post post = new Post("", "");
    private ForumController forumController = new ForumController();
    private int row = 1;
    private int column = 0;

    public void setData(Post post) {
        text.setEditable(false);
        this.post = post;
        usernameLabel.setText(post.getUsername());
        text.setText(post.getText());
    }

    public List<Comment> retrievePostComments(PostBean postBean) {
        List<CommentBean> beans  = forumController.retrieveComments(postBean);
        List<Comment> comments = new ArrayList<>();

        for (int i = 0; i < beans.size(); i++) {
            Comment comment = new Comment();
            comment.setUsername(beans.get(i).getUsername());
            comment.setText(beans.get(i).getText());
            comments.add(comment);
        }

        return comments;
    }


    public void retrieveComments(Post post) {

        PostBean postBean = new PostBean(post.getUsername(), post.getText());
        oldComments = this.retrievePostComments(postBean);

        try {
            for (int i = 0; i < oldComments.size(); i++) {

                FXMLLoader fxmlLoader = new FXMLLoader();
                fxmlLoader.setLocation(Main.class.getResource("/GamesMarket/comment.fxml"));
                AnchorPane anchorPane = fxmlLoader.load();

                CommentGraphicController commentGraphicController = fxmlLoader.getController();
                commentGraphicController.setData(oldComments.get(i));

                if (column == 1) {
                    column = 0;
                    row++;
                }

                grid.add(anchorPane, column++, row);
                GridPane.setMargin(anchorPane, new Insets(10));
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void commentButtonHandler() {
        String username;
        if (User.getInstance().isLoggedIn())
            username = User.getInstance().getUsername();
        else if (ShopOwner.getInstance().isLoggedIn())
            username = ShopOwner.getInstance().getEmail();
        else
            username = "unknown";

        String text = commentText.getText();

        Comment comment = new Comment();
        comment.setText(text);
        comment.setUsername(username);

        this.addCommentToGrid(comment);

        CommentBean commentBean = new CommentBean();
        commentBean.setUsername(username);
        commentBean.setText(text);

        PostBean postBean = new PostBean(post.getUsername(), post.getText());

        forumController.saveComment(commentBean, postBean);
    }

    public void addCommentToGrid(Comment comment) {
        oldComments.add(comment);

        try {
            FXMLLoader fxmlLoader = new FXMLLoader();
            fxmlLoader.setLocation(Main.class.getResource("/GamesMarket/comment.fxml"));
            AnchorPane anchorPane = fxmlLoader.load();

            if (column == 1) {
                column = 0;
                row++;
            }

            CommentGraphicController commentGraphicController = fxmlLoader.getController();
            commentGraphicController.setData(comment);
            grid.add(anchorPane, column++, row);
            GridPane.setMargin(anchorPane, new Insets(10));

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
