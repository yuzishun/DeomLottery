package com.example.yuzishun.deomlottery;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.os.AsyncTask;
import android.os.Environment;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import com.alibaba.fastjson.JSON;
import com.azhon.appupdate.config.UpdateConfiguration;
import com.azhon.appupdate.listener.OnButtonClickListener;
import com.azhon.appupdate.listener.OnDownloadListener;
import com.example.yuzishun.deomlottery.base.BaseActivity;
import com.example.yuzishun.deomlottery.documentary.activity.DocumentaryFragment;
import com.example.yuzishun.deomlottery.main.activity.MainFragment;
import com.example.yuzishun.deomlottery.model.UpdataBean;
import com.example.yuzishun.deomlottery.my.activity.MyFragment;
import com.example.yuzishun.deomlottery.net.OkhttpUtlis;
import com.example.yuzishun.deomlottery.net.Url;
import com.example.yuzishun.deomlottery.score.ScoreFragment;
import com.example.yuzishun.deomlottery.sunsheet.activity.SunSheetFragment;
import com.example.yuzishun.deomlottery.utils.StatusBarUtil;

import java.io.File;
import java.io.IOException;

import butterknife.ButterKnife;
import butterknife.OnClick;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

/**
 * 主页面mainactivity
 * 这里记录一下竞猜足球的刷新，篮球的刷新，两个页面的融合
 *
 */
public class MainActivity extends BaseActivity implements OnButtonClickListener, OnDownloadListener {
    public static MainActivity intentsat;
    private int mIndex=0;
    private Fragment[] mFragments;
    private int packagecode;

    private com.azhon.appupdate.manager.DownloadManager manager;

    @Override
    public int intiLayout() {
        return R.layout.activity_main;
    }

    @Override
    public void initView() {
        ButterKnife.bind(this);
        packagecode = packageCode(this);
        AsyncTask.execute(new Runnable() {
            @Override
            public void run() {
                updata();


            }
        });

        initFragment();

        intentsat = this;
    }

