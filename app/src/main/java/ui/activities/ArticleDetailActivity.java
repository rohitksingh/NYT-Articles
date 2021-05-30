package ui.activities;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.rohitksingh.nytimesarticles.R;
import com.rohitksingh.nytimesarticles.databinding.ActivityArticleDetailBinding;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProvider;
import viewmodels.ArticleDetailViewModel;

public class ArticleDetailActivity extends AppCompatActivity{

    private static final String TAG = "ArticleDetailActivity";
    
    private ActivityArticleDetailBinding binding;
    private ArticleDetailViewModel viewModel;
    private String articleUrl;

    /***********************************************************************************************
     *                              Lifecycle methods
     **********************************************************************************************/
    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        initViewModel();
        initDataBinding();
        getDataFromIntent();
        setUpWebView();
    }

    /***********************************************************************************************
     *                              private methods
     **********************************************************************************************/
    private void initDataBinding(){
        binding = DataBindingUtil.setContentView(this, R.layout.activity_article_detail);
        binding.setViewmodel(viewModel);
        binding.setLifecycleOwner(this);
    }

    private void getDataFromIntent(){
        Intent intent = getIntent();
        articleUrl = intent.getStringExtra(ArticleListActivity.ARTICLE_URL);
    }

    private void setUpWebView(){

        binding.articleDetailWebView.getSettings().setJavaScriptEnabled(true);

        binding.articleDetailWebView.setWebViewClient(new WebViewClient() {

            public void onPageFinished(WebView view, String url) {
                viewModel.setIsPageLoadingLiveData(false);
            }
        });

        binding.articleDetailWebView.loadUrl(articleUrl);
    }

    private void initViewModel(){
        viewModel = new ViewModelProvider(this).get(ArticleDetailViewModel.class);
    }
}
