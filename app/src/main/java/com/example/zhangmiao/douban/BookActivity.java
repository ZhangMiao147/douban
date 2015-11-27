

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
import java.util.logging.Handler;
import java.util.logging.LogRecord;


/**
 * Created by zhangmiao on 15-11-12.
 */
public class BookActivity extends Activity {

    private boolean mContentLoaded;

    private View mContentView;

    private View mLoadingView;

    private int mShortAnimationDuration;

    public String mainUrl = "https://api.douban.com/v2/book/search?q=小&start=0&count=9";

    private Book book;

    private RequestQueue queue;

    private String BookUrl = null;


    private ArrayList<Book> lstImageItem;
    private GridView gridview;
    private BookAdapter bookAdapter;

    private int bookID = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book_list);

        lstImageItem = new ArrayList<Book>();
        gridview = (GridView) findViewById(R.id.gridview_book);

        queue = Volley.newRequestQueue(this);

        mContentView = findViewById(R.id.content);
        mLoadingView = findViewById(R.id.loading_spinner);

        mContentView.setVisibility(View.GONE);

        mShortAnimationDuration = 10000;
        mContentLoaded = true;

        showContentOrLoadingIndicator(mContentLoaded);

        getBookInformation(mainUrl);
    }

    public void getBookInformation(final String url) {

        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(url,
                new Response.Listener<JSONObject>() {
                    public void onResponse(JSONObject response) {
                        JSONArray books = new JSONArray();
                        try {

                            BookUrl = url;
                            books = response.getJSONArray("books");
                            for(int i=0;i<books.length();i++) {
                                JSONObject res = books.getJSONObject(i);
                                book = new Book();
                                book.setBookUrl(BookUrl);
                                book.setTitle(res.getString("title"));
                                book.setImage(res.getString("image"));
                                book.setId(res.getString("id"));
                                book.setBookId(bookID);
                                ++bookID;
                                lstImageItem.add(book);
                            }
                                Collections.sort(lstImageItem, new nameComparator());

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
                        new AlertDialog.Builder(BookActivity.this)
                                .setMessage("网络有误")
                                .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialogInterface, int i) {
                                        finish();
                                    }
                                })
                                .setNegativeButton("取消", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialogInterface, int i) {

                                    }
                                })
                                .show();
                    }
                });
        queue.add(jsonObjectRequest);
    }

    private AdapterView.OnItemClickListener ListViewMainClickListener = new AdapterView.OnItemClickListener() {
        @Override
        public void onItemClick(AdapterView<?> arg0, View arg1, int arg2, long arg3) {
            Intent intent = new Intent();

            intent.setClass(BookActivity.this, BookInformation.class);
            String url = "https://api.douban.com/v2/book/"+lstImageItem.get(arg2).getId();
            intent.putExtra("url", url);
            startActivity(intent);
        }
    };

    static class nameComparator implements Comparator {
        @Override
        public int compare(Object o1, Object o2) {
            Book b1 = (Book) o1;
            Book b2 = (Book) o2;

            if (b1.getBookId() == b2.getBookId()) {
                return 0;
            } else {
                if (b1.getBookId() > b2.getBookId()) {
                    return 1;
                } else {
                    return -1;
                }
            }
        }
    }

    private void showContentOrLoadingIndicator(boolean contentLoaded) {
        final View showView = contentLoaded ? mContentView : mLoadingView;
        final View hideView = contentLoaded ? mLoadingView : mContentView;

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

        EditText editText = (EditText) findViewById(R.id.edit_book_name);
        lstImageItem.clear();
        String url = "https://api.douban.com/v2/book/search?q=" + editText.getText().toString() + "&start=0&count=3";
        getBookInformation(url);
    }

    public void test(View view) {
        Intent intent = new Intent(this, test1.class);
        startActivity(intent);
    }

    /*监听退出键，在退出时询问是否确定退出*/
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            //创建退出对话框
            AlertDialog isExit = new AlertDialog.Builder(this).create();
            //设置对话框标题
            isExit.setTitle("系统提示");
            //设置对话框消息
            isExit.setMessage("确定要退出吗");
            //添加选择按钮并注册监听
            isExit.setButton("确定", listener);
            isExit.setButton2("取消", listener);
            //显示对话框
            isExit.show();
        }
        return false;
    }

    /**
     * 监听对话框里面的button点击事件
     */
    DialogInterface.OnClickListener listener = new DialogInterface.OnClickListener() {
        public void onClick(DialogInterface dialog, int which) {
            switch (which) {
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
