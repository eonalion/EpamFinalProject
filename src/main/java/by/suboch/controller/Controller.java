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

import by.suboch.entity.Visitor;
import by.suboch.manager.ConfigurationManager;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import static by.suboch.command.CommandConstants.ATTR_CURRENT_PAGE;
import static by.suboch.command.CommandConstants.ATTR_LOCALE;
import static by.suboch.command.CommandConstants.ATTR_VISITOR_ROLE;
import static by.suboch.controller.ControllerConstants.CONTROLLER_CONFIG_KEY;

/**
 *
 */

@WebServlet(urlPatterns = "/s", name = "Controller")
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
        process(request, response);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        process(request, response);
    }

    @Override
    public void destroy() {
        super.destroy();
        ConnectionPool.getInstance().closePool();
    }

    private void process(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        ControllerConfig servletConfig = (ControllerConfig) request.getSession().getAttribute(CONTROLLER_CONFIG_KEY);
        CommandManager client = new CommandManager();
        IServletCommand command = client.defineCommand(servletConfig.getCommand());

       /* if (servletConfig.getState() == ControllerConfig.State.SKIPPED) {
            return;
        }*/
        //Visitor visitor = (Visitor) request.getSession().getAttribute(ControllerConstants.VISITOR_KEY);
        String nextPage = command.execute(request, response);

        switch (servletConfig.getState()) {
            case FORWARD:
                request.getRequestDispatcher(nextPage).forward(request, response);
                break;
            case REDIRECT:
                response.sendRedirect(nextPage);
                break;
            case AJAX:
                response.getWriter().write(nextPage);
                break;
        }
    }
}
