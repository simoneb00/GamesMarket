package GamesMarket.control;

import GamesMarket.bean.LoginCredentialsBean;
import GamesMarket.exceptions.NotLoggedInException;
import GamesMarket.model.DAO.ShopDAO;
import GamesMarket.model.DAO.ShopOwnerDAO;
import GamesMarket.model.DAO.UserDAO;
import GamesMarket.model.Shop;
import GamesMarket.model.ShopOwner;
import GamesMarket.model.User;

import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class LoginController {

    List<String> attributes = new ArrayList<>();

    public void validateFacebookLogin() throws SQLException, IOException {
        User user = User.getInstance();
        user.setLoggedIn();
        user.setUsername("facebook-user");
        user.setEmailAddress("facebookuser@gmail.com");
        user.setProfileImagePath(UserDAO.retrieveProfilePhoto().getAbsolutePath());
        user.setContacts(UserDAO.retrieveContactInf(user.getUsername()));
        user.setBio(UserDAO.retrieveBio());
        user.setTradelist(UserDAO.retrieveTradelist());
        user.setWishlist(UserDAO.retrieveWishlist());
    }

    public void validateGoogleLogin() throws SQLException, IOException {
        User user = User.getInstance();
        user.setLoggedIn();
        user.setUsername("google-user");
        user.setEmailAddress("googleuser@gmail.com");
        user.setProfileImagePath(UserDAO.retrieveProfilePhoto().getAbsolutePath());
        user.setContacts(UserDAO.retrieveContactInf(user.getUsername()));
        user.setBio(UserDAO.retrieveBio());
        user.setTradelist(UserDAO.retrieveTradelist());
        user.setWishlist(UserDAO.retrieveWishlist());
    }

    public void validateLogin(LoginCredentialsBean loginCredentialsBean) throws NotLoggedInException, SQLException, IOException {

        String emailAddress = loginCredentialsBean.getEmailAddress();
        String password = loginCredentialsBean.getPassword();

        if (ShopOwnerDAO.validateLogin(emailAddress, password)) {
            this.retrieveShopOwner(emailAddress);
        } else if (UserDAO.validateLogin(emailAddress, password)) {
            this.retrieveUser(emailAddress);
        } else {
            throw new NotLoggedInException();
        }

    }

    private void retrieveShopOwner(String email) throws SQLException, IOException {
        ShopOwner shopOwner = ShopOwner.getInstance();
        shopOwner.setLoggedIn();
        attributes = ShopOwnerDAO.retrieveShopOwner(email); // retrieves shop owner's information in this order: [password, firstName, lastName]
        shopOwner.setEmail(email);
        shopOwner.setPassword(attributes.get(0));
        shopOwner.setFirstName(attributes.get(1));
        shopOwner.setLastName(attributes.get(2));

        Shop shop = Shop.getInstance();
        String name = ShopDAO.retrieveShopName(email);
        if (name != null) {
            shop.setName(name);
            shop.setOwner(email);
            shop.setGames(ShopDAO.retrieveList(email));
            shop.setImageFile(ShopDAO.retrievePhoto(email));
            List<String> completeAddress = ShopDAO.retrieveAddress(email);  // retrieve complete address in this order [address, city, country]
            shop.setAddress(completeAddress.get(0));
            shop.setCity(completeAddress.get(1));
            shop.setCountry(completeAddress.get(2));
            shopOwner.setShop(shop);
        }
    }


    private void retrieveUser(String email) throws SQLException, IOException {
        User user = User.getInstance();
        user.setLoggedIn();
        attributes = UserDAO.retrieveUser(email); // retrieves user's information in this order : [username, password, firstName, lastName]
        user.setEmailAddress(email);
        user.setUsername(attributes.get(0));
        user.setPassword(attributes.get(1));
        user.setFirstName(attributes.get(2));
        user.setLastName(attributes.get(3));
        user.setProfileImagePath(UserDAO.retrieveProfilePhoto().getAbsolutePath());
        user.setContacts(UserDAO.retrieveContactInf(user.getUsername()));
        user.setBio(UserDAO.retrieveBio());
        user.setTradelist(UserDAO.retrieveTradelist());
        user.setWishlist(UserDAO.retrieveWishlist());
    }
}
