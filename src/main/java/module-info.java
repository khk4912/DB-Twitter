module twitter {
    requires javafx.controls;
    requires javafx.fxml;

    requires java.sql;

    opens twitter to javafx.fxml;

    exports twitter;
}
