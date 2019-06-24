package com.example.yuzishun.newdeom.documentary.activity;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.support.annotation.StringDef;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.style.ForegroundColorSpan;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.yuzishun.newdeom.R;
import com.example.yuzishun.newdeom.base.BaseActivity;
import com.example.yuzishun.newdeom.base.Content;
import com.example.yuzishun.newdeom.documentary.custom.AmountView;
import com.example.yuzishun.newdeom.model.BasketballOrderBean;
import com.example.yuzishun.newdeom.model.FootBallOrderBean;
import com.example.yuzishun.newdeom.my.activity.SendDocumentActivity;
import com.example.yuzishun.newdeom.my.custom.MyTableTextView;
import com.example.yuzishun.newdeom.net.OkhttpUtlis;
import com.example.yuzishun.newdeom.net.Url;
import com.example.yuzishun.newdeom.utils.StringDesignUtil;
import com.example.yuzishun.newdeom.utils.ToastUtil;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.StringReader;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

/***
 * 我的页面订单的详情界面
 *
 */
public class DocumentdetailsActivity extends BaseActivity implements View.OnClickListener {

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
    @BindView(R.id.Theory_Text)
    TextView Theory_Text;
    @BindView(R.id.Relate_Theory)
    RelativeLayout Relate_Theory;
    @BindView(R.id.gendan_price)
    TextView gendan_price;
    private int flag,type,order_id,multiple_price,multiple;

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
        order_id = intent.getIntExtra("order_id",0);
        //0就是订单详情，1的话就是跟单详情
            Layout_genbottom.setVisibility(View.GONE);
            layout_publicv.setVisibility(View.GONE);
            layout_buttonv.setVisibility(View.GONE);

            title_text.setText("订单详情");


