package GamesMarket.control;

import GamesMarket.bean.GameBean;
import GamesMarket.bean.OrderBean;
import GamesMarket.bean.ShopBean;
import GamesMarket.model.DAO.OrderDAO;
import GamesMarket.model.DAO.ShopDAO;
import GamesMarket.model.Order;
import GamesMarket.model.Shop;
import GamesMarket.model.ShopOwner;
import javafx.stage.FileChooser;
import java.io.File;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class YourShopController {

    public void updatePhoto() throws SQLException, IOException {
        FileChooser fileChooser = new FileChooser();
        File selectedFile = fileChooser.showOpenDialog(null);
        String path = selectedFile.getAbsolutePath();

        ShopDAO.updatePhoto(path);
        ShopOwner.getInstance().getShop().setImageFile(ShopDAO.retrievePhoto(ShopOwner.getInstance().getEmail()));
    }


    public void removeGame(GameBean gameBean) throws SQLException {
        String name = gameBean.getName();
        String platform = gameBean.getPlatform();
        double price = gameBean.getPrice();

        ShopDAO.removeGameFromSale(name, platform, price);

        for (int i = 0; i < Shop.getInstance().getGames().size(); i++) {
            if (Shop.getInstance().getGames().get(i).getName().equals(name) && Shop.getInstance().getGames().get(i).getPlatform().equals(platform) && Shop.getInstance().getGames().get(i).getPrice() == price)
                Shop.getInstance().getGames().remove(i);
        }
    }

    public void createNewShop(ShopBean shopBean) throws SQLException {
        String name = shopBean.getName();
        String address = shopBean.getAddress();
        String country = shopBean.getCountry();
        String city = shopBean.getCity();
        ShopDAO.createNewShop(name, address, city, country);

        Shop.getInstance().setName(name);
        Shop.getInstance().setAddress(address);
        Shop.getInstance().setCity(city);
        Shop.getInstance().setCountry(country);
        ShopOwner.getInstance().setShop(Shop.getInstance());
    }

    public List<OrderBean> retrieveOrders() throws SQLException {

        List<OrderBean> orderBeans = new ArrayList<>();
        List<Order> orders = OrderDAO.retrieveOrders();

        for (int i = 0; i < orders.size(); i++) {
            OrderBean orderBean = new OrderBean(
                    orders.get(i).getIdOrder(),
                    orders.get(i).getVendor(),
                    orders.get(i).getPlatform(),
                    orders.get(i).getGame(),
                    orders.get(i).getPrice(),
                    orders.get(i).getBuyerName(),
                    orders.get(i).getBuyerAddress(),
                    orders.get(i).getBuyerCity(),
                    orders.get(i).getBuyerTel(),
                    orders.get(i).getPaymentMethod(),
                    orders.get(i).getUsername(),
                    orders.get(i).getBuyerEmail(),
                    orders.get(i).getStatus()
            );

            orderBeans.add(orderBean);
        }

        return orderBeans;
    }

    public void updateStatus(OrderBean orderBean) throws SQLException{
        Order order = new Order(
                orderBean.getIdOrder(),
                orderBean.getVendor(),
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

        String newStatus = orderBean.getNewStatus();

        OrderDAO.updateStatus(order, newStatus);
    }
}
