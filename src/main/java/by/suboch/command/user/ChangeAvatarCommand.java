package by.suboch.command.user;

import by.suboch.command.IServletCommand;
import by.suboch.entity.Account;
import by.suboch.entity.Visitor;
import by.suboch.exception.LogicException;
import by.suboch.logic.AccountLogic;
import by.suboch.manager.ConfigurationManager;
import by.suboch.manager.MessageManager;

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
public class ChangeAvatarCommand implements IServletCommand {
    private static final String MESSAGE_ERROR_CHANGE_AVATAR = "message.failure.changeAvatar";
    static final String PARAM_AVATAR = "avatar";

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        Visitor visitor = (Visitor) request.getSession().getAttribute(VISITOR_KEY);
        Account account = (Account) request.getSession().getAttribute(ATTR_ACCOUNT);
        AccountLogic logic = new AccountLogic();
        String nextPage;

        int fileSize;
        byte [] avatar = null;
        try {
            Part imagePart = request.getPart(PARAM_AVATAR);
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
            } else {
                //TODO: Set message;
            }
            nextPage = visitor.getCurrentPage();
        } catch (LogicException e) {
            //TODO: Handle exception;
            request.getSession().setAttribute(ATTR_MESSAGE, MessageManager.getProperty(MESSAGE_ERROR_CHANGE_AVATAR, visitor.getLocale()));
            nextPage = ConfigurationManager.getProperty(PAGE_ERROR);
        }

        return nextPage;
    }
}
