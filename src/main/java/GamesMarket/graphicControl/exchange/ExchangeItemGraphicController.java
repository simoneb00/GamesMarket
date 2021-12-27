package GamesMarket.graphicControl.exchange;

import GamesMarket.model.ExchangePost;
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

    private ExchangePost exchangePost;

    public void setData(ExchangePost exchangePost) {
        this.exchangePost = exchangePost;
        username.setText(exchangePost.getUsername());
        gameLabel.setText(exchangePost.getGame());
        //Image image = new Image(getClass().getResourceAsStream(exchangePost.getImgSrc()));
        //img.setImage(image);
    }
}
