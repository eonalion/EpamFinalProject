package by.suboch.command.user;

import by.suboch.ajax.AJAXState;
import by.suboch.ajax.BiTuple;
import by.suboch.command.AbstractServletCommand;
import by.suboch.command.CommandConstants;
import by.suboch.controller.ControllerConfiguration;
import by.suboch.controller.ControllerConstants;
import by.suboch.entity.Track;
import by.suboch.exception.LogicException;
import by.suboch.logic.TrackLogic;
import by.suboch.manager.ConfigurationManager;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;


/**
 *
 */
public class AddToCartCommand extends AbstractServletCommand {
    private static final Logger LOG = LogManager.getLogger();
    private static final String PARAM_TRACK_ID = "trackId";

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws IOException {
        int trackId = Integer.parseInt(request.getParameter(PARAM_TRACK_ID));
        ControllerConfiguration controllerConfiguration = (ControllerConfiguration) request.getSession().getAttribute(ControllerConstants.CONTROLLER_CONFIG_KEY);

        TrackLogic trackLogic = new TrackLogic();
        String resultData = null;

        if (controllerConfiguration.getState() != ControllerConfiguration.State.AJAX) {
            resultData = ConfigurationManager.getProperty(CommandConstants.PAGE_USER_MAIN);
        } else {
            if (controllerConfiguration.getState() == ControllerConfiguration.State.AJAX) {
                try {
                    BiTuple<AJAXState, Object> data;
                    Track track = trackLogic.loadTrackById(trackId);
                    List<Track> cart = (List<Track>) request.getSession().getAttribute(CommandConstants.ATTR_CART_ITEMS);
                    if (!cart.contains(track)) {
                        cart.add(track);
                    }
                    data = new BiTuple<>(AJAXState.OK, null);
                    response.setContentType(CommandConstants.MIME_TYPE_JSON);
                    resultData = toJson(data);
                } catch (LogicException e) {
                    LOG.log(Level.ERROR, "Errors during sign in guest.", e);
                    resultData = handleDBError(e, request, response);
                }
            }
        }

        return resultData;
    }
}
