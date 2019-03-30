package ca.ubc.cs.cpsc210.controller;

import ca.ubc.cs.cpsc210.entry.Choice;
import ca.ubc.cs.cpsc210.ui.EntryWizard;
import javafx.collections.ObservableMap;
import javafx.fxml.FXML;
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

    private List<Choice> choices;
    private Stage entryCreatorStage;

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
        EntryWizard entryWizard = new EntryWizard();
        ObservableMap<String, Object> result = entryWizard.createWizard(
                "EntryCreatorChoiceEditor",
                1,
                "temp"
        );

        System.out.println("Finished! Here is the result: " + result);
    }

    @FXML
    private void handleBtnCreateEntryClick() {
        entryCreatorStage.close();
    }
}
