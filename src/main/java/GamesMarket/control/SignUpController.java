package GamesMarket.control;

import GamesMarket.DBConnection.DatabaseConnection;
import GamesMarket.bean.RegisterCredentialsBean;
import GamesMarket.exceptions.DuplicatedEmailException;
import GamesMarket.exceptions.DuplicatedUsernameException;
import GamesMarket.model.DAO.ShopOwnerDAO;
import GamesMarket.model.DAO.UserDAO;

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

    public void signUp(RegisterCredentialsBean registerCredentialsBean) throws DuplicatedEmailException, DuplicatedUsernameException, SQLException {

        firstName = registerCredentialsBean.getFirstName();
        lastName = registerCredentialsBean.getLastName();
        username = registerCredentialsBean.getUsername();
        password = registerCredentialsBean.getPassword();
        email = registerCredentialsBean.getEmail();
        String typeOfUser = registerCredentialsBean.getTypeOfUser();

        if (typeOfUser.equals("User")) {
            UserDAO.registerUser(username, email, password, firstName, lastName);
        } else {
            ShopOwnerDAO.registerShopOwner(email, password, firstName, lastName);
        }
    }
}

