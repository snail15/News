package com.example.android.news;

import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private static String mKeyword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        TextView searchView = (TextView)findViewById(R.id.search_click);

        searchView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ConnectivityManager cm = (ConnectivityManager)getSystemService(Context.CONNECTIVITY_SERVICE);
                NetworkInfo networkInfo = cm.getNetworkInfo();
                if (networkInfo != null && networkInfo.isConnectedOrConnecting()){
                    EditText keywordView = (EditText)findViewById(R.id.keyword_input);
                    mKeyword = keywordView.getText().toString();
                    Intent searchViewIntent = new Intent(MainActivity.this, NewsListingActivity.class);
                    startActivity(searchViewIntent);
                }
                else{
                    Toast.makeText(MainActivity.this, "You don't have internet!!!",Toast.LENGTH_SHORT).show();
                }
            }
        });

    }

    public static String getKeyword(){
        return mKeyword;
    }
}
