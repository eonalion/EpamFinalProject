package by.suboch.filter;

import by.suboch.controller.ControllerConstants;
import by.suboch.entity.Visitor;
import by.suboch.manager.ConfigurationManager;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

/**
 *
 */
@WebFilter(filterName = "VisitorFilter", urlPatterns = {"/*"})
public class VisitorFilter implements Filter {

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        initUserState(request);
        filterChain.doFilter(servletRequest, servletResponse);
    }

    private void initUserState(HttpServletRequest request) {
        Visitor visitor = (Visitor)request.getSession().getAttribute(ControllerConstants.VISITOR_KEY);
        if (visitor == null) {
            visitor = new Visitor();
            visitor.setRole(Visitor.Role.GUEST);
            visitor.setLocale(ControllerConstants.DEFAULT_LOCALE);
            visitor.setCurrentPage(ConfigurationManager.getProperty(ControllerConstants.PAGE_INDEX));
            request.getSession().setAttribute(ControllerConstants.VISITOR_KEY, visitor);
        }
    }

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void destroy() {

    }

}
