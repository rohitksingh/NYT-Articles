package ui.activities;

import android.os.Bundle;

import com.rohitksingh.nytimesarticles.R;
import com.rohitksingh.nytimesarticles.databinding.ActivityArticleDetailBinding;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

public class ArticleDetailActivity extends AppCompatActivity {

    private ActivityArticleDetailBinding binding;

    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
    }


    private void initDataBinding(){
        binding = DataBindingUtil.setContentView(this, R.layout.activity_article_detail);
    }
}
