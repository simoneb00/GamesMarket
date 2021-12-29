package GamesMarket.control;

import GamesMarket.bean.GameBean;
import GamesMarket.bean.ShopBean;
import GamesMarket.bean.ShopOwnerBean;
import GamesMarket.model.DAO.ShopDAO;
import GamesMarket.model.DAO.ShopOwnerDAO;
import GamesMarket.model.Game;
import GamesMarket.model.Shop;
import GamesMarket.model.ShopOwner;
import javafx.stage.FileChooser;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class YourShopController {

    private ShopDAO shopDAO = new ShopDAO();

    public ShopBean retrieveShopName(ShopOwnerBean shopOwnerBean) {
        String shopName = shopDAO.retrieveShopName(shopOwnerBean.getEmail());
        if (shopName != null) {
            ShopOwner.getInstance().setShop(Shop.getInstance());
            ShopOwner.getInstance().getShop().setName(shopName);
            ShopBean shopBean = new ShopBean();
            shopBean.setName(shopName);
            return shopBean;
        }

        return null;
    }

    public ShopBean retrieveImageFile() {
        ShopBean shopBean = new ShopBean();
        File file = shopDAO.retrievePhoto(ShopOwner.getInstance().getEmail());
        shopBean.setImageFile(file);
        ShopOwner.getInstance().getShop().setImageFile(file);
        return shopBean;
    }

    public void updatePhoto() {
        FileChooser fileChooser = new FileChooser();
        File selectedFile = fileChooser.showOpenDialog(null);
        String path = selectedFile.getAbsolutePath();

        shopDAO.updatePhoto(path);
        ShopOwner.getInstance().getShop().setImageFile(shopDAO.retrievePhoto(ShopOwner.getInstance().getEmail()));
    }

    public List<GameBean> retrieveList() {
        List<GameBean> list = new ArrayList<>();
        List<Game> games = shopDAO.retrieveList(ShopOwner.getInstance().getEmail());
        ShopOwner.getInstance().getShop().setGames(games);

        for (int i = 0; i < games.size(); i++) {
            GameBean gameBean = new GameBean();
            gameBean.setName(games.get(i).getName());
            gameBean.setPlatform(games.get(i).getPlatform());
            gameBean.setPrice(games.get(i).getPrice());
            list.add(gameBean);

        }

        return list;
    }

    public void removeGame(GameBean gameBean) {
        String name = gameBean.getName();
        String platform = gameBean.getPlatform();
        double price = gameBean.getPrice();

        shopDAO.removeGameFromSale(name, platform, price);
    }

    public void createNewShop(ShopBean shopBean) {
        String name = shopBean.getName();
        String address = shopBean.getAddress();
        String country = shopBean.getCountry();
        String city = shopBean.getCity();
        shopDAO.createNewShop(name, address, city, country);
        Shop.getInstance().setName(name);
        Shop.getInstance().setAddress(address);
        Shop.getInstance().setCity(city);
        Shop.getInstance().setCountry(country);
        ShopOwner.getInstance().setShop(Shop.getInstance());
    }
}
