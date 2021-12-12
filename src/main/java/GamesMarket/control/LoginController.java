package GamesMarket.control;

import GamesMarket.bean.LoginCredentialsBean;
import GamesMarket.model.User;

public class LoginController {

    public boolean validateLogin(LoginCredentialsBean loginCredentialsBean) {

        String emailAddress = loginCredentialsBean.getEmailAddress();
        String password = loginCredentialsBean.getPassword();

        User user = User.getInstance();
        user.setLoggedIn();

        return true;

    }
}
