package GamesMarket.model.DAO;

import GamesMarket.DBConnection.DatabaseConnection;
import GamesMarket.exceptions.DuplicatedEmailException;
import GamesMarket.exceptions.DuplicatedUsernameException;
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

    public static void registerShopOwner(String email, String password, String firstName, String lastName) throws DuplicatedEmailException, SQLException {
        DatabaseConnection databaseConnection = DatabaseConnection.getInstance();
        Connection connection = databaseConnection.getConnection();

        String register = "insert into `shop-owner` (email, password, firstname, lastname) values (" + "'" + email + "', '" + password + "', '" + firstName + "', '" + lastName + "')";
        String verifyEmail = "select count(1) from user where email = '" + email + "'";
        String verifyEmail1 = "select count(1) from `shop-owner` where email = '" + email + "'";

        Statement statement = connection.createStatement();
        ResultSet result = statement.executeQuery(verifyEmail);

        while (result.next()) {
            if (result.getInt(1) == 1) {
                DuplicatedEmailException duplicatedEmailException = new DuplicatedEmailException();
                throw duplicatedEmailException;
            }
        }

        result = statement.executeQuery(verifyEmail1);

        while (result.next()) {
            if (result.getInt(1) == 1) {
                DuplicatedEmailException duplicatedEmailException = new DuplicatedEmailException();
                throw duplicatedEmailException;
            }
        }

        statement.execute(register);
    }

    public static boolean validateLogin(String email, String password) throws SQLException {
        boolean returnValue = false;
        Statement statement = null;
        Connection connection = null;
        String verifyLogin = "SELECT count(1) FROM `shop-owner` WHERE email = '" + email + "' AND password = '" + password + "'";


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


        return returnValue;
    }

    public static List<String> retrieveShopOwner(String email) throws SQLException {
        List<String> list = new ArrayList<>();
        Statement statement = null;
        Connection connection = null;
        String retrieveUser = "select password, firstName, lastName from `shop-owner` where email = '" + email + "'";


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


        return list;
    }

}
