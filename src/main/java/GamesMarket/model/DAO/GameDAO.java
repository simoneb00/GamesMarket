package GamesMarket.model.DAO;

import GamesMarket.DBConnection.DatabaseConnection;
import GamesMarket.bean.GameBean;
import GamesMarket.model.Game;
import GamesMarket.model.User;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

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

    public File retrieveGamePhoto(String name) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        Random random = new Random();
        File file = new File(name + ".jpg");
        String retrieve = "select image from games where name = ?";

        try {
            connection = DatabaseConnection.getInstance().getConnection();
            preparedStatement = connection.prepareStatement(retrieve);
            preparedStatement.setString(1, name);
            ResultSet resultSet = preparedStatement.executeQuery();
            byte b[];
            Blob blob;
            FileOutputStream fos = new FileOutputStream(file);

            while(resultSet.next()) {
                blob = resultSet.getBlob("image");
                if (blob == null) {
                    break;
                }
                b = blob.getBytes(1, (int)blob.length());
                fos.write(b);
            }

            resultSet.close();
            if (preparedStatement != null)
                preparedStatement.close();
            fos.close();

        } catch (SQLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return file;
    }
}
