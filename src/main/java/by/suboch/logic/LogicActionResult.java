package by.suboch.logic;

/**
 *
 */
public class LogicActionResult {
    enum State {
        SUCCESS, FAILURE
    }

    private State state;
    private ActionResult result;
    private String message;
    private String inputName;

    public State getState() {
        return state;
    }

    public void setState(State state) {
        this.state = state;
    }

    public ActionResult getResult() {
        return result;
    }

    public void setResult(ActionResult result) {
        this.result = result;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getInputName() {
        return inputName;
    }

    public void setInputName(String inputName) {
        this.inputName = inputName;
    }
}