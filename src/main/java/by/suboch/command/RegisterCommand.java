package by.suboch.command;

import by.suboch.exception.LogicException;
import by.suboch.logic.AccountLogic;
import by.suboch.manager.ConfigurationManager;
import by.suboch.manager.MessageManager;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import static by.suboch.command.CommandConstants.*;

/**
 *
 */
public class RegisterCommand implements IServletCommand {
    private static final String REGISTRATION_OK = "message.registration.congratulation";
    private static final String REGISTRATION_PAGE = "path.page.registration";

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        String firstName = request.getParameter(FIRST_NAME_PARAM);
        String lastName = request.getParameter(LAST_NAME_PARAM);
        String email = request.getParameter(EMAIL_PARAM);
        String login = request.getParameter(LOGIN_PARAM);
        String password = request.getParameter(PASSWORD_PARAM);
        String passwordConfirm = request.getParameter(PASSWORD_CONFIRM_PARAM);

        AccountLogic logic = new AccountLogic();
        try {
            if(logic.registerAccount(firstName, lastName, login, email, password, passwordConfirm)) {
                request.setAttribute(MESSAGE_ATTR, MessageManager.getProperty(REGISTRATION_OK));
                return ConfigurationManager.getProperty(REGISTRATION_PAGE);
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
