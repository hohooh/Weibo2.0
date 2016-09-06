package com.example.anzhuo.weibo20.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import com.example.anzhuo.weibo20.R;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by anzhuo on 2016/8/24.
 */
public class MineFragment extends Fragment {
    ListView listView;
    List<Map<String,Object>> list =new ArrayList<>();
    private SimpleAdapter simpleAdapter;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
         super.onCreateView(inflater, container, savedInstanceState);
        View view=inflater.inflate(R.layout.mine,container,false);
        listView= (ListView) view.findViewById(R.id.list_mine);
            String from[]=new String[]{"pic","wenzi","an"};
            int to[]=new int[]{R.id.pic,R.id.wenzi,R.id.an};

            list.clear();
            simpleAdapter=new SimpleAdapter(getActivity(),getdata(),R.layout.mine_mod,from,to);
            listView.setAdapter(simpleAdapter);

        return view;
    }
    private  List<Map<String,Object>> getdata() {
        Map<String,Object> map= new HashMap<>();
        map.put("pic",R.mipmap.icon_3_new_d);
        map.put("wenzi","新的好友");
        map.put("an",R.mipmap.lea);
        Map<String,Object> map1= new HashMap<>();
        map1.put("pic",R.mipmap.rewu);
        map1.put("wenzi","新手任务");
        map1.put("an",R.mipmap.lea);
        Map<String,Object> map2= new HashMap<>();
        map2.put("pic",R.mipmap.xc);
        map2.put("wenzi","我的相册");
        map2.put("an",R.mipmap.lea);
        Map<String,Object> map3= new HashMap<>();
        map3.put("pic",R.mipmap.wdz);
        map3.put("wenzi","我的赞");
        map3.put("an",R.mipmap.lea);
        Map<String,Object> map4= new HashMap<>();
        map4.put("pic",R.mipmap.more_icon_zhifubao);
        map4.put("wenzi","微博支付");
        map4.put("an",R.mipmap.lea);
        Map<String,Object> map5= new HashMap<>();
        map5.put("pic",R.mipmap.yn);
        map5.put("wenzi","微博运动");
        map5.put("an",R.mipmap.lea);
        list.add(map);
        list.add(map1);
        list.add(map2);
        list.add(map3);
        list.add(map4);
        list.add(map5);
        return list;
    }
}
