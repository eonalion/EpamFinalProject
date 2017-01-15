package by.suboch.command;

import by.suboch.exception.LogicException;
import by.suboch.logic.AccountLogic;
import by.suboch.manager.ConfigurationManager;
import by.suboch.manager.MessageManager;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import static by.suboch.command.CommandConstants.BONUS_DISCOUNT_PARAM;
import static by.suboch.command.CommandConstants.BONUS_PRICE_PARAM;
import static by.suboch.command.CommandConstants.MESSAGE_ATTR;

/**
 *
 */
public class CreateBonusCommand implements IServletCommand {
    private static final String ADD_NEW_PAGE = "path.page.addNew";
    private static final String ERROR_PAGE = "path.page.error";
    private static final String ERROR_MESSAGE = "message.bonus.error";

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
       String bonusPrice = request.getParameter(BONUS_PRICE_PARAM);
        String discount = request.getParameter(BONUS_DISCOUNT_PARAM);

        AccountLogic logic = new AccountLogic();
        try {
            if(logic.createBonus(bonusPrice, discount)) {
                // TODO: Set success message.
                return ConfigurationManager.getProperty(ADD_NEW_PAGE);
            } else {
                // TODO: Set warn message(or it's already set?).
                return ConfigurationManager.getProperty(ADD_NEW_PAGE);
            }
        } catch (LogicException e) {
            //TODO: Handle exception;
            request.getSession().setAttribute(MESSAGE_ATTR, MessageManager.getProperty(ERROR_MESSAGE));
            return ConfigurationManager.getProperty(ERROR_PAGE);
        }
    }
}
