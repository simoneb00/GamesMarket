package GamesMarket.graphicControl.shop;

import GamesMarket.model.Game;
import GamesMarket.model.ShopPost;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class ItemGraphicController {

    @FXML
    private Label gameLabel;
    @FXML
    private ImageView img;
    @FXML
    private Label priceLabel;
    @FXML
    private Label shopNameLabel;


    public void setData(ShopPost shopPost) {
        gameLabel.setText(shopPost.getGame());
        priceLabel.setText("€ " + shopPost.getPrice());
        shopNameLabel.setText(shopPost.getShopName());
        Image image = new Image(shopPost.getImageFile().getAbsolutePath());
        img.setImage(image);
    }
}
