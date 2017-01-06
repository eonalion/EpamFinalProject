package com.suboch.logic;

import com.suboch.dao.AccountDAO;
import com.suboch.database.ConnectionPool;
import com.suboch.database.ConnectionProxy;
import com.suboch.validator.AccountValidator;
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

    public boolean registerAccount(String firstName, String lastName, String login, String email, String password, String passwordConfirm) {
        try(ConnectionProxy connection = ConnectionPool.getInstance().getConnection()) {
            AccountDAO accountDAO = new AccountDAO(connection);
            if (validator.validateRegistration(firstName, lastName, login, email, password, passwordConfirm) && accountDAO.checkLoginUniqueness(login) && accountDAO.checkEmailUniqueness(email)) {
                accountDAO.registerAccount(firstName, lastName, login, email, password);
            } else {
                //TODO:
            }
        } catch (SQLException e) {
            LOG.error(e);
        }
        return false;
    }

    public boolean authorizeAccount(String authorizationName, String password) {
        try(ConnectionProxy connection = ConnectionPool.getInstance().getConnection()) {
            AccountDAO accountDAO = new AccountDAO(connection);
            if (validator.validateAuthorization(authorizationName, password)) {
                accountDAO.authorizeAccount(authorizationName, password);
            } else {
                //TODO:
            }
        } catch (SQLException e) {
            LOG.error(e);
        }
        return false;
    }

    public boolean isAdmin(String authorizationName) {
        try(ConnectionProxy connection = ConnectionPool.getInstance().getConnection()) {
            AccountDAO accountDAO = new AccountDAO(connection);
            return accountDAO.checkAdminRights(authorizationName);
        } catch (SQLException e){
            LOG.error(e);
        }
        return false;
    }
}
