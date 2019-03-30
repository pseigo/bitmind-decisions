package ca.ubc.cs.cpsc210.parsers;

import ca.ubc.cs.cpsc210.entry.Consequence;
import ca.ubc.cs.cpsc210.persistence.JsonEncoder;
import ca.ubc.cs.cpsc210.persistence.JsonTest;
import org.json.JSONObject;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * These tests rely on persistence.JsonEncoder to generate JSON from existing objects.
 * @author Peyton Seigo
 */
class ConsequenceParserTest extends JsonTest {

    @Test
    void parseTest() {
        generateJsonAndTestEquality(generateConsequence("Consequence", 1));
        generateJsonAndTestEquality(generateConsequence("Consequence", 2));
        generateJsonAndTestEquality(generateConsequence("Consequence", 3));
        generateJsonAndTestEquality(generateConsequence("Consequence", 4));
    }

    private void generateJsonAndTestEquality(Consequence original) {
        JSONObject consequenceJson = JsonEncoder.consequenceToJson(original);
        Consequence parsed = ConsequenceParser.parse(consequenceJson.toString());
        assertEquals(original, parsed);
    }

}
