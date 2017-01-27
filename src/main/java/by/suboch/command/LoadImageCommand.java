package by.suboch.command;

import by.suboch.controller.ControllerConfig;
import by.suboch.controller.ControllerConstants;
import by.suboch.entity.Account;
import by.suboch.exception.LogicException;
import by.suboch.logic.AlbumLogic;
import by.suboch.logic.ArtistLogic;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.Base64;

import static com.sun.xml.internal.org.jvnet.fastinfoset.EncodingAlgorithmIndexes.BASE64;

/**
 *
 */
public class LoadImageCommand implements IServletCommand {
    private static final String PARAM_ELEMENT_ID = "elementId";
    private static final String PARAM_TARGET = "target";
    private static final String ALBUM_IMAGE = "album";
    private static final String ARTIST_IMAGE = "artist";
    private static final String ACCOUNT_IMAGE = "account";

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws IOException {
        ControllerConfig controllerConfig = (ControllerConfig) request.getSession().getAttribute(ControllerConstants.CONTROLLER_CONFIG_KEY);
        Account account = (Account) request.getSession().getAttribute(CommandConstants.ATTR_ACCOUNT);

        int elementId = Integer.parseInt(request.getParameter(PARAM_ELEMENT_ID));
        String imageTarget = request.getParameter(PARAM_TARGET);
        byte[] image = null;

        try {
            switch (imageTarget.toLowerCase()) {
                case ALBUM_IMAGE:
                    AlbumLogic albumLogic = new AlbumLogic();
                    image = albumLogic.loadImage(elementId);
                    break;
                case ARTIST_IMAGE:
                    ArtistLogic artistLogic = new ArtistLogic();
                    image = artistLogic.loadImage(elementId);
                    break;
                case ACCOUNT_IMAGE:
                    image = account.getAvatar();
                    break;
            }
            response.setContentType(CommandConstants.MIME_TYPE_IMAGE_JPG);
            controllerConfig.setState(ControllerConfig.State.RESPONSE);
        } catch (LogicException e) {
                //TODO: handle exception.
        }
        return Base64.getEncoder().encodeToString(image);
    }
}
