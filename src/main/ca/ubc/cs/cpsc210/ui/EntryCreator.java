package ca.ubc.cs.cpsc210.ui;

import ca.ubc.cs.cpsc210.controller.EntryCreatorController;
import ca.ubc.cs.cpsc210.entry.Choice;
import ca.ubc.cs.cpsc210.entry.Entry;
import ca.ubc.cs.cpsc210.util.FxmlResourceLoader;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Peyton Seigo
 */
public class EntryCreator extends VBox {

    private final Stage entryCreatorStage;
    private final Entry entry;
    private final List<Choice> choices;

    public EntryCreator(Stage entryCreatorStage) {
        this.entryCreatorStage = entryCreatorStage;
        entry = new Entry("(No description)");
        choices = new ArrayList<>();
        choices.add(new Choice("(No description)"));
        choices.add(new Choice("(No description)"));
        choices.add(new Choice("(No description)"));
        load();
    }

    private void load() {
        FxmlResourceLoader resourceLoader = new FxmlResourceLoader();
        FXMLLoader fxmlLoader = resourceLoader.loadFxmlAndSetRoot("EntryCreator.fxml", this);
        EntryCreatorController controller = fxmlLoader.getController();
        controller.setData(choices, entryCreatorStage);
    }

}
