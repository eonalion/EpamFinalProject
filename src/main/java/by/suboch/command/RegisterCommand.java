package by.suboch.command;

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
public class RegisterCommand implements IServletCommand {

    private static final String PARAM_FIRST_NAME = "firstName";
    private static final String PARAM_LAST_NAME = "lastName";
    private static final String PARAM_EMAIL = "email";
    private static final String PARAM_LOGIN = "login";
    private static final String PARAM_PASSWORD = "password";
    private static final String PARAM_PASSWORD_CONFIRM = "passwordConfirm";

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        Visitor visitor = (Visitor) request.getSession().getAttribute(VISITOR_KEY);
        String firstName = request.getParameter(PARAM_FIRST_NAME);
        String lastName = request.getParameter(PARAM_LAST_NAME);
        String email = request.getParameter(PARAM_EMAIL);
        String login = request.getParameter(PARAM_LOGIN);
        String password = request.getParameter(PARAM_PASSWORD);
        String passwordConfirm = request.getParameter(PARAM_PASSWORD_CONFIRM);

        AccountLogic logic = new AccountLogic();

        try {
            if(logic.registerAccount(firstName, lastName, login, email, password, passwordConfirm)) {
                request.setAttribute(ATTR_MESSAGE, MessageManager.getProperty(MESSAGE_REGISTRATION_OK, visitor.getLocale()));

                return ConfigurationManager.getProperty(PAGE_REGISTRATION);
                //TODO: DAO methods. Register. Return current page with positive message.
            } else {
                //TODO: Set warn message. Return current page with warn message.
            }
        } catch (LogicException e) {
            //TODO: Handle exception. Set error message. Return error page.
        }

        return "";
    }
}
