package com.link.cloud.fragment;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.link.cloud.R;
import com.link.cloud.adapter.SingleBuyItemAdapter;
import com.link.cloud.base.BaseFragment;
import com.link.cloud.widget.ExpandableTextView;

/**
 * @author qianlu
 * @date 2018/9/28.
 * GitHub：qiandailu
 * email：zar.l@qq.com
 * description：
 */
public class SingleBuyFragment extends BaseFragment {
    private TextView expandText;
    private ExpandableTextView expandTextView;
    private ImageButton expandCollapse;
    private SingleBuyItemAdapter adapter;
    private TextView expandableText;
    private android.widget.Button payButton;
    private TextView goLoginText;
    private RecyclerView recycleView;

    @Override
    protected int getContentViewId() {
        return R.layout.fragment_singlebuy;
    }

    @Override
    public void onInflateView(View contentView) {
        super.onInflateView(contentView);
        initialize(contentView);
    }

    private void initialize(View contentView) {
        adapter = new SingleBuyItemAdapter(getActivity());
        recycleView = (RecyclerView) contentView.findViewById(R.id.recycleView);
        recycleView.setLayoutManager(new LinearLayoutManager(getActivity()));
        expandTextView = (ExpandableTextView) contentView.findViewById(R.id.expand_text_view);
        expandTextView.setOnExpandStateChangeListener(new ExpandableTextView.OnExpandStateChangeListener() {
            @Override
            public void onExpandStateChanged(TextView textView, boolean isExpanded) {
                Toast.makeText(getActivity(), isExpanded ? "Expanded" : "Collapsed", Toast.LENGTH_SHORT).show();
            }
        });
        expandTextView.setText(getString(R.string.single_buy_rules));
        payButton = (Button) contentView.findViewById(R.id.payButton);
        goLoginText = (TextView) contentView.findViewById(R.id.goLoginText);
        recycleView.setAdapter(adapter);
    }

}
