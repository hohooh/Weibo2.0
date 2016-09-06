package com.example.anzhuo.weibo20.activity;

import android.graphics.drawable.BitmapDrawable;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageButton;
import android.widget.PopupWindow;
import android.widget.RadioGroup;

import com.example.anzhuo.weibo20.R;
import com.example.anzhuo.weibo20.fragment.HomeFragment;
import com.example.anzhuo.weibo20.fragment.MineFragment;
import com.example.anzhuo.weibo20.fragment.MsgFragment;
import com.example.anzhuo.weibo20.fragment.SearchFragment;
import com.example.anzhuo.weibo20.mypopwindow.MypopWindows;
import com.example.anzhuo.weibo20.pageadapter.PageAdapter;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity  {
   ViewPager viewPager;
    RadioGroup radioGroup;
    HomeFragment homeFragment;
    MineFragment mineFragment;
    MsgFragment msgFragment;
    SearchFragment searchFragment;
    List<Fragment> list;
    PageAdapter pageAdapter;
    ImageButton add;
    MypopWindows mypop;
   SwipeRefreshLayout swipeRefreshLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        viewPager= (ViewPager) findViewById(R.id.vp_viewPager);
        radioGroup= (RadioGroup) findViewById(R.id.rg_radioGroup);
        add= (ImageButton) findViewById(R.id.ib_add);

        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                mypop=new MypopWindows(MainActivity.this,itemsOnClick);
                //显示窗口
                mypop.showAtLocation(MainActivity.this.findViewById(R.id.ib_add), Gravity.BOTTOM| Gravity.CENTER_HORIZONTAL, 0, 0); //设置layout在PopupWindow中显示的位置
            }
        });

        list=new ArrayList<>();
        homeFragment=new HomeFragment();
        msgFragment=new MsgFragment();
        searchFragment=new SearchFragment();
        mineFragment=new MineFragment();

        list.add(homeFragment);
        list.add(msgFragment);
        list.add(searchFragment);
        list.add(mineFragment);

        //适配适配器
        pageAdapter=new PageAdapter(getSupportFragmentManager(),list);
        viewPager.setAdapter(pageAdapter);
        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                switch (i){
                    case R.id.rb_home:
                        viewPager.setCurrentItem(0);
                        break;
                    case R.id.rb_msg:
                        viewPager.setCurrentItem(1);
                        break;
                    case R.id.rb_search:
                        viewPager.setCurrentItem(2);
                        break;
                    case R.id.rb_mine:
                        viewPager.setCurrentItem(3);
                        break;
                }
            }
        });






        viewPager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                 switch (position){
                     case 0:
                         radioGroup.check(R.id.rb_home);
                         break;
                     case 1:
                         radioGroup.check(R.id.rb_msg);
                         break;
                     case 2:
                         radioGroup.check(R.id.rb_search);
                         break;
                     case 3:
                         radioGroup.check(R.id.rb_mine);
                         break;
                 }
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }
    //为弹出窗口实现监听类
    private View.OnClickListener itemsOnClick = new View.OnClickListener(){

        public void onClick(View v) {
            mypop.dismiss();
            switch (v.getId()) {
                case R.id.ib_cancel:
                    break;

                default:
                    break;
            }


        }

    };


}

