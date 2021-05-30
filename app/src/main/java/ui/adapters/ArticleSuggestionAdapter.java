package ui.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.rohitksingh.nytimesarticles.R;
import com.rohitksingh.nytimesarticles.databinding.ItemArticleSuggestionBinding;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;
import listeners.ItemClickListener;
import models.Article;

public class ArticleSuggestionAdapter extends RecyclerView.Adapter<ArticleSuggestionAdapter.SearchSuggestionViewHolder> {

    private Context context;
    public List<Article> suggestionList;
    private ItemClickListener itemClickListener;

    public ArticleSuggestionAdapter(Context context){
        this.context = context;
        suggestionList = new ArrayList<>();
        itemClickListener = (ItemClickListener)context;
    }

    /***********************************************************************************************
     *                              Adapter methods
     **********************************************************************************************/
    @NonNull
    @Override
    public SearchSuggestionViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        ItemArticleSuggestionBinding binding = DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()),
                R.layout.item_article_suggestion, parent, false);

        return new SearchSuggestionViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull SearchSuggestionViewHolder holder, int position) {
        holder.bind(position);
    }

    @Override
    public int getItemCount() {
        return suggestionList.size();
    }


    /***********************************************************************************************
     *                              Public methods
     **********************************************************************************************/
    public void updateSuggestions(List<Article> suggestionList){
        this.suggestionList = suggestionList;
        notifyDataSetChanged();
    }

    /***********************************************************************************************
     *                              ViewHolder methods
     **********************************************************************************************/
    public class SearchSuggestionViewHolder extends RecyclerView.ViewHolder{

        private ItemArticleSuggestionBinding binding;

        public SearchSuggestionViewHolder(@NonNull ItemArticleSuggestionBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }

        public void bind(int position){
            Article suggestion = suggestionList.get(position);
            binding.setSuggestion(suggestion);
            binding.setListener(itemClickListener);
        }


    }

}
