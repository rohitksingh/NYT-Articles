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

    private ArticleRepository articleRepository;

    private MutableLiveData<List<Article>> articleListLiveData;
    private MutableLiveData<List<Article>> suggestionListLiveData;

    public ArticleListViewModel(@NonNull Application application) {
        super(application);
        articleRepository = ArticleRepository.getInstance();
        articleListLiveData = articleRepository.getArticleListLiveData();
        suggestionListLiveData = articleRepository.getSuggestedArticleListLiveData();
    }

    /***********************************************************************************************
     *                              Getter and Setter methods
     **********************************************************************************************/
    public LiveData<List<Article>> getArticlesLiveData(){
        return articleListLiveData;
    }

    public LiveData<List<Article>> getSuggestionsLiveData(){
        return suggestionListLiveData;
    }

    /***********************************************************************************************
     *                              Public methods
     **********************************************************************************************/
    public void resetSuggestions(){
        suggestionListLiveData.setValue(new ArrayList<>());
    }

    public void resetArticles(){
        articleListLiveData.setValue(new ArrayList<>());
    }

    public void loadArticlesFromAPI(String searchTerm){
        articleRepository.fetchArticlesFromAPI(searchTerm);
    }

    public void getSuggestedArticles(String keywords){
        articleRepository.fetchSuggestedArticlesFromAPI(keywords);
    }

    //To fetch history 10 Items
    public void loadSuggestionsFromRoom(){
        //List<Article> suggestions = articleRepository.getSuggestedArticles();
        //suggestionListLiveData.postValue(suggestions);
    }

}
