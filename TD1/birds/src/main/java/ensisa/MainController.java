package ensisa;

import ensisa.model.Bird;
import ensisa.model.BirdCellFactory;
import ensisa.model.BirdEditDialog;
import ensisa.model.BirdRepository;
import javafx.beans.binding.Bindings;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import javafx.scene.Node;
import javafx.scene.control.Button;

public class MainController {

    private final BirdRepository repository;

    @FXML
    private Button editButton;

    @FXML
    private void editButtonAction(ActionEvent event){
        Node node = (Node) event.getSource();
        Stage stage = (Stage) node.getScene().getWindow();
        BirdEditDialog dialog = new BirdEditDialog(stage, getCurrentBird());
        dialog.showAndWait().ifPresent(bird -> {
            getCurrentBird().copyFrom(bird);
        });
    }

    @FXML
    private Label commonNameLabel;
    @FXML
    private Label latinNameLabel;
    @FXML
    private Label familyLabel;
    @FXML
    private Label genusLabel;
    @FXML
    private Label specieLabel;
    @FXML
    private Label descriptionLabel;

    @FXML
    private ImageView birdImageView;

    @FXML
    private ListView<Bird> birdListView;

    private final ObjectProperty<Bird> currentBird;

    public MainController() {
        repository = new BirdRepository();
        repository.load();
        currentBird = new SimpleObjectProperty<>(null);

    }

    // Accesseurs et mutateurs pour currentBird
    public ObjectProperty<Bird> currentBirdProperty() {
        return currentBird;
    }

    public Bird getCurrentBird() {
        return currentBird.get();
    }

    public void setCurrentBird(Bird bird) {
        currentBird.set(bird);
    }

    @FXML
    private Button deleteButton;

    @FXML
    private void deleteButtonAction(ActionEvent event) {
        Bird birdToDelete = getCurrentBird();
        if (birdToDelete != null) {
            birdListView.getSelectionModel().clearSelection();

            setCurrentBird(null);

            repository.birds.remove(birdToDelete);
            birdListView.getItems().remove(birdToDelete);
        }
    }

    @FXML
    public void initialize() {
        commonNameLabel.textProperty().bind(Bindings.selectString(currentBird, "commonName"));
        latinNameLabel.textProperty().bind(Bindings.selectString(currentBird, "latinName"));
        familyLabel.textProperty().bind(Bindings.selectString(currentBird, "family"));
        genusLabel.textProperty().bind(Bindings.selectString(currentBird, "genus"));
        specieLabel.textProperty().bind(Bindings.selectString(currentBird, "specie"));
        descriptionLabel.textProperty().bind(Bindings.selectString(currentBird, "description"));
        birdImageView.imageProperty().bind(Bindings.select(currentBird, "image"));

        birdListView.setCellFactory(new BirdCellFactory());
        birdListView.getItems().addAll(repository.birds);

        birdListView.getSelectionModel().selectedItemProperty().addListener((obs, oldBird, newBird) -> {
            if (newBird != null) {
                currentBird.set(newBird);
            }
        });

        editButton.disableProperty().bind(currentBird.isNull());
        deleteButton.disableProperty().bind(currentBird.isNull());
    }
}
