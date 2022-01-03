package GamesMarket.graphicControl.yourShop;

import GamesMarket.bean.OrderBean;
import GamesMarket.control.YourShopController;
import GamesMarket.model.Order;
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

    private YourShopController yourShopController = new YourShopController();
    private ObservableList<Order> ordersObservableList = FXCollections.observableArrayList();

    public void close(ActionEvent event) {
        Stage stage = (Stage) ((Button)event.getSource()).getScene().getWindow();
        stage.close();
    }

    public void retrieveTable() {
        List<OrderBean> orderBeans = yourShopController.retrieveOrders();
        List<Order> orders = new ArrayList<>();

        for (int i = 0; i < orderBeans.size(); i++) {
            Order order = new Order(
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
            ordersObservableList.add(order);
        }


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
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        this.retrieveTable();
    }
}
