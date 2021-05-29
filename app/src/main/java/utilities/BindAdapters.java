package utilities;

import android.util.Log;
import android.view.View;
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

//    @BindingAdapter("loadingVisibility")
//    public static void setAnimationVisibility(View view, boolean isVisible){
//
//        Log.d(TAG, "setAnimationVisibility: "+isVisible);
//
//        if(!isVisible){
//            view.setVisibility(View.VISIBLE);
//        }else{
//            view.setVisibility(View.GONE);
//        }
//    }
}
