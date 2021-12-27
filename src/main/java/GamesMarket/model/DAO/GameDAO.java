package GamesMarket.model.DAO;

import GamesMarket.DBConnection.DatabaseConnection;
import GamesMarket.bean.GameBean;
import GamesMarket.model.Game;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class GameDAO {

    public List<Game> retrieveGames() {
        List<Game> games = new ArrayList<>();
        Connection connection = null;
        Statement statement = null;
        String retrieve = "select name, genre, description, platform, year from games;";

        try {
            connection = DatabaseConnection.getInstance().getConnection();
            statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(retrieve);

            while (resultSet.next()) {
                Game game = new Game();
                game.setName(resultSet.getString("name"));
                game.setGenre(resultSet.getString("genre"));
                game.setDescription(resultSet.getString("description"));
                game.setPlatform(resultSet.getString("platform"));
                game.setYear(resultSet.getString("year"));
                games.add(game);
            }

            if (statement != null)
                statement.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return games;
    }
}
