package ui.activities;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.rohitksingh.nytimesarticles.R;
import com.rohitksingh.nytimesarticles.databinding.ActivityArticleListBinding;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import listeners.ItemClickListener;
import ui.adapters.ArticleListAdapter;
import viewmodels.ArticleListViewModel;

public class ArticleListActivity extends AppCompatActivity implements ItemClickListener,
        SearchView.OnQueryTextListener, SearchView.OnCloseListener, View.OnClickListener {

    private ArticleListAdapter adapter;
    private RecyclerView.LayoutManager layoutManager;

    private ActivityArticleListBinding binding;
    private ArticleListViewModel viewModel;

    public static final String ARTICLE_URL = "ArticleListActivity.ARTICLE_URL";

    private static final String TAG = "ArticleListActivity";

    /***********************************************************************************************
     *                              Lefecycyle methods
     **********************************************************************************************/
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        initViewModel();
        initDataBinding();
        setUpRecyclerView();
        setUpListeners();
    }

    /***********************************************************************************************
     *                              Callback methods
     **********************************************************************************************/
    @Override
    public void itemClicked(String articleUrl) {
        startArticleDetailActivity(articleUrl);
    }

    public void startArticleDetailActivity(String articleUrl){
        Intent intent = new Intent(this, ArticleDetailActivity.class);
        intent.putExtra(ARTICLE_URL, articleUrl);
        startActivity(intent);
    }

    @Override
    public boolean onQueryTextSubmit(String s) {
        fetchArticles(s);
        return false;
    }

    @Override
    public boolean onQueryTextChange(String s) {
        return false;
    }

    @Override
    public boolean onClose() {
        Log.d(TAG, "onClose: ");
        binding.searchTitle.setVisibility(View.VISIBLE);
        binding.companyName.setVisibility(View.VISIBLE);
        return false;
    }

    @Override
    public void onClick(View view) {
        binding.searchTitle.setVisibility(View.GONE);
        binding.companyName.setVisibility(View.GONE);
    }

    /***********************************************************************************************
     *                              private methods
     **********************************************************************************************/
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

    private void fetchArticles(String searchTerm){
        viewModel.loadArticlesFromAPI();
    }

    private void setUpListeners(){
        binding.searchView.setOnQueryTextListener(this);
        binding.searchView.setOnSearchClickListener(this);
        binding.searchView.setOnCloseListener(this);
        binding.searchView.setOnQueryTextListener(this);
    }

}