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

        try {
            UserDAO.checkEmail(email);

            Connection connection = DatabaseConnection.getConnection();
            statement = connection.createStatement();
            statement.execute(register);

        } finally {
            if (statement != null)
                statement.close();
        }
    }

    public static boolean validateLogin(String email, String password) throws SQLException {
        String verifyLogin = "SELECT count(1) FROM `shop-owner` WHERE email = '" + email + "' AND password = '" + password + "'";
        return UserDAO.checkLogin(verifyLogin);
    }

    public static List<String> retrieveShopOwner(String email) throws SQLException {
        List<String> list = new ArrayList<>();
        String retrieveUser = "select password, firstName, lastName from `shop-owner` where email = ?";
        PreparedStatement preparedStatement = null;

        try {
            Connection connection = DatabaseConnection.getConnection();
            preparedStatement = connection.prepareStatement(retrieveUser);
            preparedStatement.setString(1, email);
            ResultSet result = preparedStatement.executeQuery();

            while (result.next()) {
                list.add(result.getString("password"));
                list.add(result.getString("firstName"));
                list.add(result.getString("lastName"));
            }

            result.close();

        } finally {
            if (preparedStatement != null)
                preparedStatement.close();
        }

        return list;
    }

}
