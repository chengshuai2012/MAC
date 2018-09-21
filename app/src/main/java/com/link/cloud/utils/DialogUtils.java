package com.link.cloud.utils;

import android.app.AlertDialog;
import android.content.Context;
import android.view.View;
import android.view.Window;
import android.widget.LinearLayout;

/**
 * Created by OFX002 on 2018/9/21.
 */

public class DialogUtils {
    public static void showManagerDialog(View view, Context context){
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        final AlertDialog alertDialog = builder.create();
        alertDialog.setCancelable(true);
        alertDialog.setCanceledOnTouchOutside(true);
        alertDialog.show();
        Window window = alertDialog.getWindow();
        window.setBackgroundDrawableResource(android.R.color.transparent);
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(770,500);
        params.leftMargin=133;
        window.setContentView(view,params);
    }
}
