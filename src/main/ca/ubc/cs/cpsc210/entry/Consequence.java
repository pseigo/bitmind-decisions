package ca.ubc.cs.cpsc210.entry;

import ca.ubc.cs.cpsc210.exceptions.EmptyDescriptionException;

import java.util.Objects;

/**
 * Represents a side effect of some action, such as making a decision. Once constructed, the {@code Consequence} is
 * immutable. This class is mostly a wrapper around a {@code String}, but has various properties regarding the
 * consequence. A {@code Consequence} object is instantiated using {@code Consequence.Builder} and method chaining, as
 * per the "Builder" design pattern.
 * @author Peyton Seigo
 * @see <a href="https://en.wikipedia.org/wiki/Builder_pattern">Builder Pattern</a>
 * @see <a href="https://en.wikipedia.org/wiki/Method_chaining">Method Chaining</a>
 */
public class Consequence {

    public static class Builder {
        private String description;
        private boolean isLongTerm = false;
        private boolean isShortTerm = false;

        /**
         * Constructor for builder of {@code Consequence} class. Given {@code description} must be a non-empty string.
         * @param description description for the {@code Consequence}
         * @throws EmptyDescriptionException if given {@code description} is an empty string
         */
        public Builder(String description) {
            if (description.isEmpty()) {
                throw new EmptyDescriptionException();
            }

            this.description = description;
        }

        /**
         * Toggles {@code isLongTerm} to true.
         * @return this for "Builder" pattern chaining
         */
        public Builder isLongTerm() {
            this.isLongTerm = true;
            return this;
        }

        /**
         * Sets {@code isShortTerm} to true.
         * @return this for "Builder" pattern chaining
         */
        public Builder isShortTerm() {
            this.isShortTerm = true;
            return this;
        }

        /**
         * Returns a new {@code Consequence} initialized with values of member in {@code this}.
         * @return a new {@code Consequence} with matching members
         */
        public Consequence build() {
            Consequence cons = new Consequence();
            cons.description = this.description;
            cons.isLongTerm = this.isLongTerm;
            cons.isShortTerm = this.isShortTerm;

            return cons;
        }
    }

    private String description;
    private boolean isLongTerm;
    private boolean isShortTerm;

    /**
     * Constructor is private as per the "Builder" pattern.
     */
    private Consequence() {
        // Uses Builder pattern.
    }

    /**
     * Returns description of {@code this}.
     * @return description of {@code this}
     */
    public String description() {
        return description;
    }

    /**
     * Returns true if {@code this} is a long term consequence, otherwise false.
     * @return true if long term, false if not long term
     */
    public Boolean isLongTerm() {
        return isLongTerm;
    }

    /**
     * Returns true if {@code this} is a short term consequence, otherwise false.
     * @return true if short term, false if not short term
     */
    public Boolean isShortTerm() {
        return isShortTerm;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Consequence that = (Consequence) o;
        return isLongTerm == that.isLongTerm
                && isShortTerm == that.isShortTerm
                && description.equals(that.description);
    }

    @Override
    public int hashCode() {
        return Objects.hash(description, isLongTerm, isShortTerm);
    }
}