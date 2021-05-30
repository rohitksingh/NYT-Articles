package ui.activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.rohitksingh.nytimesarticles.R;
import com.rohitksingh.nytimesarticles.databinding.ActivityArticleListBinding;


import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import listeners.ItemClickListener;

import listeners.ShareActionListener;
import models.Article;
import ui.adapters.ArticleListAdapter;
import ui.adapters.ArticleSuggestionAdapter;
import viewmodels.ArticleListViewModel;

public class ArticleListActivity extends AppCompatActivity implements ItemClickListener,
        SearchView.OnQueryTextListener, SearchView.OnCloseListener, View.OnClickListener, ShareActionListener {

    private static final String TAG = "ArticleListActivity";

    public static final String ARTICLE_URL = "ArticleListActivity.ARTICLE_URL";

    private ArticleListAdapter articleListAdapter;
    private ArticleSuggestionAdapter searchSuggestionAdapter;

    private RecyclerView.LayoutManager articleListLayoutManager;
    private RecyclerView.LayoutManager suggestionListLayoutManager;

    private ActivityArticleListBinding binding;
    private ArticleListViewModel viewModel;


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

    @Override
    public boolean onQueryTextSubmit(String searchTerm) {
        viewModel.resetSuggestions();
        fetchArticles(searchTerm);
        return false;
    }

    @Override
    public boolean onQueryTextChange(String searchTerm) {

        if(searchTerm.length()%3==0 && searchTerm.length()!=0){
            viewModel.getSuggestedArticles(searchTerm);
        }

        return false;
    }

    @Override
    public boolean onClose() {
        binding.searchTitle.setVisibility(View.VISIBLE);
        binding.companyName.setVisibility(View.VISIBLE);
        viewModel.resetSuggestions();
        viewModel.resetArticles();
        return false;
    }

    @Override
    public void onClick(View view) {
        binding.searchTitle.setVisibility(View.GONE);
        binding.companyName.setVisibility(View.GONE);
        viewModel.loadSuggestionsFromRoom();
    }

    @Override
    public void emailArticle(Article article) {
        Intent intent = new Intent(Intent.ACTION_SEND);
        intent.setType("text/html");
        intent.putExtra(Intent.EXTRA_SUBJECT, article.getHeading());
        intent.putExtra(Intent.EXTRA_TEXT, article.getUrl());
        startActivity(Intent.createChooser(intent, "Send Article"));
    }

    @Override
    public void shareArticle(Article article) {
        Intent shareIntent = new Intent(Intent.ACTION_SEND);
        shareIntent.setType("text/plain");
        shareIntent.putExtra(Intent.EXTRA_TEXT, article.getUrl());
        startActivity(Intent.createChooser(shareIntent, "Share link using"));
    }


    /***********************************************************************************************
     *                              Public methods
     **********************************************************************************************/
    public void startArticleDetailActivity(String articleUrl){
        Intent intent = new Intent(this, ArticleDetailActivity.class);
        intent.putExtra(ARTICLE_URL, articleUrl);
        startActivity(intent);
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
        viewModel.loadArticlesFromAPI(searchTerm);
    }

    private void setUpListeners(){
        binding.searchView.setOnQueryTextListener(this);
        binding.searchView.setOnSearchClickListener(this);
        binding.searchView.setOnCloseListener(this);
        binding.searchView.setOnQueryTextListener(this);
    }

}