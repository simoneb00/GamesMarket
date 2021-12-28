package GamesMarket.control.profile;

import GamesMarket.bean.GameBean;
import GamesMarket.model.DAO.GameDAO;
import GamesMarket.model.DAO.ShopOwnerDAO;
import GamesMarket.model.DAO.UserDAO;
import GamesMarket.model.Game;

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
        userDAO.addToTradelist(name, platform);
    }

    public void addToWishlist(GameBean gameBean) {
        String name = gameBean.getName();
        String platform = gameBean.getPlatform();
        userDAO.addToWishlist(name, platform);
    }

    public void putForSale(GameBean gameBean) {
        String name = gameBean.getName();
        String platform = gameBean.getPlatform();
        double price = gameBean.getPrice();
        ShopOwnerDAO shopOwnerDAO = new ShopOwnerDAO();
        shopOwnerDAO.putForSale(name, platform, price);
    }
}
