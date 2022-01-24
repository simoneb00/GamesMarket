package gamesmarket.control;

import gamesmarket.bean.RegisterCredentialsBean;
import gamesmarket.exceptions.DuplicatedEmailException;
import gamesmarket.exceptions.DuplicatedUsernameException;
import gamesmarket.model.dao.ShopOwnerDAO;
import gamesmarket.model.dao.UserDAO;
import java.sql.SQLException;


public class SignUpController {


    public void signUp(RegisterCredentialsBean registerCredentialsBean) throws DuplicatedEmailException, DuplicatedUsernameException, SQLException {

        String firstName = registerCredentialsBean.getFirstName();
        String lastName = registerCredentialsBean.getLastName();
        String username = registerCredentialsBean.getRegisterUsername();
        String password = registerCredentialsBean.getRegisterPassword();
        String email = registerCredentialsBean.getRegisterEmail();
        String typeOfUser = registerCredentialsBean.getTypeOfUser();

        if (typeOfUser.equals("User")) {
            UserDAO.registerUser(username, email, password, firstName, lastName);
        } else {
            ShopOwnerDAO.registerShopOwner(email, password, firstName, lastName);
        }
    }
}

