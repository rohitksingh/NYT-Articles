package viewmodels;

import android.app.Application;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import models.Article;
import models.Suggestion;
import repositories.ArticleRepository;
import repositories.SuggestionRepository;

public class ArticleListViewModel extends AndroidViewModel {

    private MutableLiveData<List<Article>> articleListLiveData;
    private MutableLiveData<List<Suggestion>> suggestionListLiveData;
    private ArticleRepository articleRepository;
    private SuggestionRepository suggestionRepository;

    public ArticleListViewModel(@NonNull Application application) {
        super(application);
        articleListLiveData = new MutableLiveData<>(new ArrayList<>());
        suggestionListLiveData = new MutableLiveData<>(new ArrayList<>());
        articleRepository = ArticleRepository.getInstance();
        suggestionRepository = SuggestionRepository.getInstance();
    }

    public LiveData<List<Article>> getArticlesLiveData(){
        return articleListLiveData;
    }

    public LiveData<List<Suggestion>> getSuggestionsLiveData(){
        return suggestionListLiveData;
    }

    public void loadSuggestionsFromRoom(){
        List<Suggestion> suggestions = suggestionRepository.getSuggestionList();
        suggestionListLiveData.postValue(suggestions);
    }

    public void loadArticlesFromAPI(){
        List<Article> articles = articleRepository.getAllArticles();

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
