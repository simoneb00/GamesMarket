package gamesmarket.model.dao;

import gamesmarket.dbconnection.DatabaseConnection;
import gamesmarket.exceptions.DuplicatedEmailException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;


public class ShopOwnerDAO {

    private ShopOwnerDAO() {}

    public static void registerShopOwner(String email, String password, String firstName, String lastName) throws DuplicatedEmailException, SQLException {
        Statement statement = null;
        String register = "insert into `shop-owner` (email, password, firstname, lastname) values (" + "'" + email + "', '" + password + "', '" + firstName + "', '" + lastName + "')";
        String verifyEmail = "select count(1) from user where email = '" + email + "'";
        String verifyEmail1 = "select count(1) from `shop-owner` where email = '" + email + "'";

        try {
            Connection connection = DatabaseConnection.getConnection();
            statement = connection.createStatement();
            ResultSet result = statement.executeQuery(verifyEmail);

            while (result.next()) {
                if (result.getInt(1) == 1) {
                    throw new DuplicatedEmailException();
                }
            }

            result.close();
            result = statement.executeQuery(verifyEmail1);

            while (result.next()) {
                if (result.getInt(1) == 1) {
                    throw new DuplicatedEmailException();
                }
            }

            statement.execute(register);

        } finally {
            if (statement != null)
                statement.close();
        }
    }

    public static boolean validateLogin(String email, String password) throws SQLException {
        boolean returnValue = false;
        Statement statement = null;
        String verifyLogin = "SELECT count(1) FROM `shop-owner` WHERE email = '" + email + "' AND password = '" + password + "'";

        try {
            Connection connection = DatabaseConnection.getConnection();
            statement = connection.createStatement();
            ResultSet result = statement.executeQuery(verifyLogin);

            while (result.next()) {
                if (result.getInt(1) == 1) {
                    returnValue = true;
                }
            }
            result.close();

        } finally {
            if (statement != null)
                statement.close();
        }

        return returnValue;
    }

    public static List<String> retrieveShopOwner(String email) throws SQLException {
        List<String> list = new ArrayList<>();
        Statement statement = null;
        String retrieveUser = "select password, firstName, lastName from `shop-owner` where email = '" + email + "'";

        try {
            Connection connection = DatabaseConnection.getConnection();
            statement = connection.createStatement();
            ResultSet result = statement.executeQuery(retrieveUser);

            while (result.next()) {
                list.add(result.getString("password"));
                list.add(result.getString("firstName"));
                list.add(result.getString("lastName"));
            }

            result.close();

        } finally {
            if (statement != null)
                statement.close();
        }

        return list;
    }

}
