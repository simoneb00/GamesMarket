package test;

import gamesmarket.bean.LoginCredentialsBean;
import gamesmarket.bean.RegisterCredentialsBean;
import gamesmarket.control.LoginController;
import gamesmarket.control.SignUpController;
import gamesmarket.exceptions.DuplicatedEmailException;
import gamesmarket.exceptions.DuplicatedUsernameException;
import gamesmarket.exceptions.InvalidEmailException;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertTrue;
import java.io.IOException;
import java.sql.SQLException;


public class TestRegisterUser {

    @Test
    public void testRegisterUser() throws InvalidEmailException, SQLException, DuplicatedEmailException, DuplicatedUsernameException, IOException {
        RegisterCredentialsBean registerCredentialsBean = new RegisterCredentialsBean();
        registerCredentialsBean.setFirstName("test_name");
        registerCredentialsBean.setLastName("test_last_name");
        registerCredentialsBean.setTypeOfUser("User");
        registerCredentialsBean.setRegisterEmail("test@test.com");
        registerCredentialsBean.setRegisterPassword("test");
        registerCredentialsBean.setRegisterUsername("test_username");

        SignUpController signUpController = new SignUpController();
        signUpController.signUp(registerCredentialsBean);

        assertTrue(isRegistered(registerCredentialsBean));
    }

    @Test
    public void testRegisterShopOwner() throws InvalidEmailException, SQLException, DuplicatedEmailException, DuplicatedUsernameException, IOException {
        RegisterCredentialsBean registerCredentialsBean = new RegisterCredentialsBean();
        registerCredentialsBean.setFirstName("test_name_so");
        registerCredentialsBean.setLastName("test_last_name_so");
        registerCredentialsBean.setTypeOfUser("Shop Owner");
        registerCredentialsBean.setRegisterEmail("shopowner@test.com");
        registerCredentialsBean.setRegisterPassword("test");


        SignUpController signUpController = new SignUpController();
        signUpController.signUp(registerCredentialsBean);

        assertTrue(isRegistered(registerCredentialsBean));
    }

    private boolean isRegistered(RegisterCredentialsBean registerCredentialsBean) throws InvalidEmailException, SQLException, IOException {
        LoginCredentialsBean loginCredentialsBean = new LoginCredentialsBean();
        loginCredentialsBean.setEmailAddress(registerCredentialsBean.getRegisterEmail());
        loginCredentialsBean.setPassword(registerCredentialsBean.getRegisterPassword());

        LoginController loginController = new LoginController();

        return loginController.validateLogin(loginCredentialsBean);
    }


}
