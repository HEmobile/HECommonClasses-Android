package br.com.hemobile.hecommomclasses_android.dialog;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;

/**
 * Created by pedrohenriqueborges on 10/17/14.
 */
public class ConfirmationDialog extends AlertDialog {

    private ConfirmationDialogListener listener;

    /**
     * Construtor do dialog de confirmação.
     *
     * @param context   context.
     * @param message mesage String.
     * @param title title String.
     * @param listener listener.
     */

    public ConfirmationDialog(final Context context, final CharSequence message, final CharSequence title, ConfirmationDialogListener listener) {
        super(context);

        setTitle(title);
        setMessage(message);
        this.listener = listener;
        init();

    }

    /**
     * Construtor do dialog de confirmação.
     *
     * @param context   context.
     * @param messageId mesage String resource ID.
     * @param titleId title String resource ID.
     * @param listener listener.
     */
    public ConfirmationDialog(final Context context, final int messageId, final int titleId, ConfirmationDialogListener listener) {
        super(context);

        setTitle(context.getResources().getString(titleId));
        setMessage(context.getResources().getString(messageId));
        this.listener = listener;

        init();
    }

    private void init() {

        setCancelable(false);
        setButton(BUTTON_POSITIVE, getContext().getString(android.R.string.ok), new OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {

                listener.onOkClick();
            }
        });

        setButton(BUTTON_NEGATIVE, getContext().getString(android.R.string.cancel), new OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                listener.onCancelClick();
            }
        });

    }
}
