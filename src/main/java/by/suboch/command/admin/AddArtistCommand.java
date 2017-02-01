package by.suboch.command.admin;

import by.suboch.ajax.AJAXState;
import by.suboch.ajax.BiTuple;
import by.suboch.command.AbstractServletCommand;
import by.suboch.command.CommandConstants;
import by.suboch.controller.ControllerConfiguration;
import by.suboch.controller.ControllerConstants;
import by.suboch.entity.Visitor;
import by.suboch.exception.LogicException;
import by.suboch.logic.AlbumLogic;
import by.suboch.logic.ArtistLogic;
import by.suboch.logic.LogicActionResult;
import by.suboch.manager.ConfigurationManager;
import by.suboch.manager.MessageManager;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import java.io.IOException;
import java.util.Locale;

import static by.suboch.controller.ControllerConstants.VISITOR_KEY;

/**
 *
 */
public class AddArtistCommand extends AbstractServletCommand {
    private static final Logger LOG = LogManager.getLogger();

    private static final String PARAM_ARTIST_NAME = "artistName";
    private static final String PARAM_ARTIST_COUNTRY = "artistCountry";
    private static final String PARAM_ARTIST_DESCRIPTION = "artistDescription";
    private static final String PARAM_ARTIST_IMAGE = "artistImage";
    private static final String PARAM_ARTIST_ALBUMS = "artistAlbums";

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        String name = request.getParameter(PARAM_ARTIST_NAME);
        String country = request.getParameter(PARAM_ARTIST_COUNTRY);
        String description = request.getParameter(PARAM_ARTIST_DESCRIPTION);
        String[] albumIds = request.getParameterValues(PARAM_ARTIST_ALBUMS);

        int fileSize;
        byte[] image = null;
        try {
            Part imagePart = request.getPart(PARAM_ARTIST_IMAGE);
            fileSize = (int) imagePart.getSize();
            if (fileSize != 0) {
                image = new byte[fileSize];
                imagePart.getInputStream().read(image, 0, fileSize);//TODO check returned value.
            }
        } catch (IOException | ServletException e) {
            LOG.error("Errors while adding track.", e);
            return handleDBError(e, request, response);
        }

        ControllerConfiguration controllerConfiguration = (ControllerConfiguration) request.getSession().getAttribute(ControllerConstants.CONTROLLER_CONFIG_KEY);
        Visitor visitor = (Visitor) request.getSession().getAttribute(VISITOR_KEY);
        String resultData;
        ArtistLogic artistLogic = new ArtistLogic();
        AlbumLogic albumLogic = new AlbumLogic();
        if (controllerConfiguration.getState() != ControllerConfiguration.State.AJAX) {
            resultData = ConfigurationManager.getProperty(CommandConstants.PAGE_CREATE);
            //request.setAttribute(PARAM_GENRE_NAME, genreName);
        } else {
            try {
                BiTuple<LogicActionResult, Integer> result = artistLogic.addArtist(name, country, description, image);
                int artistId = result.getRight();
                LogicActionResult addArtistResult = result.getLeft();
                if (albumIds != null) {
                    albumLogic.setArtistId(albumIds, artistId);
                }
                setResultMessage(addArtistResult, visitor.getLocale());
                response.setContentType(CommandConstants.MIME_TYPE_JSON);
                resultData = toJson(AJAXState.HANDLE, addArtistResult);
            } catch (LogicException e) {
                LOG.error("Errors while adding artist.", e);
                resultData = handleDBError(e, request, response);
            }
        }

        return resultData;
    }

    private void setResultMessage(LogicActionResult addGenreResult, Locale locale) {
        switch (addGenreResult.getResult()) {
            case FAILURE_ARTIST_NOT_UNIQUE:
                addGenreResult.setMessage(MessageManager.getProperty(CommandConstants.MESSAGE_FAILURE_ARTIST_NOT_UNIQUE, locale));
                addGenreResult.setTarget(PARAM_ARTIST_NAME);
                break;
            case SUCCESS_ADD_ARTIST:
                addGenreResult.setMessage(MessageManager.getProperty(CommandConstants.MESSAGE_SUCCESS_ADD_ARTIST, locale));
                break;
            default:
        }
    }
}
