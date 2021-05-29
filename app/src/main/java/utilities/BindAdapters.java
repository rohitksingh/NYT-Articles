package utilities;

import android.widget.ImageView;

import com.rohitksingh.nytimesarticles.R;
import com.squareup.picasso.Picasso;

import androidx.databinding.BindingAdapter;

public class BindAdapters {

    private static final String TAG = "ArticleDetailActivity";

    @BindingAdapter("articleImageUrl")
    public static void loadArticleImage(ImageView imageView, String imageUrl){

        imageUrl = "https://www.nytimes.com/"+imageUrl;

        Picasso.get()
                .load(imageUrl)
                .error(R.drawable.error_image)
                .into(imageView);
    }

}
