package by.suboch.dao;

import by.suboch.entity.Genre;
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
public class GenreDAO {
    private Connection connection;
    private static final String SQL_LOAD_ALL_GENRES = "SELECT * FROM `genres`";
    private static final String COLUMN_GENRE_ID = "genre_id";
    private static final String COLUMN_GENRE_NAME = "genre_name";

    public GenreDAO(Connection connection) {
        this.connection = connection;
    }

    public List<Genre> loadAllGenres() throws DAOException {
        try (PreparedStatement preparedStatement = connection.prepareStatement(SQL_LOAD_ALL_GENRES)) {
            ResultSet resultSet = preparedStatement.executeQuery();
            List<Genre> genreList = new LinkedList<>();

            while (resultSet.next()) {
                Genre genre = new Genre();
                genre.setGenreId(resultSet.getInt(COLUMN_GENRE_ID));
                genre.setName(resultSet.getString(COLUMN_GENRE_NAME));
                genreList.add(genre);
            }
            return genreList;
        } catch (SQLException e) {
            throw new DAOException("Error while selecting all genres from database.", e);
        }
    }
}
