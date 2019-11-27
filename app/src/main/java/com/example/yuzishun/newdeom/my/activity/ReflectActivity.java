package com.example.yuzishun.newdeom.my.activity;

import android.content.Intent;
import android.graphics.drawable.BitmapDrawable;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.TextView;

import com.alibaba.fastjson.JSON;
import com.bigkoo.pickerview.adapter.ArrayWheelAdapter;
import com.bumptech.glide.Glide;
import com.contrarywind.listener.OnItemSelectedListener;
import com.contrarywind.view.WheelView;
import com.example.yuzishun.newdeom.R;
import com.example.yuzishun.newdeom.base.BaseActivity;
import com.example.yuzishun.newdeom.base.Content;
import com.example.yuzishun.newdeom.login.custom.ClearEditText;
import com.example.yuzishun.newdeom.model.CodeBean;
import com.example.yuzishun.newdeom.net.OkhttpUtlis;
import com.example.yuzishun.newdeom.net.Url;
import com.example.yuzishun.newdeom.utils.ToastUtil;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

public class ReflectActivity extends BaseActivity implements View.OnClickListener {
    @BindView(R.id.title_text)
    TextView title_text;
    @BindView(R.id.image_back)
    LinearLayout image_back;

    @BindView(R.id.Text_card_bank)
    TextView Text_card_bank;
    @BindView(R.id.Text_card_no)
    TextView Text_card_no;

    @BindView(R.id.button_t)
    Button button_t;
    @BindView(R.id.money_edittext)
    ClearEditText money_edittext;
    private Double available_balance;
    @BindView(R.id.pick_view)
    LinearLayout pick_view;
    private PopupWindow popupWindow;
    private View contentView;
    @BindView(R.id.Text_ke)
    TextView Text_ke;
    @BindView(R.id.bank_imag)
    ImageView bank_imag;
    private int card_id;
    @Override
    public int intiLayout() {
        return R.layout.activity_reflect;
    }

    @Override
    public void initView() {
        ButterKnife.bind(this);
        title_text.setText("提现");
        Intent intent = getIntent();
        available_balance = Double.parseDouble(intent.getStringExtra("available_balance"));
        image_back.setOnClickListener(this);
        pick_view.setOnClickListener(this);
        button_t.setOnClickListener(this);
        Text_ke.setText("可提现余额"+available_balance+"元");
        card_id = Content.list_bank.get(0).getId();
        Text_card_no.setText("尾号"+Content.list_bank.get(0).getCard_no().substring(Content.list_bank.get(0).getCard_no().length()-4,Content.list_bank.get(0).getCard_no().length())+"的储蓄卡");
        Text_card_bank.setText(Content.list_bank.get(0).getCard_bank());
        Glide.with(this).load(Content.list_bank.get(0).getCard_img()).into(bank_imag);

    }



    @Override
    public void initData() {

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.image_back:
                    finish();

                break;
            case R.id.button_t:

                if(money_edittext.getText().toString().equals("")){
                    ToastUtil.showToast1(this,"提现金额不能为空");
                }else {
                    if(Double.parseDouble(money_edittext.getText().toString().trim())>available_balance){
                        ToastUtil.showToast1(this,"输入的金额不能大于可提现金额");


                    }else {


                        refilect();


                    }


                }

                break;
            case R.id.pick_view:

                //加载弹出框的布局
                contentView = LayoutInflater.from(ReflectActivity.this).inflate(
                        R.layout.pop_refle_while, null);


                popupWindow = new PopupWindow(contentView, LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
                popupWindow.setFocusable(true);// 取得焦点
                //注意  要是点击外部空白处弹框消息  那么必须给弹框设置一个背景色  不然是不起作用的
                popupWindow.setBackgroundDrawable(new BitmapDrawable());
                //点击外部消失
                popupWindow.setOutsideTouchable(true);
                //设置可以点击
                popupWindow.setTouchable(true);
                //进入退出的动画，指定刚才定义的style
                popupWindow.setAnimationStyle(R.style.mypopwindow_anim_style);

                // 按下android回退物理键 PopipWindow消失解   v
                popupWindow.showAtLocation(contentView, Gravity.BOTTOM, 0, 0);
                final WheelView wheel = contentView.findViewById(R.id.wheel);
                TextView Text_sure = contentView.findViewById(R.id.Text_sure);
                TextView Text_cancel = contentView.findViewById(R.id.Text_cancel);
                final List<String> mOptionsItems = new ArrayList<>();
                for (int i = 0; i < Content.list_bank.size(); i++) {

                    mOptionsItems.add("尾号"+Content.list_bank.get(i).getCard_no().substring(Content.list_bank.get(i).getCard_no().length()-4,Content.list_bank.get(i).getCard_no().length())+"的"+
                            Content.list_bank.get(i).getCard_bank());

                }
                wheel.setCyclic(false);
                wheel.setAdapter(new ArrayWheelAdapter(mOptionsItems));
                wheel.setOnItemSelectedListener(new OnItemSelectedListener() {
                    @Override
                    public void onItemSelected(int index) {
                        card_id = Content.list_bank.get(index).getId();


                    }
                });


                Text_sure.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        card_id = Content.list_bank.get(wheel.getCurrentItem()).getId();
                        Glide.with(ReflectActivity.this).load(Content.list_bank.get(wheel.getCurrentItem()).getCard_img()).into(bank_imag);

                        Text_card_no.setText("尾号"+Content.list_bank.get(wheel.getCurrentItem()).getCard_no().substring(Content.list_bank.get(wheel.getCurrentItem()).getCard_no().length()-4,Content.list_bank.get(wheel.getCurrentItem()).getCard_no().length())+"的储蓄卡");
                        Text_card_bank.setText(Content.list_bank.get(wheel.getCurrentItem()).getCard_bank());
                        popupWindow.dismiss();
                    }
                });
                Text_cancel.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        popupWindow.dismiss();
                    }
                });


                break;

        }

    }

    private void refilect() {
        HashMap<String,String> hashMap = new HashMap<>();
        hashMap.put("card_id",card_id+"");
        hashMap.put("withdraw_type",1+"");
        hashMap.put("withdraw_price",money_edittext.getText().toString().trim()+"");
        OkhttpUtlis okhttpUtlis = new OkhttpUtlis();

        okhttpUtlis.PostAsynMap(Url.baseUrl+"app/account/addWithdraw", hashMap, new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {

            }

            @Override
            public void onResponse(final Call call, Response response) throws IOException {
            final String result = response.body().string();
            runOnUiThread(new Runnable() {
                @Override
                public void run() {

                    CodeBean codeBean  = JSON.parseObject(result,CodeBean.class);
                    if(codeBean.getCode()==10000){
                        finish();
                        ToastUtil.showToast1(ReflectActivity.this,"提交成功");
                    }else {
                        ToastUtil.showToast1(ReflectActivity.this,codeBean.getMsg()+"");

                    }

                }
            });


            }
        });




    }
}
