package by.suboch.command;

import by.suboch.exception.LogicException;
import by.suboch.logic.AccountLogic;
import by.suboch.logic.ArtistLogic;
import by.suboch.manager.ConfigurationManager;
import by.suboch.manager.MessageManager;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import static by.suboch.command.CommandConstants.*;

/**
 *
 */
public class AddArtistCommand implements IServletCommand {
    private static final String ADD_NEW_PAGE = "path.page.addNew";
    private static final String ERROR_PAGE = "path.page.error";
    private static final String ERROR_MESSAGE = "message.artist.error";

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {//TODO: ROMAN!!! init var and then only return statement.
        String name = request.getParameter(ARTIST_NAME_PARAM);
        String country = request.getParameter(ARTIST_COUNTRY_PARAM);
        String careerStart = request.getParameter(ARTIST_CAREER_START_PARAM);
        String careerEnd = request.getParameter(ARTIST_CAREER_END_PARAM);
        String description = request.getParameter(ARTIST_DESCRIPTION_PARAM);
        //TODO: ROMAN!!!String page = null;
        ArtistLogic logic = new ArtistLogic();
        try {
            if(logic.addArtist(name, country, careerStart, careerEnd, description)) {
                // TODO: Set success message.
                return ConfigurationManager.getProperty(ADD_NEW_PAGE);
            } else {
                // TODO: Set warn message(or it's already set?).
                return ConfigurationManager.getProperty(ADD_NEW_PAGE);
            }
        } catch (LogicException e) {
            //TODO: Handle exception;
            request.getSession().setAttribute(MESSAGE_ATTR, MessageManager.getProperty(ERROR_MESSAGE));
            return ConfigurationManager.getProperty(ERROR_PAGE);
        }
        //TODO: ROMAN!!!return page;
    }
}
