package by.suboch.dao;

import by.suboch.exception.DAOException;

import java.sql.*;
import java.time.LocalDate;

/**
 *
 */
public class AlbumDAO {
    private Connection connection;
    private static final String SQL_ADD_ALBUM = "INSERT INTO `albums` (`album_title`, `release_date`  /*`album_image`,*/) " +
            "VALUES (?, ?)";
    private static final String SQL_CHECK_ALBUM = "SELECT * FROM `albums` WHERE `album_title` = ? AND `release_date` = ?";
    private static final String SQL_LOAD_IMAGE = "SELECT `image` FROM `albums` WHERE `album_id` = ?";

    private static final String COLUMN_IMAGE = "image";

    private static final int INDEX_START = 0;

    public AlbumDAO(Connection connection) {
        this.connection = connection;
    }

    public void addNewAlbum(String title, LocalDate releaseDate) throws DAOException {
        try (PreparedStatement preparedStatement = connection.prepareStatement(SQL_ADD_ALBUM)) {
            preparedStatement.setString(1, title);
            preparedStatement.setDate(2, Date.valueOf(releaseDate));/*
            preparedStatement.setBlob(4, new ByteArrayInputStream(image));*/
            preparedStatement.execute();
        } catch (SQLException e) {
            throw new DAOException("Error while inserting new album into database.", e);
        }
    }

    public boolean checkAlbum(String title, LocalDate releaseDate) throws DAOException {
        try (PreparedStatement preparedStatement = connection.prepareStatement(SQL_CHECK_ALBUM)) {
            preparedStatement.setString(1, title);
            preparedStatement.setDate(2, Date.valueOf(releaseDate));
            ResultSet resultSet = preparedStatement.executeQuery();
            return !resultSet.next();
        } catch (SQLException e) {
            throw new DAOException("Error while checking album in database.", e);
        }
    }

    public byte[] findImage(int albumId) throws DAOException {
        try (PreparedStatement preparedStatement = connection.prepareStatement(SQL_LOAD_IMAGE)) {
            preparedStatement.setInt(1, albumId);
            ResultSet resultSet = preparedStatement.executeQuery();
            Blob blob = resultSet.getBlob(COLUMN_IMAGE);
            return blob.getBytes(INDEX_START, (int) blob.length());
        } catch (SQLException e) {
            throw new DAOException("Error while searching for album image in database.");
        }
    }
}
