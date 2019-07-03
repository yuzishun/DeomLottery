package com.example.yuzishun.newdeom.main.single;

import android.app.Dialog;
import android.content.Intent;
import android.graphics.drawable.BitmapDrawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
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
import com.example.yuzishun.newdeom.MainActivity;
import com.example.yuzishun.newdeom.R;
import com.example.yuzishun.newdeom.base.BaseActivity;
import com.example.yuzishun.newdeom.base.Content;
import com.example.yuzishun.newdeom.login.activity.LoginActivity;
import com.example.yuzishun.newdeom.main.activity.MixedSureActivity;
import com.example.yuzishun.newdeom.main.adapter.BettingSureRecyclerView;
import com.example.yuzishun.newdeom.main.adapter.ModeRecyclerViewAdapter;
import com.example.yuzishun.newdeom.model.ChooseMixedBean;
import com.example.yuzishun.newdeom.model.CodeBean;
import com.example.yuzishun.newdeom.model.ItemPoint;
import com.example.yuzishun.newdeom.model.MinAndMaxBean;
import com.example.yuzishun.newdeom.model.SubMixBean;
import com.example.yuzishun.newdeom.model.SubMixListBean;
import com.example.yuzishun.newdeom.model.SureguanBean;
import com.example.yuzishun.newdeom.net.Url;
import com.example.yuzishun.newdeom.utils.SpUtil;
import com.example.yuzishun.newdeom.utils.ToastUtil;

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

public class SingleSureActivity extends BaseActivity implements View.OnClickListener {

    @BindView(R.id.title_text)
    TextView title_text;
    @BindView(R.id.image_back)
    LinearLayout image_back;
    @BindView(R.id.Sure_RecyclerView)
    RecyclerView Sure_RecyclerView;
    @BindView(R.id.button_sure)
    Button button_sure;
    @BindView(R.id.edit_Multiple)
    EditText edit_Multiple;
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
    @BindView(R.id.money)
    TextView money;
    private long mLastClickTime = 0;
    public static final long TIME_INTERVAL = 1000L;
    private BettingSureRecyclerView bettingSureRecyclerView;
    private List<Double> minlist;
    private List<Double> one_max;

    private  List<String> list_adds;
    private  List<String> list_id;
    private List<Double> list_max_end;

    private List<MinAndMaxBean> list_min_and_max = new ArrayList<>();
    private List<Double> list_min_end = new ArrayList<>();
    private String format;
    private String[] string_mode=new String[]{"单关","2串1","3串1","4串1","5串1","6串1","7串1","8串1"};
    private List<SubMixBean> list_subMixBean = new ArrayList<>();
    private List<SureguanBean> list_sureguanBean = new ArrayList<>();
    private List<SubMixListBean> list_stbMixListBean = new ArrayList<>();
    private String multiple="1";
    private String theory_bonus="理论奖金:想要多少就要多少";

    @Override
    public int intiLayout() {
        return R.layout.activity_single_sure;
    }

