package by.suboch.logic;

import by.suboch.dao.AccountDAO;
import by.suboch.database.ConnectionPool;
import by.suboch.entity.Account;
import by.suboch.exception.DAOException;
import by.suboch.exception.LogicException;
import by.suboch.validator.AccountValidator;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.Connection;
import java.sql.SQLException;
import java.time.temporal.ValueRange;

/**
 *
 */

public class AccountLogic {
    private static final Logger LOG = LogManager.getLogger();

    /*public enum ActionResult {
        SUCCESS {
        },
        FAILURE {

        };
    }*/

    public AccountLogic() {
    }

    public boolean registerAccount(String firstName, String lastName, String login, String email, String password, String passwordConfirm) throws LogicException {
        try (Connection connection = ConnectionPool.getInstance().getConnection()) {
            AccountDAO accountDAO = new AccountDAO(connection);
            if (AccountValidator.validateRegistration(login, email, password, passwordConfirm) &&
                    accountDAO.checkLoginUniqueness(login) &&//TODO: return something more useful.
                    accountDAO.checkEmailUniqueness(email)) {
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
        try (Connection connection = ConnectionPool.getInstance().getConnection()) {
            AccountDAO accountDAO = new AccountDAO(connection);
            if (AccountValidator.validateAuthorization(authorizationName, password)) {
                return accountDAO.authorizeAccount(authorizationName, password);
            } else {
                return false;
            }
        } catch (SQLException | DAOException e) {
            throw new LogicException("Error while account authorization.", e);
        }
    }

    public Account loadAccount(String authorizationName) throws LogicException {
        try (Connection connection = ConnectionPool.getInstance().getConnection()) {
            AccountDAO accountDAO = new AccountDAO(connection);
            return accountDAO.findByAuthorizationName(authorizationName);
        } catch (SQLException | DAOException e) {
            throw new LogicException("Error while account authorization.", e);
        }
    }

    public boolean changeName(int accountId, String firstName, String lastName) throws LogicException {
        try (Connection connection = ConnectionPool.getInstance().getConnection()) {
            AccountDAO accountDAO = new AccountDAO(connection);
            accountDAO.updateName(accountId, firstName, lastName);
            return true;
        } catch (SQLException | DAOException e) {
            throw new LogicException("Error while changing account first name and last name");
        }
    }

    public boolean changeLogin(int accountId, String login) throws LogicException {
        try (Connection connection = ConnectionPool.getInstance().getConnection()) {
            AccountDAO accountDAO = new AccountDAO(connection);
            if (AccountValidator.validateLogin(login) &&
                    accountDAO.checkLoginUniqueness(accountId, login)) {
                accountDAO.updateLogin(accountId, login);
                return true;
            } else {
                return false;
            }
        } catch (SQLException | DAOException e) {
            throw new LogicException("Error while changing account login.", e);
        }
    }

    public boolean changeEmail(int accountId, String email) throws LogicException {
        try (Connection connection = ConnectionPool.getInstance().getConnection()) {
            AccountDAO accountDAO = new AccountDAO(connection);
            if (AccountValidator.validateEmail(email) &&
                    accountDAO.checkEmailUniqueness(accountId, email)) {
                accountDAO.updateEmail(accountId, email);
                return true;
            } else {
                return false;
            }
        } catch (SQLException | DAOException e) {
            throw new LogicException("Error while changing account email.", e);
        }
    }

    public boolean changePassword(int accountId, String oldPassword, String newPassword, String newPasswordConfirm) throws LogicException {
        try (Connection connection = ConnectionPool.getInstance().getConnection()) {
            AccountDAO accountDAO = new AccountDAO(connection);
            if (AccountValidator.validatePassword(oldPassword) &&
                    accountDAO.checkPassword(accountId, oldPassword) &&
                    AccountValidator.validatePassword(newPassword) &&
                    !AccountValidator.checkPasswordsMatch(oldPassword, newPassword) &&
                    AccountValidator.checkPasswordsMatch(newPassword, newPasswordConfirm)) {
                accountDAO.updatePassword(accountId, newPassword);
                return true;
            } else {
                return false;
            }
        } catch (SQLException | DAOException e) {
            throw new LogicException("Error while changing account password.", e);
        }
    }

    public boolean changeAvatar(int accountId, byte[] avatar) throws LogicException {
        try (Connection connection = ConnectionPool.getInstance().getConnection()) {
            AccountDAO accountDAO = new AccountDAO(connection);
            accountDAO.updateAvatar(accountId, avatar);
            return true;
        } catch (SQLException | DAOException e) {
            throw new LogicException("Error while changing account avatar.", e);
        }
    }

    public boolean isAdmin(String authorizationName) throws LogicException {
        try (Connection connection = ConnectionPool.getInstance().getConnection()) {
            AccountDAO accountDAO = new AccountDAO(connection);
            return accountDAO.checkAdminRights(authorizationName);
        } catch (SQLException | DAOException e) {
            throw new LogicException("Error while admin rights check in logic.", e);
        }
    }
}
