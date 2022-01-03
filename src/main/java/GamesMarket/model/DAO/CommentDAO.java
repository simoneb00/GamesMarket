package GamesMarket.model.DAO;

import GamesMarket.DBConnection.DatabaseConnection;
import GamesMarket.model.Comment;
import GamesMarket.model.Post;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class CommentDAO {

    public static List<Comment> retrieveComments(Post post) {

        List<Comment> comments = new ArrayList<>();

        String username = post.getUsername();
        String text = post.getText();

        Statement statement = null;
        Connection connection = null;
        String retrieveComments = "select commUsername, comment from comments where username ='" + username + "' and text = '" + text + "';";


        try {
            connection = DatabaseConnection.getInstance().getConnection();
            statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(retrieveComments);

            while (resultSet.next()) {
                Comment comment = new Comment();
                comment.setUsername(resultSet.getString("commUsername"));
                comment.setText(resultSet.getString("comment"));
                comments.add(comment);
            }

            resultSet.close();
            if (statement != null)
                statement.close();

        } catch(SQLException e) {
            e.printStackTrace();
        }

        return comments;
    }


    public static void saveComment(Comment comment, Post post) {

        String commUsername = comment.getUsername();
        String comm = comment.getText();
        String username = post.getUsername();
        String text = post.getText();

        Connection connection = null;
        Statement statement = null;

        String saveComment = "insert into comments (username, text, commUsername, comment) values ('" + username + "', '" + text + "', '" + commUsername + "', '" + comm + "');";

        try {
            connection = DatabaseConnection.getInstance().getConnection();
            statement = connection.createStatement();
            statement.execute(saveComment);

            if (statement != null)
                statement.close();

        } catch(SQLException e) {
            e.printStackTrace();
        }
    }

    public static void delete(Post post) {
        String username = post.getUsername();
        String text = post.getText();

        Connection connection = null;
        Statement statement = null;

        String delete = "delete from comments where username = '" + username + "' and text = '" + text + "';";

        try {
            connection = DatabaseConnection.getInstance().getConnection();
            statement = connection.createStatement();
            statement.execute(delete);

            if (statement != null)
                statement.close();

        } catch(SQLException e) {
            e.printStackTrace();
        }
    }
}
