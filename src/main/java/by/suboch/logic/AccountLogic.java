package by.suboch.logic;

import by.suboch.dao.AccountDAO;
import by.suboch.database.ConnectionPool;
import by.suboch.database.ProxyConnection;
import by.suboch.exception.DAOException;
import by.suboch.exception.LogicException;
import by.suboch.validator.AccountValidator;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.SQLException;

/**
 *
 */

public class AccountLogic {
    private AccountValidator validator;
    private static final Logger LOG = LogManager.getLogger();

    public AccountLogic() {
        validator = new AccountValidator();
    }

    public boolean registerAccount(String firstName, String lastName, String login, String email, String password, String passwordConfirm) throws LogicException {
        try(ProxyConnection connection = ConnectionPool.getInstance().getConnection()) {
            AccountDAO accountDAO = new AccountDAO(connection);
            if (validator.validateRegistration(firstName, lastName, login, email, password, passwordConfirm) && accountDAO.checkLoginUniqueness(login) && accountDAO.checkEmailUniqueness(email)) {
                accountDAO.registerAccount(firstName, lastName, login, email, password);
                return true;
            } else {
                return false;
            }
        } catch (SQLException | DAOException e) {
            throw new LogicException("Error while account registration.", e);
        }
    }

    public boolean authorizeAccount(String authorizationName, String password) throws LogicException {
        try(ProxyConnection connection = ConnectionPool.getInstance().getConnection()) {
            AccountDAO accountDAO = new AccountDAO(connection);
            if (validator.validateAuthorization(authorizationName, password)) {
                return accountDAO.authorizeAccount(authorizationName, password);
            } else {
                return false;
            }
        } catch (SQLException | DAOException e) {
            throw new LogicException("Error while account authorization.", e);
        }
    }

    public boolean isAdmin(String authorizationName) throws LogicException {
        try(ProxyConnection connection = ConnectionPool.getInstance().getConnection()) {
            AccountDAO accountDAO = new AccountDAO(connection);
            return accountDAO.checkAdminRights(authorizationName);
        } catch (SQLException | DAOException e){
            throw new LogicException("Error while admin rights check in logic.", e);
        }
    }
}
