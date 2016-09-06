package com.example.anzhuo.weibo20.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.anzhuo.weibo20.R;

/**
 * Created by anzhuo on 2016/8/24.
 */
public class MsgFragment extends Fragment{
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
         super.onCreateView(inflater, container, savedInstanceState);
        View view=inflater.inflate(R.layout.msg,container,false);
        return view;
    }
}
