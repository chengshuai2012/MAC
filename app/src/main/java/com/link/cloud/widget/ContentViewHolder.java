package com.link.cloud.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.ViewAnimator;

import com.link.cloud.R;
import com.zitech.framework.data.network.exception.ApiException;
import com.zitech.framework.utils.NetworkUtil;



/**
 * 用于数据的几种加载状态
 */

public class ContentViewHolder extends ViewAnimator {

    public static final int LOADING = 0;
    public static final int RETRY = 1;
    public static final int NO_DATA = 2;
    public static final int CONTENT = 3;
    protected Button retry;
    private boolean retryInNoData = false;
    protected TextView noData;
    protected TextView errorPromptView;
    protected ProgressBar progressBar;
    private static final int CHILD_SIZE = 4;
    private String noDataHint;
    private CharSequence defaultErrorPrompt;

    public ContentViewHolder(Context context) {
        super(context);
        initView();
    }

    //    private ViewCo
    public ContentViewHolder(Context context, AttributeSet attrs) {
        super(context, attrs);
        initView();
    }

    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();

    }

    public void showNoData() {
        if (retryInNoData) {
            if (errorPromptView != null)
                errorPromptView.setText(noDataHint);
            setDisplayedChild(RETRY);
        } else {
            setDisplayedChild(NO_DATA);
        }
    }

    protected void initView() {
        View v = inflateLayout();
        retry = (Button) v.findViewById(R.id.retry_btn);
        noData = (TextView) v.findViewById(R.id.no_data);
        errorPromptView = (TextView) findViewById(R.id.error_prompt_view);
        progressBar = (ProgressBar) v.findViewById(R.id.loading_progress);
        defaultErrorPrompt = errorPromptView.getText();
    }

    public void setContent(View content) {
        if (getChildCount() == CHILD_SIZE) {
            removeViewAt(CONTENT);
        }
        ViewGroup parent = (ViewGroup) content.getParent();
        ViewGroup.LayoutParams contentLayoutParams = content.getLayoutParams();
        int index = parent.indexOfChild(content);
        parent.removeView(content);
        LayoutParams params = new LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
        addView(content, CONTENT, params);
        parent.addView(this, index, contentLayoutParams);
    }

    public void setEmptyView(View emptyView) {
        removeViewAt(NO_DATA);
        LayoutParams params = new LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
        addView(emptyView, NO_DATA, params);
    }

    protected View inflateLayout() {
        return inflate(getContext(), R.layout.content_view_holder, this);
    }

    public void showContent() {
        setDisplayedChild(CONTENT);
        progressBar.invalidate();
    }

    public void showLoading() {
        setDisplayedChild(LOADING);
    }

    public void showRetry() {
        setDisplayedChild(RETRY);
        if (NetworkUtil.isNetworkAvailable()) {
            setErrorPrompt(R.string.common_error_msg);
        } else {
            setErrorPrompt(R.string.common_no_network_msg);
        }
    }

    public void showRetry(Throwable e) {
        setDisplayedChild(RETRY);
        if (NetworkUtil.isNetworkAvailable()) {
            if (e != null && e instanceof ApiException) {
                setErrorPrompt(e.getMessage());
            } else {
                setErrorPrompt(R.string.common_error_msg);
            }
        } else {
            setErrorPrompt(R.string.common_no_network_msg);
        }
    }

    public void showRetry(String retryPrompt) {
        setDisplayedChild(RETRY);
        setErrorPrompt(retryPrompt);
    }

    public void showEmpty() {
        setDisplayedChild(NO_DATA);
    }

    public void showEmpty(String noDataPrompt) {
        setDisplayedChild(NO_DATA);
        setNoDataPrompt(noDataPrompt);
    }


    public void setRetryListener(final OnClickListener listener) {
        retry.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                listener.onClick(view);
            }
        });
    }


    public void setDefaultEmptyImage(int resId) {
        noData.setBackgroundResource(resId);
    }

    public void setNoDataPrompt(String noDataPrompt) {
        noData.setText(noDataPrompt);
    }

    public void setErrorPrompt(int resId) {
        errorPromptView.setText(resId);
    }

    public void setErrorPrompt(CharSequence errorPrompt) {
        errorPromptView.setText(errorPrompt);
    }

    public int getCurrentViewIndex() {
        return indexOfChild(getCurrentView());
    }
}
