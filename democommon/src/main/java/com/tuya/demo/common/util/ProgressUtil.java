package com.tuya.demo.common.util;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.text.TextUtils;
import android.view.Gravity;
import android.view.Window;
import android.widget.TextView;

import com.tuya.demo.common.R;


/**
 * Progress loading dialog
 */
public class ProgressUtil {

    private static Dialog progressDialog;

    public static void showLoading(Context context, String message) {
        if (progressDialog == null) {
            progressDialog = getSimpleProgressDialog(context, "", new DialogInterface.OnCancelListener() {
                @Override
                public void onCancel(DialogInterface dialog) {
                    progressDialog = null;
                }
            });
        }
        ((TextView) progressDialog.findViewById(R.id.progress_dialog_message)).setText(message);
        if (!isShowLoading()) {
            progressDialog.show();
        }
    }

    public static void showLoading(Context context, int resId) {
        showLoading(context, context.getString(resId));
    }

    public static boolean isShowLoading() {
        if (progressDialog == null) {
            return false;
        }
        return progressDialog.isShowing();
    }

    public static void hideLoading() {
        if (progressDialog != null && progressDialog.getContext() != null) {
            progressDialog.hide();
            try {
                progressDialog.dismiss();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        progressDialog = null;
    }


    public static Dialog getSimpleProgressDialog(Context mContext, String msg, DialogInterface.OnCancelListener listener) {
        Dialog dialog = new Dialog(mContext, R.style.ex_Progress_Dialog);
        dialog.setContentView(R.layout.ex_progress_dialog_h);
        if (!TextUtils.isEmpty(msg)) {
            ((TextView) dialog.findViewById(R.id.progress_dialog_message)).setText(msg);
        }
        Window win = dialog.getWindow();
        win.setGravity(Gravity.CENTER);
        dialog.setCancelable(true);
        dialog.setCanceledOnTouchOutside(false);
        if (listener != null) {
            dialog.setOnCancelListener(listener);
        }
        return dialog;
    }


}
