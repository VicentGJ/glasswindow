module quantity.glasswindow {
    requires javafx.graphics;
    requires javafx.fxml;
    requires javafx.controls;
    requires com.jfoenix;
    requires java.logging;
    requires com.gluonhq.charm.glisten;
    requires com.gluonhq.attach.display;
    opens quantity.glasswindow to javafx.fxml;
    opens quantity.glasswindow.ui to javafx.fxml;
    exports quantity.glasswindow;
    exports quantity.glasswindow.core;
    exports quantity.glasswindow.core.customExceptions;
    exports quantity.glasswindow.core.enumerations;
    exports quantity.glasswindow.utils;
    exports quantity.glasswindow.ui;
}