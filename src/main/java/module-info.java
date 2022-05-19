module quantity.glasswindow {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.logging;

    opens quantity.glasswindow to javafx.fxml;
    exports quantity.glasswindow;
    exports quantity.glasswindow.utils;
}