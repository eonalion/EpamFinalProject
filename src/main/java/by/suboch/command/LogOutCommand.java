package by.suboch.command;

import by.suboch.entity.Visitor;
import by.suboch.manager.ConfigurationManager;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import static by.suboch.controller.ControllerConstants.VISITOR_KEY;

/**
 *
 */
public class LogOutCommand implements IServletCommand {
    private static  final Logger LOG = LogManager.getLogger();

    private static final String REGISTRATION_LOGIN_PAGE = "path.page.registration";

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        Visitor visitor = (Visitor) request.getSession().getAttribute(VISITOR_KEY);
        visitor.setRole(Visitor.Role.GUEST);
        //TODO: Remove all attributes from session.

        return ConfigurationManager.getProperty(REGISTRATION_LOGIN_PAGE);
    }
}