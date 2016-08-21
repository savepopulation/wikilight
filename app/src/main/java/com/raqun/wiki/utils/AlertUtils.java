package com.raqun.wiki.utils;

import android.content.Context;
import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.text.TextUtils;
import android.widget.Toast;

/**
 * Created by tyln on 21.08.16.
 */
public final class AlertUtils {
    private AlertUtils() {

    }

    public static void alert(Context context, String mesage) {
        Toast.makeText(context, mesage, Toast.LENGTH_SHORT).show();
    }

    public interface AlertButtonClickListener {
        void onButtonClicked(DialogInterface dialog, int id);
    }

    public static AlertDialog createNewAlertDialog(Context context,
                                                   CharSequence title,
                                                   CharSequence message,
                                                   boolean isCancelable,
                                                   CharSequence positiveButtonText,
                                                   CharSequence negativeButtonText,
                                                   final AlertButtonClickListener positiveButtonClickListener,
                                                   final AlertButtonClickListener negativeButtonClickListener) {
        android.support.v7.app.AlertDialog.Builder dialog = new android.support.v7.app.AlertDialog.Builder(context).setMessage(message)
                .setCancelable(isCancelable);

        if (!TextUtils.isEmpty(title)) {
            dialog.setTitle(title);
        }

        if (!TextUtils.isEmpty(positiveButtonText)) {
            dialog.setPositiveButton(positiveButtonText, new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    dialog.dismiss();
                    if (positiveButtonClickListener != null) {
                        positiveButtonClickListener.onButtonClicked(dialog, which);
                    }
                }
            });
        }

        if (!TextUtils.isEmpty(negativeButtonText)) {
            dialog.setNegativeButton(negativeButtonText, new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    dialog.dismiss();
                    if (negativeButtonClickListener != null) {
                        negativeButtonClickListener.onButtonClicked(dialog, which);
                    }
                }
            });
        }

        AlertDialog alertDialog = dialog.create();
        return alertDialog;
    }
}
