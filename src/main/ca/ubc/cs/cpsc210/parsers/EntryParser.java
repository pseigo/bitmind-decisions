package ca.ubc.cs.cpsc210.parsers;

import ca.ubc.cs.cpsc210.model.Choice;
import ca.ubc.cs.cpsc210.model.Entry;
import ca.ubc.cs.cpsc210.model.Status;
import org.json.JSONObject;

/**
 * @author Peyton Seigo
 */
class EntryParser {

    /**
     * Parses input (a JSONObject as text) to an {@code Entry} and returns the entry.
     * @param input JSON representing the entry
     * @return Entry object created from input string
     */
    public static Entry parse(String input) {
        JSONObject entryJson = new JSONObject(input);
        JSONObject cdtJson = entryJson.optJSONObject("completionDateTime");

        Entry entry = new Entry(entryJson.getString("description"),
                EntryDateTimeParser.parse(entryJson.getJSONObject("creationDateTime").toString()),
                (cdtJson == null) ? null : EntryDateTimeParser.parse(cdtJson.toString()),
                Status.valueOf(entryJson.getString("status")));

        for (Object o : entryJson.getJSONArray("choices")) {
            JSONObject choiceJson = (JSONObject) o;
            Choice choice = ChoiceParser.parse(choiceJson.toString());
            entry.addChoice(choice);
        }

        return entry;
    }

}
