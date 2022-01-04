package GamesMarket.model.DAO;

import GamesMarket.DBConnection.DatabaseConnection;
import GamesMarket.model.ExchangePost;
import GamesMarket.model.User;

import java.io.File;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class ExchangePostDAO {

    public static List<ExchangePost> retrieveExchange() {
        List<ExchangePost> exchangePosts = new ArrayList<>();
        Connection connection = null;
        Statement statement = null;
        Statement statement1 = null;


        // this query retrieve the games the current user wants to receive
        String retrieveGamesToReceive = "-- find games current user wants to receive\n" +
                "select t_other_user.username, t_other_user.game, t_other_user.platform\n" +
                "from wishlist as w_current_user join tradelist as t_other_user\n" +
                "where w_current_user.username = '" + User.getInstance().getUsername() + "' and \n" +
                "w_current_user.username <> t_other_user.username and\n" +
                "w_current_user.game = t_other_user.game and \n" +
                "w_current_user.platform = t_other_user.platform;";

        // this query retrieves the games (if they exists) other users want to receive in exchange for the previous game
        String retrieveGamesToGive = "-- find games other user wants to receive for that (those) game (games)\n" +
                "select w_other_user.username, t_current_user.game, t_current_user.platform\n" +
                "from wishlist as w_other_user join tradelist as t_current_user\n" +
                "where t_current_user.username = '" + User.getInstance().getUsername() + "' and\n" +
                "w_other_user.username <> t_current_user.username and\n" +
                "t_current_user.game = w_other_user.game and\n" +
                "t_current_user.platform = w_other_user.platform;";


        try {

            connection = DatabaseConnection.getInstance().getConnection();
            statement = connection.createStatement();
            statement1 = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(retrieveGamesToReceive);

            while(resultSet.next()) {

                String username = resultSet.getString("username");
                String game = resultSet.getString("game");
                String platform = resultSet.getString("platform");

                ExchangePost exchangePost = new ExchangePost("", "", "", "", "", null);

                exchangePost.setGame(game);
                exchangePost.setPlatform(platform);
                exchangePost.setUsername(username);

                ResultSet resultSet1 = statement1.executeQuery(retrieveGamesToGive);

                while (resultSet1.next()) {
                    String gameToGive = resultSet1.getString("game");
                    String platformToGive = resultSet1.getString("platform");

                    if (!gameToGive.isEmpty() && !platformToGive.isEmpty()) {
                        exchangePost.setGameToGive(gameToGive);
                        exchangePost.setPlatformGameToGive(platformToGive);

                        if (!exchangePosts.contains(exchangePost)) {
                            File file = new File(game + ".jpg");
                            if (file.exists())
                                exchangePost.setImageFile(file);
                            else
                                exchangePost.setImageFile(GameDAO.retrieveGamePhoto(game));


                            if (!exchangePosts.contains(exchangePost) && !gameToGive.isEmpty() && !platformToGive.isEmpty())
                                exchangePosts.add(exchangePost);
                        }
                    }
                }

                resultSet1.close();
            }

            resultSet.close();

            if (statement != null)
                statement.close();
            if (statement1 != null)
                statement1.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }


        return exchangePosts;
    }
}
