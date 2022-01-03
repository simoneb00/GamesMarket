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

    public static boolean validateLogin(String email, String password) {
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

    public static List<String> retrieveShopOwner(String email) {
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

}
