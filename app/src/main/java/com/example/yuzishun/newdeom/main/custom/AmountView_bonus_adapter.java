package com.example.yuzishun.newdeom.main.custom;

import android.content.Context;
import android.content.res.TypedArray;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.InputType;
import android.text.TextWatcher;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;

import com.example.yuzishun.newdeom.R;
import com.example.yuzishun.newdeom.base.Content;
import com.example.yuzishun.newdeom.base.MyApplication;

/**
 * Created by yuzishun on 2019/5/8.
 */

/***
 *
 *
 */
public class AmountView_bonus_adapter extends LinearLayout implements View.OnClickListener, TextWatcher {

    private static final String TAG = "AmountView";
    private int amount = 1; //购买数量
    private int goods_storage = 1; //商品库存
    private int money;
    private OnAmountChangeListener_t mListener;

    private EditText etAmount;
    private Button btnDecrease;
    private Button btnIncrease;

    public AmountView_bonus_adapter(Context context) {
        this(context, null);
    }

    public AmountView_bonus_adapter(Context context, AttributeSet attrs) {
        super(context, attrs);

        LayoutInflater.from(context).inflate(R.layout.view_amount_two_adapter, this);
        etAmount = (EditText) findViewById(R.id.etAmount_adapter);
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
        etAmount.setInputType(InputType.TYPE_NULL);
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

    public void setOnAmountChangeListener_t(OnAmountChangeListener_t onAmountChangeListener) {
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

    @Override
    public void onClick(View v) {
        int i = v.getId();
        if (i == R.id.btnDecrease) {
            if (amount > 1) {
                amount--;
                etAmount.setText(amount + "");
            }
        } else if (i == R.id.btnIncrease) {
            if (amount < goods_storage) {
                amount++;
                etAmount.setText(amount + "");

            }
        }

        etAmount.clearFocus();

        if (mListener != null) {
            Content.flag_bonus=1;

            mListener.onAmountChange(this, amount);
        }
    }


    @Override
    public void beforeTextChanged(CharSequence s, int start, int count, int after) {

    }

    @Override
    public void onTextChanged(CharSequence s, int start, int before, int count) {

    }

    @Override
    public void afterTextChanged(Editable s) {
        if (s.toString().isEmpty()) {
            return;
        }
        amount = Integer.valueOf(s.toString());
        if (amount > goods_storage) {
            etAmount.setText(goods_storage + "");
            return;
        }

        if (mListener != null) {
            Content.flag_bonus=1;


//             mListener.onAmountChange(this, amount);



        }
    }


    public interface OnAmountChangeListener_t {
        void onAmountChange(View view, int amount);
    }

}
