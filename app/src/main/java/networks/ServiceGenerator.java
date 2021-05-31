package networks;

import okhttp3.HttpUrl;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import utilities.Globals;

/**
 * This class is used to create Article API instance
 */
public class ServiceGenerator {

    public static ArticleAPI getArticleAPI() {

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Globals.baseUrl)
                .client(getHTTPClient())
                .addConverterFactory(GsonConverterFactory.create())
                .build();


        return retrofit.create(ArticleAPI.class);
    }

    /**
     * This method is used to HTTP Client for HTTPS Calls
     */
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

    /**
     * This method is used to append API KEY as query parameter in each network call
     */
    private static HttpUrl addAPIKey(HttpUrl httpUrl) {

        return httpUrl
                .newBuilder()
                .addQueryParameter("api-key", Globals.apiKey)
                .build();
    }

}
