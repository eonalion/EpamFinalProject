package by.suboch.filter;

import by.suboch.command.CommandConstants;
import by.suboch.exception.LogicException;
import by.suboch.logic.TrackLogic;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

/**
 *
 */
@WebFilter(filterName = "MainJSPFilter", urlPatterns = {"/jsp/user/main.jsp"}, dispatcherTypes = {DispatcherType.FORWARD, DispatcherType.REQUEST})
public class MainJSPFilter implements Filter {

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        TrackLogic trackLogic = new TrackLogic();
        try {
            request.setAttribute(CommandConstants.ATTR_PAGE_AMOUNT, CommandConstants.PAGES_AMOUNT);
            request.setAttribute(CommandConstants.ATTR_POPULAR_TRACKS_ON_PAGE, trackLogic.loadPopularTracks(0, CommandConstants.POPULAR_TRACKS_PER_PAGE));
        } catch (LogicException e) {
            //TODO: Handle.
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
