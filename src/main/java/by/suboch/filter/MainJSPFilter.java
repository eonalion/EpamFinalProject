package by.suboch.filter;

import by.suboch.command.AbstractServletCommand;
import by.suboch.command.CommandConstants;
import by.suboch.exception.LogicException;
import by.suboch.logic.TrackLogic;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;

/**
 *
 */
@WebFilter(filterName = "MainJSPFilter", urlPatterns = {"/jsp/user/main.jsp"}, dispatcherTypes = {DispatcherType.FORWARD, DispatcherType.REQUEST})
public class MainJSPFilter implements Filter {

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;

        if (request.getSession().getAttribute(CommandConstants.ATTR_CART_ITEMS) == null) {
            request.getSession().setAttribute(CommandConstants.ATTR_CART_ITEMS, new ArrayList<>());
        }

        TrackLogic trackLogic = new TrackLogic();
        try {
            if (request.getSession().getAttribute(CommandConstants.ATTR_PAGE_AMOUNT) == null) {
                request.getSession().setAttribute(CommandConstants.ATTR_PAGE_AMOUNT, CommandConstants.PAGES_AMOUNT);
            }
            request.getSession().setAttribute(CommandConstants.ATTR_POPULAR_TRACKS_ON_PAGE, trackLogic.loadPopularTracks(0, CommandConstants.POPULAR_TRACKS_PER_PAGE));
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
