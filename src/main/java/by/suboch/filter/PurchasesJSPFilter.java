package by.suboch.filter;

import by.suboch.command.AbstractServletCommand;
import by.suboch.command.CommandConstants;
import by.suboch.entity.Account;
import by.suboch.entity.Purchase;
import by.suboch.exception.LogicException;
import by.suboch.logic.PurchaseLogic;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 *
 */
@WebFilter(filterName = "PurchasesJSPFilter", urlPatterns = {"/jsp/user/purchases.jsp"}, dispatcherTypes = {DispatcherType.FORWARD, DispatcherType.REQUEST})
public class PurchasesJSPFilter implements Filter {

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;

        try {
            PurchaseLogic purchaseLogic = new PurchaseLogic();
            Account account = (Account) request.getSession().getAttribute(CommandConstants.ATTR_ACCOUNT);
            List<Purchase> purchaseList = purchaseLogic.loadAllPurchases(account.getAccountId());
            request.setAttribute(CommandConstants.ATTR_ACCOUNT_PURCHASES, purchaseList);
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
