package ca.ubc.cs.cpsc210.entry;

import ca.ubc.cs.cpsc210.exceptions.NoEntriesAddedException;
import ca.ubc.cs.cpsc210.exceptions.NoEntryWithIDException;

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
     * Constructor for {@code Journal} class.
     */
    public Journal() {
        entries = new HashMap<>();
        nextId = 1;
        greatestIdWithEntry = 0;
    }

    /**
     * Adds a deep copy of given {@code entry} to this [@code Journal}'s entries.
     * @param entry entry added to {@code this}
     */
    public void add(Entry entry) {
        // Calling deep copy constructor
        Entry toAdd = new Entry(entry);
        entries.put(nextId, toAdd);
        greatestIdWithEntry = nextId;
        ++nextId;
        lastEntryDateTime = entry.creationDateTime();
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
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Journal journal = (Journal) o;
        return nextId == journal.nextId
                && greatestIdWithEntry == journal.greatestIdWithEntry
                && Objects.equals(entries, journal.entries)
                && Objects.equals(lastEntryDateTime, journal.lastEntryDateTime);
    }

    @Override
    public int hashCode() {
        return Objects.hash(entries, nextId, greatestIdWithEntry, lastEntryDateTime);
    }
}
