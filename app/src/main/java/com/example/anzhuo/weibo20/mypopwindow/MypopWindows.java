package com.example.anzhuo.weibo20.mypopwindow;

import android.app.Activity;
import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.TextView;

import com.example.anzhuo.weibo20.R;

/**
 * Created by anzhuo on 2016/9/1.
 */
public class MypopWindows extends PopupWindow implements View.OnClickListener {


    private ImageButton Cancel;
    private View mMenuView;

    public MypopWindows(Activity context, View.OnClickListener itemsOnClick) {
        super(context);
        LayoutInflater inflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        mMenuView = inflater.inflate(R.layout.add, null);
        mMenuView.setOnClickListener(this);
        Cancel = (ImageButton) mMenuView.findViewById(R.id.ib_cancel);
        Cancel.setOnClickListener(this);
        //设置SelectPicPopupWindow的View
        setContentView(mMenuView);
        //设置SelectPicPopupWindow弹出窗体的宽
        setWidth(LinearLayout.LayoutParams.MATCH_PARENT);
//        //设置SelectPicPopupWindow弹出窗体的高
        setHeight(LinearLayout.LayoutParams.MATCH_PARENT);
        ColorDrawable dw = new ColorDrawable(0x00000000);
        setBackgroundDrawable(dw);
        setFocusable(true);
        setAnimationStyle(R.style.popwindow_anim_style);
    }

    @Override
    public void onClick(View v) {
        dismiss();
    }
}

