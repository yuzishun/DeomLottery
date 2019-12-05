package com.example.yuzishun.newdeom.main.betting;

import android.app.Dialog;
import android.content.Intent;
import android.graphics.drawable.BitmapDrawable;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.transition.Explode;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.alibaba.fastjson.JSON;
import com.example.yuzishun.newdeom.MainActivity;
import com.example.yuzishun.newdeom.R;
import com.example.yuzishun.newdeom.base.BaseActivity;
import com.example.yuzishun.newdeom.base.Content;
import com.example.yuzishun.newdeom.documentary.custom.AmountView;
import com.example.yuzishun.newdeom.login.activity.LoginActivity;
import com.example.yuzishun.newdeom.main.activity.MixedSureActivity;
import com.example.yuzishun.newdeom.main.adapter.BonusRecyClerViewAdapter;
import com.example.yuzishun.newdeom.main.custom.AmountView_two;
import com.example.yuzishun.newdeom.main.custom.MyRecyclerView;
import com.example.yuzishun.newdeom.main.single.SingleMessage;
import com.example.yuzishun.newdeom.main.single.SingleSureActivity;
import com.example.yuzishun.newdeom.model.BonusBean;
import com.example.yuzishun.newdeom.model.CodeBean;
import com.example.yuzishun.newdeom.net.Url;
import com.example.yuzishun.newdeom.utils.SpUtil;
import com.example.yuzishun.newdeom.utils.ToastUtil;
import com.example.yuzishun.newdeom.utils.UIHelper;
import com.example.yuzishun.newdeom.utils.eventbus.BonusMessage;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicInteger;

