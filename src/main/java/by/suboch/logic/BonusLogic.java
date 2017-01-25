package by.suboch.logic;

import by.suboch.dao.BonusDAO;
import by.suboch.database.ConnectionPool;
import by.suboch.exception.DAOException;
import by.suboch.exception.LogicException;

import java.sql.Connection;
import java.sql.SQLException;

/**
 *
 */
public class BonusLogic {
    public boolean createBonus(String price, String discount) throws LogicException {
        try (Connection connection = ConnectionPool.getInstance().getConnection()) {
            BonusDAO bonusDAO = new BonusDAO(connection);
            if (bonusDAO.checkBonus(price)) {
                bonusDAO.addBonus(price, discount);
                return true;
            } else {
                return false;
            }
        } catch (SQLException | DAOException e) {
            throw new LogicException("Error while creating bonus in logic.", e);
        }
    }
}
