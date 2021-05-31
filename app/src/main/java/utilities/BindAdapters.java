package utilities;

import android.os.Build;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.rohitksingh.nytimesarticles.BuildConfig;
import com.rohitksingh.nytimesarticles.R;
import com.squareup.picasso.Picasso;

import androidx.databinding.BindingAdapter;

public class BindAdapters {

    @BindingAdapter("articleImageUrl")
    public static void loadArticleImage(ImageView imageView, String imageUrl) {

        Picasso.get()
                .load(Globals.imgBaseUrl + imageUrl)
                .error(R.drawable.img_article_not_found)
                .into(imageView);
    }

    /**
     * This method is used to hide topic on Article List item if the topic is empty
     */
    @BindingAdapter(("topicVisibility"))
    public static void setTopicVisibility(TextView textView, String topicName) {
        if (topicName == null || topicName.length() == 0)
            textView.setVisibility(View.GONE);
    }

}
