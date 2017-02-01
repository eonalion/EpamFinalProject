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
public class ChangeEmailCommand extends AbstractServletCommand {

    private static final Logger LOG = LogManager.getLogger();
    private static final String PARAM_EMAIL = "email";

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        Visitor visitor = (Visitor) request.getSession().getAttribute(VISITOR_KEY);
        String email = request.getParameter(PARAM_EMAIL);
            ControllerConfiguration controllerConfiguration = (ControllerConfiguration) request.getSession().getAttribute(ControllerConstants.CONTROLLER_CONFIG_KEY);
            Account account = (Account) request.getSession().getAttribute(ATTR_ACCOUNT);
        String resultData;
        if (controllerConfiguration.getState() != ControllerConfiguration.State.AJAX) {
            resultData = ConfigurationManager.getProperty(CommandConstants.PAGE_SETTINGS);
        } else {
            try {
                response.setContentType(MIME_TYPE_JSON);
                LogicActionResult changeEmailResult;
                AJAXState state = AJAXState.HANDLE;
                if (!email.equals(account.getEmail())) {
                    AccountLogic logic = new AccountLogic();
                    changeEmailResult = logic.changeEmail(account.getAccountId(), email);
                    account.setEmail(email);
                    setResultMessage(changeEmailResult, visitor.getLocale());
                } else {
                    changeEmailResult = new LogicActionResult();
                    changeEmailResult.setState(LogicActionResult.State.FAILURE);
                    changeEmailResult.setMessage(MessageManager.getProperty(MESSAGE_FAILURE_NOT_NEW_EMAIL, visitor.getLocale()));
                }
                resultData = toJson(state, changeEmailResult);
            } catch (LogicException e) {
                LOG.log(Level.ERROR, "Errors during chamge imail.", e);
                resultData = handleDBError(e, request, response);
            }
        }

        return resultData;
    }

    private void setResultMessage(LogicActionResult registrationResult, Locale locale) {
        switch (registrationResult.getResult()) {
            case FAILURE_INVALID_EMAIL:
                registrationResult.setMessage(MessageManager.getProperty(CommandConstants.MESSAGE_FAILURE_INVALID_EMAIL, locale));
                break;
            case FAILURE_EMAIL_NOT_UNIQUE:
                registrationResult.setMessage(MessageManager.getProperty(CommandConstants.MESSAGE_FAILURE_EMAIL_NOT_UNIQUE, locale));
                break;
            case SUCCESS_CHANGE_EMAIL:
                registrationResult.setMessage(MessageManager.getProperty(CommandConstants.MESSAGE_SUCCESS_SAVE_CHANGES, locale));
                break;
            default:
        }
    }
}
