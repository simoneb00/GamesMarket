package GamesMarket.control.exchange;

import GamesMarket.bean.ExchangePostBean;
import GamesMarket.exceptions.ErrorMessage;
import GamesMarket.model.DAO.ExchangePostDAO;
import GamesMarket.model.ExchangePost;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ExchangeController {

public List<ExchangePostBean> retrieveExchange() throws SQLException {

        List<ExchangePostBean> exchangePostBeans = new ArrayList<>();


        List<ExchangePost> exchangePosts = ExchangePostDAO.retrieveExchange();

        for (int i = 0; i < exchangePosts.size(); i++) {
            ExchangePostBean exchangePostBean = new ExchangePostBean(
                    exchangePosts.get(i).getUsername(),
                    exchangePosts.get(i).getGame(),
                    exchangePosts.get(i).getPlatform(),
                    exchangePosts.get(i).getGameToGive(),
                    exchangePosts.get(i).getPlatformGameToGive(),
                    exchangePosts.get(i).getImageFile()
            );

            exchangePostBeans.add(exchangePostBean);
        }


        return exchangePostBeans;
    }
}
