package com.example.yuzishun.newdeom.base;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import butterknife.ButterKnife;
import butterknife.Unbinder;


/**
 * Created by yuzishun on 2018/7/2.
 */
//所有Fragment 的 基类
public abstract class Basefragment extends Fragment {
    private Bundle savedInstanceState;
    private View mContentView;
    private Context mContext;
    private Unbinder unbinder;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        if(mContentView==null){
            mContentView = inflater.inflate(setLayoutResourceID(),container,false);

        }
        mContext = getContext();
        unbinder = ButterKnife.bind(this, mContentView);


        savedInstanceState = savedInstanceState;
        init();
        setUpView();
        setUpData();
        return mContentView;
    }

    /**
     * 此方法用于返回Fragment设置ContentView的布局文件资源ID
     *
     * @return 布局文件资源ID
     */
    protected abstract int setLayoutResourceID();

    /**
     * 一些View的相关操作
     */
    protected abstract void setUpView();

    /**
     * 一些Data的相关操作
     */
    protected abstract void setUpData();

    /**
     * 此方法用于初始化成员变量及获取Intent传递过来的数据
     * 注意：这个方法中不能调用所有的View，因为View还没有被初始化，要使用View在initView方法中调用
     */
    protected void init() {


    }

    public View getContentView() {
        return mContentView;
    }

    public Context getMContext() {
        return mContext;
    }

    @Override
    public void onDestroyView() {
        if (this.unbinder != null) {
            this.unbinder.unbind();
        }
        super.onDestroyView();


    }

}

