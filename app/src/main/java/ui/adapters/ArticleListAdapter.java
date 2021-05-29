package ui.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.rohitksingh.nytimesarticles.R;
import com.rohitksingh.nytimesarticles.databinding.ItemListArticleBinding;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;
import listeners.ItemClickListener;
import models.Article;

public class ArticleListAdapter extends RecyclerView.Adapter<ArticleListAdapter.ArticleViewHolder> {

    public List<Article> articleList;
    private Context context;
    private ItemClickListener itemClickListener;

    public ArticleListAdapter(Context context){
        this.context = context;
        articleList = new ArrayList<>();
        itemClickListener = (ItemClickListener)context;
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


    public void updateArticle(List<Article> articleList){
        this.articleList = articleList;
        notifyDataSetChanged();
    }

    public void updateItem(int position, Article offer){
        articleList.set(position, offer);
        notifyItemChanged(position, offer);
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
            binding.setItemClickListener(itemClickListener);
        }
    }


}
