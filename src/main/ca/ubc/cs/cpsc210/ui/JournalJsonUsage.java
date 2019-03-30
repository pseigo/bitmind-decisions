package ca.ubc.cs.cpsc210.ui;

import ca.ubc.cs.cpsc210.model.Choice;
import ca.ubc.cs.cpsc210.model.Consequence;
import ca.ubc.cs.cpsc210.model.Entry;
import ca.ubc.cs.cpsc210.model.Journal;
import ca.ubc.cs.cpsc210.persistence.JsonEncoder;
import ca.ubc.cs.cpsc210.util.JsonFileIO;

import java.io.IOException;

/**
 * Explores JSON usage scenarios and functionality of the Journal class.
 * @author Peyton Seigo
 */
class JournalJsonUsage {

    public static void main(String[] args) {
        JsonFileIO jsonFileIO = new JsonFileIO();
        Journal journal = testJournal();
        try {
            JsonFileIO.write(journal);
            journal = JsonFileIO.read();
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println(JsonEncoder.journalToJson(journal).toString());

//        Journal journal = testJournal();
//        JSONObject entriesMap = JsonEncoder.journalToJson(journal);
//
//        System.out.println("JSON representation of Journal:\n");
//        System.out.println(entriesMap.toString(4));
    }

    private static Journal testJournal() {
        Choice startNow = buildChoiceStartNow();
        Choice startTomorrow = buildChoiceStartTomorrow();

        Entry englishPaper = new Entry("When to start English paper");
        englishPaper.addChoice(startNow);
        englishPaper.addChoice(startTomorrow);

        Journal journal = new Journal();
        journal.add(englishPaper);

        return journal;
    }

    private static Choice buildChoiceStartNow() {
        Choice startNow = new Choice("Start rough draft now");
        startNow.addPro(new Consequence.Builder("Will feel better about starting early")
                .isLongTerm()
                .build());
        startNow.addPro(new Consequence.Builder("Can get feedback on Monday")
                .isLongTerm()
                .build());
        startNow.addCon(new Consequence.Builder("Feels intimidating")
                .isShortTerm()
                .build());
        return startNow;
    }

    private static Choice buildChoiceStartTomorrow() {
        Choice startTomorrow = new Choice("Put it off until tomorrow");
        startTomorrow.addPro(new Consequence.Builder("Have time to go out")
                .isLongTerm()
                .build());
        startTomorrow.addCon(new Consequence.Builder("Fall further behind schedule")
                .isShortTerm()
                .build());
        return startTomorrow;
    }
}