package ca.ubc.cs.cpsc210.util;

import ca.ubc.cs.cpsc210.model.Journal;
import ca.ubc.cs.cpsc210.parsers.JournalParser;
import ca.ubc.cs.cpsc210.persistence.JsonEncoder;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Scanner;

/**
 * Handles writing and reading user app data to and from local storage.
 * @author Peyton Seigo
 */
public class JsonFileIO {

    private static final Path jsonDataFile = Paths.get("./resources/json/journal.json");
    private static final Path backupPath = Paths.get("./resources/json/backup");

    /**
     * Attempts to read a {@code Journal} from {@code journal.json} file on disk.
     * @return {@code Journal} parsed from {@code journal.json}
     * @throws IOException if an I/O exception occurs when reading from {@code journal.json}
     * @throws JSONException if {@code journal.json} is malformed or does not exist
     */
    public static Journal read() throws IOException, JSONException {
        StringBuilder fileInput = new StringBuilder();

        try (Scanner scanner = new Scanner(Files.newBufferedReader(jsonDataFile))) {
            while (scanner.hasNext()) {
                fileInput.append(scanner.nextLine());
            }
        }

        return JournalParser.parse(fileInput.toString());
    }


    /**
     * Overwrites or creates {@code journal.json} file on disk with given {@code journal} encoded zto JSON.
     * @param journal the {@code Journal} object to write to {@code journal.json}
     * @throws IOException if an I/O exception occurs when reading from or closing {@code journal.json}
     */
    public static void write(Journal journal) throws IOException {
        try (BufferedWriter writer = Files.newBufferedWriter(jsonDataFile)) {
            JSONObject journalJson = JsonEncoder.journalToJson(journal);
            String journalString = journalJson.toString();
            writer.write(journalString);
        }
    }


    /**
     * If {@code journal.json} exists, creates a timestamped copy at the configured backup path. Otherwise, does
     * nothing.
     */
    public static void backup() {

    }


    public static void deleteBackupsOlderThan() {

    }

}
