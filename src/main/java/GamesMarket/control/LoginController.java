package GamesMarket.control;

import GamesMarket.DBConnection.DatabaseConnection;
import GamesMarket.bean.LoginCredentialsBean;
import GamesMarket.model.DAO.ShopDAO;
import GamesMarket.model.DAO.ShopOwnerDAO;
import GamesMarket.model.DAO.UserDAO;
import GamesMarket.model.Shop;
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

        if (shopOwnerDAO.validateLogin(emailAddress, password)) {
            this.retrieveShopOwner(emailAddress);
            return 1;
        }

        else if (userDAO.validateLogin(emailAddress, password)) {
            this.retrieveUser(emailAddress);
            return 0;
        }

        return 2;

    }

    private void retrieveShopOwner(String email) {
        ShopOwner shopOwner = ShopOwner.getInstance();
        shopOwner.setLoggedIn();
        attributes = shopOwnerDAO.retrieveShopOwner(email); // retrieves shop owner's information in this order: [password, firstName, lastName]
        shopOwner.setEmail(email);
        shopOwner.setPassword(attributes.get(0));
        shopOwner.setFirstName(attributes.get(1));
        shopOwner.setLastName(attributes.get(2));

        Shop shop = Shop.getInstance();
        ShopDAO shopDAO = new ShopDAO();
        String name = shopDAO.retrieveShopName(email);
        if (name != null) {
            shop.setName(name);
            shop.setOwner(email);
            shop.setGames(shopDAO.retrieveList(email));
            shop.setImageFile(shopDAO.retrievePhoto(email));
            List<String> completeAddress = shopDAO.retrieveAddress(email);  // retrieve complete address in this order [address, city, country]
            shop.setAddress(completeAddress.get(0));
            shop.setCity(completeAddress.get(1));
            shop.setCountry(completeAddress.get(2));
            shopOwner.setShop(shop);
        }
    }


    private void retrieveUser(String email) {
        User user = User.getInstance();
        user.setLoggedIn();
        attributes = userDAO.retrieveUser(email); // retrieves user's information in this order : [username, password, firstName, lastName]
        user.setEmailAddress(email);
        user.setUsername(attributes.get(0));
        user.setPassword(attributes.get(1));
        user.setFirstName(attributes.get(2));
        user.setLastName(attributes.get(3));
        user.setProfileImagePath(userDAO.retrieveProfilePhoto().getAbsolutePath());
        user.setContacts(userDAO.retrieveContactInf(user.getUsername()));
        user.setBio(userDAO.retrieveBio());
        user.setTradelist(userDAO.retrieveTradelist());
        user.setWishlist(userDAO.retrieveWishlist());
    }
}
