package com.example.yuzishun.newdeom.score.utils;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.HorizontalScrollView;
import android.widget.LinearLayout;

import com.example.yuzishun.newdeom.R;

/**
 * Created by yuzishun on 2018/7/12.
 */

public class ContentFragmentScore extends Fragment {

    private static final String KEY = "title";
    private android.support.v7.widget.RecyclerView Score_RecyclerView;

    private View view;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        if(view ==null) {
            view = View.inflate(getActivity(), R.layout.content_score, null);
            Score_RecyclerView = view.findViewById(R.id.Score_RecyclerView);


        }
        return view;
    }
    @Override
    public void onDestroy() {
        super.onDestroy();
        try {
            if (view != null) {
                ((ViewGroup) view.getParent()).removeView(view);
            }
        }catch (Exception e){

        }


    }
    /**
     * fragment静态传值
     */
    public static ContentFragmentScore newInstance(){
        ContentFragmentScore fragment = new ContentFragmentScore();
        return fragment;
    }

}
