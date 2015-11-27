package com.example.zhangmiao.douban;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

/**
 * Created by zhangmiao on 15-11-26.
 */
public class MovieInf extends Activity{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie_inf);

        //得到传递过来的对象
        Intent intent = getIntent();
        Movie movie = (Movie)intent.getSerializableExtra("movie");

        //得到组件
        TextView movieAvatar = (TextView)findViewById(R.id.movie_avatar_inf);
        TextView movieDirector = (TextView)findViewById(R.id.movie_director_inf);
        TextView movieYear = (TextView)findViewById(R.id.movie_year_inf);
        TextView movieType = (TextView)findViewById(R.id.movie_type_inf);

        //给组件设置内容
        movieAvatar.setText(movie.getAvatars());
        movieDirector.setText(movie.getDirectors());
        movieYear.setText("上映："+movie.getYear());
        movieType.setText("类型："+movie.getType());
    }
}
