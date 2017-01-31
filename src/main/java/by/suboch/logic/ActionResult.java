package by.suboch.logic;

/**
 *
 */
public enum ActionResult {
    SUCCESS_REGISTER,
    SUCCESS_ADD_TRACK,
    SUCCESS_ADD_ALBUM,
    SUCCESS_ADD_GENRE,
    SUCCESS_ADD_BONUS,
    SUCCESS_CHANGE_AVATAR,
    SUCCESS_CHANGE_PERSONAL_INFO,
    SUCCESS_CHANGE_USERNAME,
    SUCCESS_CHANGE_EMAIL,
    SUCCESS_CHANGE_PASSWORD,

    FAILURE_INVALID_USERNAME,
    FAILURE_INVALID_EMAIL,
    FAILURE_INVALID_PASSWORD,
    FAILURE_PASSWORDS_NOT_EQUALS,
    FAILURE_USERNAME_NOT_UNIQUE,
    FAILURE_EMAIL_NOT_UNIQUE,

    FAILURE_ARTIST_NOT_UNIQUE,
    FAILURE_ALBUM_NOT_UNIQUE,
    FAILURE_TRACK_NOT_UNIQUE,
    FAILURE_BONUS_NOT_UNIQUE;
}