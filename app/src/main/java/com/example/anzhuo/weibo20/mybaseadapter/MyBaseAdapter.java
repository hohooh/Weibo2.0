package com.example.anzhuo.weibo20.mybaseadapter;

import android.content.Context;
import android.graphics.Bitmap;
import android.support.v4.util.LruCache;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.TextView;

import com.example.anzhuo.weibo20.R;

import com.example.anzhuo.weibo20.imageloader.ImageLoad;
import com.example.anzhuo.weibo20.info.Info;

import java.util.List;
import java.util.zip.Inflater;

/**
 * Created by anzhuo on 2016/8/24.
 */
public class MyBaseAdapter extends BaseAdapter {
    List<Info> list;
    Info info;
    Context context;
    String  url="https://api.weibo.com/2/statuses/public_timeline.json?access_token=2.00h3ZcYGMRcVeEe3930a0f0c0i3MUR";

    public MyBaseAdapter(Context context, List<Info> list){
       this.list=list;
       this.context=context;
   }

    @Override
    public int getCount() {
        return list==null?0:list.size();
    }

    @Override
    public Object getItem(int i) {
        return list.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder=null;
        if(convertView==null){
            viewHolder=new ViewHolder();
            convertView= LayoutInflater.from(context).inflate(R.layout.home_mod,null);
            viewHolder.imageView=(ImageView) convertView.findViewById(R.id.iv_title);
            viewHolder.name=(TextView) convertView.findViewById(R.id.tv_name);
            viewHolder.content=(TextView) convertView.findViewById(R.id.content);
            viewHolder.from= (TextView) convertView.findViewById(R.id.from);
            viewHolder.time= (TextView) convertView.findViewById(R.id.tv_time);
            viewHolder.like= (RadioButton) convertView.findViewById(R.id.love);
            viewHolder.remark= (RadioButton) convertView.findViewById(R.id.comment);
            viewHolder.send= (RadioButton) convertView.findViewById(R.id.ib_send);
            convertView.setTag(viewHolder);
        }else{
            viewHolder=(ViewHolder) convertView.getTag();
        }
        info=list.get(position);
        viewHolder.imageView.setImageResource(R.mipmap.ic_launcher);
        ImageLoad imageLoad = new ImageLoad();
        imageLoad.showImageByThead(viewHolder.imageView,info.getTitle());
        viewHolder.name.setText(info.getName());
        viewHolder.content.setText(info.getContent());
        viewHolder.from.setText(info.getFrom());
        viewHolder.time.setText(info.getTime());
//        viewHolder.send.setText(info.getSend());
//        viewHolder.remark.setText(info.getRemark());
//        viewHolder.like.setText(info.getLike());
        return convertView;
    }
    class ViewHolder{
        TextView name;
        TextView from;
        TextView content;
        ImageView imageView;
        TextView time;
        RadioButton send;
        RadioButton remark;
        RadioButton like;
    }
    }




