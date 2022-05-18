module quantity.glasswindow {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;
    requires com.dlsc.formsfx;
    requires org.kordamp.ikonli.javafx;
    requires org.kordamp.bootstrapfx.core;
    requires java.logging;

    opens quantity.glasswindow to javafx.fxml;
    exports quantity.glasswindow;
    exports quantity.glasswindow.utils;
}