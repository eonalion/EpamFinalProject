package by.suboch.filter;

import by.suboch.command.AbstractServletCommand;
import by.suboch.command.CommandConstants;
import by.suboch.entity.Track;
import by.suboch.exception.LogicException;
import by.suboch.logic.TrackLogic;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 *
 */
@WebFilter(filterName = "CatalogTracksJSPFilter", urlPatterns = {"/jsp/user/catalog_tracks.jsp"}, dispatcherTypes = {DispatcherType.FORWARD, DispatcherType.REQUEST})
public class CatalogTracksJSPFilter implements Filter {

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;
        try {
            TrackLogic trackLogic = new TrackLogic();
            List<Track> trackList = trackLogic.loadAllTracks();
            request.setAttribute(CommandConstants.ATTR_TRACK_LIST, trackList);
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
