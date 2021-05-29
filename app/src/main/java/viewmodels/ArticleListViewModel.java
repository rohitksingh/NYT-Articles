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

    private MutableLiveData<List<Article>> articleListLiveData;
    private ArticleRepository repository;

    public ArticleListViewModel(@NonNull Application application) {
        super(application);
        articleListLiveData = new MutableLiveData<>(new ArrayList<Article>());
        repository = ArticleRepository.getInstance();
        loadArticlesFromAPI();
    }

    public LiveData<List<Article>> getArticlesLiveData(){
        return articleListLiveData;
    }

    private void loadArticlesFromAPI(){
        List<Article> articles = repository.getAllArticles();

        articleListLiveData.postValue(articles);

//        Thread t = new Thread(new Runnable() {
//            @Override
//            public void run() {
//                for(int i=0;i<6;i++){
//                    try {
//                        Thread.sleep(1000);
//                    } catch (InterruptedException e) {
//                        e.printStackTrace();
//                    }
//                }
//
//                articleListLiveData.postValue(articles);
//            }
//        });

//        t.start();


    }


}
