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
public class LogOutCommand implements IServletCommand {
    private static  final Logger LOG = LogManager.getLogger();

    private static final String REGISTRATION_LOGIN_PAGE = "path.page.registration";

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        //TODO: remove attributes from session

        return ConfigurationManager.getProperty(REGISTRATION_LOGIN_PAGE);
    }
}