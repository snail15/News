package com.example.android.news;

import android.app.LoaderManager;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Vibrator;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;

public class NewsListingActivity extends AppCompatActivity implements android.app.LoaderManager.LoaderCallbacks<List<News>>, NewsAdapter.ItemClickCallback{

    private NewsAdapter mAdapter;
    private TextView mEmptyTextView;
    private Vibrator mVibrator;
    private RecyclerView mRecyclerView;
    private List<News> mNewses;
    private static final int NEWS_LOADER_ID = 1;
    public static final String LOG_TAG = NewsListingActivity.class.getName();
    public static final String QUERY_URL = "http://content.guardianapis.com/search?show-tags=contributor&q=";
    public static final String API_KEY = "&api-key=test";
    public static String loaderURL;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news_listing);

        mEmptyTextView = (TextView)findViewById(R.id.empty_view);
        mVibrator = (Vibrator)getSystemService(Context.VIBRATOR_SERVICE);
        loaderURL = QUERY_URL+MainActivity.getKeyword()+API_KEY;

        android.app.LoaderManager loaderManager = getLoaderManager();
        loaderManager.initLoader(NEWS_LOADER_ID, null, this);

        mRecyclerView = (RecyclerView) findViewById(R.id.list);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));

    }
    @Override
    public android.content.Loader<List<News>> onCreateLoader(int i, Bundle bundle) {
        return new NewsLoader(this,loaderURL);
    }

    @Override
    public void onLoadFinished(android.content.Loader<List<News>> loader, List<News> newses) {
        //vibrate when finish loading!!
        mVibrator.vibrate(500);
        if (newses != null && !newses.isEmpty()){
            mNewses = newses;
            mAdapter = new NewsAdapter(newses,this);
            mAdapter.setItemClickCallback(this);
            mRecyclerView.setAdapter(mAdapter);
        }
    }

    @Override
    public void onLoaderReset(android.content.Loader<List<News>> loader) {
        mAdapter = null;
    }

    @Override
    public void onItemClick(int p) {
        News currentNews = mNewses.get(p);
        Uri newsUri = Uri.parse(currentNews.getUrl());
        //Create a new intent to view the earthquake URI
        Intent websiteIntent = new Intent(Intent.ACTION_VIEW, newsUri);
        System.out.print("Clicked");
        startActivity(websiteIntent);
    }
}
