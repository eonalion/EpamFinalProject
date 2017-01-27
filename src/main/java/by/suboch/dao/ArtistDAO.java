package by.suboch.dao;

import by.suboch.exception.DAOException;

import java.sql.*;

/**
 *
 */
public class ArtistDAO {
    private Connection connection;
    private static final String SQL_ADD_ARTIST = "INSERT INTO `artists` (`artist_name`, `country`, `description`) " +
            "VALUES (?, ?, ?)";
    private static final String SQL_CHECK_ARTIST = "SELECT * FROM `artists` WHERE `artist_name` = ? AND `country` = ?";
    private static final String SQL_LOAD_IMAGE = "SELECT `image` FROM `artists` WHERE `artist_id` = ?";

    private static final String COLUMN_IMAGE = "image";


    public ArtistDAO(Connection connection) {
        this.connection = connection;
    }

    public void addNewArtist(String name, String country, String description) throws DAOException {
        try (PreparedStatement preparedStatement = connection.prepareStatement(SQL_ADD_ARTIST)) {
            preparedStatement.setString(1, name);
            preparedStatement.setString(2, country);
            preparedStatement.setString(3, description);
            preparedStatement.execute();
        } catch (SQLException e) {
            throw new DAOException("Error while inserting new artist into database.", e);
        }
    }

    public boolean checkArtist(String name, String country) throws DAOException {
        try (PreparedStatement preparedStatement = connection.prepareStatement(SQL_CHECK_ARTIST)) {
            preparedStatement.setString(1, name);
            preparedStatement.setString(2, country);
            ResultSet resultSet = preparedStatement.executeQuery();
            return !resultSet.next();
        } catch (SQLException e) {
            throw new DAOException("Error while checking artist in database.", e);
        }
    }

    public byte[] findImage(int artistId) throws DAOException {
        try(PreparedStatement preparedStatement = connection.prepareStatement(SQL_LOAD_IMAGE)) {
            preparedStatement.setInt(1, artistId);
            ResultSet resultSet = preparedStatement.executeQuery();
            Blob blob = resultSet.getBlob(COLUMN_IMAGE);
            return blob.getBytes(0, (int) blob.length());
        } catch (SQLException e) {
            throw new DAOException("Error while searching for artist image in database.");
        }
    }
}
