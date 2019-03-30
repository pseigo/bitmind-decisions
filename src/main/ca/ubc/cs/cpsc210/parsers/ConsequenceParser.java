package ca.ubc.cs.cpsc210.parsers;

import ca.ubc.cs.cpsc210.model.Consequence;
import org.json.JSONObject;

/**
 * @author Peyton Seigo
 */
class ConsequenceParser {

    /**
     * Parses input (a JSONObject as text) to a {@code Consequence} and returns the consequence.
     * @param input JSON representing the consequence
     * @return Consequence object created from input string
     */
    public static Consequence parse(String input) {
        JSONObject consequenceJson = new JSONObject(input);
        String description = consequenceJson.getString("description");
        boolean isLongTerm = consequenceJson.getBoolean("isLongTerm");
        boolean isShortTerm = consequenceJson.getBoolean("isShortTerm");

        Consequence.Builder consBuilder = new Consequence.Builder(description);
        if (isLongTerm) {
            consBuilder.isLongTerm();
        }
        if (isShortTerm) {
            consBuilder.isShortTerm();
        }

        return consBuilder.build();
    }

}
