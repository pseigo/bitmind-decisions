package ca.ubc.cs.cpsc210.parsers;

import ca.ubc.cs.cpsc210.entry.EntryDateTime;
import ca.ubc.cs.cpsc210.persistence.JsonEncoder;
import org.json.JSONObject;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * These tests rely on persistence.JsonEncoder to generate JSON from existing objects.
 * @author Peyton Seigo
 */
public class EntryDateTimeParserTest {

    @Test
    void parseTest() {
        generateJsonAndTestEquality(EntryDateTime.now());
        generateJsonAndTestEquality(EntryDateTime.of(
                LocalDateTime.now()
                        .plusYears(5)
                        .minusWeeks(8)
                        .minusSeconds(32)
                        .plusNanos(34)
        ));
    }

    private void generateJsonAndTestEquality(EntryDateTime original) {
        JSONObject entryDateTimeJson = JsonEncoder.entryDateTimeToJson(original);
        EntryDateTime parsed = EntryDateTimeParser.parse(entryDateTimeJson.toString());
        assertEquals(original, parsed);
    }

}
