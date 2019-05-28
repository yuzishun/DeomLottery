package com.example.yuzishun.newdeom.main.activity;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.BitmapDrawable;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v4.widget.NestedScrollView;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.Display;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.TextView;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.example.yuzishun.newdeom.MainActivity;
import com.example.yuzishun.newdeom.R;
import com.example.yuzishun.newdeom.base.BaseActivity;
import com.example.yuzishun.newdeom.base.Content;
import com.example.yuzishun.newdeom.base.MyApplication;
import com.example.yuzishun.newdeom.login.activity.LoginActivity;
import com.example.yuzishun.newdeom.main.adapter.BettingSureRecyclerView;
import com.example.yuzishun.newdeom.main.adapter.ModeRecyclerViewAdapter;
import com.example.yuzishun.newdeom.main.adapter.baskball.BasketballSureActivity;
import com.example.yuzishun.newdeom.model.ChooseMixedBean;
import com.example.yuzishun.newdeom.model.CodeBean;
import com.example.yuzishun.newdeom.model.FootballBean;
import com.example.yuzishun.newdeom.model.ItemPoint;
import com.example.yuzishun.newdeom.model.SubMixBean;
import com.example.yuzishun.newdeom.model.SubMixListBean;
import com.example.yuzishun.newdeom.model.SureguanBean;
import com.example.yuzishun.newdeom.my.adapter.BankCradAdapter;
import com.example.yuzishun.newdeom.net.OkhttpUtlis;
import com.example.yuzishun.newdeom.net.Url;
import com.example.yuzishun.newdeom.utils.SpUtil;
import com.example.yuzishun.newdeom.utils.ToastUtil;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.lang.reflect.Array;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class MixedSureActivity extends BaseActivity implements View.OnClickListener {
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
    private BettingSureRecyclerView bettingSureRecyclerView;
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
    //参数集合和需要展示的集合
    private  List<String> list_adds;
    private  List<String> list_id;

    private String[] string_mode=new String[]{"2串1","3串1","4串1","5串1","6串1","7串1","8串1"};
    private List<SubMixBean> list_subMixBean = new ArrayList<>();
    private List<SureguanBean> list_sureguanBean = new ArrayList<>();
    private List<SubMixListBean> list_stbMixListBean = new ArrayList<>();
    private String multiple="1";
    @Override
    public int intiLayout() {
        return R.layout.activity_mixed_sure;
    }

    @Override
    public void initView() {
        ButterKnife.bind(this);
        image_back.setOnClickListener(this);
        title_text.setText("混和投注确定");
        button_sure.setOnClickListener(this);
        Text_More.setOnClickListener(this);
        Button_goon.setOnClickListener(this);
        final List<ChooseMixedBean> list_chooe = Content.list_chooe;
        for (int i = 0; i <list_chooe.size(); i++) {
            list_adds = new ArrayList<>();
            list_id = new ArrayList<>();
            List<ItemPoint> onelist = list_chooe.get(i).getOnelist();
            List<ItemPoint> twolist = list_chooe.get(i).getTwolist();
            List<ItemPoint> threelist = list_chooe.get(i).getThreelist();
            List<ItemPoint> fourlist = list_chooe.get(i).getFourlist();
            for (int j = 0; j <onelist.size() ; j++) {
                if(onelist.get(j).isselect){
                    list_adds.add(onelist.get(j).getGame_odds_id());
                    switch (j){
                        case 0:
                            list_id.add("胜平负:"+onelist.get(j).getId());
                            break;
                        case 1:
                            list_id.add("胜平负:"+onelist.get(j).getId());

                            break;
                        case 2:
                            list_id.add("胜平负:"+onelist.get(j).getId());

                            break;
                        case 3:
                            list_id.add("让球胜平负:"+onelist.get(j).getId());

                            break;
                        case 4:
                            list_id.add("让球胜平负:"+onelist.get(j).getId());

                            break;
                        case 5:
                            list_id.add("让球胜平负:"+onelist.get(j).getId());

                            break;

                    }


//                    list_id.add(onelist.get(j).getId());
//                    list_chooe_adapter.get(i).setOnelist(onelist);


                }else {

                }

            }
            for (int j = 0; j <twolist.size() ; j++) {
                if(twolist.get(j).isselect){
                    list_adds.add(twolist.get(j).getGame_odds_id());
                    list_id.add("比分:"+twolist.get(j).getId());

//                    list_chooe_adapter.get(i).setTwolist(twolist);
                }else {

                }

            }
            for (int j = 0; j <threelist.size() ; j++) {
                if(threelist.get(j).isselect){
                    list_adds.add(threelist.get(j).getGame_odds_id());
                    list_id.add("总决赛:"+threelist.get(j).getId());
//                    list_chooe_adapter.get(i).setThreelist(threelist);

                }else {

                }

            }
            for (int j = 0; j <fourlist.size() ; j++) {
                if(fourlist.get(j).isselect){
                    list_adds.add(fourlist.get(j).getGame_odds_id());
                    list_id.add("半全场:"+fourlist.get(j).getId());
//                    list_chooe_adapter.get(i).setFourlist(fourlist);

                }else {

                }

            }
            if(list_adds.size()==0){


            }else {
                SubMixBean subMixBean = new SubMixBean();
                subMixBean.setGame_id(list_chooe.get(i).getGame_id());
                subMixBean.setList(list_adds);


                list_subMixBean.add(subMixBean);

                SubMixListBean subMixListBean = new SubMixListBean();
                subMixListBean.setGame_id(list_chooe.get(i).getName());

                subMixListBean.setList(list_id);

                list_stbMixListBean.add(subMixListBean);

            }




        }
        Log.e("YZS",list_subMixBean.toString());
        Log.e("YZS",list_stbMixListBean.toString());


        Sure_RecyclerView.setLayoutManager(new LinearLayoutManager(this));
        Sure_RecyclerView.setNestedScrollingEnabled(false);

        bettingSureRecyclerView = new BettingSureRecyclerView(this,list_stbMixListBean,list_subMixBean);
        Sure_RecyclerView.setAdapter(bettingSureRecyclerView);
        bettingSureRecyclerView.setOnRecyclerViewListener(new BettingSureRecyclerView.OnRecyclerViewListener() {
            @Override
            public void onItemClick(int position) {

                if(list_sureguanBean.size()>=2){
                    for (int i = 0; i <list_chooe.size() ; i++) {
                        List<ItemPoint> onelist = list_chooe.get(i).getOnelist();
                        List<ItemPoint> twolist = list_chooe.get(i).getTwolist();
                        List<ItemPoint> threelist = list_chooe.get(i).getThreelist();
                        List<ItemPoint> fourlist = list_chooe.get(i).getFourlist();
                        if(list_chooe.get(i).getGame_id()==list_subMixBean.get(position).game_id){
                            for (int j = 0; j <onelist.size() ; j++) {
                                onelist.get(j).setIsselect(false);
                            }
                            for (int j = 0; j <twolist.size() ; j++) {
                                twolist.get(j).setIsselect(false);
                            }
                            for (int j = 0; j <threelist.size() ; j++) {
                                threelist.get(j).setIsselect(false);
                            }
                            for (int j = 0; j <fourlist.size() ; j++) {
                                fourlist.get(j).setIsselect(false);
                            }

                        }else {

                        }




                    }
                    list_stbMixListBean.remove(position);
                    list_subMixBean.remove(position);
                    bettingSureRecyclerView.notifyDataSetChanged();
                    listSureguanBean();

                    String getbunch = getbunch();
                    Text_money.setText(getbunch);
                }else {

                    ToastUtil.showToast1(MixedSureActivity.this,"不可以删除");

                }

            }
        });

        listSureguanBean();
        String getbunch = getbunch();
        Text_money.setText(getbunch);
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


                String getbunch = getbunch();
                Text_money.setText(getbunch);


            }
        });

    }



    public String getbunch(){
        String bunch="";
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
        NumberFormat nf = new DecimalFormat("#.#");
        format = nf.format(pour);
        String s = edit_Multiple.getText().toString();
        if(s.equals("")){
            s="1";

        }
        bunch =  format+"注  共"+Integer.parseInt(format)*Integer.parseInt(s)*2+"元";

        Log.e("YZS",bunch+"");

        return bunch;

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








    @Override
    public void initData() {



    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.image_back:
                Content.order_flag=0;
                finish();
                break;
            case R.id.button_sure:
                dialog();
                break;
            case R.id.Text_More:
                popwindnow();

                break;
            case R.id.Button_goon:
                Content.order_flag=0;

                finish();
                break;
        }
    }

    private void listSureguanBean(){
        int length=0;

        switch (list_stbMixListBean.size()){
            case 2:
                length = 1;
                break;
            case 3:
                length = 2;

                break;
            case 4:
                length = 3;

                break;
            case 5:
                length = 4;

                break;
            case 6:
                length = 5;

                break;
            case 7:
                length = 6;

                break;
            case 8:
                length = 7;

                break;
        }

        if(list_stbMixListBean.size()>8){

            length=7;

        }



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


    private void popwindnow() {
        //加载弹出框的布局
        contentView = LayoutInflater.from(MixedSureActivity.this).inflate(
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
        final ModeRecyclerViewAdapter modeRecyclerViewAdapter = new ModeRecyclerViewAdapter(MixedSureActivity.this,list_sureguanBean,length);
        recyclerView.setAdapter(modeRecyclerViewAdapter);
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
                String getbunch = getbunch();
                Text_money.setText(getbunch);
                if(list_sureguanBean.size()==0){

                    ToastUtil.showToast1(MixedSureActivity.this,"请重新选择比赛");
                }else {
                    bunch_TextView.setText(list_sureguanBean.get(list_sureguanBean.size()-1).getName());

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

            jsonObject.put("multiple",multiple+"");
            jsonObject.put("bunch",jsonArray);
            jsonObject.put("game_type","0");
            jsonObject.put("game_bet",jsonArray1);
            String data = jsonObject.toString();
            Log.e("YZS",data.toString());

        OkHttpClient client = new OkHttpClient();


        RequestBody body = RequestBody.create(okhttp3.MediaType.parse("application/json;charset=UTF-8"), data);
        final Request request = new Request.Builder()
                .url(Url.baseUrl+"order/addOrder")
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
                            Content.order_flag=1;
                            finish();
                            ToastUtil.showToast1(MixedSureActivity.this,codeBean.getMsg()+"");
                        }else if(codeBean.getCode()==10004){
                            MainActivity.intentsat.finish();
                            startActivity(new Intent(MixedSureActivity.this, LoginActivity.class));
                            SpUtil spUtil = new SpUtil(MixedSureActivity.this,"token");
                            spUtil.putString("token","");
                            ToastUtil.showToast(MixedSureActivity.this,codeBean.getMsg()+"");

                        }else {
                            ToastUtil.showToast(MixedSureActivity.this,codeBean.getMsg()+"");

                        }
                        }catch (Exception e){
                            ToastUtil.showToast1(MixedSureActivity.this,"返回数据有误");

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


}
