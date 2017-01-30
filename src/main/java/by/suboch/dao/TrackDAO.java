package by.suboch.dao;

import by.suboch.entity.Track;
import by.suboch.exception.DAOException;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

/**
 *
 */
public class TrackDAO {
    private Connection connection;


    private static final String SQL_ADD_TRACK = "INSERT INTO `tracks` (`track_title`, `track_price`, `track_location`, `genres_id`) " +
            "VALUES (?, ?, ?, ?)";
    private static final String SQL_LOAD_POPULAR_TRACKS = "SELECT * FROM `tracks` LIMIT ?,?";
    private static final String SQL_LOAD_ALL_TRACKS = "SELECT * FROM `tracks` ORDER BY `track_title`";
    private static final String SQL_LOAD_TRACK_BY_ID = "SELECT * FROM `tracks` WHERE `track_id` = ?";
    private static final String SQL_UPDATE_ALBUM_ID = "UPDATE `tracks` SET `album_id` = ? WHERE `track_id` = ?";

    private static final String COLUMN_TRACK_ID = "track_id";
    private static final String COLUMN_ALBUM_ID = "album_id";
    private static final String COLUMN_GENRE_ID = "genres_id";
    private static final String COLUMN_TRACK_TITLE = "track_title";
    private static final String COLUMN_TRACK_PRICE = "track_price";
    private static final String COLUMN_TRACK_DISCOUNT = "track_discount";
    private static final String COLUMN_TRACK_LOCATION = "track_location";

    public TrackDAO(Connection connection) {
        this.connection = connection;
    }

    public void addNewTrack(String title, String location, double price, int genreId) throws DAOException {
        try (PreparedStatement preparedStatement = connection.prepareStatement(SQL_ADD_TRACK)) {
            preparedStatement.setString(1, title);
            preparedStatement.setDouble(2, price);
            preparedStatement.setString(3, location);
            preparedStatement.setInt(4, genreId);
            preparedStatement.execute();
        } catch (SQLException e) {
            throw new DAOException("Error while inserting new track into database.", e);
        }
    }

    public List<Track> loadPopularTracks(int startRecord, int recordsPerPage) throws DAOException {
        try (PreparedStatement preparedStatement = connection.prepareStatement(SQL_LOAD_POPULAR_TRACKS)) {
            preparedStatement.setInt(1, startRecord);
            preparedStatement.setInt(2, recordsPerPage);
            ResultSet resultSet = preparedStatement.executeQuery();
            List<Track> trackList = new LinkedList<>();

            while (resultSet.next()) {
                Track track = new Track();
                track.setTrackId(resultSet.getInt(COLUMN_TRACK_ID));
                track.setAlbumId(resultSet.getInt(COLUMN_ALBUM_ID));
                track.setGenreId(resultSet.getInt(COLUMN_GENRE_ID));
                track.setTitle(resultSet.getString(COLUMN_TRACK_TITLE));
                track.setLocation(resultSet.getString(COLUMN_TRACK_LOCATION));
                track.setPrice(resultSet.getDouble(COLUMN_TRACK_PRICE));
                track.setDiscount(resultSet.getShort(COLUMN_TRACK_DISCOUNT));
                //track.setImage(albumDAO.findImage(resultSet.getInt(COLUMN_ALBUM_ID)));
                trackList.add(track);
            }

            return trackList;
        } catch (SQLException e) {
            throw new DAOException("Error while selecting popular tracks from database.", e);
        }
    }

    public List<Track> loadAllTracks() throws DAOException {
        try (PreparedStatement preparedStatement = connection.prepareStatement(SQL_LOAD_ALL_TRACKS)) {
            ResultSet resultSet = preparedStatement.executeQuery();
            List<Track> trackList = new LinkedList<>();

            while (resultSet.next()) {
                Track track = new Track();
                track.setTrackId(resultSet.getInt(COLUMN_TRACK_ID));
                track.setAlbumId(resultSet.getInt(COLUMN_ALBUM_ID));
                track.setGenreId(resultSet.getInt(COLUMN_GENRE_ID));
                track.setTitle(resultSet.getString(COLUMN_TRACK_TITLE));
                track.setLocation(resultSet.getString(COLUMN_TRACK_LOCATION));
                track.setPrice(resultSet.getDouble(COLUMN_TRACK_PRICE));
                track.setDiscount(resultSet.getShort(COLUMN_TRACK_DISCOUNT));
                trackList.add(track);
            }

            return trackList;
        } catch (SQLException e) {
            throw new DAOException("Error while selecting popular tracks from database.", e);
        }
    }

    public Track findTrackById(int trackId) throws DAOException {
        try (PreparedStatement preparedStatement = connection.prepareStatement(SQL_LOAD_TRACK_BY_ID)) {
            preparedStatement.setInt(1, trackId);
            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                Track track = new Track();
                track.setTrackId(resultSet.getInt(COLUMN_TRACK_ID));
                track.setAlbumId(resultSet.getInt(COLUMN_ALBUM_ID));
                track.setGenreId(resultSet.getInt(COLUMN_GENRE_ID));
                track.setTitle(resultSet.getString(COLUMN_TRACK_TITLE));
                track.setLocation(resultSet.getString(COLUMN_TRACK_LOCATION));
                track.setPrice(resultSet.getDouble(COLUMN_TRACK_PRICE));
                track.setDiscount(resultSet.getShort(COLUMN_TRACK_DISCOUNT));
                return track;
            } else {
                throw new DAOException("No track with such id found in database.");
            }
        } catch (SQLException e) {
            throw new DAOException("Error while selecting track from database.", e);
        }

    }

    public void updateAlbumId(String[] trackIds, int albumId) throws DAOException {
        try (PreparedStatement preparedStatement = connection.prepareStatement(SQL_UPDATE_ALBUM_ID)) {
            for (int i = 0; i < trackIds.length; i++) {
                preparedStatement.setInt(1, albumId);
                preparedStatement.setInt(2, Integer.parseInt(trackIds[i]));
                preparedStatement.execute();
            }
        } catch (SQLException e) {
            throw new DAOException("Error while updating album id for tracks in database.");
        }
    }
}
