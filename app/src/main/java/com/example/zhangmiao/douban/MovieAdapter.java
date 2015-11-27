package com.example.zhangmiao.douban;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.StrictMode;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;
import java.util.List;

/**
 * Created by zhangmiao on 15-11-18.
 */
public class MovieAdapter extends BaseAdapter{

    private List<Movie> list;
    private LayoutInflater layoutInflater;
    private Movie movie;

    /*
    public MovieAdapter(Context context, List<HashMap<String, MovieGridViewItem>> list) {
        this.list = list;
        layoutInflater = LayoutInflater.from(context);
    }
    */
    public MovieAdapter(Context context, List<Movie> list) {
        this.list = list;
        layoutInflater = LayoutInflater.from(context);
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int position) {
        return list.get(position);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        StrictMode.setThreadPolicy(new StrictMode.ThreadPolicy.Builder()
                .detectDiskReads().detectDiskWrites().detectNetwork()
                .penaltyLog().build());
        StrictMode.setVmPolicy(new StrictMode.VmPolicy.Builder()
                .detectLeakedSqlLiteObjects().detectLeakedClosableObjects()
                .penaltyLog().penaltyDeath().build());


        View view = null;
        if(layoutInflater != null) {
            view = layoutInflater.inflate(R.layout.movie_list,null);

            ImageView imageView = (ImageView)view.findViewById(R.id.movie_image);

            TextView title = (TextView)view.findViewById(R.id.movie_title);

            TextView avatars = (TextView)view.findViewById(R.id.movie_avatar);

            TextView director = (TextView)view.findViewById(R.id.movie_director);

            TextView average = (TextView)view.findViewById(R.id.movie_average);

            RatingBar ratingBar = (RatingBar)view.findViewById(R.id.movie_ratingBar);

            movie = list.get(position);
            title.setText(movie.getTitle());
            ratingBar.setRating(movie.getRating_averageF()/2);
            avatars.setText(movie.getAvatars());
            director.setText(movie.getDirectors());
            average.setText(movie.getRating_averageS());
            imageView.setImageBitmap(BitmapUtils.returnBitMap(movie.getImages_medium()));

        }
        return view;
    }
}
