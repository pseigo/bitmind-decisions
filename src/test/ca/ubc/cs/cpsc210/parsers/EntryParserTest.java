package ca.ubc.cs.cpsc210.parsers;

import ca.ubc.cs.cpsc210.model.Entry;
import ca.ubc.cs.cpsc210.persistence.JsonEncoder;
import ca.ubc.cs.cpsc210.persistence.JsonTest;
import org.json.JSONObject;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * These tests rely on persistence.JsonEncoder to generate JSON from existing objects.
 * @author Peyton Seigo
 */
class EntryParserTest extends JsonTest {

    @Test
    void parseTest() {
        generateJsonAndTestEquality(generateEntry(1, 2, false));
        generateJsonAndTestEquality(generateEntry(2, 2, true));
    }

    private void generateJsonAndTestEquality(Entry original) {
        JSONObject entryJson = JsonEncoder.entryToJson(original);
        Entry parsed = EntryParser.parse(entryJson.toString());
        assertEquals(original, parsed);
    }
}