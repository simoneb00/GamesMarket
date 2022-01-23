package gamesmarket.model.DAO;

import gamesmarket.dbconnection.DatabaseConnection;
import gamesmarket.model.Post;
import gamesmarket.model.ShopOwner;
import gamesmarket.model.User;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PostDAO {

    private PostDAO() {}

    public static List<Post> retrievePosts() throws SQLException{

        List<Post> posts = new ArrayList<>();

        Statement statement = null;
        Connection connection = null;
        String retrievePosts = "select * from posts;";



        connection = DatabaseConnection.getConnection();
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
        statement.close();

        return posts;
    }

    public static void savePost(Post post) throws SQLException{
        PreparedStatement preparedStatement = null;
        Connection connection = null;
        String savePost = "insert into posts (username, text) values (?, ?);";


        connection = DatabaseConnection.getConnection();
        preparedStatement = connection.prepareStatement(savePost);
        preparedStatement.setString(1, post.getUsername());
        preparedStatement.setString(2, post.getText());

        preparedStatement.execute();

        preparedStatement.close();
    }

    public static List<Post> retrieveUserPosts() throws SQLException{

        List<Post> posts = new ArrayList<>();
        String username = null;
        if (User.getInstance().isLoggedIn())
            username = User.getInstance().getUsername();
        else if (ShopOwner.getInstance().isLoggedIn())
            username = ShopOwner.getInstance().getEmail();

        Statement statement = null;
        Connection connection = null;
        String retrieveUserPosts = "select text from posts where username = '" + username + "';";


        connection = DatabaseConnection.getConnection();
        statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery(retrieveUserPosts);

        while (resultSet.next()) {
            Post post = new Post(username, resultSet.getString("text"));
            posts.add(post);
        }

        return posts;

    }

    public static void delete(Post post) throws SQLException{

        PreparedStatement preparedStatement = null;
        Connection connection = null;
        String deletePost = "delete from posts where username = ? and text = ?;";


        connection = DatabaseConnection.getConnection();
        preparedStatement = connection.prepareStatement(deletePost);

        preparedStatement.setString(1, post.getUsername());
        preparedStatement.setString(2, post.getText());

        preparedStatement.execute();

        preparedStatement.close();

    }
}
