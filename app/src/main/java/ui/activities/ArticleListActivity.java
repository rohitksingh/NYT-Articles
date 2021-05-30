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


    private ArticleListAdapter articleListAdapter;
    private ArticleSuggestionAdapter searchSuggestionAdapter;

    private ActivityArticleListBinding binding;
    private ArticleListViewModel viewModel;


    /***********************************************************************************************
     *                              Lifecycle methods
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
            fetchSuggestedArticles(searchTerm);
        } else if(searchTerm.length()==0){
            viewModel.resetSuggestions();
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
    }

    @Override
    public void emailArticle(Article article) {
        Intent intent = new Intent(Intent.ACTION_SEND);
        intent.setType("text/html");
        intent.putExtra(Intent.EXTRA_SUBJECT, article.getHeading());
        intent.putExtra(Intent.EXTRA_TEXT, article.getUrl());
        startActivity(Intent.createChooser(intent, getString(R.string.email_article)));
    }

    @Override
    public void shareArticle(Article article) {
        Intent shareIntent = new Intent(Intent.ACTION_SEND);
        shareIntent.setType("text/plain");
        shareIntent.putExtra(Intent.EXTRA_TEXT, article.getUrl());
        startActivity(Intent.createChooser(shareIntent, getString(R.string.share_article)));
    }


    /***********************************************************************************************
     *                              Public methods
     **********************************************************************************************/
    public void startArticleDetailActivity(String articleUrl){
        Intent intent = new Intent(this, ArticleDetailActivity.class);
        intent.putExtra(ArticleDetailActivity.KEY_ARTICLE_URL, articleUrl);
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
        articleListAdapter = new ArticleListAdapter(this, this);
        binding.articleListRecyclerView.setAdapter(articleListAdapter);
        RecyclerView.LayoutManager articleListLayoutManager = new LinearLayoutManager(this);
        binding.articleListRecyclerView.setLayoutManager(articleListLayoutManager);
    }

    private void setUpSuggestionRecyclerView(){
        searchSuggestionAdapter = new ArticleSuggestionAdapter(this);
        binding.articleSuggestionRecyclerView.setAdapter(searchSuggestionAdapter);
        RecyclerView.LayoutManager suggestionListLayoutManager = new LinearLayoutManager(this);
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

        viewModel.getArticlesLiveData().observe(this, articleList  -> articleListAdapter.updateArticle(articleList));

        viewModel.getSuggestionsLiveData().observe(this, suggestionList -> {
            searchSuggestionAdapter.updateSuggestions(suggestionList);
            if(suggestionList.size()==0){
                binding.articleListRecyclerView.setVisibility(View.VISIBLE);
            }else{
                binding.articleListRecyclerView.setVisibility(View.GONE);
            }
        });

    }

    private void fetchArticles(String searchTerm){
        viewModel.loadArticlesFromAPI(searchTerm);
    }

    private void fetchSuggestedArticles(String searchTerm){
        viewModel.getSuggestedArticles(searchTerm);
    }

    private void setUpListeners(){
        binding.searchView.setOnQueryTextListener(this);
        binding.searchView.setOnSearchClickListener(this);
        binding.searchView.setOnCloseListener(this);
        binding.searchView.setOnQueryTextListener(this);
    }

}