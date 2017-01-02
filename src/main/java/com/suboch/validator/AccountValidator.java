package com.suboch.validator;

/**
 *
 */
public class AccountValidator {
    public boolean checkPasswordsMatch(String password, String passwordConfirm) {
        return password.equals(passwordConfirm);
    }

    public boolean checkPasswordValidity(String password) {
        //TODO: regexp
        return true;
    }

    public boolean checkEmailValidity(String email) {
        //TODO: regexp
        return true;
    }

    public boolean checkLoginValidity(String login) {
        //TODO: regexp
        return true;
    }

    public boolean validateRegistration(String firstName, String lastName, String login, String email, String password, String passwordConfirm) {
        return checkPasswordsMatch(password, passwordConfirm) &&
                checkPasswordValidity(password) &&
                (checkLoginValidity(login) || checkEmailValidity(email));
        //FIXME: login || email
    }

    public boolean validateAuthorization(String authorizationName, String password) {
        return (checkLoginValidity(authorizationName) || checkEmailValidity(authorizationName))
                && checkPasswordValidity(password);
    }
}
