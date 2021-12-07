package GamesMarket.model;

public class User {

    private String firstName;
    private String lastName;
    private String username;
    private String password;
    private String emailAddress;

    public User(String firstName, String lastName, String username, String password, String emailAddress) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.username = username;
        this.password = password;
        this.emailAddress = emailAddress;
    }
}
