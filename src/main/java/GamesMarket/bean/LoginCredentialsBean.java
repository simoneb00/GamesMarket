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

    public String getEmailAddress() {
        return this.emailAddress;
    }

    public String getPassword() {
        return this.password;
    }

    private boolean checkEmailAddress(String email) {

        Pattern pattern = Pattern.compile("^[\\w-_\\.+]*[\\w-_\\.]\\@([\\w]+\\.)+[\\w]+[\\w]$");
        Matcher matcher = pattern.matcher(email);
        if (matcher.matches()) return true;
        return false;

    }
}
