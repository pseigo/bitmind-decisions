package ca.ubc.cs.cpsc210.model;

import ca.ubc.cs.cpsc210.exceptions.ElementNotFoundException;
import ca.ubc.cs.cpsc210.exceptions.EntryIncompleteException;

import java.util.*;

/**
 * Represents a problem with an arbitrary number of choices.
 * @author Peyton Seigo
 */
public class Entry implements Iterable<Choice> {

    private String problemDescription;
    private final List<Choice> choices;
    private final EntryDateTime creationDateTime;
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
     * Constructor for {@code Entry} class, initialized with given description, creation and completion dates, and a
     * status. {@code completionDateTime} is set to {@code null} if {@code status != Status.COMPLETE}.
     * @param problemDescription description of this {@code Entry}'s problem
     * @param creationDateTime date this {@code Entry} was created
     * @param completionDateTime if {@code COMPLETE}, date this {@code Entry} was completed. Else null
     * @param status status of this {@code Entry}
     */
    public Entry(String problemDescription,
                 EntryDateTime creationDateTime,
                 EntryDateTime completionDateTime,
                 Status status) {

        this.problemDescription = problemDescription;
        choices = new ArrayList<>();
        this.creationDateTime = creationDateTime;
        this.completionDateTime = status == Status.COMPLETE ? completionDateTime : null;
        this.status = status;
    }

    /**
     * Constructs an {@code Entry} as a deep copy of given {@code Entry}. {@code String}s and {@code Consequence}s are
     * copied by reference as they are both immutable.
     * @param that the object to make a deep copy from
     */
    public Entry(Entry that) {
        this.problemDescription = that.problemDescription;
        this.choices = new ArrayList<>();
        for (Choice choice : that.choices) {
            addChoice(new Choice(choice));
        }
        this.creationDateTime = that.creationDateTime;
        this.completionDateTime = that.completionDateTime;
        this.status = that.status;
    }

    /**
     * Returns description of {@code this}.
     * @return description of {@code this}
     */
    public String description() {
        return problemDescription;
    }

    /**
     * Sets entry description to {@code description}.
     * @param description to set
     */
    public void setDescription(String description) {
        problemDescription = description;
    }

    /**
     * Returns an unmodifiable map of choices.
     * @return choices
     */
    public List<Choice> choices() {
        return Collections.unmodifiableList(choices);
    }

    /**
     * Adds a deep copy of given {@code choice} to this {@code Entry}'s choices.
     * @param choice choice added to {@code this}
     */
    public void addChoice(Choice choice) {
        Choice toAdd = new Choice(choice);
        choices.add(toAdd);
    }

    /**
     * From {@code choices}, returns first occurrence of {@code Choice} such that its description equals given
     * {@code description}.
     * @param description description to match for
     * @return {@code Choice} with its description equal to given {@code description}
     * @throws ElementNotFoundException if no {@code Choice} with given {@code description} is found
     */
    public Choice getChoice(String description) throws ElementNotFoundException {
        for (Choice choice : choices) {
            if (choice.description().equals(description)) {
                return choice;
            }
        }

        throw new ElementNotFoundException();
    }

    /**
     * In {@code choices}, removes first occurrence of {@code Choice} such that its description equals given
     * {@code description}. If no match is found, {@code this} is not modified.
     * @param description description to match for
     */
    public void removeChoice(String description) {
        Iterator<Choice> it = choices.iterator();
        while (it.hasNext()) {
            if (it.next().description().equals(description)) {
                it.remove();
                return;
            }
        }
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
        if (status == Status.COMPLETE) {
            complete();
        } else {
            this.status = status;
            completionDateTime = null;
        }
    }

    /**
     * Sets {@code Status} to {@code COMPLETE} and sets {@code completionDateTime} to now.
     */
    public void complete() {
        this.status = Status.COMPLETE;
        completionDateTime = EntryDateTime.now();
    }

    /**
     * Returns the date that {@code this} was created, formatted as a string.
     * @return creation date formatted as a string
     */
    public String creationDate() {
        return creationDateTime.dateShort();
    }

    /**
     * Returns the date and time that {@code this} was created.
     * @return creation date and time
     */
    public EntryDateTime creationDateTime() {
        return creationDateTime;
    }

    /**
     * If {@code this} has been completed, returns the appropriate date formatted as a string.
     * @return completion date formatted as a string
     * @throws EntryIncompleteException if entry status is not {@code COMPLETE}
     */
    public String completionDate() throws EntryIncompleteException {
        if (status != Status.COMPLETE) {
            throw new EntryIncompleteException();
        }

        return completionDateTime.dateShort();
    }

    /**
     * If {@code this} entry is complete, returns the completion date and time. Otherwise, returns null.
     * @return creation date and time
     */
    public EntryDateTime completionDateTime() {
        return completionDateTime;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Entry entry = (Entry) o;
        return problemDescription.equals(entry.problemDescription)
                && choices.equals(entry.choices)
                && creationDateTime.equals(entry.creationDateTime)
                && status == entry.status;
    }

    @Override
    public int hashCode() {
        return Objects.hash(problemDescription, creationDateTime, choices, status);
    }

    /**
     * Returns an iterator over elements of type {@code Choice}.
     *
     * @return an Iterator.
     */
    @Override
    public Iterator<Choice> iterator() {
        return choices.iterator();
    }
}
