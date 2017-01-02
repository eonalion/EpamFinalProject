package com.suboch.command;

import com.suboch.logic.AccountLogic;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 */
public class SignInCommand implements IServletCommand {
    private static final String AUTHORIZATION_NAME_PARAM = "authorizationName";
    private static final String PASSWORD_PARAM = "password";

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) {
        String authorizationName = request.getParameter(AUTHORIZATION_NAME_PARAM);
        String password = request.getParameter(PASSWORD_PARAM);
        AccountLogic logic = new AccountLogic();
        logic.authorizeAccount(authorizationName, password);
    }
}
