package by.suboch.logic;

import by.suboch.dao.AlbumDAO;
import by.suboch.database.ConnectionPool;
import by.suboch.exception.DAOException;
import by.suboch.exception.LogicException;

import java.sql.Blob;
import java.sql.Connection;
import java.sql.SQLException;
import java.time.LocalDate;

/**
 *
 */
public class AlbumLogic {
    public LogicActionResult addAlbum(String title, LocalDate releaseDate) throws LogicException {
        try (Connection connection = ConnectionPool.getInstance().getConnection()) {
            AlbumDAO albumDAO = new AlbumDAO(connection);
            LogicActionResult actionResult = new LogicActionResult();
            if (albumDAO.checkAlbum(title, releaseDate)) {
                albumDAO.addNewAlbum(title, releaseDate);
                actionResult.setState(LogicActionResult.State.SUCCESS);
                actionResult.addOutcome(ResultConstants.SUCCESS_ADD_ALBUM);
                //return true;
            } else {
                actionResult.setState(LogicActionResult.State.FAILURE);
                actionResult.addOutcome(ResultConstants.FAILURE_ALBUM_NOT_UNIQUE);
            }
            return actionResult;
        } catch (SQLException | DAOException e) {
            throw new LogicException("Error while creating album in logic.", e);
        }
    }

    public byte[] loadImage(int albumId) throws LogicException {
        try (Connection connection = ConnectionPool.getInstance().getConnection()) {
            AlbumDAO albumDAO = new AlbumDAO(connection);
            return albumDAO.findImage(albumId);
        } catch (SQLException | DAOException e) {
            throw new LogicException("Error while loading image.", e);
        }
    }
}
