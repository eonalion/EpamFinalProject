package by.suboch.filter;

import by.suboch.command.AbstractServletCommand;
import by.suboch.command.CommandConstants;
import by.suboch.exception.LogicException;
import by.suboch.logic.AlbumLogic;
import by.suboch.logic.GenreLogic;
import by.suboch.logic.TrackLogic;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 *
 */
@WebFilter(filterName = "CreateJSPFilter", urlPatterns = {"/jsp/admin/create.jsp"}, dispatcherTypes = {DispatcherType.FORWARD, DispatcherType.REQUEST})
public class CreateJSPFilter implements Filter {

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;
        try {
            TrackLogic trackLogic = new TrackLogic();
            AlbumLogic albumLogic = new AlbumLogic();
            GenreLogic genreLogic = new GenreLogic();
            request.setAttribute(CommandConstants.ATTR_TRACK_LIST, trackLogic.loadAllTracks());
            request.setAttribute(CommandConstants.ATTR_ALBUM_LIST, albumLogic.loadAllAlbums());
            request.setAttribute(CommandConstants.ATTR_GENRE_LIST, genreLogic.loadAllGenres());
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
