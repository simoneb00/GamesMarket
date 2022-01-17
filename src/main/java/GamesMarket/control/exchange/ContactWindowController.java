package GamesMarket.control.exchange;

import GamesMarket.bean.UserBean;
import GamesMarket.model.DAO.UserDAO;

import java.sql.SQLException;
import java.util.List;

public class ContactWindowController {

    public UserBean retrieveCI(UserBean userBean) throws SQLException {
        List<String> list = UserDAO.retrieveContactInf(userBean.getUsername()); // retrieve contact information in this order: [email, tel, address, country]
        UserBean userBean1 = new UserBean();
        userBean1.setEmail(list.get(0));
        userBean1.setTel(list.get(1));
        userBean1.setAddress(list.get(2));
        userBean1.setCountry(list.get(3));
        return userBean1;
    }
}
