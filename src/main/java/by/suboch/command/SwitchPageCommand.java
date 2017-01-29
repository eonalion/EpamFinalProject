package by.suboch.command;

import by.suboch.controller.ControllerConfig;
import by.suboch.controller.ControllerConstants;
import by.suboch.entity.Visitor;
import by.suboch.exception.LogicException;
import by.suboch.logic.TrackLogic;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static by.suboch.command.CommandConstants.ATTR_TRACKS_ON_PAGE;

/**
 *
 */
public class SwitchPageCommand implements IServletCommand {
    private static final String PARAM_CURRENT_PAGE_NUMBER = "pageNumber";
    private static final String PARAM_TO_FIRST_PAGE = "toFirstPage";
    private static final String PARAM_TO_LAST_PAGE = "toLastPage";

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws IOException {
        boolean toFirstPage = Boolean.parseBoolean(request.getParameter(PARAM_TO_FIRST_PAGE));
        boolean toLastPage = Boolean.parseBoolean(request.getParameter(PARAM_TO_LAST_PAGE));
        int pageAmount  = (int) request.getSession().getAttribute(CommandConstants.ATTR_PAGE_AMOUNT);

        ControllerConfig controllerConfig = (ControllerConfig) request.getSession().getAttribute(ControllerConstants.CONTROLLER_CONFIG_KEY);
        Visitor visitor = (Visitor) request.getSession().getAttribute(ControllerConstants.VISITOR_KEY);

        int pageNumber;
        if(toFirstPage) {
            pageNumber = 0;
        } else if(toLastPage){
            pageNumber = pageAmount-1;
        } else {
            pageNumber =  Integer.parseInt(request.getParameter(PARAM_CURRENT_PAGE_NUMBER)) - 1;
        }

        try {
            TrackLogic trackLogic = new TrackLogic();
            request.getSession().setAttribute(ATTR_TRACKS_ON_PAGE, trackLogic.loadPopularTracks(pageNumber*CommandConstants.TRACKS_PER_PAGE, CommandConstants.TRACKS_PER_PAGE));
        } catch (LogicException e){
            //TODO: Handle exception.
        }

        controllerConfig.setState(ControllerConfig.State.FORWARD);

        return visitor.getCurrentPage();
    }
}
