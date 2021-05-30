package ui.activities;

import android.content.Intent;
import android.os.Bundle;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.rohitksingh.nytimesarticles.R;
import com.rohitksingh.nytimesarticles.databinding.ActivityArticleDetailBinding;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProvider;
import viewmodels.ArticleDetailViewModel;

public class ArticleDetailActivity extends AppCompatActivity {

    public static final String KEY_ARTICLE_URL = "ArticleDetailActivity.ARTICLE_URL";

    private ActivityArticleDetailBinding binding;
    private ArticleDetailViewModel viewModel;
    private String articleUrl;


    /*                              Lifecycle methods
     **********************************************************************************************/

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initViewModel();
        initDataBinding();
        getDataFromIntent();
        setUpWebView();
    }


    /*                              private methods
     **********************************************************************************************/

    /**
     * This method is used to retrieve the web url sent from ArticleListActivity
     */
    private void getDataFromIntent() {
        Intent intent = getIntent();
        articleUrl = intent.getStringExtra(KEY_ARTICLE_URL);
    }

    /**
     * This method is used to load a web page using a web url
     */
    private void setUpWebView() {

        binding.articleDetailWebView.getSettings().setJavaScriptEnabled(true);

        binding.articleDetailWebView.setWebViewClient(new WebViewClient() {
            public void onPageFinished(WebView view, String url) {
                viewModel.setIsPageLoadingLiveData(false);
            }
        });

        binding.articleDetailWebView.loadUrl(articleUrl);
    }

    private void initDataBinding() {
        binding = DataBindingUtil.setContentView(this, R.layout.activity_article_detail);
        binding.setViewmodel(viewModel);
        binding.setLifecycleOwner(this);
    }

    private void initViewModel() {
        viewModel = new ViewModelProvider(this).get(ArticleDetailViewModel.class);
    }
}
