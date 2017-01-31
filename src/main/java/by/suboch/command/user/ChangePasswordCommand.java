package by.suboch.command.user;

import by.suboch.command.IServletCommand;
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
public class ChangePasswordCommand implements IServletCommand {
    private static final String PARAM_OLD_PASSWORD = "oldPassword";
    private static final String PARAM_NEW_PASSWORD = "newPassword";
    private static final String PARAM_NEW_PASSWORD_CONFIRM = "newPasswordConfirm";

    private static final String CHANGE_NAME_ERROR_MESSAGE = "message.error.changeName";

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        Visitor visitor = (Visitor) request.getSession().getAttribute(VISITOR_KEY);
        String oldPassword = request.getParameter(PARAM_OLD_PASSWORD);
        String newPassword = request.getParameter(PARAM_NEW_PASSWORD);
        String newPasswordConfirm = request.getParameter(PARAM_NEW_PASSWORD_CONFIRM);
        Account account = (Account) request.getSession().getAttribute(ATTR_ACCOUNT);
        String nextPage;
        try {
            AccountLogic logic = new AccountLogic();
            if (logic.changePassword(account.getAccountId(), oldPassword, newPassword, newPasswordConfirm)) {
                //TODO: Set message.
            } else {
                //TODO: Set message.
            }
            nextPage = visitor.getCurrentPage();
        } catch (LogicException e) {
            request.getSession().setAttribute(ATTR_MESSAGE, MessageManager.getProperty(CHANGE_NAME_ERROR_MESSAGE, visitor.getLocale()));
            nextPage = ConfigurationManager.getProperty(PAGE_ERROR);
        }

        return nextPage;
    }
}
