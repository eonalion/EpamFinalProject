package by.suboch.logic;

import by.suboch.dao.ArtistDAO;
import by.suboch.database.ConnectionPool;
import by.suboch.exception.DAOException;
import by.suboch.exception.LogicException;

import java.sql.Connection;
import java.sql.SQLException;

/**
 *
 */
public class ArtistLogic {
    public boolean addArtist(String name, String country, String description) throws LogicException {
        try (Connection connection = ConnectionPool.getInstance().getConnection()) {
            ArtistDAO artistDAO = new ArtistDAO(connection);
            if (artistDAO.checkArtist(name, country)) {
                artistDAO.addNewArtist(name, country, description);
                return true;
            } else {
                return false;
            }
        } catch (SQLException | DAOException e) {
            throw new LogicException("Error while creating artist in logic.", e);
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
