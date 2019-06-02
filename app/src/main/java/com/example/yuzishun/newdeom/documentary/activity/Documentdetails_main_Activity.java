package com.example.yuzishun.newdeom.documentary.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.alibaba.fastjson.JSON;
import com.bumptech.glide.Glide;
import com.example.yuzishun.newdeom.R;
import com.example.yuzishun.newdeom.base.BaseActivity;
import com.example.yuzishun.newdeom.base.Content;
import com.example.yuzishun.newdeom.documentary.custom.AmountView;
import com.example.yuzishun.newdeom.model.BasketballOrderBean;
import com.example.yuzishun.newdeom.model.CodeBean;
import com.example.yuzishun.newdeom.model.FootBallOrderBean;
import com.example.yuzishun.newdeom.model.MainOrderBasketBean;
import com.example.yuzishun.newdeom.model.MainOrderFootBean;
import com.example.yuzishun.newdeom.my.activity.SendDocumentActivity;
import com.example.yuzishun.newdeom.my.custom.MyTableTextView;
import com.example.yuzishun.newdeom.net.OkhttpUtlis;
import com.example.yuzishun.newdeom.net.Url;
import com.example.yuzishun.newdeom.utils.ToastUtil;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

/***
 * 跟单大厅里面的的详情界面
 *
 */
public class Documentdetails_main_Activity extends BaseActivity implements View.OnClickListener {

    @BindView(R.id.image_back)
    LinearLayout image_back;
    @BindView(R.id.title_text)
    TextView title_text;
    @BindView(R.id.MyTable)
    LinearLayout MyTable;
    @BindView(R.id.MyTable_gen)
    LinearLayout MyTable_gen;
    private String[] name={"用户名","跟单金额","跟单时间","奖金"};
    @BindView(R.id.button_senddocument)
    Button button_senddocument;
    @BindView(R.id.amountView)
    AmountView amountView;

    @BindView(R.id.layout_dec)
    LinearLayout layout_dec;
    @BindView(R.id.text_dec)
    TextView text_dec;
    @BindView(R.id.layout_genv)
    RelativeLayout layout_genv;
    @BindView(R.id.layout_genlistv)
    LinearLayout layout_genlistv;
    @BindView(R.id.layout_buttonv)
    RelativeLayout layout_buttonv;
    @BindView(R.id.Layout_genbottom)
    RelativeLayout Layout_genbottom;
    @BindView(R.id.layout_publicv)
    RelativeLayout layout_publicv;
    @BindView(R.id.View_genv)
    View View_genv;
    @BindView(R.id.usericon)
    ImageView usericon;
    @BindView(R.id.username)
    TextView username;
    @BindView(R.id.state)
    TextView state;
    @BindView(R.id.usedr_id)
    TextView usedr_id;
    @BindView(R.id.order_money)
    TextView order_money;
    @BindView(R.id.Winning_money)
    TextView Winning_money;
    @BindView(R.id.add_money)
    TextView add_money;
    @BindView(R.id.Text_Scene)
    TextView Text_Scene;
    @BindView(R.id.Text_bunch_two)
    TextView Text_bunch_two;
    @BindView(R.id.Text_bunch_three)
    TextView Text_bunch_three;
    @BindView(R.id.Text_bunch_four)
    TextView Text_bunch_four;
    @BindView(R.id.Text_bunch_fire)
    TextView Text_bunch_fire;
    @BindView(R.id.Text_bunch_six)
    TextView Text_bunch_six;
    @BindView(R.id.Text_bunch_seven)
    TextView Text_bunch_seven;
    @BindView(R.id.Text_bunch_entry)
    TextView Text_bunch_entry;
    @BindView(R.id.layout_bunch)
    LinearLayout layout_bunch;
    @BindView(R.id.Text_Multiple)
    TextView Text_Multiple;
    @BindView(R.id.Text_order_id)
    TextView Text_order_id;
    @BindView(R.id.Text_order_data)
    TextView Text_order_data;
    @BindView(R.id.multiple_price)
    TextView multiple_pric;
    @BindView(R.id.plan_profits)
    TextView plan_profits;
    @BindView(R.id.gendan_bumber)
    TextView gendan_bumber;
    private int flag,type,plan_id,multiple=1;
    private Double multiple_price;
    @BindView(R.id.text_pluc)
    TextView text_pluc;
    @BindView(R.id.buttom_money)
    TextView buttom_money;

