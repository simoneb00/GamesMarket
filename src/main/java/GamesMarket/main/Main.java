package GamesMarket.main;


import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;


public class Main extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        Parent root = FXMLLoader.load(Main.class.getResource("/GamesMarket/home.fxml"));
        Scene scene = new Scene(root);

        File f = new File("src/main/java/GamesMarket/main/style.css");
        scene.getStylesheets().clear();
        //scene.getStylesheets().add("file:///C:/Users/Public/GamesMarket/src/main/java/GamesMarket/main/style.css");
        scene.getStylesheets().add("file:///C:/Users/Simone%20Bauco/IdeaProjects/GamesMarket/src/main/java/GamesMarket/main/style.css");

        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) { launch(); }
}
