package com.clicklabs.shopkeeperassignmentshared.utils;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;

/**
 * Created by Krishika on 16-02-2016.
 */
public class CommonAlert {
    private static AlertDialog alertDialog;
    private static OnAlertOkClickListener onAlertOkClickListener;
    public static void alertDialogShow(Context context, String message)
    {
        alertDialog = new AlertDialog.Builder(context).setMessage(message).create();
        alertDialog.setButton(Dialog.BUTTON_NEUTRAL,"OK", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
                alertDialog.dismiss();
            }
        });
        alertDialog.show();
    }
    public static void alertDialogShow(Context context,String msg,OnAlertOkClickListener onAlertOkClickListener1) {
        onAlertOkClickListener=onAlertOkClickListener1;
        alertDialog=new AlertDialog.Builder(context).setMessage(msg).create();

        alertDialog.setButton(AlertDialog.BUTTON_NEUTRAL, "Ok", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                onAlertOkClickListener.onOkButtonClicked();
                alertDialog.dismiss();
            }
        });
        alertDialog.show();
    }
    public interface OnAlertOkClickListener
    {
        public void onOkButtonClicked();

    }


}
