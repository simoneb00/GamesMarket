package gamesmarket.model.dao;

import gamesmarket.dbconnection.DatabaseConnection;
import gamesmarket.model.Comment;
import gamesmarket.model.Post;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CommentDAO {

    private CommentDAO() {
    }

    public static List<Comment> retrieveComments(Post post) throws SQLException {

        List<Comment> comments = new ArrayList<>();

        PreparedStatement preparedStatement = null;

        String retrieveComments = "select commUsername, comment from comments where username = ? and text = ?;";

        try {

            Connection connection = DatabaseConnection.getConnection();
            preparedStatement = connection.prepareStatement(retrieveComments);
            preparedStatement.setString(1, post.getUsername());
            preparedStatement.setString(2, post.getText());
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                Comment comment = new Comment();
                comment.setUsername(resultSet.getString("commUsername"));
                comment.setText(resultSet.getString("comment"));
                comments.add(comment);
            }

            resultSet.close();

        } finally {
            if (preparedStatement != null)
                preparedStatement.close();
        }

        return comments;
    }


    public static void saveComment(Comment comment, Post post) throws SQLException {

        String commUsername = comment.getUsername();
        String comm = comment.getText();
        String username = post.getUsername();
        String text = post.getText();

        PreparedStatement preparedStatement = null;

        String saveComment = "insert into comments (username, text, commUsername, comment) values (?, ?, ?, ?);";

        try {
            Connection connection = DatabaseConnection.getConnection();
            preparedStatement = connection.prepareStatement(saveComment);
            preparedStatement.setString(1, username);
            preparedStatement.setString(2, text);
            preparedStatement.setString(3, commUsername);
            preparedStatement.setString(4, comm);
            preparedStatement.execute();

        } finally {
            if (preparedStatement != null)
                preparedStatement.close();
        }
    }

    public static void delete(Post post) throws SQLException {
        String username = post.getUsername();
        String text = post.getText();

        PreparedStatement preparedStatement = null;

        String delete = "delete from comments where username = ? and text = ?;";

        try {
            Connection connection = DatabaseConnection.getConnection();
            preparedStatement = connection.prepareStatement(delete);
            preparedStatement.setString(1, username);
            preparedStatement.setString(2, text);
            preparedStatement.execute();

        } finally {
            if (preparedStatement != null)
                preparedStatement.close();
        }
    }
}
