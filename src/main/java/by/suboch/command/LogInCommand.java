package by.suboch.command;

import by.suboch.entity.Account;
import by.suboch.exception.LogicException;
import by.suboch.logic.AccountLogic;
import by.suboch.manager.ConfigurationManager;
import by.suboch.manager.MessageManager;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import static by.suboch.command.CommandConstants.*;

/**
 *
 */
public class LogInCommand implements IServletCommand {
    private static  final Logger LOG = LogManager.getLogger();

    private static final String USER_MAIN_PAGE = "path.page.mainUser";
    private static final String ADMIN_MAIN_PAGE = "path.page.mainAdmin";
    private static final String REGISTRATION_PAGE = "path.page.registration";
    private static final String ERROR_PAGE = "path.page.error";
    private static final String LOGIN_ERROR_MESSAGE = "message.error.loginError";

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        String authorizationName = request.getParameter(AUTHORIZATION_NAME_PARAM);
        String password = request.getParameter(PASSWORD_PARAM);
        AccountLogic logic = new AccountLogic();
        String nextPage;
        try {
            if(logic.authorizeAccount(authorizationName, password)) {
                Account account = logic.loadAccount(authorizationName);
                request.getSession().setAttribute(ACCOUNT_ATTR, account);
                if(logic.isAdmin(authorizationName)) {
                    request.getSession().setAttribute(VISITOR_ROLE_ATTR, VisitorRole.ADMIN.toString());
                    nextPage =  ConfigurationManager.getProperty(ADMIN_MAIN_PAGE);
                } else {
                    request.getSession().setAttribute(VISITOR_ROLE_ATTR, VisitorRole.USER.toString());
                    nextPage = ConfigurationManager.getProperty(USER_MAIN_PAGE);
                }
            } else {
                //TODO: Set warn message through validator or what? It could be already set in validator, so just return current page.
                return ConfigurationManager.getProperty(REGISTRATION_PAGE);
            }
        } catch (LogicException e) {
            request.getSession().setAttribute(MESSAGE_ATTR, MessageManager.getProperty(LOGIN_ERROR_MESSAGE));
            nextPage = ConfigurationManager.getProperty(ERROR_PAGE);
        }
        return nextPage;
    }
}
