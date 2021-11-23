package GamesMarket;

public class LoginController {

    /*
    public void validateLogin(String username, String password) {
        DatabaseConnection connectNow = new DatabaseConnection();
        Connection connectDB = connectNow.getConnection();

        String verifyLogin = "SELECT count(1) FROM user_account WHERE username = '" + username + "' AND password = '" + password + "'";

        try {

            Statement statement = connectDB.createStatement();
            ResultSet queryResult = statement.executeQuery(verifyLogin);

            while(queryResult.next()){

                if (queryResult.getInt(1) == 1) {
                    loginLabel.setText("Welcome!");
                    isLoggedIn = true;
                    loginStage.close();
                } else {
                    loginLabel.setText("Invalid login. Please try again.");
                    isLoggedIn = false;
                }

            }

        } catch (Exception e) {
            e.printStackTrace();
            e.getCause();
        }
    }
    */

    public boolean validateLogin(String username, String password) {
        return true;
    }
}