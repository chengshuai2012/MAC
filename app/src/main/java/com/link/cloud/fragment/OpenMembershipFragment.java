package com.link.cloud.fragment;

import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.link.cloud.R;
import com.link.cloud.base.BaseFragment;
import com.link.cloud.widget.ExpandableTextView;

public class OpenMembershipFragment extends BaseFragment {


    private ExpandableTextView expandTextView;


    @Override
    protected int getContentViewId() {
        return R.layout.fragment_openmembersship;
    }

    @Override
    public void onInflateView(View contentView) {
        super.onInflateView(contentView);
        intView(contentView);
    }

    private void intView(View contentView) {
        expandTextView = (ExpandableTextView) contentView.findViewById(R.id.expand_text_view);
        expandTextView.setOnExpandStateChangeListener(new ExpandableTextView.OnExpandStateChangeListener() {
            @Override
            public void onExpandStateChanged(TextView textView, boolean isExpanded) {
                Toast.makeText(getActivity(), isExpanded ? "Expanded" : "Collapsed", Toast.LENGTH_SHORT).show();
            }
        });
        expandTextView.setText(getString(R.string.single_buy_rules));
    }
}
