package com.example.zhangmiao.douban;

import java.util.Comparator;

/**
 * Created by zhangmiao on 15-11-19.
 */
public class BookGridViewItem implements Comparable{

    public String book_image;
    public String book_name;
    public int bookId;
    public String bookUrl;

    public BookGridViewItem() {
    }

    public BookGridViewItem(String book_image, String book_name,int bookId,String bookUrl) {
        this.book_image = book_image;
        this.book_name = book_name;
        this.bookId = bookId;
        this.bookUrl = bookUrl;
    }

    public String getBook_image() {
        return book_image;
    }

    public void setBook_image(String book_image) {
        this.book_image = book_image;
    }

    public String getBook_name() {
        return book_name;
    }

    public void setBook_name(String book_name) {
        this.book_name = book_name;
    }

    public int getBookId() {
        return bookId;
    }

    public void setBookId(int bookId) {
        this.bookId = bookId;
    }

    public String getBookUrl() {
        return bookUrl;
    }

    public String getBookUrl(int id) {
        if(bookId == id)
            return bookUrl;
        return null;
    }


    public void setBookUrl(String bookUrl) {
        this.bookUrl = bookUrl;
    }

    @Override
    public int compareTo(Object o) {
        BookGridViewItem b = (BookGridViewItem)o;

        if(this.bookId == b.bookId)
            return 0;
        else if(this.bookId > b.bookId)
            return 1;
        else
            return -1;
    }
}
