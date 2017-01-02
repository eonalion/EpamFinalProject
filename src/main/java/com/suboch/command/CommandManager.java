package com.suboch.command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 */
public class CommandManager {
    private static final String COMMAND_PARAM = "command";

    public IServletCommand defineCommand(HttpServletRequest request, HttpServletResponse response) {
        IServletCommand currentCommand = new EmptyCommand();
        String currentAction = request.getParameter(COMMAND_PARAM);
        if(currentAction==null||currentAction.isEmpty()) {
            return currentCommand;
        }
        try {
            CommandType currentType = CommandType.valueOf(currentAction.toUpperCase());
            currentCommand = currentType.getCurrentCommand();
        } catch (IllegalArgumentException e) {
            //FIXME: exception
        }
        return currentCommand;
    }
}
