package gamesmarket.model.DAO;

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
        Connection connection = DatabaseConnection.getConnection();

        String register = "insert into user (username, password, firstname, lastname, email) values (" + "'" + username + "', '" + password + "', '" + firstName + "', '" + lastName + "', '" + email + "')";
        String verifyEmail = "select count(1) from user where email = '" + email + "'";
        String verifyEmail1 = "select count(1) from `shop-owner` where email = '" + email + "'";
        String verifyUsername = "select count(1) from user where username = '" + username + "'";

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

        result = statement.executeQuery(verifyUsername);

        while (result.next()) {
            if (result.getInt(1) == 1) {
                throw new DuplicatedUsernameException();
            }
        }

        statement.execute(register);


    }

    public static boolean validateLogin(String email, String password) throws SQLException {
        boolean returnValue = false;
        Statement statement = null;
        Connection connection = null;
        String verifyLogin = "SELECT count(1) FROM user WHERE email = '" + email + "' AND password = '" + password + "'";


        connection = DatabaseConnection.getConnection();
        statement = connection.createStatement();
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

    public static List<String> retrieveUser(String email) throws SQLException {

        List<String> list = new ArrayList<>();
        Statement statement = null;
        Connection connection = null;
        String retrieveUser = "select username, password, firstName, lastName from user where email = '" + email + "'";


        connection = DatabaseConnection.getConnection();
        statement = connection.createStatement();
        ResultSet result = statement.executeQuery(retrieveUser);

        while (result.next()) {
            list.add(result.getString("username"));
            list.add(result.getString("password"));
            list.add(result.getString("firstName"));
            list.add(result.getString("lastName"));
        }

        result.close();
        statement.close();


        return list;
    }


    public static List<String> retrieveContactInf(String username) throws SQLException {
        List<String> ci = new ArrayList<>();

        Connection connection = null;
        Statement statement = null;
        String retrieveCI = "select email, tel, address, country from contactinf where username = '" + username + "';";


        connection = DatabaseConnection.getConnection();
        statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery(retrieveCI);

        while (resultSet.next()) {
            ci.add(resultSet.getString("email"));
            ci.add(resultSet.getString("tel"));
            ci.add(resultSet.getString("address"));
            ci.add(resultSet.getString("country"));
        }

        resultSet.close();
        statement.close();


        return ci;
    }

    public static void updateCI(String email, String tel, String address, String country) throws SQLException {

        Connection connection = null;
        Statement statement = null;
        String deleteCI = "delete from contactinf where username = '" + User.getInstance().getUsername() + "';";
        String updateCI = "insert into contactinf (username, email, tel, address, country) values ('" + User.getInstance().getUsername() + "', '" + email + "', '" + tel + "', '" + address + "', '" + country + "');";


        connection = DatabaseConnection.getConnection();
        statement = connection.createStatement();

        statement.execute(deleteCI);
        statement.execute(updateCI);

        statement.close();


    }


    public static String retrieveBio() throws SQLException {
        String bio = "";
        Connection connection = null;
        Statement statement = null;
        String retrieveBio = "select bio from bio where username = '" + User.getInstance().getUsername() + "';";

        connection = DatabaseConnection.getConnection();
        statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery(retrieveBio);

        while (resultSet.next()) {
            bio = resultSet.getString("bio");
        }

        resultSet.close();
        statement.close();


        return bio;
    }

    public static void saveBio(String bio) throws SQLException {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        PreparedStatement preparedStatement1 = null;
        String deleteBio = "delete from bio where username = ? ;";
        String saveBio = "insert into bio (username, bio) values (?, ?);";


        connection = DatabaseConnection.getConnection();
        preparedStatement = connection.prepareStatement(deleteBio);
        preparedStatement.setString(1, User.getInstance().getUsername());
        if (bio != null)
            preparedStatement.execute();
        preparedStatement1 = connection.prepareStatement(saveBio);
        preparedStatement1.setString(1, User.getInstance().getUsername());
        preparedStatement1.setString(2, bio);
        preparedStatement1.execute();

        preparedStatement1.close();

        preparedStatement.close();


    }

    public static void updateProfilePhoto(String path) throws FileNotFoundException, SQLException {

        File image = new File(path);
        FileInputStream inputStream = new FileInputStream(image);
        String delete = "delete from profileimage where username = '" + User.getInstance().getUsername() + "';";
        String updatePhoto = "insert into profileimage (username, image) values (?, ?)";
        Connection connection = DatabaseConnection.getConnection();
        Statement statement1 = connection.createStatement();
        PreparedStatement statement = connection.prepareStatement(updatePhoto);
        statement.setString(1, User.getInstance().getUsername());
        statement.setBinaryStream(2, (InputStream) inputStream, (int) (image.length()));

        statement1.execute(delete);
        statement.executeUpdate();

        statement.close();


    }


    public static File retrieveProfilePhoto() throws IOException, SQLException {
        Connection connection = null;
        Statement statement = null;
        File file = new File("image" + User.getInstance().getUsername() + ".jpg");
        String retrieve = "select * from profileimage where username = '" + User.getInstance().getUsername() + "';";


        connection = DatabaseConnection.getConnection();
        statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery(retrieve);
        byte b[];
        Blob blob;
        FileOutputStream fos = new FileOutputStream(file);

        while (resultSet.next()) {
            blob = resultSet.getBlob("image");
            b = blob.getBytes(1, (int) blob.length());
            fos.write(b);
        }

        resultSet.close();
        statement.close();
        fos.close();


        return file;
    }

    public static List<String> retrieveWishlist() throws SQLException {
        List<String> list = new ArrayList<>();
        String game, platform, add = null;

        Connection connection = null;
        Statement statement = null;
        String retrieve = "select game, platform from wishlist where username = '" + User.getInstance().getUsername() + "';";


        connection = DatabaseConnection.getConnection();
        statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery(retrieve);

        while (resultSet.next()) {
            game = resultSet.getString("game");
            platform = resultSet.getString("platform");
            add = game + " - " + platform;
            list.add(add);
        }

        statement.close();

        resultSet.close();


        return list;
    }

    public static List<String> retrieveTradelist() throws SQLException {
        List<String> list = new ArrayList<>();
        String game, platform, add = null;

        Connection connection = null;
        Statement statement = null;
        String retrieve = "select game, platform from tradelist where username = '" + User.getInstance().getUsername() + "';";


        connection = DatabaseConnection.getConnection();
        statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery(retrieve);

        while (resultSet.next()) {
            game = resultSet.getString("game");
            platform = resultSet.getString("platform");
            add = game + " - " + platform;
            list.add(add);
        }

        statement.close();

        resultSet.close();


        return list;
    }

    public static void addToTradelist(String name, String platform) throws SQLException {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        String add = "insert into tradelist (username, game, platform) values (?, ?, ?)";


        connection = DatabaseConnection.getConnection();
        preparedStatement = connection.prepareStatement(add);
        preparedStatement.setString(1, User.getInstance().getUsername());
        preparedStatement.setString(2, name);
        preparedStatement.setString(3, platform);
        preparedStatement.executeUpdate();

        preparedStatement.close();


    }

    public static void addToWishlist(String name, String platform) throws SQLException {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        String add = "insert into wishlist (username, game, platform) values (?, ?, ?)";


        connection = DatabaseConnection.getConnection();
        preparedStatement = connection.prepareStatement(add);
        preparedStatement.setString(1, User.getInstance().getUsername());
        preparedStatement.setString(2, name);
        preparedStatement.setString(3, platform);
        preparedStatement.executeUpdate();

        preparedStatement.close();


    }

    public static void removeFromTradelist(String name, String platform) throws SQLException {

        Connection connection = null;
        PreparedStatement preparedStatement = null;
        String remove = "delete from tradelist where username = ? and game = ? and platform = ?";


        connection = DatabaseConnection.getConnection();
        preparedStatement = connection.prepareStatement(remove);
        preparedStatement.setString(1, User.getInstance().getUsername());
        preparedStatement.setString(2, name);
        preparedStatement.setString(3, platform);
        preparedStatement.executeUpdate();

        preparedStatement.close();


    }

    public static void removeFromWishlist(String name, String platform) throws SQLException {

        Connection connection = null;
        PreparedStatement preparedStatement = null;
        String remove = "delete from wishlist where username = ? and game = ? and platform = ?";


        connection = DatabaseConnection.getConnection();
        preparedStatement = connection.prepareStatement(remove);
        preparedStatement.setString(1, User.getInstance().getUsername());
        preparedStatement.setString(2, name);
        preparedStatement.setString(3, platform);
        preparedStatement.executeUpdate();

        preparedStatement.close();


    }

}
