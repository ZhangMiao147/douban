package com.example.zhangmiao.douban;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Parcelable;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.Button;
import java.util.List;

/**
 * Created by zhangmiao on 15-11-25.
 */
public class ViewPagerAdapter extends PagerAdapter {

    private List<View> views;
    private Activity activity;

    private static final String SHAREDPREFERENCES_NAME = "first_pref";

    public ViewPagerAdapter(List<View> views, Activity activity) {
        this.views = views;
        this.activity = activity;
    }

    @Override
    public void destroyItem(View container, int position, Object object) {
        ((ViewPager) container).removeView(views.get(position));
    }


    @Override
    public void finishUpdate(View container) {
    }

    @Override
    public int getCount() {

        if (views != null) {
            return views.size();
        }

        return 0;
    }

    @Override
    public Object instantiateItem(View container, int position) {
        ((ViewPager) container).addView(views.get(position), 0);

        if (position == views.size() - 1) {
            Button mStartImageButton = (Button) container
                    .findViewById(R.id.enter_button);
            mStartImageButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    //设置已经引导
                    setGuided();
                    goHome();
                }
            });
        }
        return views.get(position);
    }

    private void goHome()
    {
        //跳转
        Intent intent = new Intent(activity, MainActivity.class);
        activity.startActivity(intent);
        activity.finish();
    }

    /*
    欢迎页已经引导过了，下次启动不用再次引导
     */
    private void setGuided()
    {
        SharedPreferences preferences = activity.getSharedPreferences(
                SHAREDPREFERENCES_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = preferences.edit();
        //存入数据
        editor.putBoolean("isFirstIn",false);
        //提交修改
        editor.commit();
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return (view == object);
    }

    @Override
    public void restoreState(Parcelable state, ClassLoader loader) {
    }

    @Override
    public Parcelable saveState() {
        return null;
    }

    @Override
    public void startUpdate(View container) {
    }
}
