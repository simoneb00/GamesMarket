package GamesMarket.model.DAO;

import GamesMarket.DBConnection.DatabaseConnection;
import GamesMarket.model.Post;
import GamesMarket.model.ShopOwner;
import GamesMarket.model.User;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class PostDAO {

    public static List<Post> retrievePosts() {

        List<Post> posts = new ArrayList<>();

        Statement statement = null;
        Connection connection = null;
        String retrievePosts = "select * from posts;";


        try {
            connection = DatabaseConnection.getInstance().getConnection();
            statement = connection.createStatement();
            ResultSet result = statement.executeQuery(retrievePosts);

            while (result.next()) {
                Post post = new Post(
                        result.getString("username"),
                        result.getString("text")
                );

                posts.add(post);
            }

            result.close();
            if (statement != null)
                statement.close();

        } catch(SQLException e) {
            e.printStackTrace();
        }

        return posts;
    }

    public static void savePost(Post post) {
        Statement statement = null;
        Connection connection = null;
        String savePost = "insert into posts (username, text) values ( '" + post.getUsername() + "' , '" + post.getText() + "');";

        try {
            connection = DatabaseConnection.getInstance().getConnection();
            statement = connection.createStatement();
            statement.execute(savePost);

            if (statement != null)
                statement.close();

        } catch(SQLException e){
            e.printStackTrace();
        }
    }

    public static List<Post> retrieveUserPosts() {

        List<Post> posts = new ArrayList<>();
        String username = null;
        if (User.getInstance().isLoggedIn())
            username = User.getInstance().getUsername();
        else if (ShopOwner.getInstance().isLoggedIn())
            username = ShopOwner.getInstance().getEmail();

        Statement statement = null;
        Connection connection = null;
        String retrieveUserPosts = "select text from posts where username = '" + username + "';";

        try {
            connection = DatabaseConnection.getInstance().getConnection();
            statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(retrieveUserPosts);

            while (resultSet.next()) {
                Post post = new Post(username, resultSet.getString("text"));
                posts.add(post);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return posts;

    }

    public static void delete(Post post) {

        String username = post.getUsername();
        String text = post.getText();

        Statement statement = null;
        Connection connection = null;
        String deletePost = "delete from posts where username = '" + username + "' and text = '" + text + "';";

        try {
            connection = DatabaseConnection.getInstance().getConnection();
            statement = connection.createStatement();

            statement.execute(deletePost);

            if (statement != null)
                statement.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
