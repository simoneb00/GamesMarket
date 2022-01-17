package GamesMarket.graphicControl.mobile.forum;

import GamesMarket.bean.CommentBean;
import GamesMarket.bean.PostBean;
import GamesMarket.control.ForumController;
import GamesMarket.exceptions.ErrorMessage;
import GamesMarket.main.Main;
import GamesMarket.model.Comment;
import GamesMarket.model.Post;
import GamesMarket.model.ShopOwner;
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
    private List<Comment> userOldComments = new ArrayList<>();
    private int row = 1;
    private int column = 0;


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
                fxmlLoader.setLocation(Main.class.getResource("/GamesMarket/mobile/comment.fxml"));
                AnchorPane anchorPane = fxmlLoader.load();

                GamesMarket.graphicControl.mobile.forum.CommentGraphicController commentGraphicController = fxmlLoader.getController();
                commentGraphicController.setData(oldComments.get(i));

                if (column == 1) {
                    column = 0;
                    row++;
                }

                grid.add(anchorPane, column++, row);
                GridPane.setMargin(anchorPane, new Insets(10));
            }

        } catch (IOException e) {
            ErrorMessage.displayErrorMobile();
        }
    }

    private List<Comment> retrievePostComments(PostBean postBean) throws IOException {
        List<Comment> comments = new ArrayList<>();

        try {
            List<CommentBean> beans = forumController.retrieveComments(postBean);

            for (int i = 0; i < beans.size(); i++) {
                Comment comment = new Comment();
                comment.setUsername(beans.get(i).getUsername());
                comment.setText(beans.get(i).getText());
                comments.add(comment);
            }


        } catch (SQLException e) {
            ErrorMessage.displayErrorMessage();
        }

        return comments;
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

        String text = commentText.getText();

        Comment comment = new Comment();
        comment.setText(text);
        comment.setUsername(username);

        this.addCommentToGrid(comment);

        CommentBean commentBean = new CommentBean();
        commentBean.setUsername(username);
        commentBean.setText(text);

        PostBean postBean = new PostBean(post.getUsername(), post.getText());

        try {
            forumController.saveComment(commentBean, postBean);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void addCommentToGrid(Comment comment) {
        oldComments.add(comment);

        try {
            FXMLLoader fxmlLoader = new FXMLLoader();
            fxmlLoader.setLocation(Main.class.getResource("/GamesMarket/mobile/comment.fxml"));
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
