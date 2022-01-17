package GamesMarket.model;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class Shop {
    private String owner;
    private String name;
    private String address;
    private String city;
    private String country;
    private List<Game> games = new ArrayList<>();
    private File imageFile;
    private static Shop instance = null;

    private Shop() {this.owner = ShopOwner.getInstance().getEmail();}

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public File getImageFile() {
        return imageFile;
    }

    public void setImageFile(File imageFile) {
        this.imageFile = imageFile;
    }

    public String getOwner() {
        return owner;
    }

    public void setOwner(String owner) {
        this.owner = owner;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Game> getGames() {
        return games;
    }

    public void setGames(List<Game> games) {
        this.games = games;
    }

    public static Shop getInstance() {
        if (Shop.instance == null)
            Shop.instance = new Shop();
        return instance;
    }
}
