package gamesmarket.graphiccontrol.mobile.forum;

import gamesmarket.bean.CommentBean;
import gamesmarket.bean.PostBean;
import gamesmarket.control.ForumController;
import gamesmarket.exceptions.ErrorMessage;
import gamesmarket.main.Main;
import gamesmarket.model.Comment;
import gamesmarket.model.Post;
import gamesmarket.model.ShopOwner;
import gamesmarket.model.User;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class PostGraphicController {

    @FXML
    private TextField commentText;
    @FXML
    private GridPane grid;
    @FXML
    private TextArea text;
    @FXML
    private Label usernameLabel;

    private Post post = new Post("", "");
    private ForumController forumController = new ForumController();
    private List<Comment> oldComments = new ArrayList<>();
    private int row = 1;
    private int col = 0;


    public void setData(Post post) {
        text.setEditable(false);
        this.post = post;
        usernameLabel.setText(post.getUsername());
        text.setText(post.getText());
    }


    public void retrieveComments(Post post) {

        try {
            PostBean postBean = new PostBean(post.getUsername(), post.getText());
            oldComments = this.retrievePostComments(postBean);

            for (int i = 0; i < oldComments.size(); i++) {

                FXMLLoader fxmlLoader = new FXMLLoader();
                fxmlLoader.setLocation(Main.class.getResource("/gamesmarket/mobile/comment.fxml"));
                AnchorPane anchorPane = fxmlLoader.load();

                gamesmarket.graphiccontrol.mobile.forum.CommentGraphicController commentGraphicController = fxmlLoader.getController();
                commentGraphicController.setData(oldComments.get(i));

                if (col == 1) {
                    col = 0;
                    row++;
                }

                grid.add(anchorPane, col++, row);
                GridPane.setMargin(anchorPane, new Insets(10));
            }

        } catch (IOException e) {
            ErrorMessage.displayErrorMobile();
        }
    }

    private List<Comment> retrievePostComments(PostBean postBean) {
        List<Comment> comments = new ArrayList<>();

        try {
            List<CommentBean> commentBeans = forumController.retrieveComments(postBean);

            for (int i = 0; i < commentBeans.size(); i++) {
                Comment comment = new Comment();
                comment.setUsername(commentBeans.get(i).getCommentUsername());
                comment.setText(commentBeans.get(i).getCommentText());
                comments.add(comment);
            }

        } catch (SQLException e) {
            ErrorMessage.displayErrorMobile();
        }

        return comments;
    }

    @FXML
    public void commentButtonHandler() {
        String username = "unknown";
        if (User.getInstance().isLoggedIn())
            username = User.getInstance().getUsername();
        else if (ShopOwner.getInstance().isLoggedIn())
            username = ShopOwner.getInstance().getEmail();

        String txt = commentText.getText();

        Comment c = new Comment();
        c.setText(txt);
        c.setUsername(username);

        this.showComment(c);

        PostBean postBean = new PostBean(post.getUsername(), post.getText());
        CommentBean commentBean = new CommentBean();
        commentBean.setCommentUsername(username);
        commentBean.setCommentText(txt);

        try {
            forumController.saveComment(commentBean, postBean);     // save comment in the db
        } catch (SQLException e) {
            ErrorMessage.displayErrorMobile();
        }
    }

    private void showComment(Comment comment) {
        oldComments.add(comment);       // adds comment to post's comments list

        try {
            FXMLLoader fxmlLoader = new FXMLLoader();
            fxmlLoader.setLocation(Main.class.getResource("/gamesmarket/mobile/comment.fxml"));
            AnchorPane anchorPane = fxmlLoader.load();

            if (col == 1) {
                col = 0;
                row++;
            }

            CommentGraphicController commentGraphicController = fxmlLoader.getController();
            commentGraphicController.setData(comment);
            grid.add(anchorPane, col++, row);
            GridPane.setMargin(anchorPane, new Insets(10));

        } catch (IOException e) {
            ErrorMessage.displayErrorMobile();
        }
    }


}
