package GamesMarket.control;

import GamesMarket.bean.OrderBean;
import GamesMarket.bean.ShopPostBean;
import GamesMarket.model.DAO.OrderDAO;
import GamesMarket.model.DAO.ShopPostDAO;
import GamesMarket.model.Order;
import GamesMarket.model.ShopPost;
import GamesMarket.model.User;

import java.util.ArrayList;
import java.util.List;

public class ShopController {


    public List<ShopPostBean> retrieveShop() {
        List<ShopPost> posts = ShopPostDAO.retrieveShop();
        List<ShopPostBean> beans = new ArrayList<>();

        for (int i = 0; i < posts.size(); i++) {
            ShopPost shopPost = posts.get(i);
            ShopPostBean shopPostBean = new ShopPostBean(shopPost.getShopName(), shopPost.getGame(), shopPost.getPrice(), shopPost.getImageFile());
            beans.add(shopPostBean);
        }

        return beans;
    }

    public void saveOrder(OrderBean orderBean) {
        Order order = new Order(orderBean.getVendor(),
                orderBean.getPlatform(),
                orderBean.getGame(),
                orderBean.getPrice(),
                orderBean.getBuyerName(),
                orderBean.getBuyerAddress(),
                orderBean.getBuyerCity(),
                orderBean.getBuyerTel(),
                orderBean.getPaymentMethod(),
                orderBean.getUsername(),
                orderBean.getBuyerEmail(),
                orderBean.getStatus()
        );

        OrderDAO.saveOrder(order);
        User.getInstance().getOrders().add(order);
    }
}
