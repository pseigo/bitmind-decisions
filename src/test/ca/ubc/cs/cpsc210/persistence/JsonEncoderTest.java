package ca.ubc.cs.cpsc210.persistence;

import ca.ubc.cs.cpsc210.entry.Choice;
import ca.ubc.cs.cpsc210.entry.Consequence;
import ca.ubc.cs.cpsc210.entry.Entry;
import ca.ubc.cs.cpsc210.entry.Journal;
import org.json.JSONObject;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @author Peyton Seigo
 */
public class JsonEncoderTest {

    private Journal journal;

    @Test
    void journalToJsonTest() {
        generateJournal();
        JSONObject journalJson = JsonEncoder.journalToJson(journal);
        System.out.println(journalJson.toString(4));
    }

    @Test
    void entryToJsonTest() {

    }

    @Test
    void entryDateTimeToJsonTest() {

    }

    @Test
    void choiceToJsonTest() {

    }

    @Test
    void consequenceToJsonTest() {
        Consequence consequence = generateConsequence("Test", 1);
        JSONObject conJson = JsonEncoder.consequenceToJson(consequence);
        assertTrue(conJson.has("description"));
        assertTrue(conJson.has("isLongTerm"));
        assertTrue(conJson.has("isShortTerm"));
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

    private Journal generateJournal() {
        journal = new Journal();
        generateEntries(journal);
        return journal;
    }

    private void generateEntries(Journal journal) {
        Entry entry1 = new Entry("Entry 1");
        Entry entry2 = new Entry("Entry 2");
        entry1.addChoice(generateChoices(1));
        entry2.addChoice(generateChoices(2));
        entry2.complete();
        journal.add(entry1);
        journal.add(entry2);
    }

    private Choice generateChoices(int number) {
        Choice choice = new Choice("Choice " + number);

        choice.addPro(generateConsequence("Pro", 1));
        choice.addPro(generateConsequence("Pro", 2));
        choice.addPro(generateConsequence("Pro", 3));
        choice.addPro(generateConsequence("Pro", 4));
        choice.addCon(generateConsequence("Con", 1));
        choice.addCon(generateConsequence("Con", 2));
        choice.addCon(generateConsequence("Con", 3));
        choice.addCon(generateConsequence("Con", 4));
        choice.addRegret(generateConsequence("Regret", 1));
        choice.addRegret(generateConsequence("Regret", 2));
        choice.addRegret(generateConsequence("Regret", 3));
        choice.addRegret(generateConsequence("Regret", 4));

        return choice;
    }

    private Consequence generateConsequence(String name, int count) {
        Consequence consequence;
        String description = name + " " + count;

        switch (count) {
            case 1:
                consequence = new Consequence.Builder(description).build();
                break;
            case 2:
                consequence = new Consequence.Builder(description)
                        .isLongTerm()
                        .build();
                break;
            case 3:
                consequence = new Consequence.Builder(description)
                        .isShortTerm()
                        .build();
                break;
            case 4:
                consequence = new Consequence.Builder(description)
                        .isLongTerm()
                        .isShortTerm()
                        .build();
                break;
            default:
                consequence = new Consequence.Builder("Count #" + count).build();
        }

        return consequence;
    }

}
