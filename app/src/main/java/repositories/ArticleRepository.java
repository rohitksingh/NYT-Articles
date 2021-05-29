package repositories;

import android.util.Log;

import java.util.ArrayList;
import java.util.List;

import androidx.lifecycle.MutableLiveData;
import models.Article;
import networkModels.SearchAPIResponse;
import networks.ServiceGenerator;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ArticleRepository implements Callback<SearchAPIResponse>{

    private static final String TAG = "ArticleRepository";

    private static ArticleRepository articleRepository;
    private MutableLiveData<List<Article>> articleListLiveData = new MutableLiveData<>();
    private List<Article> sugegstions;

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
        articleListLiveData.postValue(articleList);
    }

    @Override
    public void onFailure(Call<SearchAPIResponse> call, Throwable t) {
        Log.d(TAG, "search API response failed");
    }

    /***********************************************************************************************
     *                              Public methods
     **********************************************************************************************/
    public void fetchArticlesFromAPI(String searchTerm){
        getParsedDataResponse(searchTerm);
    }

    public MutableLiveData<List<Article>> getArticleLiveData(){
        return articleListLiveData;
    }

    public List<Article> getSuggestedArticles(){
        return getDummySuggestions();
    }

    /***********************************************************************************************
     *                              Private methods
     **********************************************************************************************/
    private void getParsedDataResponse(String searchTerm){

        Call<SearchAPIResponse> call = ServiceGenerator.getArticleAPI().getSearchAPIResponse(searchTerm, "OKsEwghCzAPR3kRr7Hp51cFn2tMfXWgj");
        call.enqueue(this);
    }

    private List<Article> getDummySuggestions(){


        List<Article> articleList = new ArrayList<>();

        Article a = new Article();

        a.setHeading("McGahn Is Likely to Testify Next Week on Trump’s Efforts to Obstruct Russia Inquiry");
        a.setThumbnail("images/2021/05/24/us/politics/24dc-mcgahn/merlin_143263482_5b2f3a16-eb5c-45f3-8524-596850cfa509-articleLarge.jpg");
        a.setUrl("https://www.nytimes.com/2021/05/24/us/politics/donald-mcgahn-trump-russia.html");

        articleList.add(a);
        articleList.add(a);
        articleList.add(a);
        articleList.add(a);
        articleList.add(a);
        articleList.add(a);
        articleList.add(a);
        articleList.add(a);
        articleList.add(a);


        return articleList;

    }

}
