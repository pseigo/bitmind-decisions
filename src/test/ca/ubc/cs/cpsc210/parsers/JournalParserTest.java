package ca.ubc.cs.cpsc210.parsers;

import ca.ubc.cs.cpsc210.entry.Journal;
import ca.ubc.cs.cpsc210.persistence.JsonEncoder;
import ca.ubc.cs.cpsc210.persistence.JsonTest;
import org.json.JSONObject;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * These tests rely on persistence.JsonEncoder to generate JSON from existing objects.
 * @author Peyton Seigo
 */
public class JournalParserTest extends JsonTest {

    @Test
    void parseTest() {
        Journal original = generateJournal();
        JSONObject journalJson = JsonEncoder.journalToJson(original);
        Journal parsed = JournalParser.parse(journalJson.toString());
        assertEquals(original, parsed);
    }

}