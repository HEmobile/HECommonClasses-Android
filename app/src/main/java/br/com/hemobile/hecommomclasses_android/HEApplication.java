package br.com.hemobile.hecommomclasses_android;

import android.app.Application;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.graphics.Typeface;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.widget.TextView;

import com.google.android.gms.analytics.GoogleAnalytics;
import com.google.android.gms.analytics.HitBuilders;
import com.google.android.gms.analytics.Tracker;

public abstract class HEApplication extends Application {
    private static boolean isWifiConnected;

    private static boolean isMobileConnected;

    private static boolean appBlocked = false;

    private static HEApplication instance;

    public static HEApplication getInstance() {
        return instance;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        registerReceiver(mConnReceiver, new IntentFilter(ConnectivityManager.CONNECTIVITY_ACTION));
        instance = this;
    }

    public static int dpToPixels(int dp) {
        final float scale = instance.getResources().getDisplayMetrics().density;
        return (int) (dp * scale + 0.5f);
    }

    public static void setTypeface(TextView tv, String fontName) {
        Typeface tf = Typeface.createFromAsset(instance.getAssets(), "fonts/" + fontName);
        tv.setTypeface(tf);
    }

    public final static boolean isValidEmail(CharSequence target) {
        if (target == null) {
            return false;
        } else {
            return android.util.Patterns.EMAIL_ADDRESS.matcher(target).matches();
        }
    }

    public static boolean isMobileConnected() {
        return isMobileConnected;
    }

    public static boolean isWifiConnected() {
        return isWifiConnected;
    }

    public static boolean hasInternetConnection() {
        return isWifiConnected || isMobileConnected;
    }

    public static String getAppVersion() {
        try {
            PackageInfo pInfo = instance.getPackageManager().getPackageInfo(instance.getPackageName(), PackageManager.GET_META_DATA);
            return pInfo.versionName;
        } catch (Exception e) {
            return "";
        }
    }

    public static void sendEmail(String[] to, String subject, String text) {
        final Intent emailIntent = new Intent(Intent.ACTION_SEND);

        emailIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);

        emailIntent.setType("plain/text");
        emailIntent.putExtra(Intent.EXTRA_EMAIL, to);
        emailIntent.putExtra(Intent.EXTRA_SUBJECT, subject);
        emailIntent.putExtra(Intent.EXTRA_TEXT, text);

        instance.startActivity(emailIntent);
    }

    public static void setAppBlocked(boolean block) {
        HEApplication.appBlocked = block;
    }

    public static boolean isAppBlocked() {
        return appBlocked;
    }

    private BroadcastReceiver mConnReceiver = new BroadcastReceiver() {

        @Override
        public void onReceive(Context context, Intent intent) {
            ConnectivityManager connMgr = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);

            NetworkInfo networkInfo = connMgr.getNetworkInfo(ConnectivityManager.TYPE_WIFI);

            if (networkInfo != null)
                isWifiConnected = networkInfo.isConnected();

            networkInfo = connMgr.getNetworkInfo(ConnectivityManager.TYPE_MOBILE);

            if (networkInfo != null)
                isMobileConnected = networkInfo.isConnected();
        }
    };

    private static Tracker tracker;

    synchronized public static Tracker getTracker() {
        if (tracker == null) {
            GoogleAnalytics analytics = GoogleAnalytics.getInstance(instance);
            tracker = analytics.newTracker(instance.getAnalyticsResource());
            tracker.enableAutoActivityTracking(true);
        }
        return tracker;
    }

    public static void sendAnalyticsEvent(String category, String action, String label, long value) {
        Tracker t = getTracker();
        t.send(new HitBuilders.EventBuilder().setCategory(category)
                .setAction(action)
                .setLabel(label)
                .setValue(value)
                .build());
    }

    public abstract String getAppName();

    public abstract int getAnalyticsResource();
}