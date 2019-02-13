package ca.ubc.cs.cpsc210.entry;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.TextStyle;
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

    public static EntryDateTime of(LocalDateTime localDateTime) {
        return new EntryDateTime(ZonedDateTime.of(localDateTime, LOCAL_TIME_ZONE));
    }

    public static EntryDateTime now() {
        return new EntryDateTime(ZonedDateTime.now(LOCAL_TIME_ZONE));
    }

    public String dateShort() {
        return dateTime.getDayOfWeek().getDisplayName(TextStyle.SHORT, LOCALE) + " "
                + dateTime.getMonth().getDisplayName(TextStyle.SHORT, LOCALE) + " "
                + dateTime.getDayOfMonth() + " "
                + dateTime.getYear();
    }
}
