package by.suboch.command.user;

import by.suboch.ajax.AJAXState;
import by.suboch.command.AbstractServletCommand;
import by.suboch.command.CommandConstants;
import by.suboch.command.IServletCommand;
import by.suboch.controller.ControllerConfiguration;
import by.suboch.controller.ControllerConstants;
import by.suboch.entity.Account;
import by.suboch.entity.Visitor;
import by.suboch.exception.LogicException;
import by.suboch.logic.AccountLogic;
import by.suboch.logic.LogicActionResult;
import by.suboch.manager.ConfigurationManager;
import by.suboch.manager.MessageManager;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.util.Locale;

import static by.suboch.command.CommandConstants.*;
import static by.suboch.controller.ControllerConstants.VISITOR_KEY;

/**
 *
 */
public class ChangePasswordCommand extends AbstractServletCommand {
    private static final Logger LOG = LogManager.getLogger();
    private static final String PARAM_OLD_PASSWORD = "oldPassword";
    private static final String PARAM_NEW_PASSWORD = "newPassword";
    private static final String PARAM_NEW_PASSWORD_CONFIRM = "newPasswordConfirm";

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        Visitor visitor = (Visitor) request.getSession().getAttribute(VISITOR_KEY);
        String oldPassword = request.getParameter(PARAM_OLD_PASSWORD);
        String newPassword = request.getParameter(PARAM_NEW_PASSWORD);
        String newPasswordConfirm = request.getParameter(PARAM_NEW_PASSWORD_CONFIRM);
        ControllerConfiguration controllerConfiguration = (ControllerConfiguration) request.getSession().getAttribute(ControllerConstants.CONTROLLER_CONFIG_KEY);
        Account account = (Account) request.getSession().getAttribute(ATTR_ACCOUNT);
        String resultData;
        if (controllerConfiguration.getState() != ControllerConfiguration.State.AJAX) {
            resultData = ConfigurationManager.getProperty(CommandConstants.PAGE_SETTINGS);
        } else {
            try {
                response.setContentType(MIME_TYPE_JSON);
                AJAXState state = AJAXState.HANDLE;
                AccountLogic logic = new AccountLogic();
                LogicActionResult changePasswordResult = logic.changePassword(account.getAccountId(), oldPassword, newPassword, newPasswordConfirm);
                setResultMessage(changePasswordResult, visitor.getLocale());
                resultData = toJson(state, changePasswordResult);
            } catch (LogicException e) {
                LOG.log(Level.ERROR, "Errors during change password.", e);
                resultData = handleDBError(e, request, response);
            }
        }
        return resultData;
    }

    private void setResultMessage(LogicActionResult registrationResult, Locale locale) {
        switch (registrationResult.getResult()) {
            case FAILURE_INVALID_PASSWORD:
                registrationResult.setMessage(MessageManager.getProperty(CommandConstants.MESSAGE_FAILURE_INVALID_PASSWORD, locale));
                registrationResult.setTarget(PARAM_OLD_PASSWORD);
                break;
            case FAILURE_NOT_OLD_PASSWORD:
                registrationResult.setMessage(MessageManager.getProperty(CommandConstants.MESSAGE_FAILURE_NOT_OLD_PASSWORD, locale));
                registrationResult.setTarget(PARAM_OLD_PASSWORD);
                break;
            case FAILURE_INVALID_NEW_PASSWORD:
                registrationResult.setMessage(MessageManager.getProperty(CommandConstants.MESSAGE_FAILURE_INVALID_NEW_PASSWORD, locale));
                registrationResult.setTarget(PARAM_NEW_PASSWORD);
                break;
            case FAILURE_NOT_NEW_UNIQUE_PASSWORD:
                registrationResult.setMessage(MessageManager.getProperty(CommandConstants.MESSAGE_FAILURE_NOT_NEW_UNIQUE_PASSWORD, locale));
                registrationResult.setTarget(PARAM_NEW_PASSWORD);
                break;
            case FAILURE_PASSWORDS_NOT_EQUALS:
                registrationResult.setMessage(MessageManager.getProperty(CommandConstants.MESSAGE_FAILURE_PASSWORDS_NOT_EQUALS, locale));
                registrationResult.setTarget(PARAM_NEW_PASSWORD_CONFIRM);
                break;
            case SUCCESS_CHANGE_PASSWORD:
                registrationResult.setMessage(MessageManager.getProperty(CommandConstants.MESSAGE_SUCCESS_SAVE_CHANGES, locale));
                break;
            default:
        }
    }
}
