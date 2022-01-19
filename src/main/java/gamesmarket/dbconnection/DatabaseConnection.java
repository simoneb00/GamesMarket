package gamesmarket.dbconnection;

import gamesmarket.exceptions.ErrorMessage;

import java.sql.Connection;
import java.sql.DriverManager;

public class DatabaseConnection {
    private static Connection databaseLink = null;

    private DatabaseConnection() {}

    public static Connection getConnection(){

        if (databaseLink == null) {
            String databaseUser = System.getenv("USERNAME");
            String databasePassword = System.getenv("PASSWORD");
            String url = System.getenv("URL");

            try {
                Class.forName(System.getenv("DRIVER_CLASS_NAME"));
                databaseLink = DriverManager.getConnection(url, databaseUser, databasePassword);

            } catch (Exception e) {
                ErrorMessage.displayErrorMessage();
            }
        }

        return databaseLink;

    }

}
