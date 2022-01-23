package gamesmarket.graphic_control.mobile.exchange;

import gamesmarket.bean.ExchangePostBean;
import gamesmarket.control.exchange.ExchangeController;
import gamesmarket.exceptions.ErrorMessage;
import gamesmarket.graphic_control.mobile.ShopOwnerNavigationButtons;
import gamesmarket.main.Main;
import gamesmarket.model.ExchangePost;
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

public class ExchangeGraphicController extends ShopOwnerNavigationButtons implements Initializable {

    @FXML
    private GridPane grid;
    @FXML
    private TextField searchTF;

    private ExchangeController exchangeController = new ExchangeController();
    private List<ExchangePost> exchangePosts = new ArrayList<>();

    private void showGrid(List<ExchangePost> posts) {

        int c = 0;
        int r = 1;

        for (int i = 0; i < posts.size(); i++) {

            try {
                FXMLLoader fxmlLoader = new FXMLLoader();
                fxmlLoader.setLocation(Main.class.getResource("/gamesmarket/mobile/exchange_post.fxml"));
                AnchorPane ap = fxmlLoader.load();

                ExchangeItemGraphicController itemController = fxmlLoader.getController();
                itemController.setData(posts.get(i));

                if (c == 1) {
                    c = 0;
                    r++;
                }

                GridPane.setMargin(ap, new Insets(10));


            } catch (IOException e) {
                ErrorMessage.displayErrorMobile();
            }
        }
    }

    @FXML
    public void search() {
        String search = searchTF.getText();
        grid.getChildren().clear();
        List<ExchangePost> searchedPosts = new ArrayList<>();

        for (int i = 0; i < exchangePosts.size(); i++) {
            if (exchangePosts.get(i).getGame().toLowerCase().contains(search.toLowerCase())) {
                searchedPosts.add(exchangePosts.get(i));
            }
        }

        this.showGrid(searchedPosts);
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        gamesmarket.graphic_control.exchange.ExchangeGraphicController exchangeGraphicController = new gamesmarket.graphic_control.exchange.ExchangeGraphicController();
        try {
            exchangePosts.addAll(exchangeGraphicController.retrieveExchange());
            this.showGrid(exchangePosts);
        } catch (IOException e) {
            ErrorMessage.displayErrorMessage();
        }
    }
}
