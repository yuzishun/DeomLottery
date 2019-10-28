package com.example.yuzishun.newdeom.sunsheet.activity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Color;
import android.support.design.widget.BottomSheetBehavior;
import android.support.design.widget.BottomSheetDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ExpandableListView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.alibaba.fastjson.JSON;
import com.bumptech.glide.Glide;
import com.example.yuzishun.newdeom.R;
import com.example.yuzishun.newdeom.base.BaseActivity;
import com.example.yuzishun.newdeom.base.Content;
import com.example.yuzishun.newdeom.documentary.activity.OkamiActivity;
import com.example.yuzishun.newdeom.model.CommentsListBean;
import com.example.yuzishun.newdeom.model.SunSheetDetailsBean;
import com.example.yuzishun.newdeom.net.OkhttpUtlis;
import com.example.yuzishun.newdeom.net.Url;
import com.example.yuzishun.newdeom.sunsheet.CommentDetailBean;
import com.example.yuzishun.newdeom.sunsheet.ReplyDetailBean;
import com.example.yuzishun.newdeom.sunsheet.adapter.CommentExpandAdapter;
import com.example.yuzishun.newdeom.utils.CommentExpandableListView;
import com.example.yuzishun.newdeom.utils.CustomClickListener;
import com.example.yuzishun.newdeom.utils.ToastUtil;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.regex.PatternSyntaxException;

import butterknife.BindView;
import butterknife.ButterKnife;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

public class SunSheetDetailsActivity extends BaseActivity implements View.OnClickListener {
    @BindView(R.id.image_back)
    LinearLayout image_back;
    @BindView(R.id.title_text)
    TextView title_text;
    @BindView(R.id.fang_details)
    LinearLayout fang_details;
    @BindView(R.id.usericon)
    ImageView usericon;
    @BindView(R.id.username)
    TextView username;
    @BindView(R.id.usedr_id)
    TextView usedr_id;
    @BindView(R.id.text_dec)
    TextView text_dec;
    @BindView(R.id.list_2_1)
    TextView list_2_1;
    @BindView(R.id.list_2_2)
    TextView list_2_2;
    @BindView(R.id.list_2_3)
    TextView list_2_3;
    @BindView(R.id.list_2_4)
    TextView list_2_4;
    @BindView(R.id.follow_button)
    Button follow_button;
    @BindView(R.id.commet_count)
    TextView commet_count;
    @BindView(R.id.lisk_count)
    TextView lisk_count;
    @BindView(R.id.layout_like)
    LinearLayout layout_like;
    @BindView(R.id.dianzan)
    ImageView dianzan;
    @BindView(R.id.layout_ping)
    LinearLayout layout_ping;
    @BindView(R.id.Comment_text_null)
    TextView Comment_text_null;
    @BindView(R.id.detail_page_lv_comment)
    CommentExpandableListView detail_page_lv_comment;
    private CommentExpandAdapter adapter;
    private BottomSheetDialog dialog;
    private List<CommentsListBean.DataBean> commentsList;
    private String user_id,order_id;
    private String bask_id;
    @Override
    public int intiLayout() {
        return R.layout.activity_sun_sheet_details;
    }

