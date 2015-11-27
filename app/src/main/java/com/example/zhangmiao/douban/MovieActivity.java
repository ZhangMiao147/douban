package com.example.zhangmiao.douban;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by zhangmiao on 15-11-14.
 */
public class MovieActivity extends Activity {
    private String url;

    private TextView MovieTopic;
    private GridView gridView;
    private ImageView movieImage;

    private RequestQueue queue;

    private Bitmap bitmap = null;

    private HashMap<String,Object> map;


    private String movie_avatars = "";//影片导演
    private String movie_directors = "";//影片演员
    private String movie_title="";//影片名称
    private float rating_averageF;//得到平均的评分数
    private String rating_averageS;
    private String movie_image="";//影片照片的url地址
    private String movie_type="";//影片类型

    private Movie movie;

    private List<Movie> movieList = new ArrayList<Movie>();

    private boolean mContentLoaded;

    private View mContentView;

    private View mLoadingView;

    private int mShortAnimationDuration;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie_list);

        Intent intent = getIntent();
        url = intent.getStringExtra("url");

        queue = Volley.newRequestQueue(this);

        mContentView = findViewById(R.id.content_movie);
        mLoadingView = findViewById(R.id.loading_movie);

        mContentView.setVisibility(View.GONE);

        mShortAnimationDuration = 10000;
        mContentLoaded = true;

        MovieTopic =(TextView)findViewById(R.id.movie_topic);
        gridView = (GridView)findViewById(R.id.movie_gridview);


        showContentOrLoadingIndicator(mContentLoaded);

        getMovieInfor(url);
    }

    public void getMovieInfor(String url)
    {
        //tempHashMap = new HashMap<String,MovieGridViewItem>();


        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(
                url,
                new Response.Listener<JSONObject>(){
                    public void onResponse(JSONObject response)
                    {
                        //设置布局的title
                        try {
                            MovieTopic.setText(response.getString("title"));
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                        //处理每一个影片
                        JSONArray movies  = new JSONArray();
                        try {

                            movies = response.getJSONArray("subjects");
                            for(int i=0;i<movies.length();i++)
                            {

                                movie_avatars = "演员：";
                                movie_directors = "导演：";
                                movie_title = "";
                                rating_averageF = 0.0f;
                                rating_averageS="";
                                movie_image="";
                                movie_type="";


                                //获取需要的数据
                                //得到第i个影片的JSONObject
                                JSONObject movieInf = movies.getJSONObject(i);
                                //获取评分
                                JSONObject ratingMovie = movieInf.getJSONObject("rating");
                                rating_averageF = (float)ratingMovie.getDouble("average");
                                rating_averageS = ratingMovie.getString("average");

                                //获取影片类型

                                JSONArray genresMovie=movieInf.getJSONArray("genres");
                                for(int j=0;j<genresMovie.length();j++)
                                {
                                    movie_type+=genresMovie.getString(j)+ "/";
                                }

                                movie_type = movie_type.substring(0,movie_type.length()-1);

                                //获取演员信息
                                JSONArray castsMovie = new JSONArray();
                                castsMovie = movieInf.getJSONArray("casts");
                                JSONObject casts = new JSONObject();
                                JSONObject avatarsMovie = new JSONObject();
                                People avatar = new People();
                                for(int j=0;j<castsMovie.length();j++)
                                {
                                    casts = castsMovie.getJSONObject(j);
                                    avatar.setName(casts.getString("name"));
                                    movie_avatars += casts.getString("name")+'/';
                                }
                                movie_avatars= movie_avatars.substring(0,movie_avatars.length()-1);
                                //获取影片名称
                                movie_title = movieInf.getString("title");

                                //获取影片导演信息
                                JSONArray directorsMovie = new JSONArray();
                                directorsMovie = movieInf.getJSONArray("directors");
                                JSONObject directors = new JSONObject();
                                JSONObject directorsAvatars = new JSONObject();
                                People director = new People();
                                for(int j=0;j<directorsMovie.length();j++)
                                {
                                    directors = directorsMovie.getJSONObject(j);
                                    director.setName(directors.getString("name"));
                                    movie_directors += directors.getString("name")+'/';
                                }

                                movie_directors = movie_directors.substring(0, movie_directors.length() - 1);
                                //获取影片照片信息
                                JSONObject imagesMovie = new JSONObject();
                                imagesMovie = movieInf.getJSONObject("images");
                                movie_image = imagesMovie.getString("medium");
                                //填充类的信息
                                movie = new Movie();
                                movie.setAlt(movieInf.getString("alt"));
                                movie.setYear(movieInf.getString("year"));
                                movie.setTitle(movie_title);
                                movie.setImages_medium(movie_image);
                                movie.setRating_averageS(rating_averageS);
                                movie.setAvatars(movie_avatars);
                                movie.setDirectors(movie_directors);
                                movie.setRating_averageF(rating_averageF);
                                movie.setType(movie_type);

                                movieList.add(movie);

                                //监听点击
                                gridView.setOnItemClickListener(ListViewMainClickListener);
                            }
                            MovieAdapter myAdapter = new MovieAdapter(MovieActivity.this,movieList);
                            gridView.setAdapter(myAdapter);
                        }
                        catch (JSONException e)
                        {
                            e.printStackTrace();
                            new AlertDialog.Builder(MovieActivity.this)
                                    .setMessage("网络有误")
                                    .setPositiveButton("OK",null)
                                    .show();
                            finish();
                        }
                    }

                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Log.e("TAG",error.getMessage(),error);
                    }
                });
        queue.add(jsonObjectRequest);
    }

    private AdapterView.OnItemClickListener ListViewMainClickListener = new AdapterView.OnItemClickListener() {
        @Override
        public void onItemClick(AdapterView<?> arg0, View arg1, int arg2, long arg3) {
            Intent intent = new Intent();

            intent.setClass(MovieActivity.this,MovieInformation.class);
            intent.putExtra("movie",movieList.get(arg2));
            startActivity(intent);
        }
    };

    private void showContentOrLoadingIndicator(boolean contentLoaded)
    {
        final View showView = contentLoaded ? mContentView:mLoadingView;
        final View hideView = contentLoaded ? mLoadingView:mContentView;

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

}

