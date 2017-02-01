package by.suboch.command.user;

import by.suboch.ajax.AJAXState;
import by.suboch.command.AbstractServletCommand;
import by.suboch.command.CommandConstants;
import by.suboch.controller.ControllerConfiguration;
import by.suboch.controller.ControllerConstants;
import by.suboch.entity.Account;
import by.suboch.entity.Track;
import by.suboch.entity.Visitor;
import by.suboch.exception.LogicException;
import by.suboch.logic.LogicActionResult;
import by.suboch.logic.PurchaseLogic;
import by.suboch.manager.ConfigurationManager;
import by.suboch.manager.MessageManager;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.Locale;
import java.util.stream.Collectors;

/**
 *
 */
public class MakePurchaseCommand extends AbstractServletCommand {
    private static final Logger LOG = LogManager.getLogger();

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws IOException {
        List<Track> cartItems = (List<Track>) request.getSession().getAttribute(CommandConstants.ATTR_CART_ITEMS);
        ControllerConfiguration controllerConfiguration = (ControllerConfiguration) request.getSession().getAttribute(ControllerConstants.CONTROLLER_CONFIG_KEY);
        Visitor visitor = (Visitor) request.getSession().getAttribute(ControllerConstants.VISITOR_KEY);
        Account account = (Account) request.getSession().getAttribute(CommandConstants.ATTR_ACCOUNT);
        PurchaseLogic purchaseLogic = new PurchaseLogic();
        String resultData;

        if (controllerConfiguration.getState() != ControllerConfiguration.State.AJAX) {
            resultData = ConfigurationManager.getProperty(CommandConstants.PAGE_CART);
        } else {
            try {
                List<Integer> tracksId = cartItems.stream().map(Track::getTrackId).collect(Collectors.toList());
                LogicActionResult result = purchaseLogic.addPurchase(account.getAccountId(), tracksId);
                response.setContentType(CommandConstants.MIME_TYPE_JSON);
                setResultMessage(result, visitor.getLocale());
                if (result.getState() == LogicActionResult.State.SUCCESS) {
                    cartItems.clear();
                    resultData = toJson(AJAXState.HANDLE, result);
                } else {
                    resultData = toJson(AJAXState.HANDLE, result);
                }
            } catch (LogicException e) {
                LOG.log(Level.ERROR, "Errors while adding new purchase.", e);
                resultData = handleDBError(e, request, response);
            }
        }

        return resultData;
    }

    private void setResultMessage(LogicActionResult makePurchaseResult, Locale locale) {
        switch (makePurchaseResult.getResult()) {
            case SUCCESS_PURCHASE:
                makePurchaseResult.setMessage(MessageManager.getProperty(CommandConstants.MESSAGE_SUCCESS_MAKE_PURCHASE, locale));
                break;
            case FAILURE_EMPTY_CART:
                makePurchaseResult.setMessage(MessageManager.getProperty(CommandConstants.MESSAGE_FAILURE_EMPTY_SHOPPING_CART, locale));
                break;
        }
    }
}
