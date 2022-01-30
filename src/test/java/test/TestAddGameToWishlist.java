package test;

import gamesmarket.bean.GameBean;
import gamesmarket.bean.LoginCredentialsBean;
import gamesmarket.control.GamesTableController;
import gamesmarket.control.LoginController;
import gamesmarket.control.profile.UserProfileController;
import gamesmarket.exceptions.DuplicatedGameException;
import gamesmarket.exceptions.InvalidEmailException;
import gamesmarket.model.Game;
import gamesmarket.model.User;
import gamesmarket.model.dao.UserDAO;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class TestAddGameToWishlist {

    @Test
    public void testAddGameToWishlist() throws InvalidEmailException, SQLException, IOException, DuplicatedGameException {

        // login with an existing user (created for testing purposes)
        LoginCredentialsBean loginCredentialsBean = new LoginCredentialsBean();
        loginCredentialsBean.setEmailAddress("test@test.com");
        loginCredentialsBean.setPassword("test");
        LoginController loginController = new LoginController();
        loginController.validateLogin(loginCredentialsBean);

        // creation of a fictitious game
        Game game = new Game();
        game.setName("test_game");
        game.setPlatform("test_platform");

        // adding game to wishlist
        GamesTableController gamesTableController = new GamesTableController();
        GameBean gameBean = new GameBean();
        gameBean.setGameName(game.getName());
        gameBean.setGamePlatform(game.getPlatform());
        gamesTableController.addToWishlist(gameBean);

        assertTrue(isPresentInWishlist(game));

        // removing game from wishlist
        UserProfileController userProfileController = new UserProfileController();
        userProfileController.removeFromWishlist(gameBean);

        assertFalse(isPresentInWishlist(game));
    }


    private boolean isPresentInWishlist(Game game) throws SQLException {
        List<String> wishlist = UserDAO.retrieveWishlist();
        boolean ret = false;

        for (int i = 0; i < wishlist.size(); i++) {
            String[] strings = wishlist.get(i).split(" - ");
            if (game.getName().equals(strings[0]) && game.getPlatform().equals(strings[1]))
                ret = true;
        }

        return ret;
    }
}
