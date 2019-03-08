package ca.ubc.cs.cpsc210.parsers;

import ca.ubc.cs.cpsc210.entry.Consequence;
import org.json.JSONObject;

/**
 * @author Peyton Seigo
 */
public class ConsequenceParser {

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

        return buildConsequence(description, isLongTerm, isShortTerm);
    }

    // TODO is there a scalable solution to this? reflection?
    /**
     * Builds a {@code Consequence} with the given parameters.
     * @param description description
     * @param isLongTerm true if consequence is long term, otherwise false
     * @param isShortTerm true if consequence is short term, otherwise false
     * @return the consequence
     */
    private static Consequence buildConsequence(String description, boolean isLongTerm, boolean isShortTerm) {
        Consequence consequence;
        if (isLongTerm && isShortTerm) {
            consequence = new Consequence.Builder(description)
                    .isLongTerm()
                    .isShortTerm()
                    .build();
        } else if (isLongTerm) {
            consequence = new Consequence.Builder(description)
                    .isLongTerm()
                    .build();
        } else if (isShortTerm) {
            consequence = new Consequence.Builder(description)
                    .isShortTerm()
                    .build();
        } else {
            consequence = new Consequence.Builder(description).build();
        }
        return consequence;
    }

}
