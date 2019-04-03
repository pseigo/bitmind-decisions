package ca.ubc.cs.cpsc210.controller;

import ca.ubc.cs.cpsc210.model.Choice;
import ca.ubc.cs.cpsc210.ui.LinearFxmlWizard;
import javafx.collections.ObservableMap;
import javafx.fxml.FXML;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

import java.awt.*;
import java.util.List;

/**
 * @author Peyton Seigo
 */
public class EntryCreatorController {

    @FXML private TextField fldDescriptionChoice1;
    @FXML private TextField fldDescriptionChoice2;
    @FXML private TextField fldDescriptionChoice3;
    @FXML private GridPane gridChoices;

    private List<Choice> choices;
    private Stage entryCreatorStage;

    /**
     * Loads FXML resource and populates card with given {@code entry} data.
     * @param choices reference to choices list so that this controller can store user inputs
     * @param entryCreatorStage reference back to the containing stage so this pane can close itself
     */
    public void setData(List<Choice> choices, Stage entryCreatorStage) {
        this.choices = choices;
        this.entryCreatorStage = entryCreatorStage;
    }

    @FXML
    private void handleBtnEditChoice1Click() {
        handleBtnEditChoiceClick(choices.get(0), fldDescriptionChoice1);
    }

    @FXML
    private void handleBtnEditChoice2Click() {
        handleBtnEditChoiceClick(choices.get(1), fldDescriptionChoice2);
    }

    @FXML
    private void handleBtnEditChoice3Click() {
        handleBtnEditChoiceClick(choices.get(2), fldDescriptionChoice3);
    }

    private void handleBtnEditChoiceClick(Choice choice, TextField fldDescription) {

        LinearFxmlWizard linearFxmlWizard = new LinearFxmlWizard();
        linearFxmlWizard.load("EntryCreatorChoiceEditor", 1, "temp");
        ObservableMap<String, Object> result = linearFxmlWizard.show();


        System.out.println("Finished! Here is the result: " + result);
    }

    @FXML
    private void handleBtnCreateEntryClick() {
        entryCreatorStage.close();
    }
}
