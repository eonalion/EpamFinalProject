package by.suboch.command;

/**
 *
 */
public class CommandManager {
    private static final String MESSAGE_ERROR_ILLEGAL_ACTION = "message.error.illegalAction";

    public IServletCommand defineCommand(String commandAction) {
        IServletCommand currentCommand = new EmptyCommand();
        if (commandAction == null || commandAction.isEmpty()) {
            return currentCommand;
        }

        CommandType currentType = CommandType.valueOf(commandAction.toUpperCase());
        currentCommand = currentType.getCurrentCommand();
        return currentCommand;
    }
}
