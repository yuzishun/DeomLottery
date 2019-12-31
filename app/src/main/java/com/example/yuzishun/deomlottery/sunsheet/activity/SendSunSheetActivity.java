package com.example.yuzishun.deomlottery.sunsheet.activity;

import android.content.Intent;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.yuzishun.deomlottery.R;
import com.example.yuzishun.deomlottery.base.BaseActivity;
import com.example.yuzishun.deomlottery.login.custom.ClearEditText;
import com.example.yuzishun.deomlottery.net.OkhttpUtlis;
import com.example.yuzishun.deomlottery.net.Url;
import com.example.yuzishun.deomlottery.utils.ToastUtil;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.HashMap;

import butterknife.BindView;
import butterknife.ButterKnife;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

public class SendSunSheetActivity extends BaseActivity implements View.OnClickListener {

    @BindView(R.id.image_back)
    LinearLayout image_back;
    @BindView(R.id.title_text)
    TextView title_text;
    @BindView(R.id.sunsheet_edit)
    ClearEditText sunsheet_edit;
    @BindView(R.id.Sendsunsheet)
    Button Sendsunsheet;
    private int Order_id;

    @Override
    public int intiLayout() {
        return R.layout.activity_send_sun_sheet;
    }

    @Override
    public void initView() {
        ButterKnife.bind(this);
        image_back.setOnClickListener(this);
        title_text.setText("发起晒单");
        Sendsunsheet.setOnClickListener(this);
        Intent intent = getIntent();
        Order_id = intent.getIntExtra("order_id",0);

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
            case R.id.Sendsunsheet:
                if(sunsheet_edit.getText().toString().trim().equals("")){

                    ToastUtil.showToast1(this,"文案宣言必须要填");
                }else {
                    request();

                }

                break;

        }
    }

    private void request() {
        OkhttpUtlis okhttpUtlis = new OkhttpUtlis();
        HashMap<String,String> hashMap = new HashMap<>();
        hashMap.put("manifesto",sunsheet_edit.getText().toString().trim());
        hashMap.put("order_id",Order_id+"");
        okhttpUtlis.PostAsynMap(Url.baseUrl + "app/Bask/launch", hashMap, new Callback() {
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

                                ToastUtil.showToast1(SendSunSheetActivity.this,msg+"");
                                finish();
                            }else {
                                ToastUtil.showToast1(SendSunSheetActivity.this,msg+"");

                            }


                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                    }
                });



            }
        });




    }
}
