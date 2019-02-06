package ca.ubc.cs.cpsc210.entry;

import java.util.ArrayList;
import java.util.List;

class Entry {
    private String problemDescription;
    private List<Choice> choices;
    private Choice decision = null;
    private Date creationDate;
    private Date completionDate = null;
    private Status status;

    Entry(String problemDescription) {
        this.problemDescription = problemDescription;
        choices = new ArrayList<>();
        creationDate = new Date();
        creationDate.setNow();
        status = Status.DRAFT;
    }
}
