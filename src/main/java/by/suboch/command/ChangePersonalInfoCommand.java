package by.suboch.command;

import by.suboch.entity.Account;
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
public class ChangePersonalInfoCommand implements IServletCommand {
    private static final String SETTINGS_PAGE = "path.page.settings";
    private static final String ERROR_PAGE = "path.page.error";
    private static final String CHANGE_NAME_ERROR_MESSAGE = "message.error.changePersonalInfo";


    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        String firstName = request.getParameter(FIRST_NAME_PARAM);
        String lastName = request.getParameter(LAST_NAME_PARAM);

        AccountLogic accountLogic = new AccountLogic();
        Account account = (Account) request.getSession().getAttribute(ACCOUNT_ATTR);

        String nextPage;
        try {
            nextPage = ConfigurationManager.getProperty(SETTINGS_PAGE);
            if (accountLogic.changeName(account.getAccountId(), firstName, lastName)) {
                account.setFirstName(firstName);
                account.setLastName(lastName);
                request.getSession().setAttribute(ACCOUNT_ATTR, account);
            }
        } catch (LogicException e) {
            request.getSession().setAttribute(MESSAGE_ATTR, MessageManager.getProperty(CHANGE_NAME_ERROR_MESSAGE));
            nextPage = ConfigurationManager.getProperty(ERROR_PAGE);
        }
        return nextPage;
    }
}
