package com.example.yuzishun.deomlottery.sunsheet.activity;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.alibaba.fastjson.JSON;
import com.example.yuzishun.deomlottery.R;
import com.example.yuzishun.deomlottery.base.BaseActivity;
import com.example.yuzishun.deomlottery.documentary.activity.BonusdetailsActivity;
import com.example.yuzishun.deomlottery.model.FootBallOrderBean;
import com.example.yuzishun.deomlottery.my.custom.MyTableTextView;
import com.example.yuzishun.deomlottery.net.OkhttpUtlis;
import com.example.yuzishun.deomlottery.net.Url;
import com.example.yuzishun.deomlottery.utils.StringDesignUtil;
import com.example.yuzishun.deomlottery.utils.ToastUtil;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

public class ProgrammeActivity extends BaseActivity implements View.OnClickListener {

    @BindView(R.id.image_back)
    LinearLayout image_back;
    @BindView(R.id.title_text)
    TextView title_text;
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
    @BindView(R.id.Text_Multiple)
    TextView Text_Multiple;
    @BindView(R.id.Text_Scene)
    TextView Text_Scene;
    @BindView(R.id.Text_bunch_bonus)
    TextView Text_bunch_bonus;
    @BindView(R.id.layout_publicv)
    RelativeLayout layout_publicv;
    @BindView(R.id.text_pluc)
    TextView text_pluc;
    private int bonus=0;
    @BindView(R.id.MyTable)
    LinearLayout MyTable;
    @BindView(R.id.layout_bunch)
    LinearLayout layout_bunch;
    private String order_id;

    @Override
    public int intiLayout() {
        return R.layout.activity_programme;
    }

    @Override
    public void initView() {

        ButterKnife.bind(this);
        image_back.setOnClickListener(this);
        Text_bunch_bonus.setOnClickListener(this);
        title_text.setText("方案详情");
        Intent intent  = getIntent();
        order_id = intent.getStringExtra("order_id");

        request();

    }

    private void request() {
        OkhttpUtlis okhttpUtlis = new OkhttpUtlis();


        okhttpUtlis.GetAsynMap(Url.baseUrl + "app/order/getOrderInfo?order_id=" + order_id, new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {

            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                String result = response.body().string();
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {


                        try {
                            JSONObject jsonObject = new JSONObject(result);

                            int code = jsonObject.getInt("code");
                            String msg = jsonObject.getString("msg");
                            if(code==10000){

                                FootBallOrderBean footBallOrderBean = JSON.parseObject(result,FootBallOrderBean.class);


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

                                Text_Scene.setText(footBallOrderBean.getData().getGame_sum()+"场");
                                Text_Multiple.setText(footBallOrderBean.getData().getMultiple()+"倍");
                                List<String> bunch = footBallOrderBean.getData().getBunch();
                                if(bunch.size()>2){
                                    layout_bunch.setVisibility(View.VISIBLE);

                                }else {
                                    layout_bunch.setVisibility(View.GONE);


                                }
                                if(footBallOrderBean.getData().getSeo_status()>0){
                                    Text_bunch_bonus.setVisibility(View.VISIBLE);
                                    switch (footBallOrderBean.getData().getSeo_status()){
                                        case 1:
                                            Text_bunch_bonus.setText("平均优化");
                                            break;
                                        case 2:
                                            Text_bunch_bonus.setText("博热优化");

                                            break;
                                        case 3:
                                            Text_bunch_bonus.setText("博冷优化");

                                            break;
                                    }
                                }else {
                                    Text_bunch_bonus.setVisibility(View.GONE);

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
                                    bonus=0;

                                }else {
                                    layout_publicv.setVisibility(View.GONE);
                                    MyTable.setVisibility(View.VISIBLE);
                                    bonus=1;
                                    initdata_details(ProgrammeActivity.this,MyTable,order_odds_info);

                                }


                            }else {
                                ToastUtil.showToast1(ProgrammeActivity.this,msg);

                            }

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }


                    }
                });



            }
        });




    }


    private void initdata_details(Context context, LinearLayout linearLayout, List<FootBallOrderBean.DataBean.OrderOddsInfoBean> order_odds_info) {

        //初始化内容
        for (int i = 0; i < order_odds_info.size(); i++) {
            LinearLayout relativeLayout = (LinearLayout) LayoutInflater.from(context).inflate(R.layout.programme_details, null);
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

    @Override
    public void initData() {

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.image_back:
                finish();

                break;
            case R.id.Text_bunch_bonus:

                if(bonus==0){

                }else {

                    Intent intent = new Intent(this,BonusdetailsActivity.class);
                    intent.putExtra("order_id",order_id+"");

                    startActivity(intent);
                }
                break;

        }
    }
}
