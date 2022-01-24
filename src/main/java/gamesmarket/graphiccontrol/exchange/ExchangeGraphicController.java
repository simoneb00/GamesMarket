package gamesmarket.graphiccontrol.exchange;

import gamesmarket.bean.ExchangePostBean;
import gamesmarket.control.exchange.ExchangeController;
import gamesmarket.exceptions.ErrorMessage;
import gamesmarket.graphiccontrol.navigation.NavigationButtons;
import gamesmarket.main.Main;
import gamesmarket.model.ExchangePost;
import gamesmarket.model.ShopOwner;
import gamesmarket.model.User;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
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

public class ExchangeGraphicController extends NavigationButtons implements Initializable {

    @FXML
    private GridPane grid;
    @FXML
    private Button signInButton;
    @FXML
    private TextField searchBar;


    private List<ExchangePost> exchangePosts = new ArrayList<>();
    private ExchangeController exchangeController = new ExchangeController();

    public List<ExchangePost> retrieveExchange() throws IOException{

        List<ExchangePost> posts = new ArrayList<>();

        try {
            List<ExchangePostBean> beans = exchangeController.retrieveExchange();

            for (int i = 0; i < beans.size(); i++) {
                ExchangePost exchangePost = new ExchangePost(
                        beans.get(i).getPostUsername(),
                        beans.get(i).getPostGame(),
                        beans.get(i).getPostPlatform(),
                        beans.get(i).getGameToGive(),
                        beans.get(i).getPlatformGameToGive(),
                        beans.get(i).getPostImageFile()
                );

                posts.add(exchangePost);
            }
        } catch (SQLException e) {
            ErrorMessage.displayErrorMessage();
        }

        return posts;
    }


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        try {
            exchangePosts.addAll(this.retrieveExchange());

            if (User.getInstance().isLoggedIn() || ShopOwner.getInstance().isLoggedIn()) {
                signInButton.setVisible(false);
                signInButton.isDisabled();
            }

            this.showGrid(exchangePosts);

        } catch (IOException e) {
            ErrorMessage.displayErrorMessage();
        }

    }

    private void showGrid(List<ExchangePost> posts) {

            int column = 0;
            int row = 1;

            for (int i = 0; i < posts.size(); i++) {

                try {
                    FXMLLoader fxmlLoader = new FXMLLoader();
                    fxmlLoader.setLocation(Main.class.getResource("/gamesmarket/exchangeItem.fxml"));
                    AnchorPane anchorPane = fxmlLoader.load();

                    ExchangeItemGraphicController itemController = fxmlLoader.getController();
                    itemController.setData(posts.get(i));

                    if (column == 5) {
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

                    GridPane.setMargin(anchorPane, new Insets(20));


                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

    @FXML
    public void search() {
        String search = searchBar.getText();
        grid.getChildren().clear();
        List<ExchangePost> searchedPosts = new ArrayList<>();

        for (int i = 0; i < exchangePosts.size(); i++) {
            if (exchangePosts.get(i).getGame().toLowerCase().contains(search.toLowerCase())) {
                searchedPosts.add(exchangePosts.get(i));
            }
        }

        this.showGrid(searchedPosts);
    }

}