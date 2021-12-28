package GamesMarket.model.DAO;

import GamesMarket.DBConnection.DatabaseConnection;
import GamesMarket.model.Game;
import GamesMarket.model.Shop;
import GamesMarket.model.ShopOwner;
import GamesMarket.model.User;

import java.io.*;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class ShopOwnerDAO {

    public boolean validateLogin(String email, String password) {
        boolean returnValue = false;
        Statement statement = null;
        Connection connection = null;
        String verifyLogin = "SELECT count(1) FROM `shop-owner` WHERE email = '" + email + "' AND password = '" + password + "'";

        try {
            connection = DatabaseConnection.getInstance().getConnection();
            statement = connection.createStatement();
            ResultSet result = statement.executeQuery(verifyLogin);

            while (result.next()) {
                if (result.getInt(1) == 1) {
                    returnValue = true;
                }
            }
            result.close();
            if (statement != null)
                statement.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return returnValue;
    }

    public List<String> retrieveShopOwner(String email) {
        List<String> list = new ArrayList<>();
        Statement statement = null;
        Connection connection = null;
        String retrieveUser = "select password, firstName, lastName from `shop-owner` where email = '" + email + "'";

        try {
            connection = DatabaseConnection.getInstance().getConnection();
            statement = connection.createStatement();
            ResultSet result = statement.executeQuery(retrieveUser);

            while (result.next()) {
                list.add(result.getString("password"));
                list.add(result.getString("firstName"));
                list.add(result.getString("lastName"));
            }

            result.close();
            if (statement != null)
                statement.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return list;
    }

    public String retrieveShopName(String email) {
        String shopName = null;
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        String retrieve = "select shopName from shops where email = ?";

        try {
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

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return shopName;
    }

    public File retrievePhoto(String email) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        File file = new File(email + "-shop.jpg");
        String retrieve = "select shopImg from shops where email = ?";

        try {
            connection = DatabaseConnection.getInstance().getConnection();
            preparedStatement = connection.prepareStatement(retrieve);
            preparedStatement.setString(1, email);
            ResultSet resultSet = preparedStatement.executeQuery();
            byte b[];
            Blob blob;
            FileOutputStream fos = new FileOutputStream(file);

            while(resultSet.next()) {
                blob = resultSet.getBlob("shopImg");
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

    public void updatePhoto(String path) {
        try {
            File image = new File(path);
            FileInputStream inputStream = new FileInputStream(image);
            String delete = "update shops set shopImg = NULL where email ='" + ShopOwner.getInstance().getEmail() + "';";
            String updatePhoto = "update shops set shopImg = ? where email = '" + ShopOwner.getInstance().getEmail() + "'";
            Connection connection = DatabaseConnection.getInstance().getConnection();
            Statement statement1 = connection.createStatement();
            PreparedStatement statement = connection.prepareStatement(updatePhoto);
            statement.setBinaryStream(1, (InputStream) inputStream, (int)(image.length()));

            statement1.execute(delete);
            statement.executeUpdate();

            if (statement != null)
                statement.close();
            if (statement1 != null)
               statement1.close();

        } catch (FileNotFoundException e){
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<Game> retrieveList() {
        List<Game> games = new ArrayList<>();
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        String retrieve = "select game, platform, price from `games_for_sale` where emailOwner = ?";

        try {
            connection = DatabaseConnection.getInstance().getConnection();
            preparedStatement = connection.prepareStatement(retrieve);
            preparedStatement.setString(1, ShopOwner.getInstance().getEmail());
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

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return games;
    }

    public void putForSale(String name, String platform, double price) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        String add = "insert into `games_for_sale` (emailOwner, game, platform, price) values (?, ?, ?, ?)";

        try {

            connection = DatabaseConnection.getInstance().getConnection();
            preparedStatement = connection.prepareStatement(add);
            preparedStatement.setString(1, ShopOwner.getInstance().getEmail());
            preparedStatement.setString(2, name);
            preparedStatement.setString(3, platform);
            preparedStatement.setDouble(4, price);

            preparedStatement.executeUpdate();

            if (preparedStatement != null)
                preparedStatement.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void removeGameFromSale(String name, String platform, double price) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        String remove = "delete from `games_for_sale` where emailOwner = ? and game = ? and platform = ? and price = ?";

        try {

            connection = DatabaseConnection.getInstance().getConnection();
            preparedStatement = connection.prepareStatement(remove);
            preparedStatement.setString(1, ShopOwner.getInstance().getEmail());
            preparedStatement.setString(2, name);
            preparedStatement.setString(3, platform);
            preparedStatement.setDouble(4, price);

            preparedStatement.executeUpdate();

            if (preparedStatement != null)
                preparedStatement.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
