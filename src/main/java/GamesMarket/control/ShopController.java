package GamesMarket.control;

import GamesMarket.bean.ShopPostBean;
import GamesMarket.model.DAO.ShopPostDAO;
import GamesMarket.model.ShopPost;

import java.util.ArrayList;
import java.util.List;

public class ShopController {

    private ShopPostDAO shopPostDAO = new ShopPostDAO();

    public List<ShopPostBean> retrieveShop() {
        List<ShopPost> posts = shopPostDAO.retrieveShop();
        List<ShopPostBean> beans = new ArrayList<>();

        for (int i = 0; i < posts.size(); i++) {
            ShopPost shopPost = posts.get(i);
            ShopPostBean shopPostBean = new ShopPostBean(shopPost.getShopName(), shopPost.getGame(), shopPost.getPrice(), shopPost.getImageFile());
            beans.add(shopPostBean);
        }

        return beans;
    }
}
