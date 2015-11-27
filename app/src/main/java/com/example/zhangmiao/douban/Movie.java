package com.example.zhangmiao.douban;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by zhangmiao on 15-11-17.
 */
public class Movie implements Serializable {
    private int rating_max; //评价的最大
    private float rating_averageF;//评价的平均分
    private String rating_averageS;
    private String rating_stars;
    private int rating_min;//评价的最小

    private String type;//影片的类型
    private String collect_count;

    //public List<People> casts;

    private String avatars;
    private String directors;

    private String title;
    private String origin_title;
    private String subtype;
    //public List<People> directors;
    private String year;
    private String images_small;
    private String images_large;
    private String images_medium;
    private String alt;
    private String id;


    public String getDirectors() {
        return directors;
    }

    public void setDirectors(String directors) {
        this.directors = directors;
    }

    public String getAvatars() {
        return avatars;
    }

    public void setAvatars(String avatars) {
        this.avatars = avatars;
    }

    public float getRating_averageF() {
        return rating_averageF;
    }

    public void setRating_averageF(float rating_averageF) {
        this.rating_averageF = rating_averageF;
    }

    public int getRating_max() {
        return rating_max;
    }

    public void setRating_max(int rating_max) {
        this.rating_max = rating_max;
    }

    public String getRating_averageS() {
        return rating_averageS;
    }

    public void setRating_averageS(String rating_averageS) {
        this.rating_averageS = rating_averageS;
    }

    public String getRating_stars() {
        return rating_stars;
    }

    public void setRating_stars(String rating_stars) {
        this.rating_stars = rating_stars;
    }

    public int getRating_min() {
        return rating_min;
    }

    public void setRating_min(int rating_min) {
        this.rating_min = rating_min;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getCollect_count() {
        return collect_count;
    }

    public void setCollect_count(String collect_count) {
        this.collect_count = collect_count;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getOrigin_title() {
        return origin_title;
    }

    public void setOrigin_title(String origin_title) {
        this.origin_title = origin_title;
    }

    public String getSubtype() {
        return subtype;
    }

    public void setSubtype(String subtype) {
        this.subtype = subtype;
    }


    public String getImages_small() {
        return images_small;
    }

    public void setImages_small(String images_small) {
        this.images_small = images_small;
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
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
}
