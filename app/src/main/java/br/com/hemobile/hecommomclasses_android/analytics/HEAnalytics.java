package br.com.hemobile.hecommomclasses_android.analytics;

import android.util.Log;

/**
 * Created by hemobile on 17/10/14.
 */
public class HEAnalytics {

    public HEAnalytics() {

        Thread.UncaughtExceptionHandler exceptionHandler = new Thread.UncaughtExceptionHandler() {
            @Override
            public void uncaughtException(Thread thread, Throwable throwable) {
                Log.d("AWEAWE","AWE!!!!");

                Thread.getDefaultUncaughtExceptionHandler().uncaughtException(thread, throwable);
            }
        };
    }
}
