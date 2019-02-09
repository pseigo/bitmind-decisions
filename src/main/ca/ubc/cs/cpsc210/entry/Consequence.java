package ca.ubc.cs.cpsc210.entry;

/**
 * Represents a side effect of some action, such as making a decision. This is mostly a wrapper around a {@code String},
 * but has properties such as whether the consequence is short or long term. A {@code Consequence} object is
 * instantiated using {@code Consequence.Builder} as per the "Builder" design pattern.
 * @author Peyton Seigo
 * @see <a href="https://en.wikipedia.org/wiki/Builder_pattern">Builder Pattern</a>
 */
public class Consequence {
    public static class Builder {
        private String description;
        private boolean isLongTerm = false;
        private boolean isShortTerm = false;

        public Builder(String description) {
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
}