package by.suboch.command;

import by.suboch.entity.Visitor;
import by.suboch.exception.LogicException;
import by.suboch.logic.AlbumLogic;
import by.suboch.manager.ConfigurationManager;
import by.suboch.manager.MessageManager;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

import static by.suboch.command.CommandConstants.*;
import static by.suboch.controller.ControllerConstants.VISITOR_KEY;

/**
 *
 */
public class AddAlbumCommand implements IServletCommand {

    private static final String PATTERN_DATE = "yyyy-MM-dd";
    private static final String PARAM_ALBUM_TITLE = "albumTitle";
    private static final String PARAM_ALBUM_RELEASE_DATE = "albumReleaseDate";
    private static final String MESSAGE_ERROR_ADD_ALBUM = "message.album.error";

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        Visitor visitor = (Visitor) request.getSession().getAttribute(VISITOR_KEY);

        String title = request.getParameter(PARAM_ALBUM_TITLE);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(PATTERN_DATE);
        formatter = formatter.withLocale((Locale) request.getSession().getAttribute(ATTR_LOCALE));
        LocalDate releaseDate = LocalDate.parse(request.getParameter(PARAM_ALBUM_RELEASE_DATE), formatter);

        AlbumLogic logic = new AlbumLogic();

        String nextPage;
        try {
            if (logic.addAlbum(title, releaseDate)) {
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
