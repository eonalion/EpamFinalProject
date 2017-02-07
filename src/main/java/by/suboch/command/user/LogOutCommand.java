package by.suboch.command.user;

import by.suboch.command.CommandConstants;
import by.suboch.command.IServletCommand;
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

        request.getSession().removeAttribute(CommandConstants.ATTR_ACCOUNT);
        request.getSession().removeAttribute(CommandConstants.ATTR_GENRE_LIST);
        request.getSession().removeAttribute(CommandConstants.ATTR_TRACK_LIST);
        request.getSession().removeAttribute(CommandConstants.ATTR_ARTIST_LIST);
        request.getSession().removeAttribute(CommandConstants.ATTR_ACCOUNT_PURCHASES);
        request.getSession().removeAttribute(CommandConstants.ATTR_COMMENT_LIST);
        request.getSession().removeAttribute(CommandConstants.ATTR_ACCOUNT_LIST);
        request.getSession().removeAttribute(CommandConstants.ATTR_ALBUM_LIST);
        request.getSession().removeAttribute(CommandConstants.ATTR_TRACK_COMMENT_LIST);

        return ConfigurationManager.getProperty(REGISTRATION_LOGIN_PAGE);
    }
}