package ca.ubc.cs.cpsc210.entry;

import ca.ubc.cs.cpsc210.exceptions.ElementNotFoundException;

import java.util.ArrayList;
import java.util.List;

/**
 * Represents a problem with an arbitrary number of choices.
 * @author Peyton Seigo
 */
public class Entry {
    private String problemDescription;
    private List<Choice> choices;
    private Choice decision = null;
    private EntryDateTime creationDateTime;
    private EntryDateTime completionDateTime = null;
    private Status status;

    /**
     * Constructor for {@code Entry} class, initialized with a description of the problem.
     * @param problemDescription description of this {@code Entry}'s problem
     */
    public Entry(String problemDescription) {
        this.problemDescription = problemDescription;
        choices = new ArrayList<>();
        creationDateTime = EntryDateTime.now();
        status = Status.DRAFT;
    }

    /**
     * Returns description of {@code this}.
     * @return description of {@code this}
     */
    public String description() {
        return problemDescription;
    }

    /**
     * Adds given {@code choice} to this {@code Entry}'s choices.
     * @param choice choice added to {@code this}
     */
    public void addChoice(Choice choice) {
        choices.add(choice);
    }

    /**
     * From {@code choices}, returns first occurrence of {@code Consequence} such that its description equals given
     * {@code description}.
     * @param description description to match for
     * @return {@code Consequence} with its description equal to given {@code description}
     * @throws ElementNotFoundException if no {@code Consequence} with given {@code description} is found
     */
    public Choice getChoice(String description) throws ElementNotFoundException {
        return null;
    }

    /**
     * In {@code choices}, removes first occurrence of {@code Consequence} such that its description equals given
     * {@code description}. If no match is found, {@code this} is not modified.
     * @param description description to match for
     */
    public void removeChoice(String description) {
//        Iterator<Consequence> it = choices.iterator();
//        while (it.hasNext()) {
//            if (it.next().description().equals(description)) {
//                it.remove();
//                return;
//            }
//        }
    }

    /**
     * Returns the current entry {@code Status}.
     * @return the current entry {@code Status}
     */
    public Status status() {
        return status;
    }

    /**
     * Sets entry status to given {@code status}.
     * @param status the new status
     */
    public void setStatus(Status status) {
        this.status = status;
    }

    /**
     * Sets {@code Status} to {@code COMPLETE} and sets {@code completionDateTime} to now.
     */
    public void complete() {
        setStatus(Status.COMPLETE);
        completionDateTime = EntryDateTime.now();
    }

    /**
     * Returns the date {@code this} was created, formatted as a string.
     * @return creation date formatted as a string
     */
    public String creationDate() {
        return "";
    }

    /**
     * If {@code this} has been completed, returns the appropriate date formatted as a string.
     * @return completion date formatted as a string
     */
    public String completionDate() {
        return "";
    }
}
