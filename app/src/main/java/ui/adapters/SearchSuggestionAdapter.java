package ui.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.rohitksingh.nytimesarticles.R;
import com.rohitksingh.nytimesarticles.databinding.ItemSearchSuggestionBinding;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;
import models.Suggestion;

public class SearchSuggestionAdapter extends RecyclerView.Adapter<SearchSuggestionAdapter.SearchSuggestionViewHolder> {

    public List<Suggestion> suggestionList;
    private Context context;

    public SearchSuggestionAdapter(Context context){
        this.context = context;
        suggestionList = new ArrayList<>();
    }

    @NonNull
    @Override
    public SearchSuggestionViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        ItemSearchSuggestionBinding binding = DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()),
                R.layout.item_search_suggestion, parent, false);

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

    public class SearchSuggestionViewHolder extends RecyclerView.ViewHolder{

        private ItemSearchSuggestionBinding binding;

        public SearchSuggestionViewHolder(@NonNull ItemSearchSuggestionBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }

        public void bind(int position){
            Suggestion suggestion = suggestionList.get(position);
            binding.sugggestionText.setText(suggestion.name);
        }


    }

}
