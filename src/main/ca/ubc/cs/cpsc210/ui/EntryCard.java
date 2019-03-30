package ca.ubc.cs.cpsc210.ui;

import ca.ubc.cs.cpsc210.controller.EntryCardController;
import ca.ubc.cs.cpsc210.entry.Entry;
import ca.ubc.cs.cpsc210.util.FxmlResourceLoader;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.VBox;

/**
 * A card representing an entry.
 * @author Peyton Seigo
 */
public class EntryCard extends VBox {

    public EntryCard(Entry entry) {
        FxmlResourceLoader resourceLoader = new FxmlResourceLoader();
        FXMLLoader fxmlLoader = resourceLoader.loadFxmlAndSetRoot("EntryCard.fxml", this);
        EntryCardController controller = fxmlLoader.getController();
        controller.setData(entry);
    }

}
