package com.link.cloud.widget;

import android.app.Dialog;
import android.content.Context;
import android.view.Gravity;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.LinearLayout;

import com.link.cloud.R;
import com.zitech.framework.utils.ViewUtils;
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
    private OnPositiveButtonClickListener onPositiveButtonClickListener;
    private OnCancelButtonClickListener onCancelButtonClickListener;

    public interface OnPositiveButtonClickListener {
        public void onClick(Dialog dialog);
    }

    public interface OnCancelButtonClickListener {
        public void onClick(Dialog dialog);
    }

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

    public void setOnPositiveButtonClickListener(OnPositiveButtonClickListener onPositiveButtonClickListener) {
        this.onPositiveButtonClickListener = onPositiveButtonClickListener;
    }

    public void setOnCancelButtonClickListener(OnCancelButtonClickListener onNegativeButtonClickListener) {
        this.onCancelButtonClickListener = onNegativeButtonClickListener;
    }

    private void initialize() {
        lastButton = (Button) findViewById(R.id.lastButton);
        nextButton = (Button) findViewById(R.id.nextButton);
        bottomlayout = (LinearLayout) findViewById(R.id.bottom_layout);
        ViewUtils.setOnClickListener(lastButton, new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (onCancelButtonClickListener != null) {
                    onCancelButtonClickListener.onClick(BottomBuyDialog.this);
                }
            }
        });
        ViewUtils.setOnClickListener(nextButton, new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (onPositiveButtonClickListener != null) {
                    onPositiveButtonClickListener.onClick(BottomBuyDialog.this);
                }
            }
        });

    }

    public void setPositiveButtonText(String text, int visibilit) {
        lastButton.setText(text);
        lastButton.setVisibility(visibilit);
    }

    public void setCancelButtonText(String text, int visibilit) {
        nextButton.setText(text);
        nextButton.setVisibility(visibilit);
    }

}

