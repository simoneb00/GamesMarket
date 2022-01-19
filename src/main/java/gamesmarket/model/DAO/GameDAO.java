package gamesmarket.model.DAO;

import gamesmarket.dbconnection.DatabaseConnection;
import gamesmarket.model.Game;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;


public class GameDAO {


    public static List<Game> retrieveGames() throws SQLException{
        List<Game> games = new ArrayList<>();
        Connection connection = null;
        Statement statement = null;
        String retrieve = "select name, genre, description, platform, year from games;";


        connection = DatabaseConnection.getConnection();
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



        return games;
    }

    public static File retrieveGamePhoto(String name) throws SQLException, IOException {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        File file = new File(name + ".jpg");
        String retrieve = "select image from games where name = ?";


        connection = DatabaseConnection.getConnection();
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
        preparedStatement.close();
        fos.close();



        return file;
    }
}
