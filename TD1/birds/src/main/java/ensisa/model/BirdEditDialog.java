package ensisa.model;

import java.io.IOException;
import java.util.Objects;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.ButtonBar;
import javafx.scene.control.Dialog;
import javafx.scene.control.DialogPane;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Modality;
import javafx.stage.Window;

public class BirdEditDialog extends Dialog<Bird> {

    @FXML
    private TextField nameTextField;

    @FXML
    private TextArea descriptionTextArea;

    private Bird editedBird;

    public BirdEditDialog(Window owner, Bird bird) {
        try {
            editedBird = new Bird();
            editedBird.copyFrom(bird);

            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("/ensisa/editor-pane.fxml"));
            loader.setController(this);
            DialogPane dialogPane = loader.load();

            initOwner(owner);
            initModality(Modality.APPLICATION_MODAL);
            setResizable(true);
            setTitle("Edition de " + editedBird.getCommonName());
            setDialogPane(dialogPane);

            setResultConverter(buttonType -> {
                if (!Objects.equals(ButtonBar.ButtonData.APPLY, buttonType.getButtonData())) {
                    return null;
                }
                return editedBird;
            });

            setOnShowing(dialogEvent -> Platform.runLater(() -> nameTextField.requestFocus()));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void initialize() {
        nameTextField.textProperty().bindBidirectional(editedBird.commonNameProperty());
        descriptionTextArea.textProperty().bindBidirectional(editedBird.descriptionProperty());
    }

}
