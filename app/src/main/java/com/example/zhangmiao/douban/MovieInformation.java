package com.example.zhangmiao.douban;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.text.TextUtils;
import android.text.method.LinkMovementMethod;
import android.view.KeyEvent;
import android.view.View;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * Created by zhangmiao on 15-11-25.
 */
public class MovieInformation extends Activity{
    //private Boolean flag = true;
    private Movie movie;

    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie_information);

        //得到传递过来的对象
        Intent intent = getIntent();
        movie = (Movie)intent.getSerializableExtra("movie");

        //得到组件
        ImageView movieImage = (ImageView)findViewById(R.id.movie_image);
        TextView movieTitle = (TextView)findViewById(R.id.movie_title);
        RatingBar movieRatingBar = (RatingBar)findViewById(R.id.movie_ratingBar);
        TextView movieRatingAverage = (TextView)findViewById(R.id.movie_rating_average);
        TextView movieInf = (TextView)findViewById(R.id.movie_inf);

        //给组件设置内容
        String movieImageUrl = movie.getImages_medium();
        movieImage.setImageBitmap(BitmapUtils.returnBitMap(movieImageUrl));
        movieTitle.setText(movie.getTitle());
        movieRatingBar.setRating(movie.getRating_averageF() / 2);
        movieRatingAverage.setText(movie.getRating_averageS());

        String information = movie.getDirectors() + " " +
                movie.getAvatars() + "\n"
                +movie.getYear();
        movieInf.setText(information);

    }


    //点击“查看详情”时的点击事件
    public void movieDetals(View view)
    {
        Intent intent = new Intent(this,MovieDetails.class);
        intent.putExtra("alt",movie.getAlt());
        startActivity(intent);
    }

    //点击导演信息时的点击事件
    public void onclickmovieInf(View view)
    {
        Intent intent = new Intent(this,MovieInf.class);
        intent.putExtra("movie",movie);
        startActivity(intent);
    }
}
