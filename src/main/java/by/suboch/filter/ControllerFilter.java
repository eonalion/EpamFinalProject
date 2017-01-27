package by.suboch.filter;

import by.suboch.command.CommandType;
import by.suboch.command.IServletCommand;
import by.suboch.controller.ControllerConfig;
import by.suboch.controller.ControllerConstants;
import by.suboch.entity.Visitor;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 *
 */
@WebFilter(filterName = "ControllerFilter", servletNames = {"Controller"}, dispatcherTypes = {DispatcherType.REQUEST, DispatcherType.FORWARD})
public class ControllerFilter implements Filter {

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;
        ControllerConfig controllerConfig = initConfig(request);
        Visitor visitor = (Visitor) request.getSession().getAttribute(ControllerConstants.VISITOR_KEY);

        if (controllerConfig.getCommand() == null) {
            response.sendError(HttpServletResponse.SC_BAD_REQUEST);
            return;
        }

        CommandType currentType;
        try {
            currentType = CommandType.valueOf(controllerConfig.getCommand().toUpperCase());
            Visitor.Role currentRole = visitor.getRole();
            //FIXME: Duplicate code.
            if (!currentType.role.contains(currentRole)) {
                response.sendError(HttpServletResponse.SC_BAD_REQUEST);
            } else {
                filterChain.doFilter(servletRequest, servletResponse);
            }
        } catch (IllegalArgumentException e) {
            response.sendError(HttpServletResponse.SC_BAD_REQUEST);
        }
    }

    private ControllerConfig initConfig(HttpServletRequest request) throws IOException {
        ControllerConfig servletConfig = (ControllerConfig) request.getSession().getAttribute(ControllerConstants.CONTROLLER_CONFIG_KEY);
        if (servletConfig == null) {
            servletConfig = new ControllerConfig();
            request.getSession().setAttribute(ControllerConstants.CONTROLLER_CONFIG_KEY, servletConfig);
        }
        if (ControllerConstants.AJAX_HEADER_VALUE.equals(request.getHeader(ControllerConstants.X_REQUESTED_WITH))) {
            servletConfig.setState(ControllerConfig.State.AJAX);
        } else {
            servletConfig.setState(ControllerConfig.State.FORWARD);
        }

        servletConfig.setCommand(request.getParameter(ControllerConstants.COMMAND_PARAM));
        return servletConfig;
    }

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void destroy() {

    }

}
