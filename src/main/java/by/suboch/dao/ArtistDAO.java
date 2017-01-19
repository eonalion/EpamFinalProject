package by.suboch.dao;

import by.suboch.exception.DAOException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 */
public class ArtistDAO {

    private Connection connection;
    private static final Logger LOG = LogManager.getLogger();
    private static final String ADD_ARTIST = "INSERT INTO `artists` (`artist_name`, `country`, `career_start`, `career_end`, `description`) " +
            "VALUES (?, ?, ?, ?, ?)";
    private static final String CHECK_ARTIST = "SELECT * FROM `artists` WHERE `artist_name` = ? AND `country` = ? AND `career_start` = ? AND `career_end` = ?";


    public ArtistDAO(Connection connection) {
        this.connection = connection;
    }

    public void addNewArtist(String name, String country, String careerStart, String careerEnd, String description) throws DAOException {
        try (PreparedStatement preparedStatement = connection.prepareStatement(ADD_ARTIST)) {
            preparedStatement.setString(1, name);
            preparedStatement.setString(2, country);
            preparedStatement.setString(3, careerStart);
            preparedStatement.setString(4, careerEnd);
            preparedStatement.setString(5, description);
            preparedStatement.execute();
        } catch (SQLException e) {
            throw new DAOException("Error while adding new artist to database.", e);
        }
    }


    public boolean checkArtist(String name, String country, String careerStart, String careerEnd) throws DAOException {
        try (PreparedStatement preparedStatement = connection.prepareStatement(CHECK_ARTIST)) {
            preparedStatement.setString(1, name);
            preparedStatement.setString(2, country);
            preparedStatement.setString(3, careerStart);
            preparedStatement.setString(4, careerEnd);
            ResultSet resultSet = preparedStatement.executeQuery();
            return !resultSet.next();
        } catch (SQLException e) {
            throw new DAOException("Error while checking artist in database.", e);
        }
    }
}
