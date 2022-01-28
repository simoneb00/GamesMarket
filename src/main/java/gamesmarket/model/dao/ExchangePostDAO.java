package gamesmarket.model.dao;

import gamesmarket.dbconnection.DatabaseConnection;
import gamesmarket.exceptions.ErrorMessage;
import gamesmarket.model.ExchangePost;
import gamesmarket.model.User;

import java.io.File;
import java.io.IOException;
import java.sql.*;
import java.util.*;

public class ExchangePostDAO {

    private ExchangePostDAO() {}

    public static List<ExchangePost> retrieveExchange() throws SQLException {

        List<ExchangePost> exchangePosts = new ArrayList<>();
        Statement statement = null;

        String query = "select distinct * from \n" +
                "\t\t\t\t(select t_other_user.username, t_other_user.game, t_other_user.platform\n" +
                "                from wishlist as w_current_user join tradelist as t_other_user\n" +
                "                where w_current_user.username = '" + User.getInstance().getUsername() + "' and\n" +
                "                w_current_user.username <> t_other_user.username and\n" +
                "                w_current_user.game = t_other_user.game and\n" +
                "                w_current_user.platform = t_other_user.platform) as GTG\n" +
                "                join\n" +
                "\t\t\t\t(select w_other_user.username as u, t_current_user.game as gameToGive, t_current_user.platform as platformToGive\n" +
                "                from wishlist as w_other_user join tradelist as t_current_user\n" +
                "\t\t\t\twhere t_current_user.username = '" + User.getInstance().getUsername() + "' and\n" +
                "                w_other_user.username <> t_current_user.username and\n" +
                "                t_current_user.game = w_other_user.game and\n" +
                "                t_current_user.platform = w_other_user.platform) as GTR\n" +
                "                \n" +
                "                on username = u\n" +
                "                order by username, game;";

        try {

            Connection connection = DatabaseConnection.getConnection();
            statement = connection.createStatement();

            ResultSet resultSet = statement.executeQuery(query);

            while (resultSet.next()) {
                ExchangePost exchangePost = new ExchangePost(
                        resultSet.getString("username"),
                        resultSet.getString("game"),
                        resultSet.getString("platform"),
                        resultSet.getString("gameToGive"),
                        resultSet.getString("platformToGive"),
                        null
                );

                File file = new File(resultSet.getString("game") + ".jpg");
                if (file.exists())
                    exchangePost.setImageFile(file);
                else {
                    try {
                        exchangePost.setImageFile(GameDAO.retrieveGamePhoto(resultSet.getString("game")));
                    } catch (IOException e) {
                        ErrorMessage.displayErrorMobile();
                    }
                }

                exchangePosts.add(exchangePost);
            }

            resultSet.close();

        } finally {
            if (statement != null)
                statement.close();
        }

        return exchangePosts;
    }
}
