package ca.ubc.cs.cpsc210.util;

import javafx.fxml.FXMLLoader;

import java.io.IOException;
import java.net.URL;

/**
 * Handles loading files from the resources folder. Referenced the CPSC 210 standard project starter and a couple online
 * tutorials to get this right.
 * @author Peyton Seigo
 */
public class FxmlResourceLoader {

    private static final String FXML_PATH = "/fxml/";

    /**
     * Loads fxml file with given name (including the .fxml extension). Note that the file must not use the fx:root
     * structure.
     * @param fxmlFileName file name of the fxml resource to load
     * @return FXMLLoader object loaded according to the given fxml file name
     * @throws RuntimeException if an IOException occurs in loading the file
     */
    public FXMLLoader loadFxml(String fxmlFileName) throws RuntimeException {
        FXMLLoader fxmlLoader;
        try {
            URL fxmlUrl = getClass().getResource(FXML_PATH + fxmlFileName);
            fxmlLoader = new FXMLLoader(fxmlUrl);
            fxmlLoader.load();
        } catch (IOException exception) {
            throw new RuntimeException(exception);
        }
        return fxmlLoader;
    }

    /**
     * Loads fxml file with given name (including the .fxml extension) and sets the fx:root element to the given
     * {@code root} object. The fx:root structure can be enabled in Scene Builder in the Controller panel.
     * @param fxmlFileName file name of the fxml resource to load
     * @return FXMLLoader object loaded according to the given fxml file name
     * @throws RuntimeException if an IOException occurs in loading the file
     */
    public FXMLLoader loadFxmlAndSetRoot(String fxmlFileName, Object root) throws RuntimeException {
        FXMLLoader fxmlLoader;
        try {
            URL fxmlUrl = getClass().getResource(FXML_PATH + fxmlFileName);
            fxmlLoader = new FXMLLoader(fxmlUrl);
            fxmlLoader.setRoot(root);
            fxmlLoader.load();
        } catch (IOException exception) {
            throw new RuntimeException(exception);
        }
        return fxmlLoader;
    }
}
