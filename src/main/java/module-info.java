module com.juanite {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.xml.bind;
    requires java.sql;

    opens com.juanite to javafx.fxml;
    exports com.juanite;
    exports com.juanite.controller;
    opens com.juanite.controller to javafx.fxml;
    exports com.juanite.connection;
    opens com.juanite.connection to javafx.fxml, java.xml.bind;
}