    @BindView(R.id.text_gen)
    TextView text_gen;
    @Override
    public int intiLayout() {
        return R.layout.activity_documentdetails;
    }

    @Override
    public void initView() {
        ButterKnife.bind(this);
        Intent intent = getIntent();
        flag = intent.getIntExtra("flag",0);
        type = intent.getIntExtra("type",0);
        plan_id = intent.getIntExtra("plan_id",0);



        //0就是大厅里面的详情，1的话就是大神首页里面的详情
        if(flag==0){
            layout_buttonv.setVisibility(View.GONE);

            title_text.setText("跟单详情");

        }else {
            layout_buttonv.setVisibility(View.GONE);

            title_text.setText("跟单详情");

        }
        image_back.setOnClickListener(this);
        text_gen.setOnClickListener(this);
        button_senddocument.setOnClickListener(this);
        initnet();
        //倍数选择
        amountView.setGoods_storage(50);
        amountView.setOnAmountChangeListener(new AmountView.OnAmountChangeListener() {
            @Override
            public void onAmountChange(View view, int amount) {
                multiple = amount;
                buttom_money.setText(amount*multiple_price+"元");
//                Toast.makeText(getApplicationContext(), "Amount=>  " + amount, Toast.LENGTH_SHORT).show();
            }
        });

    }

