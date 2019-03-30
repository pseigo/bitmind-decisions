package ca.ubc.cs.cpsc210.ui;

import ca.ubc.cs.cpsc210.entry.Journal;
import ca.ubc.cs.cpsc210.util.JsonFileIO;
import javafx.application.Application;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.stage.Stage;

import java.io.IOException;

/**
 * @author Peyton Seigo
 */
public class App extends Application {

    private static final String TITLE = "Bitmind Decision Making (BETA)";
    private static final int HEIGHT = 700;
    private static final int WIDTH = 600;
    private static Stage primaryStage;

    private Journal journal;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void init() {
        load();
    }

    private void setScene(Parent root) {
        try {
            Scene scene = new Scene(root, WIDTH, HEIGHT);
            primaryStage.setTitle(TITLE);
            primaryStage.setScene(scene);
            primaryStage.setOnCloseRequest(e -> {
                e.consume(); // Prevent closing stage after event handler ends (JavaFX for Dummies, 2014, p. 89)
                closeConfirmation();
            });
            primaryStage.show();
        } catch (Exception e) {
            // TODO error + log
            e.printStackTrace();
        }
    }

    private void closeConfirmation() {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Are you sure you want to exit the program?");
        alert.setTitle("Close confirmation");

        alert.showAndWait().ifPresent(result -> {
            if (result.equals(ButtonType.OK)) {
                primaryStage.close();
            }
        });
    }

    public void start(Stage primaryStage) {
//        setUserAgentStylesheet(STYLESHEET_);
        setPrimaryStage(primaryStage);
        Parent root = new Dashboard(journal);
        setScene(root);
    }

    private void load() {
        System.out.println("Loading app data.");

        try {
            journal = JsonFileIO.read();
        } catch (IOException e) {
            // TODO: show + log error message or handle failed read by creating new Journal
            e.printStackTrace();
        }
    }

    private void setPrimaryStage(Stage stage) {
        primaryStage = stage;
    }

}
