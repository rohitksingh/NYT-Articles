package networks;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ServiceGenerator {

    //https://api.nytimes.com/svc/search/v2/articlesearch.json?q=election&api-key=OKsEwghCzAPR3kRr7Hp51cFn2tMfXWgj
    public static final String BASE_URL = "https://api.nytimes.com/";


    public static ArticleAPI getArticleAPI(){

        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        OkHttpClient client = new OkHttpClient.Builder().addInterceptor(interceptor).build();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .client(client)
                .addConverterFactory(GsonConverterFactory.create())
                .build();


        return retrofit.create(ArticleAPI.class);
    }


}
