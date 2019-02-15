package ca.ubc.cs.cpsc210.entry;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * @author Peyton Seigo
 */
public abstract class ModelTest implements DateFormatTest {
    public String formatShort(LocalDate date) {
        return date.format(DateTimeFormatter.ofPattern("E MMM d u"));
    }
}
