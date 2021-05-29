package ui.activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.rohitksingh.nytimesarticles.R;
import com.rohitksingh.nytimesarticles.databinding.ActivityArticleListBinding;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import listeners.ItemClickListener;
import ui.adapters.ArticleListAdapter;
import viewmodels.ArticleListViewModel;

public class ArticleListActivity extends AppCompatActivity implements ItemClickListener {

    private SearchView searchView;
    private TextView searchTitle;
    private ArticleListAdapter adapter;
    private RecyclerView.LayoutManager layoutManager;

    private ActivityArticleListBinding binding;
    private ArticleListViewModel viewModel;

    public static final String ARTICLE_URL = "ArticleListActivity.ARTICLE_URL";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        initDataBinding();
        setUpRecyclerView();
        initViewModel();

//        setContentView(R.layout.activity_article_list);

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
        binding.setLifecycleOwner(this);
    }

    private void setUpRecyclerView(){
        adapter = new ArticleListAdapter(this);
        binding.articleListRecyclerView.setAdapter(adapter);
        layoutManager = new LinearLayoutManager(this);
        binding.articleListRecyclerView.setLayoutManager(layoutManager);
    }

    private void initViewModel(){
        ViewModelProvider.AndroidViewModelFactory factory = ViewModelProvider
                .AndroidViewModelFactory
                .getInstance(getApplication());

        viewModel = new ViewModelProvider(this, factory).get(ArticleListViewModel.class);

        observeViewModel();
    }


    private void observeViewModel(){

        viewModel.getArticlesLiveData().observe(this, articleList  -> {
            adapter.updateArticle(articleList);
        });

    }

    @Override
    public void itemClicked(String articleUrl) {
        startArticleDetailActivity(articleUrl);
    }

    public void startArticleDetailActivity(String articleUrl){
        Intent intent = new Intent(this, ArticleDetailActivity.class);
        intent.putExtra(ARTICLE_URL, articleUrl);
        startActivity(intent);
    }
}