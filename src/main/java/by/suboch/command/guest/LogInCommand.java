package by.suboch.command.guest;

import by.suboch.ajax.AJAXState;
import by.suboch.ajax.BiTuple;
import by.suboch.command.AbstractServletCommand;
import by.suboch.command.CommandConstants;
import by.suboch.controller.ControllerConfiguration;
import by.suboch.controller.ControllerConstants;
import by.suboch.entity.Account;
import by.suboch.entity.Visitor;
import by.suboch.exception.LogicException;
import by.suboch.logic.AccountLogic;
import by.suboch.manager.ConfigurationManager;
import by.suboch.manager.MessageManager;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 */
public class LogInCommand extends AbstractServletCommand {
    private static final Logger LOG = LogManager.getLogger();

    private static final String PARAM_AUTHORIZATION_NAME = "authorizationName";
    private static final String PARAM_PASSWORD = "password";

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        String emailOrLogin = request.getParameter(PARAM_AUTHORIZATION_NAME);
        String password = request.getParameter(PARAM_PASSWORD);

        /*boolean formValid = !Stream.of(emailOrLogin, password).anyMatch(s -> s == null);
        if (!formValid) {
            LOG.log(Level.WARN, "Sign in form consists null on non-nullable parameters.");
            return suitablePageForm(ConfigurationManager.getProperty(CommandConstants.PAGE_REGISTRATION), request, response);
        }*/

        ControllerConfiguration controllerConfiguration = (ControllerConfiguration) request.getSession().getAttribute(ControllerConstants.CONTROLLER_CONFIG_KEY);
        Visitor visitor = (Visitor) request.getSession().getAttribute(ControllerConstants.VISITOR_KEY);
        AccountLogic accountLogic = new AccountLogic();
        String resultData;
        if (controllerConfiguration.getState() != ControllerConfiguration.State.AJAX) {
            resultData = ConfigurationManager.getProperty(CommandConstants.PAGE_REGISTRATION);
            request.setAttribute(PARAM_AUTHORIZATION_NAME, emailOrLogin);
            request.setAttribute(PARAM_PASSWORD, password);
        } else {
            try {
                BiTuple<AJAXState, Object> data;
                if (accountLogic.authorizeAccount(emailOrLogin, password)) {
                    Account account = accountLogic.loadAccount(emailOrLogin);
                    if (account.getAdminRights()) {
                        visitor.setRole(Visitor.Role.ADMIN);
                    } else {
                        visitor.setRole(Visitor.Role.USER);
                    }
                    request.getSession().setAttribute(CommandConstants.ATTR_ACCOUNT, account);
                    data = new BiTuple<>(AJAXState.LOCATION_REDIRECT, request.getContextPath() + ConfigurationManager.getProperty(CommandConstants.PAGE_USER_MAIN));
                } else {
                    data = new BiTuple<>(AJAXState.HANDLE, MessageManager.getProperty(CommandConstants.MESSAGE_FAILURE_LOGIN, visitor.getLocale()));
                }
                response.setContentType(CommandConstants.MIME_TYPE_JSON);
                resultData = toJson(data);
            } catch (LogicException e) {
                LOG.log(Level.ERROR, "Errors during sign in guest.", e);
                resultData = handleDBError(e, request, response);
            }
        }
        return resultData;
    }
}
