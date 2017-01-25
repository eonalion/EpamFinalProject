package by.suboch.logic;

import by.suboch.dao.TrackDAO;
import by.suboch.database.ConnectionPool;
import by.suboch.entity.Track;
import by.suboch.exception.DAOException;
import by.suboch.exception.LogicException;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

/**
 *
 */
public class TrackLogic {
    public List<Track> showPopularTracks(int page, int recordsPerPage) throws LogicException {
        try (Connection connection = ConnectionPool.getInstance().getConnection()) {
            TrackDAO trackDAO = new TrackDAO(connection);

            return trackDAO.loadPopularTracks(page, recordsPerPage);
        } catch (SQLException | DAOException e) {
            throw new LogicException(".", e);
        }
    }
}

