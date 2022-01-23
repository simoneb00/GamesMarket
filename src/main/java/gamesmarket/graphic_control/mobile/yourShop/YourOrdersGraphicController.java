package gamesmarket.graphic_control.mobile.yourShop;

import gamesmarket.bean.OrderBean;
import gamesmarket.control.YourShopController;
import gamesmarket.exceptions.ErrorMessage;
import gamesmarket.graphic_control.mobile.ShopOwnerNavigationButtons;
import gamesmarket.model.Order;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class YourOrdersGraphicController extends ShopOwnerNavigationButtons implements Initializable {

    @FXML
    private TableColumn<Order, String> completedGameCol;
    @FXML
    private TableView<Order> completedOrdersTable;
    @FXML
    private TableColumn<Order, String> completedPaymentCol;
    @FXML
    private TableColumn<Order, String> completedPlatformCol;
    @FXML
    private TableColumn<Order, String> completedUsernameCol;
    @FXML
    private Button markButton;
    @FXML
    private TableColumn<Order, String> pendingGameCol;
    @FXML
    private TableView<Order> pendingOrdersTable;
    @FXML
    private TableColumn<Order, String> pendingPaymentCol;
    @FXML
    private TableColumn<Order, String> pendingPlatformCol;
    @FXML
    private TableColumn<Order, String> pendingUsernameCol;


    private YourShopController yourShopController = new YourShopController();
    private List<Order> pendingOrders = new ArrayList<>();
    private List<Order> completedOrders = new ArrayList<>();
    private ObservableList<Order> pendingOrdersObservableList = FXCollections.observableArrayList();
    private ObservableList<Order> completedOrdersObservableList = FXCollections.observableArrayList();
    private Order selectedOrder = null;


    private void retrieveOrders() {
        try {
            List<OrderBean> orderBeans = yourShopController.retrieveOrders();
            List<Order> orders = new ArrayList<>();

            for (int i = 0; i < orderBeans.size(); i++) {
                Order order = new Order(
                        orderBeans.get(i).getIdOrder(),
                        orderBeans.get(i).getVendor(),
                        orderBeans.get(i).getOrderPlatform(),
                        orderBeans.get(i).getOrderGame(),
                        orderBeans.get(i).getOrderPrice(),
                        orderBeans.get(i).getBuyerName(),
                        orderBeans.get(i).getBuyerAddress(),
                        orderBeans.get(i).getBuyerCity(),
                        orderBeans.get(i).getBuyerTel(),
                        orderBeans.get(i).getPaymentMethod(),
                        orderBeans.get(i).getBuyerUsername(),
                        orderBeans.get(i).getBuyerEmail(),
                        orderBeans.get(i).getStatus()
                );

                orders.add(order);
            }


            for (int i = 0; i < orders.size(); i++) {
                if (!orders.get(i).getStatus().equals("Pending")) {
                    completedOrders.add(orders.get(i));
                } else {
                    pendingOrders.add(orders.get(i));
                }
            }

            this.showCompletedOrdersTable();
            this.showPendingOrdersTable();

        } catch (SQLException e) {
            ErrorMessage.displayErrorMobile();
        }
    }

    private void showCompletedOrdersTable() {
        for (int i = 0; i < completedOrders.size(); i++) {
            completedOrdersObservableList.add(completedOrders.get(i));
        }

        completedGameCol.setCellValueFactory(new PropertyValueFactory<>("game"));
        completedPaymentCol.setCellValueFactory(new PropertyValueFactory<>("paymentMethod"));
        completedPlatformCol.setCellValueFactory(new PropertyValueFactory<>("platform"));
        completedUsernameCol.setCellValueFactory(new PropertyValueFactory<>("username"));

        completedOrdersTable.setItems(completedOrdersObservableList);
    }

    private void showPendingOrdersTable() {
        for (int i = 0; i < pendingOrders.size(); i++) {
            pendingOrdersObservableList.add(pendingOrders.get(i));
        }

        pendingGameCol.setCellValueFactory(new PropertyValueFactory<>("game"));
        pendingPaymentCol.setCellValueFactory(new PropertyValueFactory<>("paymentMethod"));
        pendingPlatformCol.setCellValueFactory(new PropertyValueFactory<>("platform"));
        pendingUsernameCol.setCellValueFactory(new PropertyValueFactory<>("username"));

        pendingOrdersTable.setItems(pendingOrdersObservableList);
    }

    public void selectOrder() {
        selectedOrder = pendingOrdersTable.getSelectionModel().getSelectedItem();
        if (selectedOrder != null) {
            if (selectedOrder.getStatus().equals("Pending")) {
                if (selectedOrder.getPaymentMethod().equals("Pickup in Store"))
                    markButton.setText("Mark as Delivered");
                else
                    markButton.setText("Mark as Shipped");

                markButton.setVisible(true);
                markButton.setDisable(false);
            }
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

            yourShopController.updateStatus(orderBean);

            markButton.setDisable(true);
            markButton.setVisible(false);

        } catch (SQLException e) {
            ErrorMessage.displayErrorMobile();
        }

        pendingOrdersTable.getItems().remove(selectedOrder);
        completedOrdersTable.getItems().add(selectedOrder);

    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        this.retrieveOrders();
    }
}
