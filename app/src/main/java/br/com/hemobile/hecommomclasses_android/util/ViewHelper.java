package br.com.hemobile.hecommomclasses_android.util;

import android.view.View;

public class ViewHelper {
    public static void setVisibility(int visibilityFlag, View...views) {
        for (View view : views) {
            if (view != null) {
                view.setVisibility(visibilityFlag);
            }
        }
    }
}
