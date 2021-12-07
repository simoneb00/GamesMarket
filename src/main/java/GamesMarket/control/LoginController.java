package GamesMarket.control;

import GamesMarket.bean.LoginCredentialsBean;

public class LoginController {

    public boolean validateLogin(LoginCredentialsBean loginCredentialsBean) {

        String emailAddress = loginCredentialsBean.getEmailAddress();
        String password = loginCredentialsBean.getPassword();

        return true;
    }
}
