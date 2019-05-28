package com.example.yuzishun.newdeom.my.custom;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.widget.TextView;

import com.example.yuzishun.newdeom.R;

/**
 * Created by yuzishun on 2019/5/5.
 */

public class MyTableTextView extends android.support.v7.widget.AppCompatTextView {
    Paint paint = new Paint();

           public MyTableTextView(Context context, AttributeSet attrs) {
           super(context, attrs);
           int color = getResources().getColor(R.color.edittext_hintcolor);
           // 为边框设置颜色
            paint.setColor(color);
   }

  @Override
    protected void onDraw(Canvas canvas) {
           super.onDraw(canvas);
        // 画TextView的4个边
            canvas.drawLine(0, 0, this.getWidth() - 1, 0, paint);
           canvas.drawLine(0, 0, 0, this.getHeight() - 1, paint);
            canvas.drawLine(this.getWidth() - 1, 0, this.getWidth() - 1, this.getHeight() - 1, paint);
            canvas.drawLine(0, this.getHeight() - 1, this.getWidth() - 1, this.getHeight() - 1, paint);
         }
}