        image_back.setOnClickListener(this);
        button_senddocument.setOnClickListener(this);
        initnet();
        //倍数选择
        amountView.setGoods_storage(50);
        amountView.setOnAmountChangeListener(new AmountView.OnAmountChangeListener() {
            @Override
            public void onAmountChange(View view, int amount) {
                Toast.makeText(getApplicationContext(), "Amount=>  " + amount, Toast.LENGTH_SHORT).show();
            }
        });

    }

    private void initnet() {
        OkhttpUtlis okhttpUtlis = new OkhttpUtlis();
        okhttpUtlis.GetAsynMap(Url.baseUrl + "app/order/getOrderInfo?order_id=" + order_id, new Callback() {
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
                                Glide.with(DocumentdetailsActivity.this).load(Content.userurl).asBitmap().centerCrop().into(usericon);
                                username.setText(Content.username);



                                //type是0的时候是足球不是0则是篮球
                                if(type==0){


                                    FootBallOrderBean footBallOrderBean = com.alibaba.fastjson.JSONObject.parseObject(result,FootBallOrderBean.class);
                                    if(footBallOrderBean.getData().getIn_plan()==0){
                                        layout_buttonv.setVisibility(View.VISIBLE);

                                    }else {
                                        layout_buttonv.setVisibility(View.GONE);

                                    }
                                    if(footBallOrderBean.getData().getTheory_bonus().equals("")){
                                        Relate_Theory.setVisibility(View.GONE);
                                    }else {
                                        Theory_Text.setText(footBallOrderBean.getData().getTheory_bonus());

                                    }

                                    switch (footBallOrderBean.getData().getOrder_status()){
                                        case -1:
                                            state.setText("申请退款");
                                            order_money.setText(footBallOrderBean.getData().getOrder_price());
                                            Winning_money.setText(footBallOrderBean.getData().getBonus_price());
                                            add_money.setText("0");
                                            break;
                                        case 0:
                                            state.setText("待出票");
                                            order_money.setText(footBallOrderBean.getData().getOrder_price());
                                            Winning_money.setText(footBallOrderBean.getData().getBonus_price());
                                            add_money.setText("0");
                                            break;
                                        case 1:
                                            state.setText("待开奖");
                                            order_money.setText(footBallOrderBean.getData().getOrder_price());
                                            Winning_money.setText(footBallOrderBean.getData().getBonus_price());
                                            add_money.setText("0");
//                                            if(Double.parseDouble(footBallOrderBean.getData().getOrder_price())>=100){
//                                                layout_buttonv.setVisibility(View.VISIBLE);
//
//                                            }else {
//                                                layout_buttonv.setVisibility(View.GONE);
//
//                                            }
                                            break;
                                        case 2:
                                            state.setText("恭喜中奖");
                                            state.setTextColor(getResources().getColor(R.color.login_red));
                                            order_money.setText(footBallOrderBean.getData().getOrder_price());
                                            NumberFormat nfmin = new DecimalFormat("#.##");

                                            String winning_money = nfmin.format(Double.parseDouble(footBallOrderBean.getData().getBonus_price()) + Double.parseDouble(footBallOrderBean.getData().getBonus_price()) * 0.05);
                                            Winning_money.setText(winning_money);
                                            add_money.setText(nfmin.format(Double.parseDouble(footBallOrderBean.getData().getBonus_price())*0.05)+"");
                                            break;
                                        case 3:
                                            state.setText("再接再厉");
                                            state.setTextColor(getResources().getColor(R.color.font_gray));

                                            order_money.setText(footBallOrderBean.getData().getOrder_price());
                                            Winning_money.setText(footBallOrderBean.getData().getBonus_price());
                                            add_money.setText("0");
                                            break;

                                        case -2:
                                            state.setText("已取消");
                                            order_money.setText(footBallOrderBean.getData().getOrder_price());
                                            Winning_money.setText(footBallOrderBean.getData().getBonus_price());
                                            add_money.setText("0");
                                            break;



//                                        订单状态【0：待出票，1：待开奖，2：中奖，3：未中奖，-1：申请退款，-2：已取消】
                                    }



                                    switch (footBallOrderBean.getData().getOrder_type()){
                                        case 0:
                                            layout_dec.setVisibility(View.GONE);
                                            layout_genv.setVisibility(View.GONE);
                                            layout_genlistv.setVisibility(View.GONE);
                                            View_genv.setVisibility(View.GONE);


                                            break;
                                        case 1:
                                            layout_buttonv.setVisibility(View.GONE);
                                            layout_dec.setVisibility(View.GONE);
                                            View_genv.setVisibility(View.VISIBLE);

                                            multiple_pric.setText("单倍金额:"+footBallOrderBean.getData().getMultiple_price());
                                            plan_profits.setText("方案提成:"+footBallOrderBean.getData().getOrder_plan().getPlan_profits());
                                            gendan_bumber.setText("跟单人数（"+footBallOrderBean.getData().getOrder_plan().getPlan_follow_person()+"，共"+footBallOrderBean.getData().getOrder_plan().getPlan_follow_price()+"元）");
                                            gendan_price.setText("佣金:"+footBallOrderBean.getData().getOrder_plan().getPlan_profit_price());
                                            break;
                                        case 2:
                                            View_genv.setVisibility(View.GONE);
                                            layout_genlistv.setVisibility(View.GONE);
                                            layout_buttonv.setVisibility(View.GONE);

                                            multiple_pric.setText("单倍金额:"+footBallOrderBean.getData().getMultiple_price());

                                            plan_profits.setText("方案提成:"+footBallOrderBean.getData().getOrder_follow_plan().getPlan_profits());
                                            text_dec.setText("跟单于:"+footBallOrderBean.getData().getOrder_follow_plan().getUname());

                                            break;

                                    }


                                    Text_Scene.setText(footBallOrderBean.getData().getGame_sum()+"场");
                                    Text_Multiple.setText(footBallOrderBean.getData().getMultiple()+"倍");
                                    List<String> bunch = footBallOrderBean.getData().getBunch();
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
                                            Text_bunch_two.setText("单关");
                                            Text_bunch_three.setText(bunch.get(1)+"串1");

                                            break;
                                        case 3:
                                            Text_bunch_three.setVisibility(View.VISIBLE);
                                            Text_bunch_four.setVisibility(View.VISIBLE);
                                            Text_bunch_two.setText("单关");
                                            Text_bunch_three.setText(bunch.get(1)+"串1");
                                            Text_bunch_four.setText(bunch.get(2)+"串1");

                                            break;
                                        case 4:
                                            Text_bunch_three.setVisibility(View.VISIBLE);
                                            Text_bunch_four.setVisibility(View.VISIBLE);
                                            Text_bunch_fire.setVisibility(View.VISIBLE);
                                            Text_bunch_two.setText("单关");
                                            Text_bunch_three.setText(bunch.get(1)+"串1");
                                            Text_bunch_four.setText(bunch.get(2)+"串1");
                                            Text_bunch_fire.setText(bunch.get(3)+"串1");

                                            break;
                                        case 5:
                                            Text_bunch_three.setVisibility(View.VISIBLE);
                                            Text_bunch_four.setVisibility(View.VISIBLE);
                                            Text_bunch_fire.setVisibility(View.VISIBLE);
                                            Text_bunch_six.setVisibility(View.VISIBLE);
                                            Text_bunch_two.setText("单关");
                                            Text_bunch_three.setText(bunch.get(1)+"串1");
                                            Text_bunch_four.setText(bunch.get(2)+"串1");
                                            Text_bunch_fire.setText(bunch.get(3)+"串1");
                                            Text_bunch_six.setText(bunch.get(4)+"串1");

                                            break;
                                        case 6:
                                            Text_bunch_three.setVisibility(View.VISIBLE);
                                            Text_bunch_four.setVisibility(View.VISIBLE);
                                            Text_bunch_fire.setVisibility(View.VISIBLE);
                                            Text_bunch_six.setVisibility(View.VISIBLE);
                                            Text_bunch_seven.setVisibility(View.VISIBLE);
                                            Text_bunch_two.setText("单关");
                                            Text_bunch_three.setText(bunch.get(1)+"串1");
                                            Text_bunch_four.setText(bunch.get(2)+"串1");
                                            Text_bunch_fire.setText(bunch.get(3)+"串1");
                                            Text_bunch_six.setText(bunch.get(4)+"串1");
                                            Text_bunch_seven.setText(bunch.get(5)+"串1");

                                            break;
                                        case 7:
                                            Text_bunch_three.setVisibility(View.VISIBLE);
                                            Text_bunch_four.setVisibility(View.VISIBLE);
                                            Text_bunch_fire.setVisibility(View.VISIBLE);
                                            Text_bunch_six.setVisibility(View.VISIBLE);
                                            Text_bunch_seven.setVisibility(View.VISIBLE);
                                            Text_bunch_entry.setVisibility(View.VISIBLE);
                                            Text_bunch_two.setText("单关");
                                            Text_bunch_three.setText(bunch.get(1)+"串1");
                                            Text_bunch_four.setText(bunch.get(2)+"串1");
                                            Text_bunch_fire.setText(bunch.get(3)+"串1");
                                            Text_bunch_six.setText(bunch.get(4)+"串1");
                                            Text_bunch_seven.setText(bunch.get(5)+"串1");
                                            Text_bunch_entry.setText(bunch.get(6)+"串1");

                                            break;

                                    }
                                    List<FootBallOrderBean.DataBean.OrderOddsInfoBean> order_odds_info = footBallOrderBean.getData().getOrder_odds_info();
                                    if(order_odds_info.size()==0){
                                        layout_publicv.setVisibility(View.VISIBLE);
                                    }else {
                                        initdata_details(DocumentdetailsActivity.this,MyTable,order_odds_info);

                                    }
//                                    usedr_id.setText("ID:"+footBallOrderBean.getData().getUser_id());
                                    multiple_price  = footBallOrderBean.getData().getMultiple_price();
                                    multiple = footBallOrderBean.getData().getMultiple();
                                    Text_order_id.setText("订单编号："+footBallOrderBean.getData().getOrder_no());
                                    Text_order_data.setText("下单时间："+footBallOrderBean.getData().getCreate_time());
                                    List<FootBallOrderBean.DataBean.OrderPlanInfoBean> order_plan_info = footBallOrderBean.getData().getOrder_plan_info();
                                    if(order_plan_info.size()==0){
                                        MyTable_gen.setVisibility(View.GONE);

                                    }else {
                                        MyTable_gen.setVisibility(View.VISIBLE);
                                        initdata_documentary(DocumentdetailsActivity.this,MyTable_gen,order_plan_info);
                                    }


                                }else {
                                    //篮球
                                    BasketballOrderBean basketballOrderBean = com.alibaba.fastjson.JSONObject.parseObject(result,BasketballOrderBean.class);

//                                    usedr_id.setText("ID:"+basketballOrderBean.getData().getUser_id());

                                    multiple_price  = basketballOrderBean.getData().getMultiple_price();
                                    multiple = basketballOrderBean.getData().getMultiple();
                                    basketball(basketballOrderBean);

                                    Text_order_id.setText("订单编号："+basketballOrderBean.getData().getOrder_no());
                                    Text_order_data.setText("下单时间："+basketballOrderBean.getData().getCreate_time());
                                    List<BasketballOrderBean.DataBean.OrderPlanInfoBean> order_plan_info = basketballOrderBean.getData().getOrder_plan_info();
                                    if(order_plan_info.size()==0){
                                        MyTable_gen.setVisibility(View.GONE);

                                    }else {
                                        MyTable_gen.setVisibility(View.VISIBLE);
                                        initdata_documentary_bask(DocumentdetailsActivity.this,MyTable_gen,order_plan_info);
                                    }
                                }



                            }else {

                                ToastUtil.showToast1(DocumentdetailsActivity.this,msg+"");

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
    private void initdata_details(Context context, LinearLayout linearLayout,List<FootBallOrderBean.DataBean.OrderOddsInfoBean> order_odds_info) {

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
                String ss="";
                for (int j = 0; j <order_odds_info.get(i).getWin_odds_list().size() ; j++) {
                    ss +=order_odds_info.get(i).getWin_odds_list().get(j)+"\n";
                }
                txt.setText(ss);

            }else {
                txt.setText("暂无赛果");

            }
            txt.setTextColor(context.getResources().getColor(R.color.login_red));

            txt = (MyTableTextView) relativeLayout.findViewById(R.id.list_1_4);
            String zhi = "";
            List<String> list_color = new ArrayList<>();
            ArrayList<String> list_color_red = new ArrayList<>();

            for (int j = 0; j <order_odds_info.get(i).getBet_info().size() ; j++) {

                if(order_odds_info.get(i).getBet_info().get(j).getStatus()==1){



                    list_color_red.add(order_odds_info.get(i).getBet_info().get(j).getBet_name());

                }else {
//

                }
                list_color.add(order_odds_info.get(i).getBet_info().get(j).getBet_name());

//

            }

            for (int j = 0; j < list_color.size(); j++) {
                zhi +=list_color.get(j)+"\n";

            }
            Log.e("YZS",zhi+"");




            if(order_odds_info.get(i).getGame_status()>=2){

                txt.setText(StringDesignUtil.getSpannableStringBuilder(zhi,list_color_red, context.getResources().getColor(R.color.login_red)));

            }else {
                txt.setText(zhi);
                txt.setTextColor(context.getResources().getColor(R.color.font_gray));
            }





            linearLayout.addView(relativeLayout);
        }
    }

    //篮球
    private void initdata_details_basketball(Context context, LinearLayout linearLayout,List<BasketballOrderBean.DataBean.OrderOddsInfoBean> order_odds_info) {

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
                String ss="";
                for (int j = 0; j <order_odds_info.get(i).getWin_odds_list().size() ; j++) {
                    ss +=order_odds_info.get(i).getWin_odds_list().get(j)+"\n";
                }
                txt.setText(ss);

            }else {
                txt.setText("暂无赛果");

            }
            txt.setTextColor(context.getResources().getColor(R.color.login_red));

            txt = (MyTableTextView) relativeLayout.findViewById(R.id.list_1_4);
            String zhi = "";
            List<String> list_color = new ArrayList<>();
            ArrayList<String> list_color_red = new ArrayList<>();
            for (int j = 0; j <order_odds_info.get(i).getBet_info().size() ; j++) {
                if(order_odds_info.get(i).getBet_info().get(j).getStatus()==1){



                    list_color_red.add(order_odds_info.get(i).getBet_info().get(j).getBet_name());

                }else {
//

                }
                list_color.add(order_odds_info.get(i).getBet_info().get(j).getBet_name());

            }
            for (int j = 0; j < list_color.size(); j++) {
                zhi +=list_color.get(j)+"\n";

            }
            if(order_odds_info.get(i).getGame_status()>=2){

                txt.setText(StringDesignUtil.getSpannableStringBuilder(zhi,list_color_red, context.getResources().getColor(R.color.login_red)));

            }else {
                txt.setText(zhi);
                txt.setTextColor(context.getResources().getColor(R.color.font_gray));
            }




            linearLayout.addView(relativeLayout);
        }
    }



