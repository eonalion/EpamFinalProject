package by.suboch.logic;

import by.suboch.dao.GenreDAO;
import by.suboch.database.ConnectionPool;
import by.suboch.entity.Genre;
import by.suboch.exception.DAOException;
import by.suboch.exception.LogicException;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

/**
 *
 */
public class GenreLogic {
    public List<Genre> loadAllGenres() throws LogicException {
        try(Connection connection = ConnectionPool.getInstance().getConnection()) {
            GenreDAO genreDAO = new GenreDAO(connection);
            return genreDAO.loadAllGenres();
        } catch (SQLException | DAOException e) {
            throw new LogicException("Error while loading all genres in logic.", e);
        }
    }
}
