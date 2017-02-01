package by.suboch.command.user;

import by.suboch.ajax.AJAXState;
import by.suboch.command.AbstractServletCommand;
import by.suboch.command.CommandConstants;
import by.suboch.command.IServletCommand;
import by.suboch.entity.Account;
import by.suboch.entity.Visitor;
import by.suboch.exception.LogicException;
import by.suboch.logic.AccountLogic;
import by.suboch.manager.ConfigurationManager;
import by.suboch.manager.MessageManager;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import java.io.IOException;

import static by.suboch.command.CommandConstants.*;
import static by.suboch.controller.ControllerConstants.VISITOR_KEY;

/**
 *
 */
public class ChangeAvatarCommand extends AbstractServletCommand {
    private static final Logger LOG = LogManager.getLogger();
    static final String PARAM_AVATAR = "avatar";

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        Visitor visitor = (Visitor) request.getSession().getAttribute(VISITOR_KEY);
        Account account = (Account) request.getSession().getAttribute(ATTR_ACCOUNT);
        AccountLogic logic = new AccountLogic();
        String resultData;

        int fileSize;
        byte[] avatar = null;
        try {
            Part imagePart = request.getPart(PARAM_AVATAR);
            fileSize = (int) imagePart.getSize();
            if (fileSize != 0) {
                avatar = new byte[fileSize];
                imagePart.getInputStream().read(avatar, 0, fileSize);
            }
        } catch (IOException | ServletException e) {
            LOG.log(Level.ERROR, "Errors during uploading avatar.", e);
        }

        try {
            logic.changeAvatar(account.getAccountId(), avatar);
            account.setAvatar(avatar);
            resultData = ConfigurationManager.getProperty(CommandConstants.PAGE_SETTINGS);
        } catch (LogicException e) {
            LOG.log(Level.ERROR, "Errors during changing avatar..", e);
            resultData = handleDBError(e, request, response);
        }

        return resultData;
    }
}
