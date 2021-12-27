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
    exports GamesMarket.graphicControl.profile;
    opens GamesMarket.graphicControl.profile to javafx.fxml;
    exports GamesMarket.control.profile;
    opens GamesMarket.control.profile to javafx.fxml;

    opens GamesMarket.model to javafx.base;
}