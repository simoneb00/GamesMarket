package GamesMarket.exchange;

import GamesMarket.bean.UserBean;
import GamesMarket.model.DAO.UserDAO;

import java.util.ArrayList;
import java.util.List;

public class ContactWindowController {

    public List<String> retrieveCI(UserBean userBean) {
        UserDAO userDAO = new UserDAO();
        List<String> list = userDAO.retrieveContactInf(userBean.getUsername());
        return list;
    }
}
