package sample.com.firebaseauth.utils;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

/**
 * Created by user on 6/20/2016.
 */
public class Utils {

    public static boolean isConnectionAvailable(Context context){
        ConnectivityManager mgr = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo info = mgr.getActiveNetworkInfo();

        boolean isConnected = info.isConnectedOrConnecting();
        if (isConnected){
            return true;
        }else{
            return false;
        }
    }
}
