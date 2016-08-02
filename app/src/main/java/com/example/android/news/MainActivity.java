package com.example.android.news;

import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

public class MainActivity extends AppCompatActivity {

    private static String mKeyword;
    private EditText keywordEdit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final TextView searchView = (TextView)findViewById(R.id.search_click);
        keywordEdit = (EditText)findViewById(R.id.keyword_input);

        final ConnectivityManager cm = (ConnectivityManager)getSystemService(Context.CONNECTIVITY_SERVICE);
        final NetworkInfo networkInfo = cm.getActiveNetworkInfo();
        searchView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (networkInfo != null && networkInfo.isConnectedOrConnecting()){

                try {
                    mKeyword = URLEncoder.encode(keywordEdit.getText().toString(),"UTF-8");
                } catch (UnsupportedEncodingException e) {
                    e.printStackTrace();
                }

                    Intent searchViewIntent = new Intent(MainActivity.this, NewsListingActivity.class);
                    startActivity(searchViewIntent);
               } else{
                   Toast.makeText(MainActivity.this, R.string.noConnectionToast,Toast.LENGTH_SHORT).show();
                }
            }
        });

    }

    public static String getKeyword(){
        return mKeyword;
    }
}
