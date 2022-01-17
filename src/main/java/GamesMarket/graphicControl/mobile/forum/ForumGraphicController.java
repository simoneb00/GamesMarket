package GamesMarket.graphicControl.mobile.forum;

import GamesMarket.bean.PostBean;
import GamesMarket.control.ForumController;
import GamesMarket.graphicControl.mobile.NavigationButtons;
import GamesMarket.graphicControl.mobile.ShopOwnerNavigationButtons;
import GamesMarket.main.Main;
import GamesMarket.model.Post;
import GamesMarket.model.ShopOwner;
import GamesMarket.model.User;
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
    private List<Post> userOldPosts = new ArrayList<>();
    private ShopOwnerNavigationButtons shopOwnerNavigationButtons = new ShopOwnerNavigationButtons();

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
        userOldPosts = this.retrieveUserPosts();

        try {
            for (int i = 0; i < oldposts.size(); i++) {
                FXMLLoader fxmlLoader = new FXMLLoader();
                fxmlLoader.setLocation(Main.class.getResource("/GamesMarket/mobile/forum_post.fxml"));
                AnchorPane anchorPane = fxmlLoader.load();

                GamesMarket.graphicControl.mobile.forum.PostGraphicController postGraphicController = fxmlLoader.getController();
                postGraphicController.setData(oldposts.get(i));
                postGraphicController.retrieveComments(oldposts.get(i));

                if (column == 1) {
                    column = 0;
                    row++;
                }

                grid.add(anchorPane, column++, row);
                GridPane.setMargin(anchorPane, new Insets(10));
            }

            for (int i = 0; i < userOldPosts.size(); i++) {
                FXMLLoader fxmlLoader = new FXMLLoader();
                fxmlLoader.setLocation(Main.class.getResource("/GamesMarket/mobile/forum_your_post.fxml"));
                AnchorPane anchorPane = fxmlLoader.load();

                UserPostGraphicController userPostGraphicController = fxmlLoader.getController();
                userPostGraphicController.setData(userOldPosts.get(i));
                userPostGraphicController.retrieveComments(userOldPosts.get(i));

                if (column == 1) {
                    column = 0;
                    row++;
                }

                yourPostsGrid.add(anchorPane, column++, row);
                GridPane.setMargin(anchorPane, new Insets(10));
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void addPostToGrid(Post post) {
        oldposts.add(post);

        try {
            FXMLLoader fxmlLoader = new FXMLLoader();
            fxmlLoader.setLocation(Main.class.getResource("/GamesMarket/mobile/forum_post.fxml"));
            AnchorPane anchorPane = fxmlLoader.load();

            if (column == 1) {
                column = 0;
                row++;
            }

            GamesMarket.graphicControl.mobile.forum.PostGraphicController postGraphicController = fxmlLoader.getController();
            postGraphicController.setData(post);
            grid.add(anchorPane, column++, row);
            GridPane.setMargin(anchorPane, new Insets(10));

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void addPostToUserGrid(Post post) {

        if (User.getInstance().isLoggedIn() || ShopOwner.getInstance().isLoggedIn()) {
            try {
                FXMLLoader fxmlLoader = new FXMLLoader();
                fxmlLoader.setLocation(Main.class.getResource("/GamesMarket/mobile/forum_your_post.fxml"));
                AnchorPane anchorPane = fxmlLoader.load();

                if (column == 1) {
                    column = 0;
                    row++;
                }

                GamesMarket.graphicControl.mobile.forum.UserPostGraphicController userPostGraphicController = fxmlLoader.getController();
                userPostGraphicController.setData(post);
                yourPostsGrid.add(anchorPane, column++, row);
                GridPane.setMargin(anchorPane, new Insets(10));

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
                        beans.get(i).getUsername(),
                        beans.get(i).getText()
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
                        beans.get(i).getUsername(),
                        beans.get(i).getText()
                );

                posts.add(post);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return posts;
    }


}
