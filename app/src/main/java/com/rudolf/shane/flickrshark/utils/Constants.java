package com.rudolf.shane.flickrshark.utils;

/**
 * Created by shane on 7/9/16.
 */
public abstract class Constants {
    private Constants(){}
    public static final String URL_BASE = "https://api.flickr.com/";//separate domain for easy switching environment
    public static final String API_KEY = "949e98778755d1982f537d56236bbb42";
    public static final String FLICKR_SEARCH_URL = URL_BASE + "services/rest/?method=flickr.photos.search&api_key=" + API_KEY + "&text=shark&format=json&nojsoncallback=1&page=1&extras=url_t,url_c,url_l,url_o";
}
