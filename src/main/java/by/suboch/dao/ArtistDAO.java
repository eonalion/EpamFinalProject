package by.suboch.dao;

import by.suboch.exception.DAOException;

import java.io.ByteArrayInputStream;
import java.sql.*;

/**
 *
 */
public class ArtistDAO {
    private Connection connection;
    private static final String SQL_ADD_ARTIST = "INSERT INTO `artists` (`artist_name`, `country`, `description`, `artist_image`) " +
            "VALUES (?, ?, ?, ?)";
    private static final String SQL_CHECK_ARTIST = "SELECT * FROM `artists` WHERE `artist_name` = ? AND `country` = ?";
    private static final String SQL_FIND_ARTIST_ID = "SELECT `artist_id` FROM `artists` WHERE `artist_name` = ? AND `country` = ?";
    private static final String SQL_LOAD_IMAGE = "SELECT `artist_image` FROM `artists` WHERE `artist_id` = ?";

    private static final String COLUMN_IMAGE = "artist_image";
    private static final String COLUMN_ARTIST_ID = "artist_id";


    public ArtistDAO(Connection connection) {
        this.connection = connection;
    }

    public void addNewArtist(String name, String country, String description, byte[] image) throws DAOException {
        try (PreparedStatement preparedStatement = connection.prepareStatement(SQL_ADD_ARTIST)) {
            preparedStatement.setString(1, name);
            preparedStatement.setString(2, country);
            preparedStatement.setString(3, description);
            if (image != null) {
                preparedStatement.setBlob(4, new ByteArrayInputStream(image));
            } else {
                preparedStatement.setBlob(4, (Blob) null);
            }
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

    public int findArtistId(String name, String country) throws DAOException {
        try (PreparedStatement preparedStatement = connection.prepareStatement(SQL_FIND_ARTIST_ID)) {
            preparedStatement.setString(1, name);
            preparedStatement.setString(2, country);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                return resultSet.getInt(COLUMN_ARTIST_ID);
            } else {
                throw new DAOException("No artist with such name and country found in database.");
            }

        } catch (SQLException e) {
            throw new DAOException("Error while searching for artist by name and country in database.", e);
        }
    }

    public byte[] findImage(int artistId) throws DAOException {
        try (PreparedStatement preparedStatement = connection.prepareStatement(SQL_LOAD_IMAGE)) {
            preparedStatement.setInt(1, artistId);
            ResultSet resultSet = preparedStatement.executeQuery();
            byte[] image = null;
            while(resultSet.next()) {
                image = resultSet.getBytes(COLUMN_IMAGE);
            }
            return image;
        } catch (SQLException e) {
            throw new DAOException("Error while searching for artist image in database.");
        }
    }
}
