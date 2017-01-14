package by.suboch.command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 */
public interface IServletCommand {
    String execute(HttpServletRequest request, HttpServletResponse response);
}
