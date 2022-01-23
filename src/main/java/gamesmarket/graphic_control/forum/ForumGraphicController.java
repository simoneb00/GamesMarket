package gamesmarket.graphic_control.forum;

import gamesmarket.bean.PostBean;
import gamesmarket.control.ForumController;
import gamesmarket.graphic_control.navigation.NavigationButtons;
import gamesmarket.main.Main;
import gamesmarket.model.Post;
import gamesmarket.model.ShopOwner;
import gamesmarket.model.User;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.effect.ColorAdjust;
import javafx.scene.effect.GaussianBlur;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class ForumGraphicController extends NavigationButtons implements Initializable {

    @FXML
    private GridPane postsGrid;
    @FXML
    private TextField postText;
    @FXML
    private Button signInButton;
    @FXML
    private AnchorPane anchorPane;
    @FXML
    private GridPane userGrid;


    private int column = 0;
    private int row = 1;

    ForumController forumController = new ForumController();


    private List<Post> oldposts = new ArrayList<>();
    private List<Post> userOldPosts = new ArrayList<>();


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        if (User.getInstance().isLoggedIn() || ShopOwner.getInstance().isLoggedIn()) {
            signInButton.setVisible(false);
            signInButton.isDisabled();
        }

        oldposts = this.retrievePosts();
        userOldPosts = this.retrieveUserPosts();

        try {
            for (int i = 0; i < oldposts.size(); i++) {
                FXMLLoader fxmlLoader = new FXMLLoader();
                fxmlLoader.setLocation(Main.class.getResource("/gamesmarket/post.fxml"));
                AnchorPane anchorPane1 = fxmlLoader.load();

                PostGraphicController postGraphicController = fxmlLoader.getController();
                postGraphicController.setData(oldposts.get(i));
                postGraphicController.retrieveComments(oldposts.get(i));

                if (column == 1) {
                    column = 0;
                    row++;
                }

                postsGrid.add(anchorPane1, column++, row);
                GridPane.setMargin(anchorPane1, new Insets(10));
            }

            for (int i = 0; i < userOldPosts.size(); i++) {
                FXMLLoader fxmlLoader = new FXMLLoader();
                fxmlLoader.setLocation(Main.class.getResource("/gamesmarket/userPost.fxml"));
                AnchorPane anchorPane2 = fxmlLoader.load();

                UserPostGraphicController userPostGraphicController = fxmlLoader.getController();
                userPostGraphicController.setData(userOldPosts.get(i));
                userPostGraphicController.retrieveComments(userOldPosts.get(i));

                if (column == 1) {
                    column = 0;
                    row++;
                }

                userGrid.add(anchorPane2, column++, row);
                GridPane.setMargin(anchorPane2, new Insets(10));
            }

        } catch (IOException e) {
            e.printStackTrace();
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

    private void addPostToGrid(Post post) {
        oldposts.add(post);

        try {
            FXMLLoader fxmlLoader = new FXMLLoader();
            fxmlLoader.setLocation(Main.class.getResource("/gamesmarket/post.fxml"));
            AnchorPane anchorPane3 = fxmlLoader.load();

            if (column == 1) {
                column = 0;
                row++;
            }

            PostGraphicController postGraphicController = fxmlLoader.getController();
            postGraphicController.setData(post);
            postsGrid.add(anchorPane3, column++, row);
            GridPane.setMargin(anchorPane3, new Insets(10));

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void addPostToUserGrid(Post post) {

        if (User.getInstance().isLoggedIn() || ShopOwner.getInstance().isLoggedIn()) {
            try {
                FXMLLoader fxmlLoader = new FXMLLoader();
                fxmlLoader.setLocation(Main.class.getResource("/gamesmarket/userPost.fxml"));
                AnchorPane anchorPane4 = fxmlLoader.load();

                if (column == 1) {
                    column = 0;
                    row++;
                }

                UserPostGraphicController userPostGraphicController = fxmlLoader.getController();
                userPostGraphicController.setData(post);
                userGrid.add(anchorPane4, column++, row);
                GridPane.setMargin(anchorPane4, new Insets(10));

            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }


    @FXML
    public void postButton() {
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

    @FXML
    public void signInButtonPressed() {
        try {

            Parent root = FXMLLoader.load(Main.class.getResource("/gamesmarket/login.fxml"));
            Stage loginStage = new Stage();
            Scene loginScene = new Scene(root, 600, 400);
            loginScene.setFill(Color.TRANSPARENT);
            loginStage.initStyle(StageStyle.TRANSPARENT);
            loginStage.setScene(loginScene);

            GaussianBlur blur = new GaussianBlur(55);
            ColorAdjust adj = new ColorAdjust(-0.1, -0.1, -0.1, -0.1);
            adj.setInput(blur);
            anchorPane.setEffect(adj);

            loginStage.showAndWait();
            anchorPane.setEffect(null);

            if (User.getInstance().isLoggedIn() || ShopOwner.getInstance().isLoggedIn()) {
                signInButton.setVisible(false);
                signInButton.isDisabled();

                postsGrid.getChildren().clear();
                this.initialize(null, null);
            }


        } catch (Exception e) {
            e.printStackTrace();
            e.getCause();
        }

    }

    @FXML
    public void exchangeButtonPressed(ActionEvent event) {
        if (User.getInstance().isLoggedIn()) {
            this.exchangeButton(event);
        } else {
            this.signInButtonPressed();
        }
    }

    @FXML
    public void profileButtonPressed(ActionEvent event) {
        if (User.getInstance().isLoggedIn()) {
            this.profileButton(event);
        } else {
            this.signInButtonPressed();
        }
    }

}






