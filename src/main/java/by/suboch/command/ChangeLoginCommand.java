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
public class ChangeLoginCommand implements IServletCommand {
    private static final String SETTINGS_PAGE = "path.page.settings";
    private static final String ERROR_PAGE = "path.page.error";

    private static final String CHANGE_NAME_ERROR_MESSAGE = "message.error.changeName";

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        String login = request.getParameter(LOGIN_PARAM);
        Account account = (Account) request.getSession().getAttribute(ACCOUNT_ATTR);
        String nextPage;
        try {
            nextPage = ConfigurationManager.getProperty(SETTINGS_PAGE);
            if (!login.equals(account.getLogin())) {
                AccountLogic logic = new AccountLogic();
                if (logic.changeLogin(account.getAccountId(), login)) {
                    account.setLogin(login);
                    request.getSession().setAttribute(ACCOUNT_ATTR, account);
                    //TODO: Set message.
                } else {
                    //TODO: Set message.
                }
            } else {
                //TODO: Set message.
            }
        } catch (LogicException e) {
            request.getSession().setAttribute(MESSAGE_ATTR, MessageManager.getProperty(CHANGE_NAME_ERROR_MESSAGE));
            nextPage = ConfigurationManager.getProperty(ERROR_PAGE);
        }

        return nextPage;
    }
}
