package by.suboch.command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 *
 */
public interface IServletCommand {
    String execute(HttpServletRequest request, HttpServletResponse response) throws IOException;
}
