package GamesMarket.control;

import GamesMarket.bean.GameBean;
import GamesMarket.exceptions.DuplicatedGameException;
import GamesMarket.model.DAO.GameDAO;
import GamesMarket.model.DAO.ShopDAO;
import GamesMarket.model.DAO.UserDAO;
import GamesMarket.model.Game;
import GamesMarket.model.Shop;
import GamesMarket.model.ShopOwner;
import GamesMarket.model.User;

import java.util.List;

public class GamesTableController{

    GameDAO gameDAO = new GameDAO();
    UserDAO userDAO = new UserDAO();

    public List<Game> retrieveGames() {
        return gameDAO.retrieveGames();
    }

    public void addToTradelist(GameBean gameBean) throws DuplicatedGameException{
        String name = gameBean.getName();
        String platform = gameBean.getPlatform();
        String game = name + " - " + platform;

        if (User.getInstance().getTradelist().contains(game)) {
            DuplicatedGameException e = new DuplicatedGameException();
            throw e;
        }

        User.getInstance().getTradelist().add(game);
        userDAO.addToTradelist(name, platform);
    }

    public void addToWishlist(GameBean gameBean) throws DuplicatedGameException{
        String name = gameBean.getName();
        String platform = gameBean.getPlatform();
        String game = name + " - " + platform;

        if (User.getInstance().getWishlist().contains(game)) {
            DuplicatedGameException e = new DuplicatedGameException();
            throw e;
        }

        User.getInstance().getWishlist().add(game);
        userDAO.addToWishlist(name, platform);
    }

    public void putForSale(GameBean gameBean) throws DuplicatedGameException {

        String name = gameBean.getName();
        String platform = gameBean.getPlatform();
        double price = gameBean.getPrice();

        for (Game g: Shop.getInstance().getGames()) {
            if (g.getPrice() == price && g.getName().equals(name) && g.getPlatform().equals(platform)) {
                DuplicatedGameException duplicatedGameInShopException = new DuplicatedGameException();
                throw duplicatedGameInShopException;
            }
        }

        ShopDAO shopDAO = new ShopDAO();
        shopDAO.putForSale(name, platform, price);

        Game game = new Game();
        game.setPrice(price);
        game.setName(name);
        game.setPlatform(platform);
        ShopOwner.getInstance().getShop().getGames().add(game);

    }
}
