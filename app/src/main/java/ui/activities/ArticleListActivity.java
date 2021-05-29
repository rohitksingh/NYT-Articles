package ui.activities;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.rohitksingh.nytimesarticles.R;
import com.rohitksingh.nytimesarticles.databinding.ActivityArticleListBinding;

import java.util.List;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import listeners.ItemClickListener;
import models.Article;
import networkModels.SearchAPIResponse;
import networks.ServiceGenerator;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import ui.adapters.ArticleListAdapter;
import ui.adapters.ArticleSuggestionAdapter;
import viewmodels.ArticleListViewModel;

public class ArticleListActivity extends AppCompatActivity implements ItemClickListener,
        SearchView.OnQueryTextListener, SearchView.OnCloseListener, View.OnClickListener {

    private ArticleListAdapter articleListAdapter;
    private RecyclerView.LayoutManager articleListLayoutManager;
    private RecyclerView.LayoutManager suggestionListLayoutManager;

    private ArticleSuggestionAdapter searchSuggestionAdapter;

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
        setUpListeners();
        setUpArticleRecyclerView();
        setUpRecyclerViews();
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
        viewModel.resetSuggestions();
        fetchArticles(s);
        return false;
    }

    @Override
    public boolean onQueryTextChange(String s) {
        //Fetch similar suggestions
        return false;
    }

    @Override
    public boolean onClose() {
        Log.d(TAG, "onClose: ");
        binding.searchTitle.setVisibility(View.VISIBLE);
        binding.companyName.setVisibility(View.VISIBLE);
        viewModel.resetSuggestions();
        return false;
    }

    @Override
    public void onClick(View view) {
        binding.searchTitle.setVisibility(View.GONE);
        binding.companyName.setVisibility(View.GONE);
        viewModel.loadSuggestionsFromRoom();
    }

    /***********************************************************************************************
     *                              private methods
     **********************************************************************************************/
    private void initDataBinding(){
        binding = DataBindingUtil.setContentView(this, R.layout.activity_article_list);
        binding.setLifecycleOwner(this);
    }

    private void setUpRecyclerViews(){
        setUpArticleRecyclerView();
        setUpSuggestionRecyclerView();
    }

    private void setUpArticleRecyclerView(){
        articleListAdapter = new ArticleListAdapter(this);
        binding.articleListRecyclerView.setAdapter(articleListAdapter);
        articleListLayoutManager = new LinearLayoutManager(this);
        binding.articleListRecyclerView.setLayoutManager(articleListLayoutManager);
    }

    private void setUpSuggestionRecyclerView(){
        searchSuggestionAdapter = new ArticleSuggestionAdapter(this);
        binding.articleSuggestionRecyclerView.setAdapter(searchSuggestionAdapter);
        suggestionListLayoutManager = new LinearLayoutManager(this);
        binding.articleSuggestionRecyclerView.setLayoutManager(suggestionListLayoutManager);
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
            articleListAdapter.updateArticle(articleList);
        });

        viewModel.getSuggestionsLiveData().observe(this, suggestionList -> {
            searchSuggestionAdapter.updateSuggestions(suggestionList);
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