//跟单列表
    private void initdata_documentary(Context context,LinearLayout linearLayout,List<FootBallOrderBean.DataBean.OrderPlanInfoBean> order_plan_info) {
        RelativeLayout relativeLayout = (RelativeLayout) LayoutInflater.from(context).inflate(R.layout.table, null);
        MyTableTextView title = (MyTableTextView) relativeLayout.findViewById(R.id.list_1_1);
        title.setText(name[0]);
        title.setTextColor(context.getResources().getColor(R.color.font_gray));

        title = (MyTableTextView) relativeLayout.findViewById(R.id.list_1_2);
        title.setText(name[1]);
        title.setTextColor(context.getResources().getColor(R.color.font_gray));
        title = (MyTableTextView) relativeLayout.findViewById(R.id.list_1_3);
        title.setText(name[2]);
        title.setTextColor(context.getResources().getColor(R.color.font_gray));
        title = (MyTableTextView) relativeLayout.findViewById(R.id.list_1_4);
        title.setText(name[3]);
        title.setTextColor(context.getResources().getColor(R.color.font_gray));


        linearLayout.addView(relativeLayout);
        //初始化内容
        for (int i = 0; i < order_plan_info.size(); i++) {
            relativeLayout = (RelativeLayout) LayoutInflater.from(context).inflate(R.layout.table, null);
            MyTableTextView txt = (MyTableTextView) relativeLayout.findViewById(R.id.list_1_1);

            txt.setText(order_plan_info.get(i).getUname()+"");
            txt.setTextColor(context.getResources().getColor(R.color.font_gray));
            txt = (MyTableTextView) relativeLayout.findViewById(R.id.list_1_2);
            txt.setText(order_plan_info.get(i).getOrder_price()+"");
            txt.setTextColor(context.getResources().getColor(R.color.font_gray));

            txt = (MyTableTextView) relativeLayout.findViewById(R.id.list_1_3);
            txt.setText(order_plan_info.get(i).getCreate_time()+"");
            txt.setTextColor(context.getResources().getColor(R.color.font_gray));

            txt = (MyTableTextView) relativeLayout.findViewById(R.id.list_1_4);
            txt.setText(order_plan_info.get(i).getBonus_price()+"");
            txt.setTextColor(context.getResources().getColor(R.color.font_gray));


            linearLayout.addView(relativeLayout);


        }
    }

    //跟单列表
    private void initdata_documentary_bask(Context context,LinearLayout linearLayout,List<BasketballOrderBean.DataBean.OrderPlanInfoBean> order_plan_info) {
        RelativeLayout relativeLayout = (RelativeLayout) LayoutInflater.from(context).inflate(R.layout.table, null);
        MyTableTextView title = (MyTableTextView) relativeLayout.findViewById(R.id.list_1_1);
        title.setText(name[0]);
        title.setTextColor(context.getResources().getColor(R.color.font_gray));

        title = (MyTableTextView) relativeLayout.findViewById(R.id.list_1_2);
        title.setText(name[1]);
        title.setTextColor(context.getResources().getColor(R.color.font_gray));
        title = (MyTableTextView) relativeLayout.findViewById(R.id.list_1_3);
        title.setText(name[2]);
        title.setTextColor(context.getResources().getColor(R.color.font_gray));
        title = (MyTableTextView) relativeLayout.findViewById(R.id.list_1_4);
        title.setText(name[3]);
        title.setTextColor(context.getResources().getColor(R.color.font_gray));


        linearLayout.addView(relativeLayout);
        //初始化内容
        for (int i = 0; i < order_plan_info.size(); i++) {
            relativeLayout = (RelativeLayout) LayoutInflater.from(context).inflate(R.layout.table, null);
            MyTableTextView txt = (MyTableTextView) relativeLayout.findViewById(R.id.list_1_1);

            txt.setText(order_plan_info.get(i).getUname()+"");
            txt.setTextColor(context.getResources().getColor(R.color.font_gray));
            txt = (MyTableTextView) relativeLayout.findViewById(R.id.list_1_2);
            txt.setText(order_plan_info.get(i).getOrder_price()+"");
            txt.setTextColor(context.getResources().getColor(R.color.font_gray));

            txt = (MyTableTextView) relativeLayout.findViewById(R.id.list_1_3);
            txt.setText(order_plan_info.get(i).getCreate_time()+"");
            txt.setTextColor(context.getResources().getColor(R.color.font_gray));

            txt = (MyTableTextView) relativeLayout.findViewById(R.id.list_1_4);
            txt.setText(order_plan_info.get(i).getBonus_price()+"");
            txt.setTextColor(context.getResources().getColor(R.color.font_gray));


            linearLayout.addView(relativeLayout);


        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.image_back:
                finish();
                break;
            case R.id.button_senddocument:
                Intent intent = new Intent(this, SendDocumentActivity.class);
                intent.putExtra("order_id",order_id);
                intent.putExtra("multiple_price",multiple_price);
                intent.putExtra("multiple",multiple);
                startActivity(intent);
                break;

        }
    }


    public void basketball(BasketballOrderBean basketballOrderBean){
        Log.e("YZS",basketballOrderBean.toString()+"");

        if(basketballOrderBean.getData().getIn_plan()==0){
            layout_buttonv.setVisibility(View.VISIBLE);

        }else {
            layout_buttonv.setVisibility(View.GONE);

        }
        if(basketballOrderBean.getData().getTheory_bonus().equals("")){
            Relate_Theory.setVisibility(View.GONE);
        }else {
            Theory_Text.setText(basketballOrderBean.getData().getTheory_bonus());

        }

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
//                if(Double.parseDouble(basketballOrderBean.getData().getOrder_price())>=100){
//                    layout_buttonv.setVisibility(View.VISIBLE);
//
//                }else {
//                    layout_buttonv.setVisibility(View.GONE);
//
//                }
                break;
            case 2:
                state.setText("恭喜中奖");
                order_money.setText(basketballOrderBean.getData().getOrder_price());
                NumberFormat nfmin = new DecimalFormat("#.##");

                String winning_money = nfmin.format(Double.parseDouble(basketballOrderBean.getData().getBonus_price()) + Double.parseDouble(basketballOrderBean.getData().getBonus_price()) * 0.05);
                Winning_money.setText(winning_money);
                add_money.setText(nfmin.format(Double.parseDouble(basketballOrderBean.getData().getBonus_price())*0.05));
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

        switch (basketballOrderBean.getData().getOrder_type()){
            case 0:
                layout_dec.setVisibility(View.GONE);
                layout_genv.setVisibility(View.GONE);
                layout_genlistv.setVisibility(View.GONE);
                View_genv.setVisibility(View.GONE);


                break;
            case 1:
                layout_buttonv.setVisibility(View.GONE);
                layout_dec.setVisibility(View.GONE);
                View_genv.setVisibility(View.VISIBLE);

                multiple_pric.setText("单倍金额:"+basketballOrderBean.getData().getMultiple_price());
                plan_profits.setText("方案提成:"+basketballOrderBean.getData().getOrder_plan().getPlan_profits());
                gendan_bumber.setText("跟单人数（"+basketballOrderBean.getData().getOrder_plan().getPlan_follow_person()+"，共"+basketballOrderBean.getData().getOrder_plan().getPlan_follow_price()+"元）");
                gendan_price.setText("佣金:"+basketballOrderBean.getData().getOrder_plan().getPlan_profit_price());

                break;
            case 2:
                View_genv.setVisibility(View.GONE);
                layout_genlistv.setVisibility(View.GONE);
                layout_buttonv.setVisibility(View.GONE);

                multiple_pric.setText("单倍金额:"+basketballOrderBean.getData().getMultiple_price());

                plan_profits.setText("方案提成:"+basketballOrderBean.getData().getOrder_follow_plan().getPlan_profits());
                text_dec.setText("跟单于:"+basketballOrderBean.getData().getOrder_follow_plan().getUname());

                break;

        }


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
                Text_bunch_two.setText("单关");
                Text_bunch_three.setText(bunch.get(1)+"串1");

                break;
            case 3:
                Text_bunch_three.setVisibility(View.VISIBLE);
                Text_bunch_four.setVisibility(View.VISIBLE);
                Text_bunch_two.setText("单关");
                Text_bunch_three.setText(bunch.get(1)+"串1");
                Text_bunch_four.setText(bunch.get(2)+"串1");

                break;
            case 4:
                Text_bunch_three.setVisibility(View.VISIBLE);
                Text_bunch_four.setVisibility(View.VISIBLE);
                Text_bunch_fire.setVisibility(View.VISIBLE);
                Text_bunch_two.setText("单关");
                Text_bunch_three.setText(bunch.get(1)+"串1");
                Text_bunch_four.setText(bunch.get(2)+"串1");
                Text_bunch_fire.setText(bunch.get(3)+"串1");

                break;
            case 5:
                Text_bunch_three.setVisibility(View.VISIBLE);
                Text_bunch_four.setVisibility(View.VISIBLE);
                Text_bunch_fire.setVisibility(View.VISIBLE);
                Text_bunch_six.setVisibility(View.VISIBLE);
                Text_bunch_two.setText("单关");
                Text_bunch_three.setText(bunch.get(1)+"串1");
                Text_bunch_four.setText(bunch.get(2)+"串1");
                Text_bunch_fire.setText(bunch.get(3)+"串1");
                Text_bunch_six.setText(bunch.get(4)+"串1");

                break;
            case 6:
                Text_bunch_three.setVisibility(View.VISIBLE);
                Text_bunch_four.setVisibility(View.VISIBLE);
                Text_bunch_fire.setVisibility(View.VISIBLE);
                Text_bunch_six.setVisibility(View.VISIBLE);
                Text_bunch_seven.setVisibility(View.VISIBLE);
                Text_bunch_two.setText("单关");
                Text_bunch_three.setText(bunch.get(1)+"串1");
                Text_bunch_four.setText(bunch.get(2)+"串1");
                Text_bunch_fire.setText(bunch.get(3)+"串1");
                Text_bunch_six.setText(bunch.get(4)+"串1");
                Text_bunch_seven.setText(bunch.get(5)+"串1");

                break;
            case 7:
                Text_bunch_three.setVisibility(View.VISIBLE);
                Text_bunch_four.setVisibility(View.VISIBLE);
                Text_bunch_fire.setVisibility(View.VISIBLE);
                Text_bunch_six.setVisibility(View.VISIBLE);
                Text_bunch_seven.setVisibility(View.VISIBLE);
                Text_bunch_entry.setVisibility(View.VISIBLE);
                Text_bunch_two.setText("单关");
                Text_bunch_three.setText(bunch.get(1)+"串1");
                Text_bunch_four.setText(bunch.get(2)+"串1");
                Text_bunch_fire.setText(bunch.get(3)+"串1");
                Text_bunch_six.setText(bunch.get(4)+"串1");
                Text_bunch_seven.setText(bunch.get(5)+"串1");
                Text_bunch_entry.setText(bunch.get(6)+"串1");

                break;

        }


        List<BasketballOrderBean.DataBean.OrderOddsInfoBean> order_odds_info = basketballOrderBean.getData().getOrder_odds_info();

        if(order_odds_info.size()==0){
            layout_publicv.setVisibility(View.VISIBLE);
        }else {
            initdata_details_basketball(DocumentdetailsActivity.this,MyTable,order_odds_info);


        }



    }

    @Override
    protected void onResume() {
        super.onResume();


    }
}
