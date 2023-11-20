module twitter {
    requires javafx.controls;
    requires javafx.fxml;

    requires java.sql;
    requires atlantafx.base;

    opens twitter to javafx.fxml;

    exports twitter;
}
