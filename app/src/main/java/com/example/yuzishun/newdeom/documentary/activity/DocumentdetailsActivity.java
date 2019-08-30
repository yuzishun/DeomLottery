package com.example.yuzishun.newdeom.documentary.activity;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
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
import com.example.yuzishun.newdeom.MainActivity;
import com.example.yuzishun.newdeom.R;
import com.example.yuzishun.newdeom.base.BaseActivity;
import com.example.yuzishun.newdeom.base.Content;
import com.example.yuzishun.newdeom.documentary.custom.AmountView;
import com.example.yuzishun.newdeom.login.activity.LoginActivity;
import com.example.yuzishun.newdeom.model.CodeBean;
import com.example.yuzishun.newdeom.model.FootBallOrderBean;
import com.example.yuzishun.newdeom.my.activity.SendDocumentActivity;
import com.example.yuzishun.newdeom.my.custom.MyTableTextView;
import com.example.yuzishun.newdeom.net.OkhttpUtlis;
import com.example.yuzishun.newdeom.net.Url;
import com.example.yuzishun.newdeom.sunsheet.activity.SendSunSheetActivity;
import com.example.yuzishun.newdeom.sunsheet.activity.SunSheetDetailsActivity;
import com.example.yuzishun.newdeom.utils.SpUtil;
import com.example.yuzishun.newdeom.utils.StringDesignUtil;
import com.example.yuzishun.newdeom.utils.ToastUtil;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.HashMap;
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
    @BindView(R.id.text_gen)
    TextView text_gen;
    @BindView(R.id.buttom_money)
    TextView buttom_money;
    @BindView(R.id.text_pluc)
    TextView text_pluc;
    @BindView(R.id.layout_text_sunsheet)
    TextView layout_text_sunsheet;
    private int flag,order_id,plan_id,multiple=1,multiples;
    private Double multiple_price;
    private String order_price,bask_id;
    @Override
    public int intiLayout() {
        return R.layout.activity_documentdetails;
    }

    @Override
    public void initView() {
        ButterKnife.bind(this);
        Intent intent = getIntent();
        flag = intent.getIntExtra("flag",0);
        order_id = intent.getIntExtra("order_id",0);
        plan_id = intent.getIntExtra("plan_id",0);
        //0就是订单详情，1的话就是跟单详情
        if(flag==0){

            title_text.setText("订单详情");
            if(plan_id==1){
                layout_buttonv.setVisibility(View.GONE);

            }else {
                layout_buttonv.setVisibility(View.VISIBLE);

            }
        }else {
            title_text.setText("跟单详情");
            layout_buttonv.setVisibility(View.GONE);

        }


        text_gen.setOnClickListener(this);
        layout_text_sunsheet.setOnClickListener(this);
        image_back.setOnClickListener(this);
        button_senddocument.setOnClickListener(this);
        initnet();
        //倍数选择
        amountView.setGoods_storage(99999);
        amountView.setOnAmountChangeListener(new AmountView.OnAmountChangeListener() {
            @Override
            public void onAmountChange(View view, int amount) {
                multiple = amount;
                buttom_money.setText(amount*multiple_price+"");
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



                                    FootBallOrderBean footBallOrderBean = com.alibaba.fastjson.JSONObject.parseObject(result,FootBallOrderBean.class);
                                    if(footBallOrderBean.getData().getPlan_permission()==0){
                                        button_senddocument.setEnabled(false);
                                    }else {
                                        button_senddocument.setEnabled(true);


                                    }

                                bask_id = footBallOrderBean.getData().getBask_id();
                                order_price= footBallOrderBean.getData().getOrder_price();
                                Glide.with(DocumentdetailsActivity.this).load(footBallOrderBean.getData().getUser_info().getImg_head()).asBitmap().centerCrop().into(usericon);
                                username.setText(footBallOrderBean.getData().getUser_info().getUname());


                                buttom_money.setText(footBallOrderBean.getData().getMultiple_price()+"");

                                if(footBallOrderBean.getData().getFollow_plan_permission()==0){
                                    Layout_genbottom.setVisibility(View.GONE);

                                }else {
                                    Layout_genbottom.setVisibility(View.VISIBLE);

                                }

                                if(footBallOrderBean.getData().getTheory_bonus().equals("")){
                                        Relate_Theory.setVisibility(View.GONE);
                                    }else {
                                        Theory_Text.setText(footBallOrderBean.getData().getTheory_bonus());

                                    }


                                if(footBallOrderBean.getData().getOrder_plan().getPlan_status().equals("0")){
                                    text_pluc.setText("完全公开");
                                    layout_publicv.setVisibility(View.GONE);
                                    MyTable.setVisibility(View.VISIBLE);
                                }else if(footBallOrderBean.getData().getOrder_plan().getPlan_status().equals("1")){

                                    text_pluc.setText("跟单后公开");
                                    layout_publicv.setVisibility(View.VISIBLE);
                                    MyTable.setVisibility(View.GONE);

                                }else {
                                    text_pluc.setText("截止后公开");
                                    layout_publicv.setVisibility(View.VISIBLE);
                                    MyTable.setVisibility(View.GONE);

                                }

                                    switch (footBallOrderBean.getData().getOrder_status()){
                                        case -1:
                                            state.setText("已退款");
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

                                            Winning_money.setText(footBallOrderBean.getData().getBonus_price());
                                            add_money.setText(footBallOrderBean.getData().getCite_price());
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

                                    if(footBallOrderBean.getData().getOrder_status()>1){
                                        if(footBallOrderBean.getData().getBask_permission()==0){

                                            if(footBallOrderBean.getData().getBask_status()==0){
                                                layout_text_sunsheet.setVisibility(View.GONE);

                                            }else {
                                                layout_text_sunsheet.setText("查看评论");
                                                //先隐藏
                                                layout_text_sunsheet.setVisibility(View.GONE);

//                                                layout_text_sunsheet.setVisibility(View.VISIBLE);

//

                                            }
                                        }else {
                                            layout_text_sunsheet.setVisibility(View.VISIBLE);

                                        }


                                    }else {
                                        layout_text_sunsheet.setVisibility(View.GONE);

                                    }
                                    switch (footBallOrderBean.getData().getOrder_type()){
                                        case 0:
                                            layout_dec.setVisibility(View.VISIBLE);
                                            layout_genv.setVisibility(View.GONE);
                                            layout_genlistv.setVisibility(View.GONE);
                                            View_genv.setVisibility(View.GONE);

                                            text_dec.setText(""+footBallOrderBean.getData().getOrder_plan().getPlan_desc());

                                            break;
                                        case 1:
                                            layout_dec.setVisibility(View.VISIBLE);
                                            View_genv.setVisibility(View.VISIBLE);

                                            multiple_pric.setText("单倍金额:"+footBallOrderBean.getData().getMultiple_price());
                                            plan_profits.setText("方案提成:"+footBallOrderBean.getData().getOrder_plan().getPlan_profits());
                                            gendan_bumber.setText("跟单人数（"+footBallOrderBean.getData().getOrder_plan().getPlan_follow_person()+"，共"+footBallOrderBean.getData().getOrder_plan().getPlan_follow_price()+"元）");
                                            gendan_price.setText("佣金:"+footBallOrderBean.getData().getOrder_plan().getPlan_profit_price());
                                            text_dec.setText(""+footBallOrderBean.getData().getOrder_plan().getPlan_desc());

                                            break;
                                        case 2:
                                            View_genv.setVisibility(View.GONE);
                                            layout_genlistv.setVisibility(View.GONE);

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
                                            Text_bunch_two.setText(bunch.get(0)+"串1");
                                            break;
                                        case 2:
                                            Text_bunch_three.setVisibility(View.VISIBLE);
                                            Text_bunch_two.setText(bunch.get(0)+"串1");
                                            Text_bunch_three.setText(bunch.get(1)+"串1");

                                            break;
                                        case 3:
                                            Text_bunch_three.setVisibility(View.VISIBLE);
                                            Text_bunch_four.setVisibility(View.VISIBLE);
                                            Text_bunch_two.setText(bunch.get(0)+"串1");
                                            Text_bunch_three.setText(bunch.get(1)+"串1");
                                            Text_bunch_four.setText(bunch.get(2)+"串1");

                                            break;
                                        case 4:
                                            Text_bunch_three.setVisibility(View.VISIBLE);
                                            Text_bunch_four.setVisibility(View.VISIBLE);
                                            Text_bunch_fire.setVisibility(View.VISIBLE);
                                            Text_bunch_two.setText(bunch.get(0)+"串1");
                                            Text_bunch_three.setText(bunch.get(1)+"串1");
                                            Text_bunch_four.setText(bunch.get(2)+"串1");
                                            Text_bunch_fire.setText(bunch.get(3)+"串1");

                                            break;
                                        case 5:
                                            Text_bunch_three.setVisibility(View.VISIBLE);
                                            Text_bunch_four.setVisibility(View.VISIBLE);
                                            Text_bunch_fire.setVisibility(View.VISIBLE);
                                            Text_bunch_six.setVisibility(View.VISIBLE);
                                            Text_bunch_two.setText(bunch.get(0)+"串1");
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
                                            Text_bunch_two.setText(bunch.get(0)+"串1");
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
                                            Text_bunch_two.setText(bunch.get(0)+"串1");
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
                                        MyTable.setVisibility(View.GONE);
                                    }else {
                                        layout_publicv.setVisibility(View.GONE);
                                        MyTable.setVisibility(View.VISIBLE);

                                        initdata_details(DocumentdetailsActivity.this,MyTable,order_odds_info);

                                    }
//                                    usedr_id.setText("ID:"+footBallOrderBean.getData().getUser_id());
                                    multiple_price  = Double.valueOf(footBallOrderBean.getData().getMultiple_price());
                                    multiples = footBallOrderBean.getData().getMultiple();
                                    Text_order_id.setText("订单编号："+footBallOrderBean.getData().getOrder_no());
                                    Text_order_data.setText("下单时间："+footBallOrderBean.getData().getCreate_time());
                                    List<FootBallOrderBean.DataBean.OrderPlanInfoBean> order_plan_info = footBallOrderBean.getData().getOrder_plan_info();
                                    if(order_plan_info.size()==0){
                                        MyTable_gen.setVisibility(View.GONE);

                                    }else {
                                        MyTable_gen.setVisibility(View.VISIBLE);
                                        initdata_documentary(DocumentdetailsActivity.this,MyTable_gen,order_plan_info);
                                    }


//



                            }else if(code==10004){
                                    MainActivity.intentsat.finish();
                            startActivity(new Intent(DocumentdetailsActivity.this, LoginActivity.class));
                            SpUtil spUtil = new SpUtil(DocumentdetailsActivity.this,"token");
                            spUtil.putString("token","");
                            ToastUtil.showToast(DocumentdetailsActivity.this,msg+"");

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
            if(order_odds_info.get(i).getWin_odds_list().size()!=0){
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



    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.image_back:
                finish();
                break;
            case R.id.text_gen:

                send();

                break;
            case R.id.layout_text_sunsheet:
                if(layout_text_sunsheet.getText().equals("查看评论")){
                    Intent intent = new Intent(this, SunSheetDetailsActivity.class);
                    Content.back_id = bask_id;
                    startActivity(intent);
                }else {
                    Intent intent  = new Intent(this, SendSunSheetActivity.class);
                    intent.putExtra("order_id",order_id);
                    startActivity(intent);
                }

                break;
            case R.id.button_senddocument:
                Intent intentdocument = new Intent(this, SendDocumentActivity.class);
                intentdocument.putExtra("order_id",order_id);
                intentdocument.putExtra("multiple_price",order_price);
                intentdocument.putExtra("multiple",multiples);
                startActivity(intentdocument);
                break;

        }
    }



    private void send() {
        HashMap<String,String> hashMap = new HashMap<>();
        hashMap.put("plan_id",plan_id+"");
        hashMap.put("multiple",multiple+"");
        OkhttpUtlis okhttpUtlis  = new OkhttpUtlis();

        okhttpUtlis.PostAsynMap(Url.baseUrl + "app/order/installFollowOrder", hashMap, new Callback() {
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
                            ToastUtil.showToast1(DocumentdetailsActivity.this,codeBean.getMsg()+"");

                            finish();


                        }else {
                            ToastUtil.showToast1(DocumentdetailsActivity.this,codeBean.getMsg()+"");

                        }



                    }
                });



            }
        });



    }



    @Override
    protected void onResume() {
        super.onResume();


    }
}
