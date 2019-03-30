package ca.ubc.cs.cpsc210.ui;

import ca.ubc.cs.cpsc210.controller.EntryCardController;
import ca.ubc.cs.cpsc210.entry.Entry;
import ca.ubc.cs.cpsc210.util.FxmlResourceLoader;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.VBox;

/**
 * A card representing an entry. Courtesy of CPSC 210 standard project starter.
 * @author Peyton Seigo
 */
public class EntryCard extends VBox {

    public EntryCard(Entry entry) {
        FxmlResourceLoader resourceLoader = new FxmlResourceLoader();
        FXMLLoader fxmlLoader = resourceLoader.loadFxmlAndSetRoot("EntryCard.fxml", this);
        EntryCardController controller = fxmlLoader.getController();
        controller.setData(entry);
    }

    // TODO remove, deprecated
//    private void load() {
//        try {
//            // Load FXML resource
//            URL fxmlUrl = getClass().getResource(FXML_PATH);
//            FXMLLoader fxmlLoader = new FXMLLoader(fxmlUrl);
//            fxmlLoader.setRoot(this);
//            fxmlLoader.load();
//
//            // Set entry data
//            EntryCardController controller = fxmlLoader.getController();
//            controller.setData(entry);
//        } catch (IOException exception) {
//            throw new RuntimeException(exception);
//        }
//    }
}
