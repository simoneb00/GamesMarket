package gamesmarket.graphic_control.yourShop;

import gamesmarket.bean.OrderBean;
import gamesmarket.control.YourShopController;
import gamesmarket.exceptions.ErrorMessage;
import gamesmarket.model.Order;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class OrdersTableGraphicController implements Initializable {

    @FXML
    private TableColumn<Order, String> addressCol;
    @FXML
    private TableColumn<Order, String> cityCol;
    @FXML
    private TableColumn<Order, String> gameCol;
    @FXML
    private TableColumn<Order, String> nameCol;
    @FXML
    private TableColumn<Order, String> paymentCol;
    @FXML
    private TableColumn<Order, String> platformCol;
    @FXML
    private TableColumn<Order, String> priceCol;
    @FXML
    private TableView<Order> table;
    @FXML
    private TableColumn<Order, String> telCol;
    @FXML
    private TableColumn<Order, String> emailCol;
    @FXML
    private TableColumn<Order, String> statusCol;
    @FXML
    private Button markButton;
    @FXML
    private TableColumn<Order, Integer> idCol;

    private YourShopController yourShopController = new YourShopController();
    private ObservableList<Order> ordersObservableList = FXCollections.observableArrayList();
    private Order selectedOrder = null;

    public void close(ActionEvent event) {
        Stage stage = (Stage) ((Button)event.getSource()).getScene().getWindow();
        stage.close();
    }

    public void retrieveTable() {
        try {
            List<OrderBean> orderBeans = yourShopController.retrieveOrders();
            List<Order> orders = new ArrayList<>();

            for (int i = 0; i < orderBeans.size(); i++) {
                Order order = new Order();

                order.setIdOrder(orderBeans.get(i).getIdOrder());
                order.setVendor(orderBeans.get(i).getVendor());
                order.setPlatform(orderBeans.get(i).getOrderPlatform());
                order.setGame(orderBeans.get(i).getOrderGame());
                order.setPrice(orderBeans.get(i).getOrderPrice());
                order.setBuyerName(orderBeans.get(i).getBuyerName());
                order.setBuyerAddress(orderBeans.get(i).getBuyerAddress());
                order.setBuyerCity(orderBeans.get(i).getBuyerCity());
                order.setBuyerTel(orderBeans.get(i).getBuyerTel());
                order.setBuyerEmail(orderBeans.get(i).getBuyerEmail());
                order.setPaymentMethod(orderBeans.get(i).getPaymentMethod());
                order.setUsername(orderBeans.get(i).getBuyerUsername());
                order.setStatus(orderBeans.get(i).getStatus());

                orders.add(order);
                ordersObservableList.add(order);

            }


            idCol.setCellValueFactory(new PropertyValueFactory<>("idOrder"));
            gameCol.setCellValueFactory(new PropertyValueFactory<>("game"));
            platformCol.setCellValueFactory(new PropertyValueFactory<>("platform"));
            priceCol.setCellValueFactory(new PropertyValueFactory<>("price"));
            nameCol.setCellValueFactory(new PropertyValueFactory<>("buyerName"));
            addressCol.setCellValueFactory(new PropertyValueFactory<>("buyerAddress"));
            emailCol.setCellValueFactory(new PropertyValueFactory<>("buyerEmail"));
            cityCol.setCellValueFactory(new PropertyValueFactory<>("buyerCity"));
            telCol.setCellValueFactory(new PropertyValueFactory<>("buyerTel"));
            paymentCol.setCellValueFactory(new PropertyValueFactory<>("paymentMethod"));
            statusCol.setCellValueFactory(new PropertyValueFactory<>("status"));

            table.setItems(ordersObservableList);

        } catch (SQLException e) {
            ErrorMessage.displayErrorMessage();
        }
    }

    public void markButtonPressed() {
        try {
            String[] s = markButton.getText().split(" ");
            String newStatus = s[2];
            OrderBean orderBean = new OrderBean();

            orderBean.setIdOrder(selectedOrder.getIdOrder());
            orderBean.setVendor(selectedOrder.getVendor());
            orderBean.setOrderPlatform(selectedOrder.getPlatform());
            orderBean.setOrderGame(selectedOrder.getGame());
            orderBean.setOrderPrice(selectedOrder.getPrice());
            orderBean.setBuyerName(selectedOrder.getBuyerName());
            orderBean.setBuyerAddress(selectedOrder.getBuyerAddress());
            orderBean.setBuyerCity(selectedOrder.getBuyerCity());
            orderBean.setBuyerTel(selectedOrder.getBuyerTel());
            orderBean.setPaymentMethod(selectedOrder.getPaymentMethod());
            orderBean.setBuyerUsername(selectedOrder.getUsername());
            orderBean.setBuyerEmail(selectedOrder.getBuyerEmail());
            orderBean.setStatus(selectedOrder.getStatus());
            orderBean.setNewStatus(newStatus);
            orderBean.setNewStatus(newStatus);

            yourShopController.updateStatus(orderBean);
            table.getItems().clear();
            this.retrieveTable();

            markButton.setDisable(true);
            markButton.setVisible(false);

        } catch (SQLException e) {
            ErrorMessage.displayErrorMessage();
        }
    }

    public void selectOrder() {
        selectedOrder = table.getSelectionModel().getSelectedItem();
        if (selectedOrder != null && selectedOrder.getStatus().equals("Pending")) {
                if (selectedOrder.getPaymentMethod().equals("Pickup in Store"))
                    markButton.setText("Mark as Delivered");
                else
                    markButton.setText("Mark as Shipped");

                markButton.setVisible(true);
                markButton.setDisable(false);
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        this.retrieveTable();
    }
}
