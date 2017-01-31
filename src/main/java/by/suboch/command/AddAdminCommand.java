package by.suboch.command;

import by.suboch.ajax.AJAXState;
import by.suboch.ajax.BiTuple;
import by.suboch.controller.ControllerConfig;
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
import java.util.stream.Stream;

/**
 *
 */
public class AddAdminCommand extends AbstractServletCommand {
    private static final Logger LOG = LogManager.getLogger();

    private static final String EMAIL_OR_LOGIN_PARAM = "emailOrLogin";
    private static final String PASSWORD_PARAM = "signInPassword";

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        String emailOrLogin = request.getParameter(EMAIL_OR_LOGIN_PARAM);
        String password = request.getParameter(PASSWORD_PARAM);
        boolean formValid = !Stream.of(emailOrLogin, password).anyMatch(s -> s == null);
        ControllerConfig controllerConfig = (ControllerConfig) request.getSession().getAttribute(ControllerConstants.CONTROLLER_CONFIG_KEY);
        if (!formValid) {
            LOG.log(Level.WARN, "Sign in form consists null on non-nullable parameters.");
            return suitablePageForm(ConfigurationManager.getProperty(CommandConstants.PAGE_REGISTRATION), request, response);
        }
        String resultData;
        AccountLogic accountLogic = new AccountLogic();
        Visitor visitor = (Visitor)request.getSession().getAttribute(ControllerConstants.VISITOR_KEY);
        if (controllerConfig.getState() != ControllerConfig.State.AJAX) {
            resultData = ConfigurationManager.getProperty(CommandConstants.PAGE_REGISTRATION);
            request.setAttribute(EMAIL_OR_LOGIN_PARAM, emailOrLogin);
            request.setAttribute(PASSWORD_PARAM, password);
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
                    data = new BiTuple<>(AJAXState.HANDLE, MessageManager.getProperty(CommandConstants.MESSAGE_SIGN_IN_INVALID, visitor.getLocale()));
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
