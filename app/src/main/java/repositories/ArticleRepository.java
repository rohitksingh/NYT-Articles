package repositories;

import android.util.Log;

import java.util.List;

import androidx.lifecycle.MutableLiveData;
import models.Article;
import models.SearchAPIResponse;
import networks.ServiceGenerator;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ArticleRepository implements Callback<SearchAPIResponse>{

    private static final String TAG = "ArticleRepository";
    private Call<SearchAPIResponse> articleGETCall, suggestedArticleGETCall;

    private static ArticleRepository articleRepository;
    private MutableLiveData<List<Article>> articleListLiveData = new MutableLiveData<>();
    private MutableLiveData<List<Article>> suggestedArticleLiveData = new MutableLiveData<>();

    public static ArticleRepository getInstance(){
        if(articleRepository==null){
            articleRepository = new ArticleRepository();
        }
        return articleRepository;
    }

    /***********************************************************************************************
     *                              Callback methods
     **********************************************************************************************/
    @Override
    public void onResponse(Call<SearchAPIResponse> call, Response<SearchAPIResponse> response) {
        List<Article> articleList = response.body().getArticleResponse().getArticles();

        for(Article article: articleList){
            Log.d("ArticleRepository1", "onResponse: "+article.getThumbnail());
        }

        if(call==articleGETCall){
            articleListLiveData.postValue(articleList);
        }else if(call == suggestedArticleGETCall){
            Log.d(TAG, "onResponse: ");
            suggestedArticleLiveData.postValue(articleList);
        }
    }

    @Override
    public void onFailure(Call<SearchAPIResponse> call, Throwable t) {
        Log.d(TAG, "search API response failed");
    }

    /***********************************************************************************************
     *                              Public methods
     **********************************************************************************************/
    public void fetchArticlesFromAPI(String searchTerm){
        articleGETCall = ServiceGenerator.getArticleAPI().getSearchAPIResponse(searchTerm, "OKsEwghCzAPR3kRr7Hp51cFn2tMfXWgj");
        articleGETCall.enqueue(this);
    }

    public void fetchSuggestedArticlesFromAPI(String searchTerm){
        suggestedArticleGETCall = ServiceGenerator.getArticleAPI().getSearchAPIResponse(searchTerm, "OKsEwghCzAPR3kRr7Hp51cFn2tMfXWgj");
        suggestedArticleGETCall.enqueue(this);
    }

    public MutableLiveData<List<Article>> getArticleLiveData(){
        return articleListLiveData;
    }

    public MutableLiveData<List<Article>> getSuggestedArticles(){
        return suggestedArticleLiveData;
    }

}
