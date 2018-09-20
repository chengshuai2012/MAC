package com.link.cloud.fragment;

import android.os.Bundle;
import android.os.Handler;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;

import com.link.cloud.R;

import com.shizhefei.fragment.LazyFragment;

import java.util.ArrayList;


/**
 * Created by LuckyJayce on 2016/8/11.
 */
public class YearFragment extends LazyFragment {
    public static final String INTENT_INT_POSITION = "intent_int_position";

    @Override
    protected void onCreateViewLazy(Bundle savedInstanceState) {
        super.onCreateViewLazy(savedInstanceState);

        Log.d("pppp", "onCreateViewLazy  " + this+" "+savedInstanceState);

        int position = getArguments().getInt(INTENT_INT_POSITION);

        setContentView(R.layout.fragment_tabmain_item);

    }
}
