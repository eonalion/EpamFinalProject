package by.suboch.command;

import by.suboch.controller.ControllerConfig;
import by.suboch.controller.ControllerConstants;
import by.suboch.entity.Account;
import by.suboch.manager.ConfigurationManager;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Base64;

/**
 *
 */
public class LoadTrackCommand implements IServletCommand {
    private static final String PARAM_TRACK_ID = "trackId";
    private static final String PARAM_TRACK_LOCATION = "location";

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        ControllerConfig controllerConfig = (ControllerConfig) request.getSession().getAttribute(ControllerConstants.CONTROLLER_CONFIG_KEY);
        String trackId = request.getParameter(PARAM_TRACK_ID);
        String location = request.getParameter(PARAM_TRACK_LOCATION);

        int fileSize;
        byte[] trackBytes = null;
        try {
            Path trackPath = Paths.get(ConfigurationManager.getProperty(CommandConstants.RESOURCE_AUDIO), location);
            File trackFile = trackPath.toFile();
            BufferedInputStream in = new BufferedInputStream(new FileInputStream(trackFile));
            fileSize = (int) trackFile.length();

            if (fileSize != 0) {
                trackBytes = new byte[fileSize];
                in.read(trackBytes, 0, fileSize);
            }

            response.setContentType(CommandConstants.MIME_TYPE_AUDIO_MP3);
            controllerConfig.setState(ControllerConfig.State.RESPONSE);
        } catch (IOException e) {
            //TODO: Handle exception;
        }

        return Base64.getEncoder().encodeToString(trackBytes);
    }
}
