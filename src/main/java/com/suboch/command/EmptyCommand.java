package com.suboch.command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 */
public class EmptyCommand implements IServletCommand {
    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) {

    }
}
