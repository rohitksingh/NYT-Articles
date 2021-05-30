package networks;

import java.util.List;


import models.Article;
import models.SearchAPIResponse;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface ArticleAPI {

    //    @Headers("api-key: " + "OKsEwghCzAPR3kRr7Hp51cFn2tMfXWgj")
//    @GET("svc/search/v2/articlesearch.json")
//    Call<APIResponse> getArticles(@Query("q") String searchTerm, @Query("api-key")String apiKey);

    @GET("section/us/")
    Call<List<Article>> getTest();

    @GET("svc/search/v2/articlesearch.json")
    Call<Object> getArticlesNew(@Query("q") String searchTerm, @Query("api-key")String apiKey);


//    @GET("svc/search/v2/articlesearch.json")
//    Call<ResponseNew> getArticlesResponse(@Query("q") String searchTerm, @Query("api-key")String apiKey);
//
//    @GET("svc/search/v2/articlesearch.json")
//    Call<Object> getRawResponse(@Query("q") String searchTerm, @Query("api-key")String apiKey);
//
    @GET("svc/search/v2/articlesearch.json")
    Call<SearchAPIResponse> getSearchAPIResponse(@Query("q") String searchTerm, @Query("api-key")String apiKey);

}
