package ca.ubc.cs.cpsc210.parsers;

import ca.ubc.cs.cpsc210.entry.Choice;
import ca.ubc.cs.cpsc210.entry.Entry;
import ca.ubc.cs.cpsc210.entry.EntryDateTime;
import ca.ubc.cs.cpsc210.entry.Status;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * @author Peyton Seigo
 */
public class EntryParser {

    /**
     * Parses input (a JSONObject as text) to an {@code Entry} and returns the entry.
     * @param input JSON representing the entry
     * @return Entry object created from input string
     */
    public static Entry parse(String input) {
        JSONObject entryJson = new JSONObject(input);

        EntryDateTime completionDateTime;
        try {
            JSONObject cdtJson = entryJson.getJSONObject("completionDateTime");
            completionDateTime = EntryDateTimeParser.parse(cdtJson.toString());
        } catch (JSONException e) {
            // Entry is not complete! Therefore, there is no completion date so set null instead.
            completionDateTime = null;
        }

        Entry entry = new Entry(entryJson.getString("description"),
                EntryDateTimeParser.parse(entryJson.getJSONObject("creationDateTime").toString()),
                completionDateTime,
                Status.valueOf(entryJson.getString("status")));

        for (Object o : entryJson.getJSONArray("choices")) {
            JSONObject choiceJson = (JSONObject) o;
            Choice choice = ChoiceParser.parse(choiceJson.toString());
            entry.addChoice(choice);
        }

        return entry;
    }

}
