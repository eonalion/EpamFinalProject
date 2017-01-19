package by.suboch.util;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;

/**
 *
 */
public class DefaultContentLoader {
    private static final String DEFAULT_AVATAR_PATH = "/media/win_d/im/EPAM/EpamFinalProject/src/main/webapp/images/default_avatar.jpg";

   /* public static byte[] loadDefaultAvatar() {
        //TODO: Make array a constant.
        BufferedImage originalImage = null;
        byte[] imageInByte = null;
        try {
            originalImage = ImageIO.read(new File(DEFAULT_AVATAR_PATH));
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            ImageIO.write(originalImage, "jpeg", baos);
            baos.flush();
            imageInByte = baos.toByteArray();
            baos.close();
        } catch (IOException e) {
            //TODO: Handle exception.
        }
        return imageInByte;
    }*/
}
