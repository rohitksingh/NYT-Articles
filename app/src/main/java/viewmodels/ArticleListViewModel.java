package viewmodels;

import java.util.ArrayList;
import java.util.List;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;
import models.Article;
import repositories.ArticleRepository;

public class ArticleListViewModel extends ViewModel {

    private final ArticleRepository articleRepository;

    private final MutableLiveData<List<Article>> articleListLiveData;
    private final MutableLiveData<List<Article>> suggestionListLiveData;

    public ArticleListViewModel() {
        articleRepository = ArticleRepository.getInstance();
        articleListLiveData = articleRepository.getArticleListLiveData();
        suggestionListLiveData = articleRepository.getSuggestedArticleListLiveData();
    }


    /*                              Getter and Setter methods
     **********************************************************************************************/

    public LiveData<List<Article>> getArticlesLiveData() {
        return articleListLiveData;
    }

    public LiveData<List<Article>> getSuggestionsLiveData() {
        return suggestionListLiveData;
    }


    /*                              Public methods
     **********************************************************************************************/

    public void resetSuggestions() {
        suggestionListLiveData.setValue(new ArrayList<>());
    }

    public void resetArticles() {
        articleListLiveData.setValue(new ArrayList<>());
    }

    public void loadArticlesFromAPI(String searchTerm) {
        articleRepository.fetchArticlesFromAPI(searchTerm);
    }

    public void loadSuggestedArticles(String keywords) {
        articleRepository.fetchSuggestedArticlesFromAPI(keywords);
    }

}
