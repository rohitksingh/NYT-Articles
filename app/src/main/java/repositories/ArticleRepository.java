package repositories;

import android.util.Log;

import java.util.ArrayList;
import java.util.List;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import models.Article;
import networkModels.SearchAPIResponse;
import networks.ServiceGenerator;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ArticleRepository {

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

    public void fetchArticlesFromAPI(String searchTerm){
        getParsedDataResponse(searchTerm);
    }

    public MutableLiveData<List<Article>> getArticleLiveData(){
        return articleListLiveData;
    }

    public List<Article> getSuggestedArticles(){
        return getDummySuggestions();
    }


//    private List<Article> getDummyList(){
//
//
//        articleList = new ArrayList<>();
//
//        Article a = new Article();
//
//        a.setHeading("McGahn Is Likely to Testify Next Week on Trump’s Efforts to Obstruct Russia Inquiry");
//        a.setThumbnail("images/2021/05/24/us/politics/24dc-mcgahn/merlin_143263482_5b2f3a16-eb5c-45f3-8524-596850cfa509-articleLarge.jpg");
//        a.setUrl("https://www.nytimes.com/2021/05/24/us/politics/donald-mcgahn-trump-russia.html");
//
//        articleList.add(a);
//        articleList.add(a);
//        articleList.add(a);
//        articleList.add(a);
//        articleList.add(a);
//        articleList.add(a);
//        articleList.add(a);
//        articleList.add(a);
//        articleList.add(a);
//
//
//        return articleList;
//
//    }


    private void getParsedDataResponse(String searchTerm){
        Call<SearchAPIResponse> call = ServiceGenerator.getArticleAPI().getSearchAPIResponse(searchTerm, "OKsEwghCzAPR3kRr7Hp51cFn2tMfXWgj");
        call.enqueue(new Callback<SearchAPIResponse>() {
            @Override
            public void onResponse(Call<SearchAPIResponse> call, Response<SearchAPIResponse> response) {

                List<Article> articleList = response.body().getArticleResponse().getArticles();

                Log.d(TAG, "Article List: " + articleList.size());

                for(Article article: articleList){
                    Log.d(TAG, "search API response: " + article.getUrl());
                    Log.d(TAG, "search API response: " + article.getMultimediaList().size());
                }

                articleListLiveData.postValue(articleList);
            }

            @Override
            public void onFailure(Call<SearchAPIResponse> call, Throwable t) {
                Log.d(TAG, "search API response failed");
            }
        });

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
