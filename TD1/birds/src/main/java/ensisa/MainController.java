package ensisa;

import ensisa.model.BirdRepository;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.image.ImageView;
import ensisa.model.BirdCellFactory;
import ensisa.model.Bird;


public class MainController {

    private BirdRepository repository;

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

    public Bird currentBird;


    public MainController(){
        repository = new BirdRepository();
        repository.load();

        currentBird = repository.birds.get(0);
    }
    

    public void initialize() {
        commonNameLabel.textProperty().bind(currentBird.commonNameProperty());
        latinNameLabel.textProperty().bind(currentBird.latinNameProperty());
        familyLabel.textProperty().bind(currentBird.familyProperty());
        genusLabel.textProperty().bind(currentBird.genusProperty());
        specieLabel.textProperty().bind(currentBird.specieProperty());
        descriptionLabel.textProperty().bind(currentBird.descriptionProperty());
        birdImageView.imageProperty().bind(currentBird.imageProperty());
        birdListView.setCellFactory(new BirdCellFactory());
        birdListView.getItems().addAll(repository.birds);
    }
}
