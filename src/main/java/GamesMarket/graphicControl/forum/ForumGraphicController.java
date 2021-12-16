



package GamesMarket.graphicControl.forum;


import GamesMarket.main.Main;
import GamesMarket.model.Post;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class ForumGraphicController implements Initializable {

    @FXML
    private GridPane postsGrid;

    @FXML
    private GridPane yourPostsGrid;

    private List<Post> posts = new ArrayList<>();


    private List<Post> getData() {
        List<Post> posts = new ArrayList<>();
        Post post;


        for (int i=0; i< 20; i++) {
            post = new Post();
            post.setText("Fifa 22 is a very bad game!");
            post.setUsername("simonebauco");
            posts.add(post);
        }

        return posts;
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        posts.addAll(getData());
        int column = 0;
        int row = 1;
        try {
            for (int i = 0; i < posts.size(); i++) {
                FXMLLoader fxmlLoader = new FXMLLoader();
                fxmlLoader.setLocation(Main.class.getResource("/GamesMarket/post.fxml"));
                AnchorPane anchorPane = fxmlLoader.load();

                PostGraphicController postGraphicController = fxmlLoader.getController();
                postGraphicController.setData(posts.get(i));

                postsGrid.add(anchorPane, column, row);
                GridPane.setMargin(anchorPane, new Insets(10));

            }
        } catch(IOException e) {
            e.printStackTrace();
        }
    }
}


