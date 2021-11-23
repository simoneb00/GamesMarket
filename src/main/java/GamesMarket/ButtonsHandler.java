package GamesMarket;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.stage.StageStyle;


public class ButtonsHandler {

    public void homeButtonController(ActionEvent event) {

        try {
            Stage stage = new Stage();
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("homepage.fxml"));
            Scene scene = new Scene(fxmlLoader.load(), 1000, 703);
            scene.setFill(Color.TRANSPARENT);
            stage.initStyle(StageStyle.TRANSPARENT);
            stage.setScene(scene);
            stage.show();

            Stage stage1 = (Stage) ((Button) event.getSource()).getScene().getWindow();
            stage1.setIconified(true);
            stage1.close();

        } catch (Exception e) {
            e.printStackTrace();
            e.getCause();
        }

    }

    public void shopButtonController(ActionEvent event) {

        try {
            Stage stage = new Stage();
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("shop.fxml"));
            Scene scene = new Scene(fxmlLoader.load(), 1000, 703);
            scene.setFill(Color.TRANSPARENT);
            stage.initStyle(StageStyle.TRANSPARENT);
            stage.setScene(scene);
            stage.show();

            Stage stage1 = (Stage) ((Button) event.getSource()).getScene().getWindow();
            stage1.setIconified(true);
            stage1.close();

        } catch (Exception e) {
            e.printStackTrace();
            e.getCause();
        }
    }

    public void exchangeButtonController(ActionEvent event) {

        try {
            Stage stage = new Stage();
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("exchange.fxml"));
            Scene scene = new Scene(fxmlLoader.load(), 1000, 703);
            scene.setFill(Color.TRANSPARENT);
            stage.initStyle(StageStyle.TRANSPARENT);
            stage.setScene(scene);
            stage.show();

            Stage stage1 = (Stage) ((Button) event.getSource()).getScene().getWindow();
            stage1.setIconified(true);
            stage1.close();

        } catch (Exception e) {
            e.printStackTrace();
            e.getCause();
        }

    }

    public void forumButtonController(ActionEvent event) {

        try {
            Stage stage = new Stage();
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("forum.fxml"));
            Scene scene = new Scene(fxmlLoader.load(), 1000, 703);
            scene.setFill(Color.TRANSPARENT);
            stage.initStyle(StageStyle.TRANSPARENT);
            stage.setScene(scene);
            stage.show();

            Stage stage1 = (Stage) ((Button) event.getSource()).getScene().getWindow();
            stage1.setIconified(true);
            stage1.close();

        } catch (Exception e) {
            e.printStackTrace();
            e.getCause();
        }

    }

    public void profileButtonController(ActionEvent event) {

        try {
            Stage stage = new Stage();
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("profile.fxml"));
            Scene scene = new Scene(fxmlLoader.load(), 1000, 703);
            scene.setFill(Color.TRANSPARENT);
            stage.initStyle(StageStyle.TRANSPARENT);
            stage.setScene(scene);
            stage.show();

            Stage stage1 = (Stage) ((Button) event.getSource()).getScene().getWindow();
            stage1.setIconified(true);
            stage1.close();

        } catch (Exception e) {
            e.printStackTrace();
            e.getCause();
        }
    }

}

