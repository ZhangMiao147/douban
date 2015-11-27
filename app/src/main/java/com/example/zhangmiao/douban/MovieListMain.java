package com.example.zhangmiao.douban;


import android.app.AlertDialog;
import android.app.ExpandableListActivity;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.ExpandableListView;
import android.widget.SimpleExpandableListAdapter;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * Created by zhangmiao on 15-11-12.
 */
public class MovieListMain extends ExpandableListActivity{

    /*创建一级条目容器*/

    List<Map<String,String>> groups = new ArrayList<Map<String,String>>();

    List<List<Map<String,String>>>  childs = new ArrayList<List<Map<String,String>>>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie_main_list);
        setListData();
    }

    public void setListData()
    {
        Map<String,String> title_1 = new HashMap<String,String>();
        Map<String,String> title_2 = new HashMap<String,String>();
        Map<String,String> title_3 = new HashMap<String,String>();
        title_1.put("group","榜单");
        title_2.put("group","著名导演");
        title_3.put("group","电影类型");
        groups.add(title_1);
        groups.add(title_2);
        groups.add(title_3);

        Map<String,String> title_1_content_1 = new HashMap<String,String>();
        Map<String,String> title_1_content_2 = new HashMap<String,String>();
        Map<String,String> title_1_content_3 = new HashMap<String,String>();
        title_1_content_1.put("child","正在热映");
        title_1_content_2.put("child","即将上映");
        title_1_content_3.put("child","Top25");


        List<Map<String,String>> childs_1 = new ArrayList<Map<String,String>>();
        childs_1.add(title_1_content_1);
        childs_1.add(title_1_content_2);
        childs_1.add(title_1_content_3);

        Map<String,String> title_2_content_1 = new HashMap<String,String>();
        Map<String,String> title_2_content_2 = new HashMap<String,String>();
        Map<String,String> title_2_content_3 = new HashMap<String,String>();
        Map<String,String> title_2_content_4 = new HashMap<String,String>();
        Map<String,String> title_2_content_5 = new HashMap<String,String>();
        Map<String,String> title_2_content_6 = new HashMap<String,String>();
        title_2_content_1.put("child","张艺谋");
        title_2_content_2.put("child","李安");
        title_2_content_3.put("child","吴宇森");
        title_2_content_4.put("child","王家卫");
        title_2_content_5.put("child","陈凯歌");
        title_2_content_6.put("child","詹姆斯.卡梅隆");

        List<Map<String,String>> childs_2 = new ArrayList<Map<String,String>>();
        childs_2.add(title_2_content_1);
        childs_2.add(title_2_content_2);
        childs_2.add(title_2_content_3);
        childs_2.add(title_2_content_4);
        childs_2.add(title_2_content_5);
        childs_2.add(title_2_content_6);

        Map<String,String> title_3_content_1 = new HashMap<String,String>();
        Map<String,String> title_3_content_2 = new HashMap<String,String>();
        Map<String,String> title_3_content_3 = new HashMap<String,String>();
        Map<String,String> title_3_content_4 = new HashMap<String,String>();
        Map<String,String> title_3_content_5 = new HashMap<String,String>();
        Map<String,String> title_3_content_6 = new HashMap<String,String>();
        title_3_content_1.put("child","喜剧");
        title_3_content_2.put("child","爱情");
        title_3_content_3.put("child","动作");
        title_3_content_4.put("child","惊悚");
        title_3_content_5.put("child","古装");
        title_3_content_6.put("child","青春");

        List<Map<String,String>> childs_3 = new ArrayList<Map<String,String>>();
        childs_3.add(title_3_content_1);
        childs_3.add(title_3_content_2);
        childs_3.add(title_3_content_3);
        childs_3.add(title_3_content_4);
        childs_3.add(title_3_content_5);
        childs_3.add(title_3_content_6);

        childs.add(childs_1);
        childs.add(childs_2);
        childs.add(childs_3);

        SimpleExpandableListAdapter  sela = new SimpleExpandableListAdapter(
                this,
                groups,
                R.layout.groups,
                new String[] {"group"},
                new int[]{R.id.textGroup},
                childs,
                R.layout.childs,
                new String[] {"child"},
                new int[] {R.id.textChild}
        );
        setListAdapter(sela);

    }

    @Override
    public boolean onChildClick(ExpandableListView parent, View v,
                                int groupPosition, int childPosition, long id)
    {
        Intent intent = new Intent();
        switch (groupPosition) {
            case 0:
                switch (childPosition) {
                    case 0:
                        intent.setClass(this, MovieActivity.class);
                        intent.putExtra("url", "https://api.douban.com/v2/movie/in_theaters");
                        startActivity(intent);
                        break;
                    case 1:
                        intent.setClass(this, MovieActivity.class);
                        intent.putExtra("url", "https://api.douban.com/v2/movie/coming_soon");
                        startActivity(intent);
                        break;
                    case 2:
                        intent.setClass(this, MovieActivity.class);
                        intent.putExtra("url", "https://api.douban.com/v2/movie/top250");
                        startActivity(intent);
                        break;
                }
                break;
            case 1:
                switch (childPosition) {
                    case 0:
                        intent.setClass(this, MovieActivity.class);
                        intent.putExtra("url", "https://api.douban.com/v2/movie/search?q=张艺谋");
                        startActivity(intent);
                        break;
                    case 1:
                        intent.setClass(this, MovieActivity.class);
                        intent.putExtra("url", "https://api.douban.com/v2/movie/search?q=李安");
                        startActivity(intent);
                        break;
                    case 2:
                        intent.setClass(this, MovieActivity.class);
                        intent.putExtra("url", "https://api.douban.com/v2/movie/search?q=吴宇森");
                        startActivity(intent);
                        break;
                    case 3:
                        intent.setClass(this, MovieActivity.class);
                        intent.putExtra("url", "https://api.douban.com/v2/movie/search?q=王家卫");
                        startActivity(intent);
                        break;
                    case 4:
                        intent.setClass(this, MovieActivity.class);
                        intent.putExtra("url", "https://api.douban.com/v2/movie/search?q=陈凯歌");
                        startActivity(intent);
                        break;
                    case 5:
                        intent.setClass(this, MovieActivity.class);
                        intent.putExtra("url", "https://api.douban.com/v2/movie/search?q=詹姆斯卡梅隆");
                        startActivity(intent);
                        break;
                }
            case 2:
                switch (childPosition) {
                    case 0:
                        intent.setClass(this, MovieActivity.class);
                        intent.putExtra("url", "https://api.douban.com/v2/movie/search?tag=喜剧");
                        startActivity(intent);
                        break;
                    case 1:
                        intent.setClass(this, MovieActivity.class);
                        intent.putExtra("url", "https://api.douban.com/v2/movie/search?tag=爱情");
                        startActivity(intent);
                        break;
                    case 2:
                        intent.setClass(this, MovieActivity.class);
                        intent.putExtra("url", "https://api.douban.com/v2/movie/search?tag=动作");
                        startActivity(intent);
                        break;
                    case 3:
                        intent.setClass(this, MovieActivity.class);
                        intent.putExtra("url", "https://api.douban.com/v2/movie/search?tag=惊悚");
                        startActivity(intent);
                        break;
                    case 4:
                        intent.setClass(this, MovieActivity.class);
                        intent.putExtra("url", "https://api.douban.com/v2/movie/search?tag=古装");
                        startActivity(intent);
                        break;
                    case 5:
                        intent.setClass(this, MovieActivity.class);
                        intent.putExtra("url", "https://api.douban.com/v2/movie/search?tag=青春");
                        startActivity(intent);
                        break;
                }
        }
        return super.onChildClick(parent,v,groupPosition,childPosition,id);
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