package ui.activities;

import android.content.Intent;
import android.os.Bundle;
import android.webkit.WebViewClient;

import com.rohitksingh.nytimesarticles.R;
import com.rohitksingh.nytimesarticles.databinding.ActivityArticleDetailBinding;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

public class ArticleDetailActivity extends AppCompatActivity {

    private ActivityArticleDetailBinding binding;
    private String articleUrl;

    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        initDataBinding();
        getDataFromIntent();
        setUpWebView();
    }

    private void initDataBinding(){
        binding = DataBindingUtil.setContentView(this, R.layout.activity_article_detail);
    }

    private void getDataFromIntent(){
        Intent intent = getIntent();
        articleUrl = intent.getStringExtra(ArticleListActivity.ARTICLE_URL);
    }

    private void setUpWebView(){
        binding.articleDetailWebView.getSettings().setJavaScriptEnabled(true);
        binding.articleDetailWebView.setWebViewClient(new WebViewClient());
        binding.articleDetailWebView.loadUrl(articleUrl);
    }
}
