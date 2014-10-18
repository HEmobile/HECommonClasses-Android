package br.com.hemobile.hecommomclasses_android.dialog;

import android.app.AlertDialog;
import android.content.Context;

import br.com.hemobile.hecommomclasses_android.R;

/**
 * Created by pedrohenriqueborges on 10/17/14.
 */
public class InformationDialog extends AlertDialog {

    public InformationDialog(final Context context, final CharSequence message) {
        super(context);

        init();
        setMessage(message);
    }


    public InformationDialog(final Context context, final int messageId) {
        super(context);

        init();
        setMessage(context.getResources().getString(messageId));
    }

    private void init() {
        setIcon(android.R.drawable.ic_dialog_info);
        setTitle(R.string.dialog_information_title);
    }
}
