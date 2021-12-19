package GamesMarket.control;

import GamesMarket.DBConnection.DatabaseConnection;
import GamesMarket.bean.LoginCredentialsBean;
import GamesMarket.model.DAO.ShopOwnerDAO;
import GamesMarket.model.DAO.UserDAO;
import GamesMarket.model.ShopOwner;
import GamesMarket.model.User;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class LoginController {

    UserDAO userDAO = new UserDAO();
    ShopOwnerDAO shopOwnerDAO = new ShopOwnerDAO();
    List<String> attributes = new ArrayList<>();

    public int validateLogin(LoginCredentialsBean loginCredentialsBean) {

        String emailAddress = loginCredentialsBean.getEmailAddress();
        String password = loginCredentialsBean.getPassword();
/*
        switch (userDAO.validateLogin(emailAddress, password)) {
            case 0:
                User user = User.getInstance();
                user.setLoggedIn();
                attributes = userDAO.retrieveUser(emailAddress); // retrieves user's information in this order : [username, password, firstName, lastName]
                user.setEmailAddress(emailAddress);
                user.setUsername(attributes.get(0));
                user.setPassword(attributes.get(1));
                user.setFirstName(attributes.get(2));
                user.setLastName(attributes.get(3));
                return true;
            case 1:
                ShopOwner shopOwner = ShopOwner.getInstance();
                shopOwner.setLoggedIn();
                attributes = shopOwnerDAO.retrieveShopOwner(emailAddress); // retrieves shop owner's information in this order: [password, firstName, lastName]
                shopOwner.setEmail(emailAddress);
                shopOwner.setPassword(attributes.get(0));
                shopOwner.setFirstName(attributes.get(1));
                shopOwner.setLastName(attributes.get(2));
                return true;
            default:
                return false;
        }
*/
        if (shopOwnerDAO.validateLogin(emailAddress, password)) {
            ShopOwner shopOwner = ShopOwner.getInstance();
            shopOwner.setLoggedIn();
            attributes = shopOwnerDAO.retrieveShopOwner(emailAddress); // retrieves shop owner's information in this order: [password, firstName, lastName]
            shopOwner.setEmail(emailAddress);
            shopOwner.setPassword(attributes.get(0));
            shopOwner.setFirstName(attributes.get(1));
            shopOwner.setLastName(attributes.get(2));
            return 1;
        }

        else if (userDAO.validateLogin(emailAddress, password)) {
            User user = User.getInstance();
            user.setLoggedIn();
            attributes = userDAO.retrieveUser(emailAddress); // retrieves user's information in this order : [username, password, firstName, lastName]
            user.setEmailAddress(emailAddress);
            user.setUsername(attributes.get(0));
            user.setPassword(attributes.get(1));
            user.setFirstName(attributes.get(2));
            user.setLastName(attributes.get(3));
            return 0;
        }

        return 2;

    }
}
