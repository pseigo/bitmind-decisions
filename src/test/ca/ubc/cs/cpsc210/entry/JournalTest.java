package ca.ubc.cs.cpsc210.entry;

import ca.ubc.cs.cpsc210.exceptions.NoEntriesAddedException;
import ca.ubc.cs.cpsc210.exceptions.NoEntryWithIDException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @author Peyton Seigo
 */
public class JournalTest extends ModelTest {
    private Journal journal;

    @BeforeEach
    public void setup() {
        journal = new Journal();
    }

    @Test
    public void testConstructor() {
        assertEquals(0, journal.size());

        try {
            journal.lastEntryDateShort();
            fail("didn't catch NoEntriesAddedException when expected");
        } catch (NoEntriesAddedException e) {
            // Expected behaviour
        }

        try {
            journal.get(1);
            fail("didn't catch NoEntryWithIDException when expected");
        } catch (NoEntryWithIDException e) {
            // Expected behaviour
        }
    }

    @Test
    public void testAddEntry() {
        EntryDateTime dateTimeNow = EntryDateTime.now();
        Entry entry = new Entry("This is the description");
        journal.add(entry);

        assertEquals(1, journal.size());

        try {
            assertEquals(dateTimeNow.dateShort(), journal.lastEntryDateShort());
        } catch (NoEntriesAddedException e) {
            fail("caught " + e.toString() + " when unexpected");
        }

        // Entry in journal should be a deep copy; not the same object
        try {
            Entry entryGet = journal.get(1);
            assertNotSame(entryGet, entry); // compare by reference, not using equals()
            assertEquals(entryGet.description(), entry.description());
            assertEquals(entryGet.creationDate(), entry.creationDate());
            assertEquals(entryGet.status(), entry.status());
        } catch (NoEntryWithIDException e) {
            fail("caught " + e.toString() + " when unexpected");
        }
    }

    @Test
    public void testAddRemoveEntry() {
        Entry entry = new Entry("This is the one entry that we're adding");

        journal.add(entry);
        assertEquals(1, journal.size());

        try {
            assertEquals(journal.get(1).description(), entry.description());
        } catch (NoEntryWithIDException e) {
            fail("caught " + e.toString() + " when unexpected");
        }

        journal.remove(1);
        assertEquals(0, journal.size());

        try {
            journal.get(0);
            fail("didn't catch NoEntryWithIdException when expected");
        } catch (NoEntryWithIDException e) {
            // Expected behaviour
        }
        try {
            journal.lastEntryDateShort();
            fail("didn't catch NoEntriesAddedException when expected");
        } catch (NoEntriesAddedException e) {
            // Expected behaviour
        }
    }

    @Test
    public void testAddTwoEntriesByReference() {
        Entry entry = new Entry("Entry 1");
        addTwoEntriesEnsureDeepCopy(entry, entry);
    }

    @Test
    public void testAddTwoEntryCopies() {
        Entry entry1 = new Entry("Entry 1");
        Entry entry2 = new Entry(entry1);
        addTwoEntriesEnsureDeepCopy(entry1, entry2);
    }

    @Test
    public void testRemoveNonExistingEntry() {
        try {
            journal.remove(1);
            fail("didnt catch NoEntryWithIDException when expected");
        } catch (NoEntryWithIDException e) {
            // Expected behaviour
        }
    }

    // TODO: use different creation dates
    @Test
    public void testRemoveEntriesLastEntryDateShift() {
        for (int i = 0; i != 3; ++i) {
            Entry entry = new Entry("Some problem");
            journal.add(entry);
        }

        assertEquals(journal.get(3).creationDate(), journal.lastEntryDateShort());
        journal.remove(3);
        assertEquals(journal.get(2).creationDate(), journal.lastEntryDateShort());
        journal.remove(2);
        assertEquals(journal.get(1).creationDate(), journal.lastEntryDateShort());
        journal.remove(1);
        try {
            journal.lastEntryDateShort();
            fail("didn't catch NoEntriesAddedException when expected");
        } catch (NoEntriesAddedException e) {
            // Expected behaviour
        }
    }

    // TODO: use different creation dates
    @Test
    public void testRemoveEntriesFromMiddle() {
        for (int i = 0; i != 3; ++i) {
            Entry entry = new Entry("Entry with ID #" + (i + 1));
            journal.add(entry);
        }

        try {
            assertEquals(journal.get(3).creationDate(), journal.lastEntryDateShort());
            journal.remove(1);
            assertEquals(journal.get(3).creationDate(), journal.lastEntryDateShort());
            journal.remove(2);
            assertEquals(journal.get(3).creationDate(), journal.lastEntryDateShort());
            journal.remove(3);
        } catch (NoEntriesAddedException e) {
            fail("caught " + e.toString() + " when unexpected");
        }

        try {
            journal.lastEntryDateShort();
            fail("didn't catch NoEntriesAddedException when expected");
        } catch (NoEntriesAddedException e) {
            // Expected behaviour
        }
    }

    private void addTwoEntriesEnsureDeepCopy(Entry entry1, Entry entry2) {
        assertEquals(1, journal.add(entry1)); // version in journal should not reference our entry here. should be deep copied
        assertEquals(2, journal.add(entry2)); // entry in journal should be distinct from the last line

        Entry entryGet1 = journal.get(1);
        Entry entryGet2 = journal.get(2);

        assertEquals(2, journal.size());
        assertNotSame(entryGet1, entryGet2); // compare by reference, not using equals()
        assertEquals(entryGet1.description(), entryGet2.description());
        assertEquals(entryGet1.status(), entryGet2.status());

        // Indirectly change status using the class's exposed behaviour
        entryGet1.complete();
        assertEquals(Status.COMPLETE, entryGet1.status());
        assertEquals(Status.DRAFT, entryGet2.status());
        assertNotEquals(entryGet1.status(), entryGet2.status());

        // Mutate status directly
        entryGet1.setStatus(Status.INCOMPLETE);
        entryGet2.setStatus(Status.DRAFT);
        assertNotEquals(entryGet1.status(), entryGet2.status());
    }

    @Test
    public void testEquals() {
        // Same instance
        assertEquals(journal, journal);
        assertSame(journal, journal);

        // Misc. (for that test coverage)
        assertFalse(journal.equals(null));
        assertFalse(journal.equals(new Object()));

        // New object with identical (equal) fields
        Entry entry = new Entry("Entry description");
        entry.complete();

        journal.add(entry);

        Journal sameFields = new Journal();
        sameFields.add(new Entry(entry));

        assertEquals(journal, sameFields);
        assertEquals(journal.hashCode(), sameFields.hashCode());
        assertNotSame(journal, sameFields);

        // New objects with different (not equal) parameters
        // d = "different" to indicate what was changed
        Journal dEntries = new Journal();
        dEntries.add(entry);
        dEntries.add(new Entry("Entry description"));
        assertNotEquals(journal, dEntries);
    }
}
