package by.suboch.command;

import by.suboch.entity.Account;
import by.suboch.exception.LogicException;
import by.suboch.logic.AccountLogic;
import by.suboch.manager.ConfigurationManager;
import by.suboch.manager.MessageManager;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import java.io.IOException;

import static by.suboch.command.CommandConstants.ACCOUNT_ATTR;
import static by.suboch.command.CommandConstants.AVATAR_PARAM;
import static by.suboch.command.CommandConstants.MESSAGE_ATTR;

/**
 *
 */
public class ChangeAvatarCommand implements IServletCommand {
    private static final String SETTINGS_PAGE = "path.page.settings";
    private static final String ERROR_PAGE = "path.page.error";
    private static final String CHANGE_AVATAR_ERROR_MESSAGE = "message.error.changeAvatar";

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        int fileSize;
        byte [] avatar = null;
        String nextPage = null;
        Account account = (Account) request.getSession().getAttribute(ACCOUNT_ATTR);
        AccountLogic logic = new AccountLogic();

        try {
            Part imagePart = request.getPart(AVATAR_PARAM);
            fileSize = (int)imagePart.getSize();
            if (fileSize != 0) {
                avatar = new byte[fileSize];
                imagePart.getInputStream().read(avatar, 0, fileSize);//TODO check returned value.
            }
        } catch (IOException | ServletException e) {
            //TODO:Handle exception.
        }

        try {
            if(logic.changeAvatar(account.getAccountId(), avatar)) {
                account.setAvatar(avatar);
                request.getSession().setAttribute(ACCOUNT_ATTR, account);
                nextPage = ConfigurationManager.getProperty(SETTINGS_PAGE);
            } else {
                //TODO: Set message;
            }
        } catch (LogicException e) {
            //TODO: Handle exception;
            request.getSession().setAttribute(MESSAGE_ATTR, MessageManager.getProperty(CHANGE_AVATAR_ERROR_MESSAGE));
            nextPage = ConfigurationManager.getProperty(ERROR_PAGE);
        }

        return nextPage;
    }
}
