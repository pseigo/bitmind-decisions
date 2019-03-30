package ca.ubc.cs.cpsc210.persistence;

import ca.ubc.cs.cpsc210.model.Choice;
import ca.ubc.cs.cpsc210.model.Consequence;
import ca.ubc.cs.cpsc210.model.Entry;
import ca.ubc.cs.cpsc210.model.Journal;

/**
 * @author Peyton Seigo
 */
public abstract class JsonTest {

    protected Journal generateJournal() {
        Journal journal = new Journal();
        journal.add(generateEntry(1, 2, true));
        journal.add(generateEntry(2, 1, false));
        return journal;
    }

    protected Entry generateEntry(int number, int choiceCount, boolean complete) {
        Entry entry = new Entry("Entry " + number);
        for (int i = 0; i != choiceCount; ++i) {
            entry.addChoice(generateChoice(i + 1));
        }

        if (complete) {
            entry.complete();
        }

        return entry;
    }

    protected Choice generateChoice(int number) {
        Choice choice = new Choice("Choice " + number);
        choice.setRegretValue(42);

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

    protected Consequence generateConsequence(String name, int count) {
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
