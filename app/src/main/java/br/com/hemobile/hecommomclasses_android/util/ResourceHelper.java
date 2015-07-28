package br.com.hemobile.hecommomclasses_android.util;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.support.annotation.AttrRes;
import android.support.annotation.ColorInt;
import android.support.annotation.NonNull;
import android.support.v4.graphics.drawable.DrawableCompat;
import android.util.TypedValue;

public class ResourceHelper {
    public static Drawable resolveDrawableAttr(Context context, @AttrRes int drawableAttr) {
        TypedArray a = resolveTypedArray(drawableAttr, context);
        Drawable dr = a.getDrawable(0);

        a.recycle();

        return dr;
    }

    private static TypedArray resolveTypedArray(int attrId, Context context) {
        TypedValue typedValue = new TypedValue();
        int[] attr = new int[] {attrId};

        return context.getTheme().obtainStyledAttributes(typedValue.data, attr);
    }

    public static float spToPixels(Context context, float sp) {
        float scaledDensity = context.getResources().getDisplayMetrics().scaledDensity;
        return scaledDensity * sp;
    }
}
