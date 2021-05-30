package listeners;

import models.Article;

public interface ShareActionListener {

    void emailArticle(Article article);

    void shareArticle(Article article);

}
