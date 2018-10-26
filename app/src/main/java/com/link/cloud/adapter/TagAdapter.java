package com.link.cloud.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.link.cloud.R;
import com.link.cloud.User;
import com.zitech.framework.utils.ViewUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * 作者：qianlu on 2018/10/23 14:27
 * 邮箱：zar.l@qq.com
 */
public class TagAdapter extends RecyclerView.Adapter<TagAdapter.ContentViewHolder> {


    private List<String> data = new ArrayList<>();
    private Context activity;
    private int isSeceterPosition = -1;
    private boolean isSeceter;

    public void setSeceter(boolean seceter) {
        isSeceter = seceter;
    }

    public void setIsSeceterPosition(int isSeceterPosition) {
        this.isSeceterPosition = isSeceterPosition;
        notifyDataSetChanged();
    }

    public TagAdapter(Context activity, List<String> data) {
        this.activity = activity;
        this.data = data;
    }


    TagAdapter.onItemClickLister mListner;

    public void setOnItemClickListner(TagAdapter.onItemClickLister mListner) {
        this.mListner = mListner;
    }

    public interface onItemClickLister {
        void OnClickPre(int postion);
    }


    @NonNull
    @Override
    public TagAdapter.ContentViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.tag_item_layout, null);
        return new TagAdapter.ContentViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull TagAdapter.ContentViewHolder holder, final int position) {
        final String contentText = data.get(position);
        holder.contentText.setText(contentText+activity.getResources().getString(R.string.select_class_time));
        isSeceterPosition = User.get().getPosition();
        if (isSeceterPosition == position && isSeceter) {
            holder.rootViewLayout.setBackground(activity.getResources().getDrawable(R.drawable.border_red_gradient_30px));
            holder.contentText.setTextColor(activity.getResources().getColor(R.color.white));
        } else {
            holder.rootViewLayout.setBackground(activity.getResources().getDrawable(R.drawable.private_edu_member_bg));
            holder.contentText.setTextColor(activity.getResources().getColor(R.color.red));

        }
        ViewUtils.setOnClickListener(holder.rootViewLayout, new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mListner != null) {
                    mListner.OnClickPre(position);
                    User.get().setPosition(position);
                }
            }
        });


    }

    @Override
    public int getItemCount() {
        return data.size();
    }


    public static class ContentViewHolder extends RecyclerView.ViewHolder {

        private TextView contentText;
        private LinearLayout rootViewLayout;


        public ContentViewHolder(View itemView) {
            super(itemView);
            contentText = itemView.findViewById(R.id.contentText);
            rootViewLayout = itemView.findViewById(R.id.rootViewLayout);
        }


    }
}
