package com.example.yuzishun.deomlottery.main.activity;

import android.content.Intent;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.yuzishun.deomlottery.R;
import com.example.yuzishun.deomlottery.base.BaseActivity;

import butterknife.BindView;
import butterknife.ButterKnife;

public class Play_MessageActivity extends BaseActivity {
    @BindView(R.id.title_text)
    TextView title_text;
    @BindView(R.id.image_back)
    LinearLayout image_back;
    @BindView(R.id.tet_t)
    TextView tet_t;
    @Override
    public int intiLayout() {
        return R.layout.activity_play__message;
    }

    @Override
    public void initView() {
        ButterKnife.bind(this);
        Intent intent = getIntent();
        int flag = intent.getIntExtra("flag",0);
        if(flag==0){
            tet_t.setText("1.截止时间\n" +
                    "赛前10分钟截止投注\n" +
                    "2.选场次\n" +
                    "胜平负:竞猜某场比赛中(含伤停补时,不含加时)主队的胜,平,负;\n" +
                    "让球胜平负:竞猜某场比赛中(含伤停补时,不含加时)计算让球后的主队的胜,平,负\n" +
                    "全场比分:竞猜某场比赛中(含伤停补时,不含加时)全场比赛的比分\n" +
                    "总进球:竞猜某场比赛中(含伤停补时,不含加时)两队的总进球的数量\n" +
                    "全半场:竞猜主队在上半场和全场(喊伤停补时,不含加时)的胜,平,负结果\n" +
                    "混合过关:可同时竞猜胜平负,让球胜平负,比分,总进球,半全场;\n" +
                    "二选一:仅针对让球值(-1)和(+1)的比赛,将1场比赛转化为2种赛果的混合过关投注,其他让球值或某场比赛只开售一种投注方式的,由于无法模拟均不能进行二选一混合投注.让球符号含义:”+”为客让主,”-”为主让客.让球胜平负结果为主队得分”+ _”让球数之后的本场比赛赛果.\n" +
                    "3.选过关方式\n" +
                    "过关方式代表了你会将已选的几场比赛组合多少注,如选择了三场比赛,选择过关方式为2串1(2代表3场比赛中任意2场比赛;1代表一注),则3场比赛会任意2场自由组合成1注,共产生3注.\n" +
                    "4.胆\n" +
                    "当你觉得对某场比赛胜负把握较大时,可定胆该场比赛,则系统会将不包含该场比赛的所有投注删除,只保留含有该场比赛的所有投注\n" +
                    "5.奖项设置\n" +
                    "竞彩足球为固定奖金,指数直接决定你投注本结果将获得的奖金.比如你买了1注2元的3串1,下注的时候三个结果的指数分是1.8,3.2,2.0,那么如果三个结果全部正确,你将获得的奖金是2*1.8*3.2*2.0 = **元,以此类推.\n");
        }else {
            tet_t.setText("竞彩篮球胜负游戏:\n" +
                    "对指定的比赛场次在全场(含加时赛)的比赛结果进行投注.\n" +
                    "每一场比赛设置2种比赛结果选项:\n" +
                    "”主胜”:表示主队胜,客队负\n" +
                    "“客胜”:表示主队负,客队胜\n" +
                    "竞彩篮球让分胜负游戏:\n" +
                    "指定的比赛场次在全场(含加时赛)的比赛结果进行投注.每一场比赛设置2钟比赛结果选项:\n" +
                    "”胜”:表示主队胜,客队负\n" +
                    "“负”表示主队负,客队胜\n" +
                    "对每个选定的比赛场次,国家体育总局体育彩票管理中心根据实际比赛情况采用让分方式确定胜负关系.具体让分球队及让分数量和竞猜赛程一同公布\n" +
                    "竞彩篮球大小分游戏:\n" +
                    "对指定的比赛场次在全场(含加时赛)的主队和客队得分总数大于或小于预设总分数进行投注.每一场比赛设置2种选项:\n" +
                    "”大分”:表示主队和客队得分总数大于预设总分数\n" +
                    "“小分”:表示主队和客队得分总数小于预设总分数.\n" +
                    "对每个选定的比赛场次,国家体育总局体育彩票管理中心根据实际比赛情况给出主队和客队得分的预设总分数.具体的预设总分数和竞猜赛程一同公布.\n" +
                    "竞彩篮球胜分差游戏:\n" +
                    "对指定的比赛场次在全场(含加时赛)的主队和客队的得分差距结果进行投注.每一场比赛设置12种得分差距结果选项:\n" +
                    "”主胜26+”:表示主队胜客队26分或26分以上;\n" +
                    "“主胜21-25”:表示主队胜客队21分至25分;\n" +
                    "“主胜11-15”:表示主队胜客队11分至25分;\n" +
                    "“主胜6-10”:表示主队胜客队6分至10分;\n" +
                    "“主胜1-5”:表示主队胜客队1分至5分;\n" +
                    "“客胜1-5”:表示客队胜主队1分至5分;\n" +
                    "“客胜6-10”:表示客队胜主队6分至10分\n" +
                    "“客胜11-15”:表示客队胜主队11分至15分\n" +
                    "“客胜16-20”:表示客队胜主队16分至20分\n" +
                    "“客胜21-25”:表示客队胜主队21分至25分;\n");

        }
        title_text.setText("玩法介绍");
        image_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

    }

    @Override
    public void initData() {

    }
}
