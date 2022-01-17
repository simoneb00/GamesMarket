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

    opens GamesMarket.control to javafx.fxml;
    exports GamesMarket.control;

    opens GamesMarket.bean to javafx.fxml;
    exports GamesMarket.bean;

    opens GamesMarket.graphicControl to javafx.fxml;
    exports GamesMarket.graphicControl;

    opens GamesMarket.graphicControl.shop to javafx.fxml;
    exports GamesMarket.graphicControl.shop;

    exports GamesMarket.main;
    opens GamesMarket.main to javafx.fxml;

    opens GamesMarket.graphicControl.forum to javafx.fxml;
    exports GamesMarket.graphicControl.forum;

    opens GamesMarket.graphicControl.exchange to javafx.fxml;
    exports GamesMarket.graphicControl.exchange;
    exports GamesMarket.graphicControl.userProfile;
    opens GamesMarket.graphicControl.userProfile to javafx.fxml;
    exports GamesMarket.control.profile;
    opens GamesMarket.control.profile to javafx.fxml;

    opens GamesMarket.model to javafx.base;
    exports GamesMarket.control.exchange;
    opens GamesMarket.control.exchange to javafx.fxml;
    exports GamesMarket.graphicControl.home;
    opens GamesMarket.graphicControl.home to javafx.fxml;
    exports GamesMarket.graphicControl.navigation;
    opens GamesMarket.graphicControl.navigation to javafx.fxml;

    exports GamesMarket.graphicControl.yourShop to javafx.fxml;
    opens GamesMarket.graphicControl.yourShop;
    exports GamesMarket.graphicControl.signup;
    opens GamesMarket.graphicControl.signup to javafx.fxml;
    exports GamesMarket.graphicControl.login;
    opens GamesMarket.graphicControl.login to javafx.fxml;

    opens GamesMarket.graphicControl.mobile to javafx.fxml;
    exports GamesMarket.graphicControl.mobile;
    opens GamesMarket.graphicControl.mobile.home to javafx.fxml;
    exports GamesMarket.graphicControl.mobile.home;
    opens GamesMarket.graphicControl.mobile.login to javafx.fxml;
    exports GamesMarket.graphicControl.mobile.login;
    opens GamesMarket.graphicControl.mobile.profile to javafx.fxml;
    exports GamesMarket.graphicControl.mobile.profile;
    opens GamesMarket.exceptions to javafx.fxml;
    exports GamesMarket.exceptions;
    opens GamesMarket.graphicControl.mobile.forum to javafx.fxml;
    exports GamesMarket.graphicControl.mobile.forum;
    opens GamesMarket.graphicControl.mobile.shop to javafx.fxml;
    exports GamesMarket.graphicControl.mobile.shop;
    opens GamesMarket.graphicControl.mobile.exchange to javafx.fxml;
    exports GamesMarket.graphicControl.mobile.exchange;
    opens GamesMarket.graphicControl.mobile.yourShop to javafx.fxml;
    exports GamesMarket.graphicControl.mobile.yourShop;
    opens GamesMarket.graphicControl.mobile.signup to javafx.fxml;
    exports GamesMarket.graphicControl.mobile.signup;
}