

package com.example.zhangmiao.douban;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.GridView;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;


/**
 * Created by zhangmiao on 15-11-12.
 */
public class BookActivity extends Activity {

    private boolean mContentLoaded;

    private View mContentView;

    private View mLoadingView;

    private int mShortAnimationDuration;

    public String url = "";

    private String url1 = "https://api.douban.com/v2/book/search?q=小王子&start=10&count=1";
    private String url2 = "https://api.douban.com/v2/book/search?q=若只如初見&start=10&count=1";
    private String url3 = "https://api.douban.com/v2/book/search?q=麒麟&start=10&count=1";
    private String url4 = "https://api.douban.com/v2/book/search?q=秘密&start=10&count=1";
    private String url5 = "https://api.douban.com/v2/book/search?q=心理&start=10&count=1";
    private String url6 = "https://api.douban.com/v2/book/search?q=小时代&start=10&count=1";
    private String url7 = "https://api.douban.com/v2/book/search?q=你今天真好看&start=10&count=1";
    private String url8 = "https://api.douban.com/v2/book/search?q=追风筝的人&start=10&count=1";
    private String url9 = "https://api.douban.com/v2/book/search?q=生活与命运&start=10&count=1";
    private Book book;
    private RequestQueue queue;

    private String BookUrl=null;

    private ArrayList<BookGridViewItem> lstImageItem;
    private GridView gridview;
    private BookAdapter bookAdapter;

    private int bookID = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book_list);

        lstImageItem = new ArrayList<BookGridViewItem>();
        gridview = (GridView) findViewById(R.id.gridview_book);
        book = new Book();
        queue = Volley.newRequestQueue(this);

        mContentView = findViewById(R.id.content);
        mLoadingView = findViewById(R.id.loading_spinner);

        mContentView.setVisibility(View.GONE);

        mShortAnimationDuration = 10000;
        mContentLoaded = true;

        showContentOrLoadingIndicator(mContentLoaded);

        getBookInformation(url1);
        getBookInformation(url2);
        getBookInformation(url3);
        getBookInformation(url4);
        getBookInformation(url5);
        getBookInformation(url6);
        getBookInformation(url7);
        getBookInformation(url8);
        getBookInformation(url9);
    }

    public void getBookInformation(final String url) {

        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(url,
                new Response.Listener<JSONObject>() {
                    public void onResponse(JSONObject response) {
                        JSONArray books = new JSONArray();
                        try {

                            BookUrl = url;

                            BookGridViewItem item;
                            books = response.getJSONArray("books");
                            response = books.getJSONObject(0);
                            item = new BookGridViewItem();
                            item.book_name = response.getString("title");
                            item.book_image = response.getString("image");
                            item.bookId = bookID;
                            ++bookID;
                            item.bookUrl = BookUrl;

                            lstImageItem.add(item);
                            Collections.sort(lstImageItem,new nameComparator());
                            bookAdapter = new BookAdapter(BookActivity.this, lstImageItem);
                            gridview.setAdapter(bookAdapter);
                            bookAdapter.notifyDataSetChanged();
                            gridview.setOnItemClickListener(ListViewMainClickListener);
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                },
                new Response.ErrorListener() {
                    public void onErrorResponse(VolleyError error) {
                        Log.e("TAG", error.getMessage(), error);
                        new AlertDialog.Builder(BookActivity.this)
                                .setMessage("网络有误")
                                .setPositiveButton("OK",null)
                                .show();
                        finish();

                    }
                });
        queue.add(jsonObjectRequest);
    }

    private AdapterView.OnItemClickListener ListViewMainClickListener = new AdapterView.OnItemClickListener() {
        @Override
        public void onItemClick(AdapterView<?> arg0, View arg1, int arg2, long arg3) {
            Intent intent = new Intent();

            intent.setClass(BookActivity.this,BookInformation.class);
            intent.putExtra("url",lstImageItem.get(arg2).bookUrl);
            startActivity(intent);
        }
    };

    static class nameComparator implements Comparator
    {
        @Override
        public int compare(Object o1, Object o2) {
            BookGridViewItem b1 = (BookGridViewItem)o1;
            BookGridViewItem b2 = (BookGridViewItem)o2;

            if(b1.bookId == b2.bookId)
                return 0;
            else if(b1.bookId > b2.bookId)
                return 1;
            else
                return -1;
        }
    }

    private void showContentOrLoadingIndicator(boolean contentLoaded)
    {
        final View showView = contentLoaded ? mContentView:mLoadingView;
        final View hideView = contentLoaded ?mLoadingView:mContentView;

        showView.setAlpha(0f);
        showView.setVisibility(View.VISIBLE);

        showView.animate()
                .alpha(1f)
                .setDuration(mShortAnimationDuration)
                .setListener(null);


        hideView.animate()
                .alpha(0f)
                .setDuration(mShortAnimationDuration)
                .setListener(new AnimatorListenerAdapter() {
                    @Override
                    public void onAnimationEnd(Animator animation) {
                        hideView.setVisibility(View.GONE);
                    }
                });
    }
    public void serach_book(View view) {
        Intent intent = new Intent(this, BookInformation.class);

        EditText editText = (EditText) findViewById(R.id.edit_book_name);

        url = "https://api.douban.com/v2/book/search?q=" + editText.getText().toString() + "&start=10&count=1";
        intent.putExtra("url", url);
        startActivity(intent);
    }

    public void test(View view)
    {
        Intent intent = new Intent(this,test1.class);
        startActivity(intent);
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
