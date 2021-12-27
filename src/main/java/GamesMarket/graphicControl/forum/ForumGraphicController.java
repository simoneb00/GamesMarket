package GamesMarket.graphicControl.forum;

import GamesMarket.bean.PostBean;
import GamesMarket.control.ForumController;
import GamesMarket.graphicControl.NavigationButtons;
import GamesMarket.main.Main;
import GamesMarket.model.DAO.PostDAO;
import GamesMarket.model.Post;
import GamesMarket.model.ShopOwner;
import GamesMarket.model.User;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
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
import java.io.PipedOutputStream;
import java.net.URL;
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

    private Parent root;
    private Scene scene;
    private Stage stage;

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

        oldposts = forumController.retrievePosts();
        this.retrieveUserPosts();

        try {
            for (int i = 0; i < oldposts.size(); i++) {
                FXMLLoader fxmlLoader = new FXMLLoader();
                fxmlLoader.setLocation(Main.class.getResource("/GamesMarket/post.fxml"));
                AnchorPane anchorPane = fxmlLoader.load();

                PostGraphicController postGraphicController = fxmlLoader.getController();
                postGraphicController.setData(oldposts.get(i));
                postGraphicController.retrieveComments(oldposts.get(i));

                if (column == 1) {
                    column = 0;
                    row++;
                }

                postsGrid.add(anchorPane, column++, row);
                GridPane.setMargin(anchorPane, new Insets(10));
            }

            for (int i = 0; i < userOldPosts.size(); i++) {
                FXMLLoader fxmlLoader = new FXMLLoader();
                fxmlLoader.setLocation(Main.class.getResource("/GamesMarket/userPost.fxml"));
                AnchorPane anchorPane = fxmlLoader.load();

                UserPostGraphicController userPostGraphicController = fxmlLoader.getController();
                userPostGraphicController.setData(userOldPosts.get(i));
                userPostGraphicController.retrieveComments(userOldPosts.get(i));

                if (column == 1) {
                    column = 0;
                    row++;
                }

                userGrid.add(anchorPane, column++, row);
                GridPane.setMargin(anchorPane, new Insets(10));
            }

        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public void retrieveUserPosts() {
        userOldPosts = forumController.retrieveUserPosts();
    }

    public void addPostToGrid(Post post) {
        oldposts.add(post);

        try {
            FXMLLoader fxmlLoader = new FXMLLoader();
            fxmlLoader.setLocation(Main.class.getResource("/GamesMarket/post.fxml"));
            AnchorPane anchorPane = fxmlLoader.load();

            if (column == 1) {
                column = 0;
                row++;
            }

            PostGraphicController postGraphicController = fxmlLoader.getController();
            postGraphicController.setData(post);
            postsGrid.add(anchorPane, column++, row);
            GridPane.setMargin(anchorPane, new Insets(10));

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void addPostToUserGrid(Post post) {

        if (User.getInstance().isLoggedIn()) {
            try {
                FXMLLoader fxmlLoader = new FXMLLoader();
                fxmlLoader.setLocation(Main.class.getResource("/GamesMarket/userPost.fxml"));
                AnchorPane anchorPane = fxmlLoader.load();

                if (column == 1) {
                    column = 0;
                    row++;
                }

                UserPostGraphicController userPostGraphicController = fxmlLoader.getController();
                userPostGraphicController.setData(post);
                userGrid.add(anchorPane, column++, row);
                GridPane.setMargin(anchorPane, new Insets(10));

            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }


    public void postButton() {
        String username;
        String text = postText.getText();

        if (User.getInstance().isLoggedIn()) {
            username = User.getInstance().getUsername();
        } else {
            username = "unknown";
        }

        Post post = new Post();
        post.setUsername(username);
        post.setText(text);

        addPostToGrid(post);
        addPostToUserGrid(post);

        PostBean postBean = new PostBean();
        postBean.setUsername(username);
        postBean.setText(text);

        forumController.savePost(postBean);
    }

    public void signInButtonPressed() {
        try {

            Parent root = FXMLLoader.load(Main.class.getResource("/GamesMarket/login.fxml"));
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
            }

            this.retrieveUserPosts();

            for (int i = 0; i < userOldPosts.size(); i++) {
                this.addPostToUserGrid(userOldPosts.get(i));
            }

        } catch (Exception e) {
            e.printStackTrace();
            e.getCause();
        }

    }

    public void exchangeButtonPressed(ActionEvent event) {
        if (User.getInstance().isLoggedIn()) {
            this.exchangeButton(event);
        } else {
            this.signInButtonPressed();
        }
    }

    public void profileButtonPressed(ActionEvent event) {
        if (User.getInstance().isLoggedIn()) {
            this.profileButton(event);
        } else {
            this.signInButtonPressed();
        }
    }

}






