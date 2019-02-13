package ca.ubc.cs.cpsc210.entry;

import org.junit.jupiter.api.Test;

/**
 * @author Peyton Seigo
 */
public class EntryDateTimeTest {
    private EntryDateTime entryDateTime;
    
    @Test
    public void testConstructor() {

        entryDateTime = EntryDateTime.now();
    }
}
