package GamesMarket.model.DAO;

import GamesMarket.dbconnection.DatabaseConnection;
import GamesMarket.model.ShopPost;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ShopPostDAO {

    public static List<ShopPost> retrieveShop() throws SQLException, IOException {
        List<ShopPost> posts = new ArrayList<>();
        Connection connection = null;
        Statement statement = null;
        PreparedStatement preparedStatement = null;

        String retrieve = "select shopName, game, platform, price from `games_for_sale` join shops where emailOwner = email";
        String retrievePhoto = "select image from games where name = ? and platform = ?";


        connection = DatabaseConnection.getConnection();
        statement = connection.createStatement();
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

            File file = new File(game + ".jpg");
            byte b[];
            Blob blob;
            FileOutputStream fos = new FileOutputStream(file);

            preparedStatement = connection.prepareStatement(retrievePhoto);
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
        if (statement != null)
            statement.close();
        if (preparedStatement != null)
            preparedStatement.close();


        return posts;
    }
}
