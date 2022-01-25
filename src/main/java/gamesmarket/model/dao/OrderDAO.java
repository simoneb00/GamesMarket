package gamesmarket.model.dao;

import gamesmarket.dbconnection.DatabaseConnection;
import gamesmarket.model.Order;
import gamesmarket.model.Shop;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class OrderDAO {

    private OrderDAO() {}

    public static void saveOrder(Order order) throws SQLException{

        PreparedStatement preparedStatement = null;
        String save = """
                INSERT INTO `gamesmarketdb`.`orders`
                (`shopName`,
                `game`,
                `platform`,
                `price`,
                `buyerName`,
                `buyerAddress`,
                `buyerCity`,
                `buyerTel`,
                `paymentMethod`,
                `username`,\s
                `buyerEmail`,\s
                `status`)VALUES
                (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?);""";

        try {
            Connection connection = DatabaseConnection.getConnection();
            preparedStatement = connection.prepareStatement(save);
            preparedStatement.setString(1, order.getVendor());
            preparedStatement.setString(2, order.getGame());
            preparedStatement.setString(3, order.getPlatform());
            preparedStatement.setDouble(4, order.getPrice());
            preparedStatement.setString(5, order.getBuyerName());
            preparedStatement.setString(6, order.getBuyerAddress());
            preparedStatement.setString(7, order.getBuyerCity());
            preparedStatement.setString(8, order.getBuyerTel());
            preparedStatement.setString(9, order.getPaymentMethod());
            preparedStatement.setString(10, order.getUsername());
            preparedStatement.setString(11, order.getBuyerEmail());
            preparedStatement.setString(12, "Pending");

            preparedStatement.execute();

        } finally {
            if (preparedStatement != null)
                preparedStatement.close();
        }
    }


    public static List<Order> retrieveOrders() throws SQLException {
        List<Order> orders = new ArrayList<>();
        PreparedStatement preparedStatement = null;
        String retrieve = "select idorders, shopName, platform, game, price, buyerName, buyerAddress, buyerCity, buyerTel, paymentMethod, username, buyerEmail, status from orders where shopName = ?";

        try {

            Connection connection = DatabaseConnection.getConnection();
            preparedStatement = connection.prepareStatement(retrieve);
            preparedStatement.setString(1, Shop.getInstance().getName());
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                Order order = new Order();

                order.setIdOrder(resultSet.getInt("idorders"));
                order.setVendor(resultSet.getString("shopName"));
                order.setGame(resultSet.getString("game"));
                order.setPlatform(resultSet.getString("platform"));
                order.setPrice(resultSet.getDouble("price"));
                order.setBuyerName(resultSet.getString("buyerName"));
                order.setBuyerAddress(resultSet.getString("buyerAddress"));
                order.setBuyerCity(resultSet.getString("buyerCity"));
                order.setBuyerTel(resultSet.getString("buyerTel"));
                order.setPaymentMethod(resultSet.getString("paymentMethod"));
                order.setUsername(resultSet.getString("username"));
                order.setBuyerEmail(resultSet.getString("buyerEmail"));
                order.setStatus(resultSet.getString("status"));

                orders.add(order);
            }

            resultSet.close();

        } finally {
            if (preparedStatement != null)
                preparedStatement.close();
        }

        return orders;
    }

    public static void updateStatus(Order order, String status) throws SQLException{
        int id = order.getIdOrder();
        PreparedStatement preparedStatement = null;
        String update = "update orders set status = ? where idorders = ?";

        try {
            Connection connection = DatabaseConnection.getConnection();
            preparedStatement = connection.prepareStatement(update);
            preparedStatement.setString(1, status);
            preparedStatement.setInt(2, id);

            preparedStatement.executeUpdate();

        } finally {
            if (preparedStatement != null)
                preparedStatement.close();
        }
    }
}
