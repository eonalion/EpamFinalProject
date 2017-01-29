package by.suboch.command;

import by.suboch.entity.Visitor;
import by.suboch.exception.LogicException;
import by.suboch.logic.LogicActionResult;
import by.suboch.logic.TrackLogic;
import by.suboch.manager.ConfigurationManager;
import by.suboch.manager.MessageManager;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import java.io.*;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.logging.Level;

import static by.suboch.command.CommandConstants.ATTR_MESSAGE;
import static by.suboch.command.CommandConstants.PAGE_ERROR;
import static by.suboch.controller.ControllerConstants.VISITOR_KEY;
import static com.sun.xml.internal.ws.spi.db.BindingContextFactory.LOGGER;

/**
 *
 */
public class AddTrackCommand implements IServletCommand {
    private static final String PARAM_TRACK_GENRE = "genre";
    private static final String PARAM_TRACK_PRICE = "price";
    private static final String PARAM_TRACK_FILE = "file";

    private static final String MESSAGE_ERROR_ADD_ALBUM = "message.track.error";

    private static final String SEPARATOR = "/";

    private static final int maxFileSize = 50 * 1024 * 1024;
    private static final int maxMemSize = 50 * 1024 * 1024;

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws IOException {
        Visitor visitor = (Visitor) request.getSession().getAttribute(VISITOR_KEY);
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
                filecontent = filePart.getInputStream();//TODO check returned value.
                while ((read = filecontent.read(track)) != -1) {
                    out.write(track, 0, read);
                }
            }
        } catch (IOException | ServletException e) {
            //TODO:Handle exception.
        } finally {
            if (out != null) {
                out.close();
            }
            if (filecontent != null) {
                filecontent.close();
            }
        }

        TrackLogic trackLogic = new TrackLogic();
        try {
            String location = relativePath + SEPARATOR + fileName;
            LogicActionResult actionResult = trackLogic.addTrack(fileName, location, price, genreId);

            if (actionResult.getState() == LogicActionResult.State.SUCCESS) {
                nextPage = "";//toJson(signUpResult);
                // TODO: Set success message.
            } else {
                // TODO: Set warn message(or it's already set?).
            }
            nextPage = visitor.getCurrentPage();

        } catch (LogicException e) {
            //TODO: Handle exception
            request.getSession().setAttribute(ATTR_MESSAGE, MessageManager.getProperty(MESSAGE_ERROR_ADD_ALBUM, visitor.getLocale()));
            nextPage = ConfigurationManager.getProperty(PAGE_ERROR);

        }
        return nextPage;
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
}
