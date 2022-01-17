package GamesMarket.control;

import GamesMarket.bean.RegisterCredentialsBean;
import GamesMarket.exceptions.DuplicatedEmailException;
import GamesMarket.exceptions.DuplicatedUsernameException;
import GamesMarket.model.DAO.ShopOwnerDAO;
import GamesMarket.model.DAO.UserDAO;
import java.sql.SQLException;


public class SignUpController {


    public void signUp(RegisterCredentialsBean registerCredentialsBean) throws DuplicatedEmailException, DuplicatedUsernameException, SQLException {

        String firstName = registerCredentialsBean.getFirstName();
        String lastName = registerCredentialsBean.getLastName();
        String username = registerCredentialsBean.getUsername();
        String password = registerCredentialsBean.getPassword();
        String email = registerCredentialsBean.getEmail();
        String typeOfUser = registerCredentialsBean.getTypeOfUser();

        if (typeOfUser.equals("User")) {
            UserDAO.registerUser(username, email, password, firstName, lastName);
        } else {
            ShopOwnerDAO.registerShopOwner(email, password, firstName, lastName);
        }
    }
}

