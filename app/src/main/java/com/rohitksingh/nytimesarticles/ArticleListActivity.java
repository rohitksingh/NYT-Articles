package com.rohitksingh.nytimesarticles;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;

public class ArticleListActivity extends AppCompatActivity{

    private SearchView searchView;
    private TextView searchTitle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_article_list);

        searchView = findViewById(R.id.searchView);
        searchTitle = findViewById(R.id.searchTitle);

        searchView.setOnSearchClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                searchTitle.setVisibility(View.GONE);
            }
        });

        searchView.setOnCloseListener(new SearchView.OnCloseListener() {
            @Override
            public boolean onClose() {
                searchTitle.setVisibility(View.VISIBLE);
                return false;
            }
        });

    }

}