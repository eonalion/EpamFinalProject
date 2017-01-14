package by.suboch.command;

import by.suboch.exception.LogicException;
import by.suboch.logic.AccountLogic;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import static by.suboch.command.CommandConstants.*;

/**
 *
 */
public class RegisterCommand implements IServletCommand {

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

            }
        } catch (LogicException e) {
            //TODO: Exception
        }
        //TODO: check registration result

        return "";
    }
}
