package networks;

import okhttp3.HttpUrl;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ServiceGenerator {

    public static final String BASE_URL = "https://api.nytimes.com/";

    private static final String TAG = "ArticleRepository";

    public static ArticleAPI getArticleAPI() {

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .client(getHTTPClient())
                .addConverterFactory(GsonConverterFactory.create())
                .build();


        return retrofit.create(ArticleAPI.class);
    }

    private static OkHttpClient getHTTPClient() {

        return new OkHttpClient.Builder()
                .addInterceptor(chain -> {

                    Request originalRequest = chain.request();
                    HttpUrl httpUrl = originalRequest.url();

                    HttpUrl updatedHttpUrl = addAPIKey(httpUrl);

                    Request.Builder requestBuilder = originalRequest
                            .newBuilder()
                            .url(updatedHttpUrl);

                    Request modifiedRequest = requestBuilder
                            .build();

                    return chain.proceed(modifiedRequest);
                }).build();
    }

    private static HttpUrl addAPIKey(HttpUrl httpUrl) {

        return httpUrl
                .newBuilder()
                .addQueryParameter("api-key", "OKsEwghCzAPR3kRr7Hp51cFn2tMfXWgj")
                .build();
    }

}
