package by.suboch.command;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Locale;

import static by.suboch.command.CommandConstants.CURRENT_PAGE_ATTR;
import static by.suboch.command.CommandConstants.LOCALE_ATTR;

/**
 *
 */
public class ChangeLocaleCommand implements IServletCommand {
    private static final Logger LOG = LogManager.getLogger();
    private static final String LOCALE_DELIMITER = "_";

    private static final int LANGUAGE = 0;
    private static final int COUNTRY = 1;

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        Locale currentLocale = (Locale) request.getSession().getAttribute(LOCALE_ATTR);
        String[] chosenLocaleInfo = request.getParameter(LOCALE_ATTR).split(LOCALE_DELIMITER);
        Locale chosenLocale = new Locale(chosenLocaleInfo[LANGUAGE], chosenLocaleInfo[COUNTRY]);
        if (!chosenLocale.equals(currentLocale)) {
            request.getSession().setAttribute(LOCALE_ATTR, chosenLocale);
        }

        return (String) request.getSession().getAttribute(CURRENT_PAGE_ATTR);
    }
}
