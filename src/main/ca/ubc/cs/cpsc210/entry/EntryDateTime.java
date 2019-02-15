package ca.ubc.cs.cpsc210.entry;

import java.time.*;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

/**
 * @author Peyton Seigo
 */
public class EntryDateTime {
    private static final Locale LOCALE = Locale.CANADA;
    private static final ZoneId LOCAL_TIME_ZONE = ZoneId.of("America/Vancouver");

    private ZonedDateTime dateTime;

    private EntryDateTime(ZonedDateTime dateTime) {
        this.dateTime = dateTime;
    }

    /**
     * Returns an instance of {@code EntryDateTime} from a given date and time.
     * @return the entry date-time
     */
    public static EntryDateTime of(LocalDateTime dateTime) {
        return new EntryDateTime(ZonedDateTime.of(dateTime, LOCAL_TIME_ZONE));
    }

    /**
     * Returns an instance of {@code EntryDateTime} from the system clock.
     * @return the entry date-time
     */
    public static EntryDateTime now() {
        return new EntryDateTime(ZonedDateTime.now(LOCAL_TIME_ZONE));
    }

    /**
     * Returns the set date in a short format.
     * @return the formatted date
     */
    public String dateShort() {
        return dateTime.format(DateTimeFormatter.ofPattern("E MMM d u", LOCALE));
    }

    /**
     * Returns the set date.
     * @return the set date
     */
    public LocalDate toLocalDate() {
        return dateTime.toLocalDate();
    }

    /**
     * Returns the set time.
     * @return the set time
     */
    public LocalTime toLocalTime() {
        return dateTime.toLocalTime();
    }
}
