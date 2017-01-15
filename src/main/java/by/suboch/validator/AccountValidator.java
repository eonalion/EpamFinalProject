package by.suboch.validator;

/**
 *
 */
public class AccountValidator {

    private static final String LOGIN_REGEXP = "^\\p{L}(\\p{L}|\\p{N}|[_])*$";
    private static final String EMAIL_REGEXP = "^.+@.+[.].+$";
    private static final String PASSWORD_REGEXP = "^.*(?=.{6,})(?=.*(\\p{Ll})+)(?=.*(\\p{N})+).*$";

    private static boolean validateLogin(String login) {
        return login.matches(LOGIN_REGEXP);
    }

    private static boolean validateEmail(String email) {
        return email.matches(EMAIL_REGEXP);
    }

    private static boolean validatePassword(String password) {
        return password.matches(PASSWORD_REGEXP);
    }

    private boolean checkPasswordsMatch(String password, String passwordConfirm) {
        return password.equals(passwordConfirm);
    }

    public boolean validateRegistration(String login, String email, String password, String passwordConfirm) {
        return validateLogin(login)
                && validateEmail(email)
                && validatePassword(password)
                && checkPasswordsMatch(password, passwordConfirm);
    }

    public boolean validateAuthorization(String authorizationName, String password) {
        return (validateLogin(authorizationName) || validateEmail(authorizationName))
                && validatePassword(password);
    }
}
