package com.example.yuzishun.deomlottery.main.util;

import android.app.Activity;
import android.util.Log;

import com.alibaba.fastjson.JSON;
import com.chad.library.adapter.base.entity.MultiItemEntity;
import com.example.yuzishun.deomlottery.main.baskball.Expand1Mixed_bask;
import com.example.yuzishun.deomlottery.main.baskball.ExpandMixed_bask;
import com.example.yuzishun.deomlottery.main.single.Item1_Single;
import com.example.yuzishun.deomlottery.main.single.Item_Single;
import com.example.yuzishun.deomlottery.model.BasketSingleBean;
import com.example.yuzishun.deomlottery.model.ChooseBaskBean;
import com.example.yuzishun.deomlottery.model.ChooseMixedBean;
import com.example.yuzishun.deomlottery.model.ItemPoint;
import com.example.yuzishun.deomlottery.model.SingleBean;
import com.example.yuzishun.deomlottery.net.OkhttpUtlis;
import com.example.yuzishun.deomlottery.net.Url;
import com.example.yuzishun.deomlottery.utils.ToastUtil;

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
 * 足球单关提取某个模块
 */

public class BettFootBaskSIngleUtil {

    public Activity context;

    public BettFootBaskSIngleUtil(Activity context) {
        this.context = context;
    }

