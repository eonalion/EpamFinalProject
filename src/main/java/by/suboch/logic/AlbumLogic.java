package by.suboch.logic;

import by.suboch.dao.AlbumDAO;
import by.suboch.database.ConnectionPool;
import by.suboch.entity.Album;
import by.suboch.exception.DAOException;
import by.suboch.exception.LogicException;

import java.sql.Blob;
import java.sql.Connection;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;

/**
 *
 */
public class AlbumLogic {
    public LogicActionResult addAlbum(String title, LocalDate releaseDate, byte[] image) throws LogicException {
        try (Connection connection = ConnectionPool.getInstance().getConnection()) {
            AlbumDAO albumDAO = new AlbumDAO(connection);
            LogicActionResult actionResult = new LogicActionResult();
            if (albumDAO.checkAlbum(title, releaseDate)) {
                albumDAO.addNewAlbum(title, releaseDate, image);
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

    public Album loadAlbum(String title, LocalDate releaseDate) throws LogicException {
        try (Connection connection = ConnectionPool.getInstance().getConnection()) {
            AlbumDAO albumDAO = new AlbumDAO(connection);
            return albumDAO.findAlbum(title, releaseDate);
        } catch (SQLException | DAOException e) {
            throw new LogicException("Error while loading album in logic.", e);
        }
    }

    public int loadAlbumId(String title, LocalDate releaseDate) throws LogicException {
        try (Connection connection = ConnectionPool.getInstance().getConnection()) {
            AlbumDAO albumDAO = new AlbumDAO(connection);
            return albumDAO.findAlbumId(title, releaseDate);
        } catch (SQLException | DAOException e) {
            throw new LogicException("Error while loading album id in logic.", e);
        }
    }

    public List<Album> loadAllAlbums() throws LogicException {
        try(Connection connection = ConnectionPool.getInstance().getConnection()) {
            AlbumDAO albumDAO = new AlbumDAO(connection);
            return albumDAO.loadAllAlbums();
        } catch (SQLException | DAOException e) {
            throw new LogicException("Error while loading all albums in logic.", e);
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

    public void setArtistId(String[] albumIds, int artistId) throws LogicException {
        try (Connection connection = ConnectionPool.getInstance().getConnection()) {
            AlbumDAO albumDAO = new AlbumDAO(connection);
            albumDAO.updateArtistId(albumIds, artistId);
        } catch (SQLException | DAOException e) {
            throw new LogicException(e);
        }
    }
}
