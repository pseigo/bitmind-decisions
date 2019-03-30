package ca.ubc.cs.cpsc210.ui;

import ca.ubc.cs.cpsc210.controller.EntryCardController;
import ca.ubc.cs.cpsc210.model.Entry;
import ca.ubc.cs.cpsc210.util.FxmlResourceLoader;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.VBox;

/**
 * A card representing an entry.
 * @author Peyton Seigo
 */
public class EntryCard extends VBox {

    /**
     * Loads FXML resource and populates card with given {@code entry} data.
     * @param entry data to populate the card
     */
    public EntryCard(Entry entry) {
        FxmlResourceLoader resourceLoader = new FxmlResourceLoader();
        FXMLLoader fxmlLoader = resourceLoader.loadFxmlAndSetRoot("EntryCard.fxml", this);
        EntryCardController controller = fxmlLoader.getController();
        controller.setData(entry);
    }

}
