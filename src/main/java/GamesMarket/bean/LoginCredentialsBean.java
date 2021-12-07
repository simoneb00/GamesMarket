package GamesMarket.bean;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class LoginCredentialsBean {

    private String emailAddress;
    private String password;

    public void setEmailAddress(String email) throws Exception{

        if (checkEmailAddress(email)) {
            this.emailAddress = email;
        }
        else {
            Exception e = new Exception();
            throw e;
        }
    }

    public void setPassword(String pw) {
        this.password = pw;
    }

    private boolean checkEmailAddress(String email) {

        Pattern pattern = Pattern.compile("^(?=.{1,64}@)[A-Za-z0-9_-]+(\\\\.[A-Za-z0-9_-]+)*@[^-][A-Za-z0-9-]+(\\\\.[A-Za-z0-9-]+)*(\\\\.[A-Za-z]{2,})$");
        Matcher matcher = pattern.matcher(email);
        if (matcher.matches()) return true;
        return false;
    }
}
