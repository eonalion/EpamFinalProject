package by.suboch.command;

import by.suboch.ajax.AJAXState;
import by.suboch.ajax.BiTuple;
import by.suboch.controller.ControllerConfig;
import by.suboch.controller.ControllerConstants;
import by.suboch.entity.Visitor;
import by.suboch.manager.ConfigurationManager;
import by.suboch.manager.MessageManager;
import com.google.gson.Gson;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 */
public abstract class AbstractServletCommand implements IServletCommand {

    private static Gson gson;

    public static String toJson(BiTuple<AJAXState, ?> data) {
        if (gson == null) {
            gson = new Gson();
        }
        return gson.toJson(data);
    }

    public static String toJson(AJAXState state, Object data) {
        return toJson(new BiTuple<>(state, data));
    }

    public static String handleError(ErrorHolder holder, HttpServletRequest request, HttpServletResponse response) {
        String resultData;
        ControllerConfig controllerConfig = (ControllerConfig) request.getSession().getAttribute(ControllerConstants.CONTROLLER_CONFIG_KEY);
        if (controllerConfig.getState() == ControllerConfig.State.AJAX) {
            response.setContentType(CommandConstants.MIME_TYPE_JSON);
            return toJson(AJAXState.ERROR, holder);
        } else {
            request.getSession().setAttribute(CommandConstants.ATTR_ERROR, holder);
            resultData = ConfigurationManager.getProperty(CommandConstants.PAGE_ERROR);
        }
        return resultData;
    }

    public static String handleDBError(Exception e,  HttpServletRequest request, HttpServletResponse response) {
        Visitor visitor = (Visitor)request.getSession().getAttribute(ControllerConstants.VISITOR_KEY);
        ErrorHolder errorHolder = new ErrorHolder();
        errorHolder.setCauseMessage(MessageManager.getProperty(CommandConstants.MESSAGE_ERROR_DATABASE_CAUSE, visitor.getLocale()));
        errorHolder.setToDoMessage(MessageManager.getProperty(CommandConstants.MESSAGE_ERROR_DATABASE_TO_DO, visitor.getLocale()));
        errorHolder.setCurrentPage(visitor.getCurrentPage());
        errorHolder.setException(e);
        return handleError(errorHolder, request, response);
    }
/*
    public static String handleDBError(Exception e, String toDoMessage, HttpServletRequest request, HttpServletResponse response) {
        Visitor visitor = (Visitor)request.getSession().getAttribute(ControllerConstants.VISITOR_KEY);
        ErrorHolder errorHolder = new ErrorHolder();
        errorHolder.setCauseMessage(MessageManager.getProperty(CommandConstants.MESSAGE_ERROR_DATABASE_CAUSE, visitor.getLocale()));
        errorHolder.setToDoMessage(toDoMessage);
        errorHolder.setCurrentPage(visitor.getCurrentPage());
        errorHolder.setException(e);
        return handleError(errorHolder, request);
    }*/

    public String suitablePageForm(String uri, HttpServletRequest request, HttpServletResponse response) {
        ControllerConfig controllerConfig = (ControllerConfig) request.getSession().getAttribute(ControllerConstants.CONTROLLER_CONFIG_KEY);
        if (controllerConfig.getState() == ControllerConfig.State.AJAX) {
            response.setContentType(CommandConstants.MIME_TYPE_JSON);
            return toJson(AJAXState.LOCATION_GO, request.getContextPath() + uri);
        } else {
            return uri;
        }
    }

 /*   public byte[] loadData(Part part, int maxSize) {
        byte[] empty = new byte[]{};
        byte[] data = empty;
        int fileSize = (int)part.getSize();
        if (fileSize != 0 && fileSize <= maxSize) {
            data = new byte[fileSize];
            try {
                int bytesAmount = part.getInputStream().read(data, 0, fileSize);
                if (bytesAmount != fileSize) {
                    data = empty;
                }
            } catch (IOException e) {
                data = empty;
            }

        }
        return data;
    }
*/
}