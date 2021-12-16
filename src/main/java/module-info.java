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

//    opens GamesMarket.main to javafx.fxml;
//    exports GamesMarket.main;

    exports GamesMarket.main;
    opens GamesMarket.main to javafx.fxml;
}