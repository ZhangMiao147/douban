package com.example.zhangmiao.douban;

import android.app.AlertDialog;
import android.app.TabActivity;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.res.Resources;
import android.support.v4.view.ViewPager;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.TabHost;
import java.util.ArrayList;

public class MainActivity extends TabActivity {

    private ViewPager pager;
    ArrayList<View> viewContainter = new ArrayList<View>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Resources res = getResources();
        TabHost tabHost = getTabHost();
        TabHost.TabSpec spec;
        Intent intent;

        //第一个Tab
        intent = new Intent(this,BookActivity.class);//新建一个Intent用作Tab1显示的内容
        spec = tabHost.newTabSpec("tab1")//新建一个Tab
                .setIndicator("图书",res.getDrawable(android.R.drawable.ic_media_play))//设置名称以及图标
                .setContent(intent);//设置显示的intent，这里的参数也可以是R.id.xx
        tabHost.addTab(spec);//添加进tabHost

        //第二个Tab
        intent = new Intent(this,MovieListMain.class);//新建一个Intent用作Tab1显示的内容
        spec = tabHost.newTabSpec("tab2")//新建一个Tab
                .setIndicator("电影",res.getDrawable(android.R.drawable.ic_media_play))//设置名称以及图标
                .setContent(intent);//设置显示的intent，这里的参数也可以是R.id.xx
        tabHost.addTab(spec);//添加进tabHost

        tabHost.setCurrentTab(0);

    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if(keyCode == KeyEvent.KEYCODE_BACK)
        {
            //创建退出对话框
            AlertDialog isExit = new AlertDialog.Builder(this).create();
            //设置对话框标题
            isExit.setTitle("系统提示");
            //设置对话框消息
            isExit.setMessage("确定要退出吗");
            //添加选择按钮并注册监听
            isExit.setButton("确定",listener);
            isExit.setButton2("取消",listener);
            //显示对话框
            isExit.show();
        }
        return false;
    }

    /**监听对话框里面的button点击事件*/
    DialogInterface.OnClickListener listener = new DialogInterface.OnClickListener() {
        public void onClick(DialogInterface dialog, int which) {
            switch(which)
            {
                case AlertDialog.BUTTON_POSITIVE://“确定”按钮退出程序
                    finish();
                    break;
                case AlertDialog.BUTTON_NEGATIVE://“取消”第二个按钮取消对话框
                    break;
                default:
                    break;
            }
        }
    };
}


