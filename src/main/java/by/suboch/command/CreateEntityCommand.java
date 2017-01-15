package by.suboch.command;

import by.suboch.manager.ConfigurationManager;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 */
public class CreateEntityCommand implements IServletCommand {
    private static final String CREATE_ENTITY_PAGE = "path.page.addNew";

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        return ConfigurationManager.getProperty(CREATE_ENTITY_PAGE);
    }
}
