package br.com.hemobile.hecommomclasses_android.util;

import android.content.Context;
import android.provider.Settings;

/**
 * Created by pedrohenriqueborges on 10/16/14.
 */
public class GPSUtil {

    public static boolean isMockSettingsON(Context context) {
        // returns true if mock location enabled, false if not enabled.
        if (Settings.Secure.getString(context.getContentResolver(),
                Settings.Secure.ALLOW_MOCK_LOCATION).equals("0"))
            return false;
        else
            return true;
    }

}
