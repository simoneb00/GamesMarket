package GamesMarket.control.exchange;

import GamesMarket.model.DAO.UserDAO;
import GamesMarket.model.ExchangePost;

import java.util.List;

public class ExchangeController {

    private UserDAO userDAO = new UserDAO();

    public List<ExchangePost> retrieveExchange() {
        return userDAO.retrieveExchange();
    }
}
