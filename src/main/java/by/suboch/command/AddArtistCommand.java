package by.suboch.command;

import by.suboch.entity.Visitor;
import by.suboch.exception.LogicException;
import by.suboch.logic.AlbumLogic;
import by.suboch.logic.ArtistLogic;
import by.suboch.logic.LogicActionResult;
import by.suboch.manager.ConfigurationManager;
import by.suboch.manager.MessageManager;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import java.io.IOException;

import static by.suboch.command.CommandConstants.*;
import static by.suboch.controller.ControllerConstants.VISITOR_KEY;

/**
 *
 */
public class AddArtistCommand implements IServletCommand {

    private static final String PARAM_ARTIST_NAME = "artistName";
    private static final String PARAM_ARTIST_COUNTRY = "artistCountry";
    private static final String PARAM_ARTIST_DESCRIPTION = "artistDescription";
    private static final String PARAM_ARTIST_IMAGE = "artistImage";
    private static final String PARAM_ARTIST_ALBUMS = "artistAlbums";
    private static final String MESSAGE_ERROR_ADD_ARTIST = "message.artist.error";

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        Visitor visitor = (Visitor) request.getSession().getAttribute(VISITOR_KEY);

        String name = request.getParameter(PARAM_ARTIST_NAME);
        String country = request.getParameter(PARAM_ARTIST_COUNTRY);
        String description = request.getParameter(PARAM_ARTIST_DESCRIPTION);
        String[] albumIds = request.getParameterValues(PARAM_ARTIST_ALBUMS);


        int fileSize;
        byte [] image = null;
        try {
            Part imagePart = request.getPart(PARAM_ARTIST_IMAGE);
            fileSize = (int)imagePart.getSize();
            if (fileSize != 0) {
                image = new byte[fileSize];
                imagePart.getInputStream().read(image, 0, fileSize);//TODO check returned value.
            }
        } catch (IOException | ServletException e) {
            //TODO:Handle exception.
        }

        ArtistLogic artistLogic = new ArtistLogic();
        AlbumLogic albumLogic = new AlbumLogic();
        String nextPage;
        try {
            LogicActionResult actionResult = artistLogic.addArtist(name, country, description, image);
            if(albumIds!=null) {
                int artistId = artistLogic.loadArtistId(name, country);
                albumLogic.setArtistId(albumIds, artistId);
            }
           /* if (actionResult.getState() == LogicActionResult.State.SUCCESS) {
                nextPage = "";//toJson(signUpResult);
                // TODO: Set success message.
            } else {
                // TODO: Set warn message(or it's already set?).
            }*/
            nextPage = visitor.getCurrentPage();
        } catch (LogicException e) {
            //TODO: Handle exception;
            request.getSession().setAttribute(ATTR_MESSAGE, MessageManager.getProperty(MESSAGE_ERROR_ADD_ARTIST, visitor.getLocale()));
            nextPage = ConfigurationManager.getProperty(PAGE_ERROR);
        }
        return nextPage;
    }
}
