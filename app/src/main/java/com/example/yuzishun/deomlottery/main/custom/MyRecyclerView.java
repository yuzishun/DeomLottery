package com.example.yuzishun.deomlottery.main.custom;

import android.support.v7.widget.RecyclerView;
import android.view.View;

/**
 * Created by yuzishun on 2019/4/24.
 * 自定义recylcerview适配嵌套
 */

public class MyRecyclerView extends RecyclerView{

    public MyRecyclerView(android.content.Context context, android.util.AttributeSet attrs){
        super(context, attrs);
    }
    public MyRecyclerView(android.content.Context context){
        super(context);
    }
    /**
     * 设置不滚动
     */
    @Override
    public void onMeasure(int widthMeasureSpec, int heightMeasureSpec){
        int expandSpec = View.MeasureSpec.makeMeasureSpec(Integer.MAX_VALUE >> 2, View.MeasureSpec.AT_MOST);
        super.onMeasure(widthMeasureSpec, expandSpec);
    }


}
