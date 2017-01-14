package by.suboch.command;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import static by.suboch.command.CommandConstants.COMMAND_PARAM;
import static by.suboch.command.CommandConstants.VISITOR_ROLE_ATTR;

/**
 *
 */
public class CommandManager {
    private static final Logger LOG = LogManager.getLogger();

    public IServletCommand defineCommand(HttpServletRequest request, HttpServletResponse response) {
        IServletCommand currentCommand = new EmptyCommand();
        String currentAction = request.getParameter(COMMAND_PARAM);
        if(currentAction==null||currentAction.isEmpty()) {
            return currentCommand;
        }
        try {
            CommandType currentType = CommandType.valueOf(currentAction.toUpperCase());
            VisitorRole currentRole = VisitorRole.valueOf((String) request.getSession().getAttribute(VISITOR_ROLE_ATTR));

            if(currentType.visitorRole.contains(currentRole)) {
                currentCommand = currentType.getCurrentCommand();
            }
        } catch (IllegalArgumentException e) {
            //FIXME: exception
        }
        return currentCommand;
    }
}
