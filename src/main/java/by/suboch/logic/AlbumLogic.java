package by.suboch.logic;

import by.suboch.dao.AlbumDAO;
import by.suboch.database.ConnectionPool;
import by.suboch.exception.DAOException;
import by.suboch.exception.LogicException;

import java.sql.Connection;
import java.sql.SQLException;
import java.time.LocalDate;

/**
 *
 */
public class AlbumLogic {
    public boolean addAlbum(String title, LocalDate releaseDate) throws LogicException {
        try (Connection connection = ConnectionPool.getInstance().getConnection()) {
            AlbumDAO albumDAO = new AlbumDAO(connection);
            if (albumDAO.checkAlbum(title, releaseDate)) {
                albumDAO.addNewAlbum(title, releaseDate);
                return true;
            } else {
                return false;
            }
        } catch (SQLException | DAOException e) {
            throw new LogicException("Error while creating album in logic.", e);
        }
    }
}
