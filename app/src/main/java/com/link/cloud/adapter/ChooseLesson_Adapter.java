package com.link.cloud.adapter;

import android.app.Activity;
import android.support.v7.widget.CardView;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.link.cloud.R;
import com.link.cloud.api.bean.LessonItemBean;
import com.zitech.framework.utils.ViewUtils;

import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;


public class ChooseLesson_Adapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> implements View.OnClickListener {

    private List<LessonItemBean> dataList;

    private final int TYPE_ITEM = 1;

    private final int TYPE_FOOTER = 2;

    Activity mContext;


    public ChooseLesson_Adapter(List<LessonItemBean> dataList, Activity mContext) {
        this.dataList = dataList;
        this.mContext = mContext;
    }

    public interface onItemClickLister {
        void OnClickCoachImage(int postion);

        void OnClickPre(int postion);

        void OnClickLesson(int postion);
    }

    onItemClickLister mListner;

    public void setOnItemClickListner(onItemClickLister mListner) {
        this.mListner = mListner;
    }

    @Override
    public int getItemViewType(int position) {
        return TYPE_ITEM;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (viewType == TYPE_ITEM) {
            View view = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.choose_lesson_item, parent, false);
            return new RecyclerViewHolder(view);
        }
        return null;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, final int position) {
        if (holder instanceof RecyclerViewHolder) {

            LessonItemBean lessonItemBean = dataList.get(position);

            RecyclerViewHolder recyclerViewHolder = (RecyclerViewHolder) holder;
            recyclerViewHolder.lesson_pre_check.setTag(position);

            Glide.with(mContext).load(lessonItemBean.getStoreCoachTopimg()).into(recyclerViewHolder.coach_image);
//            private TextView lessonIntroduce;
//            private TextView lessonPriceMemeber;

            recyclerViewHolder.lessonName.setText(lessonItemBean.getFitnessCourseName());
//            recyclerViewHolder.lessonIntroduce.setText(lessonItemBean.);
            recyclerViewHolder.lessonTime.setText(lessonItemBean.getCoursePlanBegtime());
            recyclerViewHolder.lessonPrice.setText(lessonItemBean.getCourseReleaseMoney());
            recyclerViewHolder.lessonPriceMemeber.setText(lessonItemBean.getCoursePlanEndtime());

            ViewUtils.setOnClickListener(recyclerViewHolder.coach_image, new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (mListner != null) {
                        mListner.OnClickCoachImage(position);
                    }
                }
            });
            ViewUtils.setOnClickListener(recyclerViewHolder.lesson_pre_check, new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (mListner != null) {
                        mListner.OnClickPre(position);
                    }
                }
            });
        }
    }

    @Override
    public int getItemCount() {
        return dataList.size();
    }

    @Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
        RecyclerView.LayoutManager manager = recyclerView.getLayoutManager();
        if (manager instanceof GridLayoutManager) {
            final GridLayoutManager gridManager = ((GridLayoutManager) manager);
            gridManager.setSpanSizeLookup(new GridLayoutManager.SpanSizeLookup() {
                @Override
                public int getSpanSize(int position) {
                    return getItemViewType(position) == TYPE_FOOTER ? gridManager.getSpanCount() : 1;
                }
            });
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.coach_image:
                mListner.OnClickCoachImage((int) v.getTag());
                break;
            case R.id.lesson_pre_check:
                mListner.OnClickPre((int) v.getTag());
                break;

        }
    }

    private class RecyclerViewHolder extends RecyclerView.ViewHolder {
        ImageView coach_image;
        TextView lesson_pre_check;
        private CardView cardView;
        private TextView lessonName;
        private TextView lessonIntroduce;
        private TextView lessonPrice;
        private TextView lessonPriceMemeber;
        private TextView lessonTime;


        RecyclerViewHolder(View itemView) {
            super(itemView);
            coach_image = (ImageView) itemView.findViewById(R.id.coach_image);
            lesson_pre_check = (TextView) itemView.findViewById(R.id.lesson_pre_check);
            coach_image.setOnClickListener(ChooseLesson_Adapter.this);
            lesson_pre_check.setOnClickListener(ChooseLesson_Adapter.this);
            cardView = (CardView) itemView.findViewById(R.id.cardView);
            lessonName = (TextView) itemView.findViewById(R.id.lesson_name);
            lessonIntroduce = (TextView) itemView.findViewById(R.id.lesson_introduce);
            lessonPrice = (TextView) itemView.findViewById(R.id.lesson_price);
            lessonPriceMemeber = (TextView) itemView.findViewById(R.id.lesson_price_memeber);
            lessonTime = (TextView) itemView.findViewById(R.id.lesson_time);


        }
    }

}
