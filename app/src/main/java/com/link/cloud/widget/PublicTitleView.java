package com.link.cloud.widget;

import android.content.Context;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.link.cloud.R;
import com.link.cloud.adapter.PublicTitleAdapter;
import com.zitech.framework.utils.ViewUtils;

import java.util.Arrays;
import java.util.List;

public class PublicTitleView extends LinearLayout {

    private String titleText;
    private float titleTextSize;
    private int titleTextColor;


    private String finishText;
    private float finishTextSize;
    private int finishTextColor;


    private RecyclerView recyclerview;
    private TextView title;
    private TextView finsh;
    private Context context;
    private PublicTitleAdapter adapter;

    private onItemClickListener itemClickListener;

    public interface onItemClickListener {
        void itemClickListener();
    }



    public PublicTitleView(Context context) {
        super(context);
        this.context = context;
    }

    public PublicTitleView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        this.context = context;
        Resources res = context.getResources();
        LayoutInflater.from(context).inflate(R.layout.title_common_layout, this, true);
        TypedArray a = context.obtainStyledAttributes(attrs, R.styleable.PublicTitleView);

        titleText = a.getString(R.styleable.PublicTitleView_title_text);
        titleTextSize = a.getDimension(R.styleable.PublicTitleView_title_text_size, ViewUtils.getDimen(R.dimen.w20));
        titleTextColor = a.getColor(R.styleable.PublicTitleView_title_text_color, res.getColor(R.color.red));

        finishText = a.getString(R.styleable.PublicTitleView_finish_text);
        finishTextSize = a.getDimension(R.styleable.PublicTitleView_finish_text_size, ViewUtils.getDimen(R.dimen.w20));
        finishTextColor = a.getColor(R.styleable.PublicTitleView_finish_text_color, res.getColor(R.color.white));

    }

    /**
     * 此方法会在所有的控件都从xml文件中加载完成后调用
     */
    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();
        title = (TextView) findViewById(R.id.title);
        recyclerview = (RecyclerView) findViewById(R.id.recycler_view);
        finsh = (TextView) findViewById(R.id.finsh);
        LinearLayoutManager layoutManager = new LinearLayoutManager(context);
        layoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
        recyclerview.setLayoutManager(layoutManager);

//        title.setTextSize(titleTextSize);
        title.setTextColor(titleTextColor);
        if (!TextUtils.isEmpty(titleText)) {
            title.setText(titleText);
        }
        if (!TextUtils.isEmpty(finishText)) {
            finsh.setText(finishText);
        }
//        finsh.setTextSize(finishTextSize);
        finsh.setTextColor(finishTextColor);
        adapter = new PublicTitleAdapter(context);
        recyclerview.setAdapter(adapter);
        ViewUtils.setOnClickListener(finsh, new OnClickListener() {
            @Override
            public void onClick(View view) {
                if (null != itemClickListener){
                    itemClickListener.itemClickListener();
                }
            }
        });
    }

    /**
     * 设置此控件的文本
     *
     * @param text
     */
    public void setTitleText(String text) {
        title.setText(text);
    }

    /**
     * 设置文字颜色
     *
     * @param textColor
     */
    public void setTitleTextColor(int textColor) {
        title.setTextColor(textColor);
    }

    /**
     * 设置字体大小
     *
     * @param textSize
     */
    public void setTitleTextSize(int textSize) {
        title.setTextSize(textSize);
    }

    /**
     * 返钮监听事件
     *
     * @param itemClickListener
     */
    public void setItemClickListener(onItemClickListener itemClickListener) {
        this.itemClickListener = itemClickListener;
    }

    /**
     * 设置此控件的文本
     *
     * @param text
     */
    public void setFinshText(String text) {
        finsh.setText(text);
    }

    /**
     * 设置文字颜色
     *
     * @param textColor
     */
    public void setFinshTextColor(int textColor) {
        finsh.setTextColor(textColor);
    }

    /**
     * 设置字体大小
     *
     * @param textSize
     */
    public void setFinshTextSize(int textSize) {
        finsh.setTextSize(textSize);
    }

    /**
     * 设置数据
     *
     * @param data
     */
    public void setTitleDate(List<String> data) {
        adapter.setDate(data);
    }

    /**
     * 设置数据
     *
     * @param tagsList
     */
    public void setTags(String... tagsList) {
        setTitleDate(Arrays.asList(tagsList));
    }

    public void nextPosition() {
        adapter.next();
    }

    public void lastPosition() {
        adapter.last();
    }


}
