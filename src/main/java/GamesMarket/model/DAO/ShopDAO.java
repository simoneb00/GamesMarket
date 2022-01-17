package GamesMarket.model.DAO;

import GamesMarket.DBConnection.DatabaseConnection;
import GamesMarket.model.Game;
import GamesMarket.model.ShopOwner;

import java.io.*;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ShopDAO {

    public static void createNewShop(String name, String address, String city, String country) throws SQLException {
        String owner = ShopOwner.getInstance().getEmail();
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        String create = "insert into shops (email, shopName, address, city, country) values (?, ?, ?, ?, ?)";


        connection = DatabaseConnection.getInstance().getConnection();
        preparedStatement = connection.prepareStatement(create);
        preparedStatement.setString(1, owner);
        preparedStatement.setString(2, name);
        preparedStatement.setString(3, address);
        preparedStatement.setString(4, city);
        preparedStatement.setString(5, country);
        preparedStatement.executeUpdate();

        if (preparedStatement != null)
            preparedStatement.close();


    }

    public static String retrieveShopName(String email) throws SQLException {
        String shopName = null;
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        String retrieve = "select shopName from shops where email = ?";


        connection = DatabaseConnection.getInstance().getConnection();
        preparedStatement = connection.prepareStatement(retrieve);
        preparedStatement.setString(1, email);
        ResultSet resultSet = preparedStatement.executeQuery();

        while (resultSet.next()) {
            shopName = resultSet.getString("shopName");
        }

        if (preparedStatement != null)
            preparedStatement.close();
        resultSet.close();


        return shopName;
    }

    public static File retrievePhoto(String email) throws SQLException, IOException {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        File file = new File(email + "-shop.jpg");
        String retrieve = "select shopImg from shops where email = ?";


        connection = DatabaseConnection.getInstance().getConnection();
        preparedStatement = connection.prepareStatement(retrieve);
        preparedStatement.setString(1, email);
        ResultSet resultSet = preparedStatement.executeQuery();
        byte b[];
        Blob blob;
        FileOutputStream fos = new FileOutputStream(file);

        while (resultSet.next()) {
            blob = resultSet.getBlob("shopImg");
            if (blob == null) {
                break;
            }
            b = blob.getBytes(1, (int) blob.length());
            fos.write(b);
        }

        resultSet.close();
        if (preparedStatement != null)
            preparedStatement.close();
        fos.close();


        return file;
    }

    public static void updatePhoto(String path) throws FileNotFoundException, SQLException {

        File image = new File(path);
        FileInputStream inputStream = new FileInputStream(image);
        String delete = "update shops set shopImg = NULL where email ='" + ShopOwner.getInstance().getEmail() + "';";
        String updatePhoto = "update shops set shopImg = ? where email = '" + ShopOwner.getInstance().getEmail() + "'";
        Connection connection = DatabaseConnection.getInstance().getConnection();
        Statement statement1 = connection.createStatement();
        PreparedStatement statement = connection.prepareStatement(updatePhoto);
        statement.setBinaryStream(1, (InputStream) inputStream, (int) (image.length()));

        statement1.execute(delete);
        statement.executeUpdate();

        if (statement != null)
            statement.close();
        if (statement1 != null)
            statement1.close();
    }

    public static List<Game> retrieveList(String email) throws SQLException {
        List<Game> games = new ArrayList<>();
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        String retrieve = "select game, platform, price from `games_for_sale` where emailOwner = ?";

        connection = DatabaseConnection.getInstance().getConnection();
        preparedStatement = connection.prepareStatement(retrieve);
        preparedStatement.setString(1, email);
        ResultSet resultSet = preparedStatement.executeQuery();

        while (resultSet.next()) {
            Game game = new Game();
            game.setName(resultSet.getString("game"));
            game.setPlatform(resultSet.getString("platform"));
            game.setPrice(resultSet.getDouble("price"));
            games.add(game);
        }

        if (preparedStatement != null)
            preparedStatement.close();
        resultSet.close();


        return games;
    }

    public static void putForSale(String name, String platform, double price) throws SQLException {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        String add = "insert into `games_for_sale` (emailOwner, game, platform, price) values (?, ?, ?, ?)";


        connection = DatabaseConnection.getInstance().getConnection();
        preparedStatement = connection.prepareStatement(add);
        preparedStatement.setString(1, ShopOwner.getInstance().getEmail());
        preparedStatement.setString(2, name);
        preparedStatement.setString(3, platform);
        preparedStatement.setDouble(4, price);

        preparedStatement.executeUpdate();

        if (preparedStatement != null)
            preparedStatement.close();


    }

    public static void removeGameFromSale(String name, String platform, double price) throws SQLException {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        String remove = "delete from `games_for_sale` where emailOwner = ? and game = ? and platform = ? and price = ?";


        connection = DatabaseConnection.getInstance().getConnection();
        preparedStatement = connection.prepareStatement(remove);
        preparedStatement.setString(1, ShopOwner.getInstance().getEmail());
        preparedStatement.setString(2, name);
        preparedStatement.setString(3, platform);
        preparedStatement.setDouble(4, price);

        preparedStatement.executeUpdate();

        if (preparedStatement != null)
            preparedStatement.close();


    }

    public static List<String> retrieveAddress(String email) throws SQLException {
        List<String> completeAddress = new ArrayList<>();
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        String retrieve = "select address, city, country from shops where email = ?";


        connection = DatabaseConnection.getInstance().getConnection();
        preparedStatement = connection.prepareStatement(retrieve);
        preparedStatement.setString(1, email);
        ResultSet resultSet = preparedStatement.executeQuery();

        while (resultSet.next()) {
            String address = resultSet.getString("address");
            String city = resultSet.getString("city");
            String country = resultSet.getString("country");

            completeAddress.add(address);
            completeAddress.add(city);
            completeAddress.add(country);
        }

        resultSet.close();
        if (preparedStatement != null)
            preparedStatement.close();


        return completeAddress;
    }
}
