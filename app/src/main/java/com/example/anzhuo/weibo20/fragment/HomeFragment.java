package com.example.anzhuo.weibo20.fragment;

import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.PopupWindow;

import com.example.anzhuo.weibo20.R;

import com.example.anzhuo.weibo20.info.Info;
import com.example.anzhuo.weibo20.localtime.LocalTime;
import com.example.anzhuo.weibo20.mybaseadapter.MyBaseAdapter;
import com.example.anzhuo.weibo20.taget.Taget;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by anzhuo on 2016/8/24.
 */
public class HomeFragment extends Fragment{
    ImageButton friend;
    Button moremy;
    ImageButton radar;
     PopupWindow popupWindow;
    ListView listView;
    String url;
    StringBuffer stringBuffer;
    MyBaseAdapter myBaseAdapter;
    Info info;
    SwipeRefreshLayout swipeRefreshLayout;

    ImageView im;
    private static final int MSG=10;
    private static final int FRESH=11;
/*
  拿到网络数据
 */
    List<Info> list=new ArrayList<>();
    Handler handler=new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what){
                case MSG:
                    myBaseAdapter=new MyBaseAdapter(getActivity(), getdata());
                    listView.setAdapter(myBaseAdapter);
                    break;
                case FRESH:
                    myBaseAdapter=new MyBaseAdapter(getActivity(), getdata());
                    myBaseAdapter.notifyDataSetChanged();
                    listView.setAdapter(myBaseAdapter);
                    swipeRefreshLayout.setRefreshing(false);
                    break;
            }

        }
    };

        public List<Info> getdata(){
            try {
                JSONObject jso=new JSONObject(stringBuffer.toString());
                JSONArray jsa=jso.optJSONArray("statuses");
                for (int i=0;i<jsa.length();i++){
                    info=new Info();
                    JSONObject jsonObject= (JSONObject) jsa.get(i);
                    String content=jsonObject.getString("text");
                    String from=jsonObject.getString("source");
                    String time=jsonObject.getString("created_at");
                    String f=Taget.delHTMLTag(from);
                    JSONObject Object=jsonObject.getJSONObject("user");
                    String name=Object.getString("name");
                    String title=Object.getString("profile_image_url");
                    info.setTime(LocalTime.getdata(time));
                    info.setTitle(title);
                    info.setName(name);
                    info.setContent(content);
                    info.setFrom(f);
                    list.add(info);
                }
            } catch (JSONException e) {
                e.printStackTrace();
            } catch (ParseException e) {
                e.printStackTrace();
            }

           return list;
}


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
         super.onCreateView(inflater, container, savedInstanceState);
        View view=inflater.inflate(R.layout.home,container,false);
        friend= (ImageButton) view.findViewById(R.id.iv_friend);
        moremy= (Button) view.findViewById(R.id.bt_mine);
        radar= (ImageButton) view.findViewById(R.id.ib_radar);
        listView= (ListView) view.findViewById(R.id.lv_home);
        im= (ImageView) view.findViewById(R.id.title);
         swipeRefreshLayout= (SwipeRefreshLayout) view.findViewById(R.id.swip);

        swipeRefreshLayout.setColorSchemeResources(android.R.color.holo_blue_dark, android.R.color.holo_blue_light, android.R.color.holo_green_light, android.R.color.holo_green_light);
        swipeRefreshLayout.setProgressViewEndTarget(true, 100);
        // 设置手指在屏幕下拉多少距离会触发下拉刷新
        swipeRefreshLayout.setDistanceToTriggerSync(300);
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        try {
                            list.clear();
                            url="https://api.weibo.com/2/statuses/public_timeline.json?access_token=2.00h3ZcYGMRcVeEe3930a0f0c0i3MUR";
                            requrestNetWork(url,FRESH);
                            Thread.sleep(3000);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }

                    }
                }).start();
            }
        });




        new Thread(){
            @Override
            public void run() {
                url="https://api.weibo.com/2/statuses/public_timeline.json?access_token=2.00h3ZcYGMRcVeEe3930a0f0c0i3MUR";
                requrestNetWork(url,MSG);
            }
        }.start();

        //雷达按钮
        radar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (popupWindow!=null&&popupWindow.isShowing()){
                    popupWindow.dismiss();
                }else {
                    radarpopwinow();
                    popupWindow.showAsDropDown(radar,0,5);
                }
            }
        });

        //个人按钮
        moremy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
             if (popupWindow!=null&&popupWindow.isShowing()){
                 popupWindow.dismiss();
             }else {
                 PopupWindowView();
                 popupWindow.showAsDropDown(moremy,0,5);
             }

            }
        });
        return view;
    }


    private void requrestNetWork(String url,int id) {
        try {
            URL urlStr=new URL(url);
            HttpURLConnection con= (HttpURLConnection) urlStr.openConnection();
            InputStream in=new BufferedInputStream(con.getInputStream());
            stringBuffer=new StringBuffer();
            byte[] b=new byte[8*1024];
            int len;
            while((len=in.read(b))!=-1){
                stringBuffer.append(new String(b,0,len));
            }
            handler.sendEmptyMessage(id);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    private void radarpopwinow() {
        View radview=getLayoutInflater(null).inflate(R.layout.radar_item,null,false);
        popupWindow=new PopupWindow(radview,220,250);
        popupWindow.setBackgroundDrawable(new BitmapDrawable());
        popupWindow.setFocusable(true);
        popupWindow.setOnDismissListener(new PopupWindow.OnDismissListener() {
            @Override
            public void onDismiss() {
                popupWindow.dismiss();
            }
        });
        popupWindow.setAnimationStyle(R.style.AnimationFade);
        radview.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                if (popupWindow != null && popupWindow.isShowing()) {
                    popupWindow.dismiss();
                    popupWindow = null;
                }
                return false;
            }
        });
    }


    private void PopupWindowView() {
        View popview=getLayoutInflater(null).inflate(R.layout.popview_item,null,false);
        popupWindow=new PopupWindow(popview,300,1000);
        popupWindow.setBackgroundDrawable(new BitmapDrawable());//设置了Popupwindow的background以后才能让Popupwindow响应返回的操作。
        popupWindow.setFocusable(true);
        popupWindow.setOnDismissListener(new PopupWindow.OnDismissListener() {
            @Override
            public void onDismiss() {
                popupWindow.dismiss();
            }
        });
        //设置动画
        popupWindow.setAnimationStyle(R.style.AnimationFade);
        popview.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                if (popupWindow != null && popupWindow.isShowing()) {
                    popupWindow.dismiss();
                    popupWindow = null;
                }

                return false;
            }
        });
    }


}
