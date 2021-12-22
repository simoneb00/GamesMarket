package GamesMarket.model.DAO;

import GamesMarket.DBConnection.DatabaseConnection;
import GamesMarket.model.User;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.awt.image.ImageObserver;
import java.awt.image.ImageProducer;
import java.io.*;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserDAO {

    public boolean validateLogin(String email, String password) {
        boolean returnValue = false;
        Statement statement = null;
        Connection connection = null;
        String verifyLogin = "SELECT count(1) FROM user WHERE email = '" + email + "' AND password = '" + password + "'";

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

    public List<String> retrieveUser(String email) {

        List<String> list = new ArrayList<>();
        Statement statement = null;
        Connection connection = null;
        String retrieveUser = "select username, password, firstName, lastName from user where email = '" + email + "'";

        try {
            connection = DatabaseConnection.getInstance().getConnection();
            statement = connection.createStatement();
            ResultSet result = statement.executeQuery(retrieveUser);

            while (result.next()) {
                list.add(result.getString("username"));
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


    public List<String> retrieveContactInf() {
        String username = User.getInstance().getUsername();
        List<String> ci = new ArrayList<>();

        Connection connection = null;
        Statement statement = null;
        String retrieveCI = "select email, tel, address, country from contactinf where username = '" + username + "';";

        try {
            connection = DatabaseConnection.getInstance().getConnection();
            statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(retrieveCI);

            while (resultSet.next()) {
                ci.add(resultSet.getString("email"));
                ci.add(resultSet.getString("tel"));
                ci.add(resultSet.getString("address"));
                ci.add(resultSet.getString("country"));
            }

            resultSet.close();
            if (statement != null)
                statement.close();

        } catch(SQLException e) {
            e.printStackTrace();
        }

        return ci;
    }

    public void updateCI(String email, String tel, String address, String country) {

        Connection connection = null;
        Statement statement = null;
        String deleteCI = "delete from contactinf where username = '" + User.getInstance().getUsername() + "';";
        String updateCI = "insert into contactinf (username, email, tel, address, country) values ('" + User.getInstance().getUsername() + "', '" + email + "', '" + tel + "', '" + address + "', '" + country + "');";

        try {
            connection = DatabaseConnection.getInstance().getConnection();
            statement = connection.createStatement();

            statement.execute(deleteCI);
            statement.execute(updateCI);

            if (statement != null)
                statement.close();

        } catch(SQLException e) {
            e.printStackTrace();
        }
    }


    public String retrieveBio() {
        String bio = new String();
        Connection connection = null;
        Statement statement = null;
        String retrieveBio = "select bio from bio where username = '" + User.getInstance().getUsername() + "';";

        try {
            connection = DatabaseConnection.getInstance().getConnection();
            statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(retrieveBio);

            while(resultSet.next()) {
                bio = resultSet.getString("bio");
            }

            resultSet.close();
            if (statement != null)
                statement.close();

        } catch(SQLException e) {
            e.printStackTrace();
        }

        return bio;
    }

    public void saveBio(String bio) {
        Connection connection = null;
        Statement statement = null;
        String deleteBio = "delete from bio where username = '" + User.getInstance().getUsername() + "';";
        String saveBio = "insert into bio (username, bio) values ('" + User.getInstance().getUsername() + "', '" + bio + "');";

        try {
            connection = DatabaseConnection.getInstance().getConnection();
            statement = connection.createStatement();
            if (bio != null)
                statement.execute(deleteBio);
            statement.execute(saveBio);

            if (statement != null)
                statement.close();

        } catch(SQLException e) {
            e.printStackTrace();
        }
    }

    public void updateProfilePhoto(String path) {
        try {
            File image = new File(path);
            FileInputStream inputStream = new FileInputStream(image);
            String delete = "delete from profileimage where username = '" + User.getInstance().getUsername() + "';";
            String updatePhoto = "insert into profileimage (username, image) values (?, ?)";
            Connection connection = DatabaseConnection.getInstance().getConnection();
            Statement statement1 = connection.createStatement();
            PreparedStatement statement = connection.prepareStatement(updatePhoto);
            statement.setString(1, User.getInstance().getUsername());
            statement.setBinaryStream(2, (InputStream) inputStream, (int)(image.length()));

            statement1.execute(delete);
            statement.executeUpdate();

            if (statement != null)
                statement.close();

        } catch (FileNotFoundException e){
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    public File retrieveProfilePhoto() {
        Connection connection = null;
        Statement statement = null;
        File file = new File("image.jpg");
        String retrieve = "select * from profileimage where username = '" + User.getInstance().getUsername() + "';";

        try {
            connection = DatabaseConnection.getInstance().getConnection();
            statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(retrieve);
            byte b[];
            Blob blob;
            FileOutputStream fos = new FileOutputStream(file);

            while(resultSet.next()) {
                blob = resultSet.getBlob("image");
                b = blob.getBytes(1, (int)blob.length());
                fos.write(b);
            }

            resultSet.close();
            if (statement != null)
                statement.close();
            fos.close();

        } catch (SQLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return file;
    }

}
