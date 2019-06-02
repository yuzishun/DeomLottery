package com.example.yuzishun.newdeom;

import android.content.Intent;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Toast;

import com.example.yuzishun.newdeom.base.BaseActivity;
import com.example.yuzishun.newdeom.documentary.activity.DocumentaryFragment;
import com.example.yuzishun.newdeom.main.activity.MainFragment;
import com.example.yuzishun.newdeom.my.activity.MyFragment;
import com.example.yuzishun.newdeom.score.activity.ScoreFragment;

import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * 主页面
 *
 */
public class MainActivity extends BaseActivity {
    public static MainActivity intentsat;
    private int mIndex=0;
    private Fragment[] mFragments;

    @Override
    public int intiLayout() {
        return R.layout.activity_main;
    }

    @Override
    public void initView() {
        ButterKnife.bind(this);
        initFragment();
        intentsat = this;
    }

    @Override
    public void initData() {

    }


    //加载fragment页面
    private void initFragment() {
        Fragment mTab_01 = new MainFragment();
        Fragment mTab_02 = new DocumentaryFragment();
        Fragment mTab_03 = new ScoreFragment();
        Fragment mTab_04 = new MyFragment();


        //添加到数组
        mFragments = new Fragment[]{mTab_01,mTab_02,mTab_03,mTab_04};

        //开启事务

        FragmentTransaction ft =
                getSupportFragmentManager().beginTransaction();


        //添加首页
        ft.add(R.id.Lacontent,mTab_01).commit();

        //默认设置为第0个
        setIndexSelected(0);



    }


    private void setIndexSelected(int index) {

        if(mIndex==index){
            return;
        }
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction ft              = fragmentManager.beginTransaction();
        //隐藏
        ft.hide(mFragments[mIndex]);
        //判断是否添加
        if(!mFragments[index].isAdded()){
            ft.add(R.id.Lacontent,mFragments[index]).show(mFragments[index]);
        }else {
            ft.show(mFragments[index]);
        }


        ft.commit();
        //再次赋值
        mIndex=index;
    }


    @OnClick({R.id.id_tab_01, R.id.id_tab_02, R.id.id_tab_03, R.id.id_tab_04, R.id.id_tab_05})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.id_tab_01:
                setIndexSelected(0);
                break;
            case R.id.id_tab_02:
                setIndexSelected(1);
                break;
            case R.id.id_tab_03:
                startActivity(new Intent(this,WebViewCustomerActivity.class));
                break;
            case R.id.id_tab_04:
                setIndexSelected(2);
                break;
            case R.id.id_tab_05:
                setIndexSelected(3);
                break;
        }

    }

}
