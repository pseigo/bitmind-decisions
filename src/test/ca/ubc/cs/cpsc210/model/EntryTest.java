package ca.ubc.cs.cpsc210.model;

import ca.ubc.cs.cpsc210.exceptions.ElementNotFoundException;
import ca.ubc.cs.cpsc210.exceptions.EntryIncompleteException;
import ca.ubc.cs.cpsc210.exceptions.OutOfBoundsException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.ZonedDateTime;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @author Peyton Seigo
 */
class EntryTest extends ModelTest {
    private Entry entry;

    @BeforeEach
    void setup() {
        entry = new Entry("Entry description");
    }

    @Test
    void testConstructor() {
        assertEquals("Entry description", entry.description());
        assertEquals(Status.DRAFT, entry.status());

        LocalDate dateNow = ZonedDateTime.now().toLocalDate();
        assertEquals(formatShort(dateNow), entry.creationDate());

        try {
            entry.completionDate();
            fail("did not catch EntryIncompleteException when expected");
        } catch (EntryIncompleteException e) {
            // Expected behaviour
        }
    }

    @Test
    void testCopyConstructor() {
        for (int i = 0; i != 3; ++i) {
            Choice choice = new Choice("Choice #" + i);
            choice.addPro(new Consequence.Builder("A pro").build());
            entry.addChoice(choice);
        }
        entry.complete();

        Entry deepCopy = new Entry(entry);

        // compare properties of deep copy to original
        assertEquals(entry.description(), deepCopy.description());
        assertEquals(entry.creationDate(), deepCopy.creationDate());
        assertEquals(entry.creationDateTime(), deepCopy.creationDateTime());
        try {
            assertEquals(Status.COMPLETE, deepCopy.status());
            assertEquals(entry.completionDate(), deepCopy.completionDate());
        } catch (EntryIncompleteException e) {
            fail("caught " + e.toString() + " when unexpected");
        }

        for (int i = 0; i != 3; ++i) {
            try {
                Choice entryChoice = entry.getChoice("Choice #" + i);
                Choice deepCopyChoice = deepCopy.getChoice("Choice #" + i);

                assertEquals(entryChoice.getPro("A pro"),
                        deepCopyChoice.getPro("A pro"));
            } catch (ElementNotFoundException e) {
                fail("caught " + e.toString() + " when unexpected");
            }
        }

        assertNotSame(entry, deepCopy);
    }

    // TODO EntryTest.testCreationDateTime
    @Test
    void testCreationDateTime() {

    }

    @Test
    void testGetNonExistentChoice() {
        try {
            entry.getChoice("this does not exist");
            fail("did not catch ElementNotFoundException when expected");
        } catch (ElementNotFoundException e) {
            // Expected behaviour
        }
    }
    
    @Test
    void testAddChoice() {
        Choice choice = new Choice("Some description");
        entry.addChoice(choice);
        try {
            assertEquals(choice.description(), entry.getChoice("Some description").description());
        } catch (ElementNotFoundException e) {
            fail("caught " + e.toString() + " when not expected");
        }
    }

    @Test
    void testRemoveNonExistentChoice() {
        entry.removeChoice("This is not in the list");
        entry.addChoice(new Choice("Adding this for the fun of it"));
        entry.removeChoice("Another");
    }

    @Test
    void testGetRemovedChoice() {
        Choice choice = new Choice("Some description");
        entry.addChoice(choice);
        entry.removeChoice("Some description");
        try {
            entry.getChoice("Some description");
            fail("did not catch ElementNotFoundException when expected");
        } catch (ElementNotFoundException e) {
            // Expected behaviour
        }
    }

    @Test
    void testComplete() {
        entry.complete();
        assertEquals(Status.COMPLETE, entry.status());

        LocalDate dateNow = ZonedDateTime.now().toLocalDate();
        try {
            assertEquals(formatShort(dateNow), entry.completionDate());
        } catch (EntryIncompleteException e) {
            fail("caught " + e.toString() + " when entry should be complete");
        }
    }

    @Test
    void testSetStatus() {
        assertEquals(Status.DRAFT, entry.status());

        entry.setStatus(Status.INCOMPLETE);
        assertEquals(Status.INCOMPLETE, entry.status());

        entry.setStatus(Status.COMPLETE);
        assertEquals(Status.COMPLETE, entry.status());
    }

    @Test
    void testAddTwoChoicesByReference() {
        Choice choice1 = new Choice("choice 1");
        addTwoChoicesEnsureDeepCopy(choice1, choice1);
    }

    @Test
    void testAddTwoChoiceCopies() {
        Choice choice1 = new Choice("choice 1");
        Choice choice2 = new Choice(choice1);
        addTwoChoicesEnsureDeepCopy(choice1, choice2);
    }

    private void addTwoChoicesEnsureDeepCopy(Choice choice1, Choice choice2) {
        entry.addChoice(choice1);
        entry.addChoice(choice2);

        // Modify first result to see if changes are propagated
        Choice choiceGet1 = null;
        try {
            choiceGet1 = entry.getChoice(choice1.description());
            assertEquals(0, choiceGet1.regretValue());

            choiceGet1.setRegretValue(65);
            Consequence cons = new Consequence.Builder("Some consequence")
                    .isLongTerm()
                    .build();
            choiceGet1.addPro(cons);

            assertEquals(65, choiceGet1.regretValue());
            assertEquals(cons.description(), choiceGet1.getPro("Some consequence").description());
        } catch (ElementNotFoundException e) {
            fail("caught " + e.toString() + " when unexpected");
        } catch (OutOfBoundsException e) {
            fail("caught " + e.toString() + " when regret value should have been in bounds");
        }

        // Get second copy of Choice, make sure it does not have any of our changes on choiceGet1
        entry.removeChoice(choice1.description());
        Choice choiceGet2 = null;
        try {
            choiceGet2 = entry.getChoice(choice2.description());
            assertNotSame(choiceGet1, choiceGet2);
            assertEquals(0, choiceGet2.regretValue());
        } catch (ElementNotFoundException e) {
            fail("caught " + e.toString() + " when unexpected");
        }

        // Should throw because choiceGet2 != choiceGet1, so it doesn't have the consequence that we added
        try {
            choiceGet2.getPro("Some consequence");
        } catch (ElementNotFoundException e) {
            // Expected behaviour
        }
    }

    @Test
    void testEquals() {
        // Same instance
        assertEquals(entry, entry);
        assertSame(entry, entry);

        // Misc. (for that test coverage)
        assertNotEquals(null, entry);
        assertNotEquals(entry, new Object());

        // New object with identical (equal) fields
        Entry sameFields = new Entry("Entry description");
        assertEquals(entry, sameFields);
        assertNotSame(entry, sameFields);

        // New objects with different (not equal) parameters
        // d = "different" to indicate what was changed
        Entry dDescription = new Entry("Different");
        assertNotEquals(entry, dDescription);

        Entry dChoices = new Entry("Entry description");
        dChoices.addChoice(new Choice("Choice description"));
        assertNotEquals(entry, dChoices);

        Entry dComplete = new Entry("Entry description");
        dComplete.complete();
        assertNotEquals(entry, dComplete);

        Entry dStatus = new Entry("Entry description");
        dStatus.setStatus(Status.INCOMPLETE);
        assertNotEquals(entry, dStatus);

    }
}