package repositories;

import java.util.ArrayList;
import java.util.List;

import models.Article;

public class ArticleRepository {

    private static ArticleRepository articleRepository;
    private List<Article> articleList;
    private List<Article> sugegstions;

    public static ArticleRepository getInstance(){
        if(articleRepository==null){
            articleRepository = new ArticleRepository();
        }
        return articleRepository;
    }

    public List<Article> getAllArticles(){
        return getDummyList();
    }

    public List<Article> getSuggestedArticles(){
        return getDummySuggestions();
    }


    private List<Article> getDummyList(){


        articleList = new ArrayList<>();

        Article a = new Article();

        a.setHeading("McGahn Is Likely to Testify Next Week on Trump’s Efforts to Obstruct Russia Inquiry");
        a.setThumbnail("images/2021/05/24/us/politics/24dc-mcgahn/merlin_143263482_5b2f3a16-eb5c-45f3-8524-596850cfa509-articleLarge.jpg");
        a.setUrl("https://www.nytimes.com/2021/05/24/us/politics/donald-mcgahn-trump-russia.html");

        articleList.add(a);
        articleList.add(a);
        articleList.add(a);
        articleList.add(a);
        articleList.add(a);
        articleList.add(a);
        articleList.add(a);
        articleList.add(a);
        articleList.add(a);


        return articleList;

    }

    private List<Article> getDummySuggestions(){


        articleList = new ArrayList<>();

        Article a = new Article();

        a.setHeading("McGahn Is Likely to Testify Next Week on Trump’s Efforts to Obstruct Russia Inquiry");
        a.setThumbnail("images/2021/05/24/us/politics/24dc-mcgahn/merlin_143263482_5b2f3a16-eb5c-45f3-8524-596850cfa509-articleLarge.jpg");
        a.setUrl("https://www.nytimes.com/2021/05/24/us/politics/donald-mcgahn-trump-russia.html");

        articleList.add(a);
        articleList.add(a);
        articleList.add(a);
        articleList.add(a);
        articleList.add(a);
        articleList.add(a);
        articleList.add(a);
        articleList.add(a);
        articleList.add(a);


        return articleList;

    }
}
