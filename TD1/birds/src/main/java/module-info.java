module ensisa {
    requires javafx.controls;
    requires javafx.fxml;

    opens ensisa to javafx.fxml;
    exports ensisa;
}
