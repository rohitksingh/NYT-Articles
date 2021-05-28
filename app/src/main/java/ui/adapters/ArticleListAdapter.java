package ui.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.rohitksingh.nytimesarticles.R;
import com.rohitksingh.nytimesarticles.databinding.ItemListArticleBinding;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;
import models.Article;

public class ArticleListAdapter extends RecyclerView.Adapter<ArticleListAdapter.ArticleViewHolder> {

    public List<Article> articleList;

    public ArticleListAdapter(List<Article> articleList){
        this.articleList = articleList;
    }

    @NonNull
    @Override
    public ArticleViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        ItemListArticleBinding binding = DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()),
                R.layout.item_list_article, parent, false);
        return new ArticleViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull ArticleViewHolder holder, int position) {
        holder.bind(position);
    }

    @Override
    public int getItemCount() {
        return articleList.size();
    }

    class ArticleViewHolder extends RecyclerView.ViewHolder{

        private ItemListArticleBinding binding;

        public ArticleViewHolder(@NonNull ItemListArticleBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }

        private void bind(int position){
            Article article = articleList.get(position);
            binding.setArticle(article);
        }
    }


}
