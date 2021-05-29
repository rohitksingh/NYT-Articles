package ui.activities;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Toast;

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

    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        initDataBinding();
        getDataFromIntent();
        initViewModel();
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

        binding.articleDetailWebView.setWebViewClient(new WebViewClient() {

            public void onPageFinished(WebView view, String url) {

//                binding.articleDetailWebView.setVisibility(View.VISIBLE);
//                binding.loadingAnimation.setVisibility(View.GONE);

                Log.d(TAG, "onPageFinished: ");

                Log.d(TAG, "initViewModel: before"+viewModel.getIsPageLoadingLiveData().getValue());
                viewModel.setIsPageLoadingLiveData(false);
                Log.d(TAG, "initViewModel: after"+viewModel.getIsPageLoadingLiveData().getValue());

            }
        });


        binding.articleDetailWebView.loadUrl(articleUrl);
    }

    private void initViewModel(){
        viewModel = new ViewModelProvider(this).get(ArticleDetailViewModel.class);
        Log.d(TAG, "initViewModel: "+viewModel.getIsPageLoadingLiveData().getValue());
    }
}
