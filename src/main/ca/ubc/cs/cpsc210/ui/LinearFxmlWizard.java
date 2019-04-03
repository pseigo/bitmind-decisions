package ca.ubc.cs.cpsc210.ui;

import ca.ubc.cs.cpsc210.exceptions.WizardNotLoadedException;
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
public class LinearFxmlWizard {

    private static final String TITLE = "Add Details";

    private final Wizard wizard;
    private final List<WizardPane> wizardPanes;
    private boolean closedByClickingFinish;
    private boolean wizardLoaded;

    /**
     * Constructor for {@code LinearFxmlWizard}.
     */
    public LinearFxmlWizard() {
        wizard = new Wizard();
        wizardPanes = new ArrayList<>();
        wizardLoaded = false;
        closedByClickingFinish = false;
    }

    /**
     * Returns true if wizard was closed by clicking finish, otherwise false.
     * @return true if closed by clicking finish
     */
    public boolean closedByClickingFinish() {
        return closedByClickingFinish;
    }

    /**
     * Shows this wizard to the user. Returns any data entered, regardless of whether the user finishes the wizard or
     * closes it early.
     * @return {@code wizard.getSettings()} when the wizard is closed
     * @throws WizardNotLoadedException if wizard has not been loaded yet
     */
    public ObservableMap<String, Object> show() throws WizardNotLoadedException {
        if (!wizardLoaded) {
            throw new WizardNotLoadedException();
        }

        wizard.showAndWait().ifPresent(result -> {
            closedByClickingFinish = result == ButtonType.FINISH;
        });
        return wizard.getSettings();
    }

    /**
     * Creates and loads a linear wizard with {@code numPages} pages. For each page, loads FXML files with the base name
     * {@code fxmlBaseFileName} and ending with a number, starting at 1. Loads pages from 1 to {@code numPages}.
     *
     * For example, to create a 3-page wizard where the FXML files are named as:
     *
     *   - EntryWizard1.fxml
     *   - EntryWizard2.fxml
     *   - EntryWizard3.fxml
     *
     * One might call {@code load("LinearFxmlWizard", 3, "Entry Wizard")}. After created, display to the user
     * by calling {@code show()}.
     * @param fxmlBaseFileName FXML file name without the file extension nor the page index
     * @param numPages number of pages in wizard; there must be at least {@code numPages} appropriate FXML files
     * @param pageHeader header text displayed on each page
     */
    public void load(String fxmlBaseFileName, int numPages, String pageHeader) {
        loadPanes(fxmlBaseFileName, numPages, pageHeader);
        wizard.setFlow(new Wizard.LinearFlow(wizardPanes));
        wizard.setTitle(TITLE);
        wizardLoaded = true;
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

}
