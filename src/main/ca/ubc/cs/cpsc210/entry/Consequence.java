package ca.ubc.cs.cpsc210.entry;

public class Consequence {
    public static class Builder {
        private String description;
        private boolean isLongTerm = false;
        private boolean isShortTerm = false;

        public Builder(String description) {
            this.description = description;
        }

        public Builder isLongTerm(boolean isLongTerm) {
            this.isLongTerm = isLongTerm;
            return this;
        }

        public Builder isShortTerm(boolean isShortTerm) {
            this.isShortTerm = isShortTerm;
            return this;
        }

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

    private Consequence() {
        // Uses Builder pattern.
    }

    public String description() {
        return description;
    }
}
