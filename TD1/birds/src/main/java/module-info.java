module ensisa {
    requires javafx.controls;
    requires javafx.fxml;

requires com.fasterxml.jackson.databind;
opens ensisa to javafx.fxml, com.fasterxml.jackson.databind;
exports ensisa;
exports ensisa.model;

opens ensisa.model to javafx.fxml;  // pour BirdCell et autres controllers dans model

}
