package GamesMarket.graphicControl.shop;

import GamesMarket.model.Game;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class ItemGraphicController {

    @FXML
    private ImageView img;

    @FXML
    private Label nameLabel;

    @FXML
    private Label priceLabel;

    private Game game;

    public void setData(Game game) {
        this.game = game;
        nameLabel.setText(game.getName());
        priceLabel.setText("$" + game.getPrice());
        Image image = new Image(getClass().getResourceAsStream(game.getImgSrc()));
        img.setImage(image);
    }
}
