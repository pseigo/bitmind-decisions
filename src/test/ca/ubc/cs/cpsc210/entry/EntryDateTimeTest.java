package ca.ubc.cs.cpsc210.entry;

import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Month;
import java.time.ZonedDateTime;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * @author Peyton Seigo
 */
public class EntryDateTimeTest extends ModelTest {
    private EntryDateTime entryDateTime;
    private LocalDate testDate;

    @Test
    public void testNow() {
        entryDateTime = EntryDateTime.now();
        testDate = ZonedDateTime.now().toLocalDate();
        assertEquals(formatShort(testDate), entryDateTime.dateShort());
    }

    @Test
    public void testOf() {
        LocalDateTime testDateTime = LocalDateTime.of(2000, Month.APRIL, 14, 6, 23);
        entryDateTime = EntryDateTime.of(testDateTime);
        testDate = testDateTime.toLocalDate();
        assertEquals(formatShort(testDate), entryDateTime.dateShort());
    }

    @Test
    public void testGetLocalDateAndTime() {
        entryDateTime = EntryDateTime.now();
        LocalDateTime ldt = LocalDateTime.now();
        assertEquals(ldt.toLocalDate(), entryDateTime.toLocalDate());
        assertEquals(ldt.toLocalTime().getHour(), entryDateTime.toLocalTime().getHour(), 1);
        assertEquals(ldt.toLocalTime().getMinute(), entryDateTime.toLocalTime().getMinute(), 1);
        assertEquals(ldt.toLocalTime().getSecond(), entryDateTime.toLocalTime().getSecond(), 3);
    }
}
