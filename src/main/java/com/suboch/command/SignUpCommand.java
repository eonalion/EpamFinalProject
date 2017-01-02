package com.suboch.command;

import com.suboch.logic.AccountLogic;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 */
public class SignUpCommand implements IServletCommand {
    private static final String FIRST_NAME_PARAM = "firstName";
    private static final String LAST_NAME_PARAM = "lastName";
    private static final String EMAIL_PARAM = "email";
    private static final String LOGIN_PARAM = "login";
    private static final String PASSWORD_PARAM = "password";
    private static final String PASSWORD_CONFIRM_PARAM = "passwordConfirm";

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) {
        String firstName = request.getParameter(FIRST_NAME_PARAM);
        String lastName = request.getParameter(LAST_NAME_PARAM);
        String email = request.getParameter(EMAIL_PARAM);
        String login = request.getParameter(LOGIN_PARAM);
        String password = request.getParameter(PASSWORD_PARAM);
        String passwordConfirm = request.getParameter(PASSWORD_CONFIRM_PARAM);

        AccountLogic logic = new AccountLogic();
        logic.registerAccount(firstName, lastName, login, email, password, passwordConfirm);
        //TODO: check registration result
    }
}