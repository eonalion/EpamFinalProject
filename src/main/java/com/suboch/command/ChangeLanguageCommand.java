package com.suboch.command;

import com.suboch.manager.ConfigurationManager;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Locale;
import java.util.ResourceBundle;

/**
 *
 */
public class ChangeLanguageCommand implements IServletCommand {
    private static final Logger LOG = LogManager.getLogger();
    private static final String LOCALE_PARAMETER = "locale";
    private static final String LOCALE_DELIMITER = "_";

    private static final String LOGIN_PAGE = "path.page.registration";

    private static final int LANGUAGE = 0;
    private static final int COUNTRY = 1;

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        Locale currentLocale = (Locale) request.getSession().getAttribute(LOCALE_PARAMETER);
        String[] chosenLocaleInfo = request.getParameter(LOCALE_PARAMETER).split(LOCALE_DELIMITER);
        Locale chosenLocale = new Locale(chosenLocaleInfo[LANGUAGE], chosenLocaleInfo[COUNTRY]);
        LOG.info(chosenLocale.toString());
        if (!chosenLocale.equals(currentLocale)) {
            LOG.info("changed");
            request.getSession().setAttribute(LOCALE_PARAMETER, chosenLocale);
        }

        return ConfigurationManager.getProperty(LOGIN_PAGE);
    }
}
