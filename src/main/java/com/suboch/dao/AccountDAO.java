package com.suboch.dao;

import com.suboch.database.ConnectionProxy;

import java.sql.*;

/**
 *
 */
public class AccountDAO {
    private ConnectionProxy connection;
    private static final String REGISTER_SCRIPT = "INSERT INTO `test`.`clients` (`first_name`, `last_name`, `email_address`, `login`, `password`, `is_admin`) " +
            "VALUES (?, ?, ?, ?, ?, ?, ?)";
    private static final String CHECK_ACCOUNT = "SELECT `login` FROM `clients` WHERE (`login` = ? OR `email` = ?) AND `password` = ?";
    private static final String CHECK_LOGIN = "SELECT `login` FROM `clients` WHERE `login` = ?";
    private static final String CHECK_EMAIL = "SELECT `login` FROM `clients` WHERE `email` = ?";


    public AccountDAO(ConnectionProxy connection){
        this.connection = connection;
    }

    public boolean registerAccount(String firstName, String lastName, String login, String email, String password) {
        try(PreparedStatement preparedStatement = connection.prepareStatement(REGISTER_SCRIPT)) {
            preparedStatement.setString(1, firstName);
            preparedStatement.setString(2, lastName);
            preparedStatement.setString(3, login);
            preparedStatement.setString(4, email);
            preparedStatement.setString(5, password);
            preparedStatement.execute();
        } catch (SQLException e) {
            //TODO:
        }
        return true;
    }

    public boolean authorizeAccount(String authorizationName, String password) {
        try(PreparedStatement preparedStatement = connection.prepareStatement(CHECK_ACCOUNT)) {
            preparedStatement.setString(1, authorizationName);
            preparedStatement.setString(2, authorizationName);
            preparedStatement.setString(3, password);
            ResultSet resultSet = preparedStatement.executeQuery();
            return resultSet.next();
        } catch (SQLException e) {
            //TODO:
        }

        return false;
    }

    public boolean checkLoginUniqueness(String login) {
        try(PreparedStatement preparedStatement = connection.prepareStatement(CHECK_LOGIN)) {
            preparedStatement.setString(1, login);
            ResultSet resultSet = preparedStatement.executeQuery();
            return !resultSet.next();
        } catch (SQLException e) {
            //TODO:
        }
        return false;
    }

    public boolean checkEmailUniqueness(String email) {
        try(PreparedStatement preparedStatement = connection.prepareStatement(CHECK_EMAIL)) {
            preparedStatement.setString(1, email);
            ResultSet resultSet = preparedStatement.executeQuery();
            return !resultSet.next();
        } catch (SQLException e) {
            //TODO:
        }
        return false;
    }

}
