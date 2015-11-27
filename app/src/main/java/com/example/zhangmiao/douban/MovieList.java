package com.example.zhangmiao.douban;

/**
 * Created by zhangmiao on 15-11-16.
 */
import android.app.ExpandableListActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ExpandableListView;
import android.widget.SimpleExpandableListAdapter;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MovieList extends ExpandableListActivity{

    /*创建一级条目容器*/
    List<Map<String,String>> groups = new ArrayList<Map<String,String>>();

    List<List<Map<String,String>>>  childs = new ArrayList<List<Map<String,String>>>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.recommend_list);
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
        Map<String,String> title_1_content_4 = new HashMap<String,String>();
        Map<String,String> title_1_content_5 = new HashMap<String,String>();
        Map<String,String> title_1_content_6 = new HashMap<String,String>();
        title_1_content_1.put("child","正在热映");
        title_1_content_2.put("child","即将上映");
        title_1_content_3.put("child","Top25");
        title_1_content_4.put("child","口碑榜");
        title_1_content_5.put("child","北美票房榜");
        title_1_content_6.put("child","新片榜");


        List<Map<String,String>> childs_1 = new ArrayList<Map<String,String>>();
        childs_1.add(title_1_content_1);
        childs_1.add(title_1_content_2);
        childs_1.add(title_1_content_3);
        childs_1.add(title_1_content_4);
        childs_1.add(title_1_content_5);
        childs_1.add(title_1_content_6);

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
    public boolean setSelectedChild(int groupPosition, int childPosition, boolean shouldExpandGroup)
    {
        return super.setSelectedChild(groupPosition,childPosition,shouldExpandGroup);
    }

    @Override
    public void setSelectedGroup(int groupPosition)
    {
        super.setSelectedGroup(groupPosition);
    }
}
