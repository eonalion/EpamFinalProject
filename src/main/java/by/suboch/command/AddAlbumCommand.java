package by.suboch.command;

import by.suboch.controller.ControllerConfig;
import by.suboch.entity.Album;
import by.suboch.entity.Visitor;
import by.suboch.exception.LogicException;
import by.suboch.logic.AlbumLogic;
import by.suboch.logic.LogicActionResult;
import by.suboch.logic.TrackLogic;
import by.suboch.manager.ConfigurationManager;
import by.suboch.manager.MessageManager;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import java.io.IOException;
import java.lang.reflect.Array;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Locale;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static by.suboch.command.CommandConstants.*;
import static by.suboch.controller.ControllerConstants.CONTROLLER_CONFIG_KEY;
import static by.suboch.controller.ControllerConstants.VISITOR_KEY;

/**
 *
 */
public class AddAlbumCommand implements IServletCommand {

    private static final String PATTERN_DATE = "yyyy-MM-dd";
    private static final String PARAM_ALBUM_TITLE = "albumTitle";
    private static final String PARAM_ALBUM_RELEASE_DATE = "albumReleaseDate";
    private static final String PARAM_ALBUM_IMAGE = "albumImage";
    private static final String PARAM_ALBUM_TRACKS = "albumTracks";
    private static final String MESSAGE_ERROR_ADD_ALBUM = "message.album.error";

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        Visitor visitor = (Visitor) request.getSession().getAttribute(VISITOR_KEY);
        ControllerConfig controllerConfig = (ControllerConfig) request.getSession().getAttribute(CONTROLLER_CONFIG_KEY);

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

        AlbumLogic albumLogic = new AlbumLogic();
        TrackLogic trackLogic = new TrackLogic();

        String nextPage;
        try {
            LogicActionResult actionResult = albumLogic.addAlbum(title, releaseDate, image);
            if(tracksInAlbumIds!=null) {
                int albumId = albumLogic.loadAlbumId(title, releaseDate);
                trackLogic.setAlbumId(tracksInAlbumIds, albumId);
            }
            if (actionResult.getState() == LogicActionResult.State.SUCCESS) {
                nextPage = "";//toJson(signUpResult);
                // TODO: Set success message.
            } else {
                // TODO: Set warn message(or it's already set?).
            }
            nextPage = visitor.getCurrentPage();
        } catch (LogicException e) {
            //TODO: Handle exception;
            request.getSession().setAttribute(ATTR_MESSAGE, MessageManager.getProperty(MESSAGE_ERROR_ADD_ALBUM, visitor.getLocale()));
            nextPage = ConfigurationManager.getProperty(PAGE_ERROR);
        }
        return nextPage;
    }
}
