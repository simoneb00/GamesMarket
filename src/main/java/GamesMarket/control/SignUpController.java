package GamesMarket.control;

import GamesMarket.bean.RegisterCredentialsBean;
import GamesMarket.model.ShopOwner;
import GamesMarket.model.User;

public class SignUpController {

    RegisterCredentialsBean registerCredentialsBean = new RegisterCredentialsBean();

    public void signUp(RegisterCredentialsBean registerCredentialsBean) {

        String firstName = registerCredentialsBean.getFirstName();
        String lastName = registerCredentialsBean.getLastName();
        String username = registerCredentialsBean.getUsername();
        String password = registerCredentialsBean.getPassword();
        String email = registerCredentialsBean.getEmail();
        String typeOfUser = registerCredentialsBean.getTypeOfUser();

        if (typeOfUser.equals("User")) {
            User user = new User(firstName, lastName, username, password, email);
        } else {
            ShopOwner shopOwner = new ShopOwner(firstName, lastName, email, password);
        }
    }
}
