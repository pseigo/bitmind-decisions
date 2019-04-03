package ca.ubc.cs.cpsc210.model;

import java.util.Iterator;
import java.util.Map;
import java.util.NoSuchElementException;


/**
 * @author Peyton Seigo
 */
class ReverseChronoEntryIterator implements Iterator<Entry> {

    private final Map<Integer, Entry> entries;
    private int previousId;
    private int nextId;

    ReverseChronoEntryIterator(Map<Integer, Entry> entries, int greatestIdWithEntry) {
        this.entries = entries;
        nextId = greatestIdWithEntry;
        previousId = 0;
    }

    /**
     * Returns {@code true} if the iteration has more elements.
     * (In other words, returns {@code true} if {@link #next} would
     * return an element rather than throwing an exception.)
     *
     * @return {@code true} if the iteration has more elements
     */
    @Override
    public boolean hasNext() {
        updateNextId();
        return nextId != 0;
    }

    /**
     * Returns the next element in the iteration.
     *
     * @return the next element in the iteration
     * @throws NoSuchElementException if the iteration has no more elements
     */
    @Override
    public Entry next() {
        if (!hasNext()) {
            throw new NoSuchElementException();
        }
        previousId = nextId;
        return entries.get(nextId);
    }

    /**
     * Sets {@code nextId} to the next valid ID in {@code entries}. If there is next ID, sets {@code nextId} to 0.
     */
    private void updateNextId() {
        if (nextId != previousId) {
            return;
        }

        int id;
        for (id = previousId - 1; id >= 0; --id) {
            if (entries.containsKey(id)) {
                nextId = id;
                return;
            }
        }

        nextId = 0;
    }
}
