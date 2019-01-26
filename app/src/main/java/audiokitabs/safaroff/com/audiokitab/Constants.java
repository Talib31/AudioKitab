package audiokitabs.safaroff.com.audiokitab;

import audiokitabs.safaroff.com.audiokitab.Interface.BooksClient;
import audiokitabs.safaroff.com.audiokitab.Interface.RetrofitClient;

public class Constants {
    public static final String  BASE_URL = "http://aspaz.alwaysdata.net/";
    public static final int ERROR_DIALOG_REQUEST = 9001;
    public static final int PERMISSIONS_REQUEST_ACCESS_FINE_LOCATION = 9002;
    public static final int PERMISSIONS_REQUEST_ENABLE_GPS = 9003;
    public static final String MAPVIEW_BUNDLE_KEY = "MapViewBundleKey";
    public static BooksClient getAPI(){
        return RetrofitClient.getClient(BASE_URL).create(BooksClient.class);
    }
}
