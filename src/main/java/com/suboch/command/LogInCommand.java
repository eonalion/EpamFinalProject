package com.suboch.command;

import com.suboch.exception.LogicException;
import com.suboch.logic.AccountLogic;
import com.suboch.manager.ConfigurationManager;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 */
public class LogInCommand implements IServletCommand {
    private static  final Logger LOG = LogManager.getLogger();
    private static final String AUTHORIZATION_NAME_PARAM = "authorizationName";
    private static final String PASSWORD_PARAM = "password";

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
                    return ConfigurationManager.getProperty(ADMIN_MAIN_PAGE);
                } else {
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
