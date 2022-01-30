package gamesmarket.graphiccontrol.mobile.profile;

import gamesmarket.bean.GameBean;
import gamesmarket.control.profile.UserProfileController;
import gamesmarket.exceptions.ErrorMessage;
import gamesmarket.graphiccontrol.mobile.NavigationButtons;
import gamesmarket.main.Main;
import gamesmarket.model.User;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import java.net.URL;
import java.sql.SQLException;
import java.util.List;
import java.util.Objects;
import java.util.ResourceBundle;

public class TradelistController extends NavigationButtons implements Initializable {

    @FXML
    private ListView<String> tradelist;
    @FXML
    private Button removeSelected;

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

    @FXML
    public void mouseClicked() {
        String tradelistSelected = tradelist.getSelectionModel().getSelectedItems().toString();     // gets selected game in tradelist
        if (!Objects.equals(tradelistSelected, "[]")) {
            tradelistSelected = tradelistSelected.replaceAll("[\\[\\]]", "");       // replace [] chars with blank ([game] -> game)
            String[] strings = tradelistSelected.split(" - ");
            this.selectedGame = strings[0];
            this.selectedPlatform = strings[1];
            removeSelected.setVisible(true);
            removeSelected.setDisable(false);
        }
    }

    @FXML
    public void removeSelectedGame() {
        try {
            GameBean gameBean = new GameBean();
            gameBean.setGamePlatform(selectedPlatform);
            gameBean.setGameName(selectedGame);

            userProfileController.removeFromTradelist(gameBean);

            removeSelected.setDisable(true);
            removeSelected.setVisible(false);

            // refresh page
            tradelist.getItems().clear();
            this.retrieveTradelist();

        } catch (SQLException e) {
            ErrorMessage.displayErrorMessage();
        }
    }

    @FXML
    public void addGame(ActionEvent event) {
        try {
            Parent root = FXMLLoader.load(Main.class.getResource("/gamesmarket/mobile/games_table.fxml"));
            this.show(root, event);

        } catch (Exception e) {
            ErrorMessage.displayErrorMobile();
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        this.retrieveTradelist();
    }
}
