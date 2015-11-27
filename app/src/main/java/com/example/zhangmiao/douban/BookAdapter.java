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
import android.widget.TextView;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;
import java.util.List;

/**
 * Created by zhangmiao on 15-11-19.
 */
public class BookAdapter extends BaseAdapter{
    //private Book book;
    //private List<HashMap<String,Book>> list;
    private BookGridViewItem gridViewItem;
    private List<BookGridViewItem> list;
    private LayoutInflater layoutInflater;

    public BookAdapter(Context context,List<BookGridViewItem> list) {
        layoutInflater = LayoutInflater.from(context);
        this.list = list;
    }
    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int i) {
        return i;
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup viewGroup) {
        StrictMode.setThreadPolicy(new StrictMode.ThreadPolicy.Builder()
                .detectDiskReads().detectDiskWrites().detectNetwork()
                .penaltyLog().build());
        StrictMode.setVmPolicy(new StrictMode.VmPolicy.Builder()
                .detectLeakedSqlLiteObjects().detectLeakedClosableObjects()
                .penaltyLog().penaltyDeath().build());

        View view = null;
        if(layoutInflater != null)
        {
            view = layoutInflater.inflate(R.layout.pho_list,null);

            ImageView imageView = (ImageView)view.findViewById(R.id.book_item_image);

            TextView textView = (TextView)view.findViewById(R.id.book_item_name);
            gridViewItem = list.get(position);
            String image = gridViewItem.book_image;
            String title = gridViewItem.book_name;

            imageView.setImageBitmap(returnBitMap(image));
            textView.setText(title);
        }
        return view;
    }

    public Bitmap returnBitMap(String url)
    {
        URL myFileUrl = null;
        Bitmap bitmap = null;
        try {
            myFileUrl = new URL(url);

            HttpURLConnection conn = (HttpURLConnection) myFileUrl.openConnection();
            conn.setDoInput(true);
            conn.connect();
            InputStream is = conn.getInputStream();
            bitmap = BitmapFactory.decodeStream(is);
            is.close();

        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        catch(IOException e)
        {
            e.printStackTrace();
        }
        return bitmap;
    }
}
