package gamesmarket.model.dao;

import gamesmarket.dbconnection.DatabaseConnection;
import gamesmarket.model.Game;

import java.io.*;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;


public class GameDAO {

    private GameDAO() {}

    public static List<Game> retrieveGames() throws SQLException{
        List<Game> games = new ArrayList<>();

        Statement statement = null;
        String retrieve = "select name, genre, description, platform, year from games;";

        try {

            Connection connection = DatabaseConnection.getConnection();
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

        } finally {
            if (statement != null)
                statement.close();
        }

        return games;
    }

    public static File retrieveGamePhoto(String name) throws SQLException, IOException {
        File file = new File(name + ".jpg");
        String retrieve = "select image from games where name = ?";
        Connection connection = DatabaseConnection.getConnection();

        try (FileOutputStream fos = new FileOutputStream(file); PreparedStatement preparedStatement = connection.prepareStatement(retrieve)) {
            preparedStatement.setString(1, name);
            ResultSet resultSet = preparedStatement.executeQuery();

            Blob blob;
            byte[] b;

            while (resultSet.next()) {
                blob = resultSet.getBlob("image");
                if (blob == null) {
                    break;
                }
                b = blob.getBytes(1, (int) blob.length());
                fos.write(b);
            }

            resultSet.close();

        }

        return file;
    }
}
