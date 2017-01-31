package by.suboch.command.admin;

import by.suboch.command.IServletCommand;
import by.suboch.dao.BonusDAO;
import by.suboch.entity.Visitor;
import by.suboch.exception.LogicException;
import by.suboch.logic.AccountLogic;
import by.suboch.logic.BonusLogic;
import by.suboch.manager.ConfigurationManager;
import by.suboch.manager.MessageManager;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import static by.suboch.command.CommandConstants.*;
import static by.suboch.controller.ControllerConstants.VISITOR_KEY;

/**
 *
 */
public class AddBonusCommand implements IServletCommand {
    private static final String PARAM_BONUS_PRICE = "bonusPrice";
    private static final String PARAM_BONUS_DISCOUNT = "bonusDiscount";
    private static final String MESSAGE_ERROR_ADD_BONUS = "message.bonus.error";

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        Visitor visitor = (Visitor) request.getSession().getAttribute(VISITOR_KEY);

        String bonusPrice = request.getParameter(PARAM_BONUS_PRICE);
        String discount = request.getParameter(PARAM_BONUS_DISCOUNT);

        BonusLogic logic = new BonusLogic();

        String nextPage;
        try {
            if (logic.createBonus(bonusPrice, discount)) {
                // TODO: Set success message.
                return ConfigurationManager.getProperty(PAGE_ADD_NEW);
            } else {
                // TODO: Set warn message(or it's already set?).
                nextPage = visitor.getCurrentPage();
            }
        } catch (LogicException e) {
            //TODO: Handle exception;
            request.getSession().setAttribute(ATTR_MESSAGE, MessageManager.getProperty(MESSAGE_ERROR_ADD_BONUS, visitor.getLocale()));
            nextPage = ConfigurationManager.getProperty(PAGE_ERROR);
        }
        return nextPage;
    }
}
