package gamesmarket.graphiccontrol.mobile.forum;

import gamesmarket.bean.PostBean;
import gamesmarket.control.ForumController;
import gamesmarket.graphiccontrol.mobile.NavigationButtons;
import gamesmarket.main.Main;
import gamesmarket.model.Post;
import gamesmarket.model.ShopOwner;
import gamesmarket.model.User;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.control.TextArea;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class ForumGraphicController extends NavigationButtons implements Initializable {

    @FXML
    private GridPane grid;
    @FXML
    private TextArea postText;
    @FXML
    private GridPane yourPostsGrid;

    private List<Post> oldposts = new ArrayList<>();
    private int col = 0;
    private int row = 1;
    ForumController forumController = new ForumController();

    @FXML
    public void post() {
        try {
            String username;
            String text = postText.getText();

            if (User.getInstance().isLoggedIn()) {
                username = User.getInstance().getUsername();
            } else if (ShopOwner.getInstance().isLoggedIn()) {
                username = ShopOwner.getInstance().getEmail();
            } else
                username = "unknown";

            Post post = new Post(username, text);

            addPostToGrid(post);
            addPostToUserGrid(post);

            PostBean postBean = new PostBean(username, text);

            forumController.savePost(postBean);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        gamesmarket.graphiccontrol.forum.ForumGraphicController forumGraphicController = new gamesmarket.graphiccontrol.forum.ForumGraphicController();
        oldposts = forumGraphicController.retrievePosts();
        List<Post> userOldPosts = forumGraphicController.retrieveUserPosts();

        try {
            for (int i = 0; i < oldposts.size(); i++) {
                FXMLLoader fxmlLoader = new FXMLLoader();
                fxmlLoader.setLocation(Main.class.getResource("/gamesmarket/mobile/forum_post.fxml"));
                AnchorPane ancPane = fxmlLoader.load();

                gamesmarket.graphiccontrol.mobile.forum.PostGraphicController postGraphicController = fxmlLoader.getController();
                postGraphicController.setData(oldposts.get(i));
                postGraphicController.retrieveComments(oldposts.get(i));

                if (col == 1) {
                    col = 0;
                    row++;
                }

                grid.add(ancPane, col++, row);
                GridPane.setMargin(ancPane, new Insets(10));
            }

            for (int i = 0; i < userOldPosts.size(); i++) {
                FXMLLoader fxmlLoader = new FXMLLoader();
                fxmlLoader.setLocation(Main.class.getResource("/gamesmarket/mobile/forum_your_post.fxml"));
                AnchorPane aPane = fxmlLoader.load();

                UserPostGraphicController userPostGraphicController = fxmlLoader.getController();
                userPostGraphicController.setData(userOldPosts.get(i));
                userPostGraphicController.retrieveComments(userOldPosts.get(i));

                if (col == 1) {
                    col = 0;
                    row++;
                }

                yourPostsGrid.add(aPane, col++, row);
                GridPane.setMargin(aPane, new Insets(10));
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void addPostToGrid(Post post) {
        oldposts.add(post);

        try {
            FXMLLoader fxmlLoader = new FXMLLoader();
            fxmlLoader.setLocation(Main.class.getResource("/gamesmarket/mobile/forum_post.fxml"));
            AnchorPane anchorPane3 = fxmlLoader.load();

            if (col == 1) {
                col = 0;
                row++;
            }

            gamesmarket.graphiccontrol.mobile.forum.PostGraphicController postGraphicController = fxmlLoader.getController();
            postGraphicController.setData(post);
            grid.add(anchorPane3, col++, row);
            GridPane.setMargin(anchorPane3, new Insets(10));

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void addPostToUserGrid(Post post) {

        if (User.getInstance().isLoggedIn() || ShopOwner.getInstance().isLoggedIn()) {
            try {
                FXMLLoader fxmlLoader = new FXMLLoader();
                fxmlLoader.setLocation(Main.class.getResource("/gamesmarket/mobile/forum_your_post.fxml"));
                AnchorPane anchorPane4 = fxmlLoader.load();

                if (col == 1) {
                    col = 0;
                    row++;
                }

                gamesmarket.graphiccontrol.forum.UserPostGraphicController userPostGraphicController = fxmlLoader.getController();
                userPostGraphicController.setData(post);
                yourPostsGrid.add(anchorPane4, col++, row);
                GridPane.setMargin(anchorPane4, new Insets(10));

            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }


}
