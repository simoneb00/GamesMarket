package GamesMarket.graphicControl.mobile.shop;

import GamesMarket.bean.ShopPostBean;
import GamesMarket.control.ShopController;
import GamesMarket.exceptions.ErrorMessage;
import GamesMarket.graphicControl.mobile.NavigationButtons;
import GamesMarket.main.Main;
import GamesMarket.model.ShopPost;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Region;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class ShopGraphicController extends NavigationButtons implements Initializable {

    @FXML
    private GridPane grid;
    @FXML
    private TextField searchTF;

    private ShopController shopController = new ShopController();
    private List<ShopPost> posts = new ArrayList<>();

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        try {

            List<ShopPostBean> postBeans = shopController.retrieveShop();

            for (int i = 0; i < postBeans.size(); i++) {
                ShopPost shopPost = new ShopPost();
                shopPost.setShopName(postBeans.get(i).getShopName());
                shopPost.setGame(postBeans.get(i).getGame());
                shopPost.setPrice(postBeans.get(i).getPrice());
                shopPost.setImageFile(postBeans.get(i).getImageFile());

                posts.add(shopPost);
            }


            this.showGrid(posts);
        } catch (SQLException | IOException e) {
            ErrorMessage.displayErrorMessage();
        }
    }

    private void showGrid(List<ShopPost> posts) {
        int column = 0;
        int row = 1;

        try {
            for (int i = 0; i < posts.size(); i++) {
                FXMLLoader fxmlLoader = new FXMLLoader();
                fxmlLoader.setLocation(Main.class.getResource("/GamesMarket/mobile/shop_post.fxml"));
                AnchorPane anchorPane = fxmlLoader.load();

                ItemGraphicController itemController = fxmlLoader.getController();
                itemController.setData(posts.get(i));

                if (column == 1) {
                    column = 0;
                    row++;
                }

                grid.add(anchorPane, column++, row);

                grid.setMinWidth(Region.USE_COMPUTED_SIZE);
                grid.setPrefWidth(Region.USE_COMPUTED_SIZE);
                grid.setMaxWidth(Region.USE_PREF_SIZE);

                grid.setMinHeight(Region.USE_COMPUTED_SIZE);
                grid.setPrefHeight(Region.USE_COMPUTED_SIZE);
                grid.setMaxHeight(Region.USE_PREF_SIZE);

                GridPane.setMargin(anchorPane, new Insets(10));

            }
        } catch(IOException e) {
            e.printStackTrace();
        }
    }


    @FXML
    public void search() {
        String search = searchTF.getText();
        grid.getChildren().clear();
        List<ShopPost> searchedPosts = new ArrayList<>();

        for (int i = 0; i < posts.size(); i++) {
            if (posts.get(i).getGame().toLowerCase().contains(search.toLowerCase()))
                searchedPosts.add(posts.get(i));
        }

        this.showGrid(searchedPosts);
    }
}
