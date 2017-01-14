package com.suboch.controller;


import com.suboch.command.CommandManager;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Locale;

import com.suboch.command.IServletCommand;
import com.suboch.database.ConnectionPool;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 *
 */

@WebServlet("/s")
public class Controller extends HttpServlet {
    private static final Logger LOG = LogManager.getLogger();
    private static final String LOCALE_PARAMETER = "locale";
    private static final Locale DEFAULT_LOCALE = new Locale("en", "US");

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
        if(request.getSession().getAttribute(LOCALE_PARAMETER)==null) {
            request.getSession().setAttribute(LOCALE_PARAMETER, DEFAULT_LOCALE);
        }
        CommandManager client = new CommandManager();
        IServletCommand command = client.defineCommand(request, response);
        String page = command.execute(request, response);
        LOG.info(request.getSession().getAttribute("locale")+" from servlet");
        request.getRequestDispatcher(page).forward(request, response);
    }

}
