package com.example.zhangmiao.douban;
/**
 * 书籍信息类
 */


import android.util.Log;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.Serializable;

/**
 * Created by zhangmiao on 15-11-13.
 */
public class Book implements Serializable {
    private int bookId;
    private String bookUrl;

    public String getBookUrl() {
        return bookUrl;
    }

    public void setBookUrl(String bookUrl) {
        this.bookUrl = bookUrl;
    }

    private int rating_max; //评价的最大
    private String rating_numRaters;//评价的人数
    private float rating_averageF;//评价的平均分
    private String rating_averageS;
    private int rating_min;//评价的最小

    private String subtitle;//子标题
    private String author_all;//所有作者
    private String author_first;//第一个作者
    private String pubdate;//出版时间
    private String origin_title;
    private String image;
    private String binding;
    private String translator;
    private String catalog;
    private String pages;
    private String images_small;
    private String images_large;
    private String images_medium;
    private String alt;
    private String id;
    private String publisher;
    private String isbn10;
    private String isbn13;
    private String title;
    private String url;
    private String alt_title;
    private String author_intro;
    private String summary;
    private String price;


    public int getBookId() {
        return bookId;
    }

    public void setBookId(int bookId) {
        this.bookId = bookId;
    }


    public int getRating_max() {
        return rating_max;
    }

    public void setRating_max(int rating_max) {
        this.rating_max = rating_max;
    }

    public String getRating_numRaters() {
        return rating_numRaters;
    }

    public void setRating_numRaters(String rating_numRaters) {
        this.rating_numRaters = rating_numRaters;
    }

    public float getRating_averageF() {
        return rating_averageF;
    }

    public void setRating_averageF(float rating_average) {
        this.rating_averageF = rating_average;
    }


    public String getRating_averageS() {
        return rating_averageS;
    }

    public void setRating_averageS(String rating_average) {
        this.rating_averageS = rating_average;
    }

    public int getRating_min() {
        return rating_min;
    }

    public void setRating_min(int rating_min) {
        this.rating_min = rating_min;
    }

    public String getSubtitle() {
        return subtitle;
    }

    public void setSubtitle(String subtitle) {
        this.subtitle = subtitle;
    }

    public String getAuthor_all() {
        return author_all;
    }

    public void setAuthor_all(String author_all) {
        this.author_all = author_all;
    }

    public String getAuthor_first() {
        return author_first;
    }

    public void setAuthor_first(String author_first) {
        this.author_first = author_first;
    }

    public String getPubdate() {
        return pubdate;
    }

    public void setPubdate(String pubdate) {
        this.pubdate = pubdate;
    }

    public String getOrigin_title() {
        return origin_title;
    }

    public void setOrigin_title(String origin_title) {
        this.origin_title = origin_title;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getBinding() {
        return binding;
    }

    public void setBinding(String binding) {
        this.binding = binding;
    }

    public String getTranslator() {
        return translator;
    }

    public void setTranslator(String translator) {
        this.translator = translator;
    }

    public String getCatalog() {
        return catalog;
    }

    public void setCatalog(String catalog) {
        this.catalog = catalog;
    }

    public String getPages() {
        return pages;
    }

    public void setPages(String pages) {
        this.pages = pages;
    }

    public String getImages_small() {
        return images_small;
    }

    public void setImages_small(String images_small) {
        this.images_small = images_small;
    }

    public String getImages_large() {
        return images_large;
    }

    public void setImages_large(String images_large) {
        this.images_large = images_large;
    }

    public String getImages_medium() {
        return images_medium;
    }

    public void setImages_medium(String images_medium) {
        this.images_medium = images_medium;
    }

    public String getAlt() {
        return alt;
    }

    public void setAlt(String alt) {
        this.alt = alt;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getPublisher() {
        return publisher;
    }

    public void setPublisher(String publisher) {
        this.publisher = publisher;
    }

    public String getIsbn10() {
        return isbn10;
    }

    public void setIsbn10(String isbn10) {
        this.isbn10 = isbn10;
    }

    public String getIsbn13() {
        return isbn13;
    }

    public void setIsbn13(String isbn13) {
        this.isbn13 = isbn13;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getAlt_title() {
        return alt_title;
    }

    public void setAlt_title(String alt_title) {
        this.alt_title = alt_title;
    }

    public String getAuthor_intro() {
        return author_intro;
    }

    public void setAuthor_intro(String author_intro) {
        this.author_intro = author_intro;
    }

    public String getSummary() {
        return summary;
    }

    public void setSummary(String summary) {
        this.summary = summary;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }
}
