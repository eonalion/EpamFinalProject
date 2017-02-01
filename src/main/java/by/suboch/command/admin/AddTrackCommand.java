package by.suboch.command.admin;

import by.suboch.ajax.AJAXState;
import by.suboch.command.AbstractServletCommand;
import by.suboch.command.CommandConstants;
import by.suboch.command.IServletCommand;
import by.suboch.controller.ControllerConfiguration;
import by.suboch.controller.ControllerConstants;
import by.suboch.entity.Visitor;
import by.suboch.exception.LogicException;
import by.suboch.logic.GenreLogic;
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

import java.io.*;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Locale;
import java.util.logging.Level;

import static by.suboch.command.CommandConstants.ATTR_MESSAGE;
import static by.suboch.command.CommandConstants.PAGE_ERROR;
import static by.suboch.controller.ControllerConstants.VISITOR_KEY;
import static com.sun.xml.internal.ws.spi.db.BindingContextFactory.LOGGER;

/**
 *
 */
public class AddTrackCommand extends AbstractServletCommand {
    private static final Logger LOG = LogManager.getLogger();

    private static final String PARAM_TRACK_GENRE = "genre";
    private static final String PARAM_TRACK_PRICE = "price";
    private static final String PARAM_TRACK_FILE = "file";

    private static final String SEPARATOR = "/";

    private static final int maxFileSize = 50 * 1024 * 1024; //TODO: check size
    private static final int maxMemSize = 50 * 1024 * 1024;

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws IOException {
        double price = Double.parseDouble(request.getParameter(PARAM_TRACK_PRICE));
        int genreId = Integer.parseInt(request.getParameter(PARAM_TRACK_GENRE));

        String fileName = "";
        String relativePath = "";
        String nextPage;

        OutputStream out = null;
        InputStream filecontent = null;
        int fileSize;
        byte[] track = null;
        try {
            Part filePart = request.getPart(PARAM_TRACK_FILE);
            fileName = getFileName(filePart);
            fileSize = (int) filePart.getSize();

            if (fileName != null) {
                relativePath = SEPARATOR + fileName.toLowerCase().charAt(0);
            }

            Path path = Paths.get(ConfigurationManager.getProperty(CommandConstants.RESOURCE_AUDIO), relativePath);

            int read;
            if (fileSize != 0) {
                new File(path.toString()).mkdirs();
                out = new FileOutputStream(path+SEPARATOR+fileName);
                track = new byte[fileSize];
                filecontent = filePart.getInputStream();
                while ((read = filecontent.read(track)) != -1) {
                    out.write(track, 0, read);
                }
            }
        } catch (IOException | ServletException e) {
            LOG.error("Errors while adding track.", e);
            return handleDBError(e, request, response);
        } finally {
            if (out != null) {
                out.close();
            }
            if (filecontent != null) {
                filecontent.close();
            }
        }

        ControllerConfiguration controllerConfiguration = (ControllerConfiguration) request.getSession().getAttribute(ControllerConstants.CONTROLLER_CONFIG_KEY);
        Visitor visitor = (Visitor) request.getSession().getAttribute(VISITOR_KEY);
        String resultData;
        TrackLogic trackLogic = new TrackLogic();
        if (controllerConfiguration.getState() != ControllerConfiguration.State.AJAX) {
            resultData = ConfigurationManager.getProperty(CommandConstants.PAGE_CREATE);
            //request.setAttribute(PARAM_GENRE_NAME, genreName);
        } else {
            try {
                String location = relativePath + SEPARATOR + fileName;
                LogicActionResult addTrackResult = trackLogic.addTrack(fileName, location, price, genreId);
                setResultMessage(addTrackResult, visitor.getLocale());
                response.setContentType(CommandConstants.MIME_TYPE_JSON);
                resultData = toJson(AJAXState.HANDLE, addTrackResult);
            } catch (LogicException e) {
                LOG.error("Errors while adding track.", e);
                resultData = handleDBError(e, request, response);
            }
        }
        return resultData;
    }


    private String getFileName(final Part part) {
        final String partHeader = part.getHeader("content-disposition");
        LOGGER.log(Level.INFO, "Part Header = {0}", partHeader);
        for (String content : part.getHeader("content-disposition").split(";")) {
            if (content.trim().startsWith("filename")) {
                return content.substring(
                        content.indexOf('=') + 1).trim().replace("\"", "");
            }
        }
        return null;
    }

    private void setResultMessage(LogicActionResult addGenreResult, Locale locale) {
        switch (addGenreResult.getResult()) {
            case FAILURE_ADD_TRACK:
                addGenreResult.setMessage(MessageManager.getProperty(CommandConstants.MESSAGE_FAILURE_ADD_TRACK, locale));
            case SUCCESS_ADD_TRACK:
                addGenreResult.setMessage(MessageManager.getProperty(CommandConstants.MESSAGE_SUCCESS_ADD_TRACK, locale));
                break;
            default:
        }
    }
}
