package ca.ubc.cs.cpsc210.entry;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

class Choice {
    private String description;
    private List<Consequence> pros;
    private List<Consequence> cons;
    private List<Consequence> regrets;
    private int regretValue;

    Choice(String description) {
        this.description = description;
        pros = new ArrayList<>();
        cons = new ArrayList<>();
        regrets = new ArrayList<>();
        regretValue = 0;
    }

    public String getDescription() {
        return description;
    }

    public void addPro(Consequence consequence) {
        add(pros, consequence);
    }

    public void getPro(String description) {
        get(pros, description);
    }

    public void removePro(String description) {
        remove(pros, description);
    }

    public void addCon(Consequence consequence) {
        add(cons, consequence);
    }

    public void getCon(String description) {
        get(cons, description);
    }

    public void removeCon(String description) {
        remove(cons, description);
    }

    public void addRegret(Consequence consequence) {
        add(regrets, consequence);
    }

    public void getRegret(String description) {
        get(regrets, description);
    }

    public void removeRegret(String description) {
        remove(regrets, description);
    }

    public void setRegret(int value) {
        this.regretValue = value;
    }

    private void add(List<Consequence> list, Consequence consequence) {
        list.add(consequence);
    }

    private Consequence get(List<Consequence> list, String description) {
        for (Consequence cons : list) {
            if (cons.description().equals(description)) {
                return cons;
            }
        }

        return null;
    }

    // TODO: remove() by description string or by the actual object?
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
