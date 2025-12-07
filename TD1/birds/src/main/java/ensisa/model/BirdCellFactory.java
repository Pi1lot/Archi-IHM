package ensisa.model;

import javafx.fxml.FXMLLoader;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.layout.VBox;
import javafx.util.Callback;

public class BirdCellFactory implements Callback<ListView<Bird>, ListCell<Bird>> {

    @Override
    public ListCell<Bird> call(ListView<Bird> listView) {
        return new ListCell<>() {
            private VBox vbox;
            private Label commonNameLabel;
            private Label latinNameLabel;
            private FXMLLoader loader;

            @Override
            protected void updateItem(Bird bird, boolean empty) {
                super.updateItem(bird, empty);

                if (empty || bird == null) {
                    setText(null);
                    setGraphic(null);
                } else {
                    if (loader == null) {
                        loader = new FXMLLoader(getClass().getResource("/ensisa/bird-cell.fxml"));
                        try {
                            vbox = loader.load();
                            commonNameLabel = (Label) vbox.lookup("#commonNameLabel");
                            latinNameLabel = (Label) vbox.lookup("#latinNameLabel");
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }

                    commonNameLabel.setText(bird.getCommonName());
                    latinNameLabel.setText(bird.getLatinName());
                    setText(null);
                    setGraphic(vbox);
                }
            }
        };
    }
}