    @Override
    public void initView() {
        ButterKnife.bind(this);
        image_back.setOnClickListener(this);
        title_text.setText("胜负平");
        Intent intent = getIntent();
        int single = intent.getIntExtra("single",0);
        switch (single){

            case 1:
                title_text.setText("胜负平");

                break;
            case 2:
                title_text.setText("让球胜负平");

                break;
            case 3:
                title_text.setText("比分");

                break;
            case 4:
                title_text.setText("总进球");

                break;
            case 5:
                title_text.setText("半全场");

                break;
        }
        button_sure.setOnClickListener(this);
        Text_More.setOnClickListener(this);
        Button_goon.setOnClickListener(this);

        final List<ChooseMixedBean> list_chooe_single = Content.list_chooe_single;
        for (int i = 0; i <list_chooe_single.size() ; i++) {
            list_adds = new ArrayList<>();
            list_id = new ArrayList<>();
            minlist = new ArrayList<>();
            one_max = new ArrayList<>();
            list_max_end = new ArrayList<>();
            List<ItemPoint> onelist = list_chooe_single.get(i).getOnelist();
            for (int j = 0; j < onelist.size(); j++) {

                if(onelist.get(j).isselect){
                    list_id.add(""+onelist.get(j).getId());
                    one_max.add(Double.parseDouble(onelist.get(j).getOdds()));
                    minlist.add(Double.parseDouble(onelist.get(j).getOdds()));
                    list_adds.add(onelist.get(j).getGame_odds_id());

                }else {

                }

            }
            if(list_adds.size()==0){


            }else {

                SubMixBean subMixBean = new SubMixBean();
                subMixBean.setGame_id(list_chooe_single.get(i).getGame_id());
                subMixBean.setList(list_adds);


                list_subMixBean.add(subMixBean);

                SubMixListBean subMixListBean = new SubMixListBean();
                subMixListBean.setGame_id(list_chooe_single.get(i).getName());

                subMixListBean.setList(list_id);

                list_stbMixListBean.add(subMixListBean);

                MinAndMaxBean minAndMaxBean  = new MinAndMaxBean();


                minAndMaxBean.setOne_mix_and_min(one_max);

                minAndMaxBean.setMinlist(minlist);

                list_min_and_max.add(minAndMaxBean);

            }


        }
        for (int i = 0; i < list_min_and_max.size(); i++) {
            double max=0,aDouble = 0,bDouble = 0,cDouble = 0,dDouble= 0,eDouble = 0;

            if (list_min_and_max.get(i).getOne_mix_and_min().size() != 0) {
                aDouble = Double.valueOf(Collections.max(list_min_and_max.get(i).getOne_mix_and_min()));
            }
//            if (list_min_and_max.get(i).getTwo_mix_and_min().size() != 0) {
//                bDouble = Double.valueOf(Collections.max(list_min_and_max.get(i).getTwo_mix_and_min()));
//
//            }
//            if (list_min_and_max.get(i).getThree_mix_and_min().size() != 0) {
//                cDouble = Double.valueOf(Collections.max(list_min_and_max.get(i).getThree_mix_and_min()));
//
//            }
//            if (list_min_and_max.get(i).getFour_mix_and_min().size() != 0) {
//                dDouble = Double.valueOf(Collections.max(list_min_and_max.get(i).getFour_mix_and_min()));
//
//            }
//            if (list_min_and_max.get(i).getFire_mix_and_min().size() != 0) {
//                eDouble = Double.valueOf(Collections.max(list_min_and_max.get(i).getFire_mix_and_min()));
//
//            }
            list_min_end.add(Double.valueOf(Collections.min(list_min_and_max.get(i).getMinlist())));


            max += aDouble+bDouble+cDouble+dDouble+eDouble;
            list_max_end.add(max);
        }



        Log.e("Single_YZS",list_subMixBean.toString());
        Log.e("Single_YZS",list_stbMixListBean.toString());


        Sure_RecyclerView.setLayoutManager(new LinearLayoutManager(this));
        Sure_RecyclerView.setNestedScrollingEnabled(false);

        bettingSureRecyclerView = new BettingSureRecyclerView(this,list_stbMixListBean,list_subMixBean,0);
        Sure_RecyclerView.setAdapter(bettingSureRecyclerView);
        bettingSureRecyclerView.setOnRecyclerViewListener(new BettingSureRecyclerView.OnRecyclerViewListener() {
            @Override
            public void onItemClick(int position) {

                if(list_sureguanBean.size()>=2){
                    for (int i = 0; i <list_chooe_single.size() ; i++) {
                        List<ItemPoint> onelist = list_chooe_single.get(i).getOnelist();
                        if(list_chooe_single.get(i).getGame_id()==list_subMixBean.get(position).game_id){
                            for (int j = 0; j <onelist.size() ; j++) {
                                onelist.get(j).setIsselect(false);
                            }




                        }else {

                        }




                    }
                    list_stbMixListBean.remove(position);
                    list_subMixBean.remove(position);
                    bettingSureRecyclerView.notifyDataSetChanged();
                    listSureguanBean();
//                     理论奖金 先不显示
                    List<String> getbunch = getbunch();
                    Text_money.setText(getbunch.get(0));
                    money.setText(getbunch.get(1));


                }else {

                    ToastUtil.showToast1(SingleSureActivity.this,"不可以删除");

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
        int length=0;

        switch (list_stbMixListBean.size()){
            case 1:
                length = 1;

                break;
            case 2:
                length = 2;
                break;
            case 3:
                length = 3;

                break;
            case 4:
                length = 4;

                break;
            case 5:
                length = 5;

                break;
            case 6:
                length = 6;

                break;
            case 7:
                length = 7;

                break;
            case 8:
                length = 8;

                break;
        }

        if(list_stbMixListBean.size()>8){

            length=8;

        }



        list_sureguanBean = new ArrayList<>();
        for (int i = 0; i <length ; i++) {
            SureguanBean sureguanBean = new SureguanBean();
            sureguanBean.setName(string_mode[i]);
            sureguanBean.setBunch(i+1+"");
            if(i==0){
                sureguanBean.setIsselect(true);
                bunch_TextView.setText(sureguanBean.getName());

            }else {
                sureguanBean.setIsselect(false);

            }
            list_sureguanBean.add(sureguanBean);
        }
    }


    @Override
    public void initData() {

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.image_back:
                Content.order_flag_single=0;
                finish();
                break;
            case R.id.button_sure:
                dialog();
                break;
            case R.id.Text_More:
                popwindnow();

                break;
            case R.id.Button_goon:
                Content.order_flag_single=0;

                finish();
                break;
        }
    }



    private void popwindnow() {
        //加载弹出框的布局
        contentView = LayoutInflater.from(SingleSureActivity.this).inflate(
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

        TextView Text_cancle =  contentView.findViewById(R.id.Text_cancle);
        TextView Text_sure =  contentView.findViewById(R.id.Text_sure);
        final TextView text_mode = contentView.findViewById(R.id.text_mode);
        RecyclerView recyclerView = contentView.findViewById(R.id.ModeRecyclerView);

        recyclerView.setLayoutManager(new GridLayoutManager(this,4));




        text_mode.setText(list_stbMixListBean.size()+"场比赛过关方式");
        final ModeRecyclerViewAdapter modeRecyclerViewAdapter = new ModeRecyclerViewAdapter(SingleSureActivity.this,list_sureguanBean);


        recyclerView.setAdapter(modeRecyclerViewAdapter);
        modeRecyclerViewAdapter.setOnRecyclerViewListener(new ModeRecyclerViewAdapter.OnRecyclerViewListener() {
            @Override
            public void onItemClick(int position) {
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

                        ToastUtil.showToast1(SingleSureActivity.this,"必须选择一个");

                    }else {
                        modeRecyclerViewAdapter.choiceState(position);

                    }

                }else {

                    modeRecyclerViewAdapter.choiceState(position);

                }



            }
        });


        Text_cancle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                popupWindow.dismiss();
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
                Text_More.setText(ii-1+"个方式");
                popupWindow.dismiss();
                List<String> getbunch = getbunch();
                Text_money.setText(getbunch.get(0));
                money.setText(getbunch.get(1));


                if(list_sureguanBean.size()==0){

                    ToastUtil.showToast1(SingleSureActivity.this,"请重新选择比赛");
                }else {
                    for (int i = 0; i <list_sureguanBean.size() ; i++) {
                        if(list_sureguanBean.get(i).isselect){
                            bunch_TextView.setText(list_sureguanBean.get(i).getName());

                        }

                    }

//                    bunch_TextView.setText(list_sureguanBean.get(list_sureguanBean.size()-1).getName());

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
                long nowTime = System.currentTimeMillis();
                if (nowTime - mLastClickTime > TIME_INTERVAL) {
                    suborder();

                    // do something
                    mLastClickTime = nowTime;
                } else {
                    ToastUtil.showToast1(SingleSureActivity.this,"不要重复点击");
                }
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

            jsonObject.put("multiple",multiple+"");
            jsonObject.put("bunch",jsonArray);
            jsonObject.put("game_type","0");
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
                                    Content.order_flag_single=1;
                                    finish();
                                    ToastUtil.showToast1(SingleSureActivity.this,codeBean.getMsg()+"");
                                }else if(codeBean.getCode()==10004){
                                    MainActivity.intentsat.finish();
                                    startActivity(new Intent(SingleSureActivity.this, LoginActivity.class));
                                    SpUtil spUtil = new SpUtil(SingleSureActivity.this,"token");
                                    spUtil.putString("token","");
                                    ToastUtil.showToast(SingleSureActivity.this,codeBean.getMsg()+"");

                                }else {
                                    ToastUtil.showToast(SingleSureActivity.this,codeBean.getMsg()+"");

                                }
                            }catch (Exception e){
                                ToastUtil.showToast1(SingleSureActivity.this,"返回数据有误");

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






    public List<String> getbunch(){
        List<String> list_retun = new ArrayList<>();
        String bunch="";
        String bunchminandmax ="";
        Double pour=Double.valueOf(0);;
        List<Double> list_index = new ArrayList<>();
        for (int i = 0; i < list_subMixBean.size(); i++) {
            list_index.add(Double.valueOf(list_subMixBean.get(i).getList().size()));

        }
        for (int i = 0; i <list_sureguanBean.size() ; i++) {
            if(list_sureguanBean.get(i).isselect){
                pour +=getpour(list_index, Integer.parseInt(list_sureguanBean.get(i).getBunch()));

            }

        }
        Log.e("YZS",pour+"");
        NumberFormat nf = new DecimalFormat("#.##");
        format = nf.format(pour);
        String s = edit_Multiple.getText().toString();
        if(s.equals("")){
            s="1";

        }
        bunch =  format+"注  共"+Integer.parseInt(format)*Integer.parseInt(s)*2+"元";

        Log.e("YZS",bunch+"");

        //最小值
        Double pourmin=Double.valueOf(1);;

        List<Integer> list = new ArrayList<>();
        for (int i = 0; i <list_sureguanBean.size() ; i++) {
            if(list_sureguanBean.get(i).isIsselect()){

                list.add(Integer.parseInt(list_sureguanBean.get(i).getBunch()));
            }else {



            }

        }

        Collections.sort(list_min_end);
        for (int j = 0; j <list.get(0) ; j++) {


            pourmin *=list_min_end.get(j);
        }




        NumberFormat nfmin = new DecimalFormat("#.##");
        String pmin = nfmin.format(pourmin);

        String smin = edit_Multiple.getText().toString();
        if(smin.equals("")){
            smin="1";

        }
        //最大值
        Double maxbunch=0.0;
        for (int i = 0; i <list_sureguanBean.size() ; i++) {
            if(list_sureguanBean.get(i).isselect){
                maxbunch +=getpour(list_max_end, Integer.parseInt(list_sureguanBean.get(i).getBunch()));

            }

        }
        String maxbunch_end = nfmin.format(maxbunch);
        double v = Double.parseDouble(pmin) * Integer.parseInt(smin) * 2;
        double v1 = Double.parseDouble(maxbunch_end) * Integer.parseInt(smin) * 2;
        String format1 = nfmin.format(v);
        String format2 = nfmin.format(v1);
        bunchminandmax =  "理论奖金:"+format1+"元~"+format2+"元";

        Log.e("YZS",bunchminandmax+"");

        theory_bonus = bunchminandmax;






        list_retun.add(bunch);
        list_retun.add(bunchminandmax);

        return list_retun;

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



}
