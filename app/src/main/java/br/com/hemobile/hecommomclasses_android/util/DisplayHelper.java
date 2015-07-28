package br.com.hemobile.hecommomclasses_android.util;

import android.annotation.TargetApi;
import android.app.Activity;
import android.content.Context;
import android.graphics.Point;
import android.os.Build;
import android.view.Display;
import android.view.View;
import android.view.inputmethod.InputMethodManager;

public class DisplayHelper {

    public static Point scaleWithAspectRatio(double sourceWidth, double sourceHeight, double destWidth, double destHeight) {
        return scaleWithAspectRatio(new Point((int) sourceWidth, (int) sourceHeight), destWidth, destHeight);
    }

    public static Point scaleWithAspectRatio(Point source, double destWidth, double destHeight) {
        double scaleHeight = destHeight / source.y;
        double scaleWidth = destWidth / source.x;
        double scale = scaleHeight > scaleWidth ? scaleWidth : scaleHeight;

        source.set((int) (source.x * scale), (int) (source.y * scale));

        return source;
    }

    @TargetApi(Build.VERSION_CODES.HONEYCOMB_MR2)
    public static Point getWindowSize(Activity activity) {
        Display display = activity.getWindowManager().getDefaultDisplay();

        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB_MR2) {
            Point p = new Point();
            display.getSize(p);

            return p;
        } else {
            return new Point(display.getWidth(), display.getHeight());
        }
    }

    public static void hideSoftKeyboard(Context context, View rootView) {
        InputMethodManager imm = (InputMethodManager) context.getSystemService(Context.INPUT_METHOD_SERVICE);
        View focusedView = rootView.findFocus();

        imm.hideSoftInputFromWindow((focusedView == null ? rootView : focusedView).getApplicationWindowToken(), 0);
        imm.hideSoftInputFromWindow((focusedView == null ? rootView : focusedView).getApplicationWindowToken(), InputMethodManager.HIDE_IMPLICIT_ONLY);
    }
}
