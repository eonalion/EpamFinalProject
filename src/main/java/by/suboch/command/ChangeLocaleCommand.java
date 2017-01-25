package by.suboch.command;

import by.suboch.entity.Visitor;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Locale;

import static by.suboch.command.CommandConstants.ATTR_LOCALE;
import static by.suboch.controller.ControllerConstants.VISITOR_KEY;

/**
 *
 */
public class ChangeLocaleCommand implements IServletCommand {

    private static final Logger LOG = LogManager.getLogger();

    private static final String LOCALE_DELIMITER = "_";
    private static final int INDEX_LANGUAGE = 0;
    private static final int INDEX_COUNTRY = 1;

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        Visitor visitor = (Visitor) request.getSession().getAttribute(VISITOR_KEY);

        String[] chosenLocaleInfo = request.getParameter(ATTR_LOCALE).split(LOCALE_DELIMITER);
        Locale chosenLocale = new Locale(chosenLocaleInfo[INDEX_LANGUAGE], chosenLocaleInfo[INDEX_COUNTRY]);
        if (!chosenLocale.equals(visitor.getLocale())) {
            visitor.setLocale(chosenLocale);
        }
        return visitor.getCurrentPage();
    }
}
