package ca.ubc.cs.cpsc210.ui;

import ca.ubc.cs.cpsc210.util.FxmlResourceLoader;
import javafx.collections.ObservableMap;
import javafx.scene.Parent;
import javafx.scene.control.ButtonType;
import org.controlsfx.dialog.Wizard;
import org.controlsfx.dialog.WizardPane;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Peyton Seigo
 */
public class EntryWizard {

    private static final String TITLE = "Add Details";

    private final List<WizardPane> wizardPanes;
    private boolean closedByClickingFinish;

    public EntryWizard() {
        wizardPanes = new ArrayList<>();
        closedByClickingFinish = false;
    }

    /**
     * Creates and shows a linear wizard with {@code numPages} pages. For each page, loads FXML files with the base name
     * {@code fxmlBaseFileName} and ending with a number, starting at 1. Loads pages from 1 to {@code numPages}.
     *
     * For example, to create a 3-page wizard where the FXML files are named as:
     *
     *   - EntryWizard1.fxml
     *   - EntryWizard2.fxml
     *   - EntryWizard3.fxml
     *
     * One might call {@code createWizard("EntryWizard", 3, "Entry Wizard")}.
     * @param fxmlBaseFileName FXML file name without the file extension nor the page index
     * @param numPages number of pages in wizard; there must be at least {@code numPages} appropriate FXML files
     * @param pageHeader header text displayed on each page
     * @return
     */
    public ObservableMap<String, Object> createWizard(String fxmlBaseFileName,
                                                      int numPages,
                                                      String pageHeader) {

        // TODO somehow let caller know if user cancelled dialog? exception? pass closedByClickingFinish?
        loadPanes(fxmlBaseFileName, numPages, pageHeader);
        return createAndShowWizard();
    }


    private void loadPanes(String fxmlBaseFileName, int numPages, String pageHeader) {
        for (int page = 1; page != numPages + 1; ++page) {
            FxmlResourceLoader loader = new FxmlResourceLoader();
            Parent root = loader.loadFxml(fxmlBaseFileName + page + ".fxml").getRoot();

            WizardPane thisPane = new WizardPane();
            thisPane.setHeaderText(pageHeader + " (" + page + " of " + numPages + ")");
            thisPane.setContent(root);
            wizardPanes.add(thisPane);
        }
    }

    private ObservableMap<String, Object> createAndShowWizard() {
        Wizard wizard = new Wizard();
        wizard.setFlow(new Wizard.LinearFlow(wizardPanes));
        wizard.setTitle(TITLE);

        wizard.showAndWait().ifPresent(result -> {
            if (result == ButtonType.FINISH) {
                closedByClickingFinish = true;
            }
        });
        return wizard.getSettings();
    }

}
