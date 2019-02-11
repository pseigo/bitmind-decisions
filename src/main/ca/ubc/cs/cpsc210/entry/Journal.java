package ca.ubc.cs.cpsc210.entry;

import ca.ubc.cs.cpsc210.exceptions.NoEntryWithIDException;

import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.HashMap;
import java.util.Map;

/**
 * Represents a collection of entries.
 * @author Peyton Seigo
 */
public class Journal {
    private Map<Integer, Entry> entries;
    private ZonedDateTime lastEntryDateTime = null;
    private ZoneId localTimeZone = ZoneId.of("America/Vancouver");

    /**
     * Constructor for {@code Journal} class.
     */
    public Journal() {
        entries = new HashMap<>();
    }

    /**
     * Adds given {@code entry} to this [@code Journal}'s entries.
     * @param entry entry added to {@code this}
     */
    public void add(Entry entry) {

    }

    /**
     * If {@code Entry} with given {@code id} exists, remove from {@code this}.
     * @param id id to match for
     * @throws NoEntryWithIDException if no {@code Entry} with given {@code id} exists
     */
    public void remove(int id) throws NoEntryWithIDException {

    }

    /**
     * If {@code Entry} with given {@code id} exists, return matching {@code Entry}.
     * @param id id to match for
     * @return {@code Entry} with an id equal to given {@code id}
     * @throws NoEntryWithIDException if no {@code Entry} with given {@code id} exists
     */
    public Entry get(int id) throws NoEntryWithIDException {
        return null;
    }

    /**
     * Returns number of entries in {@code this}.
     * @return number of entries
     */
    public int count() {
        return 0;
    }

    /**
     * Returns the date an {@code Entry} was last added to {@code this}, formatted as a string.
     * @return last entry date formatted as a string
     */
    public String lastEntryDateTime() {
        return "";
    }
}
