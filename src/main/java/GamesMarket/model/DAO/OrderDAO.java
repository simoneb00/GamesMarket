package GamesMarket.model.DAO;

import GamesMarket.DBConnection.DatabaseConnection;
import GamesMarket.model.Order;
import GamesMarket.model.Shop;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class OrderDAO {

    public static void saveOrder(Order order) throws SQLException{
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        String save = "INSERT INTO `gamesmarketdb`.`orders`\n" +
                "(`shopName`,\n" +
                "`game`,\n" +
                "`platform`,\n" +
                "`price`,\n" +
                "`buyerName`,\n" +
                "`buyerAddress`,\n" +
                "`buyerCity`,\n" +
                "`buyerTel`,\n" +
                "`paymentMethod`,\n" +
                "`username`, \n" +
                "`buyerEmail`, \n" +
                "`status`)" +
                "VALUES\n" +
                "(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?);";


        System.out.println(order.getBuyerCity());

        connection = DatabaseConnection.getConnection();
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

        preparedStatement.close();


    }


    public static List<Order> retrieveOrders() throws SQLException {
        List<Order> orders = new ArrayList<>();
        Connection connection = DatabaseConnection.getConnection();
        PreparedStatement preparedStatement = null;
        String retrieve = "select idorders, shopName, platform, game, price, buyerName, buyerAddress, buyerCity, buyerTel, paymentMethod, username, buyerEmail, status from orders where shopName = ?";


        preparedStatement = connection.prepareStatement(retrieve);
        preparedStatement.setString(1, Shop.getInstance().getName());
        ResultSet resultSet = preparedStatement.executeQuery();

        while (resultSet.next()) {
            Order order = new Order(
                    resultSet.getInt("idorders"),
                    resultSet.getString("shopName"),
                    resultSet.getString("platform"),
                    resultSet.getString("game"),
                    resultSet.getDouble("price"),
                    resultSet.getString("buyerName"),
                    resultSet.getString("buyerAddress"),
                    resultSet.getString("buyerCity"),
                    resultSet.getString("buyerTel"),
                    resultSet.getString("paymentMethod"),
                    resultSet.getString("username"),
                    resultSet.getString("buyerEmail"),
                    resultSet.getString("status")
            );

            orders.add(order);
        }

        preparedStatement.close();
        resultSet.close();


        return orders;
    }

    public static void updateStatus(Order order, String status) throws SQLException{
        int id = order.getIdOrder();
        PreparedStatement preparedStatement = null;
        Connection connection = DatabaseConnection.getConnection();
        String update = "update orders set status = ? where idorders = ?";

        preparedStatement = connection.prepareStatement(update);
        preparedStatement.setString(1, status);
        preparedStatement.setInt(2, id);

        preparedStatement.executeUpdate();

        preparedStatement.close();
    }
}
