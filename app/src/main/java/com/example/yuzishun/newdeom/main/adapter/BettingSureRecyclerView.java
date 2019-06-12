package com.example.yuzishun.newdeom.main.adapter;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.Context;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.DisplayMetrics;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.yuzishun.newdeom.R;
import com.example.yuzishun.newdeom.base.Content;
import com.example.yuzishun.newdeom.base.MyApplication;
import com.example.yuzishun.newdeom.model.FootballBean;
import com.example.yuzishun.newdeom.model.ItemPoint;
import com.example.yuzishun.newdeom.model.SubMixBean;
import com.example.yuzishun.newdeom.model.SubMixListBean;
import com.example.yuzishun.newdeom.my.adapter.BankCradAdapter;

import java.util.ArrayList;
import java.util.List;
/**
 * Created by yuzishun on 2019/5/23.
 */

public class BettingSureRecyclerView extends RecyclerView.Adapter<BettingSureRecyclerView.ViewHolder> {
    private Context context;
    private List<SubMixListBean> list_SubMixListBean;
    private String button_name;
    private List<SubMixBean> list_SubMixbean;

    public BettingSureRecyclerView(Context context, List<SubMixListBean> list_SubMixListBean, List<SubMixBean> list_SubMixbean) {
        this.context = context;
        this.list_SubMixListBean = list_SubMixListBean;
        this.list_SubMixbean  = list_SubMixbean;
    }

    // 采用接口回调的方式实现RecyclerView的ItemClick
    public OnRecyclerViewListener mOnRecyclerViewListener;


    // 接口回调第一步: 定义接口和接口中的方法
    public interface OnRecyclerViewListener {

        void onItemClick(int position);
    }
    // 接口回调第二步: 初始化接口的引用
    public void setOnRecyclerViewListener(OnRecyclerViewListener l) {
        this.mOnRecyclerViewListener = l;
    }

    @Override
    public BettingSureRecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(context).inflate(R.layout.layout_surebetting_list,parent,false));
    }

    @Override
    public void onBindViewHolder(BettingSureRecyclerView.ViewHolder holder, final int position) {


        holder.Text_name.setText(list_SubMixListBean.get(position).getGame_id());



//        for (int i = 0; i <list_SubMixListBean.size() ; i++) {
            button_name = list_SubMixListBean.get(position).getList().toString();
        String substring = button_name.substring(1, list_SubMixListBean.get(position).getList().toString().length() - 1);
//        }
        holder.button_choose.setText(substring);


        holder.cancle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mOnRecyclerViewListener != null) {
                    mOnRecyclerViewListener.onItemClick(position);
                }
            }
        });

        holder.button_choose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                dialog(position);

            }
        });



    }




    @SuppressLint("NewApi")
    private void dialog(int postion) {
        AlertDialog.Builder alterDiaglog = new AlertDialog.Builder(context);
        alterDiaglog.setView(R.layout.layout_dialog_mixed);//加载进去
        final AlertDialog dialog = alterDiaglog.create();

        dialog.show();
        //获取屏幕宽度，9.0的dialog是不会主动居中的，这里做了处理
        DisplayMetrics dm = MyApplication.getContext().getResources().getDisplayMetrics();
        int width = dm.widthPixels;
        //放在show()之后，不然有些属性是没有效果的，比如height和width
        Window dialogWindow = dialog.getWindow();
        WindowManager.LayoutParams p = dialogWindow.getAttributes(); // 获取对话框当前的参数值
        // 设置宽度
        p.width = (int) (width * 0.95); // 宽度设置为屏幕的0.95
        p.gravity = Gravity.CENTER;//设置位置
        //p.alpha = 0.8f;//设置透明度
        dialogWindow.setAttributes(p);

        RecyclerView one_rv = dialog.findViewById(R.id.one_rv);
        RecyclerView two_rv = dialog.findViewById(R.id.two_rv);
        RecyclerView three_rv = dialog.findViewById(R.id.three_rv);
        RecyclerView four_rv = dialog.findViewById(R.id.four_rv);
        TextView name_left = dialog.findViewById(R.id.name_left);
        TextView name_right = dialog.findViewById(R.id.name_right);

        LinearLayout layout_cancel = dialog.findViewById(R.id.layout_cancel);
        layout_cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });
        LinearLayout layout_sure = dialog.findViewById(R.id.layout_sure);
        layout_sure.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();

            }
        });
        one_rv.setLayoutManager(new GridLayoutManager(MyApplication.getContext(),3));
                GridLayoutManager gridLayoutManager = new GridLayoutManager(MyApplication.getContext(),7);
        gridLayoutManager.setSpanSizeLookup(new GridLayoutManager.SpanSizeLookup() {
            @Override
            public int getSpanSize(int position) {
                int zhi = 0;
                if(position==12){
                    zhi = 2;
                }else if(position==17){
                    zhi = 3;

                }else if(position==30){
                    zhi = 2;

                }else {
                    zhi=1;
                }

                return zhi;
            }
        });
        two_rv.setLayoutManager(gridLayoutManager);
                three_rv.setLayoutManager(new GridLayoutManager(MyApplication.getContext(),4));
        four_rv.setLayoutManager(new GridLayoutManager(MyApplication.getContext(),5));

        for (int i = 0; i <Content.list_chooe.size() ; i++) {
            if(list_SubMixbean.get(postion).game_id.equals(Content.list_chooe.get(i).getGame_id())){
                List<ItemPoint> onelist = Content.list_chooe.get(i).getOnelist();
                List<ItemPoint> twolist = Content.list_chooe.get(i).getTwolist();
                List<ItemPoint> threelist = Content.list_chooe.get(i).getThreelist();
                List<ItemPoint> fourlist = Content.list_chooe.get(i).getFourlist();

                one_rv.setAdapter(new QuickAdapter(onelist,0,0,name_left,1));
                two_rv.setAdapter(new QuickAdapter_two(twolist,0));
                three_rv.setAdapter(new QuickAdapter_three(threelist,0));
                four_rv.setAdapter(new QuickAdapter_four(fourlist,0));
                TextView victor = dialog.findViewById(R.id.victor);
                TextView loser = dialog.findViewById(R.id.loser);


                name_right.setText(Content.list_chooe.get(postion).getGuest_team());
                name_left.setText(Content.list_chooe.get(postion).getHome_team());



            }


        }






    }

    @Override
    public int getItemCount() {
        return list_SubMixListBean.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView Text_name;
        Button button_choose;
        ImageView cancle;
        public ViewHolder(View itemView) {
            super(itemView);
            Text_name = itemView.findViewById(R.id.Text_name);
            button_choose = itemView.findViewById(R.id.button_choose);
            cancle = itemView.findViewById(R.id.cancle);
        }
    }
}
