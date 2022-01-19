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

    opens gamesmarket.graphic_control to javafx.fxml;
    exports gamesmarket.graphic_control;

    opens gamesmarket.graphic_control.shop to javafx.fxml;
    exports gamesmarket.graphic_control.shop;

    exports gamesmarket.main;
    opens gamesmarket.main to javafx.fxml;

    opens gamesmarket.graphic_control.forum to javafx.fxml;
    exports gamesmarket.graphic_control.forum;

    opens gamesmarket.graphic_control.exchange to javafx.fxml;
    exports gamesmarket.graphic_control.exchange;
    exports gamesmarket.graphic_control.userProfile;
    opens gamesmarket.graphic_control.userProfile to javafx.fxml;
    exports gamesmarket.control.profile;
    opens gamesmarket.control.profile to javafx.fxml;

    opens gamesmarket.model to javafx.base;
    exports gamesmarket.control.exchange;
    opens gamesmarket.control.exchange to javafx.fxml;
    exports gamesmarket.graphic_control.home;
    opens gamesmarket.graphic_control.home to javafx.fxml;
    exports gamesmarket.graphic_control.navigation;
    opens gamesmarket.graphic_control.navigation to javafx.fxml;

    exports gamesmarket.graphic_control.yourShop to javafx.fxml;
    opens gamesmarket.graphic_control.yourShop;
    exports gamesmarket.graphic_control.signup;
    opens gamesmarket.graphic_control.signup to javafx.fxml;
    exports gamesmarket.graphic_control.login;
    opens gamesmarket.graphic_control.login to javafx.fxml;

    opens gamesmarket.graphic_control.mobile to javafx.fxml;
    exports gamesmarket.graphic_control.mobile;
    opens gamesmarket.graphic_control.mobile.home to javafx.fxml;
    exports gamesmarket.graphic_control.mobile.home;
    opens gamesmarket.graphic_control.mobile.login to javafx.fxml;
    exports gamesmarket.graphic_control.mobile.login;
    opens gamesmarket.graphic_control.mobile.profile to javafx.fxml;
    exports gamesmarket.graphic_control.mobile.profile;
    opens gamesmarket.exceptions to javafx.fxml;
    exports gamesmarket.exceptions;
    opens gamesmarket.graphic_control.mobile.forum to javafx.fxml;
    exports gamesmarket.graphic_control.mobile.forum;
    opens gamesmarket.graphic_control.mobile.shop to javafx.fxml;
    exports gamesmarket.graphic_control.mobile.shop;
    opens gamesmarket.graphic_control.mobile.exchange to javafx.fxml;
    exports gamesmarket.graphic_control.mobile.exchange;
    opens gamesmarket.graphic_control.mobile.yourShop to javafx.fxml;
    exports gamesmarket.graphic_control.mobile.yourShop;
    opens gamesmarket.graphic_control.mobile.signup to javafx.fxml;
    exports gamesmarket.graphic_control.mobile.signup;
}