package ensisa;

import ensisa.model.BirdRepository;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import ensisa.model.Bird;


public class MainController {

    private BirdRepository repository;

    @FXML
    private Label commonNameLabel;
    @FXML
    private Label latinNameLabel;

    public Bird currentBird;

    public MainController(){
        repository = new BirdRepository();
        repository.load();

        currentBird = repository.birds.get(0);
    }

    public void initialize() {
        commonNameLabel.setText(currentBird.getCommonName());
        latinNameLabel.setText(currentBird.getLatinName());
    }
}
