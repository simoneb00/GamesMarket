package gamesmarket.graphic_control.mobile;

import gamesmarket.main.Main;
import gamesmarket.model.ShopOwner;
import gamesmarket.model.User;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class NavigationButtons {

    protected Parent root;
    protected Stage stage;
    protected Scene scene;
    protected String css = "file:///C:/Users/Simone%20Bauco/IdeaProjects/GamesMarket/src/main/java/GamesMarket/css/style.css";

    @FXML
    public void shopOwnerForumButton(ActionEvent event) {
        try {
            root = FXMLLoader.load(Main.class.getResource("/gamesmarket/mobile/shop_owner_forum.fxml"));
            stage = (Stage)((Node)event.getSource()).getScene().getWindow();
            scene = new Scene(root);

            scene.getStylesheets().clear();
            scene.getStylesheets().add(css);

            stage.setScene(scene);
            stage.show();

        } catch (Exception e) {
            e.printStackTrace();
            e.getCause();
        }
    }

    @FXML
    public void yourShopButton(ActionEvent event) {
        if (ShopOwner.getInstance().getShop() == null)
            this.createNewShop(event);
        else {
            try {
                root = FXMLLoader.load(Main.class.getResource("/gamesmarket/mobile/your_shop.fxml"));
                stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                scene = new Scene(root);

                scene.getStylesheets().clear();
                scene.getStylesheets().add(css);

                stage.setScene(scene);
                stage.show();

            } catch (Exception e) {
                e.printStackTrace();
                e.getCause();
            }
        }
    }

    @FXML
    public void shopOwnerHomeButton(ActionEvent event) {
        try {
            root = FXMLLoader.load(Main.class.getResource("/gamesmarket/mobile/shop_owner_homepage.fxml"));
            stage = (Stage)((Node)event.getSource()).getScene().getWindow();
            scene = new Scene(root);

            scene.getStylesheets().clear();
            scene.getStylesheets().add(css);

            stage.setScene(scene);
            stage.show();

        } catch (Exception e) {
            e.printStackTrace();
            e.getCause();
        }
    }

    @FXML
    public void createNewShop(ActionEvent event) {
        try {
            root = FXMLLoader.load(Main.class.getResource("/gamesmarket/mobile/create_new_shop.fxml"));
            stage = (Stage)((Node)event.getSource()).getScene().getWindow();
            scene = new Scene(root);

            scene.getStylesheets().clear();
            scene.getStylesheets().add(css);

            stage.setScene(scene);
            stage.show();

        } catch (Exception e) {
            e.printStackTrace();
            e.getCause();
        }
    }

    @FXML
    public void homeButton(ActionEvent event) {

        try {
            root = FXMLLoader.load(Main.class.getResource("/gamesmarket/mobile/homepage.fxml"));
            stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            scene = new Scene(root);

            scene.getStylesheets().clear();
            scene.getStylesheets().add(css);

            stage.setScene(scene);
            stage.show();

        } catch (Exception e) {
            e.printStackTrace();
            e.getCause();
        }

    }

    @FXML
    public void profileButton(ActionEvent event) {
        if (User.getInstance().isLoggedIn()) {
            try {
                root = FXMLLoader.load(Main.class.getResource("/gamesmarket/mobile/profile.fxml"));
                stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                scene = new Scene(root);

                scene.getStylesheets().clear();
                scene.getStylesheets().add(css);

                stage.setScene(scene);
                stage.show();

            } catch (Exception e) {
                e.printStackTrace();
                e.getCause();
            }
        } else {
            this.loginButton(event);
        }

    }

    @FXML
    public void exchangeButton(ActionEvent event) {
        if (User.getInstance().isLoggedIn()) {
            try {
                root = FXMLLoader.load(Main.class.getResource("/gamesmarket/mobile/exchange.fxml"));
                stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                scene = new Scene(root);

                scene.getStylesheets().clear();
                scene.getStylesheets().add(css);

                stage.setScene(scene);
                stage.show();

            } catch (Exception e) {
                e.printStackTrace();
                e.getCause();
            }
        } else {
            this.loginButton(event);
        }


    }

    @FXML
    public void forumButton(ActionEvent event) {
        try {
            root = FXMLLoader.load(Main.class.getResource("/gamesmarket/mobile/forum.fxml"));
            stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            scene = new Scene(root);

            scene.getStylesheets().clear();
            scene.getStylesheets().add(css);

            stage.setScene(scene);
            stage.show();

        } catch (Exception e) {
            e.printStackTrace();
            e.getCause();
        }

    }

    @FXML
    public void shopButton(ActionEvent event) {
        try {
            root = FXMLLoader.load(Main.class.getResource("/gamesmarket/mobile/shop.fxml"));
            stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            scene = new Scene(root);

            scene.getStylesheets().clear();
            scene.getStylesheets().add(css);

            stage.setScene(scene);
            stage.show();

        } catch (Exception e) {
            e.printStackTrace();
            e.getCause();
        }

    }

    @FXML
    protected void loginButton(ActionEvent event) {
        try {
            root = FXMLLoader.load(Main.class.getResource("/gamesmarket/mobile/login.fxml"));
            stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            scene = new Scene(root);

            scene.getStylesheets().clear();
            scene.getStylesheets().add(css);

            stage.setScene(scene);
            stage.show();

        } catch (Exception e) {
            e.printStackTrace();
            e.getCause();
        }

    }


}
