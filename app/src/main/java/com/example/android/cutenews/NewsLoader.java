package com.example.android.cutenews;

import android.content.AsyncTaskLoader;
import android.content.Context;
import android.util.Log;

import java.util.List;

/**
 * CuteNews Created by Muir on 08/06/2017.
 */

public class NewsLoader extends AsyncTaskLoader<List<News>> {

    // Tag for log messages
    private static final String LOG_TAG = NewsLoader.class.getName();

    // Query URL
    private String Url;

    /**
     * Constructs a new {@link NewsLoader}.
     * @param context of the activity
     * @param url to load data from
     */
    public NewsLoader(Context context, String url){
        super(context);
        Url = url;
    }

    @Override
    protected void onStartLoading(){
        Log.i(LOG_TAG, "TEST: onStartLoading() called...");

        forceLoad();
    }

    // This is on a background thread
    @Override
    public List<News> loadInBackground(){
        Log.i(LOG_TAG, "TEST: loadInBackground() called...");

        if (Url == null)
            return null;

        // Perform the network request, parse the response, and extract a list of news items.
        List<News> news;
        news = QueryUtils.fetchNewsData(Url);
        return news;
    }

}
