package twitter.utils;

import javafx.scene.control.Alert;

public final class TwitterAlert {
    public static void info(String header, String message) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Twitter");
        alert.setHeaderText(header);
        alert.setContentText(message);

        alert.showAndWait();
    }

    public static void error(String header, String message) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Twitter");
        alert.setHeaderText(header);
        alert.setContentText(message);

        alert.showAndWait();
    }
}
