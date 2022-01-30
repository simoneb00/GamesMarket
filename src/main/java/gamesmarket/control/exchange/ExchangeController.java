package gamesmarket.control.exchange;

import gamesmarket.bean.ExchangePostBean;
import gamesmarket.model.dao.ExchangePostDAO;
import gamesmarket.model.ExchangePost;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ExchangeController {

    public List<ExchangePostBean> retrieveExchange() throws SQLException {

        List<ExchangePostBean> exchangePostBeans = new ArrayList<>();

        List<ExchangePost> exchangePosts = ExchangePostDAO.retrieveExchange();  // retrieve exchange posts for the user

        for (ExchangePost exchangePost : exchangePosts) {               // upload exchange posts in beans
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
