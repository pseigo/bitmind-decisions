package ca.ubc.cs.cpsc210.entry;

import ca.ubc.cs.cpsc210.exceptions.NoEntriesAddedException;
import ca.ubc.cs.cpsc210.exceptions.OutOfBoundsException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @author Peyton Seigo
 */
class JournalTest extends ModelTest {

    private Journal journal;

    @BeforeEach
    void setup() {
        journal = new Journal();
    }

    @Test
    void testConstructor() {
        assertEquals(0, journal.size());

        try {
            journal.lastEntryDateShort();
            fail("didn't catch NoEntriesAddedException when expected");
        } catch (NoEntriesAddedException e) {
            // Expected behaviour
        }

        assertNull(journal.get(1));
    }

    @Test
    void testAdd() {
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
        Entry entryGet = journal.get(1);
        assertNotSame(entryGet, entry); // compare by reference, not using equals()
        assertEquals(entryGet.description(), entry.description());
        assertEquals(entryGet.creationDate(), entry.creationDate());
        assertEquals(entryGet.status(), entry.status());
    }

    @Test
    void testAddRemove() {
        Entry entry = new Entry("This is the one entry that we're adding");

        journal.add(entry);
        assertEquals(1, journal.size());
        assertEquals(journal.get(1).description(), entry.description());

        journal.remove(1);
        assertEquals(0, journal.size());
        assertNull(journal.get(0));

        try {
            journal.lastEntryDateShort();
            fail("didn't catch NoEntriesAddedException when expected");
        } catch (NoEntriesAddedException e) {
            // Expected behaviour
        }
    }

    @Test
    void testAddTwoByReference() {
        Entry entry = new Entry("Entry 1");
        addTwoEntriesEnsureDeepCopy(entry, entry);
    }

    @Test
    void testAddTwoByCopy() {
        Entry entry1 = new Entry("Entry 1");
        Entry entry2 = new Entry(entry1);
        addTwoEntriesEnsureDeepCopy(entry1, entry2);
    }

    @Test
    void testPutAfterLast() {
        Entry placeholder = new Entry("This just exists for context");
        Entry toAdd = new Entry("This is the entry we care about");

        for (int i = 0; i != 3; ++i) {
            journal.add(placeholder);
        }
        // entries: E E E N N ...
        assertEquals(3, journal.size());
        assertFalse(journal.containsId(4));

        journal.put(4, toAdd); // entries: E E E E N ...
        assertTrue(journal.containsId(4));
        assertEquals(4, journal.size());
        assertEquals(toAdd, journal.get(4));
        assertNotSame(toAdd, journal.get(4));
    }

    @Test
    void testPutAtLast() {
        Entry placeholder = new Entry("This just exists for context");
        Entry toAdd = new Entry("This is the entry we care about");

        for (int i = 0; i != 3; ++i) {
            journal.add(placeholder);
        }
        // entries: E E E
        assertEquals(3, journal.size());
        assertNotEquals(toAdd, journal.get(3));

        journal.put(3, toAdd);
        assertTrue(journal.containsId(3));
        assertEquals(3, journal.size());
        assertEquals(toAdd, journal.get(3));
        assertNotSame(toAdd, journal.get(3));
    }

    @Test
    void testPutBeforeLastAtUnmappedIndex() {
        Entry placeholder = new Entry("This just exists for context");
        Entry toAdd = new Entry("This is the entry we care about");

        for (int i = 0; i != 4; ++i) {
            journal.add(placeholder);
        }
        // entries: E E E E N N ...
        journal.remove(2);
        // entries: E N E E N N ...
        assertEquals(3, journal.size());
        assertFalse(journal.containsId(2));
        assertNull(journal.get(2));

        journal.put(2, toAdd);
        assertEquals(4, journal.size());
        assertTrue(journal.containsId(2));
        assertEquals(toAdd, journal.get(2));
        assertNotSame(toAdd, journal.get(2));
    }

    @Test
    void testPutBeforeLastAtMappedIndex() {
        Entry placeholder = new Entry("This just exists for context");
        Entry toAdd = new Entry("This is the entry we care about");

        for (int i = 0; i != 4; ++i) {
            journal.add(placeholder);
        }
        // entries: E E E E N N ...
        assertEquals(4, journal.size());
        assertTrue(journal.containsId(2));
        assertNotEquals(toAdd, journal.get(2));

        journal.put(2, toAdd);
        assertEquals(4, journal.size());
        assertEquals(toAdd, journal.get(2));
        assertNotSame(toAdd, journal.get(2));
    }

    @Test
    void testPutOutOfBounds() {
        Entry placeholder = new Entry("This just exists for context");
        Entry toAdd = new Entry("This is the entry we care about");

        for (int i = 0; i != 2; ++i) {
            journal.add(placeholder);
        }
        assertEquals(2, journal.size());

        journal.put(1, toAdd);
        assertEquals(2, journal.size());
        try {
            journal.put(0, toAdd);
            fail("did not catch OutOfBoundsException when expected");
        } catch (OutOfBoundsException e) {
            // Expected behaviour
        }
        try {
            journal.put(-1, toAdd);
            fail("did not catch OutOfBoundsException when expected");
        } catch (OutOfBoundsException e) {
            // Expected behaviour
        }
        assertEquals(2, journal.size());
    }

    @Test
    void testRemoveNonExistingEntry() {
        journal.add(new Entry("foo"));
        journal.add(new Entry("bar"));
        assertEquals(2, journal.size());

        journal.remove(3); // nothing should happen
        assertEquals(2, journal.size());
    }

    // TODO: use different creation dates
    @Test
    void testRemoveLastEntryDateShift() {
        for (int i = 0; i != 3; ++i) {
            Entry entry = new Entry("Some problem");
            journal.add(entry);
        }
        assertEquals(3, journal.size());

        assertEquals(journal.get(3).creationDate(), journal.lastEntryDateShort());
        journal.remove(3);
        assertEquals(2, journal.size());

        assertEquals(journal.get(2).creationDate(), journal.lastEntryDateShort());
        journal.remove(2);
        assertEquals(1, journal.size());

        assertEquals(journal.get(1).creationDate(), journal.lastEntryDateShort());
        journal.remove(1);
        assertEquals(0, journal.size());
        try {
            journal.lastEntryDateShort();
            fail("didn't catch NoEntriesAddedException when expected");
        } catch (NoEntriesAddedException e) {
            // Expected behaviour
        }
    }

    // TODO: use different creation dates
    @Test
    void testRemoveFromMiddle() {
        for (int i = 0; i != 3; ++i) {
            Entry entry = new Entry("Entry with ID #" + (i + 1));
            journal.add(entry);
        }
        assertEquals(3, journal.size());

        try {
            assertEquals(journal.get(3).creationDate(), journal.lastEntryDateShort());
            journal.remove(1);
            assertEquals(2, journal.size());

            assertEquals(journal.get(3).creationDate(), journal.lastEntryDateShort());
            journal.remove(2);
            assertEquals(1, journal.size());

            assertEquals(journal.get(3).creationDate(), journal.lastEntryDateShort());
            journal.remove(3);
            assertEquals(0, journal.size());
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
    void testEquals() {
        // Same instance
        assertEquals(journal, journal);
        assertSame(journal, journal);

        // Misc. (for that test coverage)
        assertNotEquals(null, journal);
        assertNotEquals(journal, new Object());

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
