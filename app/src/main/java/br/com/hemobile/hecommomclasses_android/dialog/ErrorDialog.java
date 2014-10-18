package br.com.hemobile.hecommomclasses_android.dialog;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;

import br.com.hemobile.hecommomclasses_android.R;

/**
 * Created by pedrohenriqueborges on 10/17/14.
 */
public class ErrorDialog extends AlertDialog implements DialogInterface.OnClickListener {

    public ErrorDialog(final Context context, final CharSequence message) {
        super(context);

        init();
        setMessage(message);
    }


    public ErrorDialog(final Context context, final int messageId) {
        super(context);

        init();
        setMessage(context.getResources().getString(messageId));
    }

    @Override
    public void onClick(final DialogInterface dialog, final int which) {
        dismiss();
    }


    private void init() {
        setButton(BUTTON_POSITIVE, getContext().getString(android.R.string.ok), this);
        setCancelable(true);
        setIcon(android.R.drawable.ic_dialog_alert);
        setTitle(R.string.dialog_error_title);
    }

}
