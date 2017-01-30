package by.suboch.command;

import java.io.Serializable;

/**
 *
 */
public class ErrorHolder implements Serializable {//TODO: hashCode, equals, toString
    private String causeMessage;
    private String toDoMessage;
    private String currentPage;
    private Exception exception;

    public ErrorHolder() {
    }

    public String getCurrentPage() {
        return currentPage;
    }

    public void setCurrentPage(String currentPage) {
        this.currentPage = currentPage;
    }

    public String getCauseMessage() {
        return causeMessage;
    }

    public void setCauseMessage(String causeMessage) {
        this.causeMessage = causeMessage;
    }

    public String getToDoMessage() {
        return toDoMessage;
    }

    public void setToDoMessage(String toDoMessage) {
        this.toDoMessage = toDoMessage;
    }

    public Exception getException() {
        return exception;
    }

    public void setException(Exception exception) {
        this.exception = exception;
    }

}
