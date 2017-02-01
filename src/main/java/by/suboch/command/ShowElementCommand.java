package by.suboch.command;

import by.suboch.entity.*;
import by.suboch.exception.LogicException;
import by.suboch.logic.*;
import by.suboch.manager.ConfigurationManager;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 *
 */
public class ShowElementCommand extends AbstractServletCommand {
    private static final Logger LOG = LogManager.getLogger();
    private static final String PARAM_ELEMENT_ID = "id";
    private static final String PARAM_ELEMENT_TYPE = "type";

    private static final String ATTR_CURRENT_TRACK = "currentTrack";
    private static final String ATTR_CURRENT_ALBUM = "currentAlbum";
    private static final String ATTR_CURRENT_ARTIST = "currentArtist";
    private static final String ATTR_CURRENT_ACCOUNT = "currentAccount";
    private static final String ATTR_CURRENT_PURCHASE = "currentPurchase";
    private static final String ATTR_CURRENT_CLIENT_PURCHASES = "currentClientPurchases";
    private static final String ATTR_CURRENT_ARTIST_ALBUMS = "currentArtistAlbums";
    private static final String ATTR_CURRENT_ALBUM_TRACKS = "currentAlbumTracks";
    private static final String ATTR_CURRENT_PURCHASE_TRACKS = "currentPurchaseTracks";

    private static final String TYPE_TRACK = "track";
    private static final String TYPE_ALBUM = "album";
    private static final String TYPE_ARTIST = "artist";
    private static final String TYPE_ACCOUNT = "account";
    private static final String TYPE_PURCHASE = "purchase";


    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws IOException {
        int elementId = Integer.parseInt(request.getParameter(PARAM_ELEMENT_ID));
        String elementType = request.getParameter(PARAM_ELEMENT_TYPE);

        String resultData = "";
        AlbumLogic albumLogic = new AlbumLogic();
        TrackLogic trackLogic = new TrackLogic();
        ArtistLogic artistLogic = new ArtistLogic();
        AccountLogic accountLogic = new AccountLogic();
        PurchaseLogic purchaseLogic = new PurchaseLogic();

        try {
            switch (elementType) {
                case TYPE_TRACK:
                    Track track = trackLogic.loadTrackById(elementId);
                    request.getSession().setAttribute(ATTR_CURRENT_TRACK, track);
                    //request.setAttribute("", trackLogic.loadTrackArtist(track.getAlbumId()));
                    //request.setAttribute("", trackLogic.loadTrackAlbum(track.getAlbumId()));
                    //request.setAttribute("", trackLogic.loadTrackGenre.getGenreId());
                    resultData = ConfigurationManager.getProperty(CommandConstants.PAGE_TRACK);
                    break;
                case TYPE_ALBUM:
                    Album album = albumLogic.loadAlbumById(elementId);
                    List<Track> albumTracks = trackLogic.loadAlbumTracks(album.getAlbumId());
                    request.getSession().setAttribute(ATTR_CURRENT_ALBUM, album);
                    request.getSession().setAttribute(ATTR_CURRENT_ALBUM_TRACKS, albumTracks);
                    resultData = ConfigurationManager.getProperty(CommandConstants.PAGE_ALBUM);
                    break;
                case TYPE_ARTIST:
                    Artist artist = artistLogic.loadArtistById(elementId);
                    List<Album> artistAlbums = albumLogic.loadArtistAlbums(artist.getArtistId());
                    request.getSession().setAttribute(ATTR_CURRENT_ARTIST, artist);
                    request.getSession().setAttribute(ATTR_CURRENT_ARTIST_ALBUMS, artistAlbums);
                    resultData = ConfigurationManager.getProperty(CommandConstants.PAGE_ARTIST);
                    break;
                case TYPE_ACCOUNT:
                    Account account = accountLogic.loadAccount(elementId);
                    List<Purchase> purchaseList = purchaseLogic.loadAllPurchases(account.getAccountId());
                    request.getSession().setAttribute(ATTR_CURRENT_ACCOUNT, account);
                    request.getSession().setAttribute(ATTR_CURRENT_CLIENT_PURCHASES, purchaseList);
                    resultData = ConfigurationManager.getProperty(CommandConstants.PAGE_CLIENT);
                    break;
                case TYPE_PURCHASE:
                    Purchase purchase = purchaseLogic.loadPurchaseById(elementId);
                    List<Track> purchaseTracks = trackLogic.loadPurchaseTracks(purchase.getPurchaseId());
                    request.getSession().setAttribute(ATTR_CURRENT_PURCHASE, purchase);
                    request.getSession().setAttribute(ATTR_CURRENT_PURCHASE_TRACKS, purchaseTracks);
                    resultData = ConfigurationManager.getProperty(CommandConstants.PAGE_PURCHASE);
                    break;
                default:
            }
        } catch (LogicException e) {
            LOG.error("Errors while show clicked element.", e);
            resultData = handleDBError(e, request, response);
        }

        return resultData;
    }
}
