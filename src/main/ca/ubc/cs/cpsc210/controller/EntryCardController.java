package ca.ubc.cs.cpsc210.controller;

import ca.ubc.cs.cpsc210.entry.Choice;
import ca.ubc.cs.cpsc210.entry.Entry;
import javafx.fxml.FXML;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import org.controlsfx.glyphfont.FontAwesome;
import org.controlsfx.glyphfont.Glyph;

import java.util.List;

/**
 * @author Peyton Seigo
 */
public class EntryCardController {

    @FXML VBox choicesList;
    @FXML private Label cardTitle;

    public void setData(Entry entry) {
        cardTitle.setText(entry.description());

        List<Node> choicesListChildren = choicesList.getChildren();
        for (Choice choice : entry) {
            choicesListChildren.add(makeListItem(choice));
        }
    }

    private HBox makeListItem(Choice choice) {
        Glyph glyph = new Glyph("FontAwesome", FontAwesome.Glyph.ANGLE_RIGHT);

        Label lblDescription = new Label(choice.description());
        lblDescription.setFont(new Font(14));
        lblDescription.setWrapText(true);
        lblDescription.maxHeight(Double.MAX_VALUE);

        HBox listItem = new HBox(15, glyph, lblDescription);
        listItem.setAlignment(Pos.BASELINE_LEFT);
        listItem.setFillHeight(true);
        return listItem;
    }
}
