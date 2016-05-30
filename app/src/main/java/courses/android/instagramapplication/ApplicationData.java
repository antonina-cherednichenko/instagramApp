package courses.android.instagramapplication;

public class ApplicationData {
    public static final String CLIENT_ID = "420d39323861447588dd2595d8993ed6";
    public static final String CLIENT_SECRET = "5072d6d9b16b4194b3319a35b6780472";
    public static final String CALLBACK_URL = "http://www.justcallmeitgirl.com/";

    public static final String AUTH_URL = "https://api.instagram.com/oauth/authorize/";
    public static final String TOKEN_URL = "https://api.instagram.com/oauth/access_token/";
    public static final String API_URL = "https://api.instagram.com/v1";

    public static final String authURLString = AUTH_URL + "?client_id=" + ApplicationData.CLIENT_ID + "&redirect_uri=" + ApplicationData.CALLBACK_URL + "&response_type=code&display=touch&scope=likes+comments+relationships";
//	public static final String tokenURLString = TOKEN_URL + "?client_id=" + ApplicationData.CLIENT_ID + "&client_secret=" + ApplicationData.CLIENT_SECRET + "&redirect_uri=" + ApplicationData.CALLBACK_URL + "&grant_type=authorization_code";

}
