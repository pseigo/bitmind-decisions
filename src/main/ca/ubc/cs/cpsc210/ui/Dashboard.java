package ca.ubc.cs.cpsc210.ui;

import ca.ubc.cs.cpsc210.controller.DashboardController;
import ca.ubc.cs.cpsc210.entry.Journal;
import ca.ubc.cs.cpsc210.util.FxmlResourceLoader;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.VBox;

/**
 * @author Peyton Seigo
 */
public class Dashboard extends VBox {

    private static final String FXML_FILE_NAME = "Dashboard.fxml";

    Dashboard(Journal journal) {
        FxmlResourceLoader resourceLoader = new FxmlResourceLoader();
        FXMLLoader fxmlLoader = resourceLoader.loadFxmlAndSetRoot(FXML_FILE_NAME, this);
        DashboardController controller = fxmlLoader.getController();
        controller.setData(journal);
    }

}
