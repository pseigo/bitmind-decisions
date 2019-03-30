package ca.ubc.cs.cpsc210.model;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * @author Peyton Seigo
 */
abstract class ModelTest {
    String formatShort(LocalDate date) {
        return date.format(DateTimeFormatter.ofPattern("E MMM d u"));
    }
}
