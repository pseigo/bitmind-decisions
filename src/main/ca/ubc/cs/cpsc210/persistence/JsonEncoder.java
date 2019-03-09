package ca.ubc.cs.cpsc210.persistence;

import ca.ubc.cs.cpsc210.entry.*;
import org.json.JSONArray;
import org.json.JSONObject;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Map;

/**
 * @author Peyton Seigo
 */
public class JsonEncoder {

    public static JSONObject journalToJson(Journal journal) {
        JSONObject entriesMap = new JSONObject();
        for (Map.Entry<Integer, Entry> entry : journal.entries().entrySet()) {
            entriesMap.put(entry.getKey().toString(), entryToJson(entry.getValue()));
        }
        return entriesMap;
    }

    public static JSONObject entryToJson(Entry entry) {
        JSONObject entryJson = new JSONObject();

        JSONArray choicesList = new JSONArray();
        for (Choice choice : entry.choices()) {
            choicesList.put(choiceToJson(choice));
        }

        entryJson.put("choices", choicesList);
        entryJson.put("description", entry.description());
        entryJson.put("status", entry.status().toString());

        JSONObject creationDateTime = entryDateTimeToJson(entry.creationDateTime());
        JSONObject completionDateTime = entryDateTimeToJson(entry.completionDateTime());
        entryJson.put("creationDateTime", creationDateTime);
        entryJson.put("completionDateTime", (completionDateTime == null) ? JSONObject.NULL : completionDateTime);

        return entryJson;
    }

    public static JSONObject entryDateTimeToJson(EntryDateTime entryDateTime) {
        if (entryDateTime == null) {
            return null;
        }

        JSONObject entryDateTimeJson = new JSONObject();

        LocalDate date = entryDateTime.toLocalDate();
        LocalTime time = entryDateTime.toLocalTime();

        entryDateTimeJson.put("year", date.getYear());
        entryDateTimeJson.put("month", date.getMonthValue());
        entryDateTimeJson.put("dayOfMonth", date.getDayOfMonth());
        entryDateTimeJson.put("hour", time.getHour());
        entryDateTimeJson.put("minute", time.getMinute());
        entryDateTimeJson.put("second", time.getSecond());

        return entryDateTimeJson;
    }

    public static JSONObject choiceToJson(Choice choice) {
        JSONObject choiceJson = new JSONObject();
        choiceJson.put("description", choice.description());
        choiceJson.put("regretValue", choice.regretValue());
        choiceAddConsequences(choiceJson, choice);
        return choiceJson;
    }

    private static void choiceAddConsequences(JSONObject choiceJson, Choice choice) {
        JSONArray prosList = new JSONArray();
        for (Consequence consequence : choice.pros()) {
            prosList.put(consequenceToJson(consequence));
        }
        JSONArray consList = new JSONArray();
        for (Consequence consequence : choice.cons()) {
            consList.put(consequenceToJson(consequence));
        }
        JSONArray regretsList = new JSONArray();
        for (Consequence consequence : choice.regrets()) {
            regretsList.put(consequenceToJson(consequence));
        }

        choiceJson.put("pros", prosList);
        choiceJson.put("cons", consList);
        choiceJson.put("regrets", regretsList);
    }

    public static JSONObject consequenceToJson(Consequence consequence) {
        JSONObject consequenceJson = new JSONObject();

        consequenceJson.put("description", consequence.description());
        consequenceJson.put("isLongTerm", consequence.isLongTerm());
        consequenceJson.put("isShortTerm", consequence.isShortTerm());

        return consequenceJson;
    }

}
