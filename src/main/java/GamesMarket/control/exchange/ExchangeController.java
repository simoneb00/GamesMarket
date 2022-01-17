package GamesMarket.control.exchange;

import GamesMarket.bean.ExchangePostBean;
import GamesMarket.model.DAO.ExchangePostDAO;
import GamesMarket.model.ExchangePost;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ExchangeController {

    public List<ExchangePostBean> retrieveExchange() throws SQLException {

        List<ExchangePostBean> exchangePostBeans = new ArrayList<>();


        List<ExchangePost> exchangePosts = ExchangePostDAO.retrieveExchange();

        for (ExchangePost exchangePost : exchangePosts) {
            ExchangePostBean exchangePostBean = new ExchangePostBean(
                    exchangePost.getUsername(),
                    exchangePost.getGame(),
                    exchangePost.getPlatform(),
                    exchangePost.getGameToGive(),
                    exchangePost.getPlatformGameToGive(),
                    exchangePost.getImageFile()
            );

            exchangePostBeans.add(exchangePostBean);
        }


        return exchangePostBeans;
    }
}
