module login.loginwindow {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.web;

    requires org.controlsfx.controls;
    requires com.dlsc.formsfx;
    requires validatorfx;
    requires org.kordamp.ikonli.javafx;
    requires org.kordamp.bootstrapfx.core;
    requires eu.hansolo.tilesfx;
    requires java.sql;
    requires java.desktop;
    requires javafx.graphics;
    requires java.security.jgss;

    opens gamesmarket.control to javafx.fxml;
    exports gamesmarket.control;

    opens gamesmarket.bean to javafx.fxml;
    exports gamesmarket.bean;

    opens gamesmarket.graphiccontrol to javafx.fxml;
    exports gamesmarket.graphiccontrol;

    opens gamesmarket.graphiccontrol.shop to javafx.fxml;
    exports gamesmarket.graphiccontrol.shop;

    exports gamesmarket.main;
    opens gamesmarket.main to javafx.fxml;

    opens gamesmarket.graphiccontrol.forum to javafx.fxml;
    exports gamesmarket.graphiccontrol.forum;

    opens gamesmarket.graphiccontrol.exchange to javafx.fxml;
    exports gamesmarket.graphiccontrol.exchange;
    exports gamesmarket.graphiccontrol.userProfile;
    opens gamesmarket.graphiccontrol.userProfile to javafx.fxml;
    exports gamesmarket.control.profile;
    opens gamesmarket.control.profile to javafx.fxml;

    opens gamesmarket.model to javafx.base;
    exports gamesmarket.control.exchange;
    opens gamesmarket.control.exchange to javafx.fxml;
    exports gamesmarket.graphiccontrol.home;
    opens gamesmarket.graphiccontrol.home to javafx.fxml;
    exports gamesmarket.graphiccontrol.navigation;
    opens gamesmarket.graphiccontrol.navigation to javafx.fxml;

    exports gamesmarket.graphiccontrol.yourShop to javafx.fxml;
    opens gamesmarket.graphiccontrol.yourShop;
    exports gamesmarket.graphiccontrol.signup;
    opens gamesmarket.graphiccontrol.signup to javafx.fxml;
    exports gamesmarket.graphiccontrol.login;
    opens gamesmarket.graphiccontrol.login to javafx.fxml;

    opens gamesmarket.graphiccontrol.mobile to javafx.fxml;
    exports gamesmarket.graphiccontrol.mobile;
    opens gamesmarket.graphiccontrol.mobile.home to javafx.fxml;
    exports gamesmarket.graphiccontrol.mobile.home;
    opens gamesmarket.graphiccontrol.mobile.login to javafx.fxml;
    exports gamesmarket.graphiccontrol.mobile.login;
    opens gamesmarket.graphiccontrol.mobile.profile to javafx.fxml;
    exports gamesmarket.graphiccontrol.mobile.profile;
    opens gamesmarket.exceptions to javafx.fxml;
    exports gamesmarket.exceptions;
    opens gamesmarket.graphiccontrol.mobile.forum to javafx.fxml;
    exports gamesmarket.graphiccontrol.mobile.forum;
    opens gamesmarket.graphiccontrol.mobile.shop to javafx.fxml;
    exports gamesmarket.graphiccontrol.mobile.shop;
    opens gamesmarket.graphiccontrol.mobile.exchange to javafx.fxml;
    exports gamesmarket.graphiccontrol.mobile.exchange;
    opens gamesmarket.graphiccontrol.mobile.yourShop to javafx.fxml;
    exports gamesmarket.graphiccontrol.mobile.yourShop;
    opens gamesmarket.graphiccontrol.mobile.signup to javafx.fxml;
    exports gamesmarket.graphiccontrol.mobile.signup;
}