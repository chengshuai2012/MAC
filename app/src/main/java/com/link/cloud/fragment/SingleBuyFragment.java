package com.link.cloud.fragment;

import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.link.cloud.R;
import com.link.cloud.base.BaseFragment;
import com.link.cloud.widget.ExpandableTextView;

/**
 * @author qianlu
 * @date 2018/9/28.
 * GitHub：qiandailu
 * email：zar.l@qq.com
 * description：
 */
public class SingleBuyFragment  extends BaseFragment{
    private TextView expandText;
    private ExpandableTextView expandTextView;
    private ImageButton expandCollapse;

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

//        expandText = (TextView) contentView.findViewById(R.id.expandable_text);
        expandTextView = (ExpandableTextView) contentView.findViewById(R.id.expand_text_view);
//        expandCollapse = (ImageButton) contentView.findViewById(R.id.expandCollapse);

        expandTextView.setOnExpandStateChangeListener(new ExpandableTextView.OnExpandStateChangeListener() {
            @Override
            public void onExpandStateChanged(TextView textView, boolean isExpanded) {
                Toast.makeText(getActivity(), isExpanded ? "Expanded" : "Collapsed", Toast.LENGTH_SHORT).show();
            }
        });

        expandTextView.setText(getString(R.string.single_buy_rules));

    }
}
