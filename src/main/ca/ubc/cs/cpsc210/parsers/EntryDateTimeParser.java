package ca.ubc.cs.cpsc210.parsers;

import ca.ubc.cs.cpsc210.model.EntryDateTime;
import org.json.JSONObject;

import java.time.DateTimeException;
import java.time.LocalDateTime;

/**
 * @author Peyton Seigo
 */
class EntryDateTimeParser {

    /**
     * Parses input (a JSONObject as text) to an {@code EntryDateTime} and returns it.
     * @param input JSON representing the {@code EntryDateTime}
     * @return {@code EntryDateTime} object created from input string
     * @throws DateTimeException if date-time data is invalid
     */
    public static EntryDateTime parse(String input) throws DateTimeException {
        JSONObject edtJson = new JSONObject(input);
        LocalDateTime localDateTime = LocalDateTime.of(
                edtJson.getInt("year"),
                edtJson.getInt("month"),
                edtJson.getInt("dayOfMonth"),
                edtJson.getInt("hour"),
                edtJson.getInt("minute"),
                edtJson.getInt("second")
        );
        return EntryDateTime.of(localDateTime);
    }

}
