package ca.ubc.cs.cpsc210.parsers;

import ca.ubc.cs.cpsc210.entry.Choice;
import ca.ubc.cs.cpsc210.entry.Consequence;
import org.json.JSONObject;

/**
 * @author Peyton Seigo
 */
class ChoiceParser {

    /**
     * Parses input (a JSONObject as text) to a {@code Choice} and returns the choice.
     * @param input JSON representing the choice
     * @return Choice object created from input string
     */
    public static Choice parse(String input) {
        JSONObject choiceJson = new JSONObject(input);
        Choice choice = new Choice(choiceJson.getString("description"));
        choice.setRegretValue(choiceJson.getInt("regretValue"));
        parseConsequences(choiceJson, choice);
        return choice;
    }

    private static void parseConsequences(JSONObject choiceJson, Choice choice) {
        for (Object o : choiceJson.getJSONArray("pros")) {
            JSONObject consequenceJson = (JSONObject) o;
            Consequence consequence = ConsequenceParser.parse(consequenceJson.toString());
            choice.addPro(consequence);
        }
        for (Object o : choiceJson.getJSONArray("cons")) {
            JSONObject consequenceJson = (JSONObject) o;
            Consequence consequence = ConsequenceParser.parse(consequenceJson.toString());
            choice.addCon(consequence);
        }
        for (Object o : choiceJson.getJSONArray("regrets")) {
            JSONObject consequenceJson = (JSONObject) o;
            Consequence consequence = ConsequenceParser.parse(consequenceJson.toString());
            choice.addRegret(consequence);
        }
    }
}
