package ensisa;

import java.io.IOException;
import javafx.fxml.FXML;

public class MainController {

    @FXML
    private void switchToSecondary() throws IOException {
        BirdApplication.setRoot("primary");
    }
}
