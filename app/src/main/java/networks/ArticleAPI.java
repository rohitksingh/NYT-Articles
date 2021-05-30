package networks;

import models.SearchAPIResponse;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * This class serves Article API endpoints.
 */
public interface ArticleAPI {

    @GET("svc/search/v2/articlesearch.json")
    Call<SearchAPIResponse> getSearchAPIResponse(@Query("q") String searchTerm);

}
