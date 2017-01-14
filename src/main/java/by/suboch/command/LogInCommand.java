package by.suboch.command;

import by.suboch.exception.LogicException;
import by.suboch.logic.AccountLogic;
import by.suboch.manager.ConfigurationManager;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import static by.suboch.command.CommandConstants.PASSWORD_PARAM;
import static by.suboch.command.CommandConstants.VISITOR_ROLE_ATTR;
import static by.suboch.command.CommandConstants.AUTHORIZATION_NAME_PARAM;

/**
 *
 */
public class LogInCommand implements IServletCommand {
    private static  final Logger LOG = LogManager.getLogger();

    private static final String USER_MAIN_PAGE = "path.page.mainUser";
    private static final String ADMIN_MAIN_PAGE = "path.page.mainAdmin";
    private static final String TEST_PAGE = "path.page.test";

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        String authorizationName = request.getParameter(AUTHORIZATION_NAME_PARAM);
        String password = request.getParameter(PASSWORD_PARAM);
        AccountLogic logic = new AccountLogic();
        try {
            if(logic.authorizeAccount(authorizationName, password)) {
                if(logic.isAdmin(authorizationName)) {
                    request.getSession().setAttribute(VISITOR_ROLE_ATTR, VisitorRole.ADMIN.toString());
                    return ConfigurationManager.getProperty(ADMIN_MAIN_PAGE);
                } else {
                    request.getSession().setAttribute(VISITOR_ROLE_ATTR, VisitorRole.USER.toString());
                    return ConfigurationManager.getProperty(USER_MAIN_PAGE);
                }
            } else {
                //TODO
                return ConfigurationManager.getProperty(TEST_PAGE);
            }
        } catch (LogicException e) {
            return ConfigurationManager.getProperty(TEST_PAGE);
        }
    }
}
