package com.suboch.command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 */
public class EmptyCommand implements IServletCommand {
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        return "";
    }
}
