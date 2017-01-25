package by.suboch.command;

import by.suboch.entity.Visitor;
import by.suboch.manager.ConfigurationManager;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import static by.suboch.command.CommandConstants.PAGE_ADD_NEW;
import static by.suboch.controller.ControllerConstants.VISITOR_KEY;

/**
 *
 */
public class AddTrackCommand implements IServletCommand {
    private static final String PARAM_TRACK_TITLE = "trackTitle";
    private static final String PARAM_TRACK_ARTIST = "trackArtist";
    private static final String PARAM_TRACK_ALBUM = "trackAlbum";
    private static final String PARAM_TRACK_GENRES = "trackGenres";
    private static final String PARAM_TRACK_DURATION = "trackDuration";
    private static final String PARAM_TRACK_PRICE = "trackPrice";
    private static final String PARAM_TRACK_DISCOUNT = "trackDiscount";

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        Visitor visitor = (Visitor) request.getSession().getAttribute(VISITOR_KEY);

        String title = request.getParameter(PARAM_TRACK_TITLE);
        String artist = request.getParameter(PARAM_TRACK_ARTIST);
        String album = request.getParameter(PARAM_TRACK_ALBUM);
        String genres = request.getParameter(PARAM_TRACK_GENRES);
        String duration = request.getParameter(PARAM_TRACK_DURATION);
        String price = request.getParameter(PARAM_TRACK_PRICE);
        String discount = request.getParameter(PARAM_TRACK_DISCOUNT);

        String nextPage;

        nextPage = visitor.getCurrentPage();

        return nextPage;
    }
}
