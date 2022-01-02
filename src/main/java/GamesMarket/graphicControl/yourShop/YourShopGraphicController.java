package GamesMarket.graphicControl.yourShop;

import GamesMarket.bean.GameBean;
import GamesMarket.bean.ShopBean;
import GamesMarket.bean.ShopOwnerBean;
import GamesMarket.control.YourShopController;
import GamesMarket.graphicControl.navigation.NavigationButtons;
import GamesMarket.graphicControl.navigation.ShopOwnerNavigationButtons;
import GamesMarket.main.Main;
import GamesMarket.model.*;
import GamesMarket.model.DAO.ShopOwnerDAO;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.effect.ColorAdjust;
import javafx.scene.effect.GaussianBlur;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.net.URL;
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
            photoLabel.setText("");
        } catch (RuntimeException e) {
            photoLabel.setText("No photo selected.");
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
        yourShopController.removeGame(gameBean);

        removeSelectedGame.setDisable(true);
        removeSelectedGame.setVisible(false);

        gamesList.getItems().clear();
        this.retrieveList();
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        String shopname = ShopOwner.getInstance().getShop().getName();

        shopName.setText(shopname);
        shopAddress.setText(Shop.getInstance().getAddress() + ", " + Shop.getInstance().getCity() + ", " + Shop.getInstance().getCountry());
        this.retrieveImage();
        this.retrieveList();
    }
}

