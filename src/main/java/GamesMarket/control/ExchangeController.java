package GamesMarket.control;

import GamesMarket.model.DAO.UserDAO;
import GamesMarket.model.ExchangePost;
import GamesMarket.model.Game;

import java.util.ArrayList;
import java.util.List;

public class ExchangeController {

    private UserDAO userDAO = new UserDAO();

    public List<ExchangePost> retrieveExchange() {
        return userDAO.retrieveExchange();
    }
}
