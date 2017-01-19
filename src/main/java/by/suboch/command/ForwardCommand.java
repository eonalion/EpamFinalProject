package by.suboch.command;

import by.suboch.entity.Account;
import by.suboch.manager.ConfigurationManager;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import static by.suboch.command.CommandConstants.ACCOUNT_ATTR;
import static by.suboch.command.CommandConstants.FORWARD_ACTION_PARAM;

/**
 *
 */
public class ForwardCommand implements IServletCommand {
    private static final String ADMIN_MAIN_PAGE = "path.page.mainAdmin";
    private static final String USER_MAIN_PAGE = "path.page.mainUser";
    private static final String REGISTER_PAGE = "path.page.registration";
    private static final String SETTINGS_PAGE = "path.page.settings";
    private static final String ADD_NEW_PAGE = "path.page.addNew";

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        String forwardAction = request.getParameter(FORWARD_ACTION_PARAM);
        String nextPage = null;
        switch (forwardAction) {
            case "index":
                nextPage = ConfigurationManager.getProperty(REGISTER_PAGE);
                break;
            case "main":
                Account currentAccount = (Account) request.getSession().getAttribute(ACCOUNT_ATTR);
                // FIXME: Is it necessary to check for null?
                if (currentAccount.getAdminRights()) {
                    nextPage = ConfigurationManager.getProperty(ADMIN_MAIN_PAGE);
                } else if (!currentAccount.getAdminRights()) {
                    nextPage = ConfigurationManager.getProperty(USER_MAIN_PAGE);
                }
                break;
            case "add_new":
                nextPage = ConfigurationManager.getProperty(ADD_NEW_PAGE);
                break;
            case "settings":
                nextPage = ConfigurationManager.getProperty(SETTINGS_PAGE);
                break;
            case "default":
                //TODO: Error. Can't find page.
        }
        return nextPage;
    }
}
