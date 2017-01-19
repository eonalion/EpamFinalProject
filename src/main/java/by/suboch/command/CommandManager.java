package by.suboch.command;

import by.suboch.manager.MessageManager;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import static by.suboch.command.CommandConstants.COMMAND_PARAM;
import static by.suboch.command.CommandConstants.MESSAGE_ATTR;
import static by.suboch.command.CommandConstants.VISITOR_ROLE_ATTR;

/**
 *
 */
public class CommandManager {
    private static final Logger LOG = LogManager.getLogger();

    private static final String ERROR_MESSAGE = "message.error.illegalAction";

    public IServletCommand defineCommand(HttpServletRequest request, HttpServletResponse response) {
        IServletCommand currentCommand = new EmptyCommand();
        String currentAction = request.getParameter(COMMAND_PARAM);
        if (currentAction == null || currentAction.isEmpty()) {
            return currentCommand;
        }

        CommandType currentType = CommandType.valueOf(currentAction.toUpperCase());

        VisitorRole currentRole = VisitorRole.valueOf((String) request.getSession().getAttribute(VISITOR_ROLE_ATTR));

        if (currentType.visitorRole.contains(currentRole)) {
            currentCommand = currentType.getCurrentCommand();
        } else {
            request.getSession().setAttribute(MESSAGE_ATTR, MessageManager.getProperty(ERROR_MESSAGE));
        }
        return currentCommand;
    }
}
