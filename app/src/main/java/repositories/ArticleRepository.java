package repositories;

import android.util.Log;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

import androidx.lifecycle.MutableLiveData;
import models.Article;
import models.SearchAPIResponse;
import networks.ServiceGenerator;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ArticleRepository implements Callback<SearchAPIResponse> {

    private static final String TAG = "ArticleRepository";

    private Call<SearchAPIResponse> articlesGETRequest, suggestedArticlesGETRequest;

    private static ArticleRepository articleRepository;

    private final MutableLiveData<List<Article>> articleListLiveData = new MutableLiveData<>();
    private final MutableLiveData<List<Article>> suggestedArticleListLiveData = new MutableLiveData<>();

    public static ArticleRepository getInstance() {

        if (articleRepository == null) {
            articleRepository = new ArticleRepository();
        }
        return articleRepository;
    }


    /*                              Callback methods
     **********************************************************************************************/

    @Override
    public void onResponse(Call<SearchAPIResponse> call, Response<SearchAPIResponse> response) {

        List<Article> articleList = response.body().getArticleResponse().getArticles();

        if (call == articlesGETRequest) {
            articleListLiveData.postValue(articleList);
        } else if (call == suggestedArticlesGETRequest) {
            suggestedArticleListLiveData.postValue(articleList);
        }

    }

    @Override
    public void onFailure(@NotNull Call<SearchAPIResponse> call, Throwable t) {
        Log.d(TAG, "search API response failed " + t.getMessage());
    }


    /*                              Public methods
     **********************************************************************************************/

    public MutableLiveData<List<Article>> getArticleListLiveData() {
        return articleListLiveData;
    }

    public MutableLiveData<List<Article>> getSuggestedArticleListLiveData() {
        return suggestedArticleListLiveData;
    }

    /**
     * This method is used to fetch related articles from API
     */
    public void fetchArticlesFromAPI(String searchTerm) {
        articleListLiveData.setValue(new ArrayList<>());
        articlesGETRequest = ServiceGenerator.getArticleAPI().getSearchAPIResponse(searchTerm);
        articlesGETRequest.enqueue(this);
    }

    /**
     * This method is used to suggested fetch related articles from API
     */
    public void fetchSuggestedArticlesFromAPI(String searchTerm) {
        suggestedArticlesGETRequest = ServiceGenerator.getArticleAPI().getSearchAPIResponse(searchTerm);
        suggestedArticlesGETRequest.enqueue(this);
    }

}
