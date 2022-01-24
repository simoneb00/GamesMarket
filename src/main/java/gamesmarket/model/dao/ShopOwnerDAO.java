package gamesmarket.model.dao;

import gamesmarket.dbconnection.DatabaseConnection;
import gamesmarket.exceptions.DuplicatedEmailException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;


public class ShopOwnerDAO {

    private ShopOwnerDAO() {}

    public static void registerShopOwner(String email, String password, String firstName, String lastName) throws DuplicatedEmailException, SQLException {
        Connection connection = DatabaseConnection.getConnection();

        String register = "insert into `shop-owner` (email, password, firstname, lastname) values (" + "'" + email + "', '" + password + "', '" + firstName + "', '" + lastName + "')";
        String verifyEmail = "select count(1) from user where email = '" + email + "'";
        String verifyEmail1 = "select count(1) from `shop-owner` where email = '" + email + "'";

        Statement statement = connection.createStatement();
        ResultSet result = statement.executeQuery(verifyEmail);

        while (result.next()) {
            if (result.getInt(1) == 1) {
                throw new DuplicatedEmailException();
            }
        }

        result = statement.executeQuery(verifyEmail1);

        while (result.next()) {
            if (result.getInt(1) == 1) {
                throw new DuplicatedEmailException();
            }
        }

        statement.execute(register);
    }

    public static boolean validateLogin(String email, String password) throws SQLException {
        boolean returnValue = false;
        Connection connection = DatabaseConnection.getConnection();
        Statement statement = connection.createStatement();
        String verifyLogin = "SELECT count(1) FROM `shop-owner` WHERE email = '" + email + "' AND password = '" + password + "'";

        ResultSet result = statement.executeQuery(verifyLogin);

        while (result.next()) {
            if (result.getInt(1) == 1) {
                returnValue = true;
            }
        }
        result.close();
        statement.close();


        return returnValue;
    }

    public static List<String> retrieveShopOwner(String email) throws SQLException {
        List<String> list = new ArrayList<>();
        Connection connection = DatabaseConnection.getConnection();
        Statement statement = connection.createStatement();
        String retrieveUser = "select password, firstName, lastName from `shop-owner` where email = '" + email + "'";

        ResultSet result = statement.executeQuery(retrieveUser);

        while (result.next()) {
            list.add(result.getString("password"));
            list.add(result.getString("firstName"));
            list.add(result.getString("lastName"));
        }

        result.close();
        statement.close();


        return list;
    }

}
