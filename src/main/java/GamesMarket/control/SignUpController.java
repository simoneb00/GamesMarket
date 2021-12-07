package GamesMarket.control;

import GamesMarket.bean.RegisterCredentialsBean;

public class SignUpController {

    RegisterCredentialsBean registerCredentialsBean = new RegisterCredentialsBean();

    public void signUp(RegisterCredentialsBean registerCredentialsBean) {

        String firstName = registerCredentialsBean.getFirstName();
        String lastName = registerCredentialsBean.getLastName();
        String username = registerCredentialsBean.getUsername();
        String password = registerCredentialsBean.getPassword();
        String email = registerCredentialsBean.getEmail();
        String typeOfUser = registerCredentialsBean.getTypeOfUser();

    }
}
