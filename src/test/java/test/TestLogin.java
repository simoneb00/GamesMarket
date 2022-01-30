package test;

import gamesmarket.bean.LoginCredentialsBean;
import gamesmarket.control.LoginController;
import gamesmarket.exceptions.InvalidEmailException;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;


import java.io.IOException;
import java.sql.SQLException;

public class TestLogin {

    @Test
    public void testSuccessfulLogin() throws InvalidEmailException, SQLException, IOException {
        LoginController loginController = new LoginController();
        LoginCredentialsBean loginCredentialsBean = new LoginCredentialsBean();


        loginCredentialsBean.setEmailAddress("simone@gmail.com");
        loginCredentialsBean.setPassword("simone");
        boolean ret = loginController.validateLogin(loginCredentialsBean);

        assertTrue(ret);
    }

    @Test
    public void testUnsuccessfulLogin() throws InvalidEmailException, SQLException, IOException {
        LoginController loginController = new LoginController();
        LoginCredentialsBean loginCredentialsBean = new LoginCredentialsBean();


        loginCredentialsBean.setEmailAddress("notregistereduser@gmail.com");
        loginCredentialsBean.setPassword("test");
        boolean ret = loginController.validateLogin(loginCredentialsBean);

        assertFalse(ret);
    }

}
