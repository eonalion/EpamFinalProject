package by.suboch.command.admin;

import by.suboch.ajax.AJAXState;
import by.suboch.ajax.BiTuple;
import by.suboch.command.AbstractServletCommand;
import by.suboch.command.CommandConstants;
import by.suboch.command.IServletCommand;
import by.suboch.controller.ControllerConfiguration;
import by.suboch.controller.ControllerConstants;
import by.suboch.entity.Visitor;
import by.suboch.exception.LogicException;
import by.suboch.logic.AlbumLogic;
import by.suboch.logic.ArtistLogic;
import by.suboch.logic.LogicActionResult;
import by.suboch.logic.TrackLogic;
import by.suboch.manager.ConfigurationManager;
import by.suboch.manager.MessageManager;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

import static by.suboch.controller.ControllerConstants.CONTROLLER_CONFIG_KEY;
import static by.suboch.controller.ControllerConstants.VISITOR_KEY;

/**
 *
 */
public class AddAlbumCommand extends AbstractServletCommand {

    private static final Logger LOG = LogManager.getLogger();
    private static final String PATTERN_DATE = "yyyy-MM-dd";
    private static final String PARAM_ALBUM_TITLE = "albumTitle";
    private static final String PARAM_ALBUM_RELEASE_DATE = "albumReleaseDate";
    private static final String PARAM_ALBUM_IMAGE = "albumImage";
    private static final String PARAM_ALBUM_TRACKS = "albumTracks";

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        Visitor visitor = (Visitor) request.getSession().getAttribute(VISITOR_KEY);
        ControllerConfiguration controllerConfiguration = (ControllerConfiguration) request.getSession().getAttribute(CONTROLLER_CONFIG_KEY);

        String title = request.getParameter(PARAM_ALBUM_TITLE);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(PATTERN_DATE);
        formatter = formatter.withLocale(visitor.getLocale());
        LocalDate releaseDate = LocalDate.parse(request.getParameter(PARAM_ALBUM_RELEASE_DATE), formatter);
        String[] tracksInAlbumIds = request.getParameterValues(PARAM_ALBUM_TRACKS);

        int fileSize;
        byte[] image = null;
        try {
            Part imagePart = request.getPart(PARAM_ALBUM_IMAGE);
            fileSize = (int) imagePart.getSize();
            if (fileSize != 0) {
                image = new byte[fileSize];
                imagePart.getInputStream().read(image, 0, fileSize);//TODO check returned value.
            }
        } catch (IOException | ServletException e) {
            //TODO:Handle exception.
        }

        String resultData;
        AlbumLogic albumLogic = new AlbumLogic();
        TrackLogic trackLogic = new TrackLogic();
        if (controllerConfiguration.getState() != ControllerConfiguration.State.AJAX) {
            resultData = ConfigurationManager.getProperty(CommandConstants.PAGE_CREATE);
            //request.setAttribute(PARAM_GENRE_NAME, genreName);
        } else {
            try {
                BiTuple<LogicActionResult, Integer> result = albumLogic.addAlbum(title, releaseDate, image);
                int albumId = result.getRight();
                LogicActionResult addAlbumResult = result.getLeft();
                if (tracksInAlbumIds != null) {
                    trackLogic.setAlbumId(tracksInAlbumIds, albumId);
                }
                setResultMessage(addAlbumResult, visitor.getLocale());
                response.setContentType(CommandConstants.MIME_TYPE_JSON);
                resultData = toJson(AJAXState.HANDLE, addAlbumResult);
            } catch (LogicException e) {
                LOG.error("Errors while adding artist.", e);
                resultData = handleDBError(e, request, response);
            }
        }

        return resultData;
    }

    private void setResultMessage(LogicActionResult addGenreResult, Locale locale) {
        switch (addGenreResult.getResult()) {
            case FAILURE_ALBUM_NOT_UNIQUE:
                addGenreResult.setMessage(MessageManager.getProperty(CommandConstants.MESSAGE_FAILURE_ALBUM_NOT_UNIQUE, locale));
                addGenreResult.setTarget(PARAM_ALBUM_TITLE);
                break;
            case SUCCESS_ADD_ALBUM:
                addGenreResult.setMessage(MessageManager.getProperty(CommandConstants.MESSAGE_SUCCESS_ADD_ALBUM, locale));
                break;
            default:
        }
    }
}
