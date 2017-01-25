package by.suboch.util;

/**
 *
 */
public class URIAnalyzer {

    private static final String URI_AND_PATH_PARAMETER_DELIMITER = ";";
    private static final String URI_PAGE_REGEXP = "^.*\\.(jsp)$";

    public static String cleanURI(String uri) {
        int pathParameterIndex = uri.indexOf(URI_AND_PATH_PARAMETER_DELIMITER);
        if (pathParameterIndex >= 0) {
            uri = uri.substring(0, pathParameterIndex);
        }
        return uri;
    }

    public static boolean isPageURI(String uri) {
        return uri.matches(URI_PAGE_REGEXP);
    }

}
