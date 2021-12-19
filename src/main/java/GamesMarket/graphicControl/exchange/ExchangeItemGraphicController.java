package GamesMarket.graphicControl.exchange;

import GamesMarket.model.Game;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class ExchangeItemGraphicController {

    @FXML
    private Label gameLabel;

    @FXML
    private ImageView img;

    @FXML
    private Label username;

    private Game game;

    public void setData(Game game) {
        this.game = game;
        username.setText("test");
        gameLabel.setText(game.getName());
        Image image = new Image(getClass().getResourceAsStream(game.getImgSrc()));
        img.setImage(image);
    }
}
