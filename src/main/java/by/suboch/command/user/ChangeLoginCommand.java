package by.suboch.command.user;

import by.suboch.ajax.AJAXState;
import by.suboch.command.AbstractServletCommand;
import by.suboch.command.CommandConstants;
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
public class ChangeLoginCommand extends AbstractServletCommand {

    private static final Logger LOG = LogManager.getLogger();
    private static final String PARAM_LOGIN = "login";

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        Visitor visitor = (Visitor) request.getSession().getAttribute(VISITOR_KEY);
        String login = request.getParameter(PARAM_LOGIN);
        ControllerConfiguration controllerConfiguration = (ControllerConfiguration) request.getSession().getAttribute(ControllerConstants.CONTROLLER_CONFIG_KEY);
        Account account = (Account) request.getSession().getAttribute(ATTR_ACCOUNT);
        String resultData;

        if (controllerConfiguration.getState() != ControllerConfiguration.State.AJAX) {
            resultData = ConfigurationManager.getProperty(CommandConstants.PAGE_SETTINGS);
        } else {
            try {
                response.setContentType(MIME_TYPE_JSON);
                LogicActionResult changeLoginResult;
                AJAXState state = AJAXState.HANDLE;
                if (!login.equals(account.getLogin())) {
                    AccountLogic logic = new AccountLogic();
                    account.setLogin(login);
                    changeLoginResult = logic.changeLogin(account.getAccountId(), login);
                    setResultMessage(changeLoginResult, visitor.getLocale());
                } else {
                    changeLoginResult = new LogicActionResult();
                    changeLoginResult.setState(LogicActionResult.State.FAILURE);
                    changeLoginResult.setMessage(MessageManager.getProperty(MESSAGE_FAILURE_NOT_NEW_LOGIN, visitor.getLocale()));
                }
                resultData = toJson(state, changeLoginResult);
            } catch (LogicException e) {
                LOG.log(Level.ERROR, "Errors during chamge imail.", e);
                resultData = handleDBError(e, request, response);
            }
        }
        return resultData;
    }
    private void setResultMessage(LogicActionResult registrationResult, Locale locale) {
        switch (registrationResult.getResult()) {
            case FAILURE_INVALID_USERNAME:
                registrationResult.setMessage(MessageManager.getProperty(CommandConstants.MESSAGE_FAILURE_INVALID_LOGIN, locale));
                break;
            case FAILURE_USERNAME_NOT_UNIQUE:
                registrationResult.setMessage(MessageManager.getProperty(CommandConstants.MESSAGE_FAILURE_LOGIN_NOT_UNIQUE, locale));
                break;
            case SUCCESS_CHANGE_USERNAME:
                registrationResult.setMessage(MessageManager.getProperty(CommandConstants.MESSAGE_SUCCESS_SAVE_CHANGES, locale));
                break;
            default:
        }
    }
}
