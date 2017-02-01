package by.suboch.logic;

import by.suboch.ajax.BiTuple;
import by.suboch.dao.CommentDAO;
import by.suboch.database.ConnectionPool;
import by.suboch.entity.Comment;
import by.suboch.exception.DAOException;
import by.suboch.exception.LogicException;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

/**
 *
 */
public class CommentLogic {
    public BiTuple<LogicActionResult, Integer> createComment(int trackId, int accountId, String text) throws LogicException {
        try (Connection connection = ConnectionPool.getInstance().getConnection()) {
            CommentDAO commentDAO = new CommentDAO(connection);
            int commentId = commentDAO.addNewComment(trackId, accountId, text);
            LogicActionResult logicActionResult = new LogicActionResult();
            logicActionResult.setState(LogicActionResult.State.SUCCESS);
            logicActionResult.setResult(ActionResult.SUCCESS_ADD_COMMENT);
            return new BiTuple<>(logicActionResult, commentId);
        } catch (SQLException | DAOException e) {
            throw new LogicException("Error while creating comment in logic.", e);
        }
    }

    public Comment loadComment(int commentId) throws LogicException {
        try (Connection connection = ConnectionPool.getInstance().getConnection()) {
            CommentDAO commentDAO = new CommentDAO(connection);
            return commentDAO.loadComment(commentId);
        } catch (SQLException | DAOException e) {
            throw new LogicException("Error while loading comment in logic.", e);
        }
    }
    public List<Comment> loadAllTrackComments(int trackId) throws LogicException {
        try (Connection connection = ConnectionPool.getInstance().getConnection()) {
            CommentDAO commentDAO = new CommentDAO(connection);
            return commentDAO.loadAllTrackComments(trackId);
        } catch (SQLException | DAOException e) {
            throw new LogicException("Error while loading comment in logic.", e);
        }
    }

    public List<Comment> loadAllComments() throws LogicException {
        try (Connection connection = ConnectionPool.getInstance().getConnection()) {
            CommentDAO commentDAO = new CommentDAO(connection);
            return commentDAO.loadAllComments();
        } catch (SQLException | DAOException e) {
            throw new LogicException("Error while loading comment in logic.", e);
        }
    }
}

