package ca.ubc.cs.cpsc210.parsers;

import ca.ubc.cs.cpsc210.model.Choice;
import ca.ubc.cs.cpsc210.persistence.JsonEncoder;
import ca.ubc.cs.cpsc210.persistence.JsonTest;
import org.json.JSONObject;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * These tests rely on persistence.JsonEncoder to generate JSON from existing objects.
 * @author Peyton Seigo
 */
class ChoiceParserTest extends JsonTest {

    @Test
    void parseTest() {
        Choice original = generateChoice(1);
        JSONObject choiceJson = JsonEncoder.choiceToJson(original);
        Choice parsed = ChoiceParser.parse(choiceJson.toString());
        assertEquals(original, parsed);
    }

}
