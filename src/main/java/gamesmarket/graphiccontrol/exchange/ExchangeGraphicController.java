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


    private final List<ExchangePost> exchangePosts = new ArrayList<>();
    private final ExchangeController exchangeController = new ExchangeController();

    public List<ExchangePost> retrieveExchange() throws IOException{

        List<ExchangePost> posts = new ArrayList<>();

        try {
            List<ExchangePostBean> beans = exchangeController.retrieveExchange();   // retrieve all posts for the logged user

            for (ExchangePostBean bean : beans) {
                ExchangePost exchangePost = new ExchangePost(
                        bean.getPostUsername(),
                        bean.getPostGame(),
                        bean.getPostPlatform(),
                        bean.getGameToGive(),
                        bean.getPlatformGameToGive(),
                        bean.getPostImageFile()
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

        for (ExchangePost post : posts) {

            try {
                FXMLLoader fxmlLoader = new FXMLLoader();
                fxmlLoader.setLocation(Main.class.getResource("/gamesmarket/exchangeItem.fxml"));
                AnchorPane anchorPane = fxmlLoader.load();  // loads the post

                ExchangeItemGraphicController itemController = fxmlLoader.getController();
                itemController.setData(post);

                if (column == 5) {
                    column = 0;
                    row++;
                }

                grid.add(anchorPane, column++, row);    // adds the post to the visible grid

                grid.setMinWidth(Region.USE_COMPUTED_SIZE);
                grid.setPrefWidth(Region.USE_COMPUTED_SIZE);
                grid.setMaxWidth(Region.USE_PREF_SIZE);

                grid.setMinHeight(Region.USE_COMPUTED_SIZE);
                grid.setPrefHeight(Region.USE_COMPUTED_SIZE);
                grid.setMaxHeight(Region.USE_PREF_SIZE);

                GridPane.setMargin(anchorPane, new Insets(20));


            } catch (IOException e) {
                ErrorMessage.displayErrorMessage();
            }
        }
        }

    @FXML
    public void search() {
        String search = searchBar.getText();
        grid.getChildren().clear();                             // makes the grid empty
        List<ExchangePost> searchedPosts = new ArrayList<>();

        for (ExchangePost exchangePost : exchangePosts) {
            if (exchangePost.getGame().toLowerCase().contains(search.toLowerCase())) {
                searchedPosts.add(exchangePost);        // a post is added to the grid only if it contains the searched characters
            }
        }

        this.showGrid(searchedPosts);       // shows only searched games
    }

}
