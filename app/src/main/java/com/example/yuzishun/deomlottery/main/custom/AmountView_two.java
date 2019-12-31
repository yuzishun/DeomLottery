package com.example.yuzishun.deomlottery.main.custom;

import android.content.Context;
import android.content.res.TypedArray;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;

import com.example.yuzishun.deomlottery.R;
import com.example.yuzishun.deomlottery.base.Content;

/**
 * Created by yuzishun on 2019/5/8.
 */

/***
 * 跟单数量控件自定义
 *
 */
public class AmountView_two extends LinearLayout implements View.OnClickListener, TextWatcher {

    private static final String TAG = "AmountView";
    private int amount = 1; //购买数量
    private int goods_storage = 1; //商品库存
    private int money;
    private OnAmountChangeListener mListener;

    private EditText etAmount;
    private Button btnDecrease;
    private Button btnIncrease;

    public AmountView_two(Context context) {
        this(context, null);
    }

    public AmountView_two(Context context, AttributeSet attrs) {
        super(context, attrs);

        LayoutInflater.from(context).inflate(R.layout.view_amount_two, this);
        etAmount = (EditText) findViewById(R.id.etAmount);
        btnDecrease = (Button) findViewById(R.id.btnDecrease);
        btnIncrease = (Button) findViewById(R.id.btnIncrease);
        btnDecrease.setOnClickListener(this);
        btnIncrease.setOnClickListener(this);
        etAmount.addTextChangedListener(this);

        TypedArray obtainStyledAttributes = getContext().obtainStyledAttributes(attrs, R.styleable.AmountView);
        int btnWidth = obtainStyledAttributes.getDimensionPixelSize(R.styleable.AmountView_btnWidth, LayoutParams.WRAP_CONTENT);
        int tvWidth = obtainStyledAttributes.getDimensionPixelSize(R.styleable.AmountView_tvWidth, 80);
        int tvTextSize = obtainStyledAttributes.getDimensionPixelSize(R.styleable.AmountView_tvTextSize, 0);
        int btnTextSize = obtainStyledAttributes.getDimensionPixelSize(R.styleable.AmountView_btnTextSize, 0);
        obtainStyledAttributes.recycle();

//        LayoutParams btnParams = new LayoutParams(btnWidth, LayoutParams.MATCH_PARENT);
//        btnDecrease.setLayoutParams(btnParams);
//        btnIncrease.setLayoutParams(btnParams);
        if (btnTextSize != 0) {
            btnDecrease.setTextSize(TypedValue.COMPLEX_UNIT_PX, btnTextSize);
            btnIncrease.setTextSize(TypedValue.COMPLEX_UNIT_PX, btnTextSize);
        }

//        LayoutParams textParams = new LayoutParams(tvWidth, LayoutParams.MATCH_PARENT);
//        etAmount.setLayoutParams(textParams);
        if (tvTextSize != 0) {
            etAmount.setTextSize(tvTextSize);
        }
    }

    public void setOnAmountChangeListener(OnAmountChangeListener onAmountChangeListener) {
        this.mListener = onAmountChangeListener;
    }

    public void setGoods_storage(int goods_storage) {
        this.goods_storage = goods_storage;
    }
    public void setMoney(int money) {
        this.money = money;
    }
    public void setTextd(int number){
        etAmount.setText(number+"");
    }
    public String gettext(){

        return etAmount.getText().toString().trim();
    }


    @Override
    public void onClick(View v) {
        int i = v.getId();
        if (i == R.id.btnDecrease) {
            if (amount > money) {
                amount--;
                amount--;
                etAmount.setText(amount + "");
                setsleect();
            }
        } else if (i == R.id.btnIncrease) {
            if (amount < goods_storage) {
                amount++;
                amount++;
                etAmount.setText(amount + "");
                setsleect();

            }
        }

        etAmount.clearFocus();

        if (mListener != null) {
            if( Content.flag_bonus==1){
                Content.flag_bonus=0;

            }else {
                Content.flag_bonus=2;

            };

//            mListener.onAmountChange(this, amount,1);
        }
    }



    @Override
    public void beforeTextChanged(CharSequence s, int start, int count, int after) {

    }


    @Override
    public void onTextChanged(CharSequence s, int start, int before, int count) {

    }
    public void setsleect(){
        String content = etAmount.getText().toString();
        etAmount.setSelection(content.length());
    }

    @Override
    public void afterTextChanged(Editable s) {
        if (s.toString().isEmpty()) {
            setsleect();
            return;
        }
        amount = Integer.valueOf(s.toString());
        if (amount > goods_storage) {
            etAmount.setText(goods_storage + "");
            return;
        }

        if (mListener != null) {
            if( Content.flag_bonus==1){
                Content.flag_bonus=0;

            }else {
                Content.flag_bonus=2;

            }
//            mListener.onAmountChange(this, amount,0);
        }
    }

    


    public interface OnAmountChangeListener {
        void onAmountChange(View view, int amount,int flag);
    }

}
