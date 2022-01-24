package gamesmarket.graphic_control.mobile.shop;

import gamesmarket.bean.ShopPostBean;
import gamesmarket.control.ShopController;
import gamesmarket.exceptions.ErrorMessage;
import gamesmarket.graphic_control.mobile.NavigationButtons;
import gamesmarket.main.Main;
import gamesmarket.model.ShopPost;
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
        gamesmarket.graphic_control.shop.ShopGraphicController shopGraphicController = new gamesmarket.graphic_control.shop.ShopGraphicController();
        this.posts = shopGraphicController.retrievePosts();
        this.showGrid(posts);
    }

    private void showGrid(List<ShopPost> posts) {
        int col = 0;
        int row = 1;

        try {
            for (int i = 0; i < posts.size(); i++) {
                FXMLLoader fxmlLoader = new FXMLLoader();
                fxmlLoader.setLocation(Main.class.getResource("/gamesmarket/mobile/shop_post.fxml"));
                AnchorPane anchorPane = fxmlLoader.load();

                ItemGraphicController itemController = fxmlLoader.getController();
                itemController.setData(posts.get(i));

                if (col == 1) {
                    col = 0;
                    row++;
                }

                grid.add(anchorPane, col++, row);
                GridPane.setMargin(anchorPane, new Insets(10));

            }
        } catch(IOException e) {
            e.printStackTrace();
        }
    }


    @FXML
    public void search() {
        String searched = searchTF.getText();
        List<ShopPost> searchedPosts = new ArrayList<>();

        for (int i = 0; i < posts.size(); i++) {
            if (posts.get(i).getGame().toLowerCase().contains(searched.toLowerCase()))
                searchedPosts.add(posts.get(i));
        }

        grid.getChildren().clear();
        this.showGrid(searchedPosts);
    }
}