    private void updata() {
        OkhttpUtlis okhttpUtlis = new OkhttpUtlis();
        okhttpUtlis.GetAsynMap(Url.baseUrl + "app/version_up/getVersionUp?code=" + packagecode, new Callback() {
//                    okhttpUtlis.GetAsynMap("http://192.168.1.9/app/version_up/getVersionUp?code=" + packagecode, new Callback() {

                @Override
            public void onFailure(Call call, IOException e) {

            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                final String result  = response.body().string();
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        final UpdataBean updataBean = JSON.parseObject(result,UpdataBean.class);
                        if(updataBean.getCode()==10000){

                            if(updataBean.getData().getCode()>0){





                                UpdateConfiguration configuration = new UpdateConfiguration()
                                        //输出错误日志
                                        .setEnableLog(true)
                                        //设置自定义的下载
                                        //.setHttpManager()
                                        //下载完成自动跳动安装页面
                                        .setJumpInstallPage(true)
                                        //设置对话框背景图片 (图片规范参照demo中的示例图)
                                        //.setDialogImage(R.drawable.ic_dialog)
                                        //设置按钮的颜色
                                        //.setDialogButtonColor(Color.parseColor("#E743DA"))
                                        //设置按钮的文字颜色
                                        .setDialogButtonTextColor(Color.WHITE)
                                        //支持断点下载
                                        .setBreakpointDownload(true)
                                        //设置是否显示通知栏进度
                                        .setShowNotification(true)
                                        //设置是否提示后台下载toast
                                        .setShowBgdToast(false)
                                        //设置强制更新
                                        .setForcedUpgrade(true)
                                        //设置对话框按钮的点击监听
                                        .setButtonClickListener(MainActivity.this)
                                        //设置下载过程的监听
                                        .setOnDownloadListener(MainActivity.this);

                                manager = com.azhon.appupdate.manager.DownloadManager.getInstance(MainActivity.this);
                                manager.setApkName("appupdate.apk")
                                        .setApkUrl(updataBean.getData().getLocation())
                                        .setSmallIcon(R.mipmap.ic_launcher)
                                        .setShowNewerToast(true)
                                        .setConfiguration(configuration)
                                        //这是bug提交改的空指针path
                                        .setDownloadPath(Environment.getExternalStorageDirectory() + "/AppUpdate")
                                        .setApkVersionCode(updataBean.getData().getCode())
                                        .setApkVersionName(updataBean.getData().getVersion())
                                        .setAuthorities(getPackageName())
                                        .setApkDescription(updataBean.getData().getMsg())
                                        .download();





                            }else if(updataBean.getData().getCode()==0){


                            }else if(updataBean.getData().getCode()<0){

                                show(updataBean.getData().getMsg());

                            }

                        }else {


                        }


                    }
                });


            }
        });




    }
    @SuppressLint("NewApi")
    private void show(String msg){
        //dialog简单展示

        Dialog mDialog  = new Dialog(this,R.style.dialog);
        View view_layout = LayoutInflater.from(this).inflate(R.layout.updatadialog_item, null);
        mDialog.setContentView(view_layout);
        mDialog.setCancelable(false);
        mDialog.setCanceledOnTouchOutside(false);
// 设置宽高为match_parent，不要去算出来屏幕宽高再赋值哦，因为有些
// 有虚拟按键的手机上计算出来的高度不一定准确，所以dialog不会全屏

// 设置dialog距屏幕的边距都为0
        mDialog.getWindow().getDecorView().setPadding(0,0,0,0);
        TextView textView = mDialog.findViewById(R.id.toast_text);
        textView.setText(msg+"");
        mDialog.show();

    }



    @Override
    public void initData() {

    }


    //加载fragment页面
    private void initFragment() {
        Fragment mTab_01 = new MainFragment();
        Fragment mTab_02 = new DocumentaryFragment();
        Fragment mTab_03 = new SunSheetFragment();
        Fragment mTab_04 = new ScoreFragment();
        Fragment mTab_05 = new MyFragment();


        //添加到数组
        mFragments = new Fragment[]{mTab_01,mTab_02,mTab_03,mTab_04,mTab_05};

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
                StatusBarUtil.setRootViewFitsSystemWindows(this, true);
                StatusBarUtil.setStatusBarDarkTheme(MainActivity.this, true);

                setIndexSelected(0);
                break;
            case R.id.id_tab_02:
                StatusBarUtil.setRootViewFitsSystemWindows(this, true);
                StatusBarUtil.setStatusBarDarkTheme(MainActivity.this, true);

                setIndexSelected(1);

                break;
            case R.id.id_tab_03:
                setIndexSelected(2);

                break;
            case R.id.id_tab_04:
                StatusBarUtil.setRootViewFitsSystemWindows(this, true);
                StatusBarUtil.setStatusBarDarkTheme(MainActivity.this, true);

                setIndexSelected(3);

                break;
            case R.id.id_tab_05:
                setIndexSelected(4);
                StatusBarUtil.setRootViewFitsSystemWindows(this, false);
                StatusBarUtil.setStatusBarDarkTheme(MainActivity.this, false);
//                StatusBarUtil.setTranslucentStatus(this);

                break;
        }

    }
    public static int packageCode(Context context) {
        PackageManager manager = context.getPackageManager();
        int code = 0;
        try {
            PackageInfo info = manager.getPackageInfo(context.getPackageName(), 0);
            code = info.versionCode;
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
        return code;
    }

    @Override
    public void onButtonClick(int id) {

    }

    @Override
    public void start() {

    }

    @Override
    public void downloading(int max, int progress) {

    }

    @Override
    public void done(File apk) {

    }

    @Override
    public void cancel() {

    }

    @Override
    public void error(Exception e) {

    }

    @Override
    protected void onResume() {
        super.onResume();

    }
}
