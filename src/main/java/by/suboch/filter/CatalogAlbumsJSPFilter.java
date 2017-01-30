package by.suboch.filter;

import by.suboch.command.CommandConstants;
import by.suboch.entity.Album;
import by.suboch.exception.LogicException;
import by.suboch.logic.AlbumLogic;
import by.suboch.logic.GenreLogic;
import by.suboch.logic.TrackLogic;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.List;

/**
 *
 */
@WebFilter(filterName = "CatalogAlbumsJSPFilter", urlPatterns = {"/jsp/user/catalog_albums.jsp"}, dispatcherTypes = {DispatcherType.FORWARD, DispatcherType.REQUEST})
public class CatalogAlbumsJSPFilter implements Filter {

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        try {
            AlbumLogic albumLogic = new AlbumLogic();
            List<Album> albumList = albumLogic.loadAllAlbums();
            request.getSession().setAttribute(CommandConstants.ATTR_ALBUM_LIST, albumList);
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
