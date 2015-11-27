package com.example.zhangmiao.douban;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

/**
 * Created by zhangmiao on 15-11-13.
 */
public class BookInf extends Activity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book_inf);

        Intent intent = getIntent();
        Book book = (Book)intent.getSerializableExtra("book");

        TextView bookAuthor = (TextView)findViewById(R.id.book_author);
        TextView bookPublisher = (TextView)findViewById(R.id.book_publisher);
        TextView bookTranslator = (TextView)findViewById(R.id.book_translator);
        TextView bookPubdate = (TextView)findViewById(R.id.book_pubdate);
        TextView bookPages = (TextView)findViewById(R.id.book_pages);
        TextView bookPrice = (TextView)findViewById(R.id.book_prices);
        TextView bookBinding = (TextView)findViewById(R.id.book_binding);
        TextView bookIsbn10 = (TextView)findViewById(R.id.book_isbn10);


        bookAuthor.setText("作者："+book.getAuthor_all());
        bookPublisher.setText("出版社："+book.getPublisher());
        if(book.getTranslator().isEmpty())
        {
            bookTranslator.setVisibility(View.GONE);
        }
        else
        {
            bookTranslator.setText("译者："+book.getTranslator());
        }
        bookPubdate.setText("出版年："+book.getPubdate());
        bookPages.setText("页数："+book.getPages());
        bookPrice.setText("定价："+book.getPrice());
        bookBinding.setText("装幀："+book.getBinding());
        bookIsbn10.setText("isbn："+book.getIsbn10());
    }
}

