package repositories;

import java.util.ArrayList;
import java.util.List;

import models.Article;

public class ArticleRepository {

    private static ArticleRepository articleRepository;
    private List<Article> articleList;

    public static ArticleRepository getInstance(){
        if(articleRepository==null){
            articleRepository = new ArticleRepository();
        }
        return articleRepository;
    }

    public List<Article> getAllArticles(){
        return getDummyList();
    }


    public List<Article> getDummyList(){

        return new ArrayList<>();

    }
}
