package by.suboch.logic;

import java.util.ArrayList;
import java.util.List;

/**
 *
 */
public class LogicActionResult {
    public enum State {
        SUCCESS, FAILURE
    }

    private State state;

    private List<ResultConstants> outcomes = new ArrayList<>();

    public void addOutcome(ResultConstants outcome) {
        outcomes.add(outcome);
    }

    public void setState(State state) {
        this.state = state;
    }

    public State getState() {
        return state;
    }
}
