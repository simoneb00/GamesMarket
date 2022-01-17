package GamesMarket.graphicControl.mobile.yourShop;

import GamesMarket.bean.OrderBean;
import GamesMarket.control.YourShopController;
import GamesMarket.exceptions.ErrorMessage;
import GamesMarket.graphicControl.mobile.NavigationButtons;
import GamesMarket.graphicControl.mobile.ShopOwnerNavigationButtons;
import GamesMarket.model.Order;
import GamesMarket.model.Shop;
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
                        orderBeans.get(i).getPlatform(),
                        orderBeans.get(i).getGame(),
                        orderBeans.get(i).getPrice(),
                        orderBeans.get(i).getBuyerName(),
                        orderBeans.get(i).getBuyerAddress(),
                        orderBeans.get(i).getBuyerCity(),
                        orderBeans.get(i).getBuyerTel(),
                        orderBeans.get(i).getPaymentMethod(),
                        orderBeans.get(i).getUsername(),
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
            OrderBean orderBean = new OrderBean(
                    selectedOrder.getIdOrder(),
                    selectedOrder.getVendor(),
                    selectedOrder.getPlatform(),
                    selectedOrder.getGame(),
                    selectedOrder.getPrice(),
                    selectedOrder.getBuyerName(),
                    selectedOrder.getBuyerAddress(),
                    selectedOrder.getBuyerCity(),
                    selectedOrder.getBuyerTel(),
                    selectedOrder.getPaymentMethod(),
                    selectedOrder.getUsername(),
                    selectedOrder.getBuyerEmail(),
                    selectedOrder.getStatus()
            );

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
