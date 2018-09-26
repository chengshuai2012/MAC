package com.link.cloud.widget;

import android.content.Context;
import android.view.Gravity;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.LinearLayout;

import com.link.cloud.R;
import com.zitech.framework.widget.ValidDialog;

/**
 * @author qianlu
 * @date 2018/9/26.
 * GitHub：qiandailu
 * email：zar.l@qq.com
 * description：
 */
public class BottomBuyDialog extends ValidDialog {
    private Button lastButton;
    private Button nextButton;
    private LinearLayout bottomlayout;

    public BottomBuyDialog(Context context) {
        super(context, R.style.BottomPushDialog);
        setContentView(R.layout.dialog_bottombuy);
        WindowManager.LayoutParams param = getWindow().getAttributes();
        param.gravity = Gravity.BOTTOM;
        param.height = WindowManager.LayoutParams.WRAP_CONTENT;
        param.width = WindowManager.LayoutParams.FILL_PARENT;
        getWindow().setAttributes(param);
        initialize();
    }

    private void initialize() {

        lastButton = (Button) findViewById(R.id.lastButton);
        nextButton = (Button) findViewById(R.id.nextButton);
        bottomlayout = (LinearLayout) findViewById(R.id.bottom_layout);
    }
}

