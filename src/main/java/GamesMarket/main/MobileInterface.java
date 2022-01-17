package GamesMarket.main;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.io.File;

public class MobileInterface extends Application {
    @Override
    public void start(Stage stage) throws Exception {
        Parent root = FXMLLoader.load(Main.class.getResource("/GamesMarket/mobile/homepage.fxml"));
        Scene scene = new Scene(root);

        scene.getStylesheets().clear();
        scene.getStylesheets().add("file:///C:/Users/Simone%20Bauco/IdeaProjects/GamesMarket/src/main/java/GamesMarket/css/style.css");

        stage.getIcons().add(new Image("file:///C:/Users/Simone%20Bauco/IdeaProjects/GamesMarket/src/main/resources/images/GamesMarketLogo.png"));
        stage.setTitle("GamesMarket");
        stage.setScene(scene);
        stage.show();
    }
}
