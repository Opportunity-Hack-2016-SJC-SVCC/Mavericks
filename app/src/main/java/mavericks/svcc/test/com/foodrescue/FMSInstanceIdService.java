package mavericks.svcc.test.com.foodrescue;

import android.util.Log;

import com.google.firebase.iid.FirebaseInstanceIdService;

/**
 * Created by savani on 8/30/16.
 */

public class FMSInstanceIdService extends FirebaseInstanceIdService {
    private static final String TAG= "FMSInstanceIdService";



    @Override
    public void onTokenRefresh() {
        super.onTokenRefresh();
        Log.d(TAG, "Token refresh");
    }
}
