package ui;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.rohitksingh.nytimesarticles.R;
import com.rohitksingh.nytimesarticles.databinding.ActivityArticleListBinding;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.databinding.DataBindingUtil;

public class ArticleListActivity extends AppCompatActivity{

    private SearchView searchView;
    private TextView searchTitle;

    private ActivityArticleListBinding binding;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        initDataBinding();

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

    private void initDataBinding(){
        binding = DataBindingUtil.setContentView(this, R.layout.activity_article_list);
    }

}