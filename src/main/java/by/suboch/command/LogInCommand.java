package by.suboch.command;

import by.suboch.entity.Account;
import by.suboch.entity.Visitor;
import by.suboch.exception.LogicException;
import by.suboch.logic.AccountLogic;
import by.suboch.logic.TrackLogic;
import by.suboch.manager.ConfigurationManager;
import by.suboch.manager.MessageManager;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import static by.suboch.command.CommandConstants.*;
import static by.suboch.controller.ControllerConstants.VISITOR_KEY;

/**
 *
 */
public class LogInCommand implements IServletCommand {

    private static final String PARAM_AUTHORIZATION_NAME = "authorizationName";
    private static final String PARAM_PASSWORD = "password";
    private static final String ERROR_MESSAGE_LOGIN = "message.error.loginError";

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        String authorizationName = request.getParameter(PARAM_AUTHORIZATION_NAME);
        String password = request.getParameter(PARAM_PASSWORD);

        Visitor visitor = (Visitor) request.getSession().getAttribute(VISITOR_KEY);
        AccountLogic logic = new AccountLogic();
        String nextPage;
        try {
            if(logic.authorizeAccount(authorizationName, password)) {
                Account account = logic.loadAccount(authorizationName);
                request.getSession().setAttribute(ATTR_ACCOUNT, account);

                nextPage = ConfigurationManager.getProperty(PAGE_USER_MAIN);

                if(logic.isAdmin(authorizationName)) {
                    visitor.setRole(Visitor.Role.ADMIN);
                } else {
                    visitor.setRole(Visitor.Role.USER);
                }
                //------------------
                request.getSession().setAttribute(CommandConstants.ATTR_PAGE_AMOUNT, CommandConstants.PAGES_AMOUNT);
                TrackLogic trackLogic = new TrackLogic();
                request.getSession().setAttribute(ATTR_TRACK_LIST, trackLogic.showPopularTracks(0, CommandConstants.TRACKS_PER_PAGE));
                //------------------

            } else {
                //TODO: Set warn message through validator or what? It could be already set in validator, so just return current page.
                nextPage = ConfigurationManager.getProperty(PAGE_REGISTRATION);
            }
        } catch (LogicException e) {
            request.getSession().setAttribute(ATTR_MESSAGE, MessageManager.getProperty(ERROR_MESSAGE_LOGIN, visitor.getLocale()));
            nextPage = ConfigurationManager.getProperty(PAGE_ERROR);
        }
        return nextPage;
    }
}
