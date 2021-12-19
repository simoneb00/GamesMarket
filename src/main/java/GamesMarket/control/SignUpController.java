package GamesMarket.control;

import GamesMarket.DBConnection.DatabaseConnection;
import GamesMarket.bean.RegisterCredentialsBean;
import GamesMarket.exceptions.DuplicatedEmailException;
import GamesMarket.exceptions.DuplicatedUsernameException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class SignUpController {
    private String firstName;
    private String lastName;
    private String username;
    private String password;
    private String email;

    String verifyEmail = "select count(1) from user where email = '" + email + "'";
    String verifyUsername = "select count(1) from user where username = '" + username + "'";

    public void signUp(RegisterCredentialsBean registerCredentialsBean) throws DuplicatedEmailException, DuplicatedUsernameException{

        firstName = registerCredentialsBean.getFirstName();
        lastName = registerCredentialsBean.getLastName();
        username = registerCredentialsBean.getUsername();
        password = registerCredentialsBean.getPassword();
        email = registerCredentialsBean.getEmail();
        String typeOfUser = registerCredentialsBean.getTypeOfUser();

        try {

            if (typeOfUser.equals("User")) {
                this.registerUser();
            } else {
                this.registerShopOwner();
            }

        } catch(DuplicatedEmailException e) {
            throw e;
        } catch(DuplicatedUsernameException e) {
            throw e;
        }
    }

    private void registerUser() throws DuplicatedEmailException, DuplicatedUsernameException{
        DatabaseConnection databaseConnection = DatabaseConnection.getInstance();
        Connection connection = databaseConnection.getConnection();

        String register = "insert into user (username, password, firstname, lastname, email) values (" + "'" + username + "', '" + password + "', '" + firstName + "', '" + lastName + "', '" + email + "')";

        try {
            Statement statement = connection.createStatement();
            ResultSet result = statement.executeQuery(verifyEmail);

            while (result.next()) {
                if (result.getInt(1) == 1) {
                    DuplicatedEmailException duplicatedEmailException = new DuplicatedEmailException();
                    throw duplicatedEmailException;
                }
            }

            result = statement.executeQuery(verifyUsername);

            while (result.next()) {
                if (result.getInt(1) == 1) {
                    DuplicatedUsernameException duplicatedUsernameException = new DuplicatedUsernameException();
                    throw duplicatedUsernameException;
                }
            }

            Boolean res = statement.execute(register);

        } catch(SQLException e) {
            e.printStackTrace();
        }
    }

    private void registerShopOwner() throws DuplicatedEmailException, DuplicatedUsernameException {

        DatabaseConnection databaseConnection = DatabaseConnection.getInstance();
        Connection connection = databaseConnection.getConnection();

        String register = "insert into `shop-owner` (email, password, firstname, lastname) values (" + "'" + email + "', '" + password + "', '" + firstName + "', '" + lastName + "')";

        try {
            Statement statement = connection.createStatement();
            ResultSet result = statement.executeQuery(verifyEmail);

            while (result.next()) {
                if (result.getInt(1) == 1) {
                    DuplicatedEmailException duplicatedEmailException = new DuplicatedEmailException();
                    throw duplicatedEmailException;
                }
            }

            result = statement.executeQuery(verifyUsername);

            while (result.next()) {
                if (result.getInt(1) == 1) {
                    DuplicatedUsernameException duplicatedUsernameException = new DuplicatedUsernameException();
                    throw duplicatedUsernameException;
                }
            }

            Boolean res = statement.execute(register);

        } catch(SQLException e) {
            e.printStackTrace();
        }
    }

    }

