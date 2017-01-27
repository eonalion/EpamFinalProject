package by.suboch.filter;

import by.suboch.controller.ControllerConstants;
import by.suboch.entity.Visitor;
import by.suboch.manager.ConfigurationManager;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static by.suboch.controller.ControllerConstants.PAGE_INDEX;

/**
 *
 */
@WebFilter(filterName = "GuestAccessFilter", urlPatterns = {"/jsp/guest/*"}, dispatcherTypes = {DispatcherType.REQUEST, DispatcherType.FORWARD})
public class GuestAccessFilter implements Filter {
    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;
        Visitor visitor = (Visitor) request.getSession().getAttribute(ControllerConstants.VISITOR_KEY);
        if (visitor.getRole() != Visitor.Role.GUEST) {
            response.sendError(HttpServletResponse.SC_FORBIDDEN);
        } else {
            filterChain.doFilter(servletRequest, servletResponse);
        }
    }

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void destroy() {

    }

}
