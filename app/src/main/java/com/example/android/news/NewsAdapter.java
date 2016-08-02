package com.example.android.news;

import android.app.Activity;
import android.content.Context;
import android.graphics.drawable.GradientDrawable;
import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by OWNER on 7/21/2016.
 */
public class NewsAdapter extends ArrayAdapter<News> {

    public NewsAdapter(Activity context, List<News> newses) {
        super(context, 0, newses);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View listItemView = convertView;
        if (listItemView == null) {
            listItemView = LayoutInflater.from(getContext()).inflate(R.layout.news_list, parent, false);
        }

        News currentNews = getItem(position);
        int backgroundColorID = 0;

        if (position % 2 != 0) {
            backgroundColorID = R.color.colorAccent;
        } else {
            backgroundColorID = R.color.normalBackground;
        }

        LinearLayout backgroundLayout = (LinearLayout) listItemView.findViewById(R.id.background_linear);
        backgroundLayout.setBackgroundColor(backgroundColorID);

        TextView sectionTextView = (TextView) listItemView.findViewById(R.id.section_list);
        sectionTextView.setText(currentNews.getSection());

        TextView titleTextView = (TextView) listItemView.findViewById(R.id.title_list);
        titleTextView.setText(currentNews.getTitle());

        TextView dateTextView = (TextView) listItemView.findViewById(R.id.date);
        dateTextView.setText(currentNews.getDate());

        TextView authorTextView = (TextView) listItemView.findViewById(R.id.author);
        if(currentNews.hasAuthor()){
            authorTextView.setText(currentNews.getAuthor());
            authorTextView.setVisibility(View.VISIBLE);
        }
        else{
            authorTextView.setVisibility(View.GONE);
        }

        return listItemView;
    }
}