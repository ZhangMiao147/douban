package com.example.zhangmiao.douban;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;
import android.webkit.WebView;
import android.webkit.WebViewClient;

/**
 * Created by zhangmiao on 15-11-26.
 */
public class MovieDetails extends Activity {
    private WebView movieDetails;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie_details);
        //得到传递过来的数据
        Intent intent = getIntent();
        String alt = intent.getStringExtra("alt");
        //设置WebView的相关属性
        movieDetails = (WebView)findViewById(R.id.movie_details);
        //如果访问的页面中有Javascript，则webview必须设置支持javascript
        movieDetails.getSettings().setJavaScriptEnabled(true);
        //加载网页
        movieDetails.loadUrl(alt);
        //如果页面中链接，如果希望点击链接继续在当前browser中响应，
        //而不是新开Android的系统browser中响应该链接，
        //必须覆盖webview的WebViewClient对象
        movieDetails.setWebViewClient(new movieAltClient());
    }

    @Override
    //设置回退，浏览网页后，点击“Back”键，回退而不是退出浏览器
    //覆盖Activity类的onKeyDown（int keyCoder,KeyEvent event）方法
    public boolean onKeyDown(int keyCode,KeyEvent event)
    {
        /*
        if((keyCode == KeyEvent.KEYCODE_BACK) && movieDetails.canGoBack())
        {
            movieDetails.goBack();
            return true;
        }
        */
        return super.onKeyDown(keyCode, event);
    }

    //

/*
    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }
*/
    //web视图
    private class movieAltClient extends WebViewClient
    {
        @Override
        public boolean shouldOverrideUrlLoading(WebView view,String url)
        {
            view.loadUrl(url);
            return true;
        }
    }

}
