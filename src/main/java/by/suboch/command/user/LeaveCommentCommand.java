package by.suboch.command.user;

import by.suboch.ajax.AJAXState;
import by.suboch.ajax.BiTuple;
import by.suboch.command.AbstractServletCommand;
import by.suboch.command.CommandConstants;
import by.suboch.controller.ControllerConfiguration;
import by.suboch.controller.ControllerConstants;
import by.suboch.entity.Account;
import by.suboch.entity.Comment;
import by.suboch.entity.Track;
import by.suboch.entity.Visitor;
import by.suboch.exception.LogicException;
import by.suboch.logic.AccountLogic;
import by.suboch.logic.ActionResult;
import by.suboch.logic.CommentLogic;
import by.suboch.logic.LogicActionResult;
import by.suboch.manager.ConfigurationManager;
import by.suboch.manager.MessageManager;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Locale;

/**
 *
 */
public class LeaveCommentCommand extends AbstractServletCommand {
    private static final Logger LOG = LogManager.getLogger();
    private static final String PARAM_COMMENT = "comment";
    private static final String ATTR_CURRENT_TRACK = "currentTrack";

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws IOException {
        Account account = (Account) request.getSession().getAttribute(CommandConstants.ATTR_ACCOUNT);
        Visitor visitor = (Visitor) request.getSession().getAttribute(ControllerConstants.VISITOR_KEY);
        ControllerConfiguration controllerConfiguration = (ControllerConfiguration) request.getSession().getAttribute(ControllerConstants.CONTROLLER_CONFIG_KEY);
        String commentText = request.getParameter(PARAM_COMMENT);
        Track track = (Track) request.getSession().getAttribute(ATTR_CURRENT_TRACK);
        String resultData = "";

        CommentLogic commentLogic = new CommentLogic();
        AccountLogic accountLogic = new AccountLogic();
        if (controllerConfiguration.getState() != ControllerConfiguration.State.AJAX) {
            resultData = ConfigurationManager.getProperty(CommandConstants.PAGE_REGISTRATION);
            request.setAttribute(PARAM_COMMENT, commentText);
        } else if (commentText == null || commentText.isEmpty()) {
            LogicActionResult actionResult = new LogicActionResult();
            actionResult.setResult(ActionResult.FAILURE_EMPTY_COMMENT);
            setResultMessage(actionResult, visitor.getLocale());
            resultData = toJson(AJAXState.HANDLE, actionResult);
            response.setContentType(CommandConstants.MIME_TYPE_JSON);
        } else {
            try {
                BiTuple<LogicActionResult, Integer> result = commentLogic.createComment(track.getTrackId(), account.getAccountId(), commentText);
                LogicActionResult addCommentResult = result.getLeft();
                int commentId = result.getRight();
                if (addCommentResult.getState() == LogicActionResult.State.SUCCESS) {
                    Comment comment = commentLogic.loadComment(commentId);
                    Account author = accountLogic.loadAccount(comment.getAccountId());
                    BiTuple<Comment, Account> data = new BiTuple<>(comment, author);
                    resultData = toJson(AJAXState.OK, data);
                } else {
                    addCommentResult.setResult(ActionResult.FAILURE_ADD_COMMENT);
                    setResultMessage(addCommentResult, visitor.getLocale());
                    resultData = toJson(AJAXState.HANDLE, addCommentResult);
                }
                response.setContentType(CommandConstants.MIME_TYPE_JSON);
            } catch (LogicException e) {
                LOG.log(Level.ERROR, "Errors during posting comment.", e);
                resultData = handleDBError(e, request, response);
            }
        }
        return resultData;
    }

    private void setResultMessage(LogicActionResult addGenreResult, Locale locale) {
        switch (addGenreResult.getResult()) {
            case FAILURE_EMPTY_COMMENT:
                addGenreResult.setMessage(MessageManager.getProperty(CommandConstants.MESSAGE_FAILURE_EMPTY_COMMENT, locale));
                break;
            case FAILURE_ADD_COMMENT:
                addGenreResult.setMessage(MessageManager.getProperty(CommandConstants.MESSAGE_FAILURE_ADD_COMMENT, locale));
                break;
            default:
        }
    }
}
