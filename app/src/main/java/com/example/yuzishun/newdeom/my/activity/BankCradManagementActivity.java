package com.example.yuzishun.newdeom.my.activity;

import android.content.Intent;
import android.graphics.Color;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.alibaba.fastjson.JSON;
import com.example.yuzishun.newdeom.MainActivity;
import com.example.yuzishun.newdeom.R;
import com.example.yuzishun.newdeom.base.BaseActivity;
import com.example.yuzishun.newdeom.login.custom.CheckEditForButton;
import com.example.yuzishun.newdeom.model.BankMangmentBean;
import com.example.yuzishun.newdeom.model.CodeBean;
import com.example.yuzishun.newdeom.my.adapter.BankCradAdapter;
import com.example.yuzishun.newdeom.net.OkhttpUtlis;
import com.example.yuzishun.newdeom.net.Url;
import com.example.yuzishun.newdeom.utils.RegexUtils;
import com.example.yuzishun.newdeom.utils.ToastUtil;
import com.yanzhenjie.recyclerview.swipe.SwipeMenu;
import com.yanzhenjie.recyclerview.swipe.SwipeMenuBridge;
import com.yanzhenjie.recyclerview.swipe.SwipeMenuCreator;
import com.yanzhenjie.recyclerview.swipe.SwipeMenuItem;
import com.yanzhenjie.recyclerview.swipe.SwipeMenuItemClickListener;
import com.yanzhenjie.recyclerview.swipe.SwipeMenuRecyclerView;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

/**
 * 银行卡管理页面
 *
 */
public class BankCradManagementActivity extends BaseActivity implements View.OnClickListener {

    @BindView(R.id.BankRecyclerView)
    SwipeMenuRecyclerView BankRecyclerView;
    @BindView(R.id.title_text)
    TextView title_text;
    @BindView(R.id.image_back_left)
    LinearLayout image_back_left;
    @BindView(R.id.image_back_right)
    LinearLayout image_back_right;
    private BankCradAdapter bankCradAdapter;
    private List<List<BankMangmentBean.DataBean>> list = new ArrayList<>();

    @Override
    public int intiLayout() {
        return R.layout.activity_bank_crad_management;
    }

    @Override
    public void initView() {
        ButterKnife.bind(this);
        title_text.setText(R.string.BankCradManagement);
        image_back_left.setOnClickListener(this);
        image_back_right.setOnClickListener(this);

        BankRecyclerView.setLayoutManager(new LinearLayoutManager(this));
//        BankRecyclerView.loadMoreFinish(false, true);
//        BankRecyclerView.setLoadMoreListener(new SwipeMenuRecyclerView.LoadMoreListener() {
//            @Override
//            public void onLoadMore() {
//
//
//
//            }
//        });

    }

    @Override
    public void initData() {


        // 创建菜单：
        SwipeMenuCreator mSwipeMenuCreator = new SwipeMenuCreator() {
            @Override
            public void onCreateMenu(SwipeMenu leftMenu, SwipeMenu rightMenu, int viewType) {

                int width = getResources().getDimensionPixelOffset(R.dimen.d100);
                int height = ViewGroup.LayoutParams.MATCH_PARENT;
                // 注意：哪边不想要菜单，那么不要添加即可。
                SwipeMenuItem addItem = new SwipeMenuItem(BankCradManagementActivity.this)
                        .setBackground(R.color.login_red)
                        .setText("删除")
                        .setTextColor(Color.WHITE)
                        .setWidth(width)
                        .setHeight(height);
                rightMenu.addMenuItem(addItem); // 添加菜单到右侧。
            }
        };

        // 设置监听器。
        BankRecyclerView.setSwipeMenuCreator(mSwipeMenuCreator);

        SwipeMenuItemClickListener mMenuItemClickListener = new SwipeMenuItemClickListener() {
            @Override
            public void onItemClick(SwipeMenuBridge menuBridge) {
                // 任何操作必须先关闭菜单，否则可能出现Item菜单打开状态错乱。
                menuBridge.closeMenu();
                int direction = menuBridge.getDirection(); // 左侧还是右侧菜单。
                int adapterPosition = menuBridge.getAdapterPosition(); // RecyclerView的Item的position。
                int menuPosition = menuBridge.getPosition(); // 菜单在RecyclerView的Item中的Position。
                delete(list.get(0).get(adapterPosition).getId(), adapterPosition);




//                Toast.makeText(BankCradManagementActivity.this, direction + " " + adapterPosition + " " + menuPosition, Toast.LENGTH_SHORT).show();
            }
        };
        // 菜单点击监听。
        BankRecyclerView.setSwipeMenuItemClickListener(mMenuItemClickListener);


    }

    private void delete(int id, final int adapterPosition) {
        HashMap<String,String> hashMap = new HashMap<>();
        hashMap.put("id",id+"");
        OkhttpUtlis okhttpUtlis = new OkhttpUtlis();

        okhttpUtlis.PostAsynMap(Url.baseUrl + "user/delUserBankCard", hashMap, new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {

            }

            @Override
            public void onResponse(final Call call, Response response) throws IOException {
                final String result = response.body().string();
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {

                        CodeBean codeBean = JSON.parseObject(result,CodeBean.class);
                        if(codeBean.getCode()==10000){
                            ToastUtil.showToast1(BankCradManagementActivity.this, codeBean.getMsg()+"");
                            bankCradAdapter.removeData(adapterPosition);
                        }else {
                            ToastUtil.showToast1(BankCradManagementActivity.this, codeBean.getMsg()+"");

                        }



                    }
                });


            }
        });

    }

    private void showlist() {
        HashMap<String,String> hashMap = new HashMap<>();
        OkhttpUtlis okhttpUtlis = new OkhttpUtlis();
        okhttpUtlis.PostAsynMap(Url.baseUrl + "user/getUserBankCardList", hashMap, new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {

            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {

                final String result = response.body().string();
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {


                        try {
                            JSONObject jsonObject = new JSONObject(result);
                            int code = jsonObject.getInt("code");
                            String msg = jsonObject.getString("msg");
                            if(code==10000){
                                BankMangmentBean bankMangmentBean = JSON.parseObject(result,BankMangmentBean.class);
                                list.add(bankMangmentBean.getData());
                                if(list.get(0).size()==0){
                                    ToastUtil.showToast1(BankCradManagementActivity.this, "咩有银行卡呦");

                                    //在这里操作没有银行卡的操作,首先显示这个
                                }else {

                                    bankCradAdapter = new BankCradAdapter(BankCradManagementActivity.this,list);
                                    BankRecyclerView.setAdapter(bankCradAdapter);
                                }


                            }else {
                                ToastUtil.showToast1(BankCradManagementActivity.this, msg+"");

                            }

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }


                    }
                });

            }
        });


    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.image_back_left:
                finish();
            break;
            case R.id.image_back_right:
                startActivity(new Intent(this,BindingBankActivity.class));
            break;

        }


    }

    @Override
    protected void onResume() {
        super.onResume();
        showlist();
    }
}
