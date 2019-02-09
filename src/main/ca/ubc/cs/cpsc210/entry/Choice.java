package ca.ubc.cs.cpsc210.entry;

import ca.ubc.cs.cpsc210.exceptions.ElementNotFoundException;
import ca.ubc.cs.cpsc210.exceptions.OutOfBoundsException;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * Represents a particular choice for an {@code Entry}. Has a description and properties such as pros and cons that
 * detail the choice.
 * @author Peyton Seigo
 */
public class Choice {
    private String description;
    private List<Consequence> pros;
    private List<Consequence> cons;
    private List<Consequence> regrets;
    private int regretValue;

    /**
     * Constructor for {@code Choice} class, initialized with a description of the potential choice.
     * @param description short description of what the choice is
     */
    public Choice(String description) {
        this.description = description;
        pros = new ArrayList<>();
        cons = new ArrayList<>();
        regrets = new ArrayList<>();
        regretValue = 0;
    }

    /**
     * Returns the description of this {@code Choice}.
     * @return the description for this {@code Choice} object
     */
    public String getDescription() {
        return description;
    }

    /**
     * Returns the regret value.
     * @return the regret value, an {@code int} in [0, 100]
     */
    public int getRegretValue() {
        return regretValue;
    }

    /**
     * Returns first occurrence of a {@code Consequence} in {@code pros} such that its description equals given
     * {@code description}.
     * @param description description to match for
     * @return {@code Consequence} with matching description, if found
     * @throws ElementNotFoundException if element is not found in pros
     */
    public Consequence getPro(String description) throws ElementNotFoundException {
        return get(pros, description);
    }

    /**
     * Returns first occurrence of a {@code Consequence} in {@code cons} such that its description equals given
     * {@code description}.
     * @param description description to match for
     * @return {@code Consequence} with matching description, if found
     * @throws ElementNotFoundException if element is not found in cons
     */
    public Consequence getCon(String description) throws ElementNotFoundException {
        return get(cons, description);
    }

    /**
     * Returns first occurrence of a {@code Consequence} in {@code regrets} such that its description equals given
     * {@code description}.
     * @param description description to match for
     * @return {@code Consequence} with matching description, if found
     * @throws ElementNotFoundException if element is not found in regrets
     */
    public Consequence getRegret(String description) throws ElementNotFoundException {
        return get(regrets, description);
    }

    /**
     * Sets regret scale to given {@code value}, an {@code int} in [0, 100]. Note that this is different from the
     * {@code regrets} property. {@code regretValue} is just a number, while {@code regrets} is a collection of
     * {@code Consequence}s.
     * @param value the value to set regret to
     * @throws OutOfBoundsException if {@code value} is not in [0, 100]
     */
    public void setRegretValue(int value) throws OutOfBoundsException {
        this.regretValue = value;
    }

    /**
     * Adds a {@code consequence} to this {@code Choice}'s {@code pros}. Duplicates are allowed.
     * @param consequence {@code Consequence} added to {@code pros}
     */
    public void addPro(Consequence consequence) {
        pros.add(consequence);
    }

    /**
     * Adds a {@code consequence} to this {@code Choice}'s {@code cons}. Duplicates are allowed.
     * @param consequence {@code Consequence} added to {@code cons}
     */
    public void addCon(Consequence consequence) {
        cons.add(consequence);
    }

    /**
     * Adds a {@code consequence} to this {@code Choice}'s {@code regrets}. Duplicates are allowed.
     * @param consequence {@code Consequence} added to {@code regrets}
     */
    public void addRegret(Consequence consequence) {
        regrets.add(consequence);
    }

    /**
     * Removes first occurrence of {@code Consequence} in {@code pros} such that its description equals given
     * {@code description}. If no match is found, {@code pros} is not modified.
     * @param description description to match for
     */
    public void removePro(String description) {
        remove(pros, description);
    }

    /**
     * Removes first occurrence of {@code Consequence} in {@code cons} such that its description equals given
     * {@code description}. If no match is found, {@code cons} is not modified.
     * @param description description to match for
     */
    public void removeCon(String description) {
        remove(cons, description);
    }

    /**
     * Removes first occurrence of {@code Consequence} in {@code regrets} such that its description equals given
     * {@code description}. If no match is found, {@code regrets} is not modified.
     * @param description description to match for
     */
    public void removeRegret(String description) {
        remove(regrets, description);
    }

    /**
     * Returns first occurrence of a {@code Consequence} in {@code list} such that its description equals given
     * {@code description}.
     * @param list list to search for a matching {@code Consequence}
     * @param description description to match for
     * @return {@code Consequence} with matching description, if found
     * @throws ElementNotFoundException if element is not found in cons
     */
    private Consequence get(List<Consequence> list, String description) throws ElementNotFoundException {
        for (Consequence cons : list) {
            if (cons.description().equals(description)) {
                return cons;
            }
        }

        return null;
    }

    /**
     * Removes first occurrence of {@code Consequence} in {@code list} such that its description equals given
     * {@code description}. If no match is found, {@code list} is not modified.
     * @param list list to search for a matching {@code Consequence}
     * @param description description to match for
     */
    private void remove(List<Consequence> list, String description) {
        Iterator<Consequence> it = list.iterator();
        while (it.hasNext()) {
            if (it.next().description().equals(description)) {
                it.remove();
                return;
            }
        }
    }
}
