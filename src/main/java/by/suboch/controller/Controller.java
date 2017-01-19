package by.suboch.controller;


import by.suboch.command.*;
import by.suboch.database.ConnectionPool;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Locale;

import by.suboch.manager.ConfigurationManager;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import static by.suboch.command.CommandConstants.CURRENT_PAGE_ATTR;
import static by.suboch.command.CommandConstants.LOCALE_ATTR;
import static by.suboch.command.CommandConstants.VISITOR_ROLE_ATTR;

/**
 *
 */

@WebServlet("/s")
@MultipartConfig
public class Controller extends HttpServlet {
    private static final Logger LOG = LogManager.getLogger();

    private static final Locale DEFAULT_LOCALE = new Locale("en", "EN");

    private static final String ERROR_PAGE = "path.page.error";
    private static final String REGISTRATION_LOGIN_PAGE = "path.page.registration";

    @Override
    public void init() throws ServletException {
        super.init();
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    public void destroy() {
        super.destroy();
        ConnectionPool.getInstance().closePool();
    }

    private void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        setDefaultAttributes(request);
        CommandManager client = new CommandManager();
        IServletCommand command = client.defineCommand(request, response);

        if (!(command instanceof EmptyCommand)) {
            String page = command.execute(request, response);
            request.getSession().setAttribute(CURRENT_PAGE_ATTR, page);
            request.getRequestDispatcher(page).forward(request, response);
            //TODO: Handle warn message.
            //FIXME: If we are on error page how to define where to return?
        } else {
            // Illegal action
            // TODO: Clear session.
            request.getRequestDispatcher(ConfigurationManager.getProperty(ERROR_PAGE)).forward(request, response);
        }
    }

    private void setDefaultAttributes(HttpServletRequest request) {//TODO: ROMAN!!!FILTER   .
        // FIXME: If role is not null other attrs are set?
        if (request.getSession().getAttribute(VISITOR_ROLE_ATTR) == null) {
            request.getSession().setAttribute(VISITOR_ROLE_ATTR, VisitorRole.GUEST.toString());
        }
        if (request.getSession().getAttribute(LOCALE_ATTR) == null) {
            request.getSession().setAttribute(LOCALE_ATTR, DEFAULT_LOCALE);
        }
        if (request.getSession().getAttribute(CURRENT_PAGE_ATTR) == null) {
            request.getSession().setAttribute(CURRENT_PAGE_ATTR, ConfigurationManager.getProperty(REGISTRATION_LOGIN_PAGE));
        }
    }

}
