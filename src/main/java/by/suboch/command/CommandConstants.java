package by.suboch.command;

/**
 *
 */
public class CommandConstants {
    //   Pages.
    public static final String PAGE_ADD_NEW = "path.page.addNew";
    public static final String PAGE_ERROR = "path.page.error";
    public static final String PAGE_REGISTRATION = "path.page.registration";
    public static final String PAGE_USER_MAIN = "path.page.mainUser";
    public static final String PAGE_TRACK = "path.page.track";
    public static final String PAGE_ALBUM = "path.page.album";
    public static final String PAGE_ARTIST = "path.page.artist";
    public static final String PAGE_CLIENT = "path.page.client";
    public static final String RESOURCE_AUDIO = "path.resource.audiotracks";

    //    Session and request attributes.
    public static final String ATTR_ACCOUNT = "account";
    public static final String ATTR_MESSAGE = "message";
    public static final String ATTR_POPULAR_TRACKS_ON_PAGE = "tracksOnPage";
    public static final String ATTR_TRACK_LIST = "trackList";
    public static final String ATTR_GENRE_LIST = "genreList";
    public static final String ATTR_ALBUM_LIST = "albumList";
    public static final String ATTR_ARTIST_LIST = "artistList";
    public static final String ATTR_ACCOUNT_LIST = "accountList";
    public static final String ATTR_PAGE_AMOUNT = "pageAmount";
    public static final String ATTR_ERROR = "error";

    //    Additional parameters.
    public static final int POPULAR_TRACKS_PER_PAGE = 3;
    public static final int PAGES_AMOUNT = 4;

    //    Mime types.
    public static final String MIME_TYPE_IMAGE_JPG = "image/jpeg";
    public static final String MIME_TYPE_AUDIO_MP3 = "audio/mpeg";
    public static final String MIME_TYPE_JSON = "application/json";

    //    Messages.
    public static final String MESSAGE_SUCCESS_REGISTER = "message.success.register";
    public static final String MESSAGE_SUCCESS_SAVE_CHANGES = "message.success.saveChanges";
    public static final String MESSAGE_FAILURE_INVALID_LOGIN = "message.failure.invalid.login";
    public static final String MESSAGE_FAILURE_INVALID_EMAIL = "message.failure.invalid.email";
    public static final String MESSAGE_FAILURE_INVALID_PASSWORD = "message.failure.invalid.password";
    public static final String MESSAGE_FAILURE_PASSWORDS_NOT_EQUALS = "message.failure.passwordsNotEquals";
    public static final String MESSAGE_FAILURE_LOGIN_NOT_UNIQUE = "message.failure.notUnique.login";
    public static final String MESSAGE_FAILURE_EMAIL_NOT_UNIQUE = "message.failure.notUnique.email";
    public static final String MESSAGE_FAILURE_LOGIN = "message.failure.logIn";
    public static final String MESSAGE_FAILURE_INVALID_NEW_PASSWORD = "message.invalid.new.password";
    public static final String MESSAGE_FAILURE_INVALID_IMAGE = "message.failure.invalid.image";
    public static final String MESSAGE_ERROR_DATABASE_CAUSE = "message.error.database.cause";
    public static final String MESSAGE_ERROR_DATABASE_TO_DO = "message.error.database.to.do";
}