    //单关请求的接口
    public ArrayList<MultiItemEntity> request(int single) {

        final ArrayList<MultiItemEntity> res = new ArrayList<>();

        OkhttpUtlis okhttpUtlis = new OkhttpUtlis();
        okhttpUtlis.GetAsynMap(Url.baseUrl + "app/ball/getSingleBallList?single=" + single, new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {

            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                final String result = response.body().string();
                try {


                    context.runOnUiThread(new Runnable() {
                        @Override
                        public void run() {

                            try {
                                JSONObject jsonObject = new JSONObject(result);
                                int code = jsonObject.getInt("code");
                                String msg = jsonObject.getString("msg");
                                if (code == 10000) {

                                    SingleBean singleBean = JSON.parseObject(result, SingleBean.class);
                                    List<ChooseMixedBean> list_choose = new ArrayList<>();

                                    for (int i = 0; i < singleBean.getData().getGame_info().size(); i++) {

                                        Item_Single item_single = new Item_Single(singleBean.getData().getGame_info().get(i).getGame_week() + "" + singleBean.getData().getGame_info().get(i).getGame_group_time() + "共有" + singleBean.getData().getGame_info().get(i).getGame_info().size() + "场比赛可投");

                                        for (int j = 0; j < singleBean.getData().getGame_info().get(i).getGame_info().size(); j++) {

                                            ChooseMixedBean chooseMixedBean = new ChooseMixedBean();

                                            List<SingleBean.DataBean.GameInfoBeanX.GameInfoBean.SingleOddsBean> single_odds = singleBean.getData().getGame_info().get(i).getGame_info().get(j).getSingle_odds();

                                            List<ItemPoint> list_single = new ArrayList<>();

                                            for (int k = 0; k < single_odds.size(); k++) {

                                                ItemPoint itemPoint = new ItemPoint();
                                                itemPoint.setIsselect(false);
                                                itemPoint.setId(single_odds.get(k).getOdds_code());
                                                itemPoint.setGame_odds_id(single_odds.get(k).getGame_odds_id());
                                                itemPoint.setOdds(single_odds.get(k).getOdds());
                                                list_single.add(itemPoint);


                                            }
                                            chooseMixedBean.setOnelist(list_single);
                                            chooseMixedBean.setDesc("展开更多选项");
                                            chooseMixedBean.setGame_id(singleBean.getData().getGame_info().get(i).getGame_info().get(j).getGame_id());
                                            chooseMixedBean.setHome_team(singleBean.getData().getGame_info().get(i).getGame_info().get(j).getGame_home_team_name());

                                            chooseMixedBean.setGuest_team(singleBean.getData().getGame_info().get(i).getGame_info().get(j).getGame_guest_team_name());
                                            chooseMixedBean.setName(singleBean.getData().getGame_info().get(i).getGame_info().get(j).getGame_sequence_no() + "        " + singleBean.getData().getGame_info().get(i).getGame_info().get(j).getGame_home_team_name()
                                                    + "        " + "vs" + "        " + singleBean.getData().getGame_info().get(i).getGame_info().get(j).getGame_guest_team_name());
                                            chooseMixedBean.setHome_score(singleBean.getData().getGame_info().get(i).getGame_info().get(j).getGame_home_score());
                                            chooseMixedBean.setGuest_score(singleBean.getData().getGame_info().get(i).getGame_info().get(j).getGame_let_score());
                                            list_choose.add(chooseMixedBean);


                                            Log.e("SingleSize", j + "");

                                            Item1_Single item1 = new Item1_Single(singleBean.getData().getGame_info().get(i).getGame_info().get(j).getGame_name(),
                                                    singleBean.getData().getGame_info().get(i).getGame_info().get(j).getGame_home_team_name()
                                                    , singleBean.getData().getGame_info().get(i).getGame_info().get(j).getGame_guest_team_name(), singleBean.getData().getGame_info().get(i).getGame_info().get(j).getGame_home_score(),
                                                    singleBean.getData().getGame_info().get(i).getGame_info().get(j).getGame_let_score(), singleBean.getData().getGame_info().get(i).getGame_info().get(j).getGame_sequence_no()
                                                    , singleBean.getData().getGame_info().get(i).getGame_info().get(j).getGame_stop_time()
                                                    , list_single, list_choose, "展开更多选项", singleBean.getData().getGame_info().get(i).getGame_info().get(j).getGame_no());


                                            item_single.addSubItem(item1);


                                        }
                                        res.add(item_single);


                                    }


                                } else {
                                    ToastUtil.showToast1(context, msg);

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


    //单关请求的接口
    public ArrayList<MultiItemEntity> request_bask(int single) {

        final ArrayList<MultiItemEntity> res = new ArrayList<>();

        OkhttpUtlis okhttpUtlis = new OkhttpUtlis();
        okhttpUtlis.GetAsynMap(Url.baseUrl + "app/ball/getSingleBasketBallList?single=" + single, new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {

            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                final String result = response.body().string();
                try {


                    context.runOnUiThread(new Runnable() {
                        @Override
                        public void run() {

                            try {
                                JSONObject jsonObject = new JSONObject(result);
                                int code = jsonObject.getInt("code");
                                String msg = jsonObject.getString("msg");
                                if (code == 10000) {

                                    BasketSingleBean singleBean = JSON.parseObject(result, BasketSingleBean.class);
                                    List<ChooseBaskBean> list_choose = new ArrayList<>();
                                    for (int i = 0; i < singleBean.getData().getGame_info().size(); i++) {

                                        ExpandMixed_bask item_single = new ExpandMixed_bask(singleBean.getData().getGame_info().get(i).getGame_week() + "" + singleBean.getData().getGame_info().get(i).getGame_group_time() + "共有" + singleBean.getData().getGame_info().get(i).getGame_info().size() + "场比赛可投");

                                        for (int j = 0; j < singleBean.getData().getGame_info().get(i).getGame_info().size(); j++) {
                                            ChooseBaskBean chooseBaskBean = new ChooseBaskBean();

                                            List<BasketSingleBean.DataBean.GameInfoBeanX.GameInfoBean.SingleOddsBean> single_odds = singleBean.getData().getGame_info().get(i).getGame_info().get(j).getSingle_odds();

                                            List<ItemPoint> list_single = new ArrayList<>();
                                            List<ItemPoint> list_mixed_bottom = new ArrayList<>();

                                            if(single==3){

                                                for (int k = 0; k < 6; k++) {

                                                    ItemPoint itemPoint = new ItemPoint();
                                                    itemPoint.setIsselect(false);
                                                    itemPoint.setId(single_odds.get(k).getOdds_code());
                                                    itemPoint.setGame_odds_id(single_odds.get(k).getGame_odds_id());
                                                    itemPoint.setOdds(single_odds.get(k).getOdds());
                                                    itemPoint.setSingle(single_odds.get(k).getSingle());
                                                    list_single.add(itemPoint);


                                                }

                                                for (int k = 6; k < single_odds.size(); k++) {

                                                    ItemPoint itemPoint = new ItemPoint();
                                                    itemPoint.setIsselect(false);
                                                    itemPoint.setId(single_odds.get(k).getOdds_code());
                                                    itemPoint.setGame_odds_id(single_odds.get(k).getGame_odds_id());
                                                    itemPoint.setOdds(single_odds.get(k).getOdds());
                                                    itemPoint.setSingle(single_odds.get(k).getSingle());

                                                    list_mixed_bottom.add(itemPoint);


                                                }
                                            }else {


                                                for (int k = 0; k < single_odds.size(); k++) {

                                                    ItemPoint itemPoint = new ItemPoint();
                                                    itemPoint.setIsselect(false);
                                                    itemPoint.setId(single_odds.get(k).getOdds_code());
                                                    itemPoint.setGame_odds_id(single_odds.get(k).getGame_odds_id());
                                                    itemPoint.setOdds(single_odds.get(k).getOdds());
                                                    itemPoint.setSingle(single_odds.get(k).getSingle());

                                                    list_single.add(itemPoint);


                                                }

                                            }

                                            chooseBaskBean.setOnelist(list_single);
                                            chooseBaskBean.setTwolist(list_mixed_bottom);
                                            chooseBaskBean.setDesc("展开更多选项");

                                            chooseBaskBean.setGame_id(singleBean.getData().getGame_info().get(i).getGame_info().get(j).getGame_id());
                                            chooseBaskBean.setHome_team(singleBean.getData().getGame_info().get(i).getGame_info().get(j).getGame_home_team_name());

                                            chooseBaskBean.setGuest_team(singleBean.getData().getGame_info().get(i).getGame_info().get(j).getGame_guest_team_name());
                                            chooseBaskBean.setName(singleBean.getData().getGame_info().get(i).getGame_info().get(j).getGame_sequence_no()+"        "+singleBean.getData().getGame_info().get(i).getGame_info().get(j).getGame_guest_team_name()
                                                    +"        "+"vs"+"        "+singleBean.getData().getGame_info().get(i).getGame_info().get(j).getGame_home_team_name());

                                            list_choose.add(chooseBaskBean);


                                            Expand1Mixed_bask expand1Item_bask = new Expand1Mixed_bask(singleBean.getData().getGame_info().get(i).getGame_info().get(j).getGame_name(),
                                                    singleBean.getData().getGame_info().get(i).getGame_info().get(j).getGame_home_team_name(),
                                                    singleBean.getData().getGame_info().get(i).getGame_info().get(j).getGame_guest_team_name(),
                                                    singleBean.getData().getGame_info().get(i).getGame_info().get(j).getGame_home_score(),
                                                    singleBean.getData().getGame_info().get(i).getGame_info().get(j).getGame_let_score(),
                                                    singleBean.getData().getGame_info().get(i).getGame_info().get(j).getGame_sequence_no(),
                                                    singleBean.getData().getGame_info().get(i).getGame_info().get(j).getGame_stop_time(),list_single,list_mixed_bottom,list_choose,
                                                    singleBean.getData().getGame_info().get(i).getGame_info().get(j).getGame_no(),"展开更多选项");


                                            item_single.addSubItem(expand1Item_bask);


                                        }
                                        res.add(item_single);


                                    }


                                } else {
                                    ToastUtil.showToast1(context, msg);

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
