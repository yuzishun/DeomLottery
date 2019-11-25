package com.example.yuzishun.newdeom.main.util;

import android.app.Activity;
import android.util.Log;

import com.alibaba.fastjson.JSON;
import com.chad.library.adapter.base.entity.MultiItemEntity;
import com.example.yuzishun.newdeom.main.betting.BettingfootActivity;
import com.example.yuzishun.newdeom.main.betting.Item1_Mixed;
import com.example.yuzishun.newdeom.main.betting.Item_Mixed;
import com.example.yuzishun.newdeom.model.ChooseMixedBean;
import com.example.yuzishun.newdeom.model.ItemPoint;
import com.example.yuzishun.newdeom.model.MixedFootballBean;
import com.example.yuzishun.newdeom.net.OkhttpUtlis;
import com.example.yuzishun.newdeom.net.Url;
import com.example.yuzishun.newdeom.utils.ToastUtil;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

/**
 * Created by apple on 2019/11/21.
 * 足球过关获取某个模块
 */

public class BettFootMixedUtil {

    public Activity activity;

    public BettFootMixedUtil(Activity activity) {
        this.activity = activity;
    }

    public ArrayList<MultiItemEntity> getlist_mixed(int mixed) {
        OkhttpUtlis okhttpUtlis = new OkhttpUtlis();
        final ArrayList<MultiItemEntity> res = new ArrayList<>();
        okhttpUtlis.GetAsynMap(Url.baseUrl + "app/ball/getFootballList", new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {

                Log.e("YZS", e.getMessage() + "");

            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                final String result = response.body().string();
                try {


                    activity.runOnUiThread(new Runnable() {
                        @Override
                        public void run() {

                            try {
                                JSONObject jsonObject = new JSONObject(result);
                                int code = jsonObject.getInt("code");
                                String msg = jsonObject.getString("msg");
                                if (code == 10000) {
//                                ToastUtil.showToast(BettingActivity.this, msg + "");
                                    MixedFootballBean footballBean = JSON.parseObject(result, MixedFootballBean.class);
                                    List<ChooseMixedBean> list_choose = new ArrayList<>();

                                    for (int i = 0; i < footballBean.getData().getGame_info().size(); i++) {

                                        Item_Mixed item = new Item_Mixed(footballBean.getData().getGame_info().get(i).getGame_week() + "" + footballBean.getData().getGame_info().get(i).getGame_group_time() + "共有" + footballBean.getData().getGame_info().get(i).getGame_info().size() + "场比赛可投");
                                        for (int j = 0; j < footballBean.getData().getGame_info().get(i).getGame_info().size(); j++) {
                                            ChooseMixedBean chooseMixedBean = new ChooseMixedBean();
                                            List<MixedFootballBean.DataBean.GameInfoBeanX.GameInfoBean.HomeScoreOddsBean> home_score_odds = footballBean.getData().getGame_info().get(i).getGame_info().get(j).getHome_score_odds();

                                            List<MixedFootballBean.DataBean.GameInfoBeanX.GameInfoBean.LetScoreOddsBean> home_let_odds = footballBean.getData().getGame_info().get(i).getGame_info().get(j).getLet_score_odds();
                                            List<MixedFootballBean.DataBean.GameInfoBeanX.GameInfoBean.ScoreOddsBean> score_odds = footballBean.getData().getGame_info().get(i).getGame_info().get(j).getScore_odds();
                                            List<MixedFootballBean.DataBean.GameInfoBeanX.GameInfoBean.TotalOddsBean> total_odds = footballBean.getData().getGame_info().get(i).getGame_info().get(j).getTotal_odds();
                                            List<MixedFootballBean.DataBean.GameInfoBeanX.GameInfoBean.HalfOddsBean> half_odds = footballBean.getData().getGame_info().get(i).getGame_info().get(j).getHalf_odds();
                                            List<ItemPoint> list_single = new ArrayList<>();

                                            switch (mixed) {
                                                case 1:
                                                    for (int k = 0; k < home_score_odds.size(); k++) {
                                                        ItemPoint itemPoint = new ItemPoint();
                                                        itemPoint.setIsselect(false);
                                                        itemPoint.setId(home_score_odds.get(k).getOdds_code());
                                                        itemPoint.setGame_odds_id(home_score_odds.get(k).getGame_odds_id());
                                                        itemPoint.setOdds(home_score_odds.get(k).getOdds());
                                                        itemPoint.setSingle(home_score_odds.get(k).getSingle());

                                                        list_single.add(itemPoint);
                                                    }
                                                    break;
                                                case 2:
                                                    for (int k = 0; k < home_let_odds.size(); k++) {

                                                        ItemPoint itemPoint = new ItemPoint();
                                                        itemPoint.setIsselect(false);
                                                        itemPoint.setId(home_let_odds.get(k).getOdds_code());
                                                        itemPoint.setGame_odds_id(home_let_odds.get(k).getGame_odds_id());
                                                        itemPoint.setOdds(home_let_odds.get(k).getOdds());
                                                        itemPoint.setSingle(home_let_odds.get(k).getSingle());

                                                        list_single.add(itemPoint);


                                                    }
                                                    break;
                                                case 3:
                                                    for (int k = 0; k < score_odds.size(); k++) {

                                                        ItemPoint itemPoint = new ItemPoint();
                                                        itemPoint.setIsselect(false);
                                                        itemPoint.setId(score_odds.get(k).getOdds_code());
                                                        itemPoint.setGame_odds_id(score_odds.get(k).getGame_odds_id());
                                                        itemPoint.setOdds(score_odds.get(k).getOdds());
                                                        itemPoint.setSingle(score_odds.get(k).getSingle());

                                                        list_single.add(itemPoint);


                                                    }
                                                    break;
                                                case 4:
                                                    for (int k = 0; k < total_odds.size(); k++) {

                                                        ItemPoint itemPoint = new ItemPoint();
                                                        itemPoint.setIsselect(false);
                                                        itemPoint.setId(total_odds.get(k).getOdds_code());
                                                        itemPoint.setGame_odds_id(total_odds.get(k).getGame_odds_id());
                                                        itemPoint.setOdds(total_odds.get(k).getOdds());
                                                        itemPoint.setSingle(total_odds.get(k).getSingle());

                                                        list_single.add(itemPoint);


                                                    }
                                                    break;
                                                case 5:
                                                    for (int k = 0; k < half_odds.size(); k++) {

                                                        ItemPoint itemPoint = new ItemPoint();
                                                        itemPoint.setIsselect(false);
                                                        itemPoint.setId(half_odds.get(k).getOdds_code());
                                                        itemPoint.setGame_odds_id(half_odds.get(k).getGame_odds_id());
                                                        itemPoint.setOdds(half_odds.get(k).getOdds());
                                                        itemPoint.setSingle(half_odds.get(k).getSingle());

                                                        list_single.add(itemPoint);


                                                    }

                                                    break;
                                            }


                                            chooseMixedBean.setOnelist(list_single);
                                            chooseMixedBean.setDesc("展开更多选项");
                                            chooseMixedBean.setGame_id(footballBean.getData().getGame_info().get(i).getGame_info().get(j).getGame_id());
                                            chooseMixedBean.setHome_team(footballBean.getData().getGame_info().get(i).getGame_info().get(j).getGame_home_team_name());

                                            chooseMixedBean.setGuest_team(footballBean.getData().getGame_info().get(i).getGame_info().get(j).getGame_guest_team_name());
                                            chooseMixedBean.setName(footballBean.getData().getGame_info().get(i).getGame_info().get(j).getGame_sequence_no() + "        " + footballBean.getData().getGame_info().get(i).getGame_info().get(j).getGame_home_team_name()
                                                    + "        " + "vs" + "        " + footballBean.getData().getGame_info().get(i).getGame_info().get(j).getGame_guest_team_name());
                                            chooseMixedBean.setHome_score(footballBean.getData().getGame_info().get(i).getGame_info().get(j).getGame_home_score());
                                            chooseMixedBean.setGuest_score(footballBean.getData().getGame_info().get(i).getGame_info().get(j).getGame_let_score());
                                            list_choose.add(chooseMixedBean);


                                            Log.e("SingleSize", j + "");

                                            Item1_Mixed item1 = new Item1_Mixed(footballBean.getData().getGame_info().get(i).getGame_info().get(j).getGame_name(),
                                                    footballBean.getData().getGame_info().get(i).getGame_info().get(j).getGame_home_team_name()
                                                    , footballBean.getData().getGame_info().get(i).getGame_info().get(j).getGame_guest_team_name(), footballBean.getData().getGame_info().get(i).getGame_info().get(j).getGame_home_score(),
                                                    footballBean.getData().getGame_info().get(i).getGame_info().get(j).getGame_let_score(), footballBean.getData().getGame_info().get(i).getGame_info().get(j).getGame_sequence_no()
                                                    , footballBean.getData().getGame_info().get(i).getGame_info().get(j).getGame_stop_time()
                                                    , list_single, list_choose, "展开更多选项", footballBean.getData().getGame_info().get(i).getGame_info().get(j).getGame_no());


                                            item.addSubItem(item1);


                                        }
                                        res.add(item);
                                    }


//                                handler.sendEmptyMessageDelayed(1, 3000);
//                                    handler.sendEmptyMessage(2);

                                } else {
                                    ToastUtil.showToast(activity, msg + "");

                                }
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }


                        }
                    });
                } catch (Exception e) {


                }
            }

        });


        return res;
    }
}
