package by.suboch.controller;

import java.io.Serializable;

/**
 *
 */
public class ControllerConfig implements Serializable {
    //TODO: hashCode, equals, toString.

    public enum State {FORWARD, REDIRECT, AJAX}
    private ControllerConfig.State state;
    private String command;

    public State getState() {
        return state;
    }

    public void setState(State state) {
        this.state = state;
    }

    public String getCommand() {
        return command;
    }

    public void setCommand(String command) {
        this.command = command;
    }
}
