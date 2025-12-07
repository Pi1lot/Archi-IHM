package ensisa.model;
import javafx.scene.control.ListView;
import javafx.scene.control.ListCell;
import javafx.util.Callback;

public class BirdCellFactory implements Callback<ListView<Bird>, ListCell<Bird>> {

    @Override
    public ListCell<Bird> call(ListView<Bird> listView) {
        return new ListCell<>() {
            @Override
            protected void updateItem(Bird bird, boolean empty) {
                super.updateItem(bird, empty);
                if (empty || bird == null) {
                    setText(null);
                    setGraphic(null);
                } else {
                    setText(bird.getCommonName());
                }
            }
        };
    }

}
