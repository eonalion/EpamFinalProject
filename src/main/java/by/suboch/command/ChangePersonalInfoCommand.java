package by.suboch.command;

import by.suboch.entity.Account;
import by.suboch.entity.Visitor;
import by.suboch.exception.LogicException;
import by.suboch.logic.AccountLogic;
import by.suboch.manager.ConfigurationManager;
import by.suboch.manager.MessageManager;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import static by.suboch.command.CommandConstants.*;
import static by.suboch.controller.ControllerConstants.VISITOR_KEY;

/**
 *
 */
public class ChangePersonalInfoCommand implements IServletCommand {
    private static final String FIRST_NAME_PARAM = "firstName";
    private static final String LAST_NAME_PARAM = "lastName";
    private static final String CHANGE_NAME_ERROR_MESSAGE = "message.error.changePersonalInfo";

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        Visitor visitor = (Visitor) request.getSession().getAttribute(VISITOR_KEY);
        String firstName = request.getParameter(FIRST_NAME_PARAM);
        String lastName = request.getParameter(LAST_NAME_PARAM);

        AccountLogic accountLogic = new AccountLogic();
        Account account = (Account) request.getSession().getAttribute(ATTR_ACCOUNT);

        String nextPage;
        try {
            if (accountLogic.changeName(account.getAccountId(), firstName, lastName)) {
                account.setFirstName(firstName);
                account.setLastName(lastName);
                request.getSession().setAttribute(ATTR_ACCOUNT, account);
            }
            nextPage = visitor.getCurrentPage();
        } catch (LogicException e) {
            request.getSession().setAttribute(ATTR_MESSAGE, MessageManager.getProperty(CHANGE_NAME_ERROR_MESSAGE, visitor.getLocale()));
            nextPage = ConfigurationManager.getProperty(PAGE_ERROR);
        }
        return nextPage;
    }
}
