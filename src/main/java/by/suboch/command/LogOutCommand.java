package by.suboch.command;

import by.suboch.manager.ConfigurationManager;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import static by.suboch.command.CommandConstants.*;

/**
 *
 */
public class LogOutCommand implements IServletCommand {
    private static  final Logger LOG = LogManager.getLogger();

    private static final String REGISTRATION_LOGIN_PAGE = "path.page.registration";

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        request.getSession().removeAttribute(LOCALE_ATTR);
        request.getSession().removeAttribute(MESSAGE_ATTR);
        request.getSession().removeAttribute(CURRENT_PAGE_ATTR);
        request.getSession().removeAttribute(ACCOUNT_ATTR);
        request.getSession().setAttribute(VISITOR_ROLE_ATTR, VisitorRole.GUEST.toString());
        return ConfigurationManager.getProperty(REGISTRATION_LOGIN_PAGE);
    }
}