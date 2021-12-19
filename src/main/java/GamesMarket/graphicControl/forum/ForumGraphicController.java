package GamesMarket.graphicControl.forum;

import GamesMarket.bean.PostBean;
import GamesMarket.control.ForumController;
import GamesMarket.main.Main;
import GamesMarket.model.Post;
import GamesMarket.model.ShopOwner;
import GamesMarket.model.User;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.*;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class ForumGraphicController implements Initializable {

    @FXML
    private GridPane postsGrid;
    @FXML
    private TextField postText;
    @FXML
    private Button signInButton;

    int column = 0;
    int row = 1;


    private List<Post> oldposts = new ArrayList<>();


    private List<Post> getData() {
        List<Post> posts = new ArrayList<>();
        Post post;

        post = new Post();
        post.setUsername("simoneb");
        post.setText("fifa 22 is bad");
        posts.add(post);

        post = new Post();
        post.setUsername("simoneb00");
        post.setText("fifa 21 is bad");
        posts.add(post);

        post = new Post();
        post.setUsername("simoneb01");
        post.setText("fifa 20 is bad");
        posts.add(post);

        post = new Post();
        post.setUsername("simoneb02");
        post.setText("fifa 19 is bad");
        posts.add(post);

        post = new Post();
        post.setUsername("simoneb03");
        post.setText("fifa 18 is bad");
        posts.add(post);

        post = new Post();
        post.setUsername("simoneb04");
        post.setText("fifa 17 is bad");
        posts.add(post);

        post = new Post();
        post.setUsername("simoneb05");
        post.setText("fifa 16 is bad");
        posts.add(post);

        post = new Post();
        post.setUsername("simoneb06");
        post.setText("fifa 15 is bad");
        posts.add(post);


        return posts;
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        if (User.getInstance().isLoggedIn() || ShopOwner.getInstance().isLoggedIn()) {
            signInButton.setVisible(false);
            signInButton.isDisabled();
        }

        oldposts.addAll(getData());
        try {
            for (int i = 0; i < oldposts.size(); i++) {
                FXMLLoader fxmlLoader = new FXMLLoader();
                fxmlLoader.setLocation(Main.class.getResource("/GamesMarket/post.fxml"));
                AnchorPane anchorPane = fxmlLoader.load();

                PostGraphicController postGraphicController = fxmlLoader.getController();
                postGraphicController.setData(oldposts.get(i));

                if (column == 1) {
                    column = 0;
                    row++;
                }

                postsGrid.add(anchorPane, column++, row);
                GridPane.setMargin(anchorPane, new Insets(10));

            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }




    public void showData(List<Post> posts) {
        try {
            for (int i = 0; i < posts.size(); i++) {
                FXMLLoader fxmlLoader = new FXMLLoader();
                fxmlLoader.setLocation(Main.class.getResource("/GamesMarket/post.fxml"));
                AnchorPane anchorPane = fxmlLoader.load();

                PostGraphicController postGraphicController = fxmlLoader.getController();
                postGraphicController.setData(posts.get(i));
                oldposts.add(posts.get(i));

                if (column == 1) {
                    column = 0;
                    row++;
                }

                postsGrid.add(anchorPane, column++, row);
                GridPane.setMargin(anchorPane, new Insets(10));

            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

/*
    public void addPostToGrid(Post post) {
        posts.add(post);

        try {
            FXMLLoader fxmlLoader = new FXMLLoader();
            fxmlLoader.setLocation(Main.class.getResource("/GamesMarket/post.fxml"));
            AnchorPane anchorPane = fxmlLoader.load();

            if (column == 1) {
                column = 0;
                row++;
            }

            postsGrid.add(anchorPane, column++, row);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


 */

    public void postButton() {

        Post post = new Post();
        post.setText(postText.getText());
        post.setUsername("simonebauco00");

        List<Post> posts = new ArrayList<>();
        posts.add(post);

        this.showData(posts);

        /*
        String text = postText.getText();

        PostBean postBean = new PostBean();
        postBean.setText(text);
        postBean.setUsername(User.getInstance().getEmailAddress());

        ForumController fc = new ForumController();
        fc.postButton(postBean);

         */
    }

}






