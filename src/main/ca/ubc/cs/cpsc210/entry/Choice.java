package ca.ubc.cs.cpsc210.entry;

import ca.ubc.cs.cpsc210.exceptions.ElementNotFoundException;
import ca.ubc.cs.cpsc210.exceptions.OutOfBoundsException;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Objects;

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
     * Constructs a {@code Choice} as a deep copy of given {@code Choice}. {@code String}s and {@code Consequence}s are
     * copied by reference as they are both immutable.
     * @param that the object to make a deep copy from
     */
    public Choice(Choice that) {
        this(that.description);
        for (Consequence cons : that.pros) {
            addPro(cons);
        }
        for (Consequence cons : that.cons) {
            addCon(cons);
        }
        for (Consequence cons : that.regrets) {
            addRegret(cons);
        }
        this.regretValue = that.regretValue;
    }

    /**
     * Returns the description of this {@code Choice}.
     * @return the description for this {@code Choice} object
     */
    public String description() {
        return description;
    }

    /**
     * Returns the regret value.
     * @return the regret value, an {@code int} in [0, 100]
     */
    public int regretValue() {
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
     * Returns the number of {@code Consequence}s in {@code pros}.
     * @return number of pros
     */
    public int prosCount() {
        return pros.size();
    }

    /**
     * Returns the number of {@code Consequence}s in {@code cons}.
     * @return number of cons
     */
    public int consCount() {
        return cons.size();
    }

    /**
     * Returns the number of {@code Consequence}s in {@code regrets}.
     * @return number of regrets
     */
    public int regretsCount() {
        return regrets.size();
    }

    /**
     * Sets regret scale to given {@code value}, an {@code int} in [0, 100]. Note that this is different from the
     * {@code regrets} property. {@code regretValue} is just a number, while {@code regrets} is a collection of
     * {@code Consequence}s.
     * @param value the value to set regret to
     * @throws OutOfBoundsException if {@code value} is not in [0, 100]
     */
    public void setRegretValue(int value) throws OutOfBoundsException {
        if (value < 0 || value > 100) {
            throw new OutOfBoundsException();
        }

        this.regretValue = value;
    }

    /**
     * If not already in {@code pros}, adds given {@code consequence} to this {@code Choice}'s {@code pros}.
     * Otherwise, does nothing.
     * @param consequence {@code Consequence} added to {@code pros}
     */
    public void addPro(Consequence consequence) {
        add(pros, consequence);
    }

    /**
     * If not already in {@code cons}, adds given {@code consequence} to this {@code Choice}'s {@code cons}.
     * Otherwise, does nothing.
     * @param consequence {@code Consequence} added to {@code cons}
     */
    public void addCon(Consequence consequence) {
        add(cons, consequence);
    }

    /**
     * If not already in {@code regrets}, adds given {@code consequence} to this {@code Choice}'s {@code regrets}.
     * Otherwise, does nothing.
     * @param consequence {@code Consequence} added to {@code regrets}
     */
    public void addRegret(Consequence consequence) {
        add(regrets, consequence);
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

        throw new ElementNotFoundException();
    }

    /**
     * If not already in {@code list}, adds given {@code consequence} to {@code list}. Otherwise, does nothing.
     * @param toAdd {@code Consequence} added to {@code cons}
     */
    private void add(List<Consequence> list, Consequence toAdd) {
        for (Consequence cons : list) {
            if (cons.description().equals(toAdd.description())) {
                return;
            }
        }
        list.add(toAdd);
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Choice choice = (Choice) o;
        return regretValue == choice.regretValue
                && description.equals(choice.description)
                && pros.equals(choice.pros)
                && cons.equals(choice.cons)
                && regrets.equals(choice.regrets);
    }

    @Override
    public int hashCode() {
        return Objects.hash(description, pros, cons, regrets, regretValue);
    }
}