import butterknife.BindView;
import butterknife.ButterKnife;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class BonusBettingActivity extends BaseActivity implements View.OnClickListener, TextWatcher {
    @BindView(R.id.title_text)
    TextView title_text;
    @BindView(R.id.image_back)
    LinearLayout image_back;
    @BindView(R.id.Bouns_RecyClerView)
    RecyclerView Bouns_RecyClerView;
    private Dialog mLoadingDialog;
    private UIHelper uiHelper;

    @BindView(R.id.Bonus_Money_text)
    TextView Bonus_Money_text;
    @BindView(R.id.Range_Text)
    TextView Range_Text;
    @BindView(R.id.button_Average)
    Button button_Average;
    @BindView(R.id.button_hot)
    Button button_hot;
    @BindView(R.id.button_cold)
    Button button_cold;
    @BindView(R.id.button_sure)
    Button button_sure;
    @BindView(R.id.btnDecrease)
    Button btnDecrease;
    @BindView(R.id.etAmount)
    EditText etAmount;
    @BindView(R.id.btnIncrease)
    Button btnIncrease;
    private long mLastClickTime = 0;
    private BonusRecyClerViewAdapter adapter;
    private List<BonusBean> list_bonus;
    private List<Double> list_bonus_Range = new ArrayList<>();

    private String BonusMoney,HaploidMoney,bunch;
    private int optimization_flag = 1,amoun,key_down_flag=0,amount_top;

    private Handler mHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            if (1 == msg.what) {


                if(amoun<Integer.parseInt(HaploidMoney)){

                    etAmount.setText(Integer.parseInt(HaploidMoney)+"");
                    String content = etAmount.getText().toString();
                    etAmount.setSelection(content.length());
                }else {
                    if(Content.flag_bonus!=2){


                    }else {
                        if(amoun%2==0){
                            Optimization(2);

                        }else {
                            etAmount.setText(amoun-1+"");
                            String content = etAmount.getText().toString();
                            etAmount.setSelection(content.length());
                            Optimization(2);

                        }
                    }

                }
            }

        }
    };
    @Override
    public int intiLayout() {
        return R.layout.activity_bonus_betting;
    }

    @Override
    public void initView() {
        ButterKnife.bind(this);
        title_text.setText("奖金优化");
        image_back.setOnClickListener(this);
        Intent intent  = getIntent();
        EventBus.getDefault().register(this);
        BonusMoney = intent.getStringExtra("BonusMoney");
        HaploidMoney = intent.getStringExtra("HaploidMoney");
        bunch = intent.getStringExtra("bunch");
        etAmount.setText(BonusMoney);
        amount_top=Integer.parseInt(BonusMoney);
        button_sure.setOnClickListener(this);
        btnDecrease.setOnClickListener(this);
        btnIncrease.setOnClickListener(this);
        etAmount.addTextChangedListener(this);



        Bouns_RecyClerView.setLayoutManager(new LinearLayoutManager(this));
//        LinearLayoutManager layoutManager = new LinearLayoutManager(this){
//            @Override
//            public boolean canScrollVertically() {
//                return false;
//            }
//        };

//        Bouns_RecyClerView.setLayoutManager(layoutManager);

//        Bouns_RecyClerView.setNestedScrollingEnabled(false);
//        Bouns_RecyClerView.setHasFixedSize(true);
        list_bonus = new ArrayList<>();

        list_bonus = Content.lastPriceDescList;


        Optimization(1);

        button_Average.setOnClickListener(this);
        button_hot.setOnClickListener(this);
        button_cold.setOnClickListener(this);

    }



    /**
     * 主线程中执行EventBus
     *
     * @param msg
     */
    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onMainEventBus(BonusMessage msg) {
        Log.e("",msg.getPostion()+msg.getAmount()+"");
        if(Bouns_RecyClerView.getScrollState()!=0){

        }else {
            int amount = msg.getAmount();
            int position = msg.getPostion();
            list_bonus.get(position).setNumber(amount);
            int mo=0;
            for (int i = 0; i < list_bonus.size(); i++) {
                mo += list_bonus.get(i).number;

            }
            etAmount.setText(mo*2+"");

            Bonus_Money_text.setText(Integer.parseInt(etAmount.getText().toString())/2+"注 共"+etAmount.getText().toString()+"元");
            Calculation();

            adapter.notifyItemChanged(position);



        }


//        adapter = new BonusRecyClerViewAdapter(BonusBettingActivity.this,list_bonus);
//        Bouns_RecyClerView.setAdapter(adapter);





    }



    private Runnable mRunnable = new Runnable() {
        @Override
        public void run() {

            mHandler.sendEmptyMessage(1);
        }
    };



    @Override
    public void initData() {

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
        if(uiHelper!=null){
            UIHelper.hideDialogForLoaging();

        }
    }

    //这个是单独计算奖金范围的
    public String getRange(){
        String range="奖金范围：";
        double max = 0;
        double min = list_bonus_Range.get(0);
        for (int i = 0; i < list_bonus_Range.size(); i++) {
                max += list_bonus_Range.get(i);
            if(list_bonus_Range.get(i)<min){
                min = list_bonus_Range.get(i);
            }

        }

        range = "奖金范围："+min+"~"+max;

        return range;
    }
    public void Calculation(){
        list_bonus_Range.clear();

        for (int j = 0; j <list_bonus.size() ; j++) {

//            Log.e("YZS",list_bonus.get(j).getOneBetBounsMoney()+"");
//            Log.e("YZS",list_bonus.get(j).getSingleAdds_id());
//            Log.e("YZS",list_bonus.get(j).getSingleMatch());
//            Log.e("YZS",list_bonus.get(j).getSingleGame_id());
//            Log.e("YZS",list_bonus.get(j).getStringName());

            list_bonus_Range.add(list_bonus.get(j).getOneBetBounsMoney()*list_bonus.get(j).getNumber());

        }
        Log.e("YZS",list_bonus_Range.size()+"");
        Range_Text.setText(getRange());


    }

    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.image_back:
                finish();
                break;

            case R.id.button_Average:

                if(etAmount.getText().toString().equals("")){

                    ToastUtil.showToast1(this,"当前不可点击");
                }else {
                    optimization_flag=1;

                    Optimization(1);
                    changestyle(button_Average,button_hot,button_cold);

                }
                break;
            case R.id.button_hot:

                if(etAmount.getText().toString().equals("")){

                    ToastUtil.showToast1(this,"当前不可点击");
                }else {
                    optimization_flag=2;

                    Optimization(1);
                    changestyle(button_hot,button_Average,button_cold);

                }



                break;
            case R.id.button_cold:

                if(etAmount.getText().toString().equals("")){

                    ToastUtil.showToast1(this,"当前不可点击");
                }else {
                    optimization_flag=3;

                    Optimization(1);
                    changestyle(button_cold,button_hot,button_Average);

                }


                break;
            case R.id.button_sure:

                dialog();

                break;
            case R.id.btnDecrease:
                if (amount_top > Integer.parseInt(HaploidMoney)) {
                    amount_top--;
                    amount_top--;
                    etAmount.setText(amount_top + "");
                    String content = etAmount.getText().toString();
                    etAmount.setSelection(content.length());
                    Optimization(2);

                }
                if( Content.flag_bonus==1){
                    Content.flag_bonus=0;

                }else {
                    Content.flag_bonus=2;

                };
                break;
            case R.id.btnIncrease:
                if (amount_top < 999999) {
                    amount_top++;
                    amount_top++;
                    etAmount.setText(amount_top + "");
                    String content = etAmount.getText().toString();
                    etAmount.setSelection(content.length());
                    Optimization(2);
                }
                if( Content.flag_bonus==1){
                    Content.flag_bonus=0;

                }else {
                    Content.flag_bonus=2;

                };
                break;

        }
    }
    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
    public void changestyle(Button button1, Button button2, Button button3){

        button1.setBackground(getResources().getDrawable(R.drawable.main_victory_shape_red));
        button2.setBackground(getResources().getDrawable(R.drawable.main_victory_shape));
        button3.setBackground(getResources().getDrawable(R.drawable.main_victory_shape));
        button1.setTextColor(getResources().getColor(R.color.white));
        button2.setTextColor(getResources().getColor(R.color.login_red));
        button3.setTextColor(getResources().getColor(R.color.login_red));

    }

    public void HotAndCold(int flag){
        //flag==1的时候是博热优化，flag==2的时候是博冷优化








    }

    public void Optimization(int flag){

        mLoadingDialog = new Dialog(BonusBettingActivity.this, R.style.loading_dialog_style);
        uiHelper = new UIHelper(mLoadingDialog);
        uiHelper.showDialogForLoading(BonusBettingActivity.this,"正在计算中……");
        Bonus_Money_text.setText(Integer.parseInt(etAmount.getText().toString())/2+"注 共"+etAmount.getText().toString()+"元");
        for (int i = 0; i < list_bonus.size(); i++) {
            list_bonus.get(i).setNumber(1);

        }

        switch (optimization_flag){
            case 1:

                if(Integer.parseInt(etAmount.getText().toString())/2==list_bonus.size()){
                    list_bonus = getLastPriceDescList(list_bonus);
                    if(uiHelper!=null){
                        UIHelper.hideDialogForLoaging();
                        Calculation();

                    }
                }else {
                    if(flag==1){
                        AsyncTask asyncTask = new AsyncTask() {
                            @Override
                            protected Object doInBackground(Object[] objects) {
                                int size = Integer.parseInt(etAmount.getText().toString()) / 2 - list_bonus.size();
                                for (int i = 0; i < size; i++) {
                                    list_bonus = getLastPriceDescList(list_bonus);
                                    list_bonus.get(0).setNumber(list_bonus.get(0).getNumber()+1);

                                }
                                list_bonus = getLastPriceDescList_zhu(list_bonus);
                                return null;
                            }

                            @Override
                            protected void onPostExecute(Object o) {
                                super.onPostExecute(o);
                                if(uiHelper!=null){
                                    adapter.notifyDataSetChanged();
                                    UIHelper.hideDialogForLoaging();
                                    Calculation();

                                }
                            }
                        };

                        asyncTask.execute();
                    }else {
                        int size = Integer.parseInt(etAmount.getText().toString()) / 2 - list_bonus.size();
                        for (int i = 0; i < size; i++) {
                            list_bonus = getLastPriceDescList(list_bonus);
                            list_bonus.get(0).setNumber(list_bonus.get(0).getNumber()+1);

                        }
                        list_bonus = getLastPriceDescList_zhu(list_bonus);
                        if(uiHelper!=null){
                            UIHelper.hideDialogForLoaging();
                            Calculation();

                        }
                    }



                }

                    adapter = new BonusRecyClerViewAdapter(this,list_bonus);
                    Bouns_RecyClerView.setAdapter(adapter);




                break;
            case 2:




                //博热优化
                if(Integer.parseInt(etAmount.getText().toString())/2==list_bonus.size()){
                    list_bonus = getLastPriceDescList(list_bonus);

                    if(uiHelper!=null){
                        UIHelper.hideDialogForLoaging();
                        Calculation();

                    }
                }else {
                    if(flag==1){
                        AsyncTask asyncTask = new AsyncTask() {
                            @Override
                            protected Object doInBackground(Object[] objects) {
                                list_bonus = getLastPriceDescList(list_bonus);
                                int size = Integer.parseInt(etAmount.getText().toString())/2-list_bonus.size();
                                int amountView_int = Integer.parseInt(etAmount.getText().toString());
                                int flag_hot = 0;
                                for (int i = 0; i < size; i++) {
                                    list_bonus = getLastPriceDescList(list_bonus);

                                    double v = list_bonus.get(0).getOneBetBounsMoney() * list_bonus.get(0).getNumber();
                                    if (amountView_int > v) {

                                        list_bonus.get(0).setNumber(list_bonus.get(0).getNumber() + 1);
                                    } else {
                                        flag_hot++;


                                    }


                                }

                                list_bonus = getLastPriceDescList_dan(list_bonus);

                                list_bonus.get(0).setNumber(list_bonus.get(0).getNumber() + flag_hot);
                                list_bonus = getLastPriceDescList_zhu(list_bonus);
                                return null;
                            }

                            @Override
                            protected void onPostExecute(Object o) {
                                super.onPostExecute(o);
                                if(uiHelper!=null){
                                    UIHelper.hideDialogForLoaging();
                                    adapter.notifyDataSetChanged();
                                    Calculation();

                                }
                            }
                        };


                        asyncTask.execute();
                    }else {
                        list_bonus = getLastPriceDescList(list_bonus);
                        int size = Integer.parseInt(etAmount.getText().toString())/2-list_bonus.size();
                        int amountView_int = Integer.parseInt(etAmount.getText().toString());
                        int flag_hot = 0;

                        for (int i = 0; i < size; i++) {
                            list_bonus = getLastPriceDescList(list_bonus);

                            double v = list_bonus.get(0).getOneBetBounsMoney() * list_bonus.get(0).getNumber();
                            if (amountView_int > v) {

                                list_bonus.get(0).setNumber(list_bonus.get(0).getNumber() + 1);
                            } else {
                                flag_hot++;


                            }


                        }

                        list_bonus = getLastPriceDescList_dan(list_bonus);

                        list_bonus.get(0).setNumber(list_bonus.get(0).getNumber() + flag_hot);
                        list_bonus = getLastPriceDescList_zhu(list_bonus);

                        if(uiHelper!=null){
                            UIHelper.hideDialogForLoaging();
                            Calculation();

                        }
                    }




                }


                    adapter = new BonusRecyClerViewAdapter(this,list_bonus);
                    Bouns_RecyClerView.setAdapter(adapter);





                break;
            case 3:

                //博冷优化

                if(Integer.parseInt(etAmount.getText().toString())/2==list_bonus.size()){
                    list_bonus = getLastPriceDescList(list_bonus);

                    if(uiHelper!=null){
                        UIHelper.hideDialogForLoaging();
                        Calculation();

                    }
                }else {
                    if(flag==1){
                        AsyncTask asyncTask = new AsyncTask() {
                            @Override
                            protected Object doInBackground(Object[] objects) {
                                list_bonus = getLastPriceDescList(list_bonus);
                                int size = Integer.parseInt(etAmount.getText().toString())/2-list_bonus.size();
                                int amountView_int = Integer.parseInt(etAmount.getText().toString());
                                int flag_cold = 0;

                                for (int i = 0; i < size; i++) {


                                    list_bonus = getLastPriceDescList(list_bonus);

                                    double v = list_bonus.get(0).getOneBetBounsMoney() * list_bonus.get(0).getNumber();
                                    if(amountView_int>v){

                                        list_bonus.get(0).setNumber(list_bonus.get(0).getNumber()+1);

                                    }else {
                                        flag_cold++;


                                    }





                                }

                                list_bonus = getLastPriceDescList_dan(list_bonus);

                                list_bonus.get(list_bonus.size()-1).setNumber(list_bonus.get(list_bonus.size()-1).getNumber()+flag_cold);
                                list_bonus = getLastPriceDescList_zhu(list_bonus);
                                return null;
                            }

                            @Override
                            protected void onPostExecute(Object o) {
                                super.onPostExecute(o);
                                if(uiHelper!=null){
                                    UIHelper.hideDialogForLoaging();
                                    adapter.notifyDataSetChanged();
                                    Calculation();

                                }
                            }
                        };

                        asyncTask.execute();
                    }else {


                        boolean mainThread = isMainThread();
                        Log.e("YZS",mainThread+"这是什么呢");
                        list_bonus = getLastPriceDescList(list_bonus);
                        int size = Integer.parseInt(etAmount.getText().toString())/2-list_bonus.size();
                        int amountView_int = Integer.parseInt(etAmount.getText().toString());
                        int flag_cold = 0;
                        for (int i = 0; i < size; i++) {


                            list_bonus = getLastPriceDescList(list_bonus);

                            double v = list_bonus.get(0).getOneBetBounsMoney() * list_bonus.get(0).getNumber();
                            if(amountView_int>v){

                                list_bonus.get(0).setNumber(list_bonus.get(0).getNumber()+1);

                            }else {
                                flag_cold++;


                            }





                        }
                        list_bonus = getLastPriceDescList_dan(list_bonus);

                        list_bonus.get(list_bonus.size()-1).setNumber(list_bonus.get(list_bonus.size()-1).getNumber()+flag_cold);

                        list_bonus = getLastPriceDescList_zhu(list_bonus);
                        if(uiHelper!=null){
                            UIHelper.hideDialogForLoaging();
                            Calculation();

                        }

                    }





                }

                    adapter = new BonusRecyClerViewAdapter(this,list_bonus);
                    Bouns_RecyClerView.setAdapter(adapter);

                break;
        }




    }



    //排序方式
    public synchronized List<BonusBean> getLastPriceDescList(List<BonusBean> list) {
        Collections.sort(list, new Comparator<BonusBean>() {
            @Override
            public int compare(BonusBean o1, BonusBean o2) {

                return Double.valueOf(o1.getOneBetBounsMoney()*o1.getNumber()).compareTo(Double.valueOf(o2.getOneBetBounsMoney()*o2.getNumber()));
            }
        });
        return list;
    }
    //单倍金额排序方式
    public synchronized List<BonusBean> getLastPriceDescList_dan(List<BonusBean> list) {
        Collections.sort(list, new Comparator<BonusBean>() {
            @Override
            public int compare(BonusBean o1, BonusBean o2) {

                return Double.valueOf(o1.getOneBetBounsMoney()).compareTo(Double.valueOf(o2.getOneBetBounsMoney()));
            }
        });
        return list;
    }
    //排序方式
    public synchronized List<BonusBean> getLastPriceDescList_zhu(List<BonusBean> list) {
        Collections.sort(list, new Comparator<BonusBean>() {
            @Override
            public int compare(BonusBean o1, BonusBean o2) {
                if(o2.getNumber()==o1.getNumber()){
                    return Double.valueOf(o1.getOneBetBounsMoney()*o1.getNumber()).compareTo(Double.valueOf(o2.getOneBetBounsMoney()*o2.getNumber()));

                }
                return o2.getNumber()-o1.getNumber();

            }
        });
        return list;
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


            text_bei.setText("1倍");
            pop_bunch.setText(Integer.parseInt(etAmount.getText().toString())/2+"注");

            pop_money.setText(etAmount.getText().toString()+"元");

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
                if (nowTime - mLastClickTime > 1000) {

                    suborder();

                    // do something
                    mLastClickTime = nowTime;
                } else {
                    ToastUtil.showToast1(BonusBettingActivity.this,"不要重复点击");
                }
            }
        });


    }
    public boolean isMainThread() {
        return Looper.getMainLooper() == Looper.myLooper();
    }

    public void suborder(){

        try {

        JSONObject jsonObject = new JSONObject();

            Log.e("YZS",list_bonus.size()+"");

            String game_bet="";
            for (int i = 0; i <list_bonus.size() ; i++) {

                String substring_game_id = list_bonus.get(i).getSingleGame_id().substring(0, list_bonus.get(i).getSingleGame_id().length() - 1);
                String substring_adds_id = list_bonus.get(i).getSingleAdds_id().substring(0, list_bonus.get(i).getSingleAdds_id().length() - 1);
                game_bet +=substring_game_id+"#"+substring_adds_id+"#"+list_bonus.get(i).getNumber()+";";


            }

            String game_bet_sub = game_bet.substring(0,game_bet.length()-1);


            jsonObject.put("bunch",bunch+"");

            jsonObject.put("game_bet",game_bet_sub+"");
            jsonObject.put("game_type","0");
            jsonObject.put("multiple","1");
            jsonObject.put("theory_bonus",Range_Text.getText().toString()+"");
            jsonObject.put("seo_status",optimization_flag+"");

            String data = jsonObject.toString();



        OkHttpClient client = new OkHttpClient();


        RequestBody body = RequestBody.create(okhttp3.MediaType.parse("application/json;charset=UTF-8"), data);
        final Request request = new Request.Builder()
                .url(Url.baseUrl+"app/order/addSeoOrder")
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
                try {
                    CodeBean codeBean = JSON.parseObject(result,CodeBean.class);
                    if(codeBean.getCode()==10000){
                        Content.order_flag=1;
                        Content.order_flag_single=1;

                        if(MixedSureActivity.intentfinish!=null){
                            MixedSureActivity.intentfinish.finish();

                        }
                        if(SingleSureActivity.intentt!=null){
                            SingleSureActivity.intentt.finish();

                        }
                        ToastUtil.showToast(BonusBettingActivity.this,codeBean.getMsg()+"");
                        finish();
                    }else if(codeBean.getCode()==10004){
                        MainActivity.intentsat.finish();
                        startActivity(new Intent(BonusBettingActivity.this, LoginActivity.class));
                        SpUtil spUtil = new SpUtil(BonusBettingActivity.this,"token");
                        spUtil.putString("token","");
                        ToastUtil.showToast(BonusBettingActivity.this,codeBean.getMsg()+"");

                    }else {
                        ToastUtil.showToast(BonusBettingActivity.this,codeBean.getMsg()+"");

                    }
                }catch (Exception e){
                    ToastUtil.showToast1(BonusBettingActivity.this,"返回数据有误");

                }


            }
        });


        } catch (JSONException e) {
            e.printStackTrace();
        }

    }


    @Override
    public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

    }

    @Override
    public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

    }

    @Override
    public void afterTextChanged(Editable editable) {


        if (editable.toString().isEmpty()) {
            amount_top = 0;
        }else {



        amount_top = Integer.valueOf(editable.toString());

        if (amount_top > 999999) {
            etAmount.setText(999999 + "");
            return;
        }
        if( Content.flag_bonus==1){
            Content.flag_bonus=0;

        }else {
            Content.flag_bonus=2;

        }
        }

        amoun= amount_top;
        mHandler.removeCallbacks(mRunnable);
        //800毫秒没有输入认为输入完毕
        mHandler.postDelayed(mRunnable, 1500);

        Calculation();

    }
}
