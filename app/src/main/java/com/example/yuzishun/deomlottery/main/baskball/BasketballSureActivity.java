package com.example.yuzishun.deomlottery.main.baskball;

import android.app.Dialog;
import android.content.Intent;
import android.graphics.drawable.BitmapDrawable;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.TextView;

import com.alibaba.fastjson.JSON;
import com.example.yuzishun.deomlottery.MainActivity;
import com.example.yuzishun.deomlottery.R;
import com.example.yuzishun.deomlottery.base.BaseActivity;
import com.example.yuzishun.deomlottery.base.Content;
import com.example.yuzishun.deomlottery.login.activity.LoginActivity;
import com.example.yuzishun.deomlottery.main.adapter.ModeRecyclerViewAdapter;
import com.example.yuzishun.deomlottery.main.adapter.ModeRecyclerViewMNAdapter;
import com.example.yuzishun.deomlottery.model.ChooseBaskBean;
import com.example.yuzishun.deomlottery.model.CodeBean;
import com.example.yuzishun.deomlottery.model.FootballBean;
import com.example.yuzishun.deomlottery.model.ItemPoint;
import com.example.yuzishun.deomlottery.model.MaxMinBean;
import com.example.yuzishun.deomlottery.model.MinAndMaxBean;
import com.example.yuzishun.deomlottery.model.SubMixBean;
import com.example.yuzishun.deomlottery.model.SubMixListBean;
import com.example.yuzishun.deomlottery.model.SureguanBean;
import com.example.yuzishun.deomlottery.net.Url;
import com.example.yuzishun.deomlottery.utils.SpUtil;
import com.example.yuzishun.deomlottery.utils.ToastUtil;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class BasketballSureActivity extends BaseActivity implements View.OnClickListener {
    @BindView(R.id.title_text)
    TextView title_text;
    @BindView(R.id.image_back)
    LinearLayout image_back;
    private FootballBean footballBean;
    @BindView(R.id.Sure_RecyclerView)
    RecyclerView Sure_RecyclerView;
    @BindView(R.id.button_sure)
    Button button_sure;
    @BindView(R.id.edit_Multiple)
    EditText edit_Multiple;
    private BasketBallSureRecyclerView bettingSureRecyclerView;
    @BindView(R.id.Text_More)
    TextView Text_More;
    private PopupWindow popupWindow;
    private View contentView;
    @BindView(R.id.Button_goon)
    Button Button_goon;
    @BindView(R.id.Text_money)
    TextView Text_money;
    @BindView(R.id.bunch_TextView)
    TextView bunch_TextView;
    private int length;
    private String format;
    @BindView(R.id.money)
    TextView money;
    private List<Double> one_mix_and_min;
    private List<Double> two_mix_and_min;
    private List<Double> three_mix_and_min;
    private List<Double> four_mix_and_min;
    private List<Double> fire_mix_and_min;
    private List<Double> minlist;

    private List<MinAndMaxBean> list_min_and_max = new ArrayList<>();
    private List<MaxMinBean> list_min_end = new ArrayList<>();
    private List<MaxMinBean> list_max_end = new ArrayList<>();

    //参数集合和需要展示的集合
    private List<String> list_adds;
    private  List<String> list_id;
    private String[] string_mode=new String[]{"2串1","3串1","4串1","5串1","6串1","7串1","8串1"};
    private String[] string_mode_two=new String[]{"单关","2串1","3串1","4串1","5串1","6串1","7串1","8串1"};
    private String[] string_MN=new String[]{"3串3","3串4","4串4","4串5","4串6","4串11","5串5","5串6","5串10","5串16","5串20","5串26","6串6","6串7", "6串15","6串20","6串22","6串35","6串42","6串50","6串57","7串7","7串8","7串21","7串35","7串120","8串8","8串9","8串28","8串56","8串70","8串247",};
    private String[] string_MN_bouns=new String[]{"3.3","3.4","4.4","4.5","4.6","4.11","5.5","5.6","5.10","5.16","5.20","5.26","6.6","6.7", "6.15","6.20","6.22","6.35","6.42","6.50","6.57","7.7","7.8","7.21","7.35","7.120","8.8","8.9","8.28","8.56","8.70","8.247",};
    private String[] string_MN_count=new String[]{"0","1","2","3","4","5","6","7","8","9","10","11","12","13", "14","15","16","17","18","19","20","21","22","23","24","25","26","27","28","29","30","31",};
    private List<SubMixBean> list_subMixBean = new ArrayList<>();
    private List<SureguanBean> list_sureguanBean_MN = new ArrayList<>();
    private List<SureguanBean> list_sureguanBean = new ArrayList<>();
    private List<SubMixListBean> list_stbMixListBean = new ArrayList<>();
    private  List<ChooseBaskBean> list_chooe;
    private String multiple="1";
    private String theory_bonus;
    private int isdan,single;
    @Override
    public int intiLayout() {
        return R.layout.activity_basketball_sure;
    }

    @Override
    public void initView() {
        ButterKnife.bind(this);
        image_back.setOnClickListener(this);
        title_text.setText("混合投注确认");
        button_sure.setOnClickListener(this);
        Text_More.setOnClickListener(this);
        Button_goon.setOnClickListener(this);
        Intent intent = getIntent();
        isdan = intent.getIntExtra("isdan",0);
        String title = intent.getStringExtra("title");
        single = intent.getIntExtra("single",0);
        title_text.setText(title);
        setlist();


    }

    @Override
    public void initData() {

    }





    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.image_back:
                Content.order_flag_bask=0;
                finish();
                break;
            case R.id.button_sure:
                dialog();
                break;
            case R.id.Text_More:
                popwindnow();

                break;
            case R.id.Button_goon:
                Content.order_flag_bask=0;

                finish();
                break;
        }
    }

    //初始化
    public void setlist(){
        list_chooe = Content.list_chooe_bask;
        for (int i = 0; i <list_chooe.size(); i++) {
            list_adds = new ArrayList<>();
            list_id = new ArrayList<>();
            minlist = new ArrayList<>();
            one_mix_and_min = new ArrayList<>();
            two_mix_and_min = new ArrayList<>();
            three_mix_and_min = new ArrayList<>();
            four_mix_and_min = new ArrayList<>();
            fire_mix_and_min = new ArrayList<>();

            List<ItemPoint> onelist = list_chooe.get(i).getOnelist();
            List<ItemPoint> twolist = list_chooe.get(i).getTwolist();
            List<ItemPoint> threelist = list_chooe.get(i).getThreelist();
            List<ItemPoint> fourlist = list_chooe.get(i).getFourlist();
            List<ItemPoint> firelist = list_chooe.get(i).getFirelist();

            for (int j = 0; j <onelist.size() ; j++) {
                if(onelist.get(j).isselect){
                    list_adds.add(onelist.get(j).getGame_odds_id());
                    minlist.add(Double.parseDouble(onelist.get(j).getOdds()));
                    list_id.add("胜负:"+onelist.get(j).getId());
                    one_mix_and_min.add(Double.parseDouble(onelist.get(j).getOdds()));
                }

            }
            for (int j = 0; j <twolist.size() ; j++) {
                if(twolist.get(j).isselect){
                    list_adds.add(twolist.get(j).getGame_odds_id());
                    list_id.add("胜分差:"+twolist.get(j).getId());
                    two_mix_and_min.add(Double.parseDouble(twolist.get(j).getOdds()));
                    minlist.add(Double.parseDouble(twolist.get(j).getOdds()));
                }

            }

            if(single==0){
                for (int j = 0; j <threelist.size() ; j++) {
                    if(threelist.get(j).isselect){
                        list_adds.add(threelist.get(j).getGame_odds_id());
                        list_id.add("胜分差:"+threelist.get(j).getId());
                        two_mix_and_min .add(Double.parseDouble(threelist.get(j).getOdds()));
                        minlist.add(Double.parseDouble(threelist.get(j).getOdds()));

                    }

                }




                for (int j = 0; j <fourlist.size() ; j++) {
                    if(fourlist.get(j).isselect){
                        list_adds.add(fourlist.get(j).getGame_odds_id());
                        list_id.add("让分胜负:"+fourlist.get(j).getId());
                        four_mix_and_min.add(Double.parseDouble(fourlist.get(j).getOdds()));
                        minlist.add(Double.parseDouble(fourlist.get(j).getOdds()));

                    }

                }


                for (int j = 0; j <firelist.size() ; j++) {
                    if(firelist.get(j).isselect){
                        list_adds.add(firelist.get(j).getGame_odds_id());
                        list_id.add("大小分:"+firelist.get(j).getId());
                        fire_mix_and_min.add(Double.parseDouble(firelist.get(j).getOdds()));
                        minlist.add(Double.parseDouble(firelist.get(j).getOdds()));
                    }

                }
            }else {

            }


            if(list_adds.size()==0){


            }else {
                SubMixBean subMixBean = new SubMixBean();
                subMixBean.setGame_id(list_chooe.get(i).getGame_id());
                subMixBean.setList(list_adds);

                subMixBean.setList_adds(minlist);

                list_subMixBean.add(subMixBean);

                SubMixListBean subMixListBean = new SubMixListBean();
                subMixListBean.setGame_id(list_chooe.get(i).getName());
                subMixListBean.setList(list_id);
                list_stbMixListBean.add(subMixListBean);

                MinAndMaxBean minAndMaxBean  = new MinAndMaxBean();


                minAndMaxBean.setOne_mix_and_min(one_mix_and_min);
                minAndMaxBean.setTwo_mix_and_min(two_mix_and_min);
                minAndMaxBean.setThree_mix_and_min(three_mix_and_min);
                minAndMaxBean.setFour_mix_and_min(four_mix_and_min);
                minAndMaxBean.setMinlist(minlist);
                minAndMaxBean.setGame_id(list_chooe.get(i).getGame_id());

                minAndMaxBean.setFire_mix_and_min(fire_mix_and_min);
                list_min_and_max.add(minAndMaxBean);
            }

        }

        for (int i = 0; i < list_min_and_max.size(); i++) {
            double max=0,aDouble = 0,bDouble = 0,cDouble = 0,dDouble= 0,eDouble = 0;
            MaxMinBean maxMinBean_min = new MaxMinBean();
            MaxMinBean maxMinBean_max = new MaxMinBean();
            if (list_min_and_max.get(i).getOne_mix_and_min().size() != 0) {
                aDouble = Double.valueOf(Collections.max(list_min_and_max.get(i).getOne_mix_and_min()));
            }
            if (list_min_and_max.get(i).getTwo_mix_and_min().size() != 0) {
                bDouble = Double.valueOf(Collections.max(list_min_and_max.get(i).getTwo_mix_and_min()));

            }
            if (list_min_and_max.get(i).getThree_mix_and_min().size() != 0) {
                cDouble = Double.valueOf(Collections.max(list_min_and_max.get(i).getThree_mix_and_min()));

            }
            if (list_min_and_max.get(i).getFour_mix_and_min().size() != 0) {
                dDouble = Double.valueOf(Collections.max(list_min_and_max.get(i).getFour_mix_and_min()));

            }
            if (list_min_and_max.get(i).getFire_mix_and_min().size() != 0) {
                eDouble = Double.valueOf(Collections.max(list_min_and_max.get(i).getFire_mix_and_min()));

            }
//            list_min_end.add(Double.valueOf(Collections.min(list_min_and_max.get(i).getMinlist())));
            maxMinBean_min.setGame_id(list_min_and_max.get(i).getGame_id());
            maxMinBean_min.setValue((Double.valueOf(Collections.min(list_min_and_max.get(i).getMinlist()))));
            list_min_end.add(maxMinBean_min);

            max += aDouble+bDouble+cDouble+dDouble+eDouble;
            maxMinBean_max.setValue(max);
            maxMinBean_max.setGame_id(list_min_and_max.get(i).getGame_id());

            list_max_end.add(maxMinBean_max);
        }

        Log.e("YZS",list_max_end.toString());


        Log.e("YZS",list_subMixBean.toString());
        Log.e("YZS",list_stbMixListBean.toString());


        Sure_RecyclerView.setLayoutManager(new LinearLayoutManager(this));
        Sure_RecyclerView.setNestedScrollingEnabled(false);

        bettingSureRecyclerView = new BasketBallSureRecyclerView(this,list_stbMixListBean,list_subMixBean);
        Sure_RecyclerView.setAdapter(bettingSureRecyclerView);
        bettingSureRecyclerView.setOnRecyclerViewListener(new BasketBallSureRecyclerView.OnRecyclerViewListener() {
            @Override
            public void onItemClick(int position) {


                if(list_sureguanBean.size()>1){


                for (int i = 0; i <list_chooe.size() ; i++) {
                    List<ItemPoint> onelist = list_chooe.get(i).getOnelist();
                    List<ItemPoint> twolist = list_chooe.get(i).getTwolist();
                    List<ItemPoint> threelist = list_chooe.get(i).getThreelist();
                    List<ItemPoint> fourlist = list_chooe.get(i).getFourlist();
                    List<ItemPoint> firelist = list_chooe.get(i).getFirelist();
                    if(list_chooe.get(i).getGame_id()==list_subMixBean.get(position).game_id){
                        for (int j = 0; j <onelist.size() ; j++) {
                            onelist.get(j).setIsselect(false);
                        }
                        for (int j = 0; j <twolist.size() ; j++) {
                            twolist.get(j).setIsselect(false);
                        }
                        if(single==0){
                            for (int j = 0; j <threelist.size() ; j++) {
                                threelist.get(j).setIsselect(false);
                            }
                            for (int j = 0; j <fourlist.size() ; j++) {
                                fourlist.get(j).setIsselect(false);

                            }
                            for (int j = 0; j <firelist.size() ; j++) {
                                firelist.get(j).setIsselect(false);


                            }
                        }



                    }




                }
                    if(popupWindow!=null){
                        popupWindow.dismiss();

                    }
                    for (int i = 0; i < list_min_end.size(); i++) {
                        if(list_min_end.get(i).getGame_id()==list_subMixBean.get(position).game_id){

                            list_min_end.remove(position);

                        }

                    }
                    for (int i = 0; i < list_max_end.size(); i++) {
                        if(list_max_end.get(i).getGame_id()==list_subMixBean.get(position).game_id){

                            list_max_end.remove(position);

                        }

                    }

                list_stbMixListBean.remove(position);
                list_subMixBean.remove(position);
                bettingSureRecyclerView.notifyDataSetChanged();
                listSureguanBean();
                    Text_More.setText("更多过关");

                    List<String> getbunch = getbunch();
                    Text_money.setText(getbunch.get(0));
                    money.setText(getbunch.get(1));

                }else {
                    ToastUtil.showToast1(BasketballSureActivity.this,"不可以删除");

                }

            }
        });

        listSureguanBean();
        List<String> getbunch = getbunch();
        Text_money.setText(getbunch.get(0));
        money.setText(getbunch.get(1));
        edit_Multiple.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                //在这里算倍数

                multiple = edit_Multiple.getText().toString();


                List<String> getbunch1 = getbunch();
                Text_money.setText(getbunch1.get(0));
                money.setText(getbunch1.get(1));


            }
        });


    }


    private void listSureguanBean(){
        list_sureguanBean_MN.clear();

        int length=0;


        if(isdan==0){
            length = getnumber(list_stbMixListBean.size());

            list_sureguanBean = new ArrayList<>();
            for (int i = 0; i <length ; i++) {
                SureguanBean sureguanBean = new SureguanBean();
                sureguanBean.setName(string_mode_two[i]);
                sureguanBean.setBunch(i+1+"");
                if(i==0){
                    sureguanBean.setIsselect(true);
                    bunch_TextView.setText(sureguanBean.getName());

                }else {
                    sureguanBean.setIsselect(false);

                }
                list_sureguanBean.add(sureguanBean);
            }
        }else {
            length = getnumber(list_stbMixListBean.size()-1);

            list_sureguanBean = new ArrayList<>();
            for (int i = 0; i <length ; i++) {
                SureguanBean sureguanBean = new SureguanBean();
                sureguanBean.setName(string_mode[i]);
                sureguanBean.setBunch(i+2+"");
                if(i==length-1){
                    sureguanBean.setIsselect(true);
                    bunch_TextView.setText(sureguanBean.getName());

                }else {
                    sureguanBean.setIsselect(false);

                }
                list_sureguanBean.add(sureguanBean);
            }
        }
        //m串n
        int mn_count = 0;
        int begin_count=0;
        int mn_size = list_sureguanBean.size();

        if(isdan==0) {
            mn_size--;
        }
        switch (mn_size){
            case 1:
                mn_count=0;
                begin_count = 0;
                break;
            case 2:
                begin_count=0;
                mn_count=2;

                break;
            case 3:
                begin_count=2;
                mn_count=6;

                break;
            case 4:
                begin_count=6;

                mn_count=12;

                break;
            case 5:
                begin_count=12;

                mn_count=21;

                break;
            case 6:
                begin_count=21;

                mn_count=26;

                break;
            case 7:
                begin_count=26;

                mn_count=32;

                break;
        }
        for (int i = begin_count; i < mn_count; i++) {
            SureguanBean sureguanBean = new SureguanBean();
            sureguanBean.setName(string_MN[i]);
            sureguanBean.setBunch(string_MN_bouns[i]);
            sureguanBean.setIsselect(false);
            sureguanBean.setCount(Integer.parseInt(string_MN_count[i]));
            list_sureguanBean_MN.add(sureguanBean);
        }
    }


    public int getnumber(int length) {
        int returnlenth = 0;

        int flag_two=0;
        int flag_three=0;


        if(length>=4){

            for (int i = 0; i < list_chooe.size(); i++) {

                List<ItemPoint> twolist = list_chooe.get(i).getTwolist();
                List<ItemPoint> threelist = list_chooe.get(i).getThreelist();

                for (int j = 0; j < twolist.size(); j++) {

                    if(twolist.get(j).isselect){

                        if(isdan==0){

                            returnlenth=4;

                        }else {
                            returnlenth=3;

                        }
                        flag_two=1;
                    }


                }

                if(single==0){
                    for (int j = 0; j < threelist.size(); j++) {

                        if(threelist.get(j).isselect){


                            if(isdan==0){

                                returnlenth=4;

                            }else {
                                returnlenth=3;

                            }
                            flag_three=1;
                        }


                    }
                }


            }

        }else {

            returnlenth = length;

        }
        if(flag_two==1||flag_three==1){


        }else {
            if(length>=8){
                returnlenth=7;

                if(isdan==0){
                    returnlenth=8;

                }

            }else {
                returnlenth = length;

            }


        }





        return returnlenth;
    }



        public Double getpour(List<Double> list,int number){
        Double pour= Double.valueOf(0);
        if(list.size()==number){
            //倍数
            pour = Calculation(list);
//
        }else {

            List<List<Double>> getdata = getdata(list, number);
            Log.e("YZS",getdata+"");
            for (int i = 0; i < getdata.size(); i++) {
                pour +=Calculation(getdata.get(i));

            }


        }

        return  pour;
    }

    public List<String> getbunch(){
        List<String> list_retun = new ArrayList<>();
        String bunch="";
        String bunchminandmax ="";

        //最大值
        Double maxbunch = 0.0;
        List<Double> list_max = new ArrayList<>();

        for (int i = 0; i < list_max_end.size(); i++) {

            list_max.add(list_max_end.get(i).getValue());
        }
        //最小值
        Double pourmin = Double.valueOf(1);
        List<Integer> list = new ArrayList<>();
        List<Double> list_min = new ArrayList<>();

        for (int i = 0; i < list_min_end.size(); i++) {

            list_min.add(list_min_end.get(i).getValue());
        }
        //多少注
        Double pour = Double.valueOf(0);
        List<Double> list_index = new ArrayList<>();
        for (int i = 0; i < list_subMixBean.size(); i++) {
            list_index.add(Double.valueOf(list_subMixBean.get(i).getList().size()));

        }
        int mnIstrue = 0;

        for (int i = 0; i <list_sureguanBean.size() ; i++) {
            if(list_sureguanBean.get(i).isselect){
                mnIstrue=1;
                pour +=getpour(list_index, Integer.parseInt(list_sureguanBean.get(i).getBunch()));

            }

        }

        if (mnIstrue == 0) {
            //如果是选的m串n的话，把每一个选项都列出来，单独加上去
            for (int i = 0; i < list_sureguanBean_MN.size(); i++) {
                if(list_sureguanBean_MN.get(i).isselect){

                    switch (list_sureguanBean_MN.get(i).getCount()){
                        case 0:
                            //3串3
                            pour = getpour(list_index, 2);
                            maxbunch = getpour(list_max, 2);
                            list.add(2);

                            break;
                        case 1:
                            //3串4
                            pour = getpour(list_index, 2)+getpour(list_index, 3);
                            maxbunch = getpour(list_max, 2)+getpour(list_max, 3);
                            list.add(2);
                            list.add(3);
                            break;
                        case 2:
                            //4串4
                            pour = getpour(list_index, 3);
                            maxbunch = getpour(list_max, 3);
                            list.add(3);

                            break;
                        case 3:
                            //4串5
                            pour = getpour(list_index, 3)+getpour(list_index, 4);
                            maxbunch = getpour(list_max, 3)+getpour(list_max, 4);
                            list.add(3);
                            list.add(4);

                            break;
                        case 4:
                            //4串6
                            pour = getpour(list_index, 2);
                            maxbunch = getpour(list_max, 2);
                            list.add(2);
                            break;
                        case 5:
                            //4串11
                            pour = getpour(list_index, 2)+getpour(list_index, 3)+getpour(list_index, 4);
                            maxbunch = getpour(list_max, 2)+getpour(list_max, 3)+getpour(list_max, 4);
                            list.add(2);
                            list.add(3);
                            list.add(4);
                            break;
                        case 6:
                            //5串5
                            pour = getpour(list_index, 4);
                            maxbunch = getpour(list_max, 4);
                            list.add(4);

                            break;
                        case 7:
                            //5串6
                            pour = getpour(list_index, 4)+getpour(list_index, 5);
                            maxbunch = getpour(list_max, 4)+getpour(list_max, 5);
                            list.add(4);
                            list.add(5);
                            break;
                        case 8:
                            //5串10
                            pour = getpour(list_index, 2);
                            maxbunch = getpour(list_max, 2);
                            list.add(2);

                            break;
                        case 9:
                            //5串16
                            pour = getpour(list_index, 3)+getpour(list_index, 4)+getpour(list_index, 5);
                            maxbunch = getpour(list_max, 3)+getpour(list_max, 4)+getpour(list_max, 5);
                            list.add(3);
                            list.add(4);
                            list.add(5);

                            break;
                        case 10:
                            //5串20
                            pour = getpour(list_index, 2)+getpour(list_index, 3);
                            maxbunch = getpour(list_max, 2)+getpour(list_max, 3);
                            list.add(2);
                            list.add(3);

                            break;
                        case 11:
                            //5串26
                            pour = getpour(list_index, 2)+getpour(list_index, 3)+getpour(list_index, 4)+getpour(list_index, 5);
                            maxbunch = getpour(list_max, 2)+getpour(list_max, 3)+getpour(list_max, 4)+getpour(list_max, 5);
                            list.add(2);
                            list.add(3);
                            list.add(4);
                            list.add(5);

                            break;
                        case 12:
                            //6串6
                            pour = getpour(list_index, 5);
                            maxbunch = getpour(list_max, 5);
                            list.add(5);

                            break;
                        case 13:
                            //6串7
                            pour = getpour(list_index, 5)+getpour(list_index, 6);
                            maxbunch = getpour(list_max, 6)+getpour(list_max, 6);
                            list.add(5);
                            list.add(6);

                            break;
                        case 14:
                            //6串15
                            pour = getpour(list_index, 2);
                            maxbunch = getpour(list_max, 2);
                            list.add(2);

                            break;
                        case 15:
                            //6串20
                            pour = getpour(list_index, 3);
                            maxbunch = getpour(list_max, 3);
                            list.add(3);

                            break;
                        case 16:
                            //6串22
                            pour = getpour(list_index, 4)+getpour(list_index, 5)+getpour(list_index, 6);
                            maxbunch = getpour(list_max, 4)+getpour(list_max, 5)+getpour(list_max, 6);
                            list.add(4);
                            list.add(5);
                            list.add(6);

                            break;
                        case 17:
                            //6串35
                            pour = getpour(list_index, 2)+getpour(list_index, 3);
                            maxbunch = getpour(list_max, 2)+getpour(list_max, 3);
                            list.add(2);
                            list.add(3);
                            break;
                        case 18:
                            //6串42
                            pour = getpour(list_index, 3)+getpour(list_index, 4)+getpour(list_index, 5)+getpour(list_index, 6);
                            maxbunch = getpour(list_max, 3)+getpour(list_max, 4)+getpour(list_max, 5)+getpour(list_max, 6);
                            list.add(3);
                            list.add(4);
                            list.add(5);
                            list.add(6);

                            break;
                        case 19:
                            //6串50
                            pour = getpour(list_index, 2)+getpour(list_index, 3)+getpour(list_index, 4);
                            maxbunch = getpour(list_max, 2)+getpour(list_max, 3)+getpour(list_max, 4);
                            list.add(2);
                            list.add(3);
                            list.add(4);
                            break;
                        case 20:
                            //6串57
                            pour = getpour(list_index, 2)+getpour(list_index, 3)+getpour(list_index, 4)+getpour(list_index, 5)+getpour(list_index, 6);
                            maxbunch = getpour(list_max, 2)+getpour(list_max, 3)+getpour(list_max, 4)+getpour(list_max, 5)+getpour(list_max, 6);
                            list.add(2);
                            list.add(3);
                            list.add(4);
                            list.add(5);
                            list.add(6);
                            break;
                        case 21:
                            //7串7
                            pour = getpour(list_index, 6);
                            maxbunch = getpour(list_max, 6);
                            list.add(6);

                            break;
                        case 22:
                            //7串8
                            pour = getpour(list_index, 6)+getpour(list_index, 7);
                            maxbunch = getpour(list_max, 6)+getpour(list_max, 7);
                            list.add(6);
                            list.add(7);

                            break;
                        case 23:
                            //7串21
                            pour = getpour(list_index, 5);
                            maxbunch = getpour(list_max, 5);
                            list.add(5);

                            break;
                        case 24:
                            //7串35
                            pour = getpour(list_index, 4);
                            maxbunch = getpour(list_max, 4);
                            list.add(4);

                            break;
                        case 25:
                            //7串120
                            pour = getpour(list_index, 2)+getpour(list_index, 3)+getpour(list_index, 4)+getpour(list_index, 5)+getpour(list_index, 6)+getpour(list_index, 7);
                            maxbunch = getpour(list_max, 2)+getpour(list_max, 3)+getpour(list_max, 4)+getpour(list_max, 5)+getpour(list_max, 6)+getpour(list_max, 7);
                            list.add(2);
                            list.add(3);
                            list.add(4);
                            list.add(5);
                            list.add(6);
                            list.add(7);


                            break;
                        case 26:
                            //8串8
                            pour = getpour(list_index, 7);
                            maxbunch = getpour(list_max, 7);
                            list.add(7);


                            break;
                        case 27:
                            //8串9
                            pour = getpour(list_index, 7)+getpour(list_index, 8);
                            maxbunch = getpour(list_max, 7)+getpour(list_max, 8);
                            list.add(7);
                            list.add(8);
                            break;
                        case 28:
                            //8串28
                            pour = getpour(list_index, 6);
                            maxbunch = getpour(list_max, 6);
                            list.add(6);

                            break;
                        case 29:
                            //8串56
                            pour = getpour(list_index, 5);
                            maxbunch = getpour(list_max, 5);
                            list.add(5);

                            break;
                        case 30:
                            //8串70
                            pour = getpour(list_index, 4);
                            maxbunch = getpour(list_max, 4);
                            list.add(4);
                            break;
                        case 31:
                            //8串247
                            pour = getpour(list_index, 2)+getpour(list_index, 3)+getpour(list_index, 4)+getpour(list_index, 5)+getpour(list_index, 6)+getpour(list_index, 7)+getpour(list_index, 8);
                            maxbunch = getpour(list_max, 2)+getpour(list_max, 3)+getpour(list_max, 4)+getpour(list_max, 5)+getpour(list_max, 6)+getpour(list_max, 7)+getpour(list_max, 8);
                            list.add(2);
                            list.add(3);
                            list.add(4);
                            list.add(5);
                            list.add(6);
                            list.add(7);
                            list.add(8);

                            break;

                    }




                }

            }

        }else {
            //加入最小值
            for (int i = 0; i < list_sureguanBean.size(); i++) {
                if (list_sureguanBean.get(i).isIsselect()) {

                    list.add(Integer.parseInt(list_sureguanBean.get(i).getBunch()));
                }

            }



            //加入最大值
            for (int i = 0; i < list_sureguanBean.size(); i++) {
                if (list_sureguanBean.get(i).isselect) {
                    maxbunch += getpour(list_max, Integer.parseInt(list_sureguanBean.get(i).getBunch()));

                }

            }



        }
        //计算多少注
        Log.e("YZS",pour+"");
        NumberFormat nf = new DecimalFormat("#.##");
        format = nf.format(pour);
        String s = edit_Multiple.getText().toString();
        if(s.equals("")){
            s="1";

        }
        bunch =  format+"注  共"+Double.parseDouble(format)*Integer.parseInt(s)*2+"元";

        Log.e("YZS",bunch+"");





        Collections.sort(list_min);
        for (int j = 0; j <list.get(0) ; j++) {


            pourmin *=list_min.get(j);
        }




        NumberFormat nfmin = new DecimalFormat("#.##");
        String pmin = nfmin.format(pourmin);
        String smin = edit_Multiple.getText().toString();
        if(smin.equals("")){
            smin="1";

        }
        String maxbunch_end = nfmin.format(maxbunch);

        bunchminandmax =  "理论奖金："+Double.parseDouble(pmin)*Integer.parseInt(smin)*2+"元~"+Double.parseDouble(maxbunch_end)*Integer.parseInt(smin)*2+"元";

        Log.e("YZS",bunchminandmax+"");

        theory_bonus = bunchminandmax;

        list_retun.add(bunch);
        list_retun.add(bunchminandmax);

        return list_retun;

    }


    public List<List<Double>> getdata(List<Double> list,int number){
        List<List<Double>> alist = new ArrayList<>();

        int size = list.size();

        if(size<=0||size<number){

            return alist;
        }
        for (int i = 0; i <list.size() ; i++) {
            List<Double> doubles  = new ArrayList<>();
            doubles.add(list.get(i));

            if(number==1){

                alist.add(doubles);

            }else {
                List<Double> blist= new ArrayList<>();
                for (int j = i+1; j <list.size() ; j++) {
                    if(j==0||i+1>j){

                        continue;
                    }else {

                        blist.add(list.get(j));


                    }
                }

                List<List<Double>> clist = new ArrayList<>();
                clist = getdata(blist,number-1);
                for (int j = 0; j <clist.size() ; j++) {
                    List<Double> dlist = clist.get(j);
                    dlist.add(list.get(i));
                    alist.add(dlist);

                }

            }

        }



        return alist;
    }


    public Double Calculation(List<Double> list){
        Double pour= Double.valueOf(1);
        for (int i = 0; i < list.size(); i++) {
            pour *= list.get(i);
        }

        return pour;
    }
    private void darkenBackground(Float bgcolor) {
        WindowManager.LayoutParams lp = getWindow().getAttributes();
        lp.alpha = bgcolor;

        getWindow().addFlags(WindowManager.LayoutParams.FLAG_DIM_BEHIND);
        getWindow().setAttributes(lp);

    }

    private void popwindnow() {
        //加载弹出框的布局
        contentView = LayoutInflater.from(BasketballSureActivity.this).inflate(
                R.layout.pop_mixed_guan, null);


        popupWindow = new PopupWindow(contentView, LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        popupWindow.setFocusable(false);// 取得焦点
        //注意  要是点击外部空白处弹框消息  那么必须给弹框设置一个背景色  不然是不起作用的
        popupWindow.setBackgroundDrawable(new BitmapDrawable());
        //点击外部消失
        popupWindow.setOutsideTouchable(false);
        //设置可以点击
        popupWindow.setTouchable(true);
        //进入退出的动画，指定刚才定义的style
        popupWindow.setAnimationStyle(R.style.mypopwindow_anim_style);

        // 按下android回退物理键 PopipWindow消失解决
        popupWindow.showAtLocation(contentView, Gravity.BOTTOM, 0, 0);

        Button Text_cancle =  contentView.findViewById(R.id.Text_cancle);
        Button Text_sure =  contentView.findViewById(R.id.Text_sure);
        final TextView text_mode = contentView.findViewById(R.id.text_mode);
        RecyclerView recyclerView = contentView.findViewById(R.id.ModeRecyclerView);

        recyclerView.setLayoutManager(new GridLayoutManager(this,4));
        RecyclerView ModeRecyclerViewMN = contentView.findViewById(R.id.ModeRecyclerViewMN);
        ModeRecyclerViewMNAdapter modeRecyclerViewMN = new ModeRecyclerViewMNAdapter(BasketballSureActivity.this,list_sureguanBean_MN);
        ModeRecyclerViewMN.setLayoutManager(new GridLayoutManager(this,4));
        ModeRecyclerViewMN.setAdapter(modeRecyclerViewMN);
        text_mode.setText(list_stbMixListBean.size()+"场比赛过关方式");
        final ModeRecyclerViewAdapter modeRecyclerViewAdapter = new ModeRecyclerViewAdapter(BasketballSureActivity.this,list_sureguanBean);
        recyclerView.setAdapter(modeRecyclerViewAdapter);
        if(list_subMixBean.size()>Integer.parseInt(list_sureguanBean.get(list_sureguanBean.size()-1).getBunch())){
            ModeRecyclerViewMN.setVisibility(View.GONE);
        }

        darkenBackground(0.5f);

        //popwindow消失的时候设置屏幕变亮

        popupWindow.setOnDismissListener(new PopupWindow.OnDismissListener() {
            @Override
            public void onDismiss() {

                darkenBackground(1f);

            }
        });
        modeRecyclerViewMN.setOnRecyclerViewListener(new ModeRecyclerViewMNAdapter.OnRecyclerViewListener() {
            @Override
            public void onItemClick(int position) {
                for (int i = 0; i < list_sureguanBean.size(); i++) {
                    list_sureguanBean.get(i).setIsselect(false);

                }
                for (int i = 0; i < list_sureguanBean_MN.size(); i++) {
                    if(i==position){
                        list_sureguanBean_MN.get(i).setIsselect(true);

                    }else {
                        list_sureguanBean_MN.get(i).setIsselect(false);

                    }

                }
                modeRecyclerViewMN.notifyDataSetChanged();
                modeRecyclerViewAdapter.notifyDataSetChanged();
            }
        });

        modeRecyclerViewAdapter.setOnRecyclerViewListener(new ModeRecyclerViewAdapter.OnRecyclerViewListener() {
            @Override
            public void onItemClick(int position) {
                for (int i = 0; i < list_sureguanBean_MN.size(); i++) {
                    list_sureguanBean_MN.get(i).setIsselect(false);

                }
                int flag_0 = 0,flag_1=0;
                if(list_sureguanBean.get(position).isIsselect()){
                    for (int i = 0; i < list_sureguanBean.size(); i++) {
                        if(list_sureguanBean.get(i).isIsselect()){
                            flag_0++;
                        }else {
                            flag_1++;

                        }


                    }
                    if(list_sureguanBean.size()-1==flag_1){

                        ToastUtil.showToast1(BasketballSureActivity.this,"必须选择一个");

                    }else {
                        modeRecyclerViewAdapter.choiceState(position);

                    }

                }else {

                    modeRecyclerViewAdapter.choiceState(position);

                }
                modeRecyclerViewMN.notifyDataSetChanged();



            }
        });
        Text_cancle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int ii = 1;
                for (int i = 0; i < list_sureguanBean.size() ; i++) {

                    if(list_sureguanBean.get(i).isselect){
                        ii++;
                    }else {

                    }

                }
                if(ii==1){
                    Text_More.setText(ii+"个方式");

                }else {
                    Text_More.setText(ii-1+"个方式");

                }
                popupWindow.dismiss();
                List<String> getbunch = getbunch();
                Text_money.setText(getbunch.get(0));
                money.setText(getbunch.get(1));
                if(list_sureguanBean.size()==0){

                    ToastUtil.showToast1(BasketballSureActivity.this,"请重新选择比赛");
                }else {
                    for (int i = 0; i <list_sureguanBean.size() ; i++) {
                        if(list_sureguanBean.get(i).isselect){
                            bunch_TextView.setText(list_sureguanBean.get(i).getName());

                        }

                    }
                    for (int i = 0; i < list_sureguanBean_MN.size(); i++) {
                        if(list_sureguanBean_MN.get(i).isselect){
                            bunch_TextView.setText(list_sureguanBean_MN.get(i).getName());

                        }

                    }

                }

            }
        });
        Text_sure.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                int ii = 1;
                for (int i = 0; i < list_sureguanBean.size() ; i++) {

                    if(list_sureguanBean.get(i).isselect){
                        ii++;
                    }else {

                    }

                }
                if(ii==1){
                    Text_More.setText(ii+"个方式");

                }else {
                    Text_More.setText(ii-1+"个方式");

                }
                popupWindow.dismiss();
                List<String> getbunch = getbunch();
                Text_money.setText(getbunch.get(0));
                money.setText(getbunch.get(1));
                if(list_sureguanBean.size()==0){

                    ToastUtil.showToast1(BasketballSureActivity.this,"请重新选择比赛");
                }else {
                    for (int i = 0; i <list_sureguanBean.size() ; i++) {
                        if(list_sureguanBean.get(i).isselect){
                            bunch_TextView.setText(list_sureguanBean.get(i).getName());

                        }

                    }
                    for (int i = 0; i < list_sureguanBean_MN.size(); i++) {
                        if(list_sureguanBean_MN.get(i).isselect){
                            bunch_TextView.setText(list_sureguanBean_MN.get(i).getName());

                        }

                    }

                }


            }
        });




    }

    private void dialog() {
        final Dialog dialog = new Dialog(this,R.style.dialog);
        dialog.setContentView(R.layout.dialog_sure_mixed);
        Window window = dialog.getWindow();
        WindowManager.LayoutParams lp = window.getAttributes();

        lp.width = WindowManager.LayoutParams.WRAP_CONTENT;
        getWindow().setBackgroundDrawable(new BitmapDrawable());
        lp.height = WindowManager.LayoutParams.WRAP_CONTENT;
        dialog.getWindow().setAttributes(lp);
        dialog.setCanceledOnTouchOutside(false);


        dialog.show();
        TextView text_bei = dialog.findViewById(R.id.text_bei);
        TextView pop_money = dialog.findViewById(R.id.pop_money);
        TextView pop_bunch = dialog.findViewById(R.id.pop_bunch);
        Button button_cancle = dialog.findViewById(R.id.button_cancle);
        if(edit_Multiple.getText().toString().equals("")){
            ToastUtil.showToast1(this,"请输入倍数");
        }else {

            text_bei.setText(edit_Multiple.getText().toString()+"倍");
            pop_bunch.setText(format+"注");

            pop_money.setText(Integer.parseInt(format)*Integer.parseInt(edit_Multiple.getText().toString())*2+"元");
        }

        Button submit_order =  dialog.findViewById(R.id.submit_order);
        button_cancle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });

        submit_order.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                suborder();

            }
        });







    }


    private void suborder(){
        try {






            // 创建json对象
            JSONObject jsonObject = new JSONObject();
            org.json.JSONArray jsonArray1 = new org.json.JSONArray();
            List<JSONObject> list_jsonobject = new ArrayList<>();

            for (int i = 0; i <list_subMixBean.size() ; i++) {
                JSONObject jsonObject_son = new JSONObject();

                org.json.JSONArray jsonArray_son = new org.json.JSONArray();

                for (int j = 0; j <list_subMixBean.get(i).getList().size() ; j++) {
                    jsonArray_son.put(list_subMixBean.get(i).getList().get(j));

                }
                jsonObject_son.put("game_id",list_subMixBean.get(i).game_id);
                jsonObject_son.put("bet_id",jsonArray_son);

                list_jsonobject.add(jsonObject_son);

            }
            for (int i = 0; i <list_jsonobject.size() ; i++) {

                jsonArray1.put(list_jsonobject.get(i));

            }

            org.json.JSONArray jsonArray = getlist_guan();
            if(jsonArray.length()==0){
                jsonArray = getlist_guan_mn();

            }
            jsonObject.put("multiple",multiple+"");
            jsonObject.put("bunch",jsonArray);
            jsonObject.put("game_type","1");
            jsonObject.put("game_bet",jsonArray1);
            jsonObject.put("theory_bonus",theory_bonus);
            String data = jsonObject.toString();
            Log.e("YZS",data.toString());

            OkHttpClient client = new OkHttpClient();


            RequestBody body = RequestBody.create(okhttp3.MediaType.parse("application/json;charset=UTF-8"), data);
            final Request request = new Request.Builder()
                    .url(Url.baseUrl+"app/order/addOrder")
                    .addHeader("token", Content.ToKen)
                    .post(body)

                    .build();
            Call call = client.newCall(request);
            call.enqueue(new Callback() {
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



                            CodeBean codeBean = JSON.parseObject(result,CodeBean.class);
                            if(codeBean.getCode()==10000){
                                Content.order_flag_bask=1;
                                finish();
                                ToastUtil.showToast1(BasketballSureActivity.this,codeBean.getMsg()+"");
                            }else if(codeBean.getCode()==10004){
                                MainActivity.intentsat.finish();
                                startActivity(new Intent(BasketballSureActivity.this, LoginActivity.class));
                                SpUtil spUtil = new SpUtil(BasketballSureActivity.this,"token");
                                spUtil.putString("token","");
                                ToastUtil.showToast(BasketballSureActivity.this,codeBean.getMsg()+"");

                            }else {
                                ToastUtil.showToast(BasketballSureActivity.this,codeBean.getMsg()+"");

                            }
                            }catch (Exception e){
                            ToastUtil.showToast1(BasketballSureActivity.this,"返回数据有误");

                            }

                        }
                    });
                }
            });

        } catch (JSONException e) {
            e.printStackTrace();
        }


    }

    //获取几串几的集合
    public org.json.JSONArray getlist_guan(){
        org.json.JSONArray jsonArray = new org.json.JSONArray();
        for (int i = 0; i < list_sureguanBean.size(); i++) {
            if(list_sureguanBean.get(i).isselect){
                jsonArray.put(list_sureguanBean.get(i).getBunch());
            }else {


            }


        }



        return jsonArray;
    }
    public org.json.JSONArray getlist_guan_mn(){
        org.json.JSONArray jsonArray = new org.json.JSONArray();
        for (int i = 0; i < list_sureguanBean_MN.size(); i++) {
            if(list_sureguanBean_MN.get(i).isselect){
                jsonArray.put(list_sureguanBean_MN.get(i).getBunch());
            }
        }
        return jsonArray;

    }


}
