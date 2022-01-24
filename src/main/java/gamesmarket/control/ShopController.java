package gamesmarket.control;

import gamesmarket.bean.OrderBean;
import gamesmarket.bean.ShopPostBean;
import gamesmarket.model.DAO.OrderDAO;
import gamesmarket.model.DAO.ShopDAO;
import gamesmarket.model.Order;
import gamesmarket.model.ShopPost;
import gamesmarket.model.User;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ShopController {


    public List<ShopPostBean> retrieveShop() throws SQLException, IOException {
        List<ShopPost> posts = ShopDAO.retrieveShop();
        List<ShopPostBean> beans = new ArrayList<>();

        for (int i = 0; i < posts.size(); i++) {
            ShopPost shopPost = posts.get(i);
            ShopPostBean shopPostBean = new ShopPostBean(shopPost.getShopName(), shopPost.getGame(), shopPost.getPrice(), shopPost.getImageFile());
            beans.add(shopPostBean);
        }

        return beans;
    }

    public void saveOrder(OrderBean orderBean) throws SQLException {
        Order order = new Order();

        order.setIdOrder(orderBean.getIdOrder());
        order.setVendor(orderBean.getVendor());
        order.setGame(orderBean.getOrderGame());
        order.setPlatform(orderBean.getOrderPlatform());
        order.setPrice(orderBean.getOrderPrice());
        order.setBuyerAddress(orderBean.getBuyerAddress());
        order.setBuyerName(orderBean.getBuyerName());
        order.setBuyerCity(orderBean.getBuyerCity());
        order.setBuyerTel(orderBean.getBuyerTel());
        order.setPaymentMethod(orderBean.getPaymentMethod());
        order.setUsername(orderBean.getBuyerUsername());
        order.setBuyerEmail(orderBean.getBuyerEmail());
        order.setStatus(orderBean.getStatus());


        OrderDAO.saveOrder(order);

        User.getInstance().getOrders().add(order);
    }
}
