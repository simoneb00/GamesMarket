package gamesmarket.bean;

import gamesmarket.exceptions.InvalidEmailException;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class LoginCredentialsBean {

    private String emailAddress;
    private String password;

    public void setEmailAddress(String email) throws InvalidEmailException {

        if (checkEmailAddress(email)) {
            this.emailAddress = email;
        }
        else {
            throw new InvalidEmailException();
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

        Pattern pattern = Pattern.compile("^[\\w-\\.]+@([\\w-]+\\.)+[\\w-]{2,4}$");
        Matcher matcher = pattern.matcher(email);
        return matcher.matches();

    }
}
