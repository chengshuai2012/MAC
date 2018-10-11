package com.link.cloud.fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;
import android.widget.Toast;

import com.link.cloud.R;
import com.link.cloud.adapter.CardViewAdapter;
import com.link.cloud.api.bean.MemberCardInfoBean;
import com.link.cloud.base.BaseFragment;
import com.link.cloud.widget.TurnCardListView;


import java.util.ArrayList;
import java.util.List;

/**
 * 作者：qianlu on 2018/9/30 15:34
 * 邮箱：zar.l@qq.com
 */
public class MemberCardFragment extends BaseFragment {


    private TurnCardListView cardListView;
    private android.widget.Button sureInfoButton;
    private android.widget.Button sureButton;
    private CardViewAdapter cardViewAdapter;
    private List<MemberCardInfoBean> date = new ArrayList<>();


    @Override
    protected int getContentViewId() {
        return R.layout.fragment_membercard;
    }


    @Override
    public void onInflateView(View contentView) {
        super.onInflateView(contentView);
        initView(contentView);
    }

    private void initView(View contentView) {
        cardListView = contentView.findViewById(R.id.card_list);

        sureInfoButton = contentView.findViewById(R.id.sureInfoButton);
        sureButton = contentView.findViewById(R.id.sureButton);
        cardViewAdapter = new CardViewAdapter(getActivity());
        date.add(new MemberCardInfoBean());
        date.add(new MemberCardInfoBean());
        date.add(new MemberCardInfoBean());
        date.add(new MemberCardInfoBean());
        cardViewAdapter.setList(date);
        cardListView.setAdapter(cardViewAdapter);


        TurnCardListView list = (TurnCardListView) contentView.findViewById(R.id.card_list);

        list.setOnTurnListener(new TurnCardListView.OnTurnListener() {
            @Override
            public void onTurned(int position) {
                Toast.makeText(getActivity(), "position = " + position, Toast.LENGTH_SHORT).show();
            }
        });


        list.setAdapter(new BaseAdapter() {
            int[] colors = {0xffFF9800, 0xff3F51B5, 0xff673AB7, 0xff006064, 0xffC51162, 0xffFFEB3B, 0xff795548, 0xff9E9E9E};

            @Override
            public int getCount() {
                return colors.length;
            }

            @Override
            public Object getItem(int position) {
                return position;
            }

            @Override
            public long getItemId(int position) {
                return position;
            }

            @Override
            public View getView(int position, View child, ViewGroup parent) {
                if (child == null) {
                    child = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_message, parent, false);
                }
//
//                ((TextView) child.findViewById(R.id.pos)).setText("" + position);
//                child.findViewById(R.id.image).setBackgroundColor(colors[position]);
                return child;
            }
        });

    }


}
