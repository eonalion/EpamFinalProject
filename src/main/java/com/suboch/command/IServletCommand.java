package com.suboch.command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 */
public interface IServletCommand {
    void execute(HttpServletRequest request, HttpServletResponse response);
}
