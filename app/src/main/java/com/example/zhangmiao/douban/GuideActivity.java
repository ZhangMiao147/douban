package com.example.zhangmiao.douban;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by zhangmiao on 15-11-25.
 */
public class GuideActivity extends Activity implements ViewPager.OnPageChangeListener {

    private ViewPager vp;
    private ViewPagerAdapter vpAdapter;
    private List<View> views;

    //底部小点图片
    private ImageView[] dots;

    //记录当前选中位置
    private int currentIndex;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.guide);

        //初始化页面
        initViews();

        //初始化底部小点
        initDots();
    }

    private void initViews() {
        LayoutInflater inflater = LayoutInflater.from(this);

        views = new ArrayList<View>();
        //初始化欢迎界面
        views.add(inflater.inflate(R.layout.interface_view1, null));
        views.add(inflater.inflate(R.layout.interface_view2, null));
        views.add(inflater.inflate(R.layout.interface_view3, null));
        views.add(inflater.inflate(R.layout.interface_view4, null));

        //初始化Adapter
        vpAdapter = new ViewPagerAdapter(views, this);

        vp = (ViewPager) findViewById(R.id.viewpager);
        vp.setAdapter(vpAdapter);
        //绑定回调
        vp.setOnPageChangeListener(this);
    }

    private void initDots()
    {
        LinearLayout ll = (LinearLayout)findViewById(R.id.ll);

        dots = new ImageView[views.size()];

        //循环取得小点图片
        for(int i=0;i<views.size();i++)
        {
            dots[i] = (ImageView)ll.getChildAt(i);
            dots[i].setEnabled(true);
        }

        currentIndex = 0;
        dots[currentIndex].setEnabled(false);
    }

    private void setCurrentDot(int position)
    {
        if(position < 0 || position > views.size() -1
                || currentIndex == position)
        {
            return;
        }
        dots[position].setEnabled(false);
        dots[currentIndex].setEnabled(true);

        currentIndex = position;
    }

    //当滑动状态改变时调用
    @Override
    public void onPageScrollStateChanged(int state) {
    }

    //当当前页面被滑动时调用
    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

    }
    //当新的页面被选中时调用
    @Override
    public void onPageSelected(int position) {
        setCurrentDot(position);
    }

}
