package networks;

import android.util.Log;

import java.io.IOException;

import okhttp3.HttpUrl;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ServiceGenerator {

    public static final String BASE_URL = "https://api.nytimes.com/";

    private static final String TAG = "ArticleRepository";

    public static ArticleAPI getArticleAPI(){

        OkHttpClient client = new OkHttpClient.Builder()
                .addInterceptor(new Interceptor() {
                    @Override
                    public Response intercept(Chain chain) throws IOException {
                        Request original = chain.request();
                        HttpUrl httpUrl = original.url();

                        HttpUrl newHttpUrl = httpUrl
                                .newBuilder()
                                .addQueryParameter("api-key", "OKsEwghCzAPR3kRr7Hp51cFn2tMfXWgj")
                                .build();

                        Request.Builder requestBuilder = original
                                .newBuilder()
                                .url(newHttpUrl);

                        Request request = requestBuilder
                                .build();

                        return chain.proceed(request);
                    }
                }).build();


        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .client(client)
                .addConverterFactory(GsonConverterFactory.create())
                .build();


        return retrofit.create(ArticleAPI.class);
    }

    private static OkHttpClient getHTTPClient(){

        return null;
    }

}
