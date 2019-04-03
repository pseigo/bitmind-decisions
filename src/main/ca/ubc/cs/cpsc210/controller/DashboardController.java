package ca.ubc.cs.cpsc210.controller;

import ca.ubc.cs.cpsc210.model.Entry;
import ca.ubc.cs.cpsc210.model.Journal;
import ca.ubc.cs.cpsc210.ui.EntryCard;
import ca.ubc.cs.cpsc210.ui.EntryCreator;
import javafx.fxml.FXML;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

/**
 * @author Peyton Seigo
 */
public class DashboardController {

    @FXML private VBox entryCards;
    @FXML private Label lblTitle;
    @FXML private HBox btnNewEntry;

    /**
     * Creates entry UI elements for entries in the given {@code Journal}.
     * @param journal the source of entries
     */
    public void setData(Journal journal) {
        setTitle();

        for (Entry entry : journal) {
            VBox infoPanel = makeInfoPanel(entry);
            EntryCard entryCard = new EntryCard(entry);

            HBox entryPane = new HBox(infoPanel, entryCard);
            entryPane.setSpacing(15);
            entryCards.getChildren().add(entryPane);
        }
    }

    @FXML
    private void handleNewEntryClick() {
        Stage entryCreatorStage = new Stage();
        Parent root = new EntryCreator(entryCreatorStage);

        try {
            Scene scene = new Scene(root, 500, 400);
            entryCreatorStage.setTitle("New Entry");
            entryCreatorStage.setScene(scene);
            entryCreatorStage.initModality(Modality.APPLICATION_MODAL);
            entryCreatorStage.setResizable(true);
            entryCreatorStage.show();
        } catch (Exception e) {

            e.printStackTrace();
        }
    }

    private void setTitle() {
        String titleText;
        int hour = LocalTime.now().getHour();

        if (hour > 4 && hour <= 11) {
            titleText = "Good morning!";
        } else if (hour > 11 && hour <= 15) {
            titleText = "Good afternoon!";
        } else {
            titleText = "Good evening!";
        }

        lblTitle.setText(titleText);
    }


    private VBox makeInfoPanel(Entry entry) {
        final LocalDate localDate = entry.creationDateTime().toLocalDate();
        Label lblMonthDay = new Label(localDate.format(DateTimeFormatter.ofPattern("MMM d")));
        Label lblYear = new Label(Integer.toString(localDate.getYear()));

        Label lblStatus = new Label(entry.status().toString());

        lblMonthDay.setFont(new Font(24));
        lblYear.setFont(new Font(18));

        VBox datePane = new VBox(lblMonthDay, lblYear, lblStatus);
        datePane.setAlignment(Pos.BASELINE_RIGHT);
        datePane.setMinWidth(90);
        return datePane;
    }
}
