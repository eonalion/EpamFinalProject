package by.suboch.filter;

import by.suboch.command.AbstractServletCommand;
import by.suboch.command.CommandConstants;
import by.suboch.exception.LogicException;
import by.suboch.logic.AccountLogic;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 *
 */
@WebFilter(filterName = "ClientsJSPFilter", urlPatterns = {"/jsp/admin/clients.jsp"}, dispatcherTypes = {DispatcherType.FORWARD, DispatcherType.REQUEST})
public class ClientsJSPFilter implements Filter {

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;
        try {
            AccountLogic accountLogic = new AccountLogic();
            request.setAttribute(CommandConstants.ATTR_ACCOUNT_LIST, accountLogic.loadAllAccounts());
        } catch (LogicException e) {
            AbstractServletCommand.handleDBError(e, request, response);
            return;
        }

        filterChain.doFilter(servletRequest, servletResponse);
    }

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void destroy() {

    }
}
