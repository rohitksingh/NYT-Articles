package networks;

import models.SearchAPIResponse;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface ArticleAPI {

    //TODO: Move api-key from query parameter

    @GET("svc/search/v2/articlesearch.json")
    Call<SearchAPIResponse> getSearchAPIResponse(@Query("q") String searchTerm);

}
