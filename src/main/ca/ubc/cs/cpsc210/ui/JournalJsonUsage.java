// For testing purposes
//package ca.ubc.cs.cpsc210.ui;
//
//import ca.ubc.cs.cpsc210.entry.Choice;
//import ca.ubc.cs.cpsc210.entry.Consequence;
//import ca.ubc.cs.cpsc210.entry.Entry;
//import ca.ubc.cs.cpsc210.entry.Journal;
//import ca.ubc.cs.cpsc210.persistence.JsonEncoder;
//import org.json.JSONObject;
//
///**
// * Explores JSON usage scenarios and functionality of the Journal class.
// * @author Peyton Seigo
// */
//public class JournalJsonUsage {
//
//    public static void main(String[] args) {
//        Journal journal = testJournal();
//        JSONObject entriesMap = JsonEncoder.journalToJson(journal);
//
//        System.out.println("JSON representation of Journal:\n");
//        System.out.println(entriesMap.toString(4));
//    }
//
//    private static Journal testJournal() {
//        Choice startNow = buildChoiceStartNow();
//        Choice startTomorrow = buildChoiceStartTomorrow();
//
//        Entry englishPaper = new Entry("When to start English paper");
//        englishPaper.addChoice(startNow);
//        englishPaper.addChoice(startTomorrow);
//
//        Journal journal = new Journal();
//        journal.add(englishPaper);
//
//        return journal;
//    }
//
//    private static Choice buildChoiceStartNow() {
//        Choice startNow = new Choice("Start rough draft now");
//        startNow.addPro(new Consequence.Builder("Will feel better about starting early")
//                .isLongTerm()
//                .build());
//        startNow.addPro(new Consequence.Builder("Can get feedback on Monday")
//                .isLongTerm()
//                .build());
//        startNow.addCon(new Consequence.Builder("Feels intimidating")
//                .isShortTerm()
//                .build());
//        return startNow;
//    }
//
//    private static Choice buildChoiceStartTomorrow() {
//        Choice startTomorrow = new Choice("Put it off until tomorrow");
//        startTomorrow.addPro(new Consequence.Builder("Have time to go out")
//                .isLongTerm()
//                .build());
//        startTomorrow.addCon(new Consequence.Builder("Fall further behind schedule")
//                .isShortTerm()
//                .build());
//        return startTomorrow;
//    }
//}