package ca.ubc.cs.cpsc210.entry;

import ca.ubc.cs.cpsc210.exceptions.NoEntriesAddedException;
import ca.ubc.cs.cpsc210.exceptions.NoEntryWithIDException;
import ca.ubc.cs.cpsc210.exceptions.OutOfBoundsException;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

/**
 * Represents a collection of entries.
 * @author Peyton Seigo
 */
public class Journal {
    private Map<Integer, Entry> entries;
    private int nextId;
    private int greatestIdWithEntry;
    private EntryDateTime lastEntryDateTime;

    /**
     * Constructor for {@code Journal} class. The first entry has ID 1.
     */
    public Journal() {
        entries = new HashMap<>();
        nextId = 1;
        greatestIdWithEntry = 0;
    }

    // TODO test
    /**
     * Returns an unmodifiable map of entries.
     * @return entries
     */
    public Map<Integer, Entry> entries() {
        return Collections.unmodifiableMap(entries);
    }

    // TODO test adding entry at next available ID (before and at greatestIdWithEntry)
    /**
     * Adds a deep copy of given {@code entry} to this [@code Journal}'s entries at the first available id after
     * the greatest ID that maps to an entry (greatestIdWithEntry). This will not override any previously created
     * entries and will preserve any non-mapped IDs in between entries. This method returns the ID where {@code entry}
     * is added.
     * @param entry entry added to {@code this}
     * @return entry ID in this journal where given entry was added
     */
    public int add(Entry entry) {
        // Calling deep copy constructor
        Entry toAdd = new Entry(entry);
        int toAddId = (nextId <= greatestIdWithEntry) ? greatestIdWithEntry + 1 : nextId;
        assert(!entries.containsKey(toAddId));

        entries.put(toAddId, toAdd);
        greatestIdWithEntry = toAddId;
        nextId = toAddId + 1;
        lastEntryDateTime = toAdd.creationDateTime();

        return toAddId;
    }

    // TODO implement and test
    /**
     * Adds a deep copy of given {@code entry} at entry {@code id}. Given {@code id} must be greater than or equal to 1.
     * Replaces any entry that exists at {@code id}.
     * @param id entry ID to add entry to or overwrite
     * @param entry entry added to {@code this} at {@code id}
     * @throws OutOfBoundsException if id < 1
     */
    public void put(int id, Entry entry) {
        if (id < 1) {
            throw new OutOfBoundsException();
        }

        Entry toAdd = new Entry(entry);
        entries.put(id, entry);
        if (id > greatestIdWithEntry) {
            greatestIdWithEntry = id;
            nextId = id + 1;
            // TODO need to deal with lastEntryDateTime?
        }
    }

    /**
     * If {@code Entry} with given {@code id} exists, remove from {@code this}. If most recent {@code Entry} was
     * removed, updates date of last entry in {@code this} ({@code lastEntryDateTime}) to whichever {@code Entry} was
     * added most recently in the {@code Journal}.
     * @param id id to match for
     * @throws NoEntryWithIDException if no {@code Entry} with given {@code id} exists
     */
    public void remove(int id) throws NoEntryWithIDException {
        if (!entries.containsKey(id)) {
            throw new NoEntryWithIDException();
        }

        if (id == greatestIdWithEntry) {
            // Stays null if never finds an existing entry
            lastEntryDateTime = null;

            for (int i = greatestIdWithEntry - 1; i != 0; --i) {
                // Assume that entries does not contain null values
                if (entries.containsKey(i)) {
                    lastEntryDateTime = entries.get(i).creationDateTime();
                    greatestIdWithEntry = i;
                    break;
                }
            }
        }

        // We needed greatestIdWithEntry for our search, but we didn't find an entry, so set the greatest ID to 0.
        if (lastEntryDateTime == null) {
            greatestIdWithEntry = 0;
        }

        entries.remove(id);
    }

    /**
     * If {@code Entry} with given {@code id} exists, return matching {@code Entry}.
     * @param id id to match for
     * @return {@code Entry} with an id equal to given {@code id}
     * @throws NoEntryWithIDException if no {@code Entry} with given {@code id} exists
     */
    public Entry get(int id) throws NoEntryWithIDException {
        // Assume that entries does not contain null values
        if (entries.containsKey(id)) {
            return entries.get(id);
        }
        throw new NoEntryWithIDException();
    }

    /**
     * Returns number of entries in {@code this}.
     * @return number of entries
     */
    public int size() {
        return entries.size();
    }

    /**
     * Returns the date an {@code Entry} was last added to {@code this}, formatted as a string.
     * @return last entry date formatted as a string
     * @throws NoEntriesAddedException if no entries have been added to {@code this} so far; there is no history to show
     */
    public String lastEntryDateShort() throws NoEntriesAddedException {
        if (lastEntryDateTime == null) {
            throw new NoEntriesAddedException();
        }

        return lastEntryDateTime.dateShort();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Journal journal = (Journal) o;
        return nextId == journal.nextId
                && greatestIdWithEntry == journal.greatestIdWithEntry
                && Objects.equals(entries, journal.entries);
    }

    @Override
    public int hashCode() {
        return Objects.hash(entries, nextId, greatestIdWithEntry);
    }
}
