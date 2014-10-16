package br.com.hemobile.hecommomclasses_android;

import android.content.Context;
import android.widget.RelativeLayout;

public abstract class ItemView<T> extends RelativeLayout {

	public ItemView(Context context) {
		super(context);
	}

	public abstract void bind(T obj, int position);
}