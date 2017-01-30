package by.suboch.command;

import by.suboch.entity.Account;
import by.suboch.entity.Album;
import by.suboch.entity.Artist;
import by.suboch.entity.Track;
import by.suboch.exception.LogicException;
import by.suboch.logic.AccountLogic;
import by.suboch.logic.AlbumLogic;
import by.suboch.logic.ArtistLogic;
import by.suboch.logic.TrackLogic;
import by.suboch.manager.ConfigurationManager;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 *
 */
public class ShowElementCommand implements IServletCommand {
    private static final String PARAM_ELEMENT_ID = "id";
    private static final String PARAM_ELEMENT_TYPE = "type";
    private static final String ATTR_CURRENT_TRACK = "currentTrack";
    private static final String ATTR_CURRENT_ALBUM = "currentAlbum";
    private static final String ATTR_CURRENT_ARTIST = "currentArtist";
    private static final String ATTR_CURRENT_ACCOUNT = "currentAccount";
    private static final String ATTR_ARTIST_ALBUMS = "artistAlbums";
    private static final String ATTR_ALBUM_TRACKS = "albumTracks";

    private static final String TYPE_TRACK = "track";
    private static final String TYPE_ALBUM = "album";
    private static final String TYPE_ARTIST = "artist";
    private static final String TYPE_ACCOUNT = "account";


    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws IOException {
        int elementId = Integer.parseInt(request.getParameter(PARAM_ELEMENT_ID));
        String elementType = request.getParameter(PARAM_ELEMENT_TYPE);

        String nextPage = "";
        try {
            switch (elementType) {
                case TYPE_TRACK:
                    TrackLogic trackLogic = new TrackLogic();
                    Track track = trackLogic.loadTrackById(elementId);
                    request.getSession().setAttribute(ATTR_CURRENT_TRACK, track);
                    nextPage = ConfigurationManager.getProperty(CommandConstants.PAGE_TRACK);
                    break;
                case TYPE_ALBUM:
                    AlbumLogic albumLogic = new AlbumLogic();
                    Album album = albumLogic.loadAlbumById(elementId);
                    //List<Track> tracks = albumLogic.loadTracks(album.getAlbumId());
                    //request.getSession().setAttribute(ATTR_ALBUM_TRACKS, tracks);
                    request.getSession().setAttribute(ATTR_CURRENT_ALBUM, album);
                    nextPage = ConfigurationManager.getProperty(CommandConstants.PAGE_ALBUM);
                    break;
                case TYPE_ARTIST:
                    ArtistLogic artistLogic = new ArtistLogic();
                    Artist artist = artistLogic.loadArtistById(elementId);
                    //List<Album> albums = artistLogic.loadAlbums(artist.getArtistId());
                    //request.getSession().setAttribute(ATTR_ARTIST_ALBUMS, albums);
                    request.getSession().setAttribute(ATTR_CURRENT_ARTIST, artist);
                    nextPage = ConfigurationManager.getProperty(CommandConstants.PAGE_ARTIST);
                    break;
                case TYPE_ACCOUNT:
                    AccountLogic accountLogic = new AccountLogic();
                    Account account = accountLogic.loadAccount(elementId);
                    request.getSession().setAttribute(ATTR_CURRENT_ACCOUNT, account);
                    nextPage = ConfigurationManager.getProperty(CommandConstants.PAGE_CLIENT);
                    break;
                default:
            }
        } catch (LogicException e) {
            //TODO: Handle exception.
        }

        return nextPage;
    }
}
