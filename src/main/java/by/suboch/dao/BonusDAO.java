package by.suboch.dao;

import by.suboch.exception.DAOException;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 */
public class BonusDAO {

    private Connection connection;
    private static final String SQL_ADD_BONUS = "INSERT INTO `bonuses` (`price`, `discount`) VALUES (?, ?)";
    private static final String SQL_CHECK_BONUS = "SELECT `price` FROM `bonuses` WHERE `price` = ?";

    public BonusDAO(Connection connection) {
        this.connection = connection;
    }

    public boolean checkBonus(String price) throws DAOException {
        try (PreparedStatement preparedStatement = connection.prepareStatement(SQL_CHECK_BONUS)) {
            preparedStatement.setString(1, price);
            ResultSet resultSet = preparedStatement.executeQuery();
            return !resultSet.next();
        } catch (SQLException e) {
            throw new DAOException("Error while checking bonus in database.", e);
        }
    }

    public void addBonus(String price, String discount) throws DAOException {
        try (PreparedStatement preparedStatement = connection.prepareStatement(SQL_ADD_BONUS)) {
            preparedStatement.setString(1, price);
            preparedStatement.setString(2, discount);
            preparedStatement.execute();
        } catch (SQLException e) {
            throw new DAOException("Error while inserting new bonus into database.", e);
        }
    }
}
