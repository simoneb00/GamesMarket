package gamesmarket.control;

import gamesmarket.bean.GameBean;
import gamesmarket.exceptions.DuplicatedGameException;
import gamesmarket.model.dao.GameDAO;
import gamesmarket.model.dao.ShopDAO;
import gamesmarket.model.dao.UserDAO;
import gamesmarket.model.Game;
import gamesmarket.model.Shop;
import gamesmarket.model.ShopOwner;
import gamesmarket.model.User;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class GamesTableController {

    public List<GameBean> retrieveGames() throws SQLException {
        List<GameBean> beans = new ArrayList<>();
        List<Game> games = GameDAO.retrieveGames();     // retrieving all the games present in the DB

        for (int i = 0; i < games.size(); i++) {
            GameBean bean = new GameBean();
            bean.setGameName(games.get(i).getName());
            bean.setGamePlatform(games.get(i).getPlatform());
            bean.setGameGenre(games.get(i).getGenre());
            bean.setGameDescription(games.get(i).getDescription());
            bean.setGameYear(games.get(i).getYear());

            beans.add(bean);
        }

        return beans;
    }

    public void addToTradelist(GameBean gameBean) throws DuplicatedGameException, SQLException {
        String name = gameBean.getGameName();
        String platform = gameBean.getGamePlatform();
        String game = name + " - " + platform;

        if (User.getInstance().getTradelist().contains(game)) {
            throw new DuplicatedGameException();        // if the game is already present in the user's tradelist, this exception is raised to warn the user
        }

        User.getInstance().getTradelist().add(game);        //updating the user instance's tradelist
        UserDAO.addToTradelist(name, platform);
    }

    public void addToWishlist(GameBean gameBean) throws DuplicatedGameException, SQLException {
        String name = gameBean.getGameName();
        String platform = gameBean.getGamePlatform();
        String game = name + " - " + platform;

        if (User.getInstance().getWishlist().contains(game)) {
            throw new DuplicatedGameException();        // if the game is already present in the user's wishlist, this exception is raised to warn the user
        }

        User.getInstance().getWishlist().add(game);     //updating the user instance's tradelist
        UserDAO.addToWishlist(name, platform);
    }

    public void putForSale(GameBean gameBean) throws DuplicatedGameException, SQLException {

        String name = gameBean.getGameName();
        String platform = gameBean.getGamePlatform();
        double price = gameBean.getGamePrice();

        for (Game g : Shop.getInstance().getGames()) {
            if (g.getPrice() == price && g.getName().equals(name) && g.getPlatform().equals(platform)) {
                throw new DuplicatedGameException();        // if the game is already present in the shop, this exception is raised to warn the user
            }
        }

        ShopDAO.putForSale(name, platform, price);

        Game game = new Game();
        game.setPrice(price);
        game.setName(name);
        game.setPlatform(platform);
        ShopOwner.getInstance().getShop().getGames().add(game);     // updating the shop owner instance's shop

    }
}
