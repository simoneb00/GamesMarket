package GamesMarket.graphicControl.yourShop;

import GamesMarket.bean.GameBean;
import GamesMarket.bean.OrderBean;
import GamesMarket.control.YourShopController;
import GamesMarket.exceptions.ErrorMessage;
import GamesMarket.graphicControl.navigation.NavigationButtons;
import GamesMarket.main.Main;
import GamesMarket.model.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.effect.ColorAdjust;
import javafx.scene.effect.GaussianBlur;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.*;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import static java.time.zone.ZoneRulesProvider.refresh;


public class YourShopGraphicController extends NavigationButtons implements Initializable {

    @FXML
    private AnchorPane anchorPane;
    @FXML
    private TableColumn<Order, String> gameCol;
    @FXML
    private ListView<String> gamesList;
    @FXML
    private TableView<Order> ordersTable;
    @FXML
    private TableColumn<Order, String> paymentCol;
    @FXML
    private TableColumn<Order, String> platformCol;
    @FXML
    private ImageView shopImg;
    @FXML
    private Label shopName;
    @FXML
    private TableColumn<Order, String> usernameCol;
    @FXML
    private Button removeSelectedGame;
    @FXML
    private Label shopAddress;
    @FXML
    private Label photoLabel;

    private YourShopController yourShopController = new YourShopController();
    private String selected, selectedGame, selectedPlatform;
    private double selectedPrice;
    private ObservableList<Order> ordersObservableList = FXCollections.observableArrayList();


    private void retrieveImage() {
        try {
            File path = ShopOwner.getInstance().getShop().getImageFile();
            if (path != null) {
                File file = new File(path.getAbsolutePath());
                InputStream isImage = (InputStream) new FileInputStream(file);
                shopImg.setImage(new Image(isImage));
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    public void updatePhoto() {
        try {
            yourShopController.updatePhoto();
            gamesList.getItems().clear();
            this.initialize(null, null);
            ordersTable.getItems().clear();
            this.retrieveOrdersTable();
            photoLabel.setText("");
        } catch (RuntimeException e) {
            photoLabel.setText("No photo selected.");
        } catch (IOException e) {
            ErrorMessage.displayErrorMessage();
        } catch (SQLException e) {
            ErrorMessage.displayErrorMessage();
        }
    }

    public void retrieveList() {
        List<Game> games = ShopOwner.getInstance().getShop().getGames();
        String name, platform, string;
        double price;

        for (int i = 0; i < games.size(); i++) {
            name = games.get(i).getName();
            platform = games.get(i).getPlatform();
            price = games.get(i).getPrice();

            string = name + " - " + platform + " - €" + price;
            gamesList.getItems().add(string);
        }
    }

    public void add() {
        try {

            Parent root = FXMLLoader.load(Main.class.getResource("/GamesMarket/gamesTableSO.fxml"));
            Stage stage = new Stage();
            Scene scene = new Scene(root);
            scene.setFill(Color.TRANSPARENT);
            stage.initStyle(StageStyle.TRANSPARENT);
            stage.setScene(scene);

            GaussianBlur blur = new GaussianBlur(55);
            ColorAdjust adj = new ColorAdjust(-0.1, -0.1, -0.1, -0.1);
            adj.setInput(blur);
            anchorPane.setEffect(adj);

            stage.showAndWait();
            anchorPane.setEffect(null);

        } catch (Exception e) {
            e.printStackTrace();
            e.getCause();
        }

        gamesList.getItems().clear();
        this.retrieveList();
        ordersTable.getItems().clear();
        this.retrieveOrdersTable();
    }

    public void listMouseClicked() {
        selected = gamesList.getSelectionModel().getSelectedItems().toString();
        if (selected != "[]") {
            selected = selected.replaceAll("\\[|\\]", "");
            String[] strings = selected.split(" - ");
            selectedGame = strings[0];
            selectedPlatform = strings[1];
            String price = strings[2];
            price = price.replaceAll("€", "");
            selectedPrice = Double.parseDouble(price);
            removeSelectedGame.setVisible(true);
            removeSelectedGame.setDisable(false);
        }
    }

    public void removeSelectedGame() {
        GameBean gameBean = new GameBean();
        gameBean.setName(selectedGame);
        gameBean.setPlatform(selectedPlatform);
        gameBean.setPrice(selectedPrice);

        Game game = new Game();
        game.setName(selectedGame);
        game.setPrice(selectedPrice);
        game.setPlatform(selectedPlatform);
        int index = Shop.getInstance().getGames().indexOf(game);
        System.out.println(index);
        try {
            yourShopController.removeGame(gameBean);
        } catch (SQLException e) {
            ErrorMessage.displayErrorMessage();
        }

        removeSelectedGame.setDisable(true);
        removeSelectedGame.setVisible(false);

        gamesList.getItems().clear();
        this.retrieveList();
        ordersTable.getItems().clear();
        this.retrieveOrdersTable();
    }

    public void showCompleteTable() {
        try {

            Parent root = FXMLLoader.load(Main.class.getResource("/GamesMarket/orders_table.fxml"));
            Stage stage = new Stage();
            Scene scene = new Scene(root);
            scene.setFill(Color.TRANSPARENT);
            stage.initStyle(StageStyle.TRANSPARENT);
            stage.setScene(scene);

            GaussianBlur blur = new GaussianBlur(55);
            ColorAdjust adj = new ColorAdjust(-0.1, -0.1, -0.1, -0.1);
            adj.setInput(blur);
            anchorPane.setEffect(adj);

            stage.showAndWait();
            refresh();
            anchorPane.setEffect(null);

        } catch (Exception e) {
            e.printStackTrace();
            e.getCause();
        }
    }

    private void retrieveOrdersTable() {
        List<OrderBean> orderBeans = null;
        try {
            orderBeans = yourShopController.retrieveOrders();
        } catch (SQLException e) {
            ErrorMessage.displayErrorMessage();
        }
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
            ordersObservableList.add(orders.get(i));
        }

        usernameCol.setCellValueFactory(new PropertyValueFactory<>("username"));
        gameCol.setCellValueFactory(new PropertyValueFactory<>("game"));
        platformCol.setCellValueFactory(new PropertyValueFactory<>("platform"));
        paymentCol.setCellValueFactory(new PropertyValueFactory<>("paymentMethod"));

        ordersTable.setItems(ordersObservableList);
    }


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        String shopname = ShopOwner.getInstance().getShop().getName();

        shopName.setText(shopname);
        shopAddress.setText(Shop.getInstance().getAddress() + ", " + Shop.getInstance().getCity() + ", " + Shop.getInstance().getCountry());
        this.retrieveImage();
        this.retrieveList();
        this.retrieveOrdersTable();
    }
}

