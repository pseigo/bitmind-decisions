package ca.ubc.cs.cpsc210.persistence;

import ca.ubc.cs.cpsc210.entry.*;
import org.json.JSONObject;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.LocalTime;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @author Peyton Seigo
 */
class JsonEncoderTest extends JsonTest {

    @Test
    void journalToJsonTest() {
        Journal journal = generateJournal();
        JSONObject journalJson = JsonEncoder.journalToJson(journal);
        System.out.println(journalJson.toString(4));
    }

    @Test
    void entryToJsonTest() {
        Entry entry = generateEntry(1, 2, false);
        JSONObject entryJson = JsonEncoder.entryToJson(entry);
        assertTrue(entryJson.has("description"));
        assertTrue(entryJson.has("choices"));
        assertTrue(entryJson.has("creationDateTime"));
        assertTrue(entryJson.has("completionDateTime"));
        assertTrue(entryJson.has("status"));
        assertEquals(5, entryJson.length());

        assertEquals("Entry 1", entryJson.getString("description"));
        assertEquals(2, entryJson.getJSONArray("choices").length());
        assertNotNull(entryJson.optJSONObject("creationDateTime"));
        assertNull(entryJson.optJSONObject("completionDateTime"));
        assertEquals("DRAFT", entryJson.getString("status"));
    }

    @Test
    void entryDateTimeToJsonTest() {
        EntryDateTime edt = EntryDateTime.now();
        JSONObject edtJson = JsonEncoder.entryDateTimeToJson(edt);
        assertTrue(edtJson.has("year"));
        assertTrue(edtJson.has("month"));
        assertTrue(edtJson.has("dayOfMonth"));
        assertTrue(edtJson.has("hour"));
        assertTrue(edtJson.has("minute"));
        assertTrue(edtJson.has("second"));
        assertEquals(6, edtJson.length());

        LocalDate localDate = edt.toLocalDate();
        LocalTime localTime = edt.toLocalTime();

        assertEquals(localDate.getYear(), edtJson.getInt("year"));
        assertEquals(localDate.getMonthValue(), edtJson.getInt("month"));
        assertEquals(localDate.getDayOfMonth(), edtJson.getInt("dayOfMonth"));
        assertEquals(localTime.getHour(), edtJson.getInt("hour"));
        assertEquals(localTime.getMinute(), edtJson.getInt("minute"));
        assertEquals(localTime.getSecond(), edtJson.getInt("second"));
    }

    @Test
    void choiceToJsonTest() {
        Choice choice = generateChoice(1);
        JSONObject choiceJson = JsonEncoder.choiceToJson(choice);
        assertTrue(choiceJson.has("description"));
        assertTrue(choiceJson.has("pros"));
        assertTrue(choiceJson.has("cons"));
        assertTrue(choiceJson.has("regrets"));
        assertTrue(choiceJson.has("regretValue"));
        assertEquals(5, choiceJson.length());

        assertEquals("Choice 1", choiceJson.getString("description"));
        assertEquals(4, choiceJson.getJSONArray("pros").length());
        assertEquals(4, choiceJson.getJSONArray("cons").length());
        assertEquals(4, choiceJson.getJSONArray("regrets").length());
        assertEquals(42, choiceJson.getInt("regretValue"));
    }

    @Test
    void consequenceToJsonTest() {
        Consequence consequence = generateConsequence("Test", 1);
        JSONObject conJson = JsonEncoder.consequenceToJson(consequence);
        assertTrue(conJson.has("description"));
        assertTrue(conJson.has("isLongTerm"));
        assertTrue(conJson.has("isShortTerm"));
        assertEquals(3, conJson.length());

        assertEquals("Test 1", conJson.get("description"));
        assertFalse(conJson.getBoolean("isLongTerm"));
        assertFalse(conJson.getBoolean("isShortTerm"));

        consequence = generateConsequence("Test", 2);
        conJson = JsonEncoder.consequenceToJson(consequence);
        assertTrue(conJson.getBoolean("isLongTerm"));
        assertFalse(conJson.getBoolean("isShortTerm"));

        consequence = generateConsequence("Test", 3);
        conJson = JsonEncoder.consequenceToJson(consequence);
        assertFalse(conJson.getBoolean("isLongTerm"));
        assertTrue(conJson.getBoolean("isShortTerm"));

        consequence = generateConsequence("Test", 4);
        conJson = JsonEncoder.consequenceToJson(consequence);
        assertTrue(conJson.getBoolean("isLongTerm"));
        assertTrue(conJson.getBoolean("isShortTerm"));
    }

}
