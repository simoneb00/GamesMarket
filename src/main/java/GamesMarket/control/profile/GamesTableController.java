package GamesMarket.control.profile;

import GamesMarket.bean.GameBean;
import GamesMarket.model.DAO.GameDAO;
import GamesMarket.model.DAO.ShopDAO;
import GamesMarket.model.DAO.ShopOwnerDAO;
import GamesMarket.model.DAO.UserDAO;
import GamesMarket.model.Game;
import GamesMarket.model.ShopOwner;
import GamesMarket.model.User;

import java.util.ArrayList;
import java.util.List;

public class GamesTableController {

    GameDAO gameDAO = new GameDAO();
    UserDAO userDAO = new UserDAO();

    public List<Game> retrieveGames() {
        return gameDAO.retrieveGames();
    }

    public void addToTradelist(GameBean gameBean) {
        String name = gameBean.getName();
        String platform = gameBean.getPlatform();
        String game = name + " - " + platform;
        User.getInstance().getTradelist().add(game);
        userDAO.addToTradelist(name, platform);
    }

    public void addToWishlist(GameBean gameBean) {
        String name = gameBean.getName();
        String platform = gameBean.getPlatform();
        String game = name + " - " + platform;
        User.getInstance().getWishlist().add(game);
        userDAO.addToWishlist(name, platform);
    }

    public void putForSale(GameBean gameBean) {
        String name = gameBean.getName();
        String platform = gameBean.getPlatform();
        double price = gameBean.getPrice();
        ShopDAO shopDAO = new ShopDAO();
        shopDAO.putForSale(name, platform, price);

        Game game = new Game();
        game.setPrice(price);
        game.setName(name);
        game.setPlatform(platform);
        ShopOwner.getInstance().getShop().getGames().add(game);
    }
}
