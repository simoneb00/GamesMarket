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

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class GamesTableController {

    public List<GameBean> retrieveGames() throws SQLException {
        List<GameBean> beans = new ArrayList<>();
        List<Game> games = GameDAO.retrieveGames();

        for (int i = 0; i < games.size(); i++) {
            GameBean bean = new GameBean();
            bean.setName(games.get(i).getName());
            bean.setPlatform(games.get(i).getPlatform());
            bean.setGenre(games.get(i).getGenre());
            bean.setDescription(games.get(i).getDescription());
            bean.setYear(games.get(i).getYear());

            beans.add(bean);
        }

        return beans;
    }

    public void addToTradelist(GameBean gameBean) throws DuplicatedGameException, SQLException {
        String name = gameBean.getName();
        String platform = gameBean.getPlatform();
        String game = name + " - " + platform;

        if (User.getInstance().getTradelist().contains(game)) {
            throw new DuplicatedGameException();
        }

        User.getInstance().getTradelist().add(game);
        UserDAO.addToTradelist(name, platform);
    }

    public void addToWishlist(GameBean gameBean) throws DuplicatedGameException, SQLException {
        String name = gameBean.getName();
        String platform = gameBean.getPlatform();
        String game = name + " - " + platform;

        if (User.getInstance().getWishlist().contains(game)) {
            throw new DuplicatedGameException();
        }

        User.getInstance().getWishlist().add(game);
        UserDAO.addToWishlist(name, platform);
    }

    public void putForSale(GameBean gameBean) throws DuplicatedGameException, SQLException {

        String name = gameBean.getName();
        String platform = gameBean.getPlatform();
        double price = gameBean.getPrice();

        for (Game g : Shop.getInstance().getGames()) {
            if (g.getPrice() == price && g.getName().equals(name) && g.getPlatform().equals(platform)) {
                throw new DuplicatedGameException();
            }
        }

        ShopDAO.putForSale(name, platform, price);

        Game game = new Game();
        game.setPrice(price);
        game.setName(name);
        game.setPlatform(platform);
        ShopOwner.getInstance().getShop().getGames().add(game);

    }
}
