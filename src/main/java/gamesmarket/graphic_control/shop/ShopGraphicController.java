package gamesmarket.graphic_control.shop;

import gamesmarket.bean.ShopPostBean;
import gamesmarket.control.ShopController;
import gamesmarket.exceptions.ErrorMessage;
import gamesmarket.graphic_control.navigation.NavigationButtons;
import gamesmarket.main.Main;
import gamesmarket.model.*;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.effect.ColorAdjust;
import javafx.scene.effect.GaussianBlur;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Region;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class ShopGraphicController extends NavigationButtons implements Initializable {

    @FXML
    private AnchorPane anchorPane;
    @FXML
    private GridPane grid;
    @FXML
    private TextField searchBar;
    @FXML
    private Button signInButton;


    private ShopController shopController = new ShopController();
    private List<ShopPost> posts = new ArrayList<>();


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        try {

            List<ShopPostBean> postBeans = shopController.retrieveShop();

            for (int i = 0; i < postBeans.size(); i++) {
                ShopPost shopPost = new ShopPost();
                shopPost.setShopName(postBeans.get(i).getPostShopName());
                shopPost.setGame(postBeans.get(i).getShopPostGame());
                shopPost.setPrice(postBeans.get(i).getShopPostPrice());
                shopPost.setImageFile(postBeans.get(i).getShopPostImageFile());

                posts.add(shopPost);
            }

            if (User.getInstance().isLoggedIn() || ShopOwner.getInstance().isLoggedIn()) {
                signInButton.setVisible(false);
                signInButton.isDisabled();
            }

            this.showGrid(posts);
        } catch (SQLException | IOException e) {
            ErrorMessage.displayErrorMessage();
        }
    }

    public void profileButtonPressed(ActionEvent event) {
        if (User.getInstance().isLoggedIn()) {
            this.profileButton(event);
        } else {
            this.signInButtonPressed(event);
        }
    }

    public void exchangeButtonPressed(ActionEvent event) {
        if (User.getInstance().isLoggedIn()) {
            this.exchangeButton(event);
        } else {
            this.signInButtonPressed(event);
        }
    }

    public void signInButtonPressed(ActionEvent event) {
        try {

            Parent root = FXMLLoader.load(Main.class.getResource("/gamesmarket/login.fxml"));
            Stage loginStage = new Stage();
            Scene loginScene = new Scene(root, 600, 400);
            loginScene.setFill(Color.TRANSPARENT);
            loginStage.initStyle(StageStyle.TRANSPARENT);
            loginStage.setScene(loginScene);

            GaussianBlur blur = new GaussianBlur(55);
            ColorAdjust adj = new ColorAdjust(-0.1, -0.1, -0.1, -0.1);
            adj.setInput(blur);
            anchorPane.setEffect(adj);

            loginStage.showAndWait();
            if (User.getInstance().isLoggedIn()) {
                signInButton.setVisible(false);
                signInButton.setDisable(true);
            }
            anchorPane.setEffect(null);

        } catch (Exception e) {
            e.printStackTrace();
            e.getCause();
        }

        if (ShopOwner.getInstance().isLoggedIn()) {
            try {
                Parent root = FXMLLoader.load(Main.class.getResource("/gamesmarket/shopOwnerHome.fxml"));
                Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                Scene scene = new Scene(root);

                scene.getStylesheets().clear();
                scene.getStylesheets().add("file:///C:/Users/Simone%20Bauco/IdeaProjects/GamesMarket/src/main/java/GamesMarket/css/style.css");

                stage.setScene(scene);
                stage.show();

            } catch (Exception e) {
                e.printStackTrace();
                e.getCause();
            }
        }

    }

    private void showGrid(List<ShopPost> posts) {
        int column = 0;
        int row = 1;

        try {
            for (int i = 0; i < posts.size(); i++) {
                FXMLLoader fxmlLoader = new FXMLLoader();
                fxmlLoader.setLocation(Main.class.getResource("/gamesmarket/shopItem.fxml"));
                AnchorPane anchorPane = fxmlLoader.load();

                ShopPostGraphicController itemController = fxmlLoader.getController();
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

            }
        } catch(IOException e) {
            e.printStackTrace();
        }
    }

    public void search() {
        String search = searchBar.getText();
        grid.getChildren().clear();
        List<ShopPost> searchedPosts = new ArrayList<>();

        for (int i = 0; i < posts.size(); i++) {
            if (posts.get(i).getGame().toLowerCase().contains(search.toLowerCase()))
                searchedPosts.add(posts.get(i));
        }

        this.showGrid(searchedPosts);
    }





}
