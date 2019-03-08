package ca.ubc.cs.cpsc210.parsers;

import ca.ubc.cs.cpsc210.entry.Entry;
import ca.ubc.cs.cpsc210.entry.Journal;
import org.json.JSONObject;

/**
 * @author Peyton Seigo
 */
public class JournalParser {

    /**
     * Parses input (a JSONObject as text) to a {@code Journal} and returns the journal.
     * @param input JSON representing the journal
     * @return Journal object created from input string
     */
    public static Journal parse(String input) {
        Journal journal = new Journal();
        JSONObject journalJson = new JSONObject(input);

        for (String key : journalJson.keySet()) {
            JSONObject entryJson = (JSONObject) journalJson.get(key);
            Entry entry = EntryParser.parse(entryJson.toString());
            journal.put(Integer.parseInt(key), entry);
        }

        return journal;
    }

}
