package com.example.sakethreddy.mco;

import android.util.Log;

import com.google.firebase.iid.FirebaseInstanceId;
import com.google.firebase.iid.FirebaseInstanceIdService;


/**
 * Created by sakethreddy on 01/09/17.
 */

public class FcmInstanceIdService extends FirebaseInstanceIdService {
    //SharedPreferences sharedPreferences;
    public static final String TAG = "YOUR-TAG-NAME";
    @Override
    public void onTokenRefresh() {
        //super.onTokenRefresh();

        String refreshedToken = FirebaseInstanceId.getInstance().getToken();
       /* SharedPreferences sharedPreferences = getApplicationContext().getSharedPreferences(getString(R.string.FCM_PREF), Context.MODE_PRIVATE);
        SharedPreferences.Editor editor= sharedPreferences.edit();
        editor.putString(getString(R.string.FCM_TOKEN),refreshedToken);
        editor.commit();*/

        Log.d(TAG ,"Refreshed token: " + refreshedToken);

        storeToken(refreshedToken);
    }

   private void storeToken(String token) {


    }
}
