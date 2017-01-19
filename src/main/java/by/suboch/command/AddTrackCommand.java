package by.suboch.command;

import by.suboch.manager.ConfigurationManager;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import static by.suboch.command.CommandConstants.*;

/**
 *
 */
public class AddTrackCommand implements IServletCommand {
    private static final String CREATE_ENTITY_PAGE = "path.page.addNew";

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        String title = request.getParameter(TRACK_TITLE_PARAM);
        String artist = request.getParameter(TRACK_ARTIST_PARAM);
        String album = request.getParameter(TRACK_ALBUM_PARAM);
        String genres = request.getParameter(TRACK_GENRES_PARAM);
        String duration = request.getParameter(TRACK_DURATION_PARAM);
        String price = request.getParameter(TRACK_PRICE_PARAM);
        String discount = request.getParameter(TRACK_DISCOUNT_PARAM);

        return ConfigurationManager.getProperty(CREATE_ENTITY_PAGE);
    }
}
