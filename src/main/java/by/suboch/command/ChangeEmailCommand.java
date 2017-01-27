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
public class ChangeEmailCommand implements IServletCommand {

    private static final String PARAM_EMAIL = "email";
    private static final String MESSAGE_CHANGE_EMAIL_ERROR = "message.error.changeEmail";

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        Visitor visitor = (Visitor) request.getSession().getAttribute(VISITOR_KEY);
        String email = request.getParameter(PARAM_EMAIL);
        Account account = (Account) request.getSession().getAttribute(ATTR_ACCOUNT);
        String nextPage;
        try {
            if (!email.equals(account.getEmail())) {
                AccountLogic logic = new AccountLogic();
                if (logic.changeEmail(account.getAccountId(), email)) {
                    account.setEmail(email);
                    //TODO: Set message.
                } else {
                    //TODO: Set message.
                }
            } else {
                //TODO: Set message.
            }
            nextPage = visitor.getCurrentPage();
        } catch (LogicException e) {
            request.getSession().setAttribute(ATTR_MESSAGE, MessageManager.getProperty(MESSAGE_CHANGE_EMAIL_ERROR, visitor.getLocale()));
            nextPage = ConfigurationManager.getProperty(PAGE_ERROR);
        }

        return nextPage;
    }
}
