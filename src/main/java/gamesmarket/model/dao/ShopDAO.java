package gamesmarket.model.dao;

import gamesmarket.dbconnection.DatabaseConnection;
import gamesmarket.model.Game;
import gamesmarket.model.ShopOwner;
import gamesmarket.model.ShopPost;

import java.io.*;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ShopDAO {

    private ShopDAO() {}

    public static List<ShopPost> retrieveShop() throws SQLException, IOException {
        List<ShopPost> posts = new ArrayList<>();

        File file = new File("image.jpg");
        String retrieve = "select shopName, game, platform, price from `games_for_sale` join shops where emailOwner = email";
        String retrievePhoto = "select image from games where name = ? and platform = ?";
        Connection connection = DatabaseConnection.getConnection();
        try (FileOutputStream fos = new FileOutputStream(file); PreparedStatement preparedStatement = connection.prepareStatement(retrievePhoto); Statement statement = connection.createStatement()) {

            ResultSet resultSet = statement.executeQuery(retrieve);

            while (resultSet.next()) {
                String name = resultSet.getString("shopName");
                String game = resultSet.getString("game");
                String platform = resultSet.getString("platform");
                double price = resultSet.getDouble("price");

                ShopPost shopPost = new ShopPost();
                shopPost.setShopName(name);
                shopPost.setPrice(price);
                shopPost.setGame(game + " - " + platform);

                file = new File(game + ".jpg");
                byte[] b;
                Blob blob;

                preparedStatement.setString(1, game);
                preparedStatement.setString(2, platform);

                ResultSet resultSet1 = preparedStatement.executeQuery();

                while (resultSet1.next()) {
                    blob = resultSet1.getBlob("image");
                    if (blob == null) {
                        break;
                    }
                    b = blob.getBytes(1, (int) blob.length());
                    fos.write(b);
                }

                shopPost.setImageFile(file);
                posts.add(shopPost);

                resultSet1.close();
            }

            resultSet.close();

        }

        return posts;
    }

    public static void createNewShop(String name, String address, String city, String country) throws SQLException {
        String owner = ShopOwner.getInstance().getEmail();
        PreparedStatement preparedStatement = null;
        String create = "insert into shops (email, shopName, address, city, country) values (?, ?, ?, ?, ?)";

        try {
            Connection connection = DatabaseConnection.getConnection();
            preparedStatement = connection.prepareStatement(create);
            preparedStatement.setString(1, owner);
            preparedStatement.setString(2, name);
            preparedStatement.setString(3, address);
            preparedStatement.setString(4, city);
            preparedStatement.setString(5, country);
            preparedStatement.executeUpdate();

        } finally {
            if (preparedStatement != null)
                preparedStatement.close();
        }
    }

    public static String retrieveShopName(String email) throws SQLException {
        String shopName = null;
        PreparedStatement preparedStatement = null;
        String retrieve = "select shopName from shops where email = ?";

        try {
            Connection connection = DatabaseConnection.getConnection();
            preparedStatement = connection.prepareStatement(retrieve);
            preparedStatement.setString(1, email);
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                shopName = resultSet.getString("shopName");
            }

            resultSet.close();

        } finally {
            if (preparedStatement != null)
                preparedStatement.close();
        }

        return shopName;
    }

    public static File retrievePhoto(String email) throws SQLException, IOException {
        PreparedStatement preparedStatement = null;
        File file = new File(email + "-shop.jpg");
        String retrieve = "select shopImg from shops where email = ?";

        try (FileOutputStream fos = new FileOutputStream(file)){
            Connection connection = DatabaseConnection.getConnection();
            preparedStatement = connection.prepareStatement(retrieve);
            preparedStatement.setString(1, email);
            ResultSet resultSet = preparedStatement.executeQuery();
            byte[] b;
            Blob blob;

            while (resultSet.next()) {
                blob = resultSet.getBlob("shopImg");
                if (blob == null) {
                    break;
                }
                b = blob.getBytes(1, (int) blob.length());
                fos.write(b);
            }

            resultSet.close();

        } finally {
            if (preparedStatement != null)
                preparedStatement.close();
        }

        return file;
    }

    public static void updatePhoto(String path) throws IOException, SQLException {
        String delete = "update shops set shopImg = NULL where email ='" + ShopOwner.getInstance().getEmail() + "';";
        String updatePhoto = "update shops set shopImg = ? where email = '" + ShopOwner.getInstance().getEmail() + "'";
        File image = new File(path);
        Connection connection = DatabaseConnection.getConnection();

        try (FileInputStream inputStream = new FileInputStream(image); Statement statement = connection.createStatement(); PreparedStatement preparedStatement = connection.prepareStatement(updatePhoto)) {
            preparedStatement.setBinaryStream(1, inputStream, (int) (image.length()));
            statement.execute(delete);
            preparedStatement.executeUpdate();
        }
    }

    public static List<Game> retrieveList(String email) throws SQLException {
        List<Game> games = new ArrayList<>();
        PreparedStatement preparedStatement = null;
        String retrieve = "select game, platform, price from `games_for_sale` where emailOwner = ?";

        try {
            Connection connection = DatabaseConnection.getConnection();
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

            resultSet.close();

        } finally {
            if (preparedStatement != null)
                preparedStatement.close();
        }

        return games;
    }

    public static void putForSale(String name, String platform, double price) throws SQLException {
        PreparedStatement preparedStatement = null;
        String add = "insert into `games_for_sale` (emailOwner, game, platform, price) values (?, ?, ?, ?)";

        try {
            Connection connection = DatabaseConnection.getConnection();
            preparedStatement = connection.prepareStatement(add);
            preparedStatement.setString(1, ShopOwner.getInstance().getEmail());
            preparedStatement.setString(2, name);
            preparedStatement.setString(3, platform);
            preparedStatement.setDouble(4, price);

            preparedStatement.executeUpdate();

        } finally {
            if (preparedStatement != null)
                preparedStatement.close();
        }
    }

    public static void removeGameFromSale(String name, String platform, double price) throws SQLException {
        PreparedStatement preparedStatement = null;
        String remove = "delete from `games_for_sale` where emailOwner = ? and game = ? and platform = ? and price = ?";

        try {
            Connection connection = DatabaseConnection.getConnection();
            preparedStatement = connection.prepareStatement(remove);
            preparedStatement.setString(1, ShopOwner.getInstance().getEmail());
            preparedStatement.setString(2, name);
            preparedStatement.setString(3, platform);
            preparedStatement.setDouble(4, price);

            preparedStatement.executeUpdate();
        } finally {
            if (preparedStatement != null)
                preparedStatement.close();
        }


    }

    public static List<String> retrieveAddress(String email) throws SQLException {
        List<String> completeAddress = new ArrayList<>();
        PreparedStatement preparedStatement = null;
        String retrieve = "select address, city, country from shops where email = ?";

        try {
            Connection connection = DatabaseConnection.getConnection();
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

        } finally {
            if (preparedStatement != null)
                preparedStatement.close();
        }

        return completeAddress;
    }
}
