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
    public LogicActionResult addTrack(String title, String location, double price, int genreId) throws LogicException {
        try (Connection connection = ConnectionPool.getInstance().getConnection()) {
            TrackDAO trackDAO = new TrackDAO(connection);
            LogicActionResult actionResult = new LogicActionResult();
            trackDAO.addNewTrack(title, location, price, genreId);
            actionResult.setState(LogicActionResult.State.SUCCESS);
            actionResult.addOutcome(ResultConstants.SUCCESS_ADD_ALBUM);
            return actionResult;
        } catch (SQLException | DAOException e) {
            throw new LogicException("Error while creating track in logic.", e);
        }
    }

    public List<Track> loadPopularTracks(int page, int recordsPerPage) throws LogicException {
        try (Connection connection = ConnectionPool.getInstance().getConnection()) {
            TrackDAO trackDAO = new TrackDAO(connection);

            return trackDAO.loadPopularTracks(page, recordsPerPage);
        } catch (SQLException | DAOException e) {
            throw new LogicException("Error while loading popular tracks in logic.", e);
        }
    }

    public List<Track> loadAllTracks() throws LogicException {
        try (Connection connection = ConnectionPool.getInstance().getConnection()) {
            TrackDAO trackDAO = new TrackDAO(connection);
            return trackDAO.loadAllTracks();
        } catch (SQLException | DAOException e) {
            throw new LogicException("Error while loading all tracks in logic.", e);
        }
    }

    public Track loadTrackById(int trackId) throws LogicException {
        try (Connection connection = ConnectionPool.getInstance().getConnection()) {
            TrackDAO trackDAO = new TrackDAO(connection);
            return trackDAO.findTrackById(trackId);
        } catch (SQLException | DAOException e) {
            throw new LogicException("Error while loading all tracks in logic.", e);
        }
    }

    public void setAlbumId(String[] trackIds, int albumId) throws LogicException {
        try (Connection connection = ConnectionPool.getInstance().getConnection()) {
            TrackDAO trackDAO = new TrackDAO(connection);
            trackDAO.updateAlbumId(trackIds, albumId);
        } catch (SQLException | DAOException e) {
            throw new LogicException(e);
        }
    }
}

