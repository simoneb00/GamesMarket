package gamesmarket.model.dao;

import gamesmarket.dbconnection.DatabaseConnection;
import gamesmarket.exceptions.DuplicatedEmailException;
import gamesmarket.exceptions.DuplicatedUsernameException;
import gamesmarket.model.User;

import java.io.*;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserDAO {

    private UserDAO() {}

    public static void registerUser(String username, String email, String password, String firstName, String lastName) throws DuplicatedEmailException, DuplicatedUsernameException, SQLException {
        Statement statement = null;
        String register = "insert into user (username, password, firstname, lastname, email) values (" + "'" + username + "', '" + password + "', '" + firstName + "', '" + lastName + "', '" + email + "')";

        try {
            checkEmail(email);
            checkUsername(username);

            Connection connection = DatabaseConnection.getConnection();
            statement = connection.createStatement();
            statement.execute(register);

        } finally {
            if (statement != null)
                statement.close();
        }
    }

    public static void checkEmail(String email) throws DuplicatedEmailException, SQLException {
        Statement statement = null;
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

            result.close();

        } finally {
            if (statement != null)
                statement.close();
        }
    }

    public static void checkUsername(String username) throws DuplicatedUsernameException, SQLException{
        Statement statement = null;
        String verifyUsername = "select count(1) from user where username = '" + username + "'";

        try {
            Connection connection = DatabaseConnection.getConnection();
            statement = connection.createStatement();
            ResultSet result = statement.executeQuery(verifyUsername);

            while (result.next()) {
                if (result.getInt(1) == 1) {
                    throw new DuplicatedUsernameException();
                }
            }

        } finally {
            if (statement != null)
                statement.close();
        }
    }

    public static boolean validateLogin(String email, String password) throws SQLException {
        String verifyLogin = "SELECT count(1) FROM user WHERE email = '" + email + "' AND password = '" + password + "'";
        return checkLogin(verifyLogin);
    }

    public static boolean checkLogin(String query) throws SQLException{
        boolean returnValue = false;
        Statement statement = null;

        try {
            Connection connection = DatabaseConnection.getConnection();
            statement = connection.createStatement();
            ResultSet result = statement.executeQuery(query);

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

    public static List<String> retrieveUser(String email) throws SQLException {

        List<String> list = new ArrayList<>();
        Statement statement = null;
        String retrieveUser = "select username, password, firstName, lastName from user where email = '" + email + "'";

        try {
            Connection connection = DatabaseConnection.getConnection();
            statement = connection.createStatement();
            ResultSet result = statement.executeQuery(retrieveUser);

            while (result.next()) {
                list.add(result.getString("username"));
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


    public static List<String> retrieveContactInf(String username) throws SQLException {
        List<String> ci = new ArrayList<>();
        Statement statement = null;
        String retrieveCI = "select email, tel, address, country from contactinf where username = '" + username + "';";

        try {
            Connection connection = DatabaseConnection.getConnection();
            statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(retrieveCI);

            while (resultSet.next()) {
                ci.add(resultSet.getString("email"));
                ci.add(resultSet.getString("tel"));
                ci.add(resultSet.getString("address"));
                ci.add(resultSet.getString("country"));
            }

            resultSet.close();

        } finally {
            if (statement != null)
                statement.close();
        }

        return ci;
    }

    public static void updateCI(String email, String tel, String address, String country) throws SQLException {

        Statement statement = null;
        String deleteCI = "delete from contactinf where username = '" + User.getInstance().getUsername() + "';";
        String updateCI = "insert into contactinf (username, email, tel, address, country) values ('" + User.getInstance().getUsername() + "', '" + email + "', '" + tel + "', '" + address + "', '" + country + "');";

        try {
            Connection connection = DatabaseConnection.getConnection();
            statement = connection.createStatement();

            statement.execute(deleteCI);
            statement.execute(updateCI);

        } finally {
            if (statement != null)
                statement.close();
        }
    }


    public static String retrieveBio() throws SQLException {
        String bio = "";
        Statement statement = null;
        String retrieveBio = "select bio from bio where username = '" + User.getInstance().getUsername() + "';";

        try {
            Connection connection = DatabaseConnection.getConnection();
            statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(retrieveBio);

            while (resultSet.next()) {
                bio = resultSet.getString("bio");
            }

            resultSet.close();

        } finally {
            if (statement != null)
                statement.close();
        }

        return bio;
    }

    public static void saveBio(String bio) throws SQLException {
        PreparedStatement preparedStatement = null;
        PreparedStatement preparedStatement1 = null;
        String deleteBio = "delete from bio where username = ? ;";
        String saveBio = "insert into bio (username, bio) values (?, ?);";

        try {
            Connection connection = DatabaseConnection.getConnection();
            preparedStatement = connection.prepareStatement(deleteBio);
            preparedStatement.setString(1, User.getInstance().getUsername());
            if (bio != null)
                preparedStatement.execute();
            preparedStatement1 = connection.prepareStatement(saveBio);
            preparedStatement1.setString(1, User.getInstance().getUsername());
            preparedStatement1.setString(2, bio);
            preparedStatement1.execute();

        } finally {
            if (preparedStatement != null)
                preparedStatement.close();
            if (preparedStatement1 != null)
                preparedStatement1.close();
        }
    }

    public static void updateProfilePhoto(String path) throws IOException, SQLException {
        Statement statement = null;
        PreparedStatement preparedStatement = null;
        File image = new File(path);
        FileInputStream inputStream = new FileInputStream(image);
        String delete = "delete from profileimage where username = '" + User.getInstance().getUsername() + "';";
        String updatePhoto = "insert into profileimage (username, image) values (?, ?)";

        try {
            Connection connection = DatabaseConnection.getConnection();
            statement = connection.createStatement();
            preparedStatement = connection.prepareStatement(updatePhoto);
            preparedStatement.setString(1, User.getInstance().getUsername());
            preparedStatement.setBinaryStream(2, inputStream, (int) (image.length()));

            statement.execute(delete);
            preparedStatement.executeUpdate();

        } finally {
            inputStream.close();
            if (statement != null)
                statement.close();
            if (preparedStatement != null)
                preparedStatement.close();
        }
    }


    public static File retrieveProfilePhoto() throws IOException, SQLException {
        Statement statement = null;
        File file = new File("image" + User.getInstance().getUsername() + ".jpg");
        FileOutputStream fos = new FileOutputStream(file);
        String retrieve = "select * from profileimage where username = '" + User.getInstance().getUsername() + "';";

        try {
            Connection connection = DatabaseConnection.getConnection();
            statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(retrieve);
            byte[] b;
            Blob blob;

            while (resultSet.next()) {
                blob = resultSet.getBlob("image");
                b = blob.getBytes(1, (int) blob.length());
                fos.write(b);
                fos.close();
            }

            resultSet.close();

        } finally {
            if (statement != null)
                statement.close();
            fos.close();
        }

        return file;
    }

    private static List<String> retrieveList(String query) throws SQLException {
        List<String> list = new ArrayList<>();
        String game;
        String platform;
        String add;
        Statement statement = null;

        try {
            Connection connection = DatabaseConnection.getConnection();
            statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(query);

            while (resultSet.next()) {
                game = resultSet.getString("game");
                platform = resultSet.getString("platform");
                add = game + " - " + platform;
                list.add(add);
            }

            resultSet.close();

        } finally {
            if (statement != null)
                statement.close();
        }

        return list;
    }

    public static List<String> retrieveWishlist() throws SQLException {
        String retrieve = "select game, platform from wishlist where username = '" + User.getInstance().getUsername() + "';";
        return retrieveList(retrieve);
    }

    public static List<String> retrieveTradelist() throws SQLException {
        String retrieve = "select game, platform from tradelist where username = '" + User.getInstance().getUsername() + "';";
        return retrieveList(retrieve);
    }

    private static void updateList(String query, String name, String platform) throws SQLException {

        PreparedStatement preparedStatement = null;

        try {
            Connection connection = DatabaseConnection.getConnection();
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, User.getInstance().getUsername());
            preparedStatement.setString(2, name);
            preparedStatement.setString(3, platform);
            preparedStatement.executeUpdate();

        } finally {
            if (preparedStatement != null)
                preparedStatement.close();
        }
    }

    public static void addToTradelist(String name, String platform) throws SQLException {
        String add = "insert into tradelist (username, game, platform) values (?, ?, ?)";
        updateList(add, name, platform);
    }

    public static void addToWishlist(String name, String platform) throws SQLException {
        String add = "insert into wishlist (username, game, platform) values (?, ?, ?)";
        updateList(add, name, platform);
    }

    public static void removeFromTradelist(String name, String platform) throws SQLException {
        String remove = "delete from tradelist where username = ? and game = ? and platform = ?";
        updateList(remove, name, platform);
    }

    public static void removeFromWishlist(String name, String platform) throws SQLException {
        String remove = "delete from wishlist where username = ? and game = ? and platform = ?";
        updateList(remove, name, platform);
    }

}
