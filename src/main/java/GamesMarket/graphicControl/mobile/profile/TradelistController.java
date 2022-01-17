package GamesMarket.graphicControl.mobile.profile;

import GamesMarket.bean.GameBean;
import GamesMarket.control.profile.UserProfileController;
import GamesMarket.exceptions.ErrorMessage;
import GamesMarket.graphicControl.mobile.NavigationButtons;
import GamesMarket.main.Main;
import GamesMarket.model.User;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.stage.Stage;

import javax.security.auth.callback.NameCallback;
import java.net.URL;
import java.sql.SQLException;
import java.util.List;
import java.util.ResourceBundle;

public class TradelistController extends NavigationButtons implements Initializable {

    @FXML
    private ListView<String> tradelist;
    @FXML
    private Button removeSelected;

    private Parent root;
    private Stage stage;
    private Scene scene;
    private String tradelistSelected;
    private String selectedGame;
    private String selectedPlatform;
    private UserProfileController userProfileController = new UserProfileController();

    private void retrieveTradelist() {
        List<String> tl = User.getInstance().getTradelist();

        if (tl != null) {
            for (int i = 0; i < tl.size(); i++) {
                tradelist.getItems().add(tl.get(i));
            }
        }
    }

    public void mouseClicked() {
        tradelistSelected = tradelist.getSelectionModel().getSelectedItems().toString();
        if (tradelistSelected != "[]") {
            tradelistSelected = tradelistSelected.replaceAll("\\[|\\]", "");
            String[] strings = tradelistSelected.split(" - ");
            selectedGame = strings[0];
            selectedPlatform = strings[1];
            removeSelected.setVisible(true);
            removeSelected.setDisable(false);
        }
    }

    public void removeSelectedGame() {
        try {
            GameBean gameBean = new GameBean();
            gameBean.setPlatform(selectedPlatform);
            gameBean.setName(selectedGame);

            userProfileController.removeFromTradelist(gameBean);

            removeSelected.setDisable(true);
            removeSelected.setVisible(false);

            tradelist.getItems().clear();
            this.retrieveTradelist();

        } catch (SQLException e) {
            ErrorMessage.displayErrorMessage();
        }
    }

    public void addGame(ActionEvent event) {
        try {
            root = FXMLLoader.load(Main.class.getResource("/GamesMarket/mobile/games_table.fxml"));
            stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            scene = new Scene(root);

            scene.getStylesheets().clear();
            scene.getStylesheets().add("file:///C:/Users/Simone%20Bauco/IdeaProjects/GamesMarket/src/main/java/GamesMarket/css/style.css");

            stage.setScene(scene);
            stage.show();

        } catch (Exception e) {
            e.printStackTrace();
            e.getCause();
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        this.retrieveTradelist();
    }
}
