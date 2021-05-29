package networkModels;

import com.google.gson.annotations.SerializedName;

import java.util.List;

import models.Article;

public class ArticleResponse {

    /*/////////////////////////////////////////////////
    //MEMBERS
    /*/////////////////////////////////////////////////
    @SerializedName("docs")
    private List<Article> articles;


    /*/////////////////////////////////////////////////
    //PROPERTY
    /*/////////////////////////////////////////////////
    public List<Article> getArticles() {
        return articles;
    }
    public void setArticles(List<Article> articles) {
        this.articles = articles;
    }

}
