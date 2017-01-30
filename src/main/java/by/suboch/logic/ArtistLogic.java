package by.suboch.logic;

import by.suboch.dao.ArtistDAO;
import by.suboch.database.ConnectionPool;
import by.suboch.entity.Album;
import by.suboch.entity.Artist;
import by.suboch.exception.DAOException;
import by.suboch.exception.LogicException;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

/**
 *
 */
public class ArtistLogic {
    public LogicActionResult addArtist(String name, String country, String description, byte[] image) throws LogicException {
        try (Connection connection = ConnectionPool.getInstance().getConnection()) {
            ArtistDAO artistDAO = new ArtistDAO(connection);
            LogicActionResult actionResult = new LogicActionResult();

            if (artistDAO.checkArtist(name, country)) {
                artistDAO.addNewArtist(name, country, description, image);
                actionResult.setState(LogicActionResult.State.SUCCESS);
                actionResult.addOutcome(ResultConstants.SUCCESS_ADD_ALBUM);
            } else {
                actionResult.setState(LogicActionResult.State.FAILURE);
                actionResult.addOutcome(ResultConstants.FAILURE_ALBUM_NOT_UNIQUE);
            }
            return actionResult;
        } catch (SQLException | DAOException e) {
            throw new LogicException("Error while creating artist in logic.", e);
        }
    }

    public int loadArtistId(String name, String country) throws LogicException {
        try (Connection connection = ConnectionPool.getInstance().getConnection()) {
            ArtistDAO artistDAO = new ArtistDAO(connection);
            return artistDAO.findArtistId(name, country);
        } catch (SQLException | DAOException e) {
            throw new LogicException("Error while loading album id in logic.", e);
        }
    }

    public Artist loadArtistById(int artistId) throws LogicException {
        try (Connection connection = ConnectionPool.getInstance().getConnection()) {
            ArtistDAO artistDAO = new ArtistDAO(connection);
            return artistDAO.findArtistById(artistId);
        } catch (SQLException | DAOException e) {
            throw new LogicException("Error while loading album id in logic.", e);
        }
    }

    public List<Artist> loadAllArtists() throws LogicException {
        try(Connection connection = ConnectionPool.getInstance().getConnection()) {
            ArtistDAO artistDAO = new ArtistDAO(connection);
            return artistDAO.loadAllArtists();
        } catch (SQLException | DAOException e) {
            throw new LogicException("Error while loading all artists in logic.", e);
        }
    }

    public byte[] loadImage(int albumId) throws LogicException {
        try (Connection connection = ConnectionPool.getInstance().getConnection()) {
            ArtistDAO artistDAO = new ArtistDAO(connection);
            return artistDAO.findImage(albumId);
        } catch (SQLException | DAOException e) {
            throw new LogicException("Error while loading image.", e);
        }
    }
}
