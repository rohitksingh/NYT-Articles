package ui.adapters;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.rohitksingh.nytimesarticles.R;
import com.rohitksingh.nytimesarticles.databinding.ItemListArticleSuggestionBinding;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;
import listeners.ItemClickListener;
import models.Article;

public class ArticleSuggestionAdapter extends RecyclerView.Adapter<ArticleSuggestionAdapter.SearchSuggestionViewHolder> {

    public List<Article> suggestionList;
    private final ItemClickListener itemClickListener;

    public ArticleSuggestionAdapter(ItemClickListener itemClickListener) {
        suggestionList = new ArrayList<>();
        this.itemClickListener = itemClickListener;
    }

    /***********************************************************************************************
     *                              Adapter methods
     **********************************************************************************************/
    @NonNull
    @Override
    public SearchSuggestionViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        ItemListArticleSuggestionBinding binding = DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()),
                R.layout.item_list_article_suggestion, parent, false);

        return new SearchSuggestionViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull SearchSuggestionViewHolder holder, int position) {
        holder.bind(position);
    }

    @Override
    public int getItemCount() {
        return (suggestionList == null) ? 0 : suggestionList.size();
    }


    /***********************************************************************************************
     *                              Public methods
     **********************************************************************************************/
    public void updateSuggestions(List<Article> suggestionList) {
        this.suggestionList = suggestionList;
        notifyDataSetChanged();
    }

    /***********************************************************************************************
     *                              ViewHolder methods
     **********************************************************************************************/
    public class SearchSuggestionViewHolder extends RecyclerView.ViewHolder {

        private final ItemListArticleSuggestionBinding binding;

        public SearchSuggestionViewHolder(@NonNull ItemListArticleSuggestionBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }

        public void bind(int position) {
            Article suggestion = suggestionList.get(position);
            binding.setSuggestion(suggestion);
            binding.setListener(itemClickListener);
        }


    }

}
