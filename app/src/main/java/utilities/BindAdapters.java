package utilities;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.rohitksingh.nytimesarticles.R;
import com.squareup.picasso.Picasso;

import androidx.databinding.BindingAdapter;

public class BindAdapters {

    private static final String TAG = "ArticleDetailActivity";
    private static final String IMAGE_BASE_URL = "https://www.nytimes.com/";

    @BindingAdapter("articleImageUrl")
    public static void loadArticleImage(ImageView imageView, String imageUrl){

        Picasso.get()
                .load(IMAGE_BASE_URL+imageUrl)
                .error(R.drawable.img_article_not_found)
                .into(imageView);
    }

    @BindingAdapter(("topicVisibility"))
    public static void setTopicVisibility(TextView textView, String topicName){
        if(topicName==null || topicName.length()==0)
            textView.setVisibility(View.GONE);
    }

}
