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

    private static final String TITLE = "New Entry Wizard";
    private static final int PAGE_COUNT = 1;

    private List<WizardPane> panes;

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

        List<WizardPane> wizardPanes = new ArrayList<>();
        loadPanes(fxmlBaseFileName, numPages, pageHeader, wizardPanes);

        Wizard wizard = new Wizard();
        createAndShowWizard(pageHeader, wizardPanes, wizard);

        // TODO somehow let caller know if user cancelled dialog? exception?
        System.out.println("Returning settings. (" + wizard.getSettings() + ")");
        return wizard.getSettings();
    }

    private void createAndShowWizard(String pageHeader, List<WizardPane> wizardPanes, Wizard wizard) {
        wizard.setFlow(new Wizard.LinearFlow(wizardPanes));
        wizard.setTitle(pageHeader);

        wizard.showAndWait().ifPresent(result -> {
            if (result == ButtonType.FINISH) {
                System.out.println("Wizard completed! Settings: " + wizard.getSettings());
            } else {
                System.out.println("Action: " + result.getButtonData().toString() + ". Settings so far: "
                        + wizard.getSettings());
            }
        });
    }

    private void loadPanes(String fxmlBaseFileName, int numPages, String pageHeader, List<WizardPane> wizardPanes) {
        for (int page = 1; page != numPages + 1; ++page) {
            FxmlResourceLoader loader = new FxmlResourceLoader();
            Parent root = loader.loadFxml(fxmlBaseFileName + page + ".fxml").getRoot();

            WizardPane thisPane = new WizardPane();
            thisPane.setHeaderText(pageHeader + " (" + page + " of " + numPages + ")");
            thisPane.setContent(root);
            wizardPanes.add(thisPane);
        }
    }

}