    private void initnet() {
        OkhttpUtlis okhttpUtlis = new OkhttpUtlis();
        okhttpUtlis.GetAsynMap(Url.baseUrl + "order_plan/getOrderPlanInfo?plan_id=" + plan_id, new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {

            }

            @Override
            public void onResponse(final Call call, Response response) throws IOException {
                final String result = response.body().string();
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {

                        try {
                            JSONObject jsonObject = new JSONObject(result);
                            int code = jsonObject.getInt("code");
                            String msg = jsonObject.getString("msg");

                            if(code==10000){

                                //type是0的时候是足球不是0则是篮球
                                if(type==0){
                                    MainOrderFootBean mainOrderFootBean = com.alibaba.fastjson.JSONObject.parseObject(result,MainOrderFootBean.class);

                                    Glide.with(Documentdetails_main_Activity.this).load(mainOrderFootBean.getData().getImg_head()).asBitmap().centerCrop().into(usericon);
                                    username.setText(mainOrderFootBean.getData().getUname());
                                    buttom_money.setText(mainOrderFootBean.getData().getMultiple_price()+"");
                                    switch (mainOrderFootBean.getData().getOrder_status()){
                                        case -1:
                                            state.setText("申请退款");
                                            order_money.setText(mainOrderFootBean.getData().getOrder_price());
                                            Winning_money.setText(mainOrderFootBean.getData().getBonus_price());
                                            add_money.setText("0");
                                            break;
                                        case 0:
                                            state.setText("待出票");
                                            order_money.setText(mainOrderFootBean.getData().getOrder_price());
                                            Winning_money.setText(mainOrderFootBean.getData().getBonus_price());
                                            add_money.setText("0");
                                            break;
                                        case 1:
                                            state.setText(mainOrderFootBean.getData().getCut_off_time()+"截止");
                                            order_money.setText(mainOrderFootBean.getData().getOrder_price());
                                            Winning_money.setText("未开奖");

                                            add_money.setText("0");
                                            if(mainOrderFootBean.getData().getPlan_status()==0){
                                                text_pluc.setText("完全公开");
                                                layout_publicv.setVisibility(View.GONE);
                                                MyTable.setVisibility(View.VISIBLE);
                                            }else if(mainOrderFootBean.getData().getPlan_status()==1){
                                                text_pluc.setText("跟单后公开");
                                                layout_publicv.setVisibility(View.VISIBLE);
                                                MyTable.setVisibility(View.GONE);

                                            }else {
                                                text_pluc.setText("截止后公开");
                                                layout_publicv.setVisibility(View.VISIBLE);
                                                MyTable.setVisibility(View.GONE);

                                            }


                                            break;
                                        case 2:
                                            state.setText("恭喜中奖");
                                            order_money.setText(mainOrderFootBean.getData().getOrder_price());
                                            Winning_money.setText(mainOrderFootBean.getData().getBonus_price());
                                            add_money.setText(Double.parseDouble(mainOrderFootBean.getData().getBonus_price())*0.05+"");
                                            break;
                                        case 3:
                                            state.setText("再接再厉");
                                            order_money.setText(mainOrderFootBean.getData().getOrder_price());
                                            Winning_money.setText(mainOrderFootBean.getData().getBonus_price());
                                            add_money.setText("0");
                                            break;

                                        case -2:
                                            state.setText("已取消");
                                            order_money.setText(mainOrderFootBean.getData().getOrder_price());
                                            Winning_money.setText(mainOrderFootBean.getData().getBonus_price());
                                            add_money.setText("0");
                                            break;



//                                        订单状态【0：待出票，1：待开奖，2：中奖，3：未中奖，-1：申请退款，-2：已取消】
                                    }




                                            layout_buttonv.setVisibility(View.GONE);
                                            View_genv.setVisibility(View.VISIBLE);

                                    gendan_bumber.setText("跟单人数（"+mainOrderFootBean.getData().getPlan_follow_person()+"，共"+mainOrderFootBean.getData().getPlan_follow_price()+"元）");


                                    layout_genlistv.setVisibility(View.VISIBLE);

                                            multiple_pric.setText("单倍金额:"+mainOrderFootBean.getData().getMultiple_price());

                                            plan_profits.setText("方案提成:"+Double.parseDouble(mainOrderFootBean.getData().getPlan_profits())*100+"%");
                                            text_dec.setText(""+mainOrderFootBean.getData().getPlan_desc());




                                    Text_Scene.setText(mainOrderFootBean.getData().getGame_sum()+"场");
                                    Text_Multiple.setText(mainOrderFootBean.getData().getMultiple()+"倍");
                                    List<String> bunch = mainOrderFootBean.getData().getBunch();
                                    if(bunch.size()>2){
                                        layout_bunch.setVisibility(View.VISIBLE);

                                    }else {
                                        layout_bunch.setVisibility(View.GONE);


                                    }

                                    switch (bunch.size()){
                                        case 1:
                                            Text_bunch_two.setText(bunch.get(0)+"串一");
                                            break;
                                        case 2:
                                            Text_bunch_three.setVisibility(View.VISIBLE);
                                            Text_bunch_two.setText(bunch.get(0)+"串一");
                                            Text_bunch_three.setText(bunch.get(1)+"串一");

                                            break;
                                        case 3:
                                            Text_bunch_three.setVisibility(View.VISIBLE);
                                            Text_bunch_four.setVisibility(View.VISIBLE);
                                            Text_bunch_two.setText(bunch.get(0)+"串一");
                                            Text_bunch_three.setText(bunch.get(1)+"串一");
                                            Text_bunch_four.setText(bunch.get(2)+"串一");

                                            break;
                                        case 4:
                                            Text_bunch_three.setVisibility(View.VISIBLE);
                                            Text_bunch_four.setVisibility(View.VISIBLE);
                                            Text_bunch_fire.setVisibility(View.VISIBLE);
                                            Text_bunch_two.setText(bunch.get(0)+"串一");
                                            Text_bunch_three.setText(bunch.get(1)+"串一");
                                            Text_bunch_four.setText(bunch.get(2)+"串一");
                                            Text_bunch_fire.setText(bunch.get(3)+"串一");

                                            break;
                                        case 5:
                                            Text_bunch_three.setVisibility(View.VISIBLE);
                                            Text_bunch_four.setVisibility(View.VISIBLE);
                                            Text_bunch_fire.setVisibility(View.VISIBLE);
                                            Text_bunch_six.setVisibility(View.VISIBLE);
                                            Text_bunch_two.setText(bunch.get(0)+"串一");
                                            Text_bunch_three.setText(bunch.get(1)+"串一");
                                            Text_bunch_four.setText(bunch.get(2)+"串一");
                                            Text_bunch_fire.setText(bunch.get(3)+"串一");
                                            Text_bunch_six.setText(bunch.get(4)+"串一");

                                            break;
                                        case 6:
                                            Text_bunch_three.setVisibility(View.VISIBLE);
                                            Text_bunch_four.setVisibility(View.VISIBLE);
                                            Text_bunch_fire.setVisibility(View.VISIBLE);
                                            Text_bunch_six.setVisibility(View.VISIBLE);
                                            Text_bunch_seven.setVisibility(View.VISIBLE);
                                            Text_bunch_two.setText(bunch.get(0)+"串一");
                                            Text_bunch_three.setText(bunch.get(1)+"串一");
                                            Text_bunch_four.setText(bunch.get(2)+"串一");
                                            Text_bunch_fire.setText(bunch.get(3)+"串一");
                                            Text_bunch_six.setText(bunch.get(4)+"串一");
                                            Text_bunch_seven.setText(bunch.get(5)+"串一");

                                            break;
                                        case 7:
                                            Text_bunch_three.setVisibility(View.VISIBLE);
                                            Text_bunch_four.setVisibility(View.VISIBLE);
                                            Text_bunch_fire.setVisibility(View.VISIBLE);
                                            Text_bunch_six.setVisibility(View.VISIBLE);
                                            Text_bunch_seven.setVisibility(View.VISIBLE);
                                            Text_bunch_entry.setVisibility(View.VISIBLE);
                                            Text_bunch_two.setText(bunch.get(0)+"串一");
                                            Text_bunch_three.setText(bunch.get(1)+"串一");
                                            Text_bunch_four.setText(bunch.get(2)+"串一");
                                            Text_bunch_fire.setText(bunch.get(3)+"串一");
                                            Text_bunch_six.setText(bunch.get(4)+"串一");
                                            Text_bunch_seven.setText(bunch.get(5)+"串一");
                                            Text_bunch_entry.setText(bunch.get(6)+"串一");

                                            break;

                                    }
                                    List<MainOrderFootBean.DataBean.OrderOddsInfoBean> order_odds_info = mainOrderFootBean.getData().getOrder_odds_info();
                                    if(order_odds_info.size()==0){

                                    }else {
                                        initdata_details(Documentdetails_main_Activity.this,MyTable,order_odds_info);

                                    }

                                    multiple_price  = Double.parseDouble(mainOrderFootBean.getData().getMultiple_price());
                                    Text_order_id.setText("订单编号："+mainOrderFootBean.getData().getOrder_id());
                                    Text_order_data.setText("下单时间："+mainOrderFootBean.getData().getCut_off_time());



                                }else {
                                    //篮球
                                    MainOrderBasketBean mainOrderBasketBean = com.alibaba.fastjson.JSONObject.parseObject(result,MainOrderBasketBean.class);

                                    Glide.with(Documentdetails_main_Activity.this).load(mainOrderBasketBean.getData().getImg_head()).asBitmap().centerCrop().into(usericon);
                                    username.setText(mainOrderBasketBean.getData().getUname());
                                    multiple_price  = Double.parseDouble(mainOrderBasketBean.getData().getMultiple_price());
                                    basketball(mainOrderBasketBean);
                                    buttom_money.setText(mainOrderBasketBean.getData().getMultiple_price()+"");

                                    Text_order_id.setText("订单编号："+mainOrderBasketBean.getData().getOrder_id());
                                    Text_order_data.setText("下单时间："+mainOrderBasketBean.getData().getCut_off_time());

                                }



                            }else {

                                ToastUtil.showToast1(Documentdetails_main_Activity.this,msg+"");

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
    public void initData() {

    }
    //足球
    private void initdata_details(Context context, LinearLayout linearLayout,List<MainOrderFootBean.DataBean.OrderOddsInfoBean> order_odds_info) {

        //初始化内容
        for (int i = 0; i < order_odds_info.size(); i++) {
            RelativeLayout relativeLayout = (RelativeLayout) LayoutInflater.from(context).inflate(R.layout.programme_details, null);
            MyTableTextView txt = (MyTableTextView) relativeLayout.findViewById(R.id.list_1_1);
            txt.setText(order_odds_info.get(i).getGame_begin_time()+"");
            txt.setTextColor(context.getResources().getColor(R.color.font_gray));
            txt = (MyTableTextView) relativeLayout.findViewById(R.id.list_1_2);
            txt.setText(order_odds_info.get(i).getGame_home_team_name()+"\n"+order_odds_info.get(i).getGame_guest_team_name());
            txt.setTextColor(context.getResources().getColor(R.color.font_gray));

            txt = (MyTableTextView) relativeLayout.findViewById(R.id.list_1_3);
            if(order_odds_info.get(i).getGame_status()>=2){
                txt.setText(order_odds_info.get(i).getWin_odds_list().get(0)+"\n"+order_odds_info.get(i).getWin_odds_list().get(1)+"\n"+order_odds_info.get(i).getWin_odds_list().get(2)+
                        "\n"+order_odds_info.get(i).getWin_odds_list().get(3)+"\n"+order_odds_info.get(i).getWin_odds_list().get(4));

            }else {
                txt.setText("暂无赛果");

            }
            txt.setTextColor(context.getResources().getColor(R.color.login_red));

            txt = (MyTableTextView) relativeLayout.findViewById(R.id.list_1_4);
            String zhi = "";
            for (int j = 0; j <order_odds_info.get(i).getBet_info().size() ; j++) {
                zhi +=order_odds_info.get(i).getBet_info().get(j).getBet_name()+"("+order_odds_info.get(i).getBet_info().get(j).getBet_odds()+")"+"\n";

            }

            txt.setText(zhi);
            txt.setTextColor(context.getResources().getColor(R.color.login_red));


            linearLayout.addView(relativeLayout);
        }
    }

    //篮球
    private void initdata_details_basketball(Context context, LinearLayout linearLayout,List<MainOrderBasketBean.DataBean.OrderOddsInfoBean> order_odds_info) {

        //初始化内容
        for (int i = 0; i < order_odds_info.size(); i++) {
            RelativeLayout relativeLayout = (RelativeLayout) LayoutInflater.from(context).inflate(R.layout.programme_details, null);
            MyTableTextView txt = (MyTableTextView) relativeLayout.findViewById(R.id.list_1_1);
            txt.setText(order_odds_info.get(i).getGame_begin_time()+"");
            txt.setTextColor(context.getResources().getColor(R.color.font_gray));
            txt = (MyTableTextView) relativeLayout.findViewById(R.id.list_1_2);
            txt.setText(order_odds_info.get(i).getGame_home_team_name()+"\n"+order_odds_info.get(i).getGame_guest_team_name());
            txt.setTextColor(context.getResources().getColor(R.color.font_gray));

            txt = (MyTableTextView) relativeLayout.findViewById(R.id.list_1_3);
            if(order_odds_info.get(i).getGame_status()>=2){
                txt.setText(order_odds_info.get(i).getWin_odds_list().get(0)+"\n"+order_odds_info.get(i).getWin_odds_list().get(1)+"\n"+order_odds_info.get(i).getWin_odds_list().get(2)+
                        "\n"+order_odds_info.get(i).getWin_odds_list().get(3));

            }else {
                txt.setText("暂无赛果");

            }
            txt.setTextColor(context.getResources().getColor(R.color.login_red));

            txt = (MyTableTextView) relativeLayout.findViewById(R.id.list_1_4);
            String zhi = "";
            for (int j = 0; j <order_odds_info.get(i).getBet_info().size() ; j++) {
                zhi +=order_odds_info.get(i).getBet_info().get(j).getBet_name()+"("+order_odds_info.get(i).getBet_info().get(j).getBet_odds()+")"+"\n";

            }

            txt.setText(zhi);
            txt.setTextColor(context.getResources().getColor(R.color.login_red));


            linearLayout.addView(relativeLayout);
        }
    }



//跟单列表
//    private void initdata_documentary(Context context,LinearLayout linearLayout) {
//        RelativeLayout relativeLayout = (RelativeLayout) LayoutInflater.from(context).inflate(R.layout.table, null);
//        MyTableTextView title = (MyTableTextView) relativeLayout.findViewById(R.id.list_1_1);
//        title.setText(name[0]);
//        title.setTextColor(context.getResources().getColor(R.color.font_gray));
//
//        title = (MyTableTextView) relativeLayout.findViewById(R.id.list_1_2);
//        title.setText(name[1]);
//        title.setTextColor(context.getResources().getColor(R.color.font_gray));
//        title = (MyTableTextView) relativeLayout.findViewById(R.id.list_1_3);
//        title.setText(name[2]);
//        title.setTextColor(context.getResources().getColor(R.color.font_gray));
//        title = (MyTableTextView) relativeLayout.findViewById(R.id.list_1_4);
//        title.setText(name[3]);
//        title.setTextColor(context.getResources().getColor(R.color.font_gray));
//
//
//        linearLayout.addView(relativeLayout);
//        //初始化内容
//        for (int i = 0; i < 2; i++) {
//            relativeLayout = (RelativeLayout) LayoutInflater.from(context).inflate(R.layout.table, null);
//            MyTableTextView txt = (MyTableTextView) relativeLayout.findViewById(R.id.list_1_1);
//            txt.setText("k**w");
//            txt.setTextColor(context.getResources().getColor(R.color.font_gray));
//            txt = (MyTableTextView) relativeLayout.findViewById(R.id.list_1_2);
//            txt.setText("2800.00元");
//            txt.setTextColor(context.getResources().getColor(R.color.font_gray));
//
//            txt = (MyTableTextView) relativeLayout.findViewById(R.id.list_1_3);
//            txt.setText("08:36:45");
//            txt.setTextColor(context.getResources().getColor(R.color.font_gray));
//
//            txt = (MyTableTextView) relativeLayout.findViewById(R.id.list_1_4);
//            txt.setText("----");
//            txt.setTextColor(context.getResources().getColor(R.color.font_gray));
//
//
//            linearLayout.addView(relativeLayout);
//
//
//        }
//    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.image_back:
                finish();
                break;
            case R.id.button_senddocument:

                break;
            case R.id.text_gen:
                send();
                break;

        }
    }

    private void send() {
        HashMap<String,String> hashMap = new HashMap<>();
        hashMap.put("plan_id",plan_id+"");
        hashMap.put("multiple",multiple+"");
        OkhttpUtlis okhttpUtlis  = new OkhttpUtlis();

        okhttpUtlis.PostAsynMap(Url.baseUrl + "order/installFollowOrder", hashMap, new Callback() {
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
                            finish();

                            ToastUtil.showToast1(Documentdetails_main_Activity.this,codeBean.getMsg()+"");

                        }else {
                            ToastUtil.showToast1(Documentdetails_main_Activity.this,codeBean.getMsg()+"");

                        }



                    }
                });



            }
        });



    }






    public void basketball(MainOrderBasketBean basketballOrderBean){

        switch (basketballOrderBean.getData().getOrder_status()){
            case -1:
                state.setText("申请退款");
                order_money.setText(basketballOrderBean.getData().getOrder_price());
                Winning_money.setText(basketballOrderBean.getData().getBonus_price());
                add_money.setText("0");
                break;
            case 0:
                state.setText("待出票");
                order_money.setText(basketballOrderBean.getData().getOrder_price());
                Winning_money.setText("未开奖");
                add_money.setText("0");
                break;
            case 1:
                state.setText("待开奖");
                order_money.setText(basketballOrderBean.getData().getOrder_price());
                Winning_money.setText("未开奖");

                add_money.setText("0");
                if(basketballOrderBean.getData().getPlan_status()==0){
                    text_pluc.setText("完全公开");
                    layout_publicv.setVisibility(View.GONE);
                    MyTable.setVisibility(View.VISIBLE);
                }else if(basketballOrderBean.getData().getPlan_status()==1){
                    text_pluc.setText("跟单后公开");
                    layout_publicv.setVisibility(View.VISIBLE);
                    MyTable.setVisibility(View.GONE);

                }else {
                    text_pluc.setText("截止后公开");
                    layout_publicv.setVisibility(View.VISIBLE);
                    MyTable.setVisibility(View.GONE);

                }
                break;
            case 2:
                state.setText("恭喜中奖");
                order_money.setText(basketballOrderBean.getData().getOrder_price());
                Winning_money.setText(basketballOrderBean.getData().getBonus_price());
                add_money.setText(Double.parseDouble(basketballOrderBean.getData().getBonus_price())*0.05+"");
                break;
            case 3:
                state.setText("再接再厉");
                order_money.setText(basketballOrderBean.getData().getOrder_price());
                Winning_money.setText(basketballOrderBean.getData().getBonus_price());
                add_money.setText("0");
                break;

            case -2:
                state.setText("已取消");
                order_money.setText(basketballOrderBean.getData().getOrder_price());
                Winning_money.setText(basketballOrderBean.getData().getBonus_price());
                add_money.setText("0");
                break;




        }

//
        layout_buttonv.setVisibility(View.GONE);
        View_genv.setVisibility(View.VISIBLE);

        gendan_bumber.setText("跟单人数（"+basketballOrderBean.getData().getPlan_follow_person()+"，共"+basketballOrderBean.getData().getPlan_follow_price()+"元）");


        layout_genlistv.setVisibility(View.VISIBLE);

        multiple_pric.setText("单倍金额:"+basketballOrderBean.getData().getMultiple_price());

        plan_profits.setText("方案提成:"+Double.parseDouble(basketballOrderBean.getData().getPlan_profits())*100+"%");
        text_dec.setText(""+basketballOrderBean.getData().getPlan_desc());


        Text_Scene.setText(basketballOrderBean.getData().getGame_sum()+"场");
        Text_Multiple.setText(basketballOrderBean.getData().getMultiple()+"倍");
        List<String> bunch = basketballOrderBean.getData().getBunch();
        if(bunch.size()>2){
            layout_bunch.setVisibility(View.VISIBLE);

        }else {
            layout_bunch.setVisibility(View.GONE);


        }

        switch (bunch.size()){
            case 1:
                Text_bunch_two.setText("单关");
                break;
            case 2:
                Text_bunch_three.setVisibility(View.VISIBLE);
                Text_bunch_two.setText(bunch.get(0)+"串一");
                Text_bunch_three.setText(bunch.get(1)+"串一");

                break;
            case 3:
                Text_bunch_three.setVisibility(View.VISIBLE);
                Text_bunch_four.setVisibility(View.VISIBLE);
                Text_bunch_two.setText(bunch.get(0)+"串一");
                Text_bunch_three.setText(bunch.get(1)+"串一");
                Text_bunch_four.setText(bunch.get(2)+"串一");

                break;
            case 4:
                Text_bunch_three.setVisibility(View.VISIBLE);
                Text_bunch_four.setVisibility(View.VISIBLE);
                Text_bunch_fire.setVisibility(View.VISIBLE);
                Text_bunch_two.setText(bunch.get(0)+"串一");
                Text_bunch_three.setText(bunch.get(1)+"串一");
                Text_bunch_four.setText(bunch.get(2)+"串一");
                Text_bunch_fire.setText(bunch.get(3)+"串一");

                break;
            case 5:
                Text_bunch_three.setVisibility(View.VISIBLE);
                Text_bunch_four.setVisibility(View.VISIBLE);
                Text_bunch_fire.setVisibility(View.VISIBLE);
                Text_bunch_six.setVisibility(View.VISIBLE);
                Text_bunch_two.setText(bunch.get(0)+"串一");
                Text_bunch_three.setText(bunch.get(1)+"串一");
                Text_bunch_four.setText(bunch.get(2)+"串一");
                Text_bunch_fire.setText(bunch.get(3)+"串一");
                Text_bunch_six.setText(bunch.get(4)+"串一");

                break;
            case 6:
                Text_bunch_three.setVisibility(View.VISIBLE);
                Text_bunch_four.setVisibility(View.VISIBLE);
                Text_bunch_fire.setVisibility(View.VISIBLE);
                Text_bunch_six.setVisibility(View.VISIBLE);
                Text_bunch_seven.setVisibility(View.VISIBLE);
                Text_bunch_two.setText(bunch.get(0)+"串一");
                Text_bunch_three.setText(bunch.get(1)+"串一");
                Text_bunch_four.setText(bunch.get(2)+"串一");
                Text_bunch_fire.setText(bunch.get(3)+"串一");
                Text_bunch_six.setText(bunch.get(4)+"串一");
                Text_bunch_seven.setText(bunch.get(5)+"串一");

                break;
            case 7:
                Text_bunch_three.setVisibility(View.VISIBLE);
                Text_bunch_four.setVisibility(View.VISIBLE);
                Text_bunch_fire.setVisibility(View.VISIBLE);
                Text_bunch_six.setVisibility(View.VISIBLE);
                Text_bunch_seven.setVisibility(View.VISIBLE);
                Text_bunch_entry.setVisibility(View.VISIBLE);
                Text_bunch_two.setText(bunch.get(0)+"串一");
                Text_bunch_three.setText(bunch.get(1)+"串一");
                Text_bunch_four.setText(bunch.get(2)+"串一");
                Text_bunch_fire.setText(bunch.get(3)+"串一");
                Text_bunch_six.setText(bunch.get(4)+"串一");
                Text_bunch_seven.setText(bunch.get(5)+"串一");
                Text_bunch_entry.setText(bunch.get(6)+"串一");

                break;

        }


        List<MainOrderBasketBean.DataBean.OrderOddsInfoBean> order_odds_info = basketballOrderBean.getData().getOrder_odds_info();

        if(order_odds_info.size()==0){


        }else {
            initdata_details_basketball(Documentdetails_main_Activity.this,MyTable,order_odds_info);

        }




    }

    @Override
    protected void onResume() {
        super.onResume();


    }
}
