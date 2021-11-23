package GamesMarket;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.effect.ColorAdjust;
import javafx.scene.effect.GaussianBlur;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class HomeController extends ButtonsHandler {

    @FXML
    private AnchorPane homePane;
    @FXML
    private Label homeLabel;


    public void SignInButtonController() {

        try {

            Parent root = FXMLLoader.load(getClass().getResource("login.fxml"));
            Stage loginStage = new Stage();
            Scene loginScene = new Scene(root, 600, 400);
            loginScene.setFill(Color.TRANSPARENT);
            loginStage.initStyle(StageStyle.TRANSPARENT);
            loginStage.setScene(loginScene);

            GaussianBlur blur = new GaussianBlur(55);
            ColorAdjust adj = new ColorAdjust(0, 0, 0, 0);
            adj.setInput(blur);
            homePane.setEffect(adj);
            loginStage.showAndWait();
            homePane.setEffect(null);

        } catch (Exception e) {
            e.printStackTrace();
            e.getCause();
        }

    }

    public void homeCloseButtonController(ActionEvent event) {
        try {
            Stage exitStage = new Stage();
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("exit.fxml"));
            Scene scene = new Scene(fxmlLoader.load(), 489, 314);
            scene.setFill(Color.TRANSPARENT);
            exitStage.initStyle(StageStyle.TRANSPARENT);
            exitStage.setScene(scene);

            GaussianBlur blur = new GaussianBlur(55);
            ColorAdjust adj = new ColorAdjust(0, 0, 0, 0);
            adj.setInput(blur);
            homePane.setEffect(adj);
            exitStage.showAndWait();
            homePane.setEffect(null);

        } catch (Exception e) {
            e.printStackTrace();
            e.getCause();
        }
    }

    public void yesExitController(ActionEvent event) {
        Platform.exit();
    }

    public void noExitController(ActionEvent event) {
        Stage stage = (Stage) ((Button) event.getSource()).getScene().getWindow();
        stage.close();
    }


}
