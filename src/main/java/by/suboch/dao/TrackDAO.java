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

    private static final String SQL_LOAD_TRACKS = "SELECT * FROM `tracks` LIMIT ?,?";

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

    public List<Track> loadPopularTracks(int startRecord, int recordsPerPage) throws DAOException {
        try (PreparedStatement preparedStatement = connection.prepareStatement(SQL_LOAD_TRACKS)) {
            preparedStatement.setInt(1, startRecord);
            preparedStatement.setInt(2, recordsPerPage);
            ResultSet resultSet = preparedStatement.executeQuery();
            List<Track> trackList = new LinkedList<>();
            //AlbumDAO albumDAO = new AlbumDAO(connection);

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
}
