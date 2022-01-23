package gamesmarket.graphic_control.mobile.forum;

import gamesmarket.bean.PostBean;
import gamesmarket.control.ForumController;
import gamesmarket.graphic_control.mobile.NavigationButtons;
import gamesmarket.graphic_control.mobile.ShopOwnerNavigationButtons;
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
    private int column = 0;
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
        oldposts = this.retrievePosts();
        List<Post> userOldPosts = this.retrieveUserPosts();

        try {
            for (int i = 0; i < oldposts.size(); i++) {
                FXMLLoader fxmlLoader = new FXMLLoader();
                fxmlLoader.setLocation(Main.class.getResource("/gamesmarket/mobile/forum_post.fxml"));
                AnchorPane ancPane = fxmlLoader.load();

                gamesmarket.graphic_control.mobile.forum.PostGraphicController postGraphicController = fxmlLoader.getController();
                postGraphicController.setData(oldposts.get(i));
                postGraphicController.retrieveComments(oldposts.get(i));

                if (column == 1) {
                    column = 0;
                    row++;
                }

                grid.add(ancPane, column++, row);
                GridPane.setMargin(ancPane, new Insets(10));
            }

            for (int i = 0; i < userOldPosts.size(); i++) {
                FXMLLoader fxmlLoader = new FXMLLoader();
                fxmlLoader.setLocation(Main.class.getResource("/gamesmarket/mobile/forum_your_post.fxml"));
                AnchorPane aPane = fxmlLoader.load();

                UserPostGraphicController userPostGraphicController = fxmlLoader.getController();
                userPostGraphicController.setData(userOldPosts.get(i));
                userPostGraphicController.retrieveComments(userOldPosts.get(i));

                if (column == 1) {
                    column = 0;
                    row++;
                }

                yourPostsGrid.add(aPane, column++, row);
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
            AnchorPane pane = fxmlLoader.load();

            if (column == 1) {
                column = 0;
                row++;
            }

            gamesmarket.graphic_control.mobile.forum.PostGraphicController postGraphicController = fxmlLoader.getController();
            postGraphicController.setData(post);
            grid.add(pane, column++, row);
            GridPane.setMargin(pane, new Insets(10));

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void addPostToUserGrid(Post post) {

        if (User.getInstance().isLoggedIn() || ShopOwner.getInstance().isLoggedIn()) {
            try {
                FXMLLoader fxmlLoader = new FXMLLoader();
                fxmlLoader.setLocation(Main.class.getResource("/gamesmarket/mobile/forum_your_post.fxml"));
                AnchorPane anchPane = fxmlLoader.load();

                if (column == 1) {
                    column = 0;
                    row++;
                }

                gamesmarket.graphic_control.mobile.forum.UserPostGraphicController userPostGraphicController = fxmlLoader.getController();
                userPostGraphicController.setData(post);
                yourPostsGrid.add(anchPane, column++, row);
                GridPane.setMargin(anchPane, new Insets(10));

            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }


    private List<Post> retrievePosts() {

        List<Post> posts = new ArrayList<>();

        try {
            List<PostBean> beans = forumController.retrievePosts();

            for (int i = 0; i < beans.size(); i++) {
                Post post = new Post(
                        beans.get(i).getPostUsername(),
                        beans.get(i).getPostText()
                );

                posts.add(post);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return posts;
    }

    private List<Post> retrieveUserPosts() {
        List<Post> posts = new ArrayList<>();

        try {
            List<PostBean> beans = forumController.retrieveUserPosts();
            for (int i = 0; i < beans.size(); i++) {
                Post post = new Post(
                        beans.get(i).getPostUsername(),
                        beans.get(i).getPostText()
                );

                posts.add(post);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return posts;
    }


}