    @Override
    public void initView() {
        ButterKnife.bind(this);
        image_back.setOnClickListener(this);
        fang_details.setOnClickListener(this);
        layout_like.setOnClickListener(this);
        layout_ping.setOnClickListener(this);
        title_text.setText("晒单详情");
        bask_id = Content.back_id;
        request();
        follow_button.setOnClickListener(new CustomClickListener() {
            @Override
            protected void onSingleClick() {
                if(follow_button.getText().equals("取消关注")){

                    Cancelfollow();
                }else {
                    follow();

                }
            }

            @Override
            protected void onFastClick() {

            }
        });

        usericon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intentokmin = new Intent(SunSheetDetailsActivity.this, OkamiActivity.class);
                intentokmin.putExtra("user_id",user_id);

                startActivity(intentokmin);

            }
        });
        Loadcomments();
    }

    private void Loadcomments() {
        OkhttpUtlis okhttpUtlis = new OkhttpUtlis();
        HashMap<String,String> hashMap= new HashMap<>();
        hashMap.put("bask_id",bask_id+"");
        okhttpUtlis.PostAsynMap(Url.baseUrl + "app/Comment/queryCommentList", hashMap, new Callback() {
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
                            String msg = jsonObject.getString("msg");
                            int code = jsonObject.getInt("code");
                            if(code==10000){

                                CommentsListBean commentsListBean = JSON.parseObject(result,CommentsListBean.class);
                                commentsList = commentsListBean.getData();
                                if(commentsListBean.getData().size()==0){
                                    Comment_text_null.setVisibility(View.VISIBLE);
                                    detail_page_lv_comment.setVisibility(View.GONE);

                                }else {
                                    Comment_text_null.setVisibility(View.GONE);
                                    detail_page_lv_comment.setVisibility(View.VISIBLE);
                                }
                                initExpandableListView(commentsList);



                            }else {
                                ToastUtil.showToast1(SunSheetDetailsActivity.this,msg);

                            }

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }


                    }
                });

            }
        });


    }

    /**
     * 初始化评论和回复列表
     */
    private void initExpandableListView(final List<CommentsListBean.DataBean> commentList){
        detail_page_lv_comment.setGroupIndicator(null);
        //默认展开所有回复
        adapter = new CommentExpandAdapter(SunSheetDetailsActivity.this, commentList);
        detail_page_lv_comment.setAdapter(adapter);
        for(int i = 0; i<commentList.size(); i++){
            detail_page_lv_comment.expandGroup(i);
        }
        detail_page_lv_comment.setOnGroupClickListener(new ExpandableListView.OnGroupClickListener() {
            @Override
            public boolean onGroupClick(ExpandableListView expandableListView, View view, int groupPosition, long l) {
                boolean isExpanded = expandableListView.isGroupExpanded(groupPosition);
//                if(isExpanded){
//                    expandableListView.collapseGroup(groupPosition);
//                }else {
//                    expandableListView.expandGroup(groupPosition, true);
//                }
                showReplyDialog(groupPosition,0,0);
                return true;
            }
        });

        detail_page_lv_comment.setOnChildClickListener(new ExpandableListView.OnChildClickListener() {
            @Override
            public boolean onChildClick(ExpandableListView expandableListView, View view, int groupPosition, int childPosition, long l) {
                showReplyDialog(groupPosition,1,childPosition);

                return true;
            }
        });

        detail_page_lv_comment.setOnGroupExpandListener(new ExpandableListView.OnGroupExpandListener() {
            @Override
            public void onGroupExpand(int groupPosition) {
                //toast("展开第"+groupPosition+"个分组");

            }
        });
        adapter.setOnRecyclerViewListener(new CommentExpandAdapter.OnRecyclerViewListener() {
            @Override
            public void onItemClick(String bask_id) {
                setFabulous(bask_id,1);
            }
        });
    }

    public static String stringFilter(String str) throws PatternSyntaxException {
        String regEx = "[/\\:*?<>|\"\n\t]";
        Pattern p = Pattern.compile(regEx);
        Matcher m = p.matcher(str);
        return m.replaceAll(" ");
    }
    /**
     * by moos on 2018/04/20
     * func:弹出回复框
     */
    private void showReplyDialog(final int position,int ii,int childpostion){
        dialog = new BottomSheetDialog(this);
        View commentView = LayoutInflater.from(this).inflate(R.layout.comment_dialog_layout,null);
        final EditText commentText = (EditText) commentView.findViewById(R.id.dialog_comment_et);
        final Button bt_comment = (Button) commentView.findViewById(R.id.dialog_comment_bt);
        if(ii==0){
            commentText.setHint("评论 " + commentsList.get(position).getUname() + " 的评论:");

        }else {
            commentText.setHint("回复 " + commentsList.get(position).getChildren().get(childpostion).getUname() + " 的评论:");

        }
        dialog.setContentView(commentView);
        bt_comment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String replyContent = commentText.getText().toString().trim();
                String  s= stringFilter(replyContent);

                if(!TextUtils.isEmpty(s)){
                    if(ii==0){

                        Comments(s,commentsList.get(position).getComment_id()+"",1);

                    }else {
                        Comments(s,commentsList.get(position).getChildren().get(childpostion).getComment_id()+"",1);

                    }

//                    if(commentsList.get(position).getChildren().size()==0)
//                    {
//                        Comments(replyContent,commentsList.get(position).getComment_id()+"",ii);
//
//                    }else {
//                        Comments(replyContent,commentsList.get(position).getChildren().get(childpostion).getComment_id()+"",ii);
//
//                    }

                    dialog.dismiss();
//                    CommentsListBean.DataBean.ChildrenBean detailBean = new CommentsListBean.DataBean.ChildrenBean("小红",replyContent);
//                    adapter.addTheReplyData(detailBean, position);
//                    detail_page_lv_comment.expandGroup(position);
//                    Toast.makeText(SunSheetDetailsActivity.this,"回复成功",Toast.LENGTH_SHORT).show();
                }else {
                    Toast.makeText(SunSheetDetailsActivity.this,"回复内容不能为空",Toast.LENGTH_SHORT).show();
                }
            }
        });
        commentText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if(!TextUtils.isEmpty(charSequence) && charSequence.length()>2){
                    bt_comment.setBackgroundColor(Color.parseColor("#FFB568"));
                }else {
                    bt_comment.setBackgroundColor(Color.parseColor("#D8D8D8"));
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
        dialog.show();
    }


    /**
     * by moos on 2018/04/20
     * func:弹出评论框
     */
    private void showCommentDialog(){
        dialog = new BottomSheetDialog(this);
        View commentView = LayoutInflater.from(this).inflate(R.layout.comment_dialog_layout,null);
        final EditText commentText = (EditText) commentView.findViewById(R.id.dialog_comment_et);
        final Button bt_comment = (Button) commentView.findViewById(R.id.dialog_comment_bt);
        dialog.setContentView(commentView);
        /**
         * 解决bsd显示不全的情况
         */
        View parent = (View) commentView.getParent();
        BottomSheetBehavior behavior = BottomSheetBehavior.from(parent);
        commentView.measure(0,0);
        behavior.setPeekHeight(commentView.getMeasuredHeight());

        bt_comment.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                String commentContent = commentText.getText().toString().trim();
                String  s= stringFilter(commentContent);
                if(!TextUtils.isEmpty(s)){

                    //commentOnWork(commentContent);

                    Comments(s,"",0);



                    dialog.dismiss();
//                    CommentsListBean.DataBean detailBean = new CommentsListBean.DataBean("小明", commentContent,"刚刚");
//                    adapter.addTheCommentData(detailBean);
//                    Toast.makeText(SunSheetDetailsActivity.this,"评论成功",Toast.LENGTH_SHORT).show();

                }else {
                    Toast.makeText(SunSheetDetailsActivity.this,"评论内容不能为空",Toast.LENGTH_SHORT).show();
                }
            }
        });
        commentText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if(!TextUtils.isEmpty(charSequence) && charSequence.length()>2){
                    bt_comment.setBackgroundColor(Color.parseColor("#FFB568"));
                }else {
                    bt_comment.setBackgroundColor(Color.parseColor("#D8D8D8"));
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
        dialog.show();
    }


    private void Comments(String comment_content,String id,int flag){
        OkhttpUtlis okhttpUtlis = new OkhttpUtlis();
        HashMap<String,String> hashMap = new HashMap<>();
        hashMap.put("bask_id",bask_id);
        hashMap.put("comment_content",comment_content);
        String url="app/Comment/fragrant";
        if(flag==0){
            url="app/Comment/fragrant";
        }else if(flag==1){
            hashMap.put("id",id);
            url="app/Comment/reply";

        }else if(flag==2){


        }

       okhttpUtlis.PostAsynMap(Url.baseUrl + url, hashMap, new Callback() {
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

                               ToastUtil.showToast1(SunSheetDetailsActivity.this,msg);
                               Loadcomments();
                           }else {
                               ToastUtil.showToast1(SunSheetDetailsActivity.this,msg);


                           }

                       } catch (JSONException e) {
                           e.printStackTrace();
                       }


                   }
               });

           }
       });



    }

    private void request() {

        OkhttpUtlis okhttpUtlis = new OkhttpUtlis();
        HashMap<String,String> hashMap = new HashMap<>();
        hashMap.put("bask_id",bask_id);

        okhttpUtlis.PostAsynMap(Url.baseUrl+"app/Bask/baskDetails", hashMap, new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {

            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {

                String result = response.body().string();
                runOnUiThread(new Runnable() {
                    @SuppressLint("NewApi")
                    @Override
                    public void run() {

                        try {
                            JSONObject jsonObject = new JSONObject(result);
                            int code = jsonObject.getInt("code");

                            String msg = jsonObject.getString("msg");

                            if(code==10000){

                                SunSheetDetailsBean sunSheetDetailsBean = JSON.parseObject(result,SunSheetDetailsBean.class);

                                user_id = String.valueOf(sunSheetDetailsBean.getData().getUser_id());

                                Glide.with(SunSheetDetailsActivity.this).load(sunSheetDetailsBean.getData().getImg_head()).asBitmap().centerCrop().into(usericon);
                                username.setText(sunSheetDetailsBean.getData().getUname());
                                usedr_id.setText(sunSheetDetailsBean.getData().getUser_id()+"");
                                if(sunSheetDetailsBean.getData().getIs_fans()==0){

                                    follow_button.setText("关注");
                                    follow_button.setBackground(getResources().getDrawable(R.drawable.login_shap));
                                }else {
                                    follow_button.setText("取消关注");
                                    follow_button.setBackground(getResources().getDrawable(R.drawable.login_change));

                                }
                                text_dec.setText(sunSheetDetailsBean.getData().getManifesto());

                                order_id = sunSheetDetailsBean.getData().getOrder_id()+"";
                                if(sunSheetDetailsBean.getData().getGame_type()==0){

                                    list_2_1.setText("足球");
                                }else {
                                    list_2_1.setText("篮球");

                                }
                                list_2_2.setText(sunSheetDetailsBean.getData().getOrder_price());
                                list_2_3.setText(sunSheetDetailsBean.getData().getWin_money());

                                list_2_4.setText(sunSheetDetailsBean.getData().getMultiple()+"倍");
                                commet_count.setText(sunSheetDetailsBean.getData().getComment_count()+"");
                                lisk_count.setText(sunSheetDetailsBean.getData().getLike_count()+"");
                                if(sunSheetDetailsBean.getData().getBask_is_like()==0){

                                    dianzan.setImageDrawable(getResources().getDrawable(R.mipmap.dianzan));


                                }else {
                                    dianzan.setImageDrawable(getResources().getDrawable(R.mipmap.dianzan_red));

                                }

                            }else {

                                ToastUtil.showToast1(SunSheetDetailsActivity.this,msg);
                            }

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }


                    }
                });

            }
        });




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
            case R.id.fang_details:
                Intent intent = new Intent(this,ProgrammeActivity.class);
                intent.putExtra("order_id",order_id);
                startActivity(intent);
                break;
            case R.id.layout_like:
                setFabulous(bask_id,0);

                break;
            case R.id.layout_ping:

                showCommentDialog();
                break;

        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Content.refre_flag=1;

    }

    private void follow() {
        HashMap<String,String> hashMap = new HashMap<>();
        hashMap.put("parent_id",user_id);
        OkhttpUtlis okhttpUtlis = new OkhttpUtlis();
        okhttpUtlis.PostAsynMap(Url.baseUrl + "app/god/attention", hashMap, new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {

            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                final String result = response.body().string();
                runOnUiThread(new Runnable() {
                    @SuppressLint("NewApi")
                    @Override
                    public void run() {
                        try {
                            JSONObject jsonObject = new JSONObject(result);
                            int code = jsonObject.getInt("code");
                            String msg = jsonObject.getString("msg");
                            if(code==10000){

                                ToastUtil.showToast1(SunSheetDetailsActivity.this,msg);
                                follow_button.setText("取消关注");
                                follow_button.setBackground(getResources().getDrawable(R.drawable.login_change));

                            }else {
                                ToastUtil.showToast1(SunSheetDetailsActivity.this,msg);
                            }

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }


                    }
                });


            }
        });


    }


    private void setFabulous(String bask_id,int like_type) {
        OkhttpUtlis okhttpUtlis = new OkhttpUtlis();

        HashMap<String,String> hashMap = new HashMap<>();
        hashMap.put("id",bask_id);
        hashMap.put("like_type",like_type+"");
        okhttpUtlis.PostAsynMap(Url.baseUrl + "app/Comment/likeTags", hashMap, new Callback() {
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
                            JSONObject  jsonObject= new JSONObject(result);
                            int code = jsonObject.getInt("code");

                            String msg = jsonObject.getString("msg");

                            if(code==10000){

                                ToastUtil.showToast(SunSheetDetailsActivity.this,msg);
                                if(like_type==0){
                                    request();

                                }else {

                                    Loadcomments();
                                }


                            }else {
                                ToastUtil.showToast(SunSheetDetailsActivity.this,msg);


                            }

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }


                    }
                });



            }
        });


    }


    private void Cancelfollow() {
        HashMap<String,String> hashMap = new HashMap<>();
        hashMap.put("parent_id",user_id);
        OkhttpUtlis okhttpUtlis = new OkhttpUtlis();
        okhttpUtlis.PostAsynMap(Url.baseUrl + "app/god/abolish", hashMap, new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {

            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                final String result = response.body().string();
                runOnUiThread(new Runnable() {
                    @SuppressLint("NewApi")
                    @Override
                    public void run() {
                        try {
                            JSONObject jsonObject = new JSONObject(result);
                            int code = jsonObject.getInt("code");
                            String msg = jsonObject.getString("msg");
                            if(code==10000){

                                ToastUtil.showToast1(SunSheetDetailsActivity.this,msg);
                                follow_button.setText("关注");
                                follow_button.setBackground(getResources().getDrawable(R.drawable.login_shap));

                            }else {
                                ToastUtil.showToast1(SunSheetDetailsActivity.this,msg);
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
