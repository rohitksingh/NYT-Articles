package viewmodels;

import android.app.Application;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import models.Article;
import repositories.ArticleRepository;

public class ArticleListViewModel extends AndroidViewModel {

    private MutableLiveData<List<Article>> articleMutableLiveData;
    private ArticleRepository repository;

    public ArticleListViewModel(@NonNull Application application) {
        super(application);
        articleMutableLiveData = new MutableLiveData<>(new ArrayList<Article>());
        repository = ArticleRepository.getInstance();
        loadArticlesFromAPI();
    }


    public LiveData<List<Article>> getArticlesLiveData(){
        return articleMutableLiveData;
    }

    private void loadArticlesFromAPI(){
        List<Article> articles = repository.getAllArticles();
        articleMutableLiveData.setValue(articles);
    }


}
