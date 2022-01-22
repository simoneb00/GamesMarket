package gamesmarket.control.exchange;

import gamesmarket.bean.UserBean;
import gamesmarket.model.DAO.UserDAO;

import java.sql.SQLException;
import java.util.List;

public class ContactWindowController {

    public UserBean retrieveCI(UserBean userBean) throws SQLException {
        List<String> list = UserDAO.retrieveContactInf(userBean.getUsername()); // retrieve contact information in this order: [email, tel, address, country]
        UserBean userBean1 = new UserBean();
        userBean1.setUserEmail(list.get(0));
        userBean1.setUserTel(list.get(1));
        userBean1.setUserAddress(list.get(2));
        userBean1.setUserCountry(list.get(3));
        return userBean1;
    }
}
