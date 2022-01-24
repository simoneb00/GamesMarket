package gamesmarket.graphiccontrol.forum;

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
    private TextArea text;
    @FXML
    private Label usernameLabel;
    @FXML
    private GridPane grid;
    @FXML
    private TextField commentText;

    private List<Comment> oldComments = new ArrayList<>();
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

    private List<Comment> retrievePostComments(PostBean postBean) {
        List<Comment> comments = new ArrayList<>();

        try {
            List<CommentBean> beans = forumController.retrieveComments(postBean);

            for (int i = 0; i < beans.size(); i++) {
                Comment comment = new Comment();
                comment.setUsername(beans.get(i).getCommentUsername());
                comment.setText(beans.get(i).getCommentText());
                comments.add(comment);
            }


        } catch (SQLException e) {
            ErrorMessage.displayErrorMessage();
        }

        return comments;
    }


    public void retrieveComments(Post post) throws IOException {

        PostBean postBean = new PostBean(post.getUsername(), post.getText());
        oldComments = this.retrievePostComments(postBean);

        try {
            for (int i = 0; i < oldComments.size(); i++) {

                FXMLLoader fxmlLoader = new FXMLLoader();
                fxmlLoader.setLocation(Main.class.getResource("/gamesmarket/comment.fxml"));
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
            ErrorMessage.displayErrorMessage();
        }
    }

    @FXML
    public void commentButtonHandler() {
        String username;
        if (User.getInstance().isLoggedIn())
            username = User.getInstance().getUsername();
        else if (ShopOwner.getInstance().isLoggedIn())
            username = ShopOwner.getInstance().getEmail();
        else
            username = "unknown";

        String txt = commentText.getText();

        Comment comment = new Comment();
        comment.setText(txt);
        comment.setUsername(username);

        this.addCommentToGrid(comment);

        CommentBean commentBean = new CommentBean();
        commentBean.setCommentText(username);
        commentBean.setCommentText(txt);

        PostBean postBean = new PostBean(post.getUsername(), post.getText());

        try {
            forumController.saveComment(commentBean, postBean);
        } catch (SQLException e) {
            ErrorMessage.displayErrorMessage();
        }
    }

    private void addCommentToGrid(Comment comment) {
        oldComments.add(comment);

        try {
            FXMLLoader fxmlLoader = new FXMLLoader();
            fxmlLoader.setLocation(Main.class.getResource("/gamesmarket/comment.fxml"));
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
            ErrorMessage.displayErrorMessage();
        }
    }


}
