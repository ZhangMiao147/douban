package com.example.zhangmiao.douban;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.ImageRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by zhangmiao on 15-11-14.
 */
public class BookInformation extends Activity {
    public Book book = new Book();

    private static final String TAG = "com.lms.volleydemo.JsonActivity";

    private RequestQueue queue;
    private String url;

    private TextView bookSum,bookInf,bookTitle,bookRatingAverage,bookRatingNum,bookIntr;
    private RatingBar bookRatingBar;
    private ImageView bookImage;
    private Boolean flag = true;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book_information);

        Intent intent = this.getIntent();

        url = (String)intent.getStringExtra("url");

        queue = Volley.newRequestQueue(this);
        bookSum = (TextView) findViewById(R.id.book_summary);
        bookInf = (TextView) findViewById(R.id.book_Inf);
        bookTitle = (TextView) findViewById(R.id.book_title);
        bookRatingBar = (RatingBar) findViewById(R.id.book_ratingBar);
        bookRatingAverage = (TextView) findViewById(R.id.book_rating_average);
        bookRatingNum = (TextView) findViewById(R.id.book_rating_numRater);
        bookImage = (ImageView) findViewById(R.id.book_image);

        bookIntr = (TextView)findViewById(R.id.book_intr);

        getBookInformation(url);
    }
    public void getBookImage(String bookImageUrl)
    {
        ImageRequest imageRequest = new ImageRequest(
                bookImageUrl,
                new Response.Listener<Bitmap>() {
                    @Override
                    public void onResponse(Bitmap response) {
                        bookImage.setImageBitmap(response);
                    }
                }, 0, 0, Bitmap.Config.RGB_565, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                bookImage.setImageResource(R.drawable.ic_launcher);
            }
        });
        queue.add(imageRequest);
    }

    public void getBookInformation(String bookUrl)
    {
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(bookUrl,
                new Response.Listener<JSONObject>() {
                    public void onResponse(JSONObject response) {
                        JSONArray books = new JSONArray();

                        String bookAuthor = "";
                        String bookInformation = "";
                        try {
                            book.setSummary(response.getString("summary"));

                            bookSum.setText(book.getSummary());
                            JSONArray author_book = new JSONArray();
                            author_book = response.getJSONArray("author");
                            book.setAuthor_first(author_book.getString(0));

                            for(int i=0;i<author_book.length();i++)
                            {
                                bookAuthor += author_book.getString(i);
                            }
                            book.setAuthor_all(bookAuthor);
                            book.setTitle(response.getString("title"));
                            bookTitle.setText(book.getTitle());
                            JSONObject rating_book = response.getJSONObject("rating");

                            book.setRating_averageF((float) rating_book.getDouble("average"));
                            book.setRating_averageS(rating_book.getString("average"));
                            book.setRating_numRaters(rating_book.getString("numRaters"));

                            bookRatingBar.setRating(book.getRating_averageF() / 2);
                            bookRatingAverage.setText(book.getRating_averageS());
                            bookRatingNum.setText(book.getRating_numRaters() + "人评价");

                            book.setImage(response.getString("image"));
                            getBookImage(book.getImage());
                            book.setPublisher(response.getString("publisher"));
                            book.setPubdate(response.getString("pubdate"));
                            bookInformation += book.getAuthor_first() + '/' + book.getPublisher() + '/' + book.getPubdate();
                            bookInf.setText(bookInformation);
                            book.setPrice(response.getString("price"));
                            String bookTranslator="";
                            JSONArray translator_book = new JSONArray();
                            translator_book = response.getJSONArray("translator");
                            for(int i=0;i<author_book.length();i++)
                            {
                                bookTranslator += translator_book.getString(i) + "  ";
                            }
                            book.setTranslator(bookTranslator);
                            book.setSubtitle(response.getString("subtitle"));
                            book.setOrigin_title(response.getString("origin_title"));
                            book.setBinding(response.getString("binding"));
                            book.setCatalog(response.getString("catalog"));
                            book.setPages(response.getString("pages"));
                            book.setAlt(response.getString("alt"));
                            book.setId(response.getString("id"));
                            book.setIsbn10(response.getString("isbn10"));
                            book.setIsbn13(response.getString("isbn13"));
                            book.setAlt_title(response.getString("alt_title"));
                            book.setAuthor_intro(response.getString("author_intro"));

                        }catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                },
                new Response.ErrorListener() {
                    public void onErrorResponse(VolleyError error) {
                        Log.e("TAG", error.getMessage(), error);
                    }
                });
        queue.add(jsonObjectRequest);
    }

    public void onclickbookInf(View view)
    {
        Intent intent = new Intent(this,BookInf.class);
        intent.putExtra("book",book);
        startActivity(intent);
    }

    public void packUp(View view)
    {
        TextView textView = (TextView)findViewById(R.id.book_summary);
        TextView PackUp = (TextView)findViewById(R.id.book_packup);
        if(flag)
        {
            flag = false;
            textView.setEllipsize(null);
            textView.setSingleLine(flag);
            PackUp.setText("收起");
        }
        else
        {
            flag = true;
            textView.setLines(3);
            textView.setEllipsize(TextUtils.TruncateAt.END);
            PackUp.setText("更多");
        }
    }
}
