package ca.ubc.cs.cpsc210.model;

import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Month;
import java.time.ZonedDateTime;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @author Peyton Seigo
 */
class EntryDateTimeTest extends ModelTest {
    private EntryDateTime entryDateTime;
    private LocalDate testDate;

    @Test
    void testNow() {
        entryDateTime = EntryDateTime.now();
        testDate = ZonedDateTime.now().toLocalDate();
        assertEquals(formatShort(testDate), entryDateTime.dateShort());
    }

    @Test
    void testOf() {
        LocalDateTime testDateTime = LocalDateTime.of(2000, Month.APRIL, 14, 6, 23);
        entryDateTime = EntryDateTime.of(testDateTime);
        testDate = testDateTime.toLocalDate();
        assertEquals(formatShort(testDate), entryDateTime.dateShort());
    }

    @Test
    void testGetLocalDateAndTime() {
        entryDateTime = EntryDateTime.now();
        LocalDateTime ldt = LocalDateTime.now();
        assertEquals(ldt.toLocalDate(), entryDateTime.toLocalDate());
        assertEquals(ldt.toLocalTime().getHour(), entryDateTime.toLocalTime().getHour(), 1);
        assertEquals(ldt.toLocalTime().getMinute(), entryDateTime.toLocalTime().getMinute(), 1);
        assertEquals(ldt.toLocalTime().getSecond(), entryDateTime.toLocalTime().getSecond(), 3);
    }

    @Test
    void testEquals() {
        entryDateTime = EntryDateTime.now();


        assertEquals(entryDateTime, entryDateTime);
        assertSame(entryDateTime, entryDateTime);


        assertNotEquals(null, entryDateTime);
        assertNotEquals(entryDateTime, new Object());


        EntryDateTime sameTime = EntryDateTime.of(
                LocalDateTime.of(entryDateTime.toLocalDate(), entryDateTime.toLocalTime())
        );

        assertEquals(entryDateTime, sameTime);
        assertEquals(entryDateTime.hashCode(), sameTime.hashCode());
        assertNotSame(entryDateTime, sameTime);


        EntryDateTime plusFiveMinutes = EntryDateTime.of(LocalDateTime.now().plusMinutes(5));
        assertNotEquals(entryDateTime, plusFiveMinutes);
    }
}
