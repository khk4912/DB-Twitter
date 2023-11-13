module twitter {
    requires javafx.controls;
    requires javafx.fxml;

    opens twitter to javafx.fxml;

    exports twitter;
}
