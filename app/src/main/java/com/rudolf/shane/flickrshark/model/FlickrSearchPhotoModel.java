package com.rudolf.shane.flickrshark.model;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

/**
 * Created by shane on 7/9/16.
 */
public class FlickrSearchPhotoModel {

    public PhotosModel photoes;

    public static class PhotosModel{
        public int page;
        public int pages;
        public int perpage;
        public int total;
        ArrayList<PhotoModel> photo;
    }


    public static class PhotoModel{
        public int id;
        public String title;
        @SerializedName("url_t")
        public String thumbNeilUrl;
        @SerializedName("url_c")
        public String mediumPhotoUrl;
        @SerializedName("url_l")
        public String largePhotoUrl;
        @SerializedName("url_o")
        public String originalPhotoUrl;

    }
}